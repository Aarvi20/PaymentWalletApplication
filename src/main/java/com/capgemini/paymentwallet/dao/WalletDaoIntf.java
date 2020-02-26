package com.capgemini.paymentwallet.dao;

import java.util.List;

import com.capgemini.paymentwallet.model.WalletUser;

public interface WalletDaoIntf {

	public List<WalletUser> getUserData();
	public void setUserData(WalletUser user);
	
}