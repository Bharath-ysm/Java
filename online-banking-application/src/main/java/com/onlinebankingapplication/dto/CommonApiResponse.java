package com.onlinebankingapplication.dto;

public class CommonApiResponse {
	
	private String responseMessage;
	private boolean isSuccess;
	
	
	
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}