package com.gmail.at.ivanehreshi.user;

import java.util.ArrayList;
import java.util.Iterator;

import com.gmail.at.ivanehreshi.mobilenetwork.MobileOperator;
import com.gmail.at.ivanehreshi.mobilenetwork.NationalOperatorRegister;
import com.gmail.at.ivanehreshi.mobilenetwork.SMS;
import com.gmail.at.ivanehreshi.mobilenetwork.globalevents.ListenerPriorityComparator;
import com.gmail.at.ivanehreshi.mobilenetwork.globalevents.UserLoginEvent;
import com.gmail.at.ivanehreshi.mobilenetwork.globalevents.UserLogoutEvent;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.Balance;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.BaseStation;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.MobilePackage;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.PhoneNumber;
import com.gmail.at.ivanehreshi.mobilenetwork.packages.StandardMobilePackage;
import com.gmail.at.ivanehreshi.mobilenetwork.user.events.SMSUserInitEvent;
import com.gmail.at.ivanehreshi.mobilenetwork.user.events.SMSUserListener;
import com.gmail.at.ivanehreshi.mobilenetwork.user.events.SMSUserObserable;
import com.gmail.at.ivanehreshi.mobilenetwork.user.events.UserCallInitEvent;
import com.gmail.at.ivanehreshi.mobilenetwork.util.EventHandler;
import com.gmail.at.ivanehreshi.mobilenetwork.util.EventListener;
import com.gmail.at.ivanehreshi.mobilenetwork.util.Observable;
import com.gmail.at.ivanehreshi.user.listeners.SMSDefaultPayListener;
import com.gmail.at.ivanehreshi.user.listeners.SMSDefaultPriceListener;

public class User implements SMSUserObserable, Observable, EventListener{
	private PhoneNumber phoneNumber;
	private Balance balance;
	private MobilePackage paidPackage;
	private MobilePackage defaultPackage = new StandardMobilePackage();
	private UserPackageStatus packageStatus = UserPackageStatus.DEFAULT;
	private BaseStation station;
	private MobileOperator operator;
	public CallSession callSession= null;
	
	// EVENTS
	private ArrayList<SMSUserListener> smsEventListeners;
	
	
	public User(PhoneNumber number,  MobileOperator op, double initialBalance){
		this.phoneNumber = number; 
		balance = new BalanceImpl(initialBalance);
		smsEventListeners = new ArrayList<>();
		station = null;
		this.operator = op;
		
		manageListeners();
	}
	
	private void manageListeners() {
		addListener(SMSDefaultPriceListener.instance);
		addListener(SMSDefaultPayListener.instance);
		addListener(this);
	}

	/**
	 * if no enough money to pay for mainPackage then this package
	 * will be used
	 */

	public MobileOperator getOperator() {
		return phoneNumber.getOperator();
	}

	
	public Balance getBalance() {
		return balance;
	}


	public void setBalance(Balance balance) {
		this.balance = balance;
	}


	public BaseStation getStation() {
		return station;
	}

	
	public MobilePackage getPackage(){
		if(packageStatus == UserPackageStatus.DEFAULT)
			return defaultPackage;
		
		return paidPackage;
	}
	
	public void setStation(BaseStation station) {
		System.out.println(phoneNumber.toString() + " changed station to " + (station==null?"null":station) );
		if(getStation() == null && station != null){
			this.station = station;
			getOperator().eventBus.notifyListeners(new UserLoginEvent(this), 
					ListenerPriorityComparator.instance );
			return;
		}
		if(getStation() != null && station == null){
			this.station = null;
			
			getOperator().eventBus.notifyListeners(new UserLogoutEvent(this), 
					ListenerPriorityComparator.instance );
			return;
		}
		this.station = station;
	}

	public boolean sendSMS(SMS sms) {
		SMSUserInitEvent event = new SMSUserInitEvent(sms);
		notifyListeners(event);
		if(event.refused)
			return false;
		
		getBalance().withdraw(event.price);
		getOperator().sendSMS(sms);
		
		return true;
	}

	@Override
	public boolean addListener(SMSUserListener listener) {
		// TODO Auto-generated method stub
		return smsEventListeners.add(listener);
	}


	@Override
	public boolean removeListener(SMSUserListener listener) {
		return smsEventListeners.remove(listener);
	}


	@Override
	public void notifyListeners(SMSUserInitEvent event) {
		java.util.Collections.sort(smsEventListeners, new SMSUserListener.Comparator());
		for(SMSUserListener list: smsEventListeners){
			list.onSMSInitEvent(event);
			if(event.canceled)
				break;
		}
	}

	public void receiveSMS(SMS sms) {
		getStation().sendPacketToClient(sms.receiver, "{type: message; from: "+ sms.sender.toString()
				+ "; message: " + sms.message + "}");
		
	}

	/**
	 * check whenever the user currently logged in to the network
	 * @return
	 */
	public boolean isOnline() {
		if(station != null)
			return true;
		return false;
	}

	public Object getPhoneNumber() {
		return phoneNumber;
	}

	public boolean call(PhoneNumber number) {
		if(station == null)
			return false;
		
		if(callSession != null){
			System.out.println("User tried to call another user while he already connected with one");
			return false;
		}
		
		User receiver = NationalOperatorRegister.getInstance().queryForOperator(number).getUser(number);
		
		if(receiver.isBusy()){
			System.out.println("User busy");
			return false;
		}
		
		UserCallInitEvent event = new UserCallInitEvent(this, receiver);
		notifyListeners(event, ListenerPriorityComparator.instance);
		
		if(getBalance().getBalance() > event.price){
			
			CallSession callSession = new CallSession(this, receiver);
			this.callSession = callSession;
			receiver.callSession = callSession;
			
			Thread thread = new Thread(callSession);
			thread.start();
			
			return true;
		}
		
		return false;
	}

	// EVENTS
	
	public boolean isBusy() {
		// TODO Auto-generated method stub
		return callSession != null;
	}

	private ArrayList<EventListener> listeners = new ArrayList<>();
	
	@Override
	public boolean addListener(EventListener listener) {
		if(listener == null)
			return false;
		
		listeners.add(listener);
		return true;
	}

	@Override
	public boolean removeListener(EventListener listener) {
		return listeners.remove(listener);
	}

	@Override
	public Iterator<EventListener> iterator() {
		return listeners.iterator();
	}
	
	// EVENT HANDLERS
	
	@EventHandler
	public void onCallInit(UserCallInitEvent event){
		event.price = getPackage().getCallPrice(event.caller, event.receiver, 0);
	}

	private int priority = Priority.INIT;
	private String name = "";
	
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}

	@Override
	public boolean setPriority(int priority) {
		// TODO Auto-generated method stub
		this.priority = priority;
		return true;
	}
	
	public String toString(){
		return name;
	}
	
}

enum UserPackageStatus {
	DEFAULT,
	PAID;
}