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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
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
import org.springframework.web.servlet.ModelAndView;

import com.softwaresecurity.util.GenerateOtp;
import com.softwaresecurity.util.StatementGenerator;
import com.softwaresecurity.util.pkiGringott;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Handles requests for the application home page.
 */
@Controller
public class ExternalUserController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ExternalUserController.class);

	private static final int ONE_MINUTE_IN_MILLIS = 60000;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
		DatabaseConnectors databaseConnector = new DatabaseConnectors();
		@RequestMapping(value = "/extUserHomePage", method = RequestMethod.GET)
	public String mangrUserHomePageGet(Locale locale, ModelMap model, HttpSession session) {
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
			model.addAttribute("paymerchantOp",temp);
			model.addAttribute("checkAccBal", temp.getBalance() );
			model.addAttribute("UpdateProfile", new UserInfo() );
			List<Transactions> obj= displaytransaction(session);
			if(obj == null){
				model.addAttribute("transactionOp",null);
			}
			else{
			model.addAttribute("transactionOp",obj);
			}
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
			model.addAttribute("accountno",extUser.getAccountno());
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
					String uniqId= (String)session.getAttribute("uniqueid");
					
					ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqId);
					transPost.setBalance(extUser.getBalance());
					float amount = transPost.getTransactionAmount();
					float currentBalance = transPost.getBalance();
					
					
					
					if(currentBalance >= amount){
					logger.info("EU.getBalance" + transPost.getBalance());
					//debit amount from current account balance		
					transPost.setUniqId(uniqId);
					transPost.setDescription("debited amount: "+amount);
					transPost.setTransactionAmount(amount);
					transPost.setTransactionType("debit");
					transPost.setBalance(currentBalance-amount);
					transPost.setStatus("Pending");
				
					extUser.setBalance(currentBalance-amount);
					
					databaseConnector.updateExternalUser(extUser);
					databaseConnector.saveTransaction(transPost);
					
					}
					TempTransactions temp = new TempTransactions();
					temp.setBalance(transPost.getBalance());
					model.addAttribute("debitOp", transPost );
					model.addAttribute("creditOp",transPost);
					model.addAttribute("checkAccBal", transPost.getBalance() );
					model.addAttribute("transferOp",temp);
					model.addAttribute("paymerchantOp",temp);
					model.addAttribute("UpdateProfile", new UserInfo() );
					List<Transactions> obj= displaytransaction(session);
					if(obj == null){
						model.addAttribute("transactionOp",null);
					}
					else{
					model.addAttribute("transactionOp",obj);
					}

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
					model.addAttribute("accountno",extUser.getAccountno());
					model.addAttribute("streetAddress",UI.getAddress());
					model.addAttribute("city",UI.getCity());
					model.addAttribute("state",UI.getState());
					model.addAttribute("country",UI.getCountry());
					model.addAttribute("zip",UI.getZipcode());
					model.addAttribute("contactNo",UI.getContactNo());
					model.addAttribute("userType",utype);
					
					logger.info("Leaving debit money POST");
					
					return "extUserHomePage";
			}
			
			@RequestMapping(value = "/credit_money", method = RequestMethod.POST)
			public String creditmoneyPageAction(@ModelAttribute("creditOp") Transactions transactionObj, Model model, HttpSession session){
					logger.info("Inside credit money op POST");
					logger.info("Current Balance" + transactionObj.getBalance());
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
					//String uniqueID ="EM123";
					ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqueID);
					
					model.addAttribute("accountno",extUser.getAccountno());
					
					transactionObj.setBalance(extUser.getBalance());
					float amount = transactionObj.getTransactionAmount();
					float currentBalance = transactionObj.getBalance();
					logger.info("balance :",currentBalance);
					//credit amount from current account balance		
					transactionObj.setUniqId(uniqueID);
					transactionObj.setDescription("credited amount: "+amount);
					transactionObj.setTransactionType("credit");
					transactionObj.setBalance(currentBalance+amount);
					transactionObj.setStatus("Pending");
					extUser.setBalance(currentBalance+amount);
					databaseConnector.updateExternalUser(extUser);
					databaseConnector.saveTransaction(transactionObj);
					
					TempTransactions temp = new TempTransactions();
					temp.setBalance(transactionObj.getBalance());

					model.addAttribute("debitOp", transactionObj );
					model.addAttribute("creditOp",transactionObj);
					model.addAttribute("checkAccBal", transactionObj.getBalance() );
					model.addAttribute("transferOp",temp);
					model.addAttribute("paymerchantOp",temp);
					model.addAttribute("UpdateProfile", new UserInfo() );
					List<Transactions> obj= displaytransaction(session);
					if(obj == null){
						model.addAttribute("transactionOp",null);
					}
					else{
					model.addAttribute("transactionOp",obj);
					}
					


					logger.info("Leaving credit money POST");
					return "extUserHomePage";
					}
			
			
			@RequestMapping(value = "/transfer_money", method = RequestMethod.POST)
			public String transfermoneyPageAction(@ModelAttribute("transferOp") TempTransactions transObj, Model model, HttpSession session) throws Exception{
				logger.info("Inside transfer money op POST");
				@SuppressWarnings("deprecation")
				String uniqId= (String)session.getAttribute("uniqueid");
				
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
				
				ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqId);
				
				model.addAttribute("firstName",UI.getFirstName());
				model.addAttribute("lastName",UI.getLastName());
				model.addAttribute("Username",UI.getUsername());
				model.addAttribute("email",UI.getEmailId());
				model.addAttribute("accountno",extUser.getAccountno());
				model.addAttribute("streetAddress",UI.getAddress());
				model.addAttribute("city",UI.getCity());
				model.addAttribute("state",UI.getState());
				model.addAttribute("country",UI.getCountry());
				model.addAttribute("zip",UI.getZipcode());
				model.addAttribute("contactNo",UI.getContactNo());
				model.addAttribute("userType",utype);
				
				
				MultipartFile fileGot = transObj.getMpFile();
				String file_name = fileGot.getOriginalFilename();
				
				ServletContext context = session.getServletContext();
	            String realContextPath = context.getRealPath("/");
				
