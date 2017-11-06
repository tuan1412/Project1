package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertAccount {
	public void insertAccount(String username,String password) {
		try {
			Connection connection = SqliteConnection.dbConnection();
			String query = "insert into AccountInfo (Username,Password) values (?,?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.execute();	
		
			pst.close();
		} catch (Exception e) {
			System.out.println(e.toString());		}
	}
}
