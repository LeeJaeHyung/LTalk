package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatController implements Initializable{
	
	@FXML
	BorderPane root;
	@FXML
	HBox root_center_HBox;
	@FXML
	VBox root_Center_VBox;
	@FXML
	Button closeb;
	@FXML
	Button hideb;
	
	Stage stage = null;
	private double x = 0, y = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(Thread.currentThread().getName()+" => [ChatController] initialize() is Strating");
		closeb.setStyle("-fx-background-color:#A9BDCE");
		hideb.setStyle("-fx-background-color:#A9BDCE");
		root_Center_VBox.setStyle("-fx-background-color:#FFFFFF");
		closeb.setOnAction(event -> Platform.exit());
		stageMove();
		hiddingButton();
	}
	
	private void hiddingButton() {
		hideb.setOnAction(event -> {stage = (Stage) root.getScene().getWindow(); stage.setIconified(true);});
	}
	
	private void stageMove() {//https://ohtanja.tistory.com/90 참고함
		root_center_HBox.setOnMousePressed((event) -> {
			x = event.getSceneX();
			y = event.getSceneY();
		});

		root_center_HBox.setOnMouseDragged((event) -> {
			stage = (Stage) root.getScene().getWindow();
			stage.setX(event.getScreenX() - x);
			stage.setY(event.getScreenY() - y);
		});

		root_center_HBox.setOnMouseReleased((event) -> {
			stage = (Stage) root.getScene().getWindow();
		});
	}
	
}
