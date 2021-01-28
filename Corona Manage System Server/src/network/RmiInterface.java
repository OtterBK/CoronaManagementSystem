//Front: 전재욱
//Back: 전재욱
//Last Update : 20.11.20
//RMI 통신 인터페이스

package network;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface RmiInterface {
    
    public boolean checkConnection();
    
    public ArrayList<String[]> requestCoronaMapData(); //모든 코로나 동선 데이터
    //0. date, 1.time, 2.latitude, 3.logitude, 4.detail, 5coronicId
    
    public ArrayList<String[]> requestCoronicData(); //모든 확진자 데이터
    
    public ArrayList<String[]> requestCoronicDataFromDate(String date); //date는 [2020-01-11] 같은 문자열 형식
    
    public ArrayList<String[]> requestCoronicDataFromAddress(String address); //address는 [경기도 안양시동안구 안양1동] 같은 문자열 형식
    
    public ArrayList<String[]> requestCoronaMapDataFromId(String coronicId); //coronidID 는 숫자 문자열값
    
    public LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> requestAreaData();
}
