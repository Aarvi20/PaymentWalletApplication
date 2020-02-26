package com.capgemini.paymentwallet.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.paymentwallet.model.WalletUser;

public class WalletDaoImpl implements WalletDaoIntf {
	
	List<WalletUser> uList=new ArrayList<WalletUser>();

	public List<WalletUser> getUserData() {
		return uList;
	}

	public void setUserData(WalletUser user) {
		uList.add(user);
		
	}

	
}

