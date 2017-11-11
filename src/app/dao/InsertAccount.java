package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import app.utils.ConnectionUtils;

public class InsertAccount {
	public void insertAccount(String username,String password) {
		try {
			Connection connection = ConnectionUtils.getConnection();
			String sql = "insert into UserInfo (username,password) values (?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.execute();	
		
			pst.close();
			ConnectionUtils.close();
		} catch (Exception e) {
			System.out.println(e.toString());		}
	}
}
