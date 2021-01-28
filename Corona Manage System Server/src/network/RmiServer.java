//Front: 전재욱
//Back: 전재욱
//Last Update : 20.11.20
//Des: 네트워크 통신 메서드

package network;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import addon.AreaData;
import addon.MyUtility;
import coronamanagesystem.CoronaSystem;
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
            server.bind(3099, callHandler);
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
	public boolean checkConnection() {
		return true;
	}


	@Override
	public ArrayList<String[]> requestCoronaMapData() {
		ArrayList<String[]> coronaMapdata = CoronaSystem.database.getAllCoronaMapInfo();
		return coronaMapdata;
	}
	
	@Override
	public ArrayList<String[]> requestCoronaMapDataFromId(String coronicId){
		ArrayList<String[]> coronaMapdata = CoronaSystem.database.getAllCoronaMapInfo();
		ArrayList<String[]> resultData = new ArrayList<String[]>();
		if(coronaMapdata != null) {
			for(String[] data : coronaMapdata) {
				String id = data[5];
				if(id.equals(coronicId)) {
					resultData.add(data);
				}
			}
		}
		return resultData;
	}
	
	@Override
	public ArrayList<String[]> requestCoronicData(){
		ArrayList<String[]> coronicData = CoronaSystem.database.getAllCoronicInfo();
		return coronicData;
	}
	
	@Override
	public ArrayList<String[]> requestCoronicDataFromDate(String date){
		ArrayList<String[]> coronicData = CoronaSystem.database.getAllCoronicInfo();
		ArrayList<String[]> resultData = new ArrayList<String[]>();
		if(coronicData != null) {
			for(String[] data : coronicData) {
				String checkDate = data[4];
				if(checkDate.equals(date)) {
					resultData.add(data);
				}
			}
		}
		return resultData;
	}
	
	@Override
	public ArrayList<String[]> requestCoronicDataFromAddress(String address){
		ArrayList<String[]> coronicData = CoronaSystem.database.getAllCoronicInfo();
		ArrayList<String[]> resultData = new ArrayList<String[]>();
		if(coronicData != null) {
			for(String[] data : coronicData) {
				String coronicAddress = data[3];
				if(coronicAddress.contains(address)) {
					resultData.add(data);
				}
			}
		}
		return resultData;
	}


	@Override
	public LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> requestAreaData() {
		return AreaData.areaData;
	}
}
