package com.softwaresecurity.gringotts;
import com.softwaresecurity.util.*;
import pojo.*;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import dao.*;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
//import com.softwaresecurity.gringotts.RegistrationInput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
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
				}else if(role.equals("gov")){
				return "redirect:GovHomePage";
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
		
		if(login != null && login.getLoginstatus() == 1){
			String existingtime = login.getLogintime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currenttime = dateFormat.format(date);
			String[] currentsplittime=currenttime.split(" ");
			String currdate=currentsplittime[0]; //current date
			
			String currenthoursmin=currentsplittime[1];
			String[] curhourssplitmin=currenthoursmin.split(":");
			String currenthours=curhourssplitmin[0]; //current hours
			String currentmin=curhourssplitmin[1]; //current min
			
			String[] datesplittime=existingtime.split(" ");
			String existingdate=datesplittime[0]; //date
			
			String hoursmin=datesplittime[1];
			String[] hourssplitmin=hoursmin.split(":");
			String existinghours=hourssplitmin[0]; //hours
			String existingmin=hourssplitmin[1]; //min
			if( !currdate.equals(existingdate) || !currenthours.equals(existinghours) || ((Integer.parseInt(currentmin) - Integer.parseInt(existingmin)) >= 10))
			{
				login.setLoginstatus(0);
				dbcon.updateLogin(login);
			}
			
			
		}
		
		
		
		if( login != null && result==1 && !login.getStatus().equals("Locked") && login.getLoginstatus() == 0)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date1 = new Date();
			String logintime = dateFormat.format(date1);
			
			login.setAttempt(0);
			login.setLoginstatus(1);
			login.setLogintime(logintime);
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
			}else if(role.equals("gov")){
				return "redirect:GovHomePage";
			}	
		}else if(login!= null && login.getLoginstatus() == 1){
			model.addAttribute("message","You are already logged in or did not securely log out");
			return "home";
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
	public void submitForm(@ModelAttribute("send")UserInfo uinfoget, ModelMap m,
				HttpSession session, HttpServletResponse response) {
		Login uloginset = new Login();
		ExternalUser extUser = new ExternalUser();
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
			
			long accntNo = GeneralUtils.createRandomInteger("external");
			
			extUser.setBalance(0.0f);
			extUser.setUniqId(uniqId);
			extUser.setAccountno(accntNo);
			
			DatabaseConnectors dbcon = new DatabaseConnectors();
			dbcon.saveUserInfo(uinfoget);
			dbcon.saveLogin(uloginset);
			dbcon.saveExternalUser(extUser);
			
			pkiGringott.generateSignedX509Certificate(uniqId, session);
			
			
	        ServletContext context = session.getServletContext();
	                
	        String realContextPath = context.getRealPath("/");
	        String certpath = realContextPath+"/certificates/"+uniqId+"_cert.pem";
	        String privateKeypath = realContextPath+"/privatekeys/"+uniqId+"_private.key";
	        		
	        response.setContentType("Content-type: text/zip");
			response.setHeader("Content-Disposition", "attachment; filename=UserFile.zip");

			try{
				ServletOutputStream out = response.getOutputStream();
				ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));

				String path = null;
				File downloadFile = null;
				
				for (int i=0; i<2; i++) 
				{
					if(i==0){
						path = certpath;
					}else{
						path = privateKeypath;
					}

		            downloadFile = new File(path);
					System.out.println("Adding " + downloadFile.getName());
					zos.putNextEntry(new ZipEntry(downloadFile.getName()));

					// Get the file
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(downloadFile);

					} catch (FileNotFoundException fnfe) {
						// If the file does not exists, write an error entry instead of
						// file
						// contents
						zos.write(("ERRORld not find file " + downloadFile.getName())
								.getBytes());
						zos.closeEntry();
						System.out.println("Couldfind file "
								+ downloadFile.getAbsolutePath());
						continue;
					}

					BufferedInputStream fif = new BufferedInputStream(fis);

					// Write the contents of the file
					int data = 0;
					while ((data = fif.read()) != -1) {
						zos.write(data);
					}
					fif.close();

					zos.closeEntry();
					System.out.println("Finishedng file " + downloadFile.getName());
				}

				zos.close();
				pkiGringott.deleteCertificateAndPrivateKeyFile(uniqId, session);
			}catch(Exception e){
				e.printStackTrace();
			}

			logger.info("leaving post");
			return;
	    
	}
	@RequestMapping(value = "/registrationSuccessful", method = RequestMethod.POST)
	public String regSuccess(Model model, HttpSession session) {

		return "registrationSuccessful";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutpost(HttpSession session) {
		
		String username = session.getAttribute("username").toString();
		
		DatabaseConnectors dbcon = new DatabaseConnectors();
		Login login = dbcon.getLoginByUsername(username);
		login.setLoginstatus(0);
		dbcon.updateLogin(login);
		session.invalidate();
		
		return "redirect:";
	}
}
