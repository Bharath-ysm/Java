package com.onlinebankingapplication.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.onlinebankingapplication.dto.BankTransactionRequestDto;
import com.onlinebankingapplication.dto.BankTransactionResponseDto;
import com.onlinebankingapplication.dto.CommonApiResponse;
import com.onlinebankingapplication.resource.BankAccountTransactionResource;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/transaction")
public class BankAccountTransactionController {

	@Autowired
	
	private BankAccountTransactionResource  bankAccountTransactionResource;
	
	@PostMapping ("deposit/transaction")
	 
	@Operation (summary="API to deposit Transaction")
	
	public ResponseEntity<CommonApiResponse> bankDepositTransaction(@RequestBody BankTransactionRequestDto request) throws Exception{
		
		return this.bankAccountTransactionResource.depositAmountTxn(request);
	}
	
	@PostMapping("with draw/transaction")
	
	public ResponseEntity<CommonApiResponse> bankWithDrawTransaction(@RequestBody BankTransactionRequestDto request) throws Exception{
		return this.bankAccountTransactionResource.withdrawAmountTxn(request);
	}
	
	@PostMapping("Transfer/Transaction")
	
	public ResponseEntity<CommonApiResponse> accountTransferTransaction(@RequestBody BankTransactionRequestDto request) throws Exception{
		return this.bankAccountTransactionResource.accountTransfer(request);
	}
	
	@GetMapping("bank/user Transaction History")
	public ResponseEntity<BankTransactionResponseDto> getUserBankTransactionHistory(@RequestParam("UserId") int userId){
		return this.bankAccountTransactionResource.bankTransactionHistory(userId);
	}
	
	@GetMapping("All Bank/ customer transaction")
	
	public ResponseEntity<BankTransactionResponseDto> getAllBankCustomerTransactions(){
		return this.bankAccountTransactionResource.allBankCustomerTransactions();
	} 
	@GetMapping("bank/customer transaction")
	public ResponseEntity<BankTransactionResponseDto> getBankCustomerTransaction(@RequestParam("BankId") int bankId,@RequestParam("AccountNumber") String accountNo) {
		return this.bankAccountTransactionResource.getBankCustomerTransaction(bankId, accountNo);
	}
	@GetMapping("Bank customer transaction/ by Time Range")
	
	public ResponseEntity<BankTransactionResponseDto> getBankCustomerTransactionByTimeRange(@RequestParam("BankId") int bankId,@RequestParam("AccountNumber")
			String accountNo,@RequestParam("StartTime") String startTime,@RequestParam("EndTime") String endTime) {
		return this.bankAccountTransactionResource.getBankCustomerTransactionByTimeRange(bankId,accountNo,startTime, endTime);
	}
	
	@GetMapping ("All customers Transaction/ by time range")
	public ResponseEntity<BankTransactionResponseDto> getBankAllCustomerTransactionByTimeRange(@RequestParam("BankId") int bankId,
		   @RequestParam("StartTime") String startTime,@RequestParam("EndTime") String endTime){
		return this.bankAccountTransactionResource.getBankAllCustomerTransactionByTimeRange(bankId, startTime, endTime);
	}
	
	@GetMapping("All customer Transaction")
	public ResponseEntity<BankTransactionResponseDto> getBankAllCustomerTransaction(@RequestParam("BankId") int bankId){
		return this.bankAccountTransactionResource.getBankAllCustomerTransaction(bankId);
	}
	@GetMapping("CustomerTransaction/byTimeRange")
	public ResponseEntity<BankTransactionResponseDto> bankTransactionHistoryByTimeRange(@RequestParam("UserId")int userId,@RequestParam("StartTime") String startTime,
		@RequestParam("EndTime") String endTime){
		return this.bankAccountTransactionResource.bankTransactionHistoryByTimeRange(userId,startTime,endTime);
	}
	
	@PostMapping("download/Statement")
	
	public void downloadBankStatement(@RequestParam("AccountId") int accountId,@RequestParam("StartTime") String startTime,@RequestParam("EndTime") String endTime,HttpServletResponse  response)
			throws DocumentException, IOException{
		
		 this.bankAccountTransactionResource.downloadBankStatement(accountId, startTime, endTime, response);
	}
}