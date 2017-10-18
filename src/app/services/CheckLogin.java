package app.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.dao.SqliteConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CheckLogin {
	private static Connection connection = null;
	
	public static void checkLogin(String str1, String str2) {
		try {
			connection = SqliteConnection.dbConnection();
			String query = "select * from AccountInfo where UserName=? and Password=? ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, str1);
			pst.setString(2, str2);
			
			ResultSet rs = pst.executeQuery();
			int count = 0;
			while(rs.next()) {
				count +=1;
			}
			if(count == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("success");
				alert.setHeaderText(null);
				alert.setContentText("Login successful!!");
				alert.showAndWait();
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Login fail");
				alert.setHeaderText(null);
				alert.setContentText("Wrong username or password");
				alert.showAndWait();
			}
				rs.close();
				pst.close();
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
}
