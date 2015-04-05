package com.gmail.at.ivanehreshi.mobilenetwork;

import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.IPhoneNumber;

/**
 * represents PhoneNumber of any Ukrainian mobile operator
 * @author Ivan
 */
public class PhoneNumber implements IPhoneNumber{
	private String countryCode = "+380";
	private String operatorCode;
	private String number;
	private Integer cachedHash = null; 
	
	public PhoneNumber(String operatorCode, String number){
		if(!checkOperatorCode(operatorCode))
			throw new IllegalArgumentException("Invalud operator code format");
		
		if(!checkNumber(number))
			throw new IllegalArgumentException("Invalid number format");
	
		this.operatorCode  = operatorCode;
		this.number = number;
		
	}
	
	private boolean checkNumber(String number2) {
		if(number2.length() != 7)
			return false;
		
		int num = -1;
		try {
			num = Integer.valueOf(operatorCode); 
		}catch(NumberFormatException e){
			return false;
		}
		
		return num >= 0;
	}

	private boolean checkOperatorCode(String operatorCode) {
		if(operatorCode.length() != 2)
			return false;
		
		int op = -1;
		try {
			op = Integer.valueOf(operatorCode); 
		}catch(NumberFormatException e){
			return false;
		}
		
		return op >= 0;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(countryCode);
		builder.append(operatorCode);
		builder.append(number);
		
		return builder.toString();
	}

	public int hashCode(){
		if(cachedHash != null)
			return cachedHash;
		
		cachedHash = toString().hashCode();
		return cachedHash;
	}
	
}
