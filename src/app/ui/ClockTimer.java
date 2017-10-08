package app.ui;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ClockTimer extends AnchorPane{
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");
	private LocalTime time;
	private Timeline timeline;
	private double process;
	private double delta;
	private boolean isPlay = true;
	
	private Circle circle;
	private Arc arc;
	private Pane pauseBtn;
	private Pane doneBtn;
	private Label timerLabel;
	private ImageView pauseImg;
	private ImageView doneImg;
	private ImageView playImg;
	
	public ClockTimer(LocalTime time) {
		this.time = time;
		process = 0;
		delta = (double)360 / time.toSecondOfDay();
		
		initCircle();
		initArc();
		initImage();
		initPauseBtn();
		initDoneBtn();
		initLabel();
		
		this.getChildren().addAll(circle, arc, pauseBtn, doneBtn, timerLabel);
	}
	
	private void initCircle() {
		circle = new Circle(0, 0, 120);
		circle.setLayoutX(150);
		circle.setLayoutY(150);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.RED);
		circle.setStrokeWidth(2.75);
	}
	
	private void initArc() {
		arc = new Arc(0, 0, 120, 120, 90, 360);
		arc.setLayoutX(150);
		arc.setLayoutY(150);
		arc.setManaged(false);
		arc.setFill(Color.WHITE);
		arc.setStroke(Color.BLUE);
		arc.setStrokeWidth(3);
		arc.setType(ArcType.OPEN);
	}
	
	private void initImage() {
	
		pauseImg = new ImageView(new Image("app/resource/pause.png"));
		
		pauseImg.setFitHeight(54);
		pauseImg.setFitWidth(54);
		
		playImg = new ImageView(new Image("app/resource/play.png"));
		playImg.setFitHeight(54);
		playImg.setFitWidth(54);
		
		doneImg = new ImageView(new Image("app/resource/done.png"));
		doneImg.setFitHeight(54);
		doneImg.setFitWidth(54);
		
	}
	
	private void initPauseBtn() {
		pauseBtn = new Pane();
		pauseBtn.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		pauseBtn.setLayoutX(123);
		pauseBtn.setLayoutY(14);
		pauseBtn.getChildren().add(pauseImg);
		
		pauseBtn.setOnMouseClicked(e->onClickPause());
	}
	
	private void onClickPause() {
		if (isPlay) {
			pauseBtn.getChildren().removeAll();
			pauseBtn.getChildren().add(playImg);
			timeline.pause();
			isPlay = false;
		}else {
			pauseBtn.getChildren().removeAll();
			pauseBtn.getChildren().add(pauseBtn);
			isPlay = true;
			timeline.playFromStart();
		}
	}

	private void initDoneBtn() {
		doneBtn = new Pane();
		doneBtn.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		doneBtn.setLayoutX(123);
		doneBtn.setLayoutY(232);
		doneBtn.getChildren().add(doneImg);
	}
	
	private void initLabel() {
		String text = formatter.format(time);
		timerLabel = new Label();
		
		timerLabel.setLayoutX(90);
		timerLabel.setLayoutY(100);
		timerLabel.setPrefSize(140, 90);
		timerLabel.setTextFill(Color.RED);
		timerLabel.setFont(Font.font(50));
		timerLabel.setText(text);
	}
	
	private void updateLabel() {
		String text = formatter.format(time);
		timerLabel.setText(text);
	}
	
	public void startTimeline() {
		timeline = new Timeline(new KeyFrame(Duration.seconds(1), event ->countTime()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.playFromStart();

	}
	
	private void countTime() {
		time = time.minusSeconds(1);
		updateLabel();
		process += delta;
        arc.setLength(360 - process);
        
		if (time.equals(LocalTime.MIN)) {
			timeline.stop();
		}
	}
	
	
	
}