//	            String certpath = realContextPath+"/certificates/"+fileGot.getOriginalFilename();
	            String certpath = realContextPath+"/certificates/"+uniqId+"_cert.pem";
				
	            String temp__2_2 = realContextPath+"/certificates";
				File temp_1_2 = new File(temp__2_2);
				if (!temp_1_2.exists())
					temp_1_2.mkdirs();
	            
				File convFile = new File(certpath);
			    convFile.createNewFile(); 
			    FileOutputStream fos = new FileOutputStream(convFile); 
			    fos.write(fileGot.getBytes());
			    fos.close(); 
			    
			    if(pkiGringott.verifyCertificate(uniqId, session)==false){
			    	logger.info(certpath);
			    	logger.info("uniqId is:" + uniqId);
			    	logger.info("Certificate verification is failed");
			    	model.addAttribute("message", "Certificate verification failed");
			    
			    	Transactions transObj_1 = new Transactions();
//					logger.info("Ext User"+extUser);
					//transObj.setBalance(extUser.getBalance());
					
					TempTransactions temp_1 = new TempTransactions();
					temp_1.setBalance(transObj.getBalance());
			    	
			    	model.addAttribute("debitOp", transObj_1 );
					model.addAttribute("creditOp",transObj_1);
					model.addAttribute("checkAccBal", transObj_1.getBalance() );

					model.addAttribute("transferOp",temp_1);
					model.addAttribute("paymerchantOp",temp_1);
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
			    	logger.info("uniqId is:" + uniqId);
			    	logger.info("Certificate verified");
			    }
			    
			    MultipartFile pkf = transObj.getPkFile();
			    
			    String pkf_name = pkf.getOriginalFilename();
			    logger.info(pkf_name);
				
