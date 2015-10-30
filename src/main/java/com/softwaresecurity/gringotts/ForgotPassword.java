package com.softwaresecurity.gringotts;
import com.softwaresecurity.util.*;
import pojo.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import dao.*;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
//import com.softwaresecurity.gringotts.RegistrationInput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
public class ForgotPassword {
	public static final int ONE_MINUTE_IN_MILLIS= 60000;
	@RequestMapping(value = "forgotPass", method = RequestMethod.GET)
	public String forgotpass(ModelMap model, HttpSession session) {
		Login forgotPassword = new Login();
	//	Login loginObj = new Login();
		model.put("forgotPassword", forgotPassword);
		return "forgotPass";
	}
	@RequestMapping(value = "forgotPass", method = RequestMethod.POST)
	public String forgotpassPost(@ModelAttribute("forgotPassword")Login loginSet, ModelMap model, HttpSession session2) throws Exception {
	//	model.addAttribute("message",loginSet.getPasswd());
		
		
		session2.setAttribute("uname", loginSet.getUserId());
	//	session2.setAttribute("uniqID", loginSet.getUniqId());
		
		DatabaseConnectors d2 = new DatabaseConnectors();
		
//		System.out.println("uniq id:"+(String)session2.getAttribute("uniqID"));
		
		String uniqueID = d2.getUniqIdByUsername((String)session2.getAttribute("uname"));
		System.out.println("unique id is:"+uniqueID);
		UserInfo foremail = d2.getUserInfoByUniqId(uniqueID);
		
		System.out.println("Emial id is:"+foremail.getEmailId());
		
		Random rand = new Random();
		
		int randomNum = rand.nextInt(737568)+256846;
		String IV = Integer.toString(randomNum);
		System.out.println("Random number (IV): "+ IV);
		//String IVtest = "123456";
		
		//String test2 = "5aba1db3b561abe65a12fd109b50ca5ecfc88e5d106d4b511c7653b843d0e3d4";
	 	//SecureRandom randomGenerator = new SecureRandom();
		//byte[] randomNumber = new byte[20];
		//randomGenerator.nextBytes(randomNumber);
	 	String app1Hash;
		
		String app1Password;

	 	//counter starts at 0 - no clicks yet
		int app1Counter=0;
		 
		Hashtable<String,Integer> h = new Hashtable<String, Integer>();

		//do first run with intialization vector
		GenerateOtp firstApp = new GenerateOtp();
	 	app1Hash = firstApp.genHash(IV);
		app1Password = firstApp.genPassword(app1Hash); 	

		
		System.out.println(IV);
	for(int i = 0; i < 1; i++) {
	 		app1Hash = firstApp.genHash(app1Hash); //send old hash as seed for next sha hash
			app1Password = firstApp.genPassword(app1Hash); //new OTP will be calculated using the new hash
			
			if(!h.containsKey(app1Password)){
				h.put(app1Password, 0);
				app1Counter++;
			}	
			System.out.println("app1 OTP: " + app1Password);
			System.out.println(app1Counter);
		}
	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class",
			"javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");

