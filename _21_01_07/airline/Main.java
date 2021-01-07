package airline;

import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		View vw=new View();
		vw.menuLoop();
	}
}
