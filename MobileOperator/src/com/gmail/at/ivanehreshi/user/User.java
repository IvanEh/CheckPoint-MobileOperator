package com.gmail.at.ivanehreshi.user;

import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.Balance;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.MobilePackage;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.PhoneNumber;

public class User {
	private PhoneNumber phoneNumber;
	private Balance balance;
	private MobilePackage paidPackage;
	
	/**
	 * if no enough money to pay for mainPackage then this package
	 * will be used
	 */
	private MobilePackage defaultPackage;
	
	
}

enum UserPackageStatus {
	DEFAULT,
	PAID;
}