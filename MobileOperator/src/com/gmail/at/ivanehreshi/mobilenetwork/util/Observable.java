package com.gmail.at.ivanehreshi.mobilenetwork.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public interface Observable {
	public boolean addListener(EventListener listener);
	public boolean removeListener(EventListener listener);
	public Iterator<EventListener> iterator();
	public default void notifyListeners(Event event, Comparator comp){
		
		Iterator<EventListener> it = iterator();
		ArrayList<EventListener> list = new ArrayList<EventListener>();
		while(it.hasNext()){
			EventListener ob = it.next();
			list.add(ob);
		}
		
		Collections.sort(list, comp);
		
		for(EventListener ob: list){
			for(Method method: ob.getClass().getMethods()){
				boolean isAnnotated = method.isAnnotationPresent(EventHandler.class);
				if(!isAnnotated)
					continue;
				
				int parametersCount = method.getParameterCount();
				if(parametersCount != 1)
					continue;
				
				Class classParam = method.getParameterTypes()[0];
				Class needParam = event.getClass();
				boolean paramIsEvent = classParam.isAssignableFrom(needParam);
				if(!paramIsEvent)
					continue;
				
				try {
					method.invoke(ob, event);
					if(event.isCanceled()){
						return;
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
}
