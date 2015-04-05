package com.gmail.at.ivanehreshi.mobilenetwork;

import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.PhoneNumber;

public class SMS {
	public PhoneNumber sender;
	public PhoneNumber receiver;
	public String message;
	
	public SMS(PhoneNumber sender, PhoneNumber receiver, String message){
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
	}
}
