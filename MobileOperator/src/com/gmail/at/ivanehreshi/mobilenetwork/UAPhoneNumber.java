package com.gmail.at.ivanehreshi.mobilenetwork;

import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.PhoneNumber;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

/**
 * represents PhoneNumber of any Ukrainian mobile operator
 * @author Ivan
 */
public class UAPhoneNumber implements PhoneNumber{
	private String countryCode = "+380";
	private String operatorCode;
	private String number;
	private Integer cachedHash = null;
	private MobileOperator operator; 
	
	public UAPhoneNumber(String operatorCode, String number, MobileOperator operator){
		if(!checkOperatorCode(operatorCode))
			throw new IllegalArgumentException("Invalud operator code format");
		
		if(!checkNumber(number))
			throw new IllegalArgumentException("Invalid number format");
	
		this.operatorCode  = operatorCode;
		this.number = number;
		
		this.operator = operator;
		
	}
	
	private boolean checkNumber(String number2) {
		if(number2.length() != 7)
			return false;
		
		int num = -1;
		try {
			num = Integer.valueOf(number2); 
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

	@Override
	public MobileOperator getOperator() {
		return operator;
	}
	
}
