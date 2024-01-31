package com.onlinebankingapplication.resource;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.lowagie.text.DocumentException;
import com.onlinebankingapplication.dto.BankTransactionRequestDto;
import com.onlinebankingapplication.dto.BankTransactionResponseDto;
import com.onlinebankingapplication.dto.CommonApiResponse;
import com.onlinebankingapplication.entity.Bank;
import com.onlinebankingapplication.entity.BankAccount;
import com.onlinebankingapplication.entity.BankAccountTransaction;
import com.onlinebankingapplication.entity.User;
import com.onlinebankingapplication.exception.BankAccountTransactionException;
import com.onlinebankingapplication.service.BankAccountService;
import com.onlinebankingapplication.service.BankAccountTransactionService;
import com.onlinebankingapplication.service.BankService;
import com.onlinebankingapplication.service.UserService;
import com.onlinebankingapplication.www.utility.BankStatementDownloaderPdf;
import com.onlinebankingapplication.www.utility.Constants.BankAccountStatus;
import com.onlinebankingapplication.www.utility.Constants.TransactionNarration;
import com.onlinebankingapplication.www.utility.Constants.TransactionType;
import com.onlinebankingapplication.www.utility.Constants.UserStatus;
import com.onlinebankingapplication.www.utility.DateTimeUtils;
import com.onlinebankingapplication.www.utility.TransactionIdGenerator;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Component
public class BankAccountTransactionResource {

	private final Logger LOG = LoggerFactory.getLogger(BankAccountTransactionResource.class);

	
	private UserService userService;

	
	private BankAccountTransactionService bankAccountTransactionService;

	
	private BankAccountService bankAccountService;

	
	private BankService bankService;

