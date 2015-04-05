package com.gmail.at.ivanehreshi.mobilenetwork.emulator;


public class SMSLocalHistory {
	private TextMessage[] messages;
	private int length;
	
	public SMSLocalHistory(int cap){
		messages = new TextMessage[cap];
		length = 0;
	}
	
	public int getCapacity(){
		return messages.length;
	}
	
	public boolean add(TextMessage mess){
		if(length == getCapacity())
			return false;
		
		length++;
		messages[length-1] = mess;
		return true;
	}
	
	public void clear(){
		length = 0;
	}
	
}
