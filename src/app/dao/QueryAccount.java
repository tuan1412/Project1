package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.utils.ConnectionUtils;
/**
 * Ket noi den database kiem tra tai khoan de dang nhap
 * @author HUYQUANGPTN
 *
 */
public class QueryAccount {
	/**
	 * Tra ve userId can tim
	 * @param username
	 * @param password
	 * @return
	 */
	public int isExistAccount(String username, String password) {
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
	public static void main(String[] args) {
		QueryAccount test = new QueryAccount();
		System.out.println(test.isExistAccount("tuan", "123456"));
	}
}
	