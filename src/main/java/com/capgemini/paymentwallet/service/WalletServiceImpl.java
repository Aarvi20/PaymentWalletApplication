package com.capgemini.paymentwallet.service;

import java.time.LocalDateTime;
import java.util.List;
import com.capgemini.paymentwallet.dao.WalletDaoImpl;
import com.capgemini.paymentwallet.model.WalletTransaction;
import com.capgemini.paymentwallet.model.WalletUser;
import com.capgemini.paymentwallet.model.WalletAccount.*;


public class WalletServiceImpl implements WalletServiceIntf{
			
	int adminID=101;
	String adminPassword="password";
	WalletDaoImpl wDI=new WalletDaoImpl(); 
	
	//Function To Register Users
	
	public void registerUser(WalletUser user) {
				
				wDI.setUserData(user);
				WalletUser.showMsg("\nYour Account Has Been Successfully Registered");
				WalletUser.showMsg("Your Account ID : "+user.getwAccount().getAccountId()+"\n");				
	}

	//Function To Login As Admin
	
	public String loginAdmin(int aid, String apass) {
		if (aid == adminID && apass.equals(adminPassword))
			return "Logged In";
		else
			return "Not Logged In";
	}
	
	//Function To Show Accounts That Have Not Been Approved By Admin
	
	public void showNonApprovedAccounts(){
		List<WalletUser> uList=wDI.getUserData();
		WalletUser.showMsg("Account ID \t\tUser ID \t\tLogin Name");
		for(WalletUser user:uList)
		{
			if(user.getwAccount().getType().equals(Status.NotApproved))
			{
				WalletUser.showMsg(user.getwAccount().getAccountId()+"\t\t\t"+user.getUserId()+"\t\t\t"+user.getLoginName());
			}
		}				
	}

	//Function To Show Accounts That Have Been Approved By Admin
	
	public void showApprovedAccounts(){
		List<WalletUser> uList=wDI.getUserData();
		WalletUser.showMsg("Account ID \t\tUser ID \t\tLogin Name");
		for(WalletUser user:uList)
		{
			if(user.getwAccount().getType().equals(Status.Approved)) {
				WalletUser.showMsg(user.getwAccount().getAccountId()+"\t\t\t"+user.getUserId()+"\t\t\t"+user.getLoginName());
			}
		}
	}

	//Function That Approves Accounts By Admin
	

	public void approveAccount(int appAccID) {	
		List<WalletUser> uList=wDI.getUserData();
		for(WalletUser user:uList){
			if(user.getwAccount().getAccountId()==appAccID) {
				user.getwAccount().setType(Status.Approved);
			}
		}
	}

	//Function That Show Details of Accounts to an Admin
	
	public void showAccountDetails() {
		List<WalletUser> uList=wDI.getUserData();
		WalletUser.showMsg("Login Name \t Account Balance \t Account ID \t    Status \t");
		for(WalletUser user:uList){
			WalletUser.showMsg("    "+user.getLoginName()+"\t\t       "+user.getwAccount().getAccountBalance()+"\t           "+user.getwAccount().getAccountId()+"\t    "+user.getwAccount().getType());
		}
	}

	//Function That Checks Whether a User can Login or Not
	

	public WalletUser loginUser(String id, String pass) {
		List<WalletUser> uList=wDI.getUserData();
		boolean flag=false;
		WalletUser currentUser=null;
		for(WalletUser u:uList) {
			if(u.getLoginName().equals(id) && u.getPassword().equals(pass)) {
				if(u.getwAccount().getType().equals(Status.Approved)) {
					flag=true;
					currentUser=u;
					break;
				}
				else {
					WalletUser.showMsg("\nAccount Not Approved By Admin\n");
					return null;	
				}							
			}
		}
		if(flag) {	
				return currentUser;
		}
		else {
			WalletUser.showMsg("\nInvalid UserName & Password \n Enter Valid Input\n");			
			return null;
		}
	}

	//Function That Shows Account Details of a Logged In User
	
	public void myAccount(WalletUser user) {
		
		WalletUser.showMsg("Welcome User    \t: \t"+user.getLoginName());
		WalletUser.showMsg("Account Id      \t: \t"+user.getwAccount().getAccountId());
		WalletUser.showMsg("Account Balance \t: \t"+user.getwAccount().getAccountBalance());	
	}

	//Function That Shows Account Details of a Logged In User
	
	public void showBalance(WalletUser user) {
		WalletUser.showMsg("Current Account Balance\t\t:\t"+user.getwAccount().getAccountBalance());
	}

	//Function That Allows a Logged In User to Add Amount in his Wallet
	
	public void addMoneyToWallet(WalletUser user,double amount) {
		user.getwAccount().setAccountBalance(user.getwAccount().getAccountBalance()+amount);
		WalletUser.showMsg(amount+" Added Successfully\n");
		int tid=(int) (Math.random()*1234 + 9999);
		LocalDateTime date=LocalDateTime.now();
		String description = "Deposit";
		user.setwTransaction(new WalletTransaction(tid, description, date, amount, user.getwAccount().getAccountBalance()));
		user.getwAccount().getTransactionHistory().add(user.getwTransaction());
	}

	//Function That Allows a Logged In User to Send Money to any User

	
	public String sendMoney(WalletUser user, int userid,double amnt) {
		List<WalletUser> uList=wDI.getUserData();
		String msg="";

 for(WalletUser i:uList) {
	if(i.getUserId()==userid) {
		if(user.getwAccount().getAccountBalance()>amnt) {
			if(i.getwAccount().getType().equals(Status.Approved)) {
				user.getwAccount().setAccountBalance(user.getwAccount().getAccountBalance()-amnt);
				i.getwAccount().setAccountBalance(i.getwAccount().getAccountBalance()+amnt);
				msg=amnt+" Transferred To "+i.getUserName()+" (Account ID : "+i.getwAccount().getAccountId()+" )\n";
				int tid=(int) (Math.random()*1234 + 9999);
				LocalDateTime date=LocalDateTime.now();
				String description = "Money Transfer";
				user.setwTransaction(new WalletTransaction(tid, description, date, amnt, user.getwAccount().getAccountBalance()));
				user.getwAccount().getTransactionHistory().add(user.getwTransaction());
				return msg;
			}
			else if(i.getwAccount().getType().equals(Status.NotApproved)) {
				msg="\nTransaction Failed\nCannot Transfer as the account is not approved";
				return msg;
			}

              }
		else if(user.getwAccount().getAccountBalance()<amnt){
		msg="\nTransaction Failed\nCannot Transfer "+amnt+" as your current balance is "+user.getwAccount().getAccountBalance()+"\n";
		return msg;

             	}
			}
				else	
					msg="Transaction Failed\nInvalid User ID\n";
		}
		return msg;
	}
	
//Function That Allows a Logged In User to Show History of all Transactions

	public void showTransacations(WalletUser user) {
		for(WalletTransaction i:user.getwAccount().getTransactionHistory()) {
			if(i.getTransactionId()!=null) {
				WalletUser.showMsg("Transaction ID        \t\t: \t"+i.getTransactionId());
				WalletUser.showMsg("Type Of Transaction   \t\t: \t"+i.getDescription());
				WalletUser.showMsg("Date Of Transaction   \t\t: \t"+i.getDateOfTransaction());
				WalletUser.showMsg("Amount Transacted     \t\t: \t"+i.getAmount());
				WalletUser.showMsg("Current Account Balance\t\t: \t"+i.getAccountBalance());
				WalletUser.showMsg("\n");
			}
		}
	}

}