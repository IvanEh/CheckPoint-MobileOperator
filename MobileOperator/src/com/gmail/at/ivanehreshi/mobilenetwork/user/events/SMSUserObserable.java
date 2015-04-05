package com.gmail.at.ivanehreshi.mobilenetwork.user.events;

public interface SMSUserObserable {
	public boolean addListener(SMSUserListener listener);
	public boolean removeListener(SMSUserListener listener);
	public void notifyListeners(SMSUserInitEvent event);
}
