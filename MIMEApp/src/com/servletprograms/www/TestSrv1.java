package com.servletprograms.www;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestSrv1 extends GenericServlet 
{
	public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException
	{
		PrintWriter pw =res.getWriter();
		res.setContentType("text/html");
		
		pw.println("<table border='1'>");
		pw.println("<tr><th>NO</th><th>NAME</th><th>ADD</th></tr>");
		pw.println("<tr><td>101</td><td>Alan</td><td>Florida</td></tr>");
		pw.println("<tr><td>102</td><td>Jose</td><td>Miami</td></tr>");
		pw.println("<tr><td>103</td><td>Mark</td><td>California</td></tr>");
		pw.println("</table>");
		
		pw.close();
		
	}
}
