package app.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;

import app.model.Job;
import app.services.JobService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class HomeController {
	private ListJobController listJobController;
	@FXML
	private AnchorPane parentPane;

	@FXML
	private FlowPane mainPane;

	@FXML
	private JFXDatePicker workDatePicker;

	@FXML
	private JFXButton addNewBtn;

	@FXML
	private JFXComboBox<String> comboBox;

	@FXML
	private JFXListView<Job> jobsList;

	@FXML
	private StackPane stackPane;

	private ObservableList<Job> items = FXCollections.observableArrayList();

	private JobService jobService = new JobService();

	private int iduser = ContextController.getInstance().getLoggedUserId();

	public void setListJobController(ListJobController listJobController) {
		this.listJobController = listJobController;
	}

	public void initialize() {
		initDatePicker();
		initComboBox();
		addNewBtn.setOnAction(e -> switchScene());
		initListView();

	}

	private void initDatePicker() {
		workDatePicker.setValue(LocalDate.now());
		workDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
			String chose = comboBox.getValue();
			if (chose == null) {
				return;
			}
			if (chose.equals("Not Done")) {
				LocalDate date = workDatePicker.getValue();
				List<Job> jobs = jobService.queryJobNotDone(iduser, date);
				items.setAll(jobs);
				jobsList.setItems(items);
				return;
			}
			if (chose.equals("Done")) {
				LocalDate date = workDatePicker.getValue();
				List<Job> jobs = jobService.queryJobDone(iduser, date);
				items.setAll(jobs);
				jobsList.setItems(items);
				return;
			}
			if (chose.equals("Overdate")) {
				LocalDate date = workDatePicker.getValue();
				System.out.println(date);
				List<Job> jobs = jobService.queryJobOverdate(iduser, date);
				items.setAll(jobs);
				jobsList.setItems(items);
				return;
			}
		});

	}

	private void initComboBox() {
		comboBox.getItems().add("Not Done");
		comboBox.getItems().add("Done");
		comboBox.getItems().add("Overdate");

		comboBox.valueProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue.equals("Not Done")) {
				LocalDate date = workDatePicker.getValue();
				List<Job> jobs = jobService.queryJobNotDone(iduser, date);
				items.setAll(jobs);
				jobsList.setItems(items);
				return;
			}
			if (newValue.equals("Done")) {
				LocalDate date = workDatePicker.getValue();
				List<Job> jobs = jobService.queryJobDone(iduser, date);
				items.setAll(jobs);
				jobsList.setItems(items);
				return;
			}
			if (newValue.equals("Overdate")) {
				LocalDate date = workDatePicker.getValue();
				System.out.println(date);
				List<Job> jobs = jobService.queryJobOverdate(iduser, date);
				items.setAll(jobs);
				jobsList.setItems(items);
				return;
			}
		});
		comboBox.setValue("Not Done");
	}

	private void switchScene() {

		listJobController.setCreate();
	}

	private void initListView() {
		jobsList.setPlaceholder(new Label("No Content In List"));
		jobsList.setCellFactory(p -> {
			ListCell<Job> cell = new ListCell<Job>() {
				protected void updateItem(Job item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						setText(item.getTitle());
					}
				}
			};
			ContextMenu contextMenu = new ContextMenu();
			MenuItem playItem = new MenuItem("Play");
			playItem.setOnAction(e -> makePlay(jobsList.getSelectionModel().getSelectedItem()));
			MenuItem doneItem = new MenuItem("Mark as done");
			doneItem.setOnAction(e -> moveDone(jobsList.getSelectionModel().getSelectedIndex()));
			MenuItem detailItem = new MenuItem("View detail");
			detailItem.setOnAction(e -> viewDetail(jobsList.getSelectionModel().getSelectedItem()));
			MenuItem removeItem = new MenuItem("Remove job");
			removeItem.setOnAction(e-> removeItem(jobsList.getSelectionModel().getSelectedItem()));
			contextMenu.getItems().addAll(playItem, doneItem, detailItem, removeItem);

			cell.emptyProperty().addListener((ov, wasEmpty, isNowEmpty) -> {
				if (isNowEmpty) {
					cell.setContextMenu(null);
				} else {
					if (comboBox.getValue().equals("Not Done") || comboBox.getValue().equals("Overdate")) {
						cell.setContextMenu(contextMenu);
					}
				}
			});
			return cell;
		});

	}

	private void moveDone(int index) {
		jobService.updateDone(items.get(index).getIdjob());
		items.remove(index);
	}

	private void makePlay(Job job) {
		ContextController.getInstance().setJob(job);
		listJobController.setPlay();
	}

	private void viewDetail(Job job) {
		try {
			ContextController.getInstance().setJob(job);
			stackPane.getChildren().add(FXMLLoader.load(getClass().getResource("../view/Detail.fxml")));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void removeItem(Job job) {
		items.remove(job);
//		jobService.removeByID(job.getIdjob());
	}
}
