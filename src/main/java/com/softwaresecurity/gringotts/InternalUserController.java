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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class InternalUserController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(InternalUserController.class);
	private DatabaseConnectors db = new DatabaseConnectors();
	private List<UserInfo> obj;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
		@RequestMapping(value = "/intUserHomePage", method = RequestMethod.GET)
	public String mangrUserHomePageGet(Locale locale, ModelMap model, HttpSession session) {
			logger.info("In user account op GET");

			if(session.getAttribute("uniqueid") == null){
				return "redirect:";
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
						List<Transactions> pending;
						pending = displaytransaction(session);
						if(pending == null){
							model.addAttribute("transactionOp",null);
						}
						else{
						
						model.addAttribute("transactionOp", pending);
						}

						obj = getuserInfo(session);
						model.addAttribute("displayPiiUsers",obj);

						List<Transactions> deleteTrans = db.getTransactionsByStatus();
						if(deleteTrans == null){
							model.addAttribute("deleteOp", null);
						}
						else{
						model.addAttribute("deleteOp", deleteTrans);
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
				if(role.equals("ei")){
					return "redirect:extUserHomePage";
				}else if(role.equals("admin")){
					return "redirect:adminHomePage";
				}else if(role.equals("em")){
					return "redirect:merchantHomePage";
				}else if(role.equals("im")){
					return "redirect:managerHomePage";
				}else if( role.equals("ir")){
					
					return "intUserHomePage";
				}
			}
			
			return "redirect:";
				

	}
		public List<UserInfo> getuserInfo(HttpSession session){
			logger.info("Inside transactions op get");
			List<UserInfo> userInfo;
			userInfo = db.getUserInfo();
			System.out.println("Length of list :"+userInfo.size());
			logger.info("Length of list :",userInfo.size());
			@SuppressWarnings("unused")
			List<UserInfo> temp = new ArrayList<UserInfo>();
			for( int i = 0; i < userInfo.size(); i++ ) {
				UserInfo t = userInfo.get(i);
				String username = t.getUsername();
				Login l = db.getLoginByUsername(username);
				if(l.getStatus().equalsIgnoreCase("locked")) {
					temp.add(t);
				}
			}
			logger.info("Leaving userinfo op POST");
			return temp;
			}
		
		public List<Transactions> displaytransaction(HttpSession session){
			
			logger.info("Inside transactions op get");
			
			List<Transactions> transactionObj = new ArrayList<Transactions>();
			transactionObj	=	db.getTransactions();
			
			System.out.println("In transactions:"+transactionObj.toString());
			System.out.println("transaction size"+transactionObj.size());
			List<Transactions> critTransactions = new ArrayList<Transactions>();
			for( int i = 0; i < transactionObj.size(); i++ ){
				try{
					Transactions temp = transactionObj.get(i);
					if( (temp.getTransactionAmount() < 1000.0) && (temp.getStatus().equalsIgnoreCase("pending") ) ) {
						critTransactions.add(temp);
					}
				}catch(Exception e) {
					
				}
			}
			
			return critTransactions;
			}
		

		
			@RequestMapping(value = "/authorization", method = RequestMethod.GET)
		public String authorizeTransactions(HttpServletRequest request, HttpSession session){
			
				
				
				int size = Integer.parseInt(request.getParameter("size"));
				for(int i=0;i<size;i++){
				String action = request.getParameter("radioValues"+i);
				//System.out.println("radioValues"+i+" "+request.getParameter("radioValues"+i)+" he"+i);
				
				//System.out.println("Inside authorize transactions");
				
				if(action.contains("approve"))
					approveTransaction(session);
				else
					rejectTransaction(session);
				}
				return "redirect:";
				
			}
		
			@RequestMapping(value = "/approveUserAccount", method = RequestMethod.GET)
			public String authorizePII(HttpServletRequest request, HttpSession session){
				
				int size = Integer.parseInt(request.getParameter("size"));
					for(int i=0;i<size;i++){
						String action = request.getParameter("radioValues"+i);
						UserInfo userInfo = obj.get(i);
						if(action != null && action.contains("approve")) {
							Login login = db.getLoginByUsername(userInfo.getUsername());
							login.setStatus("unlocked");
							db.updateLogin(login);
						}
						else {
							String uniqId = userInfo.getUniqId();
							db.deleteUserProfileByUniqId(uniqId);
						}
					}
					return "redirect:";
					
				}
				
			
		public void approveTransaction(HttpSession session){
			//System.out.println("substring value"+tNum);
//			int transactionNum = Integer.parseInt(tNum.substring(7));
//			System.out.println("transaction num"+transactionNum);
			Transactions approve = displaytransaction(session).get(0);
			approve.setStatus("approved");
			db.removeTransaction(approve);
			db.saveTransaction(approve);
			
		}
		
		public void rejectTransaction(HttpSession session){
			//int transactionNum = Integer.parseInt(tNum.substring(6));
			//System.out.println("transaction num"+transactionNum);
			Transactions reject = new Transactions();
			reject = displaytransaction(session).get(0);
			ExternalUser extUser = db.getExternalUserByUniqId(reject.getUniqId());
			if(reject.getTransactionType().equals("credit")){
			extUser.setBalance(extUser.getBalance()-reject.getTransactionAmount());
			}
			else if(reject.getTransactionType().equals("debit")){
				extUser.setBalance(extUser.getBalance()+reject.getTransactionAmount());
			}
			db.removeTransaction(reject);
		}
		
		@RequestMapping(value = "/modifytransaction", method = RequestMethod.POST)
		public String modifyTransaction(@ModelAttribute("modifyOp")Transactions trans, HttpSession session,Model model){
			
			Transactions actual= new Transactions();
			actual = db.getTransactionsById(trans.getId());
			
			if(actual.getTransactionType().equals("debit")){
				actual.setBalance(actual.getBalance()+actual.getTransactionAmount());
				
				if(actual.getBalance()-trans.getTransactionAmount() >=0)
				actual.setBalance(actual.getBalance()-trans.getTransactionAmount());
			}
			else{
				
				actual.setBalance(actual.getBalance()-actual.getTransactionAmount());
				
				actual.setBalance(actual.getBalance()+trans.getTransactionAmount());
				
			}
			
			return "redirect:";
		}
		
		
		@RequestMapping(value = "/deletetransaction", method = RequestMethod.GET)
		public String deleteTransaction(HttpServletRequest request, HttpSession session,Model model){
			
			int size = Integer.parseInt(request.getParameter("size"));
			List<Transactions> transList = db.getTransactionsByStatus();
			for(int i=0;i<size;i++){
			String action = request.getParameter("radioValues"+i);
			//System.out.println("radioValues"+i+" "+request.getParameter("radioValues"+i)+" he"+i);
			
			//System.out.println("Inside authorize transactions");
			int transID = Integer.parseInt(action.substring(5));
			
			db.deleteTransactionByInternalUser(transID);
			
			}
			
			return "redirect:";
		}
}