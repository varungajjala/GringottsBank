package com.softwaresecurity.gringotts;
import pojo.*;
import java.util.Random;
import dao.*;

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

import com.softwaresecurity.util.GenerateOtp;
import com.softwaresecurity.util.StatementGenerator;

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
					List<Transactions> obj= displaytransaction(session);
					if(obj == null){
						model.addAttribute("transactionOp",null);
					}
					else{
					model.addAttribute("transactionOp",obj);
					}

					logger.info("Leaving debit money POST");
					
					return "extUserHomePage";
			}
			
			@RequestMapping(value = "/credit_money", method = RequestMethod.POST)
			public String creditmoneyPageAction(@ModelAttribute("creditOp") Transactions transactionObj, Model model, HttpSession session){
					logger.info("Inside credit money op POST");
					logger.info("Current Balance" + transactionObj.getBalance());
					String uniqueID = (String) session.getAttribute("uniqueid");
					//String uniqueID ="EM123";
					ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqueID);
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
					
					TempTransactions temp = new TempTransactions();
					temp.setBalance(transactionObj.getBalance());

					model.addAttribute("debitOp", transactionObj );
					model.addAttribute("creditOp",transactionObj);
					model.addAttribute("checkAccBal", transactionObj.getBalance() );
					model.addAttribute("transferOp",temp);
					model.addAttribute("paymerchantOp",temp);
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
				
				ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqId);
				
				OtpTransactions transPost = new OtpTransactions();
				transPost.setBalance(extUser.getBalance());
				transObj.setBalance(extUser.getBalance());
				float amount = transObj.getTransactionAmount();
				float currentBalance = transObj.getBalance();
				
				
				
				if(currentBalance >= amount){
					logger.info("EU.getBalance" + transPost.getBalance());
					//debit amount from current account balance		
					transPost.setUniqId(uniqId);
					transPost.setDescription("debited amount: "+amount);
					transPost.setTransactionAmount(amount);
					transPost.setTransactionType("debit");
					transPost.setBalance(currentBalance-amount);
				
					extUser.setBalance(currentBalance-amount);
					databaseConnector.updateExternalUser(extUser);
					databaseConnector.saveOtpTransaction(transPost);
				
				
				
				session.setAttribute("transAccntNo", transObj.getAccountno());
				
				logger.info("Inside credit part of transfer money op POST");
				//String uniqueID = (String)session.getAttribute("uniqueid");
				//String uniqueID ="EM123";
				OtpTransactions transPost2 = new OtpTransactions();
				ExternalUser extUser2 = databaseConnector.getExternalUserByAccNum(transObj.getAccountno());
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
				extUser2.setBalance(currentBalance1+amount);
				databaseConnector.updateExternalUser(extUser2);
				databaseConnector.saveOtpTransaction(transPost2);
				

				model.addAttribute("debitOp", transPost );
				model.addAttribute("creditOp",transPost);
				model.addAttribute("checkAccBal", extUser.getBalance() );
				model.addAttribute("transferOp",transObj);
				model.addAttribute("paymerchantOp",transObj);
				List<Transactions> obj= displaytransaction(session);
				if(obj == null){
					model.addAttribute("transactionOp",null);
				}
				else{
				model.addAttribute("transactionOp",obj);
				
				}
				
				}
				Random rand = new Random();
				int randomNum = rand.nextInt(737568)+256846;
				String IV = Integer.toString(randomNum);
				System.out.println("Random number (IV): "+ IV);
				//String IVtest = "123456";
				
				//String test2 = "5aba1db3b561abe65a12fd109b50ca5ecfc88e5d106d4b511c7653b843d0e3d4";
			 	//SecureRandom randomGenerator = new SecureRandom();
				//byte[] randomNumber = new byte[20];
				//randomGenerator.nextBytes(randomNumber);
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
						InternetAddress.parse("gajjala.varun@gmail.com"));
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

			
			@RequestMapping(value = "/pay_merchant", method = RequestMethod.POST)
			public String paymerchantPageAction(@ModelAttribute("paymerchantOp") TempTransactions transactionObj, Model model, HttpSession session){
				logger.info("Inside pay merchant op POST");
				
				String uniqueID = (String) session.getAttribute("uniqueid");
				logger.info("Current user"+uniqueID);
				
				ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqueID);
				transactionObj.setBalance(extUser.getBalance());
				logger.info("Current Balance" + transactionObj.getBalance());
				float amount = transactionObj.getTransactionAmount();
				float currentBalance = transactionObj.getBalance();
				logger.info("balance :",currentBalance);
				logger.info("account number ",transactionObj.getAccountno());
				//credit amount from current account balance	
		
				transactionObj.setUniqId(uniqueID);
				transactionObj.setDescription("transferred amount: "+amount);
				transactionObj.setTransactionType("tranfer");
				transactionObj.setBalance(currentBalance-amount);
				
				
				extUser.setBalance(currentBalance-amount);
				databaseConnector.updateExternalUser(extUser);
				databaseConnector.saveTempTransaction(transactionObj);

				Transactions temp = new Transactions();
				temp.setBalance(transactionObj.getBalance());
				model.addAttribute("debitOp", temp );
				model.addAttribute("creditOp",temp);
				model.addAttribute("checkAccBal", temp.getBalance() );
				model.addAttribute("transferOp",transactionObj);
				model.addAttribute("paymerchantOp",transactionObj);
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
			public String downloadStatement(Model model, HttpSession session){
				String uniqId = session.getAttribute("uniqueid").toString();
				StatementGenerator statementGenerator = new StatementGenerator();
				statementGenerator.statementbyuniqid(uniqId);
				
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
				return "redirect:extUserHomePage";
			}
}