package com.gmail.at.ivanehreshi.mobilenetwork.globalevents;

import com.gmail.at.ivanehreshi.user.User;

public class UserLogoutEvent extends UserEvent {
	public User user;
	public UserLogoutEvent(User user) {
		this.user = user;
	}

}
