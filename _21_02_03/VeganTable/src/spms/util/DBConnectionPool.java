package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnectionPool {
	final int PRE_POOL_SIZE = 10; 
	String url;
	String username;
	String password;
	ArrayList<Connection> connList = new ArrayList<Connection>();
	
	public DBConnectionPool(String driver, String url,
					String username, String password) throws Exception{
		this.url = url;
		this.username = username;
		this.password = password;
		
		Class.forName(driver);
		
		// 미리 PRE_POOL_SIZE 만큼 생성해 놓는다
		for(int i=0;i<PRE_POOL_SIZE;i++)
			connList.add(DriverManager.getConnection(url, username, password));
	}
	
	// Connection 객체를 요청하면 대여해줌
	public Connection getConnection() throws Exception{
		// 현재 저장된 여유분이 존재한다면
		if(connList.size() > 0) {
			// 1번째 Connection객체를 꺼낸다
			Connection conn = connList.remove(0);
			/* DBMS는 일정시간 아무 요청이 없는 경우
			 * timeout에 의해 연결이 해제될 수 있다
			 * */
			// Connection객체가 DB와 연결되어 있다면
			if(conn.isValid(10)) {
				// 꺼낸 것을 리턴한다
				return conn;
			}
		}
		
		// 여유분이 없으면 새로 연결해서 리턴한다
		return DriverManager.getConnection(url, username, password);
	}
	
	// 빌려서 사용하고 다 사용했으면 Connection객체를 반납한다
	public void returnConnection(Connection conn) throws Exception{
		if(conn != null && conn.isClosed()==false)
			connList.add(conn);
	}
	
	// 종료시 모든 Connection 객체 닫기
	public void closeAll() {
		for(Connection conn : connList) {
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}









