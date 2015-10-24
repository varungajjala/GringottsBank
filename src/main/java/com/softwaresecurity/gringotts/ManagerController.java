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
			
			
		return "managerHomePage";
	}
		
			
			
		@RequestMapping(value = "/delete_user", method = RequestMethod.GET)
		public String delete_user(ModelMap model) {
			logger.info("In delete User GET");
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() ); 
			
			ExternalUser EU = new ExternalUser();
			model.put("send", EU);
			
			return "managerHomePage";
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
		public String modify_user_get(ModelMap model) {
			logger.info("In modify User GET");
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() );
			
			return "managerHomePage";
		}
		
		@RequestMapping(value = "/modify_user", method = RequestMethod.POST)
		public String modify_user_post(@ModelAttribute("modifyOp") UserInfo UI, Model model) {
			logger.info("In modify user POST");
			
			logger.info("UI.getAddress() - " + UI.getAddress());
			logger.info("UI.getAddress() - " + UI.getFirstName());
			logger.info("UI.getAddress() - " + UI.getLastName());
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() );
			
			return "managerHomePage";
		}
		
		@RequestMapping(value = "/create_user", method = RequestMethod.GET)
		public String create_user(ModelMap model) {
			logger.info("In create user GET");
			
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
			
			model.addAttribute("deleteOp", new ExternalUser() );
			model.addAttribute("modifyOp", new UserInfo() );
			model.addAttribute("createOp", new UserInfo() );
			
			return "managerHomePage";
		}

	
	
}