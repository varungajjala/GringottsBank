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

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
		@RequestMapping(value = "/adminHomePage", method = RequestMethod.GET)
	public String adminUserHomePageGet(Locale locale, ModelMap model, HttpSession session) {
			logger.info("In user account op GET");
			
			model.addAttribute("deleteOp_internal", new ExternalUser() );
			model.addAttribute("modifyOp_internal", new UserInfo() );
			model.addAttribute("createOp_internal", new UserInfo() ); 
			
			if(session.getAttribute("role") != null){
				String role = session.getAttribute("role").toString();
				if(role.equals("ei")){
					return "redirect:extUserHomePage";
				}else if(role.equals("em")){
					return "redirect:merchantHomePage";
				}else if(role.equals("im")){
					return "redirect:managerHomePage";
				}else if(role.equals("ir")){
					return "redirect:intUserHomePage";
				}else if(role.equals("admin")){
					return "adminHomePage";
				}
			}
			
			return "redirect:home";
			
		
	}
	
		@RequestMapping(value = "/delete_user_internal", method = RequestMethod.GET)
		public String delete_user(ModelMap model) {
			logger.info("In delete User GET");
			
			model.addAttribute("deleteOp_internal", new ExternalUser() );
			model.addAttribute("modifyOp_internal", new UserInfo() );
			model.addAttribute("createOp_internal", new UserInfo() );
			
			ExternalUser EU = new ExternalUser();
			model.put("send", EU);
			
			return "adminHomePage";
		}
		
		@RequestMapping(value = "/delete_user_internal", method = RequestMethod.POST)
		public String delete_user_post(@ModelAttribute("deleteOp_internal") ExternalUser EU, ModelMap model) {
			logger.info("In delete User POST");
			
			logger.info("EU.getUniqId()" + EU.getUniqId());
			model.put("send_d", EU);
			
			DatabaseConnectors dbcon = new DatabaseConnectors();
			dbcon.deleteUserProfileByUniqId(EU.getUniqId());
			
			model.addAttribute("deleteOp_internal", new ExternalUser() );
			model.addAttribute("modifyOp_internal", new UserInfo() );
			model.addAttribute("createOp_internal", new UserInfo() );
			
			logger.info("leaving delete User POST");
			
			return "adminHomePage";
		}
		
		@RequestMapping(value = "/modify_user_internal", method = RequestMethod.GET)
		public String modify_user_get(ModelMap model) {
			logger.info("In modify User GET");
			
			model.addAttribute("deleteOp_internal", new ExternalUser() );
			model.addAttribute("modifyOp_internal", new UserInfo() );
			model.addAttribute("createOp_internal", new UserInfo() );
			
			return "adminrHomePage";
		}
		
		@RequestMapping(value = "/modify_user_internal", method = RequestMethod.POST)
		public String modify_user_post(@ModelAttribute("modifyOp_internal") UserInfo UI, Model model) {
			logger.info("In modify user POST");
			
			model.addAttribute("deleteOp_internal", new ExternalUser() );
			model.addAttribute("modifyOp_internal", new UserInfo() );
			model.addAttribute("createOp_internal", new UserInfo() );
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() );
			
			return "adminHomePage";
		}
		
		@RequestMapping(value = "/create_user_internal", method = RequestMethod.GET)
		public String create_user(ModelMap model) {
			logger.info("In create user GET");
			
			model.addAttribute("deleteOp_internal", new ExternalUser() );
			model.addAttribute("modifyOp_internal", new UserInfo() );
			model.addAttribute("createOp_internal", new UserInfo() );
			
			return "adminHomePage";
		}
		
		@RequestMapping(value = "/create_user_internal", method = RequestMethod.POST)
		public String create_user_post(@ModelAttribute("createOp_internal") UserInfo UI, Model model) {
			logger.info("In create user POST");
			
			logger.info("UI.getAddress() - " + UI.getAddress());
			logger.info("UI.getAddress() - " + UI.getFirstName());
			logger.info("UI.getAddress() - " + UI.getLastName());
			
			model.addAttribute("deleteOp_internal", new ExternalUser() );
			model.addAttribute("modifyOp_internal", new UserInfo() );
			model.addAttribute("createOp_internal", new UserInfo() );
			
			return "adminHomePage";
		}

	
			
			
		
	
}
