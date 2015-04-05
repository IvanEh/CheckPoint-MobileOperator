package com.gmail.at.ivanehreshi.mobilenetwork.globalevents;

import com.gmail.at.ivanehreshi.mobilenetwork.util.*;
import com.gmail.at.ivanehreshi.user.User;

public class UserLoginEvent extends UserEvent{
	public User user;
	
	public UserLoginEvent(User user){
		this.user = user;
	}
	


}
