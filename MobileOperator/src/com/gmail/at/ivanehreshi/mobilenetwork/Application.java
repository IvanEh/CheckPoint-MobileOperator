package com.gmail.at.ivanehreshi.mobilenetwork;

import java.util.ArrayList;
import java.util.Random;

import com.gmail.at.ivanehreshi.mobilenetwork.emulator.CellPhoneEmulator;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.BaseStation;
import com.gmail.at.ivanehreshi.mobilenetwork.interfaces.PhoneNumber;

public class Application implements Runnable{
	
	public static Random random = new Random(1);
	private MobileOperator KyivStart;
	private MobileOperator STM;
	private ArrayList<MultiOperatorBaseStation> stations = new ArrayList<>();
	private ArrayList<CellPhoneEmulator> emul = new ArrayList<>();

	public Application(){
		initRND();
	}
	
	private void initRND(){
		KyivStart = new MobileOperator();
		STM = new MobileOperator();
		
		NationalOperatorRegister.getInstance().register(KyivStart);
		NationalOperatorRegister.getInstance().register(STM);

		int numb = 111_11_11;
		
		for(int i = 0; i < 10; i++){
			stations.add(
					new MultiOperatorBaseStation(2 + random.nextInt(5), new MobileOperator[]{KyivStart, STM},  1000));
		}
		
		for(int i = 0; i < 30; i++){
			int oper = random.nextInt(3);
			MobileOperator op;
			if(oper == 1){
				op = KyivStart;
			}else{
				op = STM;
			}
			
			PhoneNumber number = new UAPhoneNumber("96", String.valueOf(numb), op);
			NationalOperatorRegister.getInstance().register(number, op);
			CellPhoneEmulator temp = new CellPhoneEmulator(number);
			emul.add(temp);
			op.simpleAddUser(temp.getPhoneNumber());
			numb++;
		}
		
	}
	
	
	public static void main(String[] args) {
		Application app = new Application();
		app.run();
	
	}
	
	CellPhoneEmulator pickRandom(){
		int i = random.nextInt(emul.size());
		return emul.get(i);
	}

	BaseStation pickRandomStation(){
		int i = random.nextInt(stations.size());
		return stations.get(i);
	}
	
	MobileOperator pickRandomOper(){
		int oper = random.nextInt(3);
		MobileOperator op;
		if(oper == 1){
			op = KyivStart;
		}else{
			op = STM;
		}
		
		return op;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(220);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int choice = random.nextInt(5);
			CellPhoneEmulator emul1, emul2;
			switch (choice) {
			case 1:
				emul1 = pickRandom();
				BaseStation st = pickRandomStation();
				boolean success = emul1.move(st);
				if(!success){
					System.out.println(emul1.getPhoneNumber() + " wasnt able to connect to the station " +st + " (" + st.getCurrentUsersCount()
							+ "/" + st.getMaxUsersCount() +")");
				}
				break;
			case 2:
				emul1 = pickRandom();
				double money = 1 + random.nextInt(5);
				emul1.getPhoneNumber().getOperator().getUser(emul1.getPhoneNumber()).getBalance().addMoney(money);
				break;
			case 3:
				emul1 = pickRandom();
				emul2 = pickRandom();
				emul1.call(emul2.getPhoneNumber());
				break;
			case 4:
				emul1 = pickRandom();
				emul2 = pickRandom();
				emul1.sendMessage(String.valueOf(random.nextInt(20)), emul2.getPhoneNumber());
			default:
				break;
			}
		}
	}
}
