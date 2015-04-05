package com.gmail.at.ivanehreshi.mobilenetwork.user.events;

import com.gmail.at.ivanehreshi.mobilenetwork.util.Event;
import com.gmail.at.ivanehreshi.user.User;

public class UserCallInitEvent implements Event {

	public final User receiver;
	public final User caller;
	private boolean canceled = false;
	public boolean refused;
	public double price;

	public UserCallInitEvent(User user, User receiver) {
		this.caller = user;
		this.receiver = receiver;
	}

	@Override
	public boolean isCanceled() {
		// TODO Auto-generated method stub
		return canceled;
	}

	@Override
	public void setCanceled(boolean flag) {
		canceled = flag;
	}

}
