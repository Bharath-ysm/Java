package com.onlinebankingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinebankingapplication.dto.AddBankAccountRequestDto;
import com.onlinebankingapplication.dto.BankAccountResponseDto;
import com.onlinebankingapplication.dto.BankAccountStatusUpdateRequestDto;
import com.onlinebankingapplication.dto.CommonApiResponse;
import com.onlinebankingapplication.resource.BankAccountResource;

import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping("api/account")
public class BankAccountController 
{
	@Autowired
	private BankAccountResource bankAccountResource;
	
	@PostMapping("/add")
	@Operation(summary="addBankAccount")
	public ResponseEntity<CommonApiResponse> addBankAccount(@RequestBody AddBankAccountRequestDto request )
	{
		return this.bankAccountResource.addBankAccount(request);
	}
	
	@GetMapping("/fetch/all")
	public ResponseEntity<BankAccountResponseDto> getAllBankAccounts(@RequestBody BankAccountResponseDto response )
	{
		return this.bankAccountResource.fetchAllBankAccounts();
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<BankAccountResponseDto> getBankAccount(@RequestParam ("bankId") int bankId)
	{
		return this.bankAccountResource.fetchBankAccountByBank(bankId);
	}
	
	@GetMapping("/fetch/bank")
	public ResponseEntity<BankAccountResponseDto> getBankAccountById(@RequestParam ("account Id") int accountId)
	{
		return this.bankAccountResource.fetchBankAccountByBank(accountId);
	}
	
	@GetMapping("/fetch/user")
	public ResponseEntity<BankAccountResponseDto> getBankAccountByUserId(@RequestParam ("userId") int userId)
	{
		return this.bankAccountResource.fetchBankAccountByBank(userId);
	}
	
	@GetMapping("/search")
	public ResponseEntity<BankAccountResponseDto> searchBankAccount(@RequestParam ("bankId") int bankId,@RequestParam ("Account Id") String accountId)
	{
		return this.bankAccountResource.searchBankAccounts(accountId,bankId);
	}
	
	@PutMapping("/update/status")
	public ResponseEntity<CommonApiResponse> updateBankAccountStatus(@RequestBody BankAccountStatusUpdateRequestDto request)
	{
		return this.bankAccountResource.updateBankAccountStatus(request);
	}
	
	
	
	
	
	
	
}

