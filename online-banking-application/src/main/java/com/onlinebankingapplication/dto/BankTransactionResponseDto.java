package com.onlinebankingapplication.dto;

import java.util.ArrayList;
import java.util.List;

import com.onlinebankingapplication.entity.BankAccountTransaction;

public class BankTransactionResponseDto extends CommonApiResponse {

	private List<BankAccountTransaction> bankTransactions = new ArrayList<>();
	public void setBankAccountTransaction(List<BankAccountTransaction> bankTransactions) {
		this.bankTransactions=bankTransactions;
	}
	
	private List<BankAccountTransaction> bankAccountTransactions = new ArrayList<>();
	public void setBankTransactions(List<BankAccountTransaction> bankAccountTransactions) {
		this.bankAccountTransactions=bankAccountTransactions;
		
	}
}