	@Transactional(rollbackOn = BankAccountTransactionException.class)
	public ResponseEntity<CommonApiResponse> depositAmountTxn(BankTransactionRequestDto request) throws Exception {

		LOG.info("Received request for deposit amount in customer account");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null) {
			response.setResponseMessage("bad request, missing data");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response,HttpStatus.BAD_REQUEST);
		}

		if(request.getAmount() == null || request.getSourceBankAccountId() == 0)
		{
			response.setResponseMessage("bad request,missing data");
			response.setIsSuccess(true);
			
			return new ResponseEntity<CommonApiResponse>(response,HttpStatus.BAD_REQUEST);
		}

		if (request.getAmount().compareTo(BigDecimal.ZERO) < 0) {
			response.setResponseMessage("Failed to deposit amount, please select valid amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		BankAccount account = this.bankAccountService.findByAccountId(request.getSourceBankAccountId());

		if (account == null) {
			response.setResponseMessage("Bank Account found, enter correct account details!!!");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (!account.getStatus().equals(BankAccountStatus.OPEN.value())) {
			response.setResponseMessage("Bank Account is Locked, Can't Deposit amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Bank bank = account.getBank();

		account.setBalance(account.getBalance().add(request.getAmount()));
		BankAccount updateAccount = this.bankAccountService.updateBankAccount(account);

		if (updateAccount == null) {
			response.setResponseMessage("Failed to deposit the amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		User user = account.getUser();

		if (!user.getStatus().equals(UserStatus.ACTIVATED.value())) {
			response.setResponseMessage("User is not Active, Can't Deposit amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		BankAccountTransaction transaction = new BankAccountTransaction();
		transaction.setType(TransactionType.DEPOSIT.Value());
		transaction.setBank(bank);
		transaction.setDestinationBankAccount(account);
		transaction.setAmount(request.getAmount());
		transaction.setNarration(TransactionNarration.BANK_DEPOSIT.Value());
		transaction.setTransactionId(TransactionIdGenerator.TransactionIdGenerator());
		transaction.setTransactionTime(
				String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
		transaction.setUser(user);

		BankAccountTransaction addedTxn = this.bankAccountTransactionService.addBankTransaction(transaction);

		if (addedTxn == null) {
			throw new BankAccountTransactionException("Failed to deposit amount in customer account");
		}

		else {
			response.setResponseMessage("Amount Deposited succesful!!!");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
		}
	}

	@Transactional(rollbackOn = BankAccountTransactionException.class)
	public ResponseEntity<CommonApiResponse> withdrawAmountTxn(BankTransactionRequestDto request) throws Exception {

		LOG.info("Received request for withdraw amount from customer account");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null) {
			response.setResponseMessage("bad request, missing data");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (request.getAmount() == null || request.getSourceBankAccountId() == 0) {
			response.setResponseMessage("bad request, invalid or missing data");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (request.getAmount().compareTo(BigDecimal.ZERO) < 0) {
			response.setResponseMessage("Failed to deposit amount, please select valid amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		BankAccount account = this.bankAccountService.findByAccountId(request.getSourceBankAccountId());

		if (account == null) {
			response.setResponseMessage("Bank Account found, enter correct account details!!!");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (!account.getStatus().equals(BankAccountStatus.OPEN.value())) {
			response.setResponseMessage("Bank Account is Locked, Can't Withdraw amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (account.getBalance().compareTo(request.getAmount()) < 0) {
			response.setResponseMessage("Failed to withdraw amount, insufficient balance in customer account");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Bank bank = account.getBank();

		account.setBalance(account.getBalance().subtract(request.getAmount()));
		BankAccount updateAccount = this.bankAccountService.updateBankAccount(account);

		if (updateAccount == null) {
			response.setResponseMessage("Failed to withdraw the amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		User user = account.getUser();

		if (!user.getStatus().equals(UserStatus.ACTIVATED.value())) {
			response.setResponseMessage("User is not Active, Can't Withdraw amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		BankAccountTransaction transaction = new BankAccountTransaction();
		transaction.setType(TransactionType.WITHDRAW.Value());
		transaction.setBank(bank);
		transaction.setDestinationBankAccount(account);
		transaction.setAmount(request.getAmount());
		transaction.setNarration(TransactionNarration.BANK_WITHDRAW.Value());
		transaction.setTransactionId(TransactionIdGenerator.TransactionIdGenerator());
		transaction.setTransactionTime(
				String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
		transaction.setUser(user);

		BankAccountTransaction addedTxn = this.bankAccountTransactionService.addBankTransaction(transaction);

		if (addedTxn == null) {
			throw new BankAccountTransactionException("Failed to withdraw amount from customer account");
		}

		else {
			response.setResponseMessage("Amount Withdraw succesful!!!");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
		}
	}

	@Transactional(rollbackOn = BankAccountTransactionException.class)
	public ResponseEntity<CommonApiResponse> accountTransfer(BankTransactionRequestDto request) throws Exception {

		LOG.info("Received request for customer account transfer");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null) {
			response.setResponseMessage("bad request, missing data");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (request.getUserId() == 0 || request.getBankId() == null || request.getAmount() == null
				|| request.getToBankAccount() == null || request.getToBankIfsc() == null) {
			response.setResponseMessage("bad request, invalid or missing data");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (request.getAmount().compareTo(BigDecimal.ZERO) < 0) {
			response.setResponseMessage("Failed to deposit amount, please select valid amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		User user = this.userService.getUserById(request.getUserId());

		if (user == null) {
			response.setResponseMessage("Sender User not found in Db");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		BankAccount senderAccount = this.bankAccountService.findByUserAndStatus(user.getId(),
				BankAccountStatus.OPEN.value());

		if (senderAccount == null) {
			response.setResponseMessage("No Linked Bank Account found, contact Bank Administrator");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (!senderAccount.getStatus().equals(BankAccountStatus.OPEN.value())) {
			response.setResponseMessage("Bank Account is Locked, Can't Transfer the Amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (senderAccount.getBalance().compareTo(request.getAmount()) < 0) {
			response.setResponseMessage("Insufficient Fund, Failed to transfer the amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		BankAccount reciepentAccount = this.bankAccountService.findByNumberAndIfscCodeAndStatus(
				request.getToBankAccount(), request.getToBankIfsc(), BankAccountStatus.OPEN.value());

		if (reciepentAccount == null) {
			response.setResponseMessage("Receipent account not found, please enter the correct details and try again");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		senderAccount.setBalance(senderAccount.getBalance().subtract(request.getAmount()));
		BankAccount updateSenderAccount = this.bankAccountService.updateBankAccount(senderAccount);

		if (updateSenderAccount == null) {
			response.setResponseMessage("Failed to transfer the amount");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		reciepentAccount.setBalance(reciepentAccount.getBalance().add(request.getAmount()));
		BankAccount updateReciepentAccount = this.bankAccountService.updateBankAccount(reciepentAccount);

		if (updateReciepentAccount == null) {
			response.setResponseMessage("Failed to transfer the amount");
			response.setIsSuccess(true);

			throw new BankAccountTransactionException("Failed to transfer the amount");
		}

		BankAccountTransaction transaction = new BankAccountTransaction();
		transaction.setType(TransactionType.ACCOUNT_TRANSFER.Value());
		transaction.setBank(senderAccount.getBank());
		transaction.setDestinationBankAccount(senderAccount);
		transaction.setDestinationBankAccount(reciepentAccount);
		transaction.setAmount(request.getAmount());
		transaction.setNarration(request.getAccountTransferPurpose());
		transaction.setTransactionId(TransactionIdGenerator.TransactionIdGenerator());
		transaction.setTransactionTime(
				String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
		transaction.setUser(user);

		BankAccountTransaction addedTxn = this.bankAccountTransactionService.addBankTransaction(transaction);

		if (addedTxn == null) {
			throw new BankAccountTransactionException("Failed to transfer the amount");
		}

		else {
			response.setResponseMessage("Amount Transfer succesful!!!");
			response.setIsSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
		}
	}

	public ResponseEntity<BankTransactionResponseDto> bankTransactionHistory(int userId) {

		LOG.info("Received request for fetching the bank transaction history");

		BankTransactionResponseDto response = new BankTransactionResponseDto();

		if (userId == 0) {
			response.setResponseMessage("bad request, missing data");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		User user = this.userService.getUserById(userId);

		if (user == null) {
			response.setResponseMessage("user not found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<BankAccountTransaction> bankAccountTransactions = new ArrayList<>();

		bankAccountTransactions = this.bankAccountTransactionService.getTransactionsByUserId(user.getId());

		if (bankAccountTransactions.isEmpty()) {
			response.setResponseMessage("No transaction found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);
		}

		response.setBankTransactions(bankAccountTransactions);
		response.setResponseMessage("Bank Transaction history fetched successfully");
		response.setIsSuccess(true);

		return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);

	}

	public ResponseEntity<BankTransactionResponseDto> allBankCustomerTransactions() {

		LOG.info("Received request for fetching all bank customer transaction");

		BankTransactionResponseDto response = new BankTransactionResponseDto();

		List<BankAccountTransaction> bankAccountTransactions = new ArrayList<>();

		bankAccountTransactions = this.bankAccountTransactionService.getAllTransactions();

		if (bankAccountTransactions.isEmpty()) {
			response.setResponseMessage("No transaction found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);
		}

		response.setBankTransactions(bankAccountTransactions);
		response.setResponseMessage("Bank Transactions fetched successfully");
		response.setIsSuccess(true);

		return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);

	}

	public ResponseEntity<BankTransactionResponseDto> getBankCustomerTransaction(int bankId, String accountNo) {

		LOG.info("Received request for fetching bank customer transaction by account no.");

		BankTransactionResponseDto response = new BankTransactionResponseDto();

		if (bankId == 0 || accountNo == null) {
			response.setResponseMessage("bad request, missing data");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Bank bank = this.bankService.getBankById(bankId);

		if (bank == null) {
			response.setResponseMessage("bank not found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<BankAccount> accounts = this.bankAccountService.getByNumberContainingIgnoreCaseAndBank(accountNo, bankId);

		if (CollectionUtils.isEmpty(accounts)) {
			response.setResponseMessage("bank account found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		BankAccount account = accounts.get(0);

		List<BankAccountTransaction> bankAccountTransactions = new ArrayList<>();

		bankAccountTransactions = this.bankAccountTransactionService.getTransactionsByUserId(account.getUser().getId());

		if (bankAccountTransactions.isEmpty()) {
			response.setResponseMessage("No transaction found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);
		}

		response.setBankTransactions(bankAccountTransactions);
		response.setResponseMessage("Bank Transactions fetched successfully");
		response.setIsSuccess(true);

		return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);

	}

	public ResponseEntity<BankTransactionResponseDto> getBankCustomerTransactionByTimeRange(int bankId,
			String accountNo, String startTime, String endTime) {

		LOG.info("Received request for fetching bank customer transaction by account and time range");

		BankTransactionResponseDto response = new BankTransactionResponseDto();

		if (bankId == 0 || accountNo == null) {
			response.setResponseMessage("bad request, missing data");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Bank bank = this.bankService.getBankById(bankId);

		if (bank == null) {
			response.setResponseMessage("bank not found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<BankAccount> accounts = this.bankAccountService.getByNumberContainingIgnoreCaseAndBank(accountNo, bankId);

		if (CollectionUtils.isEmpty(accounts)) {
			response.setResponseMessage("bank account found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		BankAccount account = accounts.get(0);

		List<BankAccountTransaction> bankAccountTransactions = new ArrayList<>();

		bankAccountTransactions = this.bankAccountTransactionService
				.getAllTransactionsByTransactionTimeAndBankAccoountId(startTime, endTime, account.getId());

		if (bankAccountTransactions.isEmpty()) {
			response.setResponseMessage("No transaction found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);
		}

		response.setBankTransactions(bankAccountTransactions);
		response.setResponseMessage("Bank Transactions fetched successfully");
		response.setIsSuccess(true);

		return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);

	}

	public ResponseEntity<BankTransactionResponseDto> getBankAllCustomerTransactionByTimeRange(int bankId,
			String startTime, String endTime) {

		LOG.info("Received request for fetching bank customer transaction by account no.");

		BankTransactionResponseDto response = new BankTransactionResponseDto();

		if (bankId == 0) {
			response.setResponseMessage("bad request, missing data");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Bank bank = this.bankService.getBankById(bankId);

		if (bank == null) {
			response.setResponseMessage("bank not found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<BankAccountTransaction> bankAccountTransactions = new ArrayList<>();

		bankAccountTransactions = this.bankAccountTransactionService
				.getAllTransactionsByTransactionTimeAndBankId(startTime, endTime, bankId);

		if (bankAccountTransactions.isEmpty()) {
			response.setResponseMessage("No transaction found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);
		}

		response.setBankTransactions(bankAccountTransactions);
		response.setResponseMessage("Bank Transactions fetched successfully");
		response.setIsSuccess(true);

		return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);

	}

	public ResponseEntity<BankTransactionResponseDto> getBankAllCustomerTransaction(int bankId) {

		LOG.info("Received request for fetching bank all customer transactions");

		BankTransactionResponseDto response = new BankTransactionResponseDto();

		if (bankId == 0) {
			response.setResponseMessage("bad request, missing data");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Bank bank = this.bankService.getBankById(bankId);

		if (bank == null) {
			response.setResponseMessage("bank not found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<BankAccountTransaction> bankAccountTransactions = new ArrayList<>();

		bankAccountTransactions = this.bankAccountTransactionService.findByBankOrderByIdDesc(bankId);

		if (bankAccountTransactions.isEmpty()) {
			response.setResponseMessage("No transaction found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);
		}

		response.setBankTransactions(bankAccountTransactions);
		response.setResponseMessage("Bank Transactions fetched successfully");
		response.setIsSuccess(true);

		return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);

	}

	public ResponseEntity<BankTransactionResponseDto> bankTransactionHistoryByTimeRange(int userId, String startTime,
			String endTime) {

		LOG.info("Received request for fetching bank all customer transactions");

		BankTransactionResponseDto response = new BankTransactionResponseDto();

		if (userId == 0) {
			response.setResponseMessage("bad request, missing data");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		User user = this.userService.getUserById(userId);

		if (user == null) {
			response.setResponseMessage("user not found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		BankAccount account = this.bankAccountService.findByUserAndStatus(user.getId(), BankAccountStatus.OPEN.value());

		if (account == null) {
			response.setResponseMessage("account not linked with user account");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<BankAccountTransaction> bankAccountTransactions = new ArrayList<>();

		bankAccountTransactions = this.bankAccountTransactionService
				.getByUserAndTransactionTimeBetweenOrderByIdDesc(userId, startTime, endTime);

		if (bankAccountTransactions.isEmpty()) {
			response.setResponseMessage("No transaction found");
			response.setIsSuccess(true);

			return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);
		}

		response.setBankTransactions(bankAccountTransactions);
		response.setResponseMessage("Bank Transactions fetched successfully");
		response.setIsSuccess(true);

		return new ResponseEntity<BankTransactionResponseDto>(response, HttpStatus.OK);

	}

	public void downloadBankStatement(int accountId, String startTime, String endTime, HttpServletResponse response)
			throws DocumentException, IOException {

		if (accountId == 0 || startTime == null || endTime == null) {
			return;
		}

		List<BankAccountTransaction> bankAccountTransactions = this.bankAccountTransactionService
				.getAllTransactionsByTransactionTimeAndBankAccoountId(startTime, endTime, accountId);

		if (CollectionUtils.isEmpty(bankAccountTransactions)) {
			return;
		}

		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + bankAccountTransactions.get(0).getBankAccount().getNumber()
				+ "_Statement.pdf";
		response.setHeader(headerKey, headerValue);

		BankStatementDownloaderPdf exporter = new BankStatementDownloaderPdf(DateTimeUtils.getLocalDateTimeFromEpochTime(endTime),
				DateTimeUtils.getLocalDateTimeFromEpochTime(startTime),
				bankAccountTransactions);
		exporter.export(response);

		return;

	}

}
