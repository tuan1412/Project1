package app.controller;

import java.time.LocalTime;

import org.controlsfx.control.Notifications;

import app.ui.ClockTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class DemoController  {
	@FXML
	private Label backLoginLabel;
	@FXML
	private TextField taskFeild;
	@FXML
	private AnchorPane clockPane;

    @FXML
    private Label stateLabel;

    private LoginController loginController;
    
    public void setLoginController(LoginController loginController) {
    	this.loginController = loginController;
    }
	public void initialize() {
		backLoginLabel.setOnMouseClicked(e -> backLogin());
		backLoginLabel.setOnMouseEntered(e -> backLoginLabel.setUnderline(true));
		backLoginLabel.setOnMouseExited(e -> backLoginLabel.setUnderline(false));
		
		taskFeild.setOnMouseExited(e->taskFeild.setEditable(false));
		taskFeild.setOnMouseClicked(e->taskFeild.setEditable(true));
		
		ClockTimer timer = new ClockTimer(LocalTime.of(0, 25, 0));

		clockPane.getChildren().add(timer);
		timer.startTimeline();
		
		timer.isStopProperty().addListener((ov, oldValue, newValue)->{
			stateLabel.setText("Job done. Sign in for more.");
			showDoneDemo();
		});

	}

	public void backLogin() {
		loginController.setLogin();
	}
	
	// hiện thị thong báo, dùng thư viện controlsfx, cũng k có gì, cứ làm theo thôi
	private void showDoneDemo() {
		Notifications noti = Notifications.create()
				.title("Demo done")
				.text("Take a account to try more")
				.graphic(new ImageView(new Image("app/resource/ok.png")))
				.position(Pos.BOTTOM_RIGHT)
				.hideAfter(Duration.seconds(2))
				.darkStyle();
		Platform.runLater(new Runnable() {
			public void run() {
				 noti.show();
			}
		});
	}

}
