package com.capgemini.paymentwallet.service;

import com.capgemini.paymentwallet.model.WalletUser;

public interface WalletServiceIntf {
   
	public void registerUser(WalletUser user);
	
	public String loginAdmin(int adminID,String adminPassword);
	
	public void showNonApprovedAccounts();

	public void showApprovedAccounts();
	
	public void approveAccount(int appAccID);
	
	public void showAccountDetails();
		
	public WalletUser loginUser(String id, String pass);
	
	public void myAccount(WalletUser user);
		
	public void addMoneyToWallet(WalletUser user,double amount);
	
	public String sendMoney(WalletUser user, int userid,double amnt);
	
	public void showTransacations(WalletUser user);
	
	public void showBalance(WalletUser user);

}




