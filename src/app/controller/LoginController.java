package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import app.MainApp;
import app.services.UserService;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
/**
 * Class này để bắt sự kiện trong login.fxml
 * @author Chu lun Kute
 *
 */
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
		// bắt sự kiện ấn enter khi đăng nhập
		passwordFeild.setOnKeyPressed(e->{
			if (e.getCode().equals(KeyCode.ENTER)) {
				onClickLogin();
			}
		});
		// bắt sự kiện khi ấn nút đăng kí
		signUp.setOnAction(e -> setSignUp());
		// bắt sự kiện khi ấn link get demo
		demoLabel.setOnMouseClicked(e -> setDemo());
		// bắt sự kiện khi ấn nút login 
		signIn.setOnAction(e -> onClickLogin());
		// ẩn cái hiệu ứng xoay khi đăng nhập
		spiner.setVisible(false);
	}

	// khi nhận sự kiện enter hay ấn nút login
	private void onClickLogin() {
		// lấy dữ liệu từ 2 trường
		String username = usernameFeild.getText();
		String password = passwordFeild.getText();
		
		// đoạn code này để cho cái hiệu ứng xoay, sau khi kết thúc sẽ đăng nhập
		spiner.setVisible(true);
		RotateTransition trans = new RotateTransition(Duration.seconds(1), spiner);
		trans.setByAngle(360);
		trans.play();
		// gọi service validate 
		int iduser = userService.validateLogin(username, password);
		trans.setOnFinished(e -> login(iduser));
	}
	// sau khi kết thúc hiệu ứng sẽ đăng nhập
	private void login(int iduser) {
		// neu validate trả về 0 thì đúng, còn lại sẽ trả về user id
		// neu iduser khác 0, có người đăng nhập, sẽ lưu iduser vào một class có biến static để tiện dùng cho về sau
		if (iduser != 0) {
			ContextController context = ContextController.getInstance();
			context.setLoggedUserId(iduser);
			setListJob();
		// nếu iduser = 0 thì thông báo lỗi
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Lỗi đăng nhập");
			alert.setContentText("Tài khoản hoặc mật khẩu không tồn tại");
			alert.show();
			spiner.setVisible(false);
		}
	}


	// hàm này để quay lại trang login dùng cho cái controller sau
	public void setLogin() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Login.fxml"));
			Parent root = loader.load();
			main.getChildren().clear();
			main.getChildren().add((Node) root);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// hàm này để app hiện sang chức năng demo
	public void setDemo() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Demo.fxml"));
			Parent root = loader.load();
			DemoController demoController = loader.getController();
			demoController.setLoginController(this);

			main.getChildren().clear();
			main.getChildren().add((Node) root);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// hàm này để app hiện sang chức năng liệt kê list công việc
	public void setListJob() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/ListJob.fxml"));
			Parent root = loader.load();
			ListJobController listJobController = loader.getController();
			listJobController.setLoginController(this);

			main.getChildren().clear();
			main.getChildren().add((Node) root);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// hàm này để app chuyển sang trang đăng kí
	public void setSignUp() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/SignUp.fxml"));
			Parent root = loader.load();
			SignUpController  signUpController = loader.getController();
			signUpController.setLoginController(this);

			main.getChildren().clear();
			main.getChildren().add((Node) root);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
