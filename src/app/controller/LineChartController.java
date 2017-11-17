package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import app.dao.QueryTaskDone;
import app.model.TaskDoneOfDay;
import app.services.DateIncrementer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class LineChartController implements Initializable {

	@FXML
	private JFXButton btnLoadChart2;

	@FXML
	private JFXButton btnLoadChart1;

	@FXML
	private LineChart<String, Integer> lineChart;

	@FXML
	private JFXComboBox<String> cbDate2;

	@FXML
	private JFXComboBox<String> cbDate1;

	private List<TaskDoneOfDay> listTaskDone = new ArrayList<>();

	private String startDate;
	private String endDate;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listTaskDone = new QueryTaskDone().getListTaskDone(ContextController.getInstance().getLoggedUserId());
		startDate = listTaskDone.get(0).getDate();
		endDate = listTaskDone.get(listTaskDone.size() - 1).getDate();
		btnLoadChart1.setOnAction(e -> clickLoadChart1());
		for (int i = 0; i < listTaskDone.size(); i++) {
			cbDate1.getItems().add(listTaskDone.get(i).getDate());
		}
		cbDate1.valueProperty().addListener((ov, oldValue, newValue) -> {
			for (int i = 0; i < listTaskDone.size(); i++) {
				if (listTaskDone.get(i).getDate().equals(newValue)) {
					cbDate2.getItems().clear();
					for (int j = i + 1; j < listTaskDone.size(); j++) {
						cbDate2.getItems().add(listTaskDone.get(j).getDate());
					}
					break;
				}
			}
		});

		btnLoadChart2.setOnAction(e -> clickLoadChart2(cbDate1.getValue(), cbDate2.getValue()));
	}

	private void clickLoadChart2(String date1, String date2) {
		String currentDate = date1;
		int index1 = 0, index2 = 0;
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		for (int i = 0; i < listTaskDone.size(); i++) {
			if (listTaskDone.get(i).getDate().equals(date1)) {
				index1 = i;
			}
			if (listTaskDone.get(i).getDate().equals(date2)) {
				index2 = i;
			}
		}
		while (index1 <= index2) {
			if (currentDate.equals(listTaskDone.get(index1).getDate())) {
				series.getData().add(new XYChart.Data<>(listTaskDone.get(index1).getDate(),
						listTaskDone.get(index1).getTotalTaskDone()));
				currentDate = DateIncrementer.addOneDay(currentDate);
				index1 += 1;
			} else {
				series.getData().add(new XYChart.Data<>(currentDate, 0));
				currentDate = DateIncrementer.addOneDay(currentDate);
			}
		}
		lineChart.getData().clear();
		lineChart.getData().add(series);
	}

	private void clickLoadChart1() {
		String currentDate = startDate;
		int indexList = 0;
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		while (indexList < listTaskDone.size()) {
			if (currentDate.equals(listTaskDone.get(indexList).getDate())) {
				series.getData().add(new XYChart.Data<>(listTaskDone.get(indexList).getDate(),
						listTaskDone.get(indexList).getTotalTaskDone()));
				currentDate = DateIncrementer.addOneDay(currentDate);
				indexList += 1;
			} else {
				series.getData().add(new XYChart.Data<>(currentDate, 0));
				currentDate = DateIncrementer.addOneDay(currentDate);
			}
		}
		lineChart.getData().clear();
		lineChart.getData().add(series);
	}

}
