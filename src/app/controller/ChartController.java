package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import app.dao.QueryTimeWork;
import app.model.TimeWorkOfDay;
import app.services.DateIncrementer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ChartController implements Initializable {

	@FXML
	private JFXButton btnLoadChart2;

	@FXML
	private LineChart<String, Integer> lineChart;

	@FXML
	private JFXComboBox<String> cbDate2;

	@FXML
	private JFXComboBox<String> cbDate1;

	private List<TimeWorkOfDay> listTimeWork = new ArrayList<>();

	private String startDate;
	private String endDate;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listTimeWork = new QueryTimeWork().getListTimeWork(ContextController.getInstance().getLoggedUserId());
		startDate = listTimeWork.get(0).getDate();
		endDate = listTimeWork.get(listTimeWork.size() - 1).getDate();
		for (int i = 0; i < listTimeWork.size(); i++) {
			cbDate1.getItems().add(listTimeWork.get(i).getDate());
		}
		cbDate1.valueProperty().addListener((ov, oldValue, newValue) -> {
			for (int i = 0; i < listTimeWork.size(); i++) {
				if (listTimeWork.get(i).getDate().equals(newValue)) {
					cbDate2.getItems().clear();
					for (int j = i + 1; j < listTimeWork.size(); j++) {
						cbDate2.getItems().add(listTimeWork.get(j).getDate());
					}
					break;
				}
			}
		});

		btnLoadChart2.setOnAction(e -> clickLoadChart2(cbDate1.getValue(), cbDate2.getValue()));

	}

	private void clickLoadChart2(String date1, String date2) {
		if ((cbDate1.getSelectionModel().isEmpty()) || (cbDate2.getSelectionModel().isEmpty())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("Please select date in combo box!!");
			alert.showAndWait();
		} else {
			String currentDate = date1;
			int index1 = 0, index2 = 0;
			XYChart.Series<String, Integer> series = new XYChart.Series<>();
			for (int i = 0; i < listTimeWork.size(); i++) {
				if (listTimeWork.get(i).getDate().equals(date1)) {
					index1 = i;
				}
				if (listTimeWork.get(i).getDate().equals(date2)) {
					index2 = i;
				}
			}
			while (index1 <= index2) {
				if (currentDate.equals(listTimeWork.get(index1).getDate())) {
					series.getData().add(new XYChart.Data<>(listTimeWork.get(index1).getDate(),
							listTimeWork.get(index1).getMinutesWork()));
					currentDate = DateIncrementer.addOneDay(currentDate);
					index1 += 1;
				} else {
					series.getData().add(new XYChart.Data<>(currentDate, 0));
					currentDate = DateIncrementer.addOneDay(currentDate);
				}
			}
			lineChart.setLegendVisible(false);
			lineChart.getData().clear();
			lineChart.getData().add(series);
		}

	}
}
