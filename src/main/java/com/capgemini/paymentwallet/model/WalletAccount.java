package com.capgemini.paymentwallet.model;

import java.util.ArrayList;
import java.util.List;

public class WalletAccount {
	public enum Status{Approved,NotApproved}
	Integer accountId=null;
	Double accountBalance;
	Status type=Status.NotApproved;
	List<WalletTransaction> transactionHistory;
	
	public WalletAccount() {
	
		this.transactionHistory=new ArrayList<WalletTransaction>();
	}
	
	public WalletAccount(Integer accountId, Double accountBalance, Status type, List<WalletTransaction> transactionHistory) {
	
		this.accountId = accountId;
		this.accountBalance = accountBalance;
		this.type = type;
		this.transactionHistory = transactionHistory;
	}
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public Status getType() {
		return type;
	}

	public void setType(Status type) {
		this.type = type;
	}

	public List<WalletTransaction> getTransactionHistory() {
		return transactionHistory;
	}
	public void setTransactionHistory(List<WalletTransaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
		
}
