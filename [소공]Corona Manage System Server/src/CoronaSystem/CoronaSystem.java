package CoronaSystem;

import CoronaSystem.UserInterface.HomeGUI;
import Network.RmiServer;

public class CoronaSystem {

	public static void main(String[] args) {
		HomeGUI home = new HomeGUI();
		RmiServer rmiServer = new RmiServer(); //서버 열기
	}
	
}
