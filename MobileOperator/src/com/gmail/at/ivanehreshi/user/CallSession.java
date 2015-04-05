package com.gmail.at.ivanehreshi.user;

import java.util.Random;

import com.gmail.at.ivanehreshi.mobilenetwork.globalevents.ListenerPriorityComparator;
import com.gmail.at.ivanehreshi.mobilenetwork.user.events.CallEndedEvent;

public class CallSession implements Runnable {

	private User caller;
	private User receiver;
	private boolean ended = false;
	
	public CallSession(User a, User b){
		caller =a;
		receiver = b;
	}
	
	@Override
	public void run() {
		
		long duration = 0;
		String message;
		int chance = 10;
		
		System.out.printf("Conversation started between %s(%s) and %s(%s) \n",
				caller.getPhoneNumber().toString(),
				caller.getOperator().toString(),
				receiver.getPhoneNumber().toString(),
				receiver.getOperator().toString());
		
		while(!caller.getBalance().isBlocked() && chance > 2){
			duration += 1000;
			chance = (new Random()).nextInt(10);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double price = caller.getPackage().getCallPrice(caller, receiver, duration);
			caller.getBalance().withdraw(price);
		};
		if(chance == 1){
			message = caller.getPhoneNumber() + " hang the phone ";
		}else{
			if(chance == 2){
				message = receiver.getPhoneNumber() + " hang the phone ";
			}else{
				message = " WASTED :D " + (duration + (new Random()).nextInt(6)*100 )/1000d + " s";
			}
		}
		
		caller.callSession = null;
		receiver.callSession = null;
		
		System.out.printf("Conversation between %s and %s ended.\n\t Reason:" + message + "\n",
				caller.getPhoneNumber().toString(), receiver.getPhoneNumber().toString());
		
		caller.notifyListeners(new CallEndedEvent(caller, receiver), ListenerPriorityComparator.instance);
	}

}
