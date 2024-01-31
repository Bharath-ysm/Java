package com.onlinebankingapplication.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestHeaderInterceptor implements HandlerInterceptor
{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
		
		//we can validate few things before the request go the controller
		
		if(StringUtils.isEmpty(request.getHeader("Authorization")))
		{
			//throw exception
			
		}
		System.out.println("preHandle() method invoked ");
		
		System.out.println("----RequestStart----");
		System.out.println("Request URL:"+request.getRequestURI());
		System.out.println("MethodType:"+request.getMethod());
		System.out.println("Local Address: "+request.getLocalAddr());
		System.out.println("Local Port: "+request.getLocalPort());
		
		return  true;
	}
}
