package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.utils.ConnectionUtils;
/**
 * Kiem tra ten dang nhap co ton tai khong
 * @author Chu lun Kute
 *
 */
public class QueryUsername {
	public boolean isExistUsername(String username) {
		boolean flag = false;
		try {
			Connection connection = ConnectionUtils.getConnection();
			String sql = "select * from project1 where username=?";
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
