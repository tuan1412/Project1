package app.controller;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import app.ui.ClockTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DemoController implements Initializable {
	@FXML
	private Label backLoginLabel;

	@FXML
	private TextField taskFeild;

	@FXML
	private AnchorPane clockPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		backLoginLabel.setOnMouseClicked(e -> backLogin());
		backLoginLabel.setOnMouseEntered(e -> backLoginLabel.setUnderline(true));
		backLoginLabel.setOnMouseExited(e -> backLoginLabel.setUnderline(false));
		
		taskFeild.setOnMouseExited(e->taskFeild.setEditable(false));
		taskFeild.setOnMouseClicked(e->taskFeild.setEditable(true));
		
		ClockTimer timer = new ClockTimer(LocalTime.of(0, 0, 40));
		clockPane.getChildren().add(timer);
		timer.startTimeline();

	}

	public void backLogin() {
		backLoginLabel.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
