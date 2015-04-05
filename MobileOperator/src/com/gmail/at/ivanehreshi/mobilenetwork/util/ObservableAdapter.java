package com.gmail.at.ivanehreshi.mobilenetwork.util;

import java.util.ArrayList;
import java.util.Iterator;

public class ObservableAdapter implements Observable{

	private ArrayList<EventListener> listeners = new ArrayList<>();
	
	@Override
	public boolean addListener(EventListener listener) {
		if(listener == null)
			return false;
		
		listeners.add(listener);
		return true;
	}

	@Override
	public boolean removeListener(EventListener listener) {
		return listeners.remove(listener);
	}

	@Override
	public Iterator<EventListener> iterator() {
		return listeners.iterator();
	}
	
}
