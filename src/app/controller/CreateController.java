package app.controller;

import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSlider;

import app.model.JobForm;
import app.services.JobService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CreateController {
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
    
    private JobService jobService = new JobService();
    
    private int iduser = ContextController.getInstance().getLoggedUserId();
    
    private ListJobController listJobController;
    
	public void setListJobController(ListJobController listJobController) {
		this.listJobController = listJobController;
	}
	

	
    public void initialize() {
		backLabel.setOnMouseClicked(e->backMain());
		confirmBtn.setOnAction(e->addJob());

	}
    
    private void backMain() {
    	listJobController.setHome();
    }
    
    private void addJob() {
    	LocalDate startDate = startDatePicker.getValue();
    	String title = titleFeild.getText();
    	String des = desFeild.getText();
    	int taskNumber = (int) taskNumberSlider.getValue();
    	int workTime = (int) workTimeSlider.getValue();
    	int shortBreakTime = (int) shortBreakTimeSlider.getValue();
    	int longBreakTime = (int) longBreakTimeSlider.getValue();
    	
    	JobForm form = new JobForm(iduser, startDate, title, des, taskNumber, workTime, shortBreakTime, longBreakTime);
    	if (jobService.insertJob(form)) {
    		
    	}else{
    		showErrorInsert();
    	};
    }
    
    private void showErrorInsert() {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error add job");
    	alert.setHeaderText(null);
    	alert.setContentText("Job don't add. Check your information");

    	alert.showAndWait();
    }
    
    private void showSuccessInsert(){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Success");
    	alert.setHeaderText(null);
    	alert.setContentText("Job added. Try add more.");

    	alert.showAndWait();
    	
    }
}
