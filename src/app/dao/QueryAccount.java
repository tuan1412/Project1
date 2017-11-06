package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Ket noi den database kiem tra tai khoan de dang nhap
 * @author HUYQUANGPTN
 *
 */
public class QueryAccount {

	public boolean isExistAccount(String username, String password) {
		boolean flag = false;

		try {
			Connection connection = SqliteConnection.dbConnection();
			String query = "select * from AccountInfo where Username=? and Password=? ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				flag = true;
				System.out.println("Sign in sucessful!!");
			}

			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return flag;
	}
}
	