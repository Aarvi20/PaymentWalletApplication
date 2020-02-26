package com.capgemini.paymentwallet.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import com.capgemini.paymentwallet.exception.WalletException;
import com.capgemini.paymentwallet.model.WalletUser;
import com.capgemini.paymentwallet.service.WalletServiceImpl;

public class UserInterface {
		InputStreamReader isr;
		BufferedReader bsr;
		List<WalletUser> userList;
		WalletServiceImpl wCon;

	public static void main(String[] args) {

			UserInterface ui = new UserInterface();
			ui.isr = new InputStreamReader(System.in);
			ui.bsr = new BufferedReader(ui.isr);
			ui.wCon=new WalletServiceImpl();
			ui.paymentWallet();
		}

		public void paymentWallet(){
				
			try {							
					WalletUser.showMsg("Welcome to Payment Wallet\t\t\t\t\t \n  1. Login \n  2. Register  \n  3. Exit\n");
					int choice = Integer.parseInt(bsr.readLine());
					switch (choice) {				
					case 1:				
						login();
						break;					
					case 2:
						registerUser();
						break;
					case 3:
						WalletUser.showMsg("\n ***THANKYOU FOR USING PAYMENT WALLET APPLICATION***");
						System.exit(0);
						break;
					default:
						throw new WalletException("Please Select Again");										
					}
				}
				catch(Exception e) {
					WalletUser.showMsg("Exception occured: "+e.getMessage()+"\nYou have entered a wrong choice\n");
					paymentWallet();
				}		
	}

		private void registerUser() {
			Integer userId=0;
			String password = null;
			String phoneNumber=null;
			
			try {
				WalletUser.showMsg("Enter your Details\n");
				try {
					WalletUser.showMsg("Enter your UserId      : ");
					 userId = Integer.parseInt(bsr.readLine());
				}catch (Exception e) {
					WalletUser.showMsg("Exception occured: "+e.getMessage());
					WalletUser.showMsg("Create Valid UserId (UserId can only be an Integer)\n");
					registerUser();
				}
				
				WalletUser.showMsg("Enter your UserName    : ");
				String userName = bsr.readLine();
				
			try {
				WalletUser.showMsg("Enter your Password    : ");
				 password = bsr.readLine();
				if(password.length()<8)
					throw new WalletException("Create Valid Password (Password must be minimum of 8 characters)\n");											
			}catch(Exception e) {
				WalletUser.showMsg("Exception occured: "+e.getMessage());
				registerUser();
			}
				
			try {
				WalletUser.showMsg("Enter your PhoneNumber :");
				 phoneNumber = bsr.readLine();
				if(phoneNumber.length()!=10)
					throw new WalletException("Enter Valid PhoneNumber (PhoneNumber must be minimum of 10 Digits)\n");											
			}catch(Exception e) {
				WalletUser.showMsg("Exception occured: "+e.getMessage());
				registerUser();
			}	
							
				WalletUser.showMsg("Enter your LoginName   : ");
				String loginName = bsr.readLine();				
				
				wCon.registerUser(new WalletUser(userId,userName,password,phoneNumber,loginName));
			    paymentWallet();	    	
			} catch(Exception e) {
				e.getMessage();
		    	}
		}

		private void login() {
			WalletUser.showMsg("1. Login As User\n2. Login As Admin\n");
			int choice1;
			try {
				choice1 = Integer.parseInt(bsr.readLine());
				switch (choice1) {
				case 1:
					loginUser();
					break;
				case 2:
					loginAdmin();
					break;		
				default :
					throw new WalletException(" You Have Entered a Wrong Choice \nPlease Login Again\n");	
				}
			} 
			catch (NumberFormatException e) {
				WalletUser.showMsg(e.getMessage());
				login();
			}catch (IOException ioe) {
				WalletUser.showMsg(ioe.getMessage());
				login();
			} catch (WalletException e) {
				WalletUser.showMsg("Exception Occured: "+ e.getMessage());
				login();
			}
			
		}
		
