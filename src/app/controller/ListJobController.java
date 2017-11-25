package app.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;

import app.model.Job;
import app.services.CreateJob;
import app.services.GetListJob;
import app.services.GetListJobNotDone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 * 
 * @author Chu lun Kute
 *
 */
public class ListJobController implements Initializable {

	@FXML
	private FlowPane mainPane;
	@FXML
	private DatePicker workDatePicker;
	@FXML
	private JFXButton addNewBtn;
	@FXML
	private AnchorPane createJobPane;
	@FXML
	private Label backLabel;
	@FXML
	private TextField titleFeild;
	@FXML
	private TextArea desFeild;
	@FXML
	private JFXSlider taskNumberSlider;
	@FXML
	private JFXSlider workTimeSlider;
	@FXML
	private JFXSlider shortBreakTimeSlider;
	@FXML
	private JFXSlider longBreakTimeSlider;
	@FXML
	private JFXButton confirmBtn;
	@FXML
	private JFXDatePicker startDatePicker;
	@FXML
	private JFXComboBox<String> comBoxJob;
	@FXML
	private JFXListView<Job> jobsList;
	@FXML
	private JFXComboBox<String> comBoxJobOverdate;
	@FXML
	private JFXListView<Job> jobsListOverdate;

	private ObservableList<Job> items = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		workDatePicker.setValue(LocalDate.now());
		startDatePicker.setValue(LocalDate.now());

		createJobPane.setVisible(false);
		addNewBtn.setOnAction(e -> onClickAddNewBtn());
		backLabel.setOnMouseClicked(e -> backJobList());
		confirmBtn.setOnAction(e -> confirmNewJob());

		jobsList.setPlaceholder(new Label("No Content In List"));
		workDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
			List<Job> jobs = new GetListJob(ContextController.getInstance().getLoggedUserId(), newValue).getJobs();
			items.setAll(jobs);
			jobsList.setItems(items);
		});

		// items.addListener(new ListChangeListener<Job>() {
		// @Override
		// public void onChanged(Change<? extends Job> c) {
		// if (items.isEmpty()) {
		// jobsList.setPrefHeight(183);
		// }
		// else{
		// jobsList.setPrefHeight(items.size() * 35 + 2);
		// }
		// }
		// });

		comBoxJob.getItems().add("Not Done");
		comBoxJob.getItems().add("Done");
		comBoxJob.setValue("Not Done");

		comBoxJob.valueProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue.equals("Not Done")) {
				List<Job> jobs = new GetListJob(ContextController.getInstance().getLoggedUserId(),
						workDatePicker.getValue()).getJobs();
				items.setAll(jobs);
				jobsList.setItems(items);
				return;
			}
			if (newValue.equals("Done")) {
				List<Job> jobs = new GetListJobNotDone(ContextController.getInstance().getLoggedUserId(),
						workDatePicker.getValue()).getJobs();
				items.setAll(jobs);
				jobsList.setItems(items);
				return;
			}
		});
		
		comBoxJobOverdate.getItems().add("Overdate");
		comBoxJobOverdate.setValue("Overdate");
		
		jobsListOverdate.setPlaceholder(new Label("No Content In List"));


	}

	private void onClickAddNewBtn() {
		createJobPane.setVisible(true);
	}

	private void backJobList() {
		createJobPane.setVisible(false);
	}

	private void confirmNewJob() {
		int iduser = ContextController.getInstance().getLoggedUserId();
		String title = titleFeild.getText();
		String des = titleFeild.getText();
		LocalDate startDate = startDatePicker.getValue();
		int taskNumber = (int) taskNumberSlider.getValue();
		int workTime = (int) workTimeSlider.getValue();
		int shortBreakTime = (int) shortBreakTimeSlider.getValue();
		int longBreakTime = (int) longBreakTimeSlider.getValue();

		CreateJob createJob = new CreateJob(iduser, startDate, title, des, taskNumber, workTime, shortBreakTime,
				longBreakTime);
		createJob.create();
		if (startDate.equals(workDatePicker.getValue()) && comBoxJob.getValue().equals("Not Done")) {
			List<Job> jobs = new GetListJob(ContextController.getInstance().getLoggedUserId(),
					workDatePicker.getValue()).getJobs();
			items.setAll(jobs);
			jobsList.setItems(items);
		}
		backJobList();
	}
}
