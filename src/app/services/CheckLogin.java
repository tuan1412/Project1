package app.services;

import java.sql.Connection;

import app.dao.QueryAccount;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *  
 * @author HUYQUANGPTN
 *
 */
public class CheckLogin {
	
	public boolean checkLogin(String username, String password) {
		QueryAccount qAccount = new QueryAccount();
		return qAccount.isExistAccount(username, password);
	
	}
	
}
