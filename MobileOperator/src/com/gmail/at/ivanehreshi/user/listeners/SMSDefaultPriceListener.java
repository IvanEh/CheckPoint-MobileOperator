package com.gmail.at.ivanehreshi.user.listeners;

import com.gmail.at.ivanehreshi.mobilenetwork.user.events.SMSUserInitEvent;
import com.gmail.at.ivanehreshi.mobilenetwork.user.events.SMSUserListenerAdapter;
import com.gmail.at.ivanehreshi.user.User;

/**
 * sets the default price determined by the package
 * @author Ivan
 */
public class SMSDefaultPriceListener extends SMSUserListenerAdapter {
	public final static SMSDefaultPriceListener instance = new SMSDefaultPriceListener(); 	

	private int priority = 1;
	@Override
	public void onSMSInitEvent(SMSUserInitEvent event){
		User sender = event.sms.sender.getOperator().getUser(event.sms.sender);
		event.price = sender.getPackage().getSMSPrice(event.sms);
	}
}
