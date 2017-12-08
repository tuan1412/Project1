package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import app.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
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
	
	private UserService userService = new UserService();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnBack.setOnAction(e -> backLogin());
		btnSignUp.setOnAction(e -> createUser());
	}

	private void backLogin() {
		btnBack.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/app/resource/css/login.css");
			stage.setResizable(false);
			stage.getIcons().add(new Image("/app/resource/tomato.png"));
			stage.setScene(scene);
			stage.show();
		} catch (Exception c) {
			c.printStackTrace();
		}
	}
	
	private void createUser() {
		String username = tfUsername.getText();
		String password = pfPassword1.getText();
		String repassword = pfPassword2.getText();
		if (userService.create(username, password, repassword)) {
			showError();
		}else{
			showSuccess();
		};
		removeInfo();
	}
	
	private void showError() {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error add user");
    	alert.setHeaderText(null);
    	alert.setContentText("User don't add. Check your information");

    	alert.showAndWait();
	}
	
	private void showSuccess() {
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Success");
    	alert.setHeaderText(null);
    	alert.setContentText("User added. You can make job.");

    	alert.showAndWait();
	}
	
	private void removeInfo() {
		tfUsername.setText("");
		pfPassword1.setText("");
		pfPassword2.setText("");
	}
	
}
