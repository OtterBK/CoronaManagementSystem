package Network;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import Addon.MyUtility;
import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.IServerListener;
import net.sf.lipermi.net.Server;

public class RmiServer implements RmiInterface {
	
	private ArrayList<Socket> clientsList = new ArrayList<Socket>();
	
    public RmiServer() {
        try {
            CallHandler callHandler = new CallHandler();
            callHandler.registerGlobal(RmiInterface.class, this);
            Server server = new Server();
            server.bind(7777, callHandler);
            server.addServerListener(new IServerListener() {
                @Override
                public void clientDisconnected(Socket socket) {
                	clientsList.add(socket);
                	MyUtility.printLog("Client Disconnected: " + socket.getInetAddress());
                }
                @Override
                public void clientConnected(Socket socket) {
                	clientsList.remove(socket);
                    MyUtility.printLog("Client Connected: " + socket.getInetAddress());
                }
            });
            MyUtility.printLog("Server Listening");
        } catch (LipeRMIException | IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public String getResponse(String data) {
        MyUtility.printLog("getResponse called");
        return "Your data: " + data;
    }
	@Override
	public boolean checkConnection() {
		MyUtility.printLog("");
		return true;
	}
}
