package com.gmail.at.ivanehreshi.mobilenetwork.globalevents;

import com.gmail.at.ivanehreshi.mobilenetwork.util.Event;

public class UserEvent implements Event {
	@Override
	public boolean isCanceled() {
		return false;
	}

	@Override
	public void setCanceled(boolean flag) {
		
	}
}
