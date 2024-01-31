package com.onlinebankingapplication.www.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.onlinebankingapplication.interceptor.RequestHeaderInterceptor;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer
{
	@Autowired
	private RequestHeaderInterceptor requestHeaderInterceptor; 
	
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(requestHeaderInterceptor);
		
	}
	
}

