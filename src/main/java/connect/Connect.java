package connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public Connect() {
		// TODO Auto-generated constructor stub
	}
	public Connection getConnect() {
		Connection con=null;
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=QlyLuong";
			con=DriverManager.getConnection(url,"sa","123456");
		} catch (Exception e) {
			System.out.println("khong the ket noi CSDL");
		}
		return con;
	}
		
}
