//Front: 전재욱
//Back: 전재욱
//Last Update : 20.11.23
//Des: 소프트웨어 공학 코로나 동선 파악 및 조회 시스템의 서버 부분

package coronamanagesystem;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import addon.AreaData;
import coronamanagesystem.userinterface.LoginGUI;
import network.MyDatabase;
import network.RmiServer;

public class CoronaSystem {

	public static MyDatabase database = new MyDatabase(); //데이터베이스 통신용 객체 생성
	
	public static void main(String[] args) {
		LoginGUI home = new LoginGUI();
		
		//new HomeGUI();
		
		//new AdminInfoAddGUI();
		
		//new AdminInfoDeleteGUI();
		
		//new InformationAdd();
		
		//new CoronaMapAdd();
		
		AreaData.areaData = database.getAreaInfo();
		
		RmiServer rmiServer = new RmiServer(); //서버 열기
		
	}
	
}
