package com.gmail.at.ivanehreshi.mobilenetwork.user.events;

import com.gmail.at.ivanehreshi.mobilenetwork.util.Event;
import com.gmail.at.ivanehreshi.user.User;

public class CallEndedEvent implements Event{
	
	public final User caller;
	public final User receiver;

	public CallEndedEvent(User caller, User receiver) {
		this.caller = caller;
		this.receiver = receiver;
	}

	@Override
	public boolean isCanceled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCanceled(boolean flag) {
		// TODO Auto-generated method stub
		
	}

}
