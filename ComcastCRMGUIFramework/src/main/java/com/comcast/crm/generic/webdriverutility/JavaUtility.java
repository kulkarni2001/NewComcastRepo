package com.comcast.crm.generic.webdriverutility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import freemarker.template.SimpleDate;



public class JavaUtility {
	public int getRandomNumber()
	{
		Random random=new Random();
		int randomNumber=random.nextInt(5000);
		return randomNumber;
		
	}
	
	public String getSystemDateYYYYMMDD()
	{
		Date dateObj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		return date;
	}
	public String getRequiredDateYYYYMMDD(int days)
	{
		
		Date dateObj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(dateObj);
		Calendar cal=sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String reqDate=sdf.format(cal.getTime());
		
//		
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal=sdf.getCalendar();
//		cal.add(Calendar.DAY_OF_MONTH, days);
//		String reqDate=sdf.format(cal.getTime());
		return reqDate;
	}


}
