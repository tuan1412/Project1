package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import app.services.CreatAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
	@FXML
	private JFXPasswordField pfPassword1;

	@FXML
	private JFXPasswordField pfPassword2;

	@FXML
	private JFXButton btnBack;

	@FXML
	private JFXButton btnSignUp;

	@FXML
	private JFXTextField tfUsername;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CreatAccount crAccount = new CreatAccount();
		btnBack.setOnAction(e -> backLogin());
		btnSignUp.setOnAction(e -> crAccount.creatAccount(tfUsername.getText(), pfPassword1.getText(), pfPassword2.getText()));
	}

	public void backLogin() {
		btnBack.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception c) {
			c.printStackTrace();
		}
	}
}
