package com.softwaresecurity.gringotts;
import pojo.*;
import java.util.Random;
import dao.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
//import com.softwaresecurity.gringotts.RegistrationInput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.softwaresecurity.util.pkiGringott;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MerchantController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);
	DatabaseConnectors databaseConnector = new DatabaseConnectors();
	/**
	 * Simply selects the home view to render by returning its name.
	 */
		@RequestMapping(value = "/merchantHomePage", method = RequestMethod.GET)
	public String merchantHomePageGet(Locale locale, ModelMap model, HttpSession session) {
			logger.info("In user account op GET");
			if(session.getAttribute("uniqueid") == null){
				return "redirect:";
			}
			String uniqueid = session.getAttribute("uniqueid").toString();
			logger.info("Unique ID "+uniqueid);
			ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqueid);
			Transactions transObj = new Transactions();
			logger.info("Ext User"+extUser);
			transObj.setBalance(extUser.getBalance());
			
			TempTransactions temp = new TempTransactions();
			temp.setBalance(transObj.getBalance());
			
			model.addAttribute("creditOp", transObj );
			model.addAttribute("debitOp", transObj );
			model.addAttribute("transferOp",temp);
			model.addAttribute("requestOp", temp);
			//model.addAttribute("paymerchantOp",temp);
			model.addAttribute("checkAccBal", temp.getBalance() );
			model.addAttribute("UpdateProfileM", new UserInfo() );
			List<Transactions> obj= displaytransaction(session);
			if(obj == null){
				model.addAttribute("transactionOp",null);
			}
			else{
			model.addAttribute("transactionOp",obj);
			}
			List<TempTransactions> tempTransactions = getTempTransactions(session);
			model.addAttribute("transactionApproval",tempTransactions);
			logger.info("Trans Obj:",transObj);
			logger.info("Current Balance"+extUser.getBalance());
			
