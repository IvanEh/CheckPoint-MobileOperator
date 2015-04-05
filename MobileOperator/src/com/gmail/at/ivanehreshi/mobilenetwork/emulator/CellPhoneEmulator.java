package com.gmail.at.ivanehreshi.mobilenetwork.emulator;

import com.gmail.at.ivanehreshi.mobilenetwork.SMS;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.BaseStation;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.PhoneNumber;

public class CellPhoneEmulator {
	private PhoneNumber phoneNumber;
	SMSLocalHistory smsLocalHistory;
	Receiver receiver;
	BaseStation station;
	
	public CellPhoneEmulator(PhoneNumber phoneNumber){
		this.phoneNumber = phoneNumber;
		receiver = new ReceiverImpl();
	}
	
	public boolean move(BaseStation[] nearStations){
		for(int i = 0; i < nearStations.length; i++){
			if(nearStations[i]== null)
				continue;
			
			if(nearStations[i].connect(phoneNumber, receiver)){
				this.station = nearStations[i];
				return true;
			}
		}
		
		return false;
		
	}

	public boolean move(BaseStation nearStation){
		return move(new BaseStation[]{nearStation});
	}
	
	public void shutdown(){
		if(station != null);
			station.unregister(phoneNumber);
		station = null;
	}
	
	public boolean call(PhoneNumber number){
		if(station == null){
			System.out.println(phoneNumber + " cant call while not registered in any network");
			return false;
		}
		
		return station.call(phoneNumber, number);
	}
	
	public boolean sendMessage(String message, PhoneNumber number){
		if(station == null)
			return false;
		
		return station.sendMessage(new SMS(this.phoneNumber, number, message));
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	
}
