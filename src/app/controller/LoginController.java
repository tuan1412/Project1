package app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import app.services.ValidateLogin;
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
	private JFXTextField usernameFeild;
	@FXML
	private JFXButton signIn;
	@FXML
	private JFXPasswordField passwordFeild;
	@FXML
	private JFXButton signUp;
	@FXML
	private Label demoLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		signUp.setOnAction(e -> onClickSignUp());
		demoLabel.setOnMouseClicked(e -> getDemo());
		signIn.setOnAction(e-> onClickLogin());

	}

	private void onClickLogin() {
		String username = usernameFeild.getText();
		String password = passwordFeild.getText();
		
		ValidateLogin validateLogin = new ValidateLogin();
		int iduser = validateLogin.isValidate(username, password);
		if (iduser != 0) {
			ContextController context = ContextController.getInstance();
			context.setLoggedUserId(iduser);
			switchScene("ListJob.fxml");
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Lỗi đăng nhập");
			alert.setContentText("Tài khoản hoặc mật khẩu không tồn tại");
			alert.showAndWait();
		}
	}
	

	private void onClickSignUp() {
		switchScene("SignUp.fxml");
	}

	private void getDemo() {
		switchScene("Demo.fxml");
	}
	
	private void switchScene(String newScence) {
		signUp.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			String desScene = "../view/" + newScence;
			Parent root = FXMLLoader.load(getClass().getResource(desScene));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
