package com.gmail.at.ivanehreshi.mobilenetwork.interfaces;

import java.awt.geom.Point2D;

import com.gmail.at.ivanehreshi.mobilenetwork.SMS;
import com.gmail.at.ivanehreshi.mobilenetwork.emulator.Receiver;
import com.gmail.at.ivanehreshi.user.User;

/**
 * this is actually works as a mediator
 * @author Ivan
 *
 */
public interface BaseStation {
	
	Point2D getLocation();
	int getMaxUsersCount();
	int getCurrentUsersCount();
	Receiver getReceiver(User user);
	boolean connect(PhoneNumber number, Receiver receiver);
	boolean sendMessage(SMS sms);
	boolean sendPacketToClient(PhoneNumber number, String packet);
	User getConnectedUser(PhoneNumber number);
	public boolean unregister(PhoneNumber number);
	public boolean call(PhoneNumber caller, PhoneNumber number);
}
