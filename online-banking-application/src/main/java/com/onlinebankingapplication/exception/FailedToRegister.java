package com.onlinebankingapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedToRegister extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public FailedToRegister(String message) {
		super(message);
	}

}
