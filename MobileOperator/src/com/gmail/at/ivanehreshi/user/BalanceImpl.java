package com.gmail.at.ivanehreshi.user;

import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.Balance;

public class BalanceImpl implements Balance {

	private double money = 0;
	
	 public BalanceImpl(double initialBalance) {
		this.money = initialBalance;
	}
	 
	 public BalanceImpl() {
		 this(0);
	 }
	
	
	
	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return money;
	}

	@Override
	public double setBalance(double money) {
		// TODO Auto-generated method stub
		this.money = money;
		return money;
	}

	@Override
	public double addMoney(double money) {
		if(money <= 0)
			return this.money;
		this.money += money;
		return this.money;
	}

	@Override
	public double withdraw(double money) {
		if(money <= 0)
			return this.money;
		this.money -= money;
		return this.money;
	}

	@Override
	public boolean isBlocked() {
		return money <= 0;
	}

}
