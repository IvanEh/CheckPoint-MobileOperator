package com.gmail.at.ivanehreshi.mobilenetwork;

import java.util.ArrayList;
import java.util.HashMap;

import com.gmail.at.ivanehreshi.mobilenetwork.globalevents.EventBus;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.BaseStation;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.MobilePackage;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.PhoneNumber;
import com.gmail.at.ivanehreshi.mobilenetwork.smsmanager.SMSManager;
import com.gmail.at.ivanehreshi.user.User;

/**
 * consolidate data + do prepare the technique for sending information to the cellular phone
 * @author Ivan
 */
public class MobileOperator {

	private HashMap<PhoneNumber, User> users;
	private ArrayList<MobilePackage> packages;
	private ArrayList<BaseStation> baseStations;
	private SMSManager smsManager; 
	public EventBus eventBus = new EventBus();
	
//	History history;
	
	public MobileOperator(){
		users = new HashMap<PhoneNumber, User>();
		packages = new ArrayList<MobilePackage>();
		baseStations = new ArrayList<BaseStation>();
		smsManager = new SMSManager(this);
	}
	
// initialization methods
	public void addStation(BaseStation st){
		if(st != null)
			baseStations.add(st);
	}

	/**
	 * for debug and testing purposes
	 * @param number
	 */
	@Deprecated
	public void simpleAddUser(PhoneNumber number){
		//if(NationalOperatorRegister.getInstance().registered(number)){
//			System.out.println("It seems this number already registered by this or another operator");
		//	return;
	//	}
		
		//NationalOperatorRegister.getInstance().register(number, this);
		
//		NationalOperatorRegister.getInstance().register(number, this);
		User user = new User(number, this, 0);
		users.put(number, user);
	}
	
//	
	public User getUser(
			PhoneNumber number) {
		if(number.getOperator() != this)
			return null;
		return users.get(number);
	}
	
	
	/**
	 * prepare sms that SHOULD be finally sent to the other user via BaseStation + doing the log stuff 
	 * @param sms
	 * @return true if the sms has been sent immediately
	 */
	public boolean sendSMS(SMS sms){
		User receiver = getUser(sms.receiver);
		if( receiver != null){
			if(receiver.isOnline()){
				receiver.receiveSMS(sms);
				return true;
			}else{
				smsManager.sendLater(sms);
				return false;
			}
		}
		return false;
	}
	
}
