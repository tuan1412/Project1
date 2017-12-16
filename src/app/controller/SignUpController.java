package app.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import app.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Class bắt sự kiện trang đăng kí
 * @author Chu lun Kute
 *
 */
public class SignUpController {
	
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

	private LoginController loginController;

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public void initialize() {
		// bắt sự kiện ấn nút back to login
		btnBack.setOnAction(e -> backLogin());
		// bắt sự kiến ấn nút sign up
		btnSignUp.setOnAction(e -> createUser());
	}
	
	// hàm quay lại trang login
	private void backLogin() {
		loginController.setLogin();
	}
	
	// hàm đăng kí
	private void createUser() {
		// nhận chuỗi 3 trường
		String username = tfUsername.getText();
		String password = pfPassword1.getText();
		String repassword = pfPassword2.getText();
		
		// gọi service đăng kí
		if (userService.create(username, password, repassword)) {
			showSuccess();
		} else {
			showError();
		}
		;
		removeInfo();
	}
	
	// hiện thị lỗi nếu đăng kí ko thành công
	private void showError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error add user");
		alert.setHeaderText(null);
		alert.setContentText("User don't add. Check your information");

		alert.showAndWait();
	}

	// hiện thị thông báo thành công
	private void showSuccess() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(null);
		alert.setContentText("User added. You can make job.");

		alert.showAndWait();
	}

	// nếu thành công sẽ xóa các trường
	private void removeInfo() {
		tfUsername.setText("");
		pfPassword1.setText("");
		pfPassword2.setText("");
	}

}
