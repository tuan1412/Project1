package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import app.services.CheckLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		signUp.setOnAction(e->onClickSignUp());
		signIn.setOnAction(e->CheckLogin.checkLogin(tfUsername.getText(), pfPassword.getText()));
		demoLabel.setOnMouseClicked(e->getDemo());

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
