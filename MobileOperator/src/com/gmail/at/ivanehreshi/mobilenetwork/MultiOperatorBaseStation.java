package com.gmail.at.ivanehreshi.mobilenetwork;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.util.Pair;

import com.gmail.at.ivanehreshi.mobilenetwork.emulator.Receiver;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.BaseStation;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.PhoneNumber;
import com.gmail.at.ivanehreshi.user.User;

public class MultiOperatorBaseStation implements BaseStation {
	private static int countInstances = 0; // for debug
	private final String id; // for debug
	
	private Point2D location;
	
	/**
	 * radius on which this tower available
	 */
	private int signalStrenght = 1000;
	private ArrayList<MobileOperator> operators;
	HashMap<User, Receiver> usersMap = new HashMap<User, Receiver>();
	private int maxUsersCount;
	
	public MultiOperatorBaseStation(int maxUserCount, MobileOperator[] supportedOperators, int signalStrenght){
		this.maxUsersCount = maxUserCount;
		this.signalStrenght = signalStrenght;
		
		operators = new ArrayList<MobileOperator>(Arrays.asList(supportedOperators));
		
		countInstances++;
		id = "id"+countInstances;
	}
	
	public boolean connect(PhoneNumber number, Receiver rec){
		
		User user = number.getOperator().getUser(number);
		
		if(user == null)
			return false;
		
		if(getCurrentUsersCount() >= maxUsersCount)
			return false;
		
		if(!operators.contains(user.getOperator()))
			return false;
		
		usersMap.put(user, rec);
		user.setStation(this);
		
		
		return true;
	}

	@Override
	public boolean unregister(PhoneNumber number){
		
		User user = number.getOperator().getUser(number);
		
		if(user == null)
			return false;
		
		if(getCurrentUsersCount() >= maxUsersCount)
			return false;
		
		if(!operators.contains(user.getOperator()))
			return false;
		
		usersMap.remove(user);
		user.setStation(null);
		
		
		return true;
	}
	
	@Override
	public Point2D getLocation() {
		return location;
	}

	@Override
	public int getMaxUsersCount() {
		return maxUsersCount;
	}


	@Override
	public int getCurrentUsersCount() {
		return usersMap.size();
	}


	@Override
	public Receiver getReceiver(User user) {
		if(user == null)
			return null;
		return usersMap.get(user);
	}

	
	@Override
	public User getConnectedUser(PhoneNumber number){
		User user = number.getOperator().getUser(number);
		if(usersMap.containsKey(user))
			return user;
		return null;
	}
	
	@Override
	public boolean sendMessage(SMS sms) {
		
		User user = getConnectedUser(sms.sender);
		if(user == null)
			return false;
		
		return user.sendSMS(sms);
	}

	
	@Override
	public boolean sendPacketToClient(PhoneNumber number, String packet) {
		Receiver receiver = getReceiver(getConnectedUser(number));
		if(receiver == null)
			return false;
		
		receiver.add(packet);
		System.out.println(number + " just received the following packet: " + packet);
		return true;
	}

	public String toString(){
		return id;
	}

	@Override
	public boolean call(PhoneNumber caller, PhoneNumber number) {
		
		User user = getConnectedUser(caller);
		return user.call(number);
	}
}