//				String pkpath = realContextPath+"/privatekeys/"+pkf.getOriginalFilename();
				String pkpath = realContextPath+"/privatekeys/"+uniqId+"_private.key";
				
				String temp_1 = realContextPath+"/privatekeys";
				File temp_1_1 = new File(temp_1);
				if (!temp_1_1.exists())
					temp_1_1.mkdirs();
				
				
				File convFile_1 = new File(pkpath);
				
				
				
				convFile_1.createNewFile(); 
			    FileOutputStream fos_1 = new FileOutputStream(convFile_1); 
			    fos_1.write(pkf.getBytes());
			    fos_1.close(); 
			    
			    if(pkiGringott.verifyPrivateKey(uniqId, session)==false){
			    	logger.info(pkpath);
			    	logger.info("uniqId is:" + uniqId);
			    	logger.info("private key verification is failed");
			    	model.addAttribute("message", "private key verification failed");
			    	
			    	Transactions transObj_2 = new Transactions();
//					logger.info("Ext User"+extUser);
					//transObj.setBalance(extUser.getBalance());
					
					TempTransactions temp_2 = new TempTransactions();
					temp_2.setBalance(transObj.getBalance());
			    	
			    	model.addAttribute("debitOp", transObj_2 );
					model.addAttribute("creditOp",transObj_2);
					model.addAttribute("checkAccBal", transObj_2.getBalance() );
			
					model.addAttribute("transferOp",temp_2);
					model.addAttribute("paymerchantOp",temp_2);
					model.addAttribute("UpdateProfile", new UserInfo() );
				
					List<Transactions> obj_2= displaytransaction(session);
					if(obj_2 == null){
						model.addAttribute("transactionOp",null);
					}
					else{
						model.addAttribute("transactionOp",obj_2);
					}
			    	
			    	
			    	return "extUserHomePage";
			    }else{
			    	logger.info("uniqId is:" + uniqId);
			    	logger.info("Private key verified");
			    }
				
				
				
				
				UserInfo extInfo = databaseConnector.getUserInfoByUniqId(extUser.getUniqId());
				
				session.setAttribute("transAccntNo", transObj.getAccountno());
				
				logger.info("Inside credit part of transfer money op POST");
				//String uniqueID = (String)session.getAttribute("uniqueid");
				//String uniqueID ="EM123";
				OtpTransactions transPost2 = new OtpTransactions();
				ExternalUser extUser2 = databaseConnector.getExternalUserByAccNum(transObj.getAccountno());
				OtpTransactions transPost = new OtpTransactions();
				transPost.setBalance(extUser.getBalance());
				transObj.setBalance(extUser.getBalance());
				float amount = transObj.getTransactionAmount();
				float currentBalance = transObj.getBalance();
				
				if(extUser2 == null) {
					model.addAttribute("message","Account number not found");
					model.addAttribute("debitOp", transPost );
					model.addAttribute("creditOp",transPost);
					model.addAttribute("checkAccBal", extUser.getBalance() );
					model.addAttribute("transferOp",transObj);
					model.addAttribute("paymerchantOp",transObj);
					model.addAttribute("UpdateProfile", new UserInfo() );
					List<Transactions> obj= displaytransaction(session);
					if(obj == null){
						model.addAttribute("transactionOp",null);
					}
					else{
					model.addAttribute("transactionOp",obj);
					
					}
					return "extUserHomePage";
				}
				
				databaseConnector.deleteOtpTransactionById(uniqId);
				databaseConnector.deleteOtpTransactionById(extUser2.getUniqId());		
				
				
				
				if(currentBalance >= amount){
					logger.info("EU.getBalance" + transPost.getBalance());
					//debit amount from current account balance		
					transPost.setUniqId(uniqId);
					transPost.setDescription("debited amount: "+amount);
					transPost.setTransactionAmount(amount);
					transPost.setTransactionType("debit");
					transPost.setBalance(currentBalance-amount);
				
					//extUser.setBalance(currentBalance-amount);
					//databaseConnector.updateExternalUser(extUser);
					databaseConnector.saveOtpTransaction(transPost);
				
				
				
				
				session.setAttribute("recipient", extUser2.getUniqId().toString());
				float currentBalance1 = extUser2.getBalance();
				logger.info("Current Balance" + currentBalance1);
				transPost2.setBalance(extUser.getBalance());
				logger.info("balance :"+currentBalance1);
				//credit amount from current account balance		
				transPost2.setUniqId(extUser2.getUniqId());
				transPost2.setDescription("credited amount: "+amount);
				transPost2.setTransactionAmount(amount);
				transPost2.setTransactionType("credit");
				transPost2.setBalance(currentBalance1+amount);
				//extUser2.setBalance(currentBalance1+amount);
				//databaseConnector.updateExternalUser(extUser2);
				databaseConnector.saveOtpTransaction(transPost2);
				

				model.addAttribute("debitOp", transPost );
				model.addAttribute("creditOp",transPost);
				model.addAttribute("checkAccBal", extUser.getBalance() );
				model.addAttribute("transferOp",transObj);
				model.addAttribute("paymerchantOp",transObj);
				model.addAttribute("UpdateProfile", new UserInfo() );
				List<Transactions> obj= displaytransaction(session);
				if(obj == null){
					model.addAttribute("transactionOp",null);
				}
				else{
				model.addAttribute("transactionOp",obj);
				
				}
				
				}else{
					model.addAttribute("message","Amount cannot be more than the balance");
				}
				Random rand = new Random();
				int randomNum = rand.nextInt(737568)+256846;
				String IV = Integer.toString(randomNum);
				System.out.println("Random number (IV): "+ IV);

			 	String app1Hash;
				String app1Password;

			 	//counter starts at 0 - no clicks yet
				int app1Counter=0;
				 
				Hashtable<String,Integer> h = new Hashtable<String, Integer>();

				//do first run with intialization vector
				GenerateOtp firstApp = new GenerateOtp();
			 	app1Hash = firstApp.genHash(IV);
				app1Password = firstApp.genPassword(app1Hash); 	

				
				System.out.println(IV);
			for(int i = 0; i < 1; i++) {
			 		app1Hash = firstApp.genHash(app1Hash); //send old hash as seed for next sha hash
					app1Password = firstApp.genPassword(app1Hash); //new OTP will be calculated using the new hash
					
					if(!h.containsKey(app1Password)){
						h.put(app1Password, 0);
						app1Counter++;
					}	
					System.out.println("app1 OTP: " + app1Password);
					System.out.println(app1Counter);
				}
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session1 = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("gringottsbank14@gmail.com","softwaresecurity");
					}
				});
			

			try {

				Message message = new MimeMessage(session1);
				message.setFrom(new InternetAddress("gringottsbank14@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(extInfo.getEmailId()));
				message.setSubject("One Time Password - Gringotts Bank");
				message.setText("Dear User,"+
						"\n\n OTP for your account is as follows:"+" "+app1Password+"."+"\n\n Regards,"+"\n\n Gringotts Bank");

				Transport.send(message);


			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			
			/* code for sending otp on button click ends here */
			
			/* Code for saving OTP */
			
			
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date1 = new Date();
			String initdate = dateFormat1.format(date1);
				
			long t=date1.getTime();
			Date afterAddingTenMins=new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
			String exptime = dateFormat1.format(afterAddingTenMins);
			
			String username = session.getAttribute("username").toString();
			
			//System.out.println(dateFormat1.format(date1)); //2014/08/06 15:59:48
				
				OneTimePass l = new OneTimePass(username,initdate,exptime,Integer.parseInt(app1Password));
				DatabaseConnectors d = new DatabaseConnectors();
				
				d.deleteOtpByUsername(username);
				d.saveOTP(l);
				
				/* CODE FOR SAVING OTP */
				


				logger.info("Leaving transfer money POST");
				return "redirect:confirmOtp";
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

			
			@RequestMapping(value = "/authorizeViewTransaction", method = RequestMethod.POST)
			public String authorizeViewTransactions(Model model, HttpSession session) throws IOException{
				String uniqId = session.getAttribute("uniqueid").toString();
				ExternalUser externalUser = databaseConnector.getExternalUserByUniqId(uniqId);
				externalUser.setAuthtrans("y");
				databaseConnector.updateExternalUser(externalUser);
				return "redirect:";
			}
			
			
			@RequestMapping(value = "/pay_merchant", method = RequestMethod.POST)
			public String paymerchantPageAction(@ModelAttribute("paymerchantOp") TempTransactions transactionObj, Model model, HttpSession session) throws IOException{
				logger.info("Inside pay merchant op POST");
				
	/**
	 * To display user profile			
	 */
				UserInfo UI = new UserInfo();
				DatabaseConnectors dbcon = new DatabaseConnectors();
				UI = dbcon.getUserInfoByUniqId((String)session.getAttribute("uniqueid"));
				String uniqueID = (String) session.getAttribute("uniqueid");
				ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqueID);
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
				model.addAttribute("accountno",extUser.getAccountno());
				model.addAttribute("streetAddress",UI.getAddress());
				model.addAttribute("city",UI.getCity());
				model.addAttribute("state",UI.getState());
				model.addAttribute("country",UI.getCountry());
				model.addAttribute("zip",UI.getZipcode());
				model.addAttribute("contactNo",UI.getContactNo());
				model.addAttribute("userType",utype);
				
				
				logger.info("Current user"+uniqueID);
				
				MultipartFile fileGot = transactionObj.getMpFile();
				String file_name = fileGot.getOriginalFilename();
				
				ServletContext context = session.getServletContext();
	            String realContextPath = context.getRealPath("/");
				
//	            String certpath = realContextPath+"/certificates/"+fileGot.getOriginalFilename();
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
//					logger.info("Ext User"+extUser);
					//transObj.setBalance(extUser.getBalance());
					
					TempTransactions temp_1 = new TempTransactions();
					temp_1.setBalance(transactionObj.getBalance());
			    	
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
				
//				String pkpath = realContextPath+"/privatekeys/"+pkf.getOriginalFilename(); 
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
//					logger.info("Ext User"+extUser);
					//transObj.setBalance(extUser.getBalance());
					
					TempTransactions temp_2 = new TempTransactions();
					temp_2.setBalance(transactionObj.getBalance());
			    	
			    	model.addAttribute("debitOp", transObj_2 );
					model.addAttribute("creditOp",transObj_2);
					model.addAttribute("checkAccBal", transObj_2.getBalance() );
					//model.addAttribute("savingAccBal", "500" );
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
			    	
			    	return "extUserHomePage";
			    }else{
			    	logger.info("uniqId is:" + uniqueID);
			    	logger.info("Private key verified");
			    }
				
				
				
				transactionObj.setBalance(extUser.getBalance());
				logger.info("Current Balance" + transactionObj.getBalance());
				float amount = transactionObj.getTransactionAmount();
				float currentBalance = transactionObj.getBalance();
				logger.info("balance :",currentBalance);
				logger.info("account number ",transactionObj.getAccountno());
				//credit amount from current account balance	
				
				ExternalUser merch = databaseConnector.getExternalUserByAccNum(transactionObj.getAccountno());
				if(merch == null){
					model.addAttribute("message","Account number is invalid");
					Transactions transObj_2 = new Transactions();
//					logger.info("Ext User"+extUser);
					//transObj.setBalance(extUser.getBalance());
					
					TempTransactions temp_2 = new TempTransactions();
					temp_2.setBalance(transactionObj.getBalance());
			    	
			    	model.addAttribute("debitOp", transObj_2 );
					model.addAttribute("creditOp",transObj_2);
					model.addAttribute("checkAccBal", transObj_2.getBalance() );
					//model.addAttribute("savingAccBal", "500" );
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
			    	
					return "extUserHomePage";
				}
		
				transactionObj.setUniqId(uniqueID);
				transactionObj.setDescription("transferred amount: "+amount);
				transactionObj.setTransactionType("tranfer");
				transactionObj.setBalance(currentBalance-amount);
				
				
				//extUser.setBalance(currentBalance-amount);
				//databaseConnector.updateExternalUser(extUser);
				databaseConnector.saveTempTransaction(transactionObj);

				Transactions temp = new Transactions();
				temp.setBalance(transactionObj.getBalance());
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
			@RequestMapping(value = "/download", method = RequestMethod.GET)
			public void downloadStatement(HttpSession session, HttpServletResponse response,Model model) throws IOException {
		    	String uniqId = session.getAttribute("uniqueid").toString();
		    	StatementGenerator.statementbyuniqid(uniqId,session);
		    	ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqId);
				TempTransactions transactionObj = new TempTransactions();
				transactionObj.setBalance(extUser.getBalance());
				logger.info("Current Balance" + transactionObj.getBalance());
				float amount = transactionObj.getTransactionAmount();
				float currentBalance = transactionObj.getBalance();
				logger.info("balance :",currentBalance);
				logger.info("account number ",transactionObj.getAccountno());
				//credit amount from current account balance	
		
				transactionObj.setUniqId(uniqId);
				transactionObj.setDescription("transferred amount: "+amount);
				transactionObj.setTransactionType("tranfer");
				transactionObj.setBalance(currentBalance-amount);
				
				
				extUser.setBalance(currentBalance-amount);

				Transactions temp = new Transactions();
				temp.setBalance(transactionObj.getBalance());
				model.addAttribute("debitOp", temp );
				model.addAttribute("creditOp",temp);
				model.addAttribute("checkAccBal", temp.getBalance() );
				model.addAttribute("transferOp",transactionObj);
				model.addAttribute("paymerchantOp",transactionObj);
				List<Transactions> obj= displaytransaction(session);
				model.addAttribute("transactionOp",obj);
		        // get absolute path of the application
				ServletContext context = session.getServletContext();
                
		        String realContextPath = context.getRealPath("/");
		        String fullpath = realContextPath+"/statement/"+uniqId+"_statement.pdf";
		      //  System.out.println("aPath = " +realContextPath);
		       // String filePath="Statement.pdf";
		        // construct the complete absolute path of the file
		      //  String fullPath = realContextPath+filePath; 
		        System.out.println(fullpath);
		        
		        
		        File downloadFile = new File(fullpath);
		        FileInputStream inputStream = new FileInputStream(downloadFile);
		        
		        // get MIME type of the file
		        String mimeType = context.getMimeType(fullpath);
		        if (mimeType == null) {
		            // set to binary type if MIME mapping not found
		            mimeType = "application/pdf";
		        }
		        System.out.println("MIME type: " + mimeType);
		 
		        // set content attributes for the response
		        response.setContentType(mimeType);
		        response.setContentLength((int) downloadFile.length());
		 
		        // set headers for the response
		        String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"",
		                downloadFile.getName());
		        response.setHeader(headerKey, headerValue);
		 
		        
		        OutputStream outStream = response.getOutputStream();
		        
		        byte[] buffer = new byte[4096];
		        int bytesRead = -1;
		 
		        // write bytes read from the input stream into the output stream
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		 
		        inputStream.close();
		        outStream.close();
		        
		        
//		        
//					//ServletOutputStream out = response.getOutputStream();
//					FileOutputStream fos = new FileOutputStream(downloadFile);
//						System.out.println("Adding " + downloadFile.getName());
//
//						// Get the file
//						FileInputStream fis = null;
//						try {
//							fis = new FileInputStream(downloadFile);
//
//						} catch (FileNotFoundException fnfe) {
//							// If the file does not exists, write an error entry instead of
//							// file
//							// contents
//							fos.write(("ERROR could not find file " + downloadFile.getName())
//									.getBytes());
//							fos.close();
//							System.out.println("Couldfind file "
//									+ downloadFile.getAbsolutePath());
//						}
//
//						BufferedInputStream fif = new BufferedInputStream(fis);
//
//						// Write the contents of the file
//						int data = 0;
//						while ((data = fif.read()) != -1) {
//							fos.write(data);
//						}
//						fif.close();
//
//						fos.close();
						System.out.println("Finished Downloading file " + downloadFile.getName());
						return;
						//return "redirect:extUserHomePage";
		}
			
			@RequestMapping(value = "/upate_profile", method = RequestMethod.POST)
			public String updateProfile(@ModelAttribute("UpdateProfile") TempUserInfo TUI, Model model,
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
				return "redirect:extUserHomePage";
			}
			
			
			
}