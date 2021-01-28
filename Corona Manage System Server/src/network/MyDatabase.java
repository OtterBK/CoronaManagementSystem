//Front: 전재욱
//Back: 전재욱
//Last Update: 20.11.22
//Des: 데이터베이스 통신용

package network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MyDatabase { //데이터베이스 통신용
	
	private String url = "jdbc:mysql://127.0.0.1:3306/coronaDB?serverTimezone=Asia/Seoul"; //데이터 베이스 주소
	private String rootId = "root"; //루트 id
	private String rootPw = "6789"; //루트 pw
	private Connection con; //데이터베이스 통신용
	private Statement stmt; //데이터베이스 명령 실해용ㅇ
	
	public MyDatabase() { //생성 및 초기화
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(java.lang.ClassNotFoundException e) {
			e.printStackTrace();
			return;
		} 
		try {
			con = DriverManager.getConnection(url, rootId, rootPw); //데이터 베이스 로그인
			stmt = con.createStatement();
		} catch (SQLException e) {		
			e.printStackTrace();
		}
	}
	
	public String getPassword(String id) { //ID값을 이용하여 AdminInfo(어드민정보 테이블)에서 PW 받아옴
		String pw = null;
		try {
			ResultSet result = stmt.executeQuery("SELECT PW FROM AdminInfo where ID = '"+id+"'");
			if(!result.next()) return null;
			pw = result.getString("pw");
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return pw;
	}
	
	public String getAdminName(String id) { //ID값을 이용하여 AdminInfo(어드민정보 테이블)에서  어드민명 받아옴
		String adminName = null;
		try {
			ResultSet result = stmt.executeQuery("SELECT AdminName FROM AdminInfo where ID = '"+id+"'");
			if(!result.next()) return null;
			adminName = result.getString("adminName");
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return adminName;
	}
	
	public boolean insertAdminInfo(String id, String pw, String adminName) { //AminInfo(로그인정보 테이블)에 새로운 ID,PW, AdminName삽입 또는 PW값 수정
		try {
			String chekcpw = getPassword(id); //이미 아이디 존재하는지 확인하기 위해
			if(chekcpw == null) { //존재안하면
				stmt.executeUpdate("INSERT INTO AdminInfo VALUES ('"+id+"','"+pw+"','"+adminName+"')");
			} else {
				stmt.executeUpdate("UPDATE AdminInfo SET PW = '"+pw+"' WHERE ID = '"+id+"'");
				stmt.executeUpdate("UPDATE AdminInfo SET AdminName = '"+adminName+"' WHERE ID = '"+id+"'");
			}
			return true;
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}
	
	public boolean deleteAdminInfo(String id) {
		try {
			stmt.executeUpdate("DELETE From AdminInfo WHERE ID = '"+id+"'");
			return true;
		}catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}
	
	public String[] getCoronicInfo(String id) { //coronicInfo(사용자 정보 테이블)에서 환자 ID값을 이용하여 정보를 불러옴
        String args[] = new String[5];
        try {
            ResultSet result = stmt.executeQuery("SELECT * FROM coronicInfo where coronicID = '"+id+"'");//여기는 환자 아이디로
            if(!result.next()) return null;
            args[0] = result.getString("name");
            args[1] = result.getString("age");
            args[2] = result.getString("gender");
            args[3] = result.getString("address");
            args[4] = result.getString("date");
        } catch (SQLException e) {
            args = null;
            e.printStackTrace();
        }
        return args;
    }

	public boolean editCoronicInfo(String id, String name, String age, String gender, String address, String date) { // UserInfo(사용자
		// 정보
		// 테이블에)
		// 새로운
		// 값
		// 삽입
		// 또는
		// 수정
		try {
			stmt.executeUpdate("UPDATE coronicInfo SET name= '" + name + "' WHERE coronicID = '" + id + "'");
			stmt.executeUpdate("UPDATE coronicInfo SET age = '" + age + "' WHERE coronicID = '" + id + "'");
			stmt.executeUpdate("UPDATE coronicInfo SET gender = '" + gender + "' WHERE coronicID = '" + id + "'");
			stmt.executeUpdate("UPDATE coronicInfo SET address = '" + address + "' WHERE coronicID = '" + id + "'");
			stmt.executeUpdate("UPDATE coronicInfo SET date = '" + date + "' WHERE coronicID = '" + id + "'");
			return true;
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}
	
	public boolean insertCoronicInfo(String coronicId, String name, String age, String gender, String address,
			String date) { // MapInfo에 정보 삽입
		try {
			stmt.executeUpdate("INSERT INTO CoronicInfo VALUES ('" + coronicId + "','" + name + "','" + age + "','"
					+ gender + "','" + address + "','" + date + "')");
			return true;
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}
	
	public boolean insertCoronaMapInfo(String date, String time, String latitude, String longitude, String detail,
			String coronicId, String address) { // MapInfo에 정보 삽입
		try {
			stmt.executeUpdate("INSERT INTO CoronaMap VALUES ('" + date + "','" + time + "','" + latitude + "','"
					+ longitude + "','" + detail + "','" + coronicId + "','"+ address +"')");
			return true;
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}
	
	public boolean updateCoronicInfo(String coronicId, String name, String age, String gender, String address, String date) {
		try {
			stmt.executeUpdate("UPDATE CoronicInfo SET name = '"+name+"' WHERE coronicId = '"+coronicId+"'");
			stmt.executeUpdate("UPDATE CoronicInfo SET age = '"+age+"' WHERE coronicId = '"+coronicId+"'");
			stmt.executeUpdate("UPDATE CoronicInfo SET gender = '"+gender+"' WHERE coronicId = '"+coronicId+"'");
			stmt.executeUpdate("UPDATE CoronicInfo SET address = '"+address+"' WHERE coronicId = '"+coronicId+"'");
			stmt.executeUpdate("UPDATE CoronicInfo SET date = '"+date+"' WHERE coronicId = '"+coronicId+"'");
			return true;
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}
	
	public String getMaxId() {
		int i;
		String insertId;
		insertId = "1";
		while(true) {
			if(getCoronicInfo(insertId)==null)
				break;
			i = Integer.parseInt(insertId);
			i++;
			insertId = Integer.toString(i);
		}
		return insertId;
	}

	public ArrayList<String[]> getAllCoronaMapInfo(){
		ArrayList<String[]> coronaMapData = new ArrayList<String[]>();
		
        try {
            ResultSet result = stmt.executeQuery("SELECT * FROM coronaMap");//모든 동선 조회
            while(result.next()) {
                String args[] = new String[7];
                args[0] = result.getString("date");
                args[1] = result.getString("time");
                args[2] = result.getString("place_latitude");
                args[3] = result.getString("place_longitude");
                args[4] = result.getString("detail");
                args[5] = result.getString("coronicid");
                args[6] = result.getString("address");
                coronaMapData.add(args);
            };          
        } catch (SQLException e) {
        	coronaMapData = null;
            e.printStackTrace();
        }
        return coronaMapData;
	}
	
	public ArrayList<String[]> getAllCoronicInfo(){
		ArrayList<String[]> coronicData = new ArrayList<String[]>();
		
        try {
            ResultSet result = stmt.executeQuery("SELECT * FROM coronicInfo");//모든 동선 조회
            while(result.next()) {
                String args[] = new String[5];
                args[0] = result.getString("coronicId");
                //args[1] = result.getString("name"); //이제 이름은 데이터 저장 안함
                args[1] = result.getString("age");
                args[2] = result.getString("gender");
                args[3] = result.getString("address");
                args[4] = result.getString("date");        
                coronicData.add(args);
            };          
        } catch (SQLException e) {
        	coronicData = null;
            e.printStackTrace();
        }
        return coronicData;
	}
	
	public boolean deleteCoronaInfo(String date, String time, String id) {
		try {
			stmt.executeUpdate("DELETE From coronaMap WHERE coronicId = '"+id+"' and date = '"+date+"' and time = '"+time+"'");
			return true;
		}catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}
	
	public LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> getAreaInfo() { //전체 지역목록 얻기, <(도) , <(시),(상세)>> 
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> areaList = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>(); //hashmap은 도시, list는 이 도시의 동네들
		try {
			ResultSet result = stmt.executeQuery("SELECT distinct city FROM areainfo"); //도시목록 얻기
			if(!result.next()) return null; 
			do { //각각의 도시 이름을 areaList에 키로서 넣음
				String cityName = result.getString("city");
				areaList.put(cityName, new LinkedHashMap<String, ArrayList<String>>()); //아직 시, 상세 맵은 비어있음
			}while(result.next());
			
			for(String cityName : areaList.keySet()) { //각 도시이름마다 동네를 조회할 거임
				LinkedHashMap<String, ArrayList<String>> townList = areaList.get(cityName); //도 이름에 따른 맵얻기
				ResultSet rs = stmt.executeQuery("SELECT distinct town FROM areainfo where city = '"+cityName+"'"); //시 목록 얻기 
				while(rs.next()) {
					String townName = rs.getString("town");
					townList.put(townName, new ArrayList<String>()); //해당 도의 시,상세 맵에 넣기, 상세 리스트는 비어있음
					
					ArrayList<String> sareaList = townList.get(townName); //시 이름에 따른 리스트얻기
					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT sarea FROM areainfo where city = '"+cityName+"'" 
							+ " and " + "town = '" + townName + "'"); //도, 시 이름에 따른 상세 목록 얻기
					while(rs2.next()) {
						String sarea = rs2.getString("sarea").trim();
						sareaList.add(sarea);
					}
					
				}
			}
			
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		
		return areaList.size() == 0 ? null : areaList; //areaList가 비어있으면 null 반환
	}
	
	
}
