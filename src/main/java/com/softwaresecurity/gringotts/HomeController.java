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

import org.hibernate.validator.constraints.SafeHtml;
import org.junit.runner.Request;
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
public class HomeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
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
		
		uinfoget.setUniqId(uniqId);
		//uinfoget.setUsername(uinfoget.getFirstName());
		DatabaseConnectors dbcon = new DatabaseConnectors();
		dbcon.saveUserInfo(uinfoget);
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
}
