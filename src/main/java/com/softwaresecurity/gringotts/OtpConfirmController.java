package com.softwaresecurity.gringotts;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.DatabaseConnectors;
import pojo.OtpInput;
import pojo.TempTransactions;
import pojo.Transactions;
import pojo.ExternalUser;
import pojo.OneTimePass;

/**
 * Handles requests for the application home page.
 */
@Controller

public class OtpConfirmController {
	@RequestMapping(value ="/confirmOtp", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		OtpInput otpinp = new OtpInput();
		System.out.println("I am here");
		model.put("input", otpinp);
		return "confirmOtp";
	}
	
	@RequestMapping(value ="/confirmOtp", method = RequestMethod.POST)
	public String displaypwd(@ModelAttribute("input")OtpInput name,ModelMap model, HttpSession session) throws ParseException {
		//model.addAttribute("message", name.getName());
		String username=session.getAttribute("username").toString();
		
		
		DatabaseConnectors d = new DatabaseConnectors();
		OneTimePass l = d.getOneTimePassByUsername(username);
		String datetime=l.getExptime();
		String[] datesplittime=datetime.split(" ");
		String sentdate=datesplittime[0]; //date
		
		String hoursmin=datesplittime[1];
		
		
		// Current System Date
		
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2 = new Date();
		String currentdate = dateFormat2.format(date2);
		String[] curdatesplittime=currentdate.split(" ");
		String currdate=curdatesplittime[0]; //current date
		
		String currenthoursmin=curdatesplittime[1];

		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date_db = format.parse(hoursmin);
		Date date_current = format.parse(currenthoursmin);
		long difference =  date_db.getTime() - date_current.getTime(); 
		long diff_minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
		
		System.out.println(date_db);
		System.out.println(date_current);
		System.out.println(diff_minutes);
		if( diff_minutes >= 0){
			System.out.println("Yes");
		}
		
		if( (name.getPassword().equals(String.valueOf(l.getPasswd())))  && (sentdate.equals(currdate)) && (difference >=0))
		{
			System.out.println("In this");
			String accountno = session.getAttribute("transAccntNo").toString();
			
			String sender=session.getAttribute("uniqueid").toString();
			
			d.saveOtpTransactionToTransactionById(sender);
			String recipient=session.getAttribute("recipient").toString();
			//d.deleteOtpTransactionById(recipient);
			d.saveOtpTransactionToTransactionById(recipient);
			
			ExternalUser extUser = d.getExternalUserByUniqId(sender);
			TempTransactions transactionObj = new TempTransactions();
			transactionObj.setBalance(extUser.getBalance());
			
			float amount = transactionObj.getTransactionAmount();
			float currentBalance = transactionObj.getBalance();
			
			//credit amount from current account balance	
	
			transactionObj.setUniqId(sender);
			transactionObj.setDescription("transferred amount: "+amount);
			transactionObj.setTransactionType("tranfer");
			transactionObj.setBalance(currentBalance-amount);
			
			
			extUser.setBalance(currentBalance-amount);

			Transactions temp = new Transactions();
			temp.setBalance(transactionObj.getBalance());
			model.addAttribute("debitOp", temp );
			model.addAttribute("creditOp",temp);
			model.addAttribute("checkAccBal", temp.getBalance() );
			model.addAttribute("savingAccBal", "500" );
			model.addAttribute("transferOp",transactionObj);
			model.addAttribute("paymerchantOp",transactionObj);
			List<Transactions> obj= null;
			model.addAttribute("transactionOp",obj);
			
			return "redirect:extUserHomePage";
		}
		
		
		return "confirmOtp";
	}
}

