package com.softwaresecurity.gringotts;
import pojo.*;
import java.util.Random;
import dao.*;

import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
//import com.softwaresecurity.gringotts.RegistrationInput;
	
import java.util.Date;
import java.util.Locale;

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
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ExternalUserController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ExternalUserController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
		DatabaseConnectors databaseConnector = new DatabaseConnectors();
		@RequestMapping(value = "/extUserHomePage", method = RequestMethod.GET)
	public String mangrUserHomePageGet(Locale locale, ModelMap model, HttpSession session) {
			logger.info("In user account op GET");
			ExternalUser extUser = databaseConnector.getExternalUserByUniqId("EM123");
			Transactions transObj = new Transactions();
			transObj.setBalance(extUser.getBalance());
			model.addAttribute("creditOp", transObj );
			model.addAttribute("debitOp", transObj );
			model.addAttribute("transferOp",transObj);
			model.addAttribute("paymerchantOp",transObj);
			model.addAttribute("checkAccBal", transObj.getBalance() );
			model.addAttribute("savingAccBal", "500" );

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
			model.addAttribute("userName",UI.getUsername());
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
				}else if(role.equals("em")){
					return "redirect:merchantHomePage";
				}else if(role.equals("im")){
					return "redirect:managerHomePage";
				}else if(role.equals("ir")){
					return "redirect:intUserHomePage";
				}else if(role.equals("ei")){
					return "extUserHomePage";
				}
			}
			
			return "redirect:";
		
	}

			
	@RequestMapping(value = "/debit_money", method = RequestMethod.POST)
	public String debitmoneyPageAction(@ModelAttribute("debitOp") Transactions transPost, Model model, HttpSession session){
			logger.info("Inside debit money op POST");
			@SuppressWarnings("deprecation")
			//String uniqId= (String)session.getAttribute("UniqueID");
			String uniqID ="EM123";
			ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqID);
			transPost.setBalance(extUser.getBalance());
			float amount = transPost.getTransactionAmount();
			float currentBalance = transPost.getBalance();			
			
			
			if(currentBalance >= amount){
				logger.info("EU.getBalance" + transPost.getBalance());
				//debit amount from current account balance		
				transPost.setUniqId(uniqID);
				transPost.setDescription("debited amount: "+amount);
				transPost.setTransactionAmount(amount);
				transPost.setTransactionType("debit");
				transPost.setBalance(currentBalance-amount);
			
				extUser.setBalance(currentBalance-amount);
				databaseConnector.updateExternalUser(extUser);
				databaseConnector.saveTransaction(transPost);
			
			}
			
			model.addAttribute("debitOp", transPost );
			model.addAttribute("creditOp",transPost);
			model.addAttribute("checkAccBal", transPost.getBalance() );
			model.addAttribute("savingAccBal", "500" );
			model.addAttribute("transferOp",transPost);
			model.addAttribute("paymerchantOp",transPost);
			


			logger.info("Leaving debit money POST");
			
			return "extUserHomePage";
	}
			
			
	@RequestMapping(value = "/credit_money", method = RequestMethod.POST)
	public String creditmoneyPageAction(@ModelAttribute("creditOp") Transactions transactionObj, Model model, HttpSession session){
			logger.info("Inside credit money op POST");
			logger.info("Current Balance" + transactionObj.getBalance());
			//String uniqueID = (String) session.getAttribute("UniqueID");
			String uniqueID ="EM123";
			ExternalUser extUser = databaseConnector.getExternalUserByUniqId("EM123");
			transactionObj.setBalance(extUser.getBalance());
			float amount = transactionObj.getTransactionAmount();
			float currentBalance = transactionObj.getBalance();
			logger.info("balance :",currentBalance);
			//credit amount from current account balance		
			transactionObj.setUniqId(uniqueID);
			transactionObj.setDescription("credited amount: "+amount);
			transactionObj.setTransactionType("credit");
			transactionObj.setBalance(currentBalance+amount);
			extUser.setBalance(currentBalance+amount);
			databaseConnector.updateExternalUser(extUser);
			databaseConnector.saveTransaction(transactionObj);


			model.addAttribute("debitOp", transactionObj );
			model.addAttribute("creditOp",transactionObj);
			model.addAttribute("checkAccBal", transactionObj.getBalance() );
			model.addAttribute("savingAccBal", "500" );
			model.addAttribute("transferOp",transactionObj);
			model.addAttribute("paymerchantOp",transactionObj);
			model.addAttribute("checkAccBal", transactionObj.getBalance() );
			model.addAttribute("savingAccBal", "500" );


			logger.info("Leaving credit money POST");
			return "extUserHomePage";
		}	
}
