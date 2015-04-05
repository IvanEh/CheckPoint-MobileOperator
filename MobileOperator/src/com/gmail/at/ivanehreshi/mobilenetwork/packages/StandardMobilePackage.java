package com.gmail.at.ivanehreshi.mobilenetwork.packages;

import com.gmail.at.ivanehreshi.mobilenetwork.SMS;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.MobilePackage;
import com.gmail.at.ivanehreshi.mobilenetwork.util.EventListener;
import com.gmail.at.ivanehreshi.user.User;

/**
 * This instance work as a simple mobile plan. But by extending this class could be added bonus features 
 * @author Ivan
 *
 */
public class StandardMobilePackage implements MobilePackage, EventListener {

	private double smsSingleOperator = 0.3d;
	private double smsBetweenOperators = 0.5d;
	private double connectionPrice = 0.1d;
	private double callSingleOperatorPerM = 1d;
	private double callBetweenOperatorsPerM = 2d;
	
	@Override
	public double getSMSPrice(SMS sms) {
		if(sms.receiver.getOperator() == sms.sender.getOperator())
			return smsSingleOperator;
		
		return smsBetweenOperators;
	}

	@Override
	public int getPriority() {
		return Priority.AFTER_INIT;
	}

	@Override
	public boolean setPriority(int priority) {
		return false;
	}

	
	@Override
	public double getCallPrice(User caller, User receiver, long duration) {
			if(caller.getOperator() == receiver.getOperator()){
				return callSingleOperatorPerM;
			}
			return callBetweenOperatorsPerM;
		
	}
}
