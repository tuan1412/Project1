package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import app.model.Job;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DetailController implements Initializable {
	@FXML
	private AnchorPane detailJob;
	
	@FXML
	private TextField titleFeild;

	@FXML
	private TextArea desFeild;

	@FXML
	private Label startDate;

	@FXML
	private Label taskState;

	@FXML
	private Label workTime;

	@FXML
	private Label shortBreakTime;

	@FXML
	private Label longBreakTime;
	
    @FXML
    private JFXButton backBtn;

	private Job job = ContextController.getInstance().getJob();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startDate.setText("Start date: " + job.getStartDate().toString());
		titleFeild.setText(job.getTitle());
		taskState.setText("Task done: " + job.getTaskDone() + "/" + job.getTaskNumber());
		desFeild.setText(job.getDes());
		workTime.setText("Work time: " + job.getWorkTime());
		shortBreakTime.setText("Short break time: " + job.getShortBreakTime());
		longBreakTime.setText("Long break time: " + job.getLongBreakTime());

		titleFeild.setEditable(false);
		desFeild.setEditable(false);
		
		backBtn.setOnAction(e->detailJob.setVisible(true));
	}

}
