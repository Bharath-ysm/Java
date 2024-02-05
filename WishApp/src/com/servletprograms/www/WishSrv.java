package com.servletprograms.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WishSrv extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		Calendar c=Calendar.getInstance();
		int h=c.get(Calendar.HOUR_OF_DAY);
		
		if(h<12)
			pw.println("<center><h1>GoodMorning</h1></center>");
		if(h<16)
			pw.println("<center><h1>GoodAfterNoon</h1></center>");
		if(h<20)
			pw.println("<center><h1>GoodEvening</h1></center>");
		else
			pw.println("<center><h1>GoodNight</h1></center>");
	}
}
