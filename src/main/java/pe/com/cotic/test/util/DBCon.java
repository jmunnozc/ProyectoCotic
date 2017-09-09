package pe.com.cotic.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {

	public Connection getConnection() {
		Connection con = null;

		try {
			String dbDriver = "com.mysql.jdbc.Driver";
			String dbURL = "jdbc:mysql:localhost:3306/cotic";

			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbURL,"root","admin");
			System.out.println("DB Connecting");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Database.getConnection() Error -->"
					+ e.getMessage());
		}
		return con;
	}

	public void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}
}
