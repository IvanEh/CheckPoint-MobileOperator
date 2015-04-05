package com.gmail.at.ivanehreshi.mobilenetwork.emulator;

import java.util.ArrayList;

public class ReceiverImpl implements Receiver {
	ArrayList<String> packets = new ArrayList<String>();
	
	public void add(String packet){
		packets.add(packet);
		
	}
}
