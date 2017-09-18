package pe.com.cotic.test.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class Dao {

	private Connection cn;

	public Connection getCn() {
		return cn;
	}

	public void setCn(Connection cn) {
		this.cn = cn;
	}
	
	public void Conectar() throws Exception {
		try {
			Class.forName("com.mysql.jdc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql//localhost:3306/cotic?user=root&password=admin");
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void Cerrar() {
		if (cn != null) {
			try {
				if (cn.isClosed() == false) {
					cn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
