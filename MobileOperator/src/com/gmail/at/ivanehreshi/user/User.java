package com.gmail.at.ivanehreshi.user;

import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.IBalance;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.IMobilePackage;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.IPhoneNumber;

public class User {
	private IPhoneNumber phoneNumber;
	private IBalance balance;
	private IMobilePackage paidPackage;
	
	/**
	 * if no enough money to pay for mainPackage then this package
	 * will be used
	 */
	private IMobilePackage defaultPackage;
	
	
}

enum UserPackageStatus {
	DEFAULT,
	PAID;
}