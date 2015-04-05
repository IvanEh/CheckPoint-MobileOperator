package com.gmail.at.ivanehreshi.mobilenetwork.user.events;

public class SMSUserListenerAdapter implements SMSUserListener {

	private int priority; // 1..5;
	
	@Override
	public void onSMSInitEvent(SMSUserInitEvent event) {
		
	}

	@Override
	public int getPriority() {
		return priority;
	}

}
