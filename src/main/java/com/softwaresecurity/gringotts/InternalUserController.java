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
						List<TempTransactions> pending;
						pending = displaytransaction(session);
//						System.out.println("Temp objects size"+pending.size());
//						System.out.println("temp obj 1 data"+pending.get(0).getUniqId());
//						System.out.println("temp obj 2 data"+pending.get(1).getUniqId());
						model.addAttribute("transactionOp", pending);
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
		public List<TempTransactions> displaytransaction(HttpSession session){
			
			logger.info("Inside transactions op get");
			
			List<TempTransactions> transactionObj = new ArrayList<TempTransactions>();
			transactionObj	=	db.getTempTransactions();
			System.out.println("In transactions:"+transactionObj.toString());
			System.out.println("transaction size"+transactionObj.size());
			
			return transactionObj;
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
			
		public void approveTransaction(HttpSession session){
			//System.out.println("substring value"+tNum);
//			int transactionNum = Integer.parseInt(tNum.substring(7));
//			System.out.println("transaction num"+transactionNum);
			TempTransactions approve = new TempTransactions();
			approve = displaytransaction(session).get(0);
			Transactions approved = new Transactions();
			approved.setBalance(approve.getBalance());
			approved.setDescription(approve.getDescription());
			approved.setTransactionAmount(approve.getTransactionAmount());
			approved.setTransactionType(approve.getTransactionType());
			approved.setUniqId(approve.getUniqId());
			db.saveTransaction(approved);
			db.removeTempTransaction(approve);
			
		}
		
		public void rejectTransaction(HttpSession session){
			//int transactionNum = Integer.parseInt(tNum.substring(6));
			//System.out.println("transaction num"+transactionNum);
			TempTransactions reject = new TempTransactions();
			reject = displaytransaction(session).get(0);
			ExternalUser extUser = db.getExternalUserByUniqId(reject.getUniqId());
			if(reject.getTransactionType().equals("credit")){
			extUser.setBalance(extUser.getBalance()-reject.getTransactionAmount());
			}
			else{
				extUser.setBalance(extUser.getBalance()+reject.getTransactionAmount());
			}
			db.removeTempTransaction(reject);
		}
		
}