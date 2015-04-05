package com.gmail.at.ivanehreshi.mobilenetwork.emulator;

import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.IPhoneNumber;

public class CellPhoneEmulator {
	private IPhoneNumber phoneNumber;
	SMSLocalHistory smsLocalHistory;
	
	public CellPhoneEmulator(IPhoneNumber phoneNumber){
		this.phoneNumber = phoneNumber;
	}
}
