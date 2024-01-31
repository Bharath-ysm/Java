package com.onlinebankingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebankingapplication.dto.CommonApiResponse;
import com.onlinebankingapplication.dto.RegisterUserRequestDto;
import com.onlinebankingapplication.dto.UserListResponseDto;
import com.onlinebankingapplication.dto.UserLoginRequest;
import com.onlinebankingapplication.dto.UserLoginResponse;
import com.onlinebankingapplication.dto.UserStatusUpdaterequestDto;
import com.onlinebankingapplication.resource.UserResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/user")
public class UserController 
{
	@Autowired
	private UserResource userResource;
	
	@PostMapping("register/user")
	@Operation(summary = "API to register Bank")
	public ResponseEntity<CommonApiResponse> registerUser(@RequestBody RegisterUserRequestDto register)
	{
		return this.userResource.registerUser(register);
	}
	
	@PostMapping("register/Admin")
	public ResponseEntity<UserLoginResponse> registerAdmin(@RequestBody RegisterUserRequestDto register)
	{
		return this.registerAdmin(register);
	}
	
	@PostMapping("/Login")
	public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest loginRequest)
	{
		return this.userResource.login(loginRequest);
	}
	
	@GetMapping("fetch/All bank users")
	public ResponseEntity<UserListResponseDto> fetchAllBankUsers(@RequestParam("Role") String role)
	{
		return this.userResource.getUsersByRole(role);
	}
	
	@Operation(summary="api to get bankmanagers who is not assigned to any other bank account")
	@GetMapping("fetch/AllBankManagers")
	public ResponseEntity<UserListResponseDto> fetchBankManagers()
	{
		return this.userResource.fetchBankManagers();
	}
	
	@PostMapping("Update/userStatus")
	public ResponseEntity<CommonApiResponse> updateUserStatus(UserStatusUpdaterequestDto request)
	{
		return this.userResource.updateUserStatus(request);
	}
	
	@GetMapping("fetch/By BankId")
	public ResponseEntity<UserListResponseDto> fetchBankCustomerByBankId(@RequestParam("BankId") int bankId)
	{
		return this.userResource.fetchBankCustomerByBankId(bankId);
	}
	
	@GetMapping("search/Id's,customers")
	public ResponseEntity<UserListResponseDto> searchBankCustomer(@RequestParam("bankId") int bankId,@RequestParam("CustomerName") String customerName)
	{
		return this.userResource.searchBankCustomer(bankId,customerName);
	}
	
	@GetMapping("search/customers")
	public ResponseEntity<UserListResponseDto> searchBankCustomer(@RequestParam("CustomeName") String customerName)
	{
		return this.userResource.searchBankCustomer(customerName);
	}
}
