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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class ManagerController {
	
	private DatabaseConnectors db = new DatabaseConnectors();
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
		@RequestMapping(value = "/managerHomePage", method = RequestMethod.GET)
	public String mangrUserHomePageGet(Locale locale, ModelMap model, HttpSession session) {
			logger.info("In user account op GET");
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() );
			model.addAttribute("username", new Login() );
			
			List<Login> users = displayUsers();
			
			if(users.size() != 0){
				model.addAttribute("displayUsersOp", users);
			}else{
				model.addAttribute("displayUsersOp", null);
			}
			
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
						List<Transactions> pending = displaytransaction(session);
						if(pending == null){
							model.addAttribute("transactionOp",null);
						}
						else{
						
						model.addAttribute("transactionOp", pending);
						}
						model.addAttribute("firstName",UI.getFirstName());
						model.addAttribute("lastName",UI.getLastName());
						model.addAttribute("Userame",UI.getUsername());
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
				}else if(role.equals("ir")){
					return "redirect:intUserHomePage";
				}else if(role.equals("im")){
					return "managerHomePage";
				}
			}
			
			return "redirect:";
		
	}
		
		
		@RequestMapping(value = "/delete_user", method = RequestMethod.POST)
		public String delete_user_post(@ModelAttribute("deleteOp") ExternalUser EU, ModelMap model) {
			logger.info("In delete User POST");
			
			logger.info("EU.getUniqId()" + EU.getUniqId());
			model.put("send_d", EU);
			
			DatabaseConnectors dbcon = new DatabaseConnectors();
			dbcon.deleteUserProfileByUniqId(EU.getUniqId());
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() );
			
			logger.info("leaving delete User POST");
			
			return "managerHomePage";
		}
		
public List<Transactions> displaytransaction(HttpSession session){
			
			logger.info("Inside transactions op get");
			
			List<Transactions> transactionObj = db.getTransactions();
			
			System.out.println("In transactions:"+transactionObj.toString());
			System.out.println("transaction size"+transactionObj.size());
			
			List<Transactions> critTransactions = new ArrayList<Transactions>();
			
			for( int i = 0; i < transactionObj.size(); i++ ){
				try {
					Transactions temp = transactionObj.get(i);
				if( (temp != null) &&  (temp.getTransactionAmount() >= 1000.0) && (temp.getStatus().equalsIgnoreCase("pending")) ) {
					critTransactions.add(temp);
				}
				System.out.println("object "+i);
				}catch(Exception e){	
				}
			}
			return critTransactions;
			}
			public void approveTransaction(HttpSession session){
				//System.out.println("substring value"+tNum);
			//	int transactionNum = Integer.parseInt(tNum.substring(7));
			//	System.out.println("transaction num"+transactionNum);
				Transactions approve = displaytransaction(session).get(0);
				approve.setStatus("approved");
				db.removeTransaction(approve);
				db.saveTransaction(approve);
				
			}
			
			public void rejectTransaction(HttpSession session){
				//int transactionNum = Integer.parseInt(tNum.substring(6));
				//System.out.println("transaction num"+transactionNum);
				Transactions reject = displaytransaction(session).get(0);
				ExternalUser extUser = db.getExternalUserByUniqId(reject.getUniqId());
				if(reject.getTransactionType().equals("credit")){
				extUser.setBalance(extUser.getBalance()-reject.getTransactionAmount());
				}
				else if(reject.getTransactionType().equals("debitt")){
					extUser.setBalance(extUser.getBalance()+reject.getTransactionAmount());
				}
				db.removeTransaction(reject);
			}
			@RequestMapping(value = "/authorizeCritical", method = RequestMethod.GET)
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

		@RequestMapping(value = "/modify_user", method = RequestMethod.POST)
		public String modify_user_post(@ModelAttribute("modifyOp") UserInfo UI, Model model) {
			logger.info("In modify user POST");
			
			logger.info("UI.getAddress() - " + UI.getAddress());
			logger.info("UI.getAddress() - " + UI.getFirstName());
			logger.info("UI.getAddress() - " + UI.getLastName());
			DatabaseConnectors dbcon = new DatabaseConnectors();
			dbcon.saveUserInfo(UI);
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() );
			
			return "managerHomePage";
		}
		

		@RequestMapping(value = "/create_user", method = RequestMethod.POST)
		public String create_user_post(@ModelAttribute("createOp") UserInfo UI, Model model) {
			logger.info("In create user POST");
			
			logger.info("UI.getAddress() - " + UI.getAddress());
			logger.info("UI.getAddress() - " + UI.getFirstName());
			logger.info("UI.getAddress() - " + UI.getLastName());

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Login uloginset = new Login();
			Random rand = new Random();
			int randId = rand.nextInt(1000);
			String uniqIdVal = Integer.toString(randId);
			String userType = null;
			if(UI.getutype().equals("singleUser"))
			{
				userType="ei";
			}
			else if(UI.getutype().equals("merchant"))
			{
				userType="em";
			}
			
			String uniqId = userType+uniqIdVal;
			String hashedPassword = passwordEncoder.encode(UI.getPasswd());
			UI.setUniqId(uniqId);
			uloginset.setUserId(UI.getUsername());
			uloginset.setPasswd(hashedPassword);
			uloginset.setRole(userType);
			uloginset.setUniqId(UI.getUniqId());
			uloginset.setStatus("Unlocked");
			DatabaseConnectors dbcon = new DatabaseConnectors();
			dbcon.saveUserInfo(UI);
			dbcon.saveLogin(uloginset);
			System.out.println("ended the create");
			
			
			
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() );
			
			return "managerHomePage";
		}
		
		@RequestMapping(value = "/view_transactions", method = RequestMethod.POST)
		public String viewtransactions(@ModelAttribute("username") Login lo, Model model, HttpSession session) {
			List<Transactions> lot = new ArrayList<Transactions>();
			String uniqId = db.getUniqIdByUsername(lo.getUserId());
			lot = db.getTransactionsByUniqId(uniqId);
			if(lot.size() == 0){
				model.addAttribute("usertransactionOp", null);
			}else{
				model.addAttribute("usertransactionOp", lot);
			}
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() );
			model.addAttribute("username", new Login() );
			
			List<Login> users = displayUsers();
			
			if(users.size() != 0){
				model.addAttribute("displayUsersOp", users);
			}else{
				model.addAttribute("displayUsersOp", null);
			}			
		
			
			
			
			return "managerHomePage";
		}
		
		public List<Login> displayUsers(){
			logger.info("Inside display users get");
			
			
			
			List<Login> allUsers = new ArrayList<Login>();
			allUsers = db.getAllLogins();
			
			if(allUsers.size() == 0){
				return null;
			}
			
			logger.info("Length of list :",allUsers.size());
		
			logger.info("Leaving get users op POST");
			
			
			return allUsers;
		}
		

	
	
}
