package com.gmail.at.ivanehreshi.user.listeners;

import com.gmail.at.ivanehreshi.mobilenetwork.SMS;
import com.gmail.at.ivanehreshi.mobilenetwork.user.events.SMSUserInitEvent;
import com.gmail.at.ivanehreshi.mobilenetwork.user.events.SMSUserListenerAdapter;
import com.gmail.at.ivanehreshi.user.User;

public class SMSDefaultPayListener extends SMSUserListenerAdapter{
	public final static SMSDefaultPayListener instance = new SMSDefaultPayListener(); 

	private int priority = 10;
	
	@Override
	public void onSMSInitEvent(SMSUserInitEvent event){
		User sender = event.sms.sender.getOperator().getUser(event.sms.sender);
		if(event.price > sender.getBalance().getBalance()){
			event.refused = true;
		}
	}
}
