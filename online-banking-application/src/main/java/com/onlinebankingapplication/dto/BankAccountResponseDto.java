package com.onlinebankingapplication.dto;
import java.util.List;

import com.onlinebankingapplication.entity.BankAccount;

public class BankAccountResponseDto extends CommonApiResponse
{
	private List<BankAccount> accounts;
	
	public void setAccounts(List<BankAccount>acounts)
	{
		this.accounts=accounts;
	}
}
