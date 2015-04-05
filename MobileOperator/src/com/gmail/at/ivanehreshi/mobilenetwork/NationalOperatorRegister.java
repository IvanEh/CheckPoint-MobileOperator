package com.gmail.at.ivanehreshi.mobilenetwork;

import java.util.ArrayList;
import java.util.HashMap;

import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.PhoneNumber;

public class NationalOperatorRegister {
	private static NationalOperatorRegister instance = new NationalOperatorRegister();
	
	private ArrayList<MobileOperator> operators;
	private HashMap<PhoneNumber, MobileOperator> phoneNumberRegister;
	
	private NationalOperatorRegister(){
		operators = new ArrayList<MobileOperator>();
		phoneNumberRegister = new HashMap<PhoneNumber, MobileOperator>();
	}
	
	
	
	public static NationalOperatorRegister getInstance() {
		return instance;
	}



	public boolean registered(MobileOperator mobileOperator) {
		if(operators.contains(mobileOperator))
			return true;
		
		return false;
	}
	
	public boolean registerOperator(MobileOperator op){
		if(op == null)
			return false;
		
		operators.add(op);
		return true;
	}



	public boolean registered(PhoneNumber number) {
		if(phoneNumberRegister.containsKey(number))
			return true;
		
		return false;
	}



	public void register(PhoneNumber number, MobileOperator mobileOperator) {
		if(registered(mobileOperator))
			phoneNumberRegister.put(number, mobileOperator);
	}



	public MobileOperator queryForOperator(PhoneNumber number) {
		return phoneNumberRegister.get(number);
	}



	public void register(MobileOperator kyivStart) {
		operators.add(kyivStart);
	}
}
