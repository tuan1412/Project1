package app.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import app.databases.SqliteConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private JFXTextField tfUsername;

    @FXML
    private JFXButton signIn;

    @FXML
    private JFXPasswordField pfPassword;

    @FXML
    private JFXButton signUp;

    @FXML
    private Label demoLabel;

	
	Connection connection = null;	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		signUp.setOnAction(e->onClickSignUp());
		signIn.setOnAction(e->onClickSignIn());
		demoLabel.setOnMouseClicked(e->getDemo());
		connection = SqliteConnection.dbConnection();
	}
	// cai dat action cho signin
	public void onClickSignIn() {
		try {
			String query = "select * from AccountInfo where UserName=? and Password=? ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, tfUsername.getText());
			pst.setString(2, pfPassword.getText());
			
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
	
	public void onClickSignUp(){
		signUp.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("../view/SignUp.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void getDemo() {
		signUp.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("../view/Demo.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
