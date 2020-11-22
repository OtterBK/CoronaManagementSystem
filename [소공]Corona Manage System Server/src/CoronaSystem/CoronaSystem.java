package CoronaSystem;

import CoronaSystem.UserInterface.AdminInfoAddGUI;
import CoronaSystem.UserInterface.AdminInfoDeleteGUI;
import CoronaSystem.UserInterface.CoronaMapAdd;
import CoronaSystem.UserInterface.HomeGUI;
import CoronaSystem.UserInterface.InformationAdd;
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
