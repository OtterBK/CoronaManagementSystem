//Front: 전재욱
//Back: 전재욱
//Last Update: 20.11.22
//Des: 데이터베이스 통신용

package Network;

import java.sql.*;

public class MyDatabase { //데이터베이스 통신용
	
	String url = "jdbc:mysql://127.0.0.1:3306/coronaDB?serverTimezone=Asia/Seoul"; //데이터 베이스 주소
	String rootId = "root"; //루트 id
	String rootPw = "6789"; //루트 pw
	Connection con; //데이터베이스 통신용
	Statement stmt; //데이터베이스 명령 실해용ㅇ
	
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
	
	public String[] getUserInfo(String id) { //UserInfo(사용자 정보 테이블)에서 ID값을 이용하여 정보(이름, 연락처, 닉네임) 불러옴
		String args[] = new String[3];
		try {
			ResultSet result = stmt.executeQuery("SELECT * FROM UserInfo where ID = '"+id+"'");
			if(!result.next()) return null;
			args[0] = result.getString("이름");
			args[1] = result.getString("연락처");
			args[2] = result.getString("닉네임");
		} catch (SQLException e) {		
			args = null;
			e.printStackTrace();
		}
		return args;
	}
	
	public boolean insertUserInfo(String id, String name, String telNum, String nickName) { //UserInfo(사용자 정보 테이블에) 새로운 값 삽입 또는 수정
		try {
			String userInfo[] = getUserInfo(id);
			if(userInfo == null) {
				stmt.executeUpdate("INSERT INTO USERINFO VALUES ('"+id+"','"+name+"','"+telNum+"','"+nickName+"')");
			} else {
				stmt.executeUpdate("UPDATE USERINFO SET 이름 = '"+name+"', 연락처 = '"+telNum+"', 닉네임 = '"+nickName+"' WHERE ID = '"+id+"'");
			}
			return true;
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}
	
	
}
