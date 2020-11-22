//Front: 전재욱
//Back: 전재욱
//Last Update : 20.11.23
//Des: 소프트웨어 공학 코로나 동선 파악 및 조회 시스템의 서버 부분

package CoronaSystem;

import CoronaSystem.UserInterface.LoginGUI;
import Network.MyDatabase;
import Network.RmiServer;

public class CoronaSystem {

	public static MyDatabase database = new MyDatabase();
	
	public static void main(String[] args) {
		LoginGUI home = new LoginGUI();
		
		//new HomeGUI();
		
		//new AdminInfoAddGUI();
		
		//new AdminInfoDeleteGUI();
		
		//new InformationAdd();
		
		//new CoronaMapAdd();
		
		RmiServer rmiServer = new RmiServer(); //서버 열기
	}
	
}