	Session session1 = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("gringottsbank14@gmail.com","softwaresecurity");
			}
		});
	

	try {

		Message message = new MimeMessage(session1);
		message.setFrom(new InternetAddress("gringottsbank14@gmail.com"));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(foremail.getEmailId()));
		message.setSubject("One Time Password - Gringotts Bank");
		message.setText("Dear User,"+
				"\n\n OTP for your account is as follows:"+" "+app1Password+"."+"\n\n Regards,"+"\n\n Gringotts Bank");

		Transport.send(message);


	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}		
	/* Code for saving OTP */
	
	
	DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date1 = new Date();
	String initdate = dateFormat1.format(date1);
		
	long t=date1.getTime();
	Date afterAddingTenMins=new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
	String exptime = dateFormat1.format(afterAddingTenMins);
	
	String username = loginSet.getUserId();
	
	//System.out.println(dateFormat1.format(date1)); //2014/08/06 15:59:48
		
		OneTimePass l = new OneTimePass(username,initdate,exptime,Integer.parseInt(app1Password));
		DatabaseConnectors d = new DatabaseConnectors();
		
		d.deleteOtpByUsername(username);
		d.saveOTP(l);
		
		/* CODE FOR SAVING OTP */
		return "redirect:forgotPassOTP";
	}
	
	@RequestMapping(value ="forgotPassOTP", method = RequestMethod.GET)
	public String forgotPassOTPGet(ModelMap model, HttpSession session2) {
		System.out.println("I am here");
		OtpInput hn = new OtpInput();
		model.put("confirmOTP",hn);
		System.out.println("I am here again");
		return "forgotPassOTP";
	}
	
	@RequestMapping(value ="forgotPassOTP", method = RequestMethod.POST)
	public String displaypwd(@ModelAttribute("confirmOTP")OtpInput name,ModelMap model, HttpSession session, HttpSession session2) throws ParseException {
//		model.addAttribute("message", name.getName());
		//String username="gvarun";
		System.out.println((String)session2.getAttribute("uname"));
		String username = (String)session2.getAttribute("uname");
		DatabaseConnectors d = new DatabaseConnectors();
		OneTimePass l = d.getOneTimePassByUsername(username);
		String datetime=l.getExptime();
		String[] datesplittime=datetime.split(" ");
		String sentdate=datesplittime[0]; //date
		
		String hoursmin=datesplittime[1];
		String[] hourssplitmin=hoursmin.split(":");
		String senthours=hourssplitmin[0]; //hours
		String sentmin=hourssplitmin[1]; //min
		
		
		// Current System Date
		
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2 = new Date();
		String currentdate = dateFormat2.format(date2);
		String[] curdatesplittime=currentdate.split(" ");
		String currdate=curdatesplittime[0]; //current date
		
		String currenthoursmin=curdatesplittime[1];
		String[] curhourssplitmin=currenthoursmin.split(":");
		String currenthours=curhourssplitmin[0]; //current hours
		String currentmin=curhourssplitmin[1]; //current min
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date_db = format.parse(hoursmin);
		Date date_current = format.parse(currenthoursmin);
		long difference =  date_db.getTime() - date_current.getTime(); 
		long diff_minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
		if((name.getPassword().equals(String.valueOf(l.getPasswd())))  && (sentdate.equals(currdate)) && (difference >=0))
		{
	
			System.out.println("I am trying to go to newPass");
			return "redirect:newPass";
		}
		
		System.out.println("I am returning to login");
		return "redirect:Login";
	}
	
	@RequestMapping(value ="newPass", method = RequestMethod.GET)
	public String newPassGet(ModelMap model, HttpSession session2) {
		Login newPassget = new Login();
		model.put("newPassBean", newPassget);
		return "newPass";
	}
	@RequestMapping(value ="newPass", method = RequestMethod.POST)
	public String newPassSet(@ModelAttribute("newPassBean")Login newPassSet,ModelMap model, HttpSession session2) {
		DatabaseConnectors dbcon = new DatabaseConnectors();
			
			Login getLoginInfo = new Login();
			getLoginInfo = dbcon.getLoginByUsername((String)session2.getAttribute("uname"));
		
			String newPass = newPassSet.getPasswd();
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(newPass);
			getLoginInfo.setPasswd(hashedPassword);
//			newPassSet.setPasswd(hashedPassword);
//			newPassSet.setUniqId((String)session2.getAttribute("uniqID"));
//			newPassSet.setUserId((String)session2.getAttribute("uname"));
			System.out.println("I am in new Pass");
			dbcon.updateLogin(getLoginInfo);
			
			model.addAttribute("message","Password updated");
			
		
		
		return "redirect:passwordUpdateSuccessful";
	
	}
	@RequestMapping(value ="passwordUpdateSuccessful", method = RequestMethod.GET)
	public String passSuccess(ModelMap model, HttpSession session2) {
		session2.invalidate();
		return "passwordUpdateSuccessful";
	}
	
}
