package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import app.services.UserService;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	@FXML
	private AnchorPane main;

	@FXML
	private ImageView spiner;

	private UserService userService = new UserService();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		passwordFeild.setOnKeyPressed(e->{
			if (e.getCode().equals(KeyCode.ENTER)) {
				onClickLogin();
			}
		});
		signUp.setOnAction(e -> onClickSignUp());
		demoLabel.setOnMouseClicked(e -> getDemo());
		signIn.setOnAction(e -> onClickLogin());
		spiner.setVisible(false);
	}

	private void onClickLogin() {
		String username = usernameFeild.getText();
		String password = passwordFeild.getText();

		spiner.setVisible(true);
		RotateTransition trans = new RotateTransition(Duration.seconds(1), spiner);
		trans.setByAngle(360);
		trans.play();
		int iduser = userService.validateLogin(username, password);
		trans.setOnFinished(e -> login(iduser));
	}

	private void login(int iduser) {

		if (iduser != 0) {
			ContextController context = ContextController.getInstance();
			context.setLoggedUserId(iduser);
			switchScene("ListJob.fxml");

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Lỗi đăng nhập");
			alert.setContentText("Tài khoản hoặc mật khẩu không tồn tại");
			alert.show();
			spiner.setVisible(false);
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
			scene.getStylesheets().add("/app/resource/css/login.css");

			stage.getIcons().add(new Image("/app/resource/tomato.png"));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.sizeToScene();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
