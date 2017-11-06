package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryUsername {
	public boolean isExistUsername(String username) {
		boolean flag = false;
		try {
			Connection connection = SqliteConnection.dbConnection();
			String query = "select * from AccountInfo where Username=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				flag= true;
			}
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}	
		return flag;
	}
}
