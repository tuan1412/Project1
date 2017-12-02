package app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	private static Connection conn;
	
	public static Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:..\\Project1\\project1.db");
			return conn;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
