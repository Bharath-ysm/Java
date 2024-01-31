package com.onlinebankingapplication.dto;

import com.onlinebankingapplication.entity.User;

public class UserLoginResponse extends CommonApiResponse{

	
	private User user;
	private String jwtToken;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public void setJwtToken(String jwtToken) 
	{
		
	}
	
	
}