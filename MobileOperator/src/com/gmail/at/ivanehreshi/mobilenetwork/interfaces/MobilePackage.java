package com.gmail.at.ivanehreshi.mobilenetwork.interfaces;

import com.gmail.at.ivanehreshi.mobilenetwork.SMS;
import com.gmail.at.ivanehreshi.mobilenetwork.util.EventListener;
import com.gmail.at.ivanehreshi.user.User;

public interface MobilePackage extends EventListener {

	double getSMSPrice(SMS sms);
	
	@Override
	public default int getPriority(){
		return 2;
	}

	double getCallPrice(User caller, User receiver, long i);

	
}
