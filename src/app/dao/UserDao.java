package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.utils.ConnectionUtils;

public class UserDao {
	public boolean insert(String username, String password) {
		boolean flag = false;
		try {
			Connection connection = ConnectionUtils.getConnection();
			String sql = "insert into UserInfo (username,password) values (?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.execute();	
			flag = true;
			
			pst.close();
			ConnectionUtils.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public int findByUsernameAndPassword(String username, String password) {
		int iduser = 0;

		try {
			Connection connection = ConnectionUtils.getConnection();
			String query = "select * from UserInfo where username=? and password=? ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				iduser = rs.getInt("iduser");
			}

			rs.close();
			pst.close();
			ConnectionUtils.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return iduser;
	}
	
	public boolean findByUsername(String username) {
		boolean flag = false;
		try {
			Connection connection = ConnectionUtils.getConnection();
			String sql = "select * from UserInfo where username=?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				flag= true;
			}
			pst.close();
			rs.close();
			ConnectionUtils.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}	
		return flag;
	}
	
}
