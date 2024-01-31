package com.onlinebankingapplication.www.utility;

public class Constants
{
	public enum UserRole
	{
		ROLE_CUSTOMER("customer"),
		ROLE_ADMIN("admin"),
		ROLE_BANK("bank");
		
		private String role;
		
		private UserRole(String role)
		{
			this.role=role;
		}
		
		public String value()
		{
			return this.role;
		}
	}
	public enum UserStatus
	{
		ACTIVATED("active"),
		DEACTIVATED("deactive");
		
		private String status;
		
		private UserStatus(String status)
		{
			this.status=status;
		}
		
		public String value()
		{
			return this.status;
			
		}
	}
	public enum IsAccountLinked
	{
		YES("yes"),
		NO("no");
	
	private String account;
	
	private IsAccountLinked(String account)
	{
		this.account=account;
	}
	
	public String value()
	{
		return this.account;
	}
}
	
	public enum BankAccountStatus
	{
		OPEN("open"),
		DELETED("deleted"),
		LOCK("lock");
		
		private String status;
		
		private BankAccountStatus(String status)
		{
			this.status=status;
		}
		
		public String value()
		{
			return this.status;
			
		}
	}
	
	public enum BankAccountType
	{
		SAVINGS("savings"),
		CURRENT("current");
		
		private String type;
		
		private BankAccountType(String type)
		{
			this.type=type;
		}
		
		public String value()
		{
			return this.type;
		}
	}
	
	public enum TransactionType
	{
		WITHDRAW("withdraw"),
		DEPOSIT("deposit"),
		BALANCE_FETCH("balance_fetch"),
		ACCOUNT_TRANSFER("account_transfer");
	
	
	private String type;
	
	private TransactionType(String type)
	{
		this.type=type;
	}
	
	public String Value()
	{
		return this.type;
	}
 }
	
	public enum TransactionNarration
	{
		BANK_WITHDRAW("bank_withdraw"),
		BANK_DEPOSIT("bank_deposit");
		
		private String narration;
		
		private TransactionNarration(String narration)
		{
			this.narration=narration;
		}
		
		public String Value()
		{
			return this.narration;
		}
	}
	
	
	
	
}
