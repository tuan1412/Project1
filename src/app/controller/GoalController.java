package app.controller;

import java.time.LocalDate;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog.DialogTransition;

import app.dao.QueryTimeWork;
import app.model.TimeWorkOfDay;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GoalController {
	@FXML
	private JFXDatePicker dayPicker;

	@FXML
	private PieChart pieChart;

	@FXML
	private TextField goalFeild;

	@FXML
	private ImageView reload;
	

	TimeWorkOfDay timeWork = null;
	private static int goal = 120;

	public void initialize() {
		dayPicker.setValue(LocalDate.now());
		goalFeild.setText(Integer.toString(goal));
		reload.setOnMouseClicked(e -> reloadChart());
	}

	private void reloadChart() {
		// viet them ham gay du lieu tu dayPicker va goalFeild
		timeWork = new QueryTimeWork().getTimeWorkOfDay(ContextController.getInstance().getLoggedUserId(),
				dayPicker.getValue().toString());
		if (timeWork == null) {
			timeWork = new TimeWorkOfDay(dayPicker.getValue().toString(), 0);
		}
		try {
			goal = Integer.parseInt(goalFeild.getText());
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("Please fill in goal field number minutes goad of day!!");
			alert.showAndWait();
		}
		RotateTransition trans = new RotateTransition(Duration.seconds(1.5), reload);
		trans.setByAngle(360);
		trans.play();
		trans.setOnFinished(e -> showChart());

	}

	// xu ly hien thi o day, neu cam thay bieu do tron k hop thi tuy o chinh.
	private void showChart() {
		float rate = (float) timeWork.getMinutesWork() / goal;
		ObservableList<Data> list = FXCollections.observableArrayList();
		if (rate < 1) {// chua hoan thanh goal
			list = FXCollections.observableArrayList(
					new PieChart.Data("Completed", rate),
					new PieChart.Data("Left", 1 - rate));
					//hien thi label vs truong hop chua hoan thanh goal :  timeWork.getMinutesWork() '/' goal...
			        
		} else {// da hoan thanh
			list = FXCollections.observableArrayList(
					new PieChart.Data("Completed", rate), 
					new PieChart.Data("Left", 0));
					// da hoan thanh goal vs thanh tich timeWork.getMinutesWork() '/' goal. dat goal moi...
		}
		pieChart.setData(list);

	}

}
