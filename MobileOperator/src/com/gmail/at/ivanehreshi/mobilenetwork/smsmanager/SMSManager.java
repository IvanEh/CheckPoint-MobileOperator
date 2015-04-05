package com.gmail.at.ivanehreshi.mobilenetwork.smsmanager;

import java.util.ArrayList;
import java.util.HashMap;

import com.gmail.at.ivanehreshi.mobilenetwork.MobileOperator;
import com.gmail.at.ivanehreshi.mobilenetwork.SMS;
import com.gmail.at.ivanehreshi.mobilenetwork.globalevents.UserLoginEvent;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.PhoneNumber;
import com.gmail.at.ivanehreshi.mobilenetwork.util.EventHandler;
import com.gmail.at.ivanehreshi.mobilenetwork.util.EventListener;

/**
 * sends sms after the user log in to the network
 * @author Ivan
 *
 */
public class SMSManager implements  EventListener{
	private HashMap<PhoneNumber, SMS> smsQueue;
	private MobileOperator operator;
	
	public SMSManager(MobileOperator operator){
		this.operator = operator;
		smsQueue = new HashMap<PhoneNumber, SMS>();
		manageListeners();
	}
	
	private void manageListeners(){
		operator.eventBus.addListener(this);
	}
	
	public void sendLater(SMS sms){
		if(sms == null)
			return;
		smsQueue.put(sms.receiver, sms);
	}
	
	@EventHandler
	public void onUserLoggin(UserLoginEvent event){
		if(event.user == null)
			return;
		SMS sms = smsQueue.get(event.user.getPhoneNumber());
		if(sms == null)
			return;
		
		event.user.receiveSMS(sms);
	}
	
	
	@Override
	public int getPriority() {
		return 10;
	}

	@Override
	public boolean setPriority(int priority) {
		return false;
	}
	
	
	
}
