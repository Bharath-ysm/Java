package com.onlinebankingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebankingapplication.dto.BankDetailsResponseDto;
import com.onlinebankingapplication.dto.CommonApiResponse;
import com.onlinebankingapplication.dto.RegisterBankRequestDto;
import com.onlinebankingapplication.resource.BankResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/bank")
public class BankResourceController 
{
	@Autowired
	private BankResource bankResource;
	
	@PostMapping("/add")
	@Operation(summary="addBankResource")
	public ResponseEntity<CommonApiResponse> registerBank(@RequestBody RegisterBankRequestDto request )
	{
		return this.bankResource.registerBank(request);
	}
	
	@GetMapping("/allBanks")
	public ResponseEntity<BankDetailsResponseDto> getAllBanks(@RequestBody RegisterBankRequestDto response)
	{
		return this.bankResource.fetchAllBanks();
	}
	
	@GetMapping("/BankById")
	public ResponseEntity<BankDetailsResponseDto> getBankById(@RequestParam ("bankId") int bankId )
	{
		return this.bankResource.fetchBankById(bankId);
	}
	
	@GetMapping("/BankByUserId")
	public ResponseEntity<BankDetailsResponseDto> getBankByUserId(@RequestParam ("userId") int userId)
	{
		return this.bankResource.fetchBankByUserId(userId);
	}
	
	
	
	
	
	
	
	
	
	
}

