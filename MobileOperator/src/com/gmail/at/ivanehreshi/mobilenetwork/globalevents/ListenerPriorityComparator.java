package com.gmail.at.ivanehreshi.mobilenetwork.globalevents;

import java.util.Comparator;

import sun.security.jca.GetInstance.Instance;

import com.gmail.at.ivanehreshi.mobilenetwork.user.events.SMSUserListener;
import com.gmail.at.ivanehreshi.mobilenetwork.util.EventListener;


/**
 * it is a singleton which compares Listeners by priority
 * @author Ivan
 *
 */
public class ListenerPriorityComparator implements Comparator<EventListener> {

	public static ListenerPriorityComparator instance = new ListenerPriorityComparator();
	
	@Override
	public int compare(EventListener o1, EventListener o2) {
		if(o1 == null)
			return -1;
		if(o2 == null)
			return 1;
		
		return o1.getPriority() - o2.getPriority() ;
	}

}