/**
 * To display user profile			
 */
			UserInfo UI = new UserInfo();
			DatabaseConnectors dbcon = new DatabaseConnectors();
			UI = dbcon.getUserInfoByUniqId((String)session.getAttribute("uniqueid"));
			
			String utype = null;
			String str1 = (String)session.getAttribute("uniqueid");
			System.out.println(str1);
			String str2 = str1.substring(0,2);
			
			if(str2.equals("ei"))
			{
				utype = "Single User";
			}
			else if(str2.equals("em"))
			{
				utype = "Merchant";
			}
			else if(str2.equals("ir"))
			{
				utype = "Internal User";
			}
			else if(str2.equals("im"))
			{
				utype = "Manager";
			}
			else if(str2.equals("admin"))
			{
				utype = "Administrator";
			}
			
			model.addAttribute("firstName",UI.getFirstName());
			model.addAttribute("lastName",UI.getLastName());
			model.addAttribute("Username",UI.getUsername());
			model.addAttribute("email",UI.getEmailId());
			model.addAttribute("streetAddress",UI.getAddress());
			model.addAttribute("city",UI.getCity());
			model.addAttribute("state",UI.getState());
			model.addAttribute("country",UI.getCountry());
			model.addAttribute("zip",UI.getZipcode());
			model.addAttribute("contactNo",UI.getContactNo());
			model.addAttribute("userType",utype);
			
			
			if(session.getAttribute("role") != null){
				String role = session.getAttribute("role").toString();
				if(role.equals("admin")){
					return "redirect:adminHomePage";
				}else if(role.equals("ei")){
					return "redirect:extUserHomePage";
				}else if(role.equals("im")){
					return "redirect:managerHomePage";
				}else if(role.equals("ir")){
					return "redirect:intUserHomePage";
				}else if(role.equals("em")){
					return "merchantHomePage";
				}
			}
			
			return "redirect:";
		
		
	}
		
		private List<TempTransactions> getTempTransactions(HttpSession session) {
			String uniqId = session.getAttribute("uniqueid").toString();
			long accountNo = databaseConnector.getAccountNoByUniqId(uniqId);
			List<TempTransactions> tempTransactions = databaseConnector.getTempTransactionsByAccountNo((int)accountNo);
			return tempTransactions;
	}

		public List<Transactions> displaytransaction(HttpSession session){
			logger.info("Inside transactions op get");
			
			String uniqueID = (String) session.getAttribute("uniqueid");
			System.out.println("uniqueID"+uniqueID);
			//String uniqueID ="EM123";
			List<Transactions> transactionObj = new ArrayList<Transactions>();
			transactionObj = databaseConnector.getTransactionsByUniqId(uniqueID);
			System.out.println(transactionObj.size());
			System.out.println("transactionObj"+transactionObj.toString());
			
			if(transactionObj.size() == 0){
				return null;
			}
			
			logger.info("Length of list :",transactionObj.size());
			Transactions temp = new Transactions();
			temp.setBalance(transactionObj.get(transactionObj.size()-1).getBalance());
		
			logger.info("Leaving transactions op POST");
			return transactionObj;
			}
		
		@RequestMapping(value = "/upate_profile_merchant", method = RequestMethod.POST)
		public String updateProfile(@ModelAttribute("UpdateProfileM") TempUserInfo TUI, Model model,
								HttpSession session){
			
			String uniqId = session.getAttribute("uniqueid").toString();
			UserInfo UI = databaseConnector.getUserInfoByUniqId(uniqId);
			
			if(!TUI.getFirstName().equals(null)){
				UI.setFirstName(TUI.getFirstName());
				logger.info("TUI.getFirstName()" + TUI.getFirstName());
			}else{
				logger.info("TUI.getFirstName() is null");
			}			
			
			if(!TUI.getLastName().equals(null)){
				UI.setLastName(TUI.getLastName());
			}
			
			if(!TUI.getEmailId().equals(null)){
				UI.setEmailId(TUI.getEmailId());
			}
			
			if(!TUI.getAddress().equals(null)){
				UI.setAddress(TUI.getAddress());
			}
			
			if(!TUI.getCity().equals(null)){
				UI.setCity(TUI.getCity());
			}
			
			if(!TUI.getState().equals(null)){
				UI.setState(TUI.getState());
			}
			
			if(!TUI.getCountry().equals(null)){
				UI.setCountry(TUI.getCountry());
			}
			
			if(TUI.getZipcode() != 0){
				UI.setZipcode(TUI.getZipcode());
			}
			
			if(!TUI.getContactNo().equals(null)){
				UI.setContactNo(TUI.getContactNo());
			}
			
			databaseConnector.updateUserInfo(UI);
			
			return "redirect:merchantHomePage";
		}
		@RequestMapping(value = "/authorizeCustomerTransaction", method = RequestMethod.GET)
		public String authorizeTransactions(HttpServletRequest request, HttpSession session){
			int size = Integer.parseInt(request.getParameter("size"));
			List<TempTransactions> tempTransaction = getTempTransactions(session);
			for(int i=0;i<size;i++){
				String action = request.getParameter("radioValues"+i);
				//System.out.println("radioValues"+i+" "+request.getParameter("radioValues"+i)+" he"+i);
				
				//System.out.println("Inside authorize transactions");
				TempTransactions approve = tempTransaction.get(i);
				if(action!=null && action.contains("approve")) {
					String uniqId = session.getAttribute("uniqueid").toString();
					float balance = databaseConnector.getBalanceByUniqId(uniqId);
					balance = balance+approve.getTransactionAmount();
					Transactions credit = new Transactions(approve.getTransactionType(), uniqId, approve.getDescription(), balance, approve.getTransactionAmount(), "Pending");
					float customerBalance = databaseConnector.getBalanceByUniqId(approve.getUniqId());
					customerBalance = customerBalance - approve.getTransactionAmount();
					Transactions debit = new Transactions(approve.getTransactionType(), approve.getUniqId(), approve.getDescription(), customerBalance, approve.getTransactionAmount(), "Pending");
					ExternalUser merchant = databaseConnector.getExternalUserByUniqId(uniqId);
					ExternalUser customer = databaseConnector.getExternalUserByUniqId(approve.getUniqId());
					merchant.setBalance(balance);
					customer.setBalance(customerBalance);
					databaseConnector.updateExternalUser(customer);
					databaseConnector.updateExternalUser(merchant);
					databaseConnector.saveTransaction(credit);
					databaseConnector.saveTransaction(debit);
				}
				databaseConnector.removeTempTransaction(approve);	
			}
			return "redirect:";
			
		}
	
		
		@RequestMapping(value = "/requestpayment", method = RequestMethod.POST)
		public String paymerchantPageAction(@ModelAttribute("requestOp") TempTransactions transactionObj, Model model, HttpSession session) throws IOException{
			logger.info("Inside request pay op POST");
			
				/**
				 	* To display user profile			
				 	*/
			UserInfo UI = new UserInfo();
			DatabaseConnectors dbcon = new DatabaseConnectors();
			UI = dbcon.getUserInfoByUniqId((String)session.getAttribute("uniqueid"));
			
			String utype = null;
			String str1 = (String)session.getAttribute("uniqueid");
			System.out.println(str1);
			String str2 = str1.substring(0,2);
			
			if(str2.equals("ei"))
			{
				utype = "Single User";
			}
			else if(str2.equals("em"))
			{
				utype = "Merchant";
			}
			else if(str2.equals("ir"))
			{
				utype = "Internal User";
			}
			else if(str2.equals("im"))
			{
				utype = "Manager";
			}
			else if(str2.equals("admin"))
			{
				utype = "Administrator";
			}
			
			model.addAttribute("firstName",UI.getFirstName());
			model.addAttribute("lastName",UI.getLastName());
			model.addAttribute("Username",UI.getUsername());
			model.addAttribute("email",UI.getEmailId());
			model.addAttribute("streetAddress",UI.getAddress());
			model.addAttribute("city",UI.getCity());
			model.addAttribute("state",UI.getState());
			model.addAttribute("country",UI.getCountry());
			model.addAttribute("zip",UI.getZipcode());
			model.addAttribute("contactNo",UI.getContactNo());
			model.addAttribute("userType",utype);
			
			
			String uniqueID = (String) session.getAttribute("uniqueid");
			logger.info("Current user"+uniqueID);
			
			ExternalUser source = dbcon.getExternalUserByUniqId(uniqueID);
			ExternalUser dest = dbcon.getExternalUserByAccNum(transactionObj.getAccountno());
			MultipartFile fileGot = transactionObj.getMpFile();
			String file_name = fileGot.getOriginalFilename();
			
			ServletContext context = session.getServletContext();
            String realContextPath = context.getRealPath("/");
			
//            String certpath = realContextPath+"/certificates/"+fileGot.getOriginalFilename();
            String certpath = realContextPath+"/certificates/"+uniqueID+"_cert.pem";
            
            String temp__2_2 = realContextPath+"/certificates";
			File temp_1_2 = new File(temp__2_2);
			if (!temp_1_2.exists())
				temp_1_2.mkdirs();
			
			File convFile = new File(certpath);
		    convFile.createNewFile(); 
		    FileOutputStream fos = new FileOutputStream(convFile); 
		    fos.write(fileGot.getBytes());
		    fos.close(); 
		    
		    if(pkiGringott.verifyCertificate(uniqueID, session)==false){
		    	logger.info(certpath);
		    	logger.info("uniqId is:" + uniqueID);
		    	logger.info("Certificate verification is failed");
		    	model.addAttribute("message", "Certificate verification failed");
		    	
		    	Transactions transObj_1 = new Transactions();
//				logger.info("Ext User"+extUser);
				//transObj.setBalance(extUser.getBalance());
				
				TempTransactions temp_1 = new TempTransactions();
				
				temp_1.setBalance(dest.getBalance());
		    	
		    	model.addAttribute("debitOp", transObj_1 );
				model.addAttribute("creditOp",transObj_1);
				model.addAttribute("checkAccBal", transObj_1.getBalance() );
				model.addAttribute("savingAccBal", "500" );
				model.addAttribute("transferOp",temp_1);
				model.addAttribute("paymerchantOp",temp_1);
				model.addAttribute("transactionOp",temp_1);
				model.addAttribute("UpdateProfile", new UserInfo() );
				List<Transactions> obj_1= displaytransaction(session);
				if(obj_1 == null){
					model.addAttribute("transactionOp",null);
				}
				else{
				model.addAttribute("transactionOp",obj_1);
				}
		    	
		    	return "extUserHomePage";
		    }else{
		    	logger.info("uniqId is:" + uniqueID);
		    	logger.info("Certificate verified");
		    }
		    
		    MultipartFile pkf = transactionObj.getPkFile();
		    
		    String pkf_name = pkf.getOriginalFilename();
		    logger.info(pkf_name);
			
//			String pkpath = realContextPath+"/privatekeys/"+pkf.getOriginalFilename(); 
			String pkpath = realContextPath+"/privatekeys/"+uniqueID+"_private.key";
			
			String temp_1 = realContextPath+"/privatekeys";
			File temp_1_1 = new File(temp_1);
			if (!temp_1_1.exists())
				temp_1_1.mkdirs();
			
			File convFile_1 = new File(pkpath);
			convFile_1.createNewFile(); 
		    FileOutputStream fos_1 = new FileOutputStream(convFile_1); 
		    fos_1.write(pkf.getBytes());
		    fos_1.close(); 
		    
		    if(pkiGringott.verifyPrivateKey(uniqueID, session)==false){
		    	logger.info(pkpath);
		    	logger.info("uniqId is:" + uniqueID);
		    	logger.info("private key verification is failed");
		    	model.addAttribute("message", "private key verification failed");
		    	
		    	Transactions transObj_2 = new Transactions();
//				logger.info("Ext User"+extUser);
				//transObj.setBalance(extUser.getBalance());
				
				TempTransactions temp_2 = new TempTransactions();
				temp_2.setBalance(transactionObj.getBalance());
		    	
		    	model.addAttribute("debitOp", transObj_2 );
				model.addAttribute("creditOp",transObj_2);
				model.addAttribute("checkAccBal", transObj_2.getBalance() );
				model.addAttribute("savingAccBal", "500" );
				model.addAttribute("transferOp",temp_2);
				model.addAttribute("paymerchantOp",temp_2);
				model.addAttribute("transactionOp",temp_2);
				model.addAttribute("UpdateProfile", new UserInfo() );
				List<Transactions> obj_2= displaytransaction(session);
				if(obj_2 == null){
					model.addAttribute("transactionOp",null);
				}
				else{
				model.addAttribute("transactionOp",obj_2);
				}
		    	
		    	return "redirect:";
		    }else{
		    	logger.info("uniqId is:" + uniqueID);
		    	logger.info("Private key verified");
		    }
			
			
			ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqueID);
			transactionObj.setBalance(dest.getBalance());
			logger.info("Current Balance" + transactionObj.getBalance());
			float amount = transactionObj.getTransactionAmount();
			float currentBalance = transactionObj.getBalance();
			logger.info("balance :",currentBalance);
			logger.info("account number ",transactionObj.getAccountno());
			//credit amount from current account balance	
	
			transactionObj.setUniqId(uniqueID);
			transactionObj.setDescription("transferred amount from user : "+amount);
			transactionObj.setTransactionType("tranfer");
			transactionObj.setBalance(currentBalance-amount);
			
			
			dest.setBalance(currentBalance-amount);
			databaseConnector.updateExternalUser(dest);
			databaseConnector.saveTempTransaction(transactionObj);
			Transactions temp = new Transactions();
			temp.setBalance(source.getBalance());
			model.addAttribute("debitOp", temp );
			model.addAttribute("creditOp",temp);
			model.addAttribute("checkAccBal", temp.getBalance() );
			model.addAttribute("transferOp",transactionObj);
			model.addAttribute("paymerchantOp",transactionObj);
			model.addAttribute("UpdateProfile", new UserInfo() );
			List<Transactions> obj= displaytransaction(session);
			if(obj == null){
				model.addAttribute("transactionOp",null);
			}
			else{
			model.addAttribute("transactionOp",obj);
			}

			logger.info("Leaving transfer money POST");
			return "extUserHomePage";
		}
}
