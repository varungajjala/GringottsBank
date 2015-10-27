package com.softwaresecurity.gringotts;
import pojo.*;
import java.util.Random;
import dao.*;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
//import com.softwaresecurity.gringotts.RegistrationInput;
	
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.SafeHtml;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.jdbc.log.Log;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, ModelMap model, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
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
					return "redirect:managerHomePage";
				}else if(role.equals("ir")){
					return "redirect:intUserHomePage";
				}
			}
		}
		
		
		Login loginPageGet = new Login();
		
		model.put("loginPage",loginPageGet);
		return "home";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String homePost(@ModelAttribute("loginPage")Login loginPageSet, ModelMap model, HttpSession session) {
		
		DatabaseConnectors dbcon = new DatabaseConnectors();
		
		int result = dbcon.checkLogin(loginPageSet.getUserId(), loginPageSet.getPasswd());
		Login login = dbcon.getLoginByUsername(loginPageSet.getUserId());
		if( result==1 && !login.getStatus().equals("Locked") )
		{
			login.setAttempt(0);
			dbcon.updateLogin(login);
			String role = dbcon.getRoleByUsername(loginPageSet.getUserId());
			 
			session.setAttribute("username", loginPageSet.getUserId());
			session.setAttribute("role", role);
			session.setAttribute("uniqueid", dbcon.getUniqIdByUsername(loginPageSet.getUserId()));
			if(role.equals("ei")){
				return "redirect:extUserHomePage";
			}else if(role.equals("admin")){
				return "redirect:adminHomePage";
			}else if(role.equals("em")){
				return "redirect:merchantHomePage";
			}else if(role.equals("im")){
				return "redirect:managerHomePage";
			}else if(role.equals("ir")){
				return "redirect:intUserHomePage";
			}	
		}
		else if(login != null){
			login.setAttempt(login.getAttempt()+1);
			if( login.getAttempt() <4 ) {
				dbcon.updateLogin(login);
				model.addAttribute("message","incorrect login details");
				return "home";
			} else {
				login.setStatus("Locked");
				model.addAttribute("message","Account locked");
				dbcon.updateLogin(login);
				return "home";
			}
		}
		model.addAttribute("message","incorrect login details");
		return "home";
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerme(ModelMap model) {
		logger.info("Entering get");	
		UserInfo uinfoset = new UserInfo();
		
		model.put("send", uinfoset);
		
		
		//DatabaseConnectors dbcon = new DatabaseConnectors();
		//dbcon.saveUserInfo(uinfoset);
		logger.info("leaving get");
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("send")UserInfo uinfoget, ModelMap m) {
		Login uloginset = new Login();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		logger.info("Entering post");
		 logger.info("In registration POST");
	        
	        logger.info("uinfoget.getFirstName() - " + uinfoget.getFirstName());
	        logger.info("uinfoget.getLastName() - " + uinfoget.getLastName());
	        logger.info("uinfoget.getContactNo() - " + uinfoget.getContactNo());
	        logger.info("uinfoget.getEmailId() - " + uinfoget.getEmailId());
	        logger.info("uinfoget.getUsername() - " + uinfoget.getUsername());	  
	        logger.info("uinfoget.getAddress() - " + uinfoget.getAddress());
	        logger.info("uinfoget.getZipcode() - " + uinfoget.getZipcode());
	        logger.info("uinfoget.getCity() - " + uinfoget.getCity());
	        logger.info("uinfoget.getState() - " + uinfoget.getState());
	        logger.info("uinfoget.getCountry() - " + uinfoget.getCountry());
	        logger.info("uinfoget.getIdentification - " + uinfoget.getIdentificationNo());
	        logger.info("uinfoget.getusertype - " + uinfoget.getutype());
		
	        
	    	Random rand = new Random();
			int randId = rand.nextInt(1000);
			String uniqIdVal = Integer.toString(randId);
			String userType = null;
			if(uinfoget.getutype().equals("singleUser"))
			{
				userType="ei";
			}
			else if(uinfoget.getutype().equals("merchant"))
			{
				userType="em";
			}
			
			String uniqId = userType+uniqIdVal;
			String hashedPassword = passwordEncoder.encode(uinfoget.getPasswd());
			uinfoget.setUniqId(uniqId);
			uloginset.setUserId(uinfoget.getUsername());
			uloginset.setPasswd(hashedPassword);
			uloginset.setRole(userType);
			uloginset.setUniqId(uinfoget.getUniqId());
			uloginset.setStatus("Locked");
			logger.info("login userID" + uloginset.getUserId());
			logger.info("login password" + uloginset.getPasswd());
			logger.info("login role" + uloginset.getRole());
			logger.info("login uniqId" + uloginset.getUniqId());
			
			//uinfoget.setUsername(uinfoget.getFirstName());
			DatabaseConnectors dbcon = new DatabaseConnectors();
			dbcon.saveUserInfo(uinfoget);
			dbcon.saveLogin(uloginset);
	//		dbcon.saveLogin(userLogin);
	//		m.addAttribute("message","Registeration Successful with ID"+ uniqId);
			logger.info("leaving post");
			return "registrationSuccessful";
	    
	}
	@RequestMapping(value = "/registrationSuccessful", method = RequestMethod.POST)
	public String regSuccess(Model model, HttpSession session) {

		return "registrationSuccessful";
	}
	@RequestMapping(value = "/forgotPass", method = RequestMethod.GET)
	public String forgotpass(ModelMap model, HttpSession session) {
		Login loginObj = new Login();
		model.put("forgotPassword", loginObj);
		return "forgotPass";
	}
	@RequestMapping(value = "/forgotPass", method = RequestMethod.POST)
	public String forgotpassPost(@ModelAttribute("forgotPassword")Login loginSet, ModelMap model, HttpSession session) {
		model.addAttribute("message",loginSet.getPasswd());
		
		return "forgotPass";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutpost(HttpSession session) {
		session.invalidate();
		
		return "redirect:";
	}
}
