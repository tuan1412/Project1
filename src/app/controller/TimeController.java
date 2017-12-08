package app.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.management.Notification;

import app.model.Job;
import app.services.JobService;
import app.services.UpdateStatistic;
import app.ui.ClockTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TimeController {
	private final static String WORK_TITLE = "Time to work";
	private final static String SHORT_BREAK_TITLE = "A short break time";
	private final static String LONG_BREAK_TITLE = "A long break time";
	private final static String DONE_TITLE = "Nice! Job done.";
	
	private JobService jobService = new JobService();
	
	private ListJobController listJobController;

	@FXML
	private Label stateTime;

	@FXML
	private Label backLabel;

	@FXML
	private Label titleLabel;

	@FXML
	private AnchorPane timer;
	
	@FXML
	private Label taskLabel;

	private Job job = ContextController.getInstance().getJob();
	
	private int count;
	
	private ClockTimer clockTimer;
	
	private UpdateStatistic upStatistic = new UpdateStatistic();

	private int minsDone;

	public void setListJobController(ListJobController listJobController) {
		this.listJobController = listJobController;
	}
	
	public void initialize() {
		try {
			count = 0;
			titleLabel.setText(job.getTitle());
			if (job.getPause() == null || job.getPause().equals("00:00:00")) {
				clockTimer = new ClockTimer(LocalTime.of(0, job.getWorkTime(), 0));
			} else {
				clockTimer = new ClockTimer(LocalTime.parse(job.getPause()));
			}
			backLabel.setVisible(false);
			stateTime.setText("Time to work");
			makeTimer();
			backLabel.setOnMouseClicked(e->exitClock());
			setTextTaskLabel();
			
		} catch (NullPointerException e) {
		}
	}
	
	private void doWhenStop() {
		if (stateTime.getText().equals(WORK_TITLE)) {
			job.setTaskDone(job.getTaskDone() + 1);
			minsDone += clockTimer.getTimeDone();

			setTextTaskLabel();
			if (job.getTaskDone() == job.getTaskNumber()) {
				stateTime.setText(DONE_TITLE);
				backLabel.visibleProperty().unbind();
				backLabel.setVisible(true);
				return;
			}
			if (++count == 4) {
				stateTime.setText(LONG_BREAK_TITLE);
				clockTimer = new ClockTimer(LocalTime.of(0, job.getLongBreakTime(), 0));
				makeTimer();
			}else {
				stateTime.setText(SHORT_BREAK_TITLE);
				clockTimer = new ClockTimer(LocalTime.of(0, job.getShortBreakTime(), 0));
				makeTimer();
			}
			return;
		}
		if (stateTime.getText().equals(LONG_BREAK_TITLE)) {
			stateTime.setText(WORK_TITLE);
			clockTimer = new ClockTimer(LocalTime.of(0, job.getWorkTime(), 0));
			makeTimer();
		}
		if (stateTime.getText().equals(SHORT_BREAK_TITLE)) {
			stateTime.setText(WORK_TITLE);
			clockTimer = new ClockTimer(LocalTime.of(0, job.getWorkTime(), 0));
			makeTimer();
		}
	}
	
	
	private void makeTimer() {
		timer.getChildren().add(clockTimer);
		clockTimer.startTimeline();
		clockTimer.isStopProperty().addListener(e -> doWhenStop());
		
		backLabel.visibleProperty().bind(clockTimer.isPlayProperty().not());
	}
	
	private void exitClock() {
		if (stateTime.getText().equals(WORK_TITLE) || stateTime.getText().equals(DONE_TITLE)) {
			if (stateTime.getText().equals(WORK_TITLE)) {
				minsDone += clockTimer.getTimeDone();
			}
			job.setPause(clockTimer.getTime().toString());
			jobService.updateJob(job);
			upStatistic.updateStatistic(job.getIduser(), LocalDate.now().toString(), minsDone);
			listJobController.setHome();
		}else {
			job.setPause("00:00:00");
			jobService.updateJob(job);
			listJobController.setHome();
		}
	}
	
	private void setTextTaskLabel() {
		taskLabel.setText("Task done: "+ job.getTaskDone() + "/" + job.getTaskNumber());
	}
}
