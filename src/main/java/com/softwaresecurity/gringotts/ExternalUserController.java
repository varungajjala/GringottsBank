package com.softwaresecurity.gringotts;
import pojo.*;
import java.util.Random;
import dao.*;

import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
//import com.softwaresecurity.gringotts.RegistrationInput;
	
import java.util.Date;
import java.util.List;
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
			model.addAttribute("transactionOp",temp);
			model.addAttribute("checkAccBal", temp.getBalance() );
			model.addAttribute("savingAccBal", "500" );

			logger.info("Trans Obj:",transObj);
			logger.info("Current Balance"+extUser.getBalance());
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
			
			return "redirect:home";
		
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
					model.addAttribute("savingAccBal", "500" );
					model.addAttribute("transferOp",temp);
					model.addAttribute("paymerchantOp",temp);
					model.addAttribute("transactionOp",temp);
					


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
					model.addAttribute("savingAccBal", "500" );
					model.addAttribute("transferOp",temp);
					model.addAttribute("paymerchantOp",temp);
					model.addAttribute("transactionOp",temp);
					


					logger.info("Leaving credit money POST");
					return "extUserHomePage";
					}
			
			
			@RequestMapping(value = "/transfer_money", method = RequestMethod.POST)
			public String transfermoneyPageAction(@ModelAttribute("transferOp") TempTransactions transObj, Model model, HttpSession session){
				logger.info("Inside transfer money op POST");
				@SuppressWarnings("deprecation")
				String uniqId= (String)session.getAttribute("uniqueid");
				
				ExternalUser extUser = databaseConnector.getExternalUserByUniqId(uniqId);
				
				Transactions transPost = new Transactions();
				transPost.setBalance(extUser.getBalance());
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
				databaseConnector.saveTransaction(transPost);
				
				}
				
				logger.info("Inside credit part of transfer money op POST");
				//String uniqueID = (String)session.getAttribute("uniqueid");
				//String uniqueID ="EM123";
				Transactions transPost2 = new Transactions();
				ExternalUser extUser2 = databaseConnector.getExternalUserByAccNum(transObj.getAccountno());
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
				databaseConnector.saveTransaction(transPost2);
				

				model.addAttribute("debitOp", transPost );
				model.addAttribute("creditOp",transPost);
				model.addAttribute("checkAccBal", extUser.getBalance() );
				model.addAttribute("savingAccBal", "500" );
				model.addAttribute("transferOp",transObj);
				model.addAttribute("paymerchantOp",transObj);
				model.addAttribute("transactionOp",transObj);
				


				logger.info("Leaving transfer money POST");
				return "extUserHomePage";
					}
			
			
			@RequestMapping(value = "/transactions", method = RequestMethod.POST)
			public String displaytransactionPageAction(@ModelAttribute("transactionOp") List<Transactions> transactionObj, Model model, HttpSession session){
					logger.info("Inside transactions op POST");
					logger.info("Current Balance" + transactionObj.get(transactionObj.size()-1).getBalance());
					String uniqueID = (String) session.getAttribute("uniqueid");
					//String uniqueID ="EM123";
					transactionObj = databaseConnector.getTransactionsByUniqId(uniqueID);
					
					Transactions temp = new Transactions();
					temp.setBalance(transactionObj.get(transactionObj.size()-1).getBalance());
					model.addAttribute("debitOp", temp );
					model.addAttribute("creditOp",temp);
					model.addAttribute("checkAccBal", temp.getBalance() );
					model.addAttribute("savingAccBal", "500" );
					model.addAttribute("transactionOp",transactionObj);
					model.addAttribute("transferOp",transactionObj.get(transactionObj.size()-1));
					model.addAttribute("paymerchantOp",transactionObj.get(transactionObj.size()-1));


					logger.info("Leaving transactions op POST");
					return "extUserHomePage";
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
				model.addAttribute("savingAccBal", "500" );
				model.addAttribute("transferOp",transactionObj);
				model.addAttribute("paymerchantOp",transactionObj);
				model.addAttribute("transactionOp",transactionObj);

				logger.info("Leaving transfer money POST");
				return "extUserHomePage";
					}
			
			
			}
