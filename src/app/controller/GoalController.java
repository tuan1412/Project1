package app.controller;

import java.time.LocalDate;

import com.jfoenix.controls.JFXDatePicker;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
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
    
    public void initialize() {
    	dayPicker.setValue(LocalDate.now());
    	goalFeild.setText("25");
    	
    	reload.setOnMouseClicked(e->reloadChart());
	}
    
    private void reloadChart() {
    	//viet them ham gay du lieu tu dayPicker va goalFeild
    	RotateTransition trans = new RotateTransition(Duration.seconds(1.5), reload);
    	trans.setByAngle(360);
    	trans.play();
    	trans.setOnFinished(e->showChart());
   
    }
    //xu ly hien thi o day, neu cam thay bieu do tron k hop thi tuy o chinh.
    private void showChart() {
    	
    }
    
}
