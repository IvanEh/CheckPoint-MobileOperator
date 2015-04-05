package com.gmail.at.ivanehreshi.mobilenetwork.interfaces;

public interface Balance {
	double getBalance();
	double setBalance(double money);
	double addMoney(double money);
	double withdraw(double money);
	boolean isBlocked();
}
