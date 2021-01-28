

package network;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface RmiInterface {
    
    public boolean checkConnection();
    
    public ArrayList<String[]> requestCoronaMapData();
    
    public ArrayList<String[]> requestCoronicData();
    
    public ArrayList<String[]> requestCoronicDataFromDate(String date);
    
    public ArrayList<String[]> requestCoronicDataFromAddress(String address);
    
    public ArrayList<String[]> requestCoronaMapDataFromId(String coronicId);
    
    public LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> requestAreaData();
}
