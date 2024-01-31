package com.onlinebankingapplication.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.onlinebankingapplication.dto.CommonApiResponse;
import lombok.Builder;

@Builder
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(FailedToRegister.class)
	public ResponseEntity<CommonApiResponse> handleUserNotFoundException(FailedToRegister ex) {
		String responseMessage = ex.getMessage();
		
		CommonApiResponse apiResponse =  CommonApiResponse.builder().responseMessage(responseMessage).isSuccess(true).build();
		return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(BankAccountTransactionException.class)
	public ResponseEntity<CommonApiResponse> handleBankAccountTransactionException(BankAccountTransactionException ex) {
		String responseMessage = ex.getMessage();
		
		CommonApiResponse apiResponse = CommonApiResponse.builder().responseMessage(responseMessage).isSuccess(true).build();
		return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	

}
