package com.gmail.at.ivanehreshi.mobilenetwork.util;


public interface EventListener {

	/**
	 * normally there is only one HIGHEST, INIT and AFTER_INIT listener
	 */
	static class Priority{
		public static final int INIT = 1;
		public static final int AFTER_INIT = 2;
		public static final int LOWEST = 3;
		public static final int HIGHEST = 10;
	}
	
	public int getPriority();
	public boolean setPriority(int priority);
	public default void subscribeToObservable(Observable obs){
		obs.addListener(this);
	}
}
