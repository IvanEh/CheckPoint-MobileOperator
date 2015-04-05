package com.gmail.at.ivanehreshi.mobilenetwork.user.events;


public interface SMSUserListener{
	
	void onSMSInitEvent(SMSUserInitEvent event);
	int getPriority();
	
	
	static class Comparator implements java.util.Comparator<SMSUserListener>{
		public int compare(SMSUserListener l1, SMSUserListener l2){
			return l1.getPriority() - l2.getPriority() ;
		}
	}
}



