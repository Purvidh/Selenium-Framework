package com.tutorialsninja.qa.utis;

import java.util.Date;

public class Utilities {
	
	public static final int Implicit_Wait_Time=10;
	public static final int PageLoad_Time=5;
	
	
	public static String generateEmailWithTimeStamp() {
		
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "dh.purvi"+timestamp+"@gmail.com";
	}
	

}
