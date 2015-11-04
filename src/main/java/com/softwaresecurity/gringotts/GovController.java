package com.softwaresecurity.gringotts;
import com.softwaresecurity.util.*;
import pojo.*;
import java.util.Random;
import dao.*;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
//import com.softwaresecurity.gringotts.RegistrationInput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
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
 * Handles requests for the government home page.
 */
@Controller
public class GovController {

private static final Logger logger = LoggerFactory.getLogger(GovController.class);
private DatabaseConnectors db = new DatabaseConnectors();
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/GovHomePage", method = RequestMethod.GET)
	public String govHome(Locale locale, ModelMap model, HttpSession session) {
		if(session.getAttribute("uniqueid") == null){
			return "redirect:";
		}
		
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
				return "redirect:adminHomePage";
			}else if(role.equals("gov")){
				return "GovHomePage";
			}
		}
		List<UserInfo> obj= getuserInfo(session);
		model.addAttribute("displayPiiUsers",obj);
		model.addAttribute("piiauthorization", new UserInfo());
		System.out.println(obj.get(0).getFirstName());
		//System.out.println(obj.get(0).getIdentificationNo());
		System.out.println(obj.get(0).getUsername());
		System.out.println(obj.get(0).getLastName());
				return "GovHomePage";
	}
	
	public List<UserInfo> getuserInfo(HttpSession session){
		logger.info("Inside transactions op get");
		List<UserInfo> userInfo;
		userInfo = db.getUserInfo();
		System.out.println("Length of list :"+userInfo.size());
		logger.info("Length of list :",userInfo.size());
		@SuppressWarnings("unused")
		UserInfo temp = new UserInfo();
		logger.info("Leaving userinfo op POST");
		return userInfo;
		}

	
	@RequestMapping(value = "/piiauthorization", method = RequestMethod.GET)
	public String authorizePII(HttpServletRequest request, HttpSession session){
		
		int size = Integer.parseInt(request.getParameter("size"));
			for(int i=0;i<size;i++){
			String action = request.getParameter("radioValues"+i);
			//System.out.println("radioValues"+i+" "+request.getParameter("radioValues"+i)+" he"+i);
			
			//System.out.println("Inside authorize transactions");
			
			if(action != null && action.contains("approve"))
				approvePIIRequest(session);
			else
				rejectPIIRequest(session);
			}
			return "redirect:";
			
		}
		
	public void approvePIIRequest(HttpSession session){
		//System.out.println("substring value"+tNum);
//		int transactionNum = Integer.parseInt(tNum.substring(7));
//		System.out.println("transaction num"+transactionNum);
		UserInfo approve = getuserInfo(session).get(0);
		System.out.println(approve.getIdentificationNo());
		approve.setGovapproval("approved");
		db.updateUserInfo(approve);
		//db.removeTempTransaction(approve);
		
	}
	
	public void rejectPIIRequest(HttpSession session){
		//int transactionNum = Integer.parseInt(tNum.substring(6));
		//System.out.println("transaction num"+transactionNum);
		@SuppressWarnings("unused")
		UserInfo approve = getuserInfo(session).get(0);
		approve.setGovapproval("rejected");
		db.updateUserInfo(approve);
	}
}
