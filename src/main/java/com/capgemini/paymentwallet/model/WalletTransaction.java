package com.capgemini.paymentwallet.model;

import java.time.LocalDateTime;

public class WalletTransaction {
	    Integer transactionId;
	    String description;
	    LocalDateTime dateOfTransaction;
	    Double amount;
	    Double accountBalance;
	    
	    WalletTransaction(){
	    	
	    }

		public WalletTransaction(Integer transactionId, String description, LocalDateTime dateOfTransaction,
				Double amount, Double accountBalance) {
		
			this.transactionId = transactionId;
			this.description = description;
			this.dateOfTransaction = dateOfTransaction;
			this.amount = amount;
			this.accountBalance = accountBalance;
		}

		public Integer getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(Integer transactionId) {
			this.transactionId = transactionId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public LocalDateTime getDateOfTransaction() {
			return dateOfTransaction;
		}

		public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
			this.dateOfTransaction = dateOfTransaction;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		public Double getAccountBalance() {
			return accountBalance;
		}

		public void setAccountBalance(Double accountBalance) {
			this.accountBalance = accountBalance;
		}
	    
		

}