		private void loginAdmin() {
			
				try {				
					WalletUser.showMsg("\nEnter the Admin UserId :");
					int aid = Integer.parseInt(bsr.readLine());
					WalletUser.showMsg("Enter the Admin Password :");
					String apass = bsr.readLine();
					String isSignedIn=wCon.loginAdmin(aid, apass);
					
					if(isSignedIn.equalsIgnoreCase("Logged In"))
					{
						WalletUser.showMsg("\nWelcome to Admin Panel !");
						homePageAdmin();
					}					
					else {
						throw new WalletException("Invalid Password Or Invalid User ID");						
					}
				} 
				catch (NumberFormatException ne) {
					WalletUser.showMsg(ne.getMessage());
					loginAdmin();
				} 
				catch (IOException ioe) {
					WalletUser.showMsg(ioe.getMessage());
					login();
				} catch (WalletException e) {
					WalletUser.showMsg("Exception Occured: "+e.getMessage());	
					loginAdmin();
				}						
		}
		
		private void loginUser() {
			try {
				WalletUser.showMsg("Enter your loginName : ");
				String id = bsr.readLine();
				WalletUser.showMsg("Enter your password  : ");
				String password = bsr.readLine();
				
				WalletUser currentUser=wCon.loginUser(id, password);
				
				if(currentUser!=null) {
					WalletUser.showMsg("\nWelcome "+currentUser.getLoginName());
					homePageUser(currentUser);
				}
				else
					loginUser();
				
			} catch (Exception e) {		
				e.getMessage();
				loginUser();
			}		 
		}

		public void homePageAdmin() {
			try {
				int input5=0;
				do {
					WalletUser.showMsg("\nEnter Your choice : \n 1. Show Non-Approved Accounts\n 2. Show Approved Accounts\n 3. Approve Account\n 4. Show Account Details\n 5. Log Out ");
					input5 = Integer.parseInt(bsr.readLine());					
					switch (input5) {
						case 1: 
							wCon.showNonApprovedAccounts();							
							break;
						case 2:
							wCon.showApprovedAccounts();
							break;
						case 3:
							WalletUser.showMsg("\nEnter the Account ID you want to approve");
							int appAccID=Integer.parseInt(bsr.readLine());
							wCon.approveAccount(appAccID);
							break;
						case 4:
							wCon.showAccountDetails();
							break;
						case 5:WalletUser.showMsg("\nYou Have Been Logged Out As Admin\n");
							paymentWallet();
							break;
						default:
							throw new WalletException("Invalid Input !\nPlease Enter Valid Input");						
						}	
				}while(input5!=5);
			}
			catch (NumberFormatException ne) {
				WalletUser.showMsg(ne.getMessage());
				homePageAdmin();
			}
			catch (IOException ioe) {
				WalletUser.showMsg(ioe.getMessage());
				homePageAdmin();
			} catch (WalletException e) {
				WalletUser.showMsg("Exception occured : "+e.getMessage());
				homePageAdmin();
				
			}		
		}
		
		public void homePageUser(WalletUser currentUser) {
			try {
				int choice1=0;
				do {
				WalletUser.showMsg(" 1. Send Money \n 2. Add Money to Wallet \n 3. Show Wallet Balance \n 4. Show Transactions \n 5. My Account \n 6. LogOut ");
				choice1 =Integer.parseInt(bsr.readLine());
				switch (choice1) {
				case 1:
					WalletUser.showMsg("\nEnter the User ID of the user in whose account you want to sent money");
					int userid;
					userid=Integer.parseInt(bsr.readLine());
					WalletUser.showMsg("\nEnter the amount you want to send\n");
					double amnt;
					amnt=Double.parseDouble(bsr.readLine());
					WalletUser.showMsg(wCon.sendMoney(currentUser,userid,amnt));
					break;				
				case 2:
		           WalletUser.showMsg("Enter amount you want to add in your wallet");
		           double amount=Double.parseDouble(bsr.readLine());
		           wCon.addMoneyToWallet(currentUser,amount);
		           break;	           
				case 3:
					wCon.showBalance(currentUser);
					break;
				case 4:
					wCon.showTransacations(currentUser);
					break;
				case 5:
					wCon.myAccount(currentUser);
					break;			
				case 6:
					WalletUser.showMsg("\nYou Have Been Logged Out As User\n");
					paymentWallet();
					break;	
				default:
					throw new WalletException("\\nYou Have Entered a Wrong Choice\\nPlease Login Again");				
				 }			
			  }while(choice1!=6);
				
			}catch (NumberFormatException ne) {
				WalletUser.showMsg(ne.getMessage());
				 homePageUser(currentUser);
			}
			catch (IOException ioe) {
				WalletUser.showMsg(ioe.getMessage());
				 homePageUser(currentUser);
			} catch (WalletException e) {		
				WalletUser.showMsg("Exception occured : "+e.getMessage());
				 homePageUser(currentUser);
			}
			
	}

	

}
