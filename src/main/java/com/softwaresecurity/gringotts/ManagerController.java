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
		
			
			
		@RequestMapping(value = "/delete_user", method = RequestMethod.GET)
		public String delete_user(ModelMap model, HttpSession session) {
			logger.info("In delete User GET");
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() ); 
			
			ExternalUser EU = new ExternalUser();
			model.put("send", EU);
			
			if(session.getAttribute("role") != null){
				String role = session.getAttribute("role").toString();
				if(role != null || role != ""){
					if(role.equals("ei")){
						return "redirect:extUserHomePage";
					}else if(role.equals("admin")){
						return "redirect:adminHomePage";
					}else if(role.equals("em")){
						return "redirect:merchantHomePage";
					}else if(role.equals("im")){
						return "managerHomePage";
					}else if(role.equals("ir")){
						return "redirect:intUserHomePage";
					}
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
		
		@RequestMapping(value = "/modify_user", method = RequestMethod.GET)
		public String modify_user_get(ModelMap model, HttpSession session) {
			logger.info("In modify User GET");
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() );
			
			if(session.getAttribute("role") != null){
				String role = session.getAttribute("role").toString();
				if(role != null || role != ""){
					if(role.equals("ei")){
						return "redirect:extUserHomePage";
					}else if(role.equals("admin")){
						return "redirect:adminHomePage";
					}else if(role.equals("em")){
						return "redirect:merchantHomePage";
					}else if(role.equals("im")){
						return "managerHomePage";
					}else if(role.equals("ir")){
						return "redirect:intUserHomePage";
					}
				}
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
		
		@RequestMapping(value = "/create_user", method = RequestMethod.GET)
		public String create_user(ModelMap model, HttpSession session) {
			logger.info("In create user GET");
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() );
			
			if(session.getAttribute("role") != null){
				String role = session.getAttribute("role").toString();
				if(role != null || role != ""){
					if(role.equals("ei")){
						return "redirect:extUserHomePage";
					}else if(role.equals("admin")){
						return "redirect:adminHomePage";
					}else if(role.equals("em")){
						return "redirect:merchantHomePage";
					}else if(role.equals("im")){
						return "managerHomePage";
					}else if(role.equals("ir")){
						return "redirect:intUserHomePage";
					}
				}
			}
			
			return "redirect:";
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

	
	
}
