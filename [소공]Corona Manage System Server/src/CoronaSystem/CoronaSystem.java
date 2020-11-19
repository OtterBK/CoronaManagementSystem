package CoronaSystem;

import CoronaSystem.UserInterface.HomeGUI;
import CoronaSystem.UserInterface.LoginGUI;
import Network.RmiServer;

public class CoronaSystem {

	public static void main(String[] args) {
		//LoginGUI home = new LoginGUI();
		
		new HomeGUI();
		
		RmiServer rmiServer = new RmiServer(); //서버 열기
	}
	
}
