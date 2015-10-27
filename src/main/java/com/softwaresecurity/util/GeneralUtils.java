package com.softwaresecurity.util;

import java.util.Random;

public class GeneralUtils {
	public static long createRandomInteger(String who){
		Random random = new Random();
		long start;
		long end;
		if(who.equals("external")){
			start = 100000000;
			end = 999999999;
		}else{
			start = 1000;
			end = 9999;
		}
		
	    
	    long range = end - (long)start + 1;
	    long fraction = (long)(range * random.nextDouble());
	    long randomNumber =  fraction + (long)start;    
	    
	    return randomNumber;

	  }
}
