package com.gmail.at.ivanehreshi.mobilenetwork.emulator;

import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.PhoneNumber;

public class CellPhoneEmulator {
	private PhoneNumber phoneNumber;
	SMSLocalHistory smsLocalHistory;
	
	public CellPhoneEmulator(PhoneNumber phoneNumber){
		this.phoneNumber = phoneNumber;
	}
}
