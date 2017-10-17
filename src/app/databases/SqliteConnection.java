package app.databases;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SqliteConnection {

	public static Connection dbConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:..\\Project1\\AccountData.sqlite");
			System.out.println("Connect is successfull!!");
			return conn;
		} catch (Exception e) {
			System.out.println("ccc");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Exception");
			alert.setHeaderText(null);
			alert.setContentText("Erro : " + e.toString() + ". Can't connect to database!!" );
			alert.showAndWait();
			return null;
		}
	}
}
