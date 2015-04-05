package com.gmail.at.ivanehreshi.mobilenetwork.interfaces;

import com.gmail.at.ivanehreshi.mobilenetwork.MobileOperator;

public interface PhoneNumber {
	public String toString();
	public int hashCode();
	public MobileOperator getOperator();
}
