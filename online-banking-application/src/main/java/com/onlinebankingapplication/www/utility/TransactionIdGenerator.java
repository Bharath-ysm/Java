package com.onlinebankingapplication.www.utility;

import java.util.UUID;

public class TransactionIdGenerator 
{
	public static String TransactionIdGenerator()
	{
		UUID u=UUID.randomUUID();
		
		String UUIDhex=u.toString().replace("-","");
		
		String UUID16digits=UUIDhex.substring(0,16);
		
		return UUID16digits;
	}
}
