package com.servletprograms.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DateSrv extends GenericServlet
{
	public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		Date d=new Date();
		pw.println("<center><h1>Current Date and Time <br>"+d+"</h1></center>");
		
		pw.close();
	}
}
