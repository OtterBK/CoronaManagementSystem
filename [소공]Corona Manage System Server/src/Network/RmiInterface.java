//Front: 전재욱
//Back: 전재욱
//Last Update : 20.11.20
//RMI 통신 인터페이스

package Network;

public interface RmiInterface {
    public String getResponse(String data);
    
    public boolean checkConnection();
}
