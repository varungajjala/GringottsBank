package com.softwaresecurity.gringotts;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.DatabaseConnectors;
import pojo.HelloName;
import pojo.OneTimePass;

/**
 * Handles requests for the application home page.
 */
@Controller

public class OtpConfirmController {
	@RequestMapping(value ="/confirmotp", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		HelloName hn = new HelloName();
		model.put("send", hn);
		return "confirmotp";
	}
	
	@RequestMapping(value ="/confirmotp", method = RequestMethod.POST)
	public String displaypwd(@ModelAttribute("send")HelloName name,ModelMap model, HttpSession session) {
		model.addAttribute("message", name.getName());
		String username="gvarun";
		
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
		
		
		if( (name.getName().equals(String.valueOf(l.getPasswd())))  && (sentdate.equals(currdate)) && (currenthours.equals(senthours)) && ((Integer.parseInt(sentmin)-Integer.parseInt(currentmin))>=0))
		{
			String accountno = session.getAttribute("transAccntNo").toString();
			
			return "extUserHomePage";
		}
		
		
		return "confirmotp";
	}
}

