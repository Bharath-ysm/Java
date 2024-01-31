package com.onlinebankingapplication.dto;

import java.util.ArrayList;
import java.util.List;

import com.onlinebankingapplication.entity.Bank;

public class BankDetailsResponseDto extends CommonApiResponse{

	
	List<Bank> banks = new ArrayList<>();

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}
	
	
}