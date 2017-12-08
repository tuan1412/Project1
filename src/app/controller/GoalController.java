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
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class GoalController {
	@FXML
	private JFXDatePicker dayPicker;

	@FXML
	private StackPane chartPane;

	@FXML
	private TextField goalFeild;

	@FXML
	private ImageView reload;
	
	@FXML
	private Label stateLabel;
	
	private int userId = ContextController.getInstance().getLoggedUserId();
	

	TimeWorkOfDay timeWork = null;
	private static int goal = 120;

	public void initialize() {
		dayPicker.setValue(LocalDate.now());
		goalFeild.setText(Integer.toString(goal));
		reloadChart();
		reload.setOnMouseClicked(e -> reloadChart());
	}

	private void reloadChart() {
		// viet them ham gay du lieu tu dayPicker va goalFeild
		timeWork = new QueryTimeWork().getTimeWorkOfDay(userId,
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
			return;
		}
		RotateTransition trans = new RotateTransition(Duration.seconds(1), reload);
		trans.setByAngle(360);
		trans.play();
		trans.setOnFinished(e -> showChart());

	}

	// xu ly hien thi o day, neu cam thay bieu do tron k hop thi tuy o chinh.
	private void showChart() {
		int doneMins = timeWork.getMinutesWork();
		float rate = (float) doneMins / goal;
		ObservableList<Data> list = FXCollections.observableArrayList();
		if (rate < 1) {// chua hoan thanh goal
			list = FXCollections.observableArrayList(
					new PieChart.Data("Completed", rate),
					new PieChart.Data("Not done", 1 - rate));
			stateLabel.setText(goal - doneMins + " mins to get goal!" );
			        
		} else {// da hoan thanh
			list = FXCollections.observableArrayList(
					new PieChart.Data("Completed", rate), 
					new PieChart.Data("Not done", 0));
			stateLabel.setText("Congratulations! Goal done.");
		}
		chartPane.getChildren().clear();
		PieChart pieChart = new PieChart(list);
//		pieChart.setLabelLineLength(10);
		pieChart.setLegendVisible(true);
		pieChart.setLabelsVisible(false);
		
		chartPane.getChildren().add(pieChart);
		
	}

}
