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
import com.jfoenix.controls.JFXTreeView;

import app.model.Job;
import app.services.CreateAccount;
import app.services.CreateJob;
import app.services.GetListJob;
import app.services.GetListJobNotDone;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

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
	private JFXListView<Job> jobsList;
	@FXML
	private JFXComboBox<String> comboBox;

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

		items.addListener(new ListChangeListener<Job>() {
			@Override
			public void onChanged(Change<? extends Job> c) {
				if (items.isEmpty()) {
					jobsList.setPrefHeight(183);
				}
				else{
					jobsList.setPrefHeight(items.size() * 35 + 2);
				}
			}
		});

		comboBox.getItems().add("Not Done");
		comboBox.getItems().add("Done");
		comboBox.getItems().add("Overdate");
		comboBox.setValue("Not Done");
		
		comboBox.valueProperty().addListener((ov, oldValue, newValue)->{
			if (newValue.equals("Not Done")) {
				List<Job> jobs = new GetListJob(ContextController.getInstance().getLoggedUserId(), workDatePicker.getValue()).getJobs();
				items.setAll(jobs);
				jobsList.setItems(items);
				return;
			}
			if (newValue.equals("Done")) {
				List<Job> jobs = new GetListJobNotDone(ContextController.getInstance().getLoggedUserId(), workDatePicker.getValue()).getJobs();
				items.setAll(jobs);
				jobsList.setItems(items);
				return;
			}
		});

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
		if (startDate.equals(workDatePicker.getValue()) && comboBox.getValue().equals("Not Done")) {
			List<Job> jobs = new GetListJob(ContextController.getInstance().getLoggedUserId(), workDatePicker.getValue()).getJobs();
			items.setAll(jobs);
			jobsList.setItems(items);	
		}
		backJobList();
	}
}
