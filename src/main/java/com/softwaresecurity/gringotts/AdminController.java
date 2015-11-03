package com.softwaresecurity.gringotts;
//package org.springframework.security.crypto.password;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pojo.*;
import java.util.Random;
import dao.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
//import com.softwaresecurity.gringotts.RegistrationInput;
	
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
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
		DatabaseConnectors databaseConnector = new DatabaseConnectors();
		@RequestMapping(value = "/adminHomePage", method = RequestMethod.GET)
	public String adminUserHomePageGet(Locale locale, ModelMap model, HttpSession session) {
			logger.info("In user account op GET");
			
			model.addAttribute("deleteOp_internal", new ExternalUser() );
			model.addAttribute("modifyOp_internal", new UserInfo() );
			model.addAttribute("createOp_internal", new UserInfo() );
			/* Code to access PII of all users */
			List<UserInfo> obj= displayuserInfo(session);
			model.addAttribute("displayUsers",obj);
			/*System.out.println(obj.get(0).getFirstName());
			System.out.println(obj.get(0).getIdentificationNo());
			System.out.println(obj.get(0).getUsername());
			System.out.println(obj.get(0).getLastName());*/
			
			
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
				}else if(role.equals("gov")){
					return "redirect:GovHomePage";
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
			
			String unique_id = databaseConnector.getUniqIdByUsername(UI.getUsername());
			
			if(unique_id==null || unique_id.equals(null)){
				model.addAttribute("message", "User Not exists");
				return "adminHomePage";
			}
			
			if(unique_id.equals("")){
				model.addAttribute("message", "User Not exists");
				model.addAttribute("deleteOp_internal", new ExternalUser() );
				model.addAttribute("modifyOp_internal", new UserInfo() );
				model.addAttribute("createOp_internal", new UserInfo() );
				return "adminHomePage";
			}
			
			logger.info("unique_id:" + unique_id + "--");
			
			String str2 = unique_id.substring(0,2);
			
			if(str2.equals("ei") || str2.equals("em") || str2.equals("admin") || str2.equals("gov")){
				model.addAttribute("message", "You are not allowed to change this user");
				model.addAttribute("deleteOp_internal", new ExternalUser() );
				model.addAttribute("modifyOp_internal", new UserInfo() );
				model.addAttribute("createOp_internal", new UserInfo() );
				return "adminHomePage";
			}
			
			UserInfo user_save = databaseConnector.getUserInfoByUniqId(unique_id);
			user_save.setFirstName(UI.getFirstName());
			user_save.setLastName(UI.getLastName());
			user_save.setAddress(UI.getAddress());
			user_save.setCity(UI.getCity());
			user_save.setState(UI.getState());
			user_save.setCountry(UI.getCountry());
			user_save.setZipcode(UI.getZipcode());
			user_save.setContactNo(UI.getContactNo());
			
			DatabaseConnectors dbcon = new DatabaseConnectors();
			dbcon.updateUserInfo(user_save);
			
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
		
		public List<UserInfo> displayuserInfo(HttpSession session){
			logger.info("Inside transactions op get");
			List<UserInfo> userInfo;
			userInfo = databaseConnector.getApprovedUserInfo();
			logger.info("Length of list :",userInfo.size());
			logger.info("Leaving userinfo op POST");
			return userInfo;
			}
		@RequestMapping(value = "/downloadlogs", method = RequestMethod.GET)
		public void downloadStatement(HttpSession session, HttpServletResponse response,Model model) throws IOException {
		        // get absolute path of the application
			    String fullpath;
				ServletContext context = session.getServletContext();
				String OS = System.getProperty("os.name").toLowerCase();
		        String realContextPath= System.getProperty("user.dir");
                if (OS.indexOf("mac") >= 0) {
					  fullpath = realContextPath+"/log.txt";
				}
                else  {
			          fullpath = realContextPath+"\\"+"log.txt";
					}
		       
		        System.out.println(fullpath);   
		        File downloadFile = new File(fullpath);
		        FileInputStream inputStream = new FileInputStream(downloadFile);
		        
		        // get MIME type of the file
		        String mimeType = context.getMimeType(fullpath);
		        if (mimeType == null) {
		            // set to binary type if MIME mapping not found
		            mimeType = "type=text/plain";
		        }
		        System.out.println("MIME type: " + mimeType);
		 
		        // set content attributes for the response
		        response.setContentType(mimeType);
		        response.setContentLength((int) downloadFile.length());
		 
		        // set headers for the response
		        String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"",
		                downloadFile.getName());
		        response.setHeader(headerKey, headerValue);
		 
		        
		        OutputStream outStream = response.getOutputStream();
		        
		        byte[] buffer = new byte[4096];
		        int bytesRead = -1;
		 
		        // write bytes read from the input stream into the output stream
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		 
		        inputStream.close();
		        outStream.close();
	
				System.out.println("Finished Downloading file " + downloadFile.getName());
						return;
	}



		
	
}
