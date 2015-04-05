package com.gmail.at.ivanehreshi.mobilenetwork.user.events;

import com.gmail.at.ivanehreshi.mobilenetwork.SMS;
import com.gmail.at.ivanehreshi.mobilenetwork.util.Event;

public class SMSUserInitEvent implements Event{
	public double price = 0;
	public boolean canceled = false;
	public boolean refused = false;
	public SMS sms;
	
	public SMSUserInitEvent(SMS sms){
		this.sms = sms;
	}
	
	public boolean isCanceled(){
		return canceled;
	}

	@Override
	public void setCanceled(boolean flag) {
		canceled = flag;
	}

}
