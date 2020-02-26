package com.capgemini.paymentwallet.model;

public class WalletUser {
	private Integer userId;
	private String userName;
	private String password;
	private String phoneNumber;
	private String loginName;
	
	WalletAccount wAccount;
	WalletTransaction wTransaction;
	
	public WalletUser(Integer userId, String userName, String password, String phoneNumber, String loginName) {
		
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.loginName = loginName;
		
		this.wAccount=new WalletAccount();
		this.wAccount.accountBalance=0.0;
		this.wAccount.accountId=(int) (Math.random()*1234 + 9999);
		
		this.wTransaction=new WalletTransaction();
		
		this.wAccount.transactionHistory.add(this.wTransaction);

		
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public WalletAccount getwAccount() {
		return wAccount;
	}
	public void setwAccount(WalletAccount wAccount) {
		this.wAccount = wAccount;
	}

	public WalletTransaction getwTransaction() {
		return wTransaction;
	}

	public void setwTransaction(WalletTransaction wTransaction) {
		this.wTransaction = wTransaction;
	}	
	
	public static void showMsg(String s) {
			System.out.println(s);				
	}
	
}

