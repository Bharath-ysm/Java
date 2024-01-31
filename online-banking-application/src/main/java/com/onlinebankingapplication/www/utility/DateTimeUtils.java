package com.onlinebankingapplication.www.utility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.lowagie.text.pdf.PdfPCell;


public class DateTimeUtils 
{
	private static LocalDateTime dateTime;

	public static String getLocalDateTimeFromEpochTime(String epochTimeString)
	{
		Long epochTimeUtils=Long.parseLong(epochTimeString);
		
		Instant inst=Instant.ofEpochMilli(epochTimeUtils);
		
		ZoneId zoneid=ZoneId.systemDefault();
		
		LocalDateTime ldt=LocalDateTime.ofInstant(inst, zoneid);
		
		//to decide date format
		
		String DateFormatPattern="YYYY-MM-DD HH-MM-SS";
		
		//local date time to Date time format
		String FormatedDateTime = formatLocalDateTime(ldt,DateFormatPattern);
		
		System.out.println(FormatedDateTime);
		
		return FormatedDateTime;
	}

	
	private static String formatLocalDateTime(LocalDateTime ldt, String dateFormatPattern) {
		// TODO Auto-generated method stub
		
		DateTimeFormatter formater=DateTimeFormatter.ofPattern(dateFormatPattern);
		
		
		return dateTime.format(formater);
	}


	public static PdfPCell getepoch(Date transactionTime) {
		// TODO Auto-generated method stub
		return null;
	}
}
