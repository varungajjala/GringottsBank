package com.softwaresecurity.gringotts;
import pojo.*;
import java.util.Random;
import dao.*;

import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
//import com.softwaresecurity.gringotts.RegistrationInput;
import java.util.ArrayList;
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
			//model.addAttribute("paymerchantOp",temp);
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
		
	
}
