package app.controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import app.MainApp;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ListJobController {
	@FXML
	private AnchorPane navPane;

	@FXML
	private JFXHamburger navMenu;

	@FXML
	private AnchorPane mainPane;

	private ContextMenu contextMenu;

	private LoginController loginController;

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public void initialize() {
		setHome();
		createMenu();
		mainPane.getStylesheets().add("/app/resource/css/login.css");

	}

	public void setCreate() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Create.fxml"));
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
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Time.fxml"));
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
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Home.fxml"));
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
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Chart.fxml"));
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
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Goal.fxml"));
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
		MenuItem mainItem = new MenuItem("Main", new ImageView(new Image("app/resource/main.png")));
		mainItem.setOnAction(e -> setHome());

		MenuItem createItem = new MenuItem("Create Job", new ImageView(new Image("app/resource/plus.png")));
		createItem.setOnAction(e -> setCreate());

		MenuItem goalItem = new MenuItem("View Goal", new ImageView(new Image("app/resource/goal.png")));
		goalItem.setOnAction(e -> setViewGoal());

		MenuItem statisticItem = new MenuItem("Statistic", new ImageView(new Image("app/resource/chart.png")));

		statisticItem.setOnAction(e -> setStatistic());

		MenuItem logOutItem = new MenuItem("Log Out", new ImageView(new Image("app/resource/logout.png")));
		logOutItem.setOnAction(e -> setLogin());

		MenuItem exitItem = new MenuItem("Exit", new ImageView(new Image("app/resource/exit.png")));
		exitItem.setOnAction(e -> Platform.exit());

		contextMenu = new ContextMenu();
		contextMenu.getItems().addAll(mainItem, createItem, goalItem, statisticItem, logOutItem, exitItem);

		HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(navMenu);
		transition.setRate(-1);

		navMenu.setOnMouseClicked(e -> {
			if (contextMenu.isShowing()) {
				contextMenu.hide();
			}else {
				contextMenu.show(navMenu, Side.RIGHT, 0, 0);
			}
			
		});
		
		contextMenu.showingProperty().addListener(e->{
			transition.setRate(transition.getRate() * -1);
			transition.play();
		});

	}

	private void setLogin() {
		loginController.setLogin();
	}
}
