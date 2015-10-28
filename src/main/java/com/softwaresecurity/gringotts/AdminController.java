package com.softwaresecurity.gringotts;
//package org.springframework.security.crypto.password;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

import com.softwaresecurity.util.GeneralUtils;

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
			
			return "redirect:";
			
		
	}
	
		
		@RequestMapping(value = "/delete_user_internal", method = RequestMethod.POST)
		public String delete_user_post(@ModelAttribute("deleteOp_internal") ExternalUser EU, ModelMap model) {
			logger.info("In delete User POST");
			
			logger.info("EU.getUniqId()" + EU.getUniqId());
			//model.put("send_d", EU);
			
			DatabaseConnectors dbcon = new DatabaseConnectors();
			dbcon.deleteUserProfileByUniqId(EU.getUniqId());
			
			model.addAttribute("deleteOp_internal", new ExternalUser() );
			model.addAttribute("modifyOp_internal", new UserInfo() );
			model.addAttribute("createOp_internal", new UserInfo() );
			
			logger.info("leaving delete User POST");
			
			return "adminHomePage";
		}
		
		
		@RequestMapping(value = "/modify_user_internal", method = RequestMethod.POST)
		public String modify_user_post(@ModelAttribute("modifyOp_internal") UserInfo UI, Model model) {
			logger.info("In modify user POST");
			
			DatabaseConnectors dbcon = new DatabaseConnectors();
			dbcon.saveUserInfo(UI);
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
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Login uloginset = new Login();
			Random rand = new Random();
			int randId = rand.nextInt(1000);
			String uniqIdVal = Integer.toString(randId);
			String userType = null;
			if(UI.getutype().equals("internalUser"))
			{
				userType="ir";
			}
			else if(UI.getutype().equals("manager"))
			{
				userType="im";
			}
			
			String uniqId = userType+uniqIdVal;
			String hashedPassword = passwordEncoder.encode(UI.getPasswd());
			UI.setUniqId(uniqId);
			uloginset.setUserId(UI.getUsername());
			uloginset.setPasswd(hashedPassword);
			uloginset.setRole(userType);
			uloginset.setUniqId(UI.getUniqId());
			uloginset.setStatus("Unlocked");
			
			long empid = GeneralUtils.createRandomInteger("internal");
			
			InternalUser intUser = new InternalUser();
			intUser.setEmpId(empid);
			intUser.setUniqId(uniqId);
			
			DatabaseConnectors dbcon = new DatabaseConnectors();
			dbcon.saveUserInfo(UI);
			dbcon.saveLogin(uloginset);
			dbcon.saveInternalUser(intUser);
			System.out.println("ended the create");

			model.addAttribute("deleteOp_internal", new ExternalUser() );
			model.addAttribute("modifyOp_internal", new UserInfo() );
			model.addAttribute("createOp_internal", new UserInfo() );
			
			return "adminHomePage";
		}

		
	
}
