package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ListJobController implements Initializable {
	@FXML
	private AnchorPane navPane;

	@FXML
	private JFXHamburger navMenu;

	@FXML

	private AnchorPane mainPane;

	@FXML
	private JFXDrawer drawer;

	private ContextMenu contextMenu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setHome();
		createMenu();

	}

	public void setCreate() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Create.fxml"));
			Parent root = loader.load();
			CreateController createController = loader.getController();
			createController.setListJobController(this);
			mainPane.getChildren().clear();
			mainPane.getChildren().add((Node) root);
			setEffect((Node) root);
			navMenu.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPlay() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Time.fxml"));
			Parent root = loader.load();
			TimeController timeController = loader.getController();
			timeController.setListJobController(this);
			mainPane.getChildren().clear();
			mainPane.getChildren().add((Node) root);
			setEffect((Node) root);
			navMenu.setVisible(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setHome() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Home.fxml"));
			Parent root = loader.load();
			HomeController homeController = loader.getController();
			homeController.setListJobController(this);
			mainPane.getChildren().clear();
			mainPane.getChildren().add((Node) root);
			setEffect((Node) root);
			navMenu.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setStatistic() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Chart.fxml"));
			Parent root = loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add((Node) root);
			setEffect((Node) root);
			navMenu.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setViewGoal() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Goal.fxml"));
			Parent root = loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add((Node) root);
			setEffect((Node) root);
			navMenu.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setEffect(Node node) {
		FadeTransition ft = new FadeTransition(Duration.millis(1500));
		ft.setNode(node);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}

	private void createMenu() {
//		MenuItem mainItem = new MenuItem("Main", new ImageView(new Image("app/resource/main.png")));
		MenuItem mainItem = new MenuItem("Main");

		mainItem.setOnAction(e -> setHome());

//		MenuItem createItem = new MenuItem("Create Job", new ImageView(new Image("../resource/plus.png")));
		MenuItem createItem = new MenuItem("Create Job");

		createItem.setOnAction(e -> setCreate());
		
		MenuItem goalItem = new MenuItem("View Goal");
		goalItem.setOnAction(e -> setViewGoal());

//		MenuItem statisticItem = new MenuItem("Statistic", new ImageView(new Image("../resource/chart.png")));
		MenuItem statisticItem = new MenuItem("Statistic");

		statisticItem.setOnAction(e -> setStatistic());

//		MenuItem logOutItem = new MenuItem("Log Out", new ImageView(new Image("../resource/logout.png")));
		MenuItem logOutItem = new MenuItem("Log Out");

		logOutItem.setOnAction(e -> switchScene("Login.fxml"));

//		MenuItem exitItem = new MenuItem("Exit", new ImageView(new Image("../resource/exit.png")));
		MenuItem exitItem = new MenuItem("Exit");

		exitItem.setOnAction(e -> Platform.exit());

		contextMenu = new ContextMenu();
		contextMenu.getItems().addAll(mainItem, createItem, goalItem, statisticItem, logOutItem, exitItem);

		navMenu.setOnMouseClicked(e -> contextMenu.show(navMenu, Side.RIGHT, 0, 0));
	}

	private void switchScene(String newScence) {
		navMenu.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			String desScene = "../view/" + newScence;
			Parent root = FXMLLoader.load(getClass().getResource(desScene));
			Scene scene = new Scene(root);

			stage.getIcons().add(new Image("/app/resource/tomato.png"));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.sizeToScene();

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
