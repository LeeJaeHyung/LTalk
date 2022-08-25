package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.ChatMain;
import application.LTalkServer;
import application.SoundClient;
import application.SoundSender;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import module.Member;

public class ListController implements Initializable {

	@FXML
	BorderPane root;
	@FXML
	VBox root_Left_VBox;
	@FXML
	HBox root_center_HBox;
	@FXML
	Button closeb;
	@FXML
	Button hideb;
	@FXML
	Button friendButton;
	@FXML
	Button chatListButton;

	private double x = 0;
	private double y = 0;
	private Stage stage;
	private String nowView = "friend";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(Thread.currentThread().getName() + " => [ListController] initialize() is Strating");
		closeb.setStyle("-fx-background-color:#FFFFFF");
		hideb.setStyle("-fx-background-color:#FFFFFF");
		root.setStyle("-fx-background-color:#FFFFFF");
		root_Left_VBox.setStyle("-fx-background-color:#ECECED");
		chatListButton.setStyle("-fx-background-color:#ECECED");
		friendButton.setStyle("-fx-background-color:#ECECED");
		hideb.setStyle("-fx-background-color:#FFFFFF");
		closeb.setOnAction(event -> Platform.exit());
		stageMove();
		stageMove2();
//		maxWindow();
		hiddingButton();
//		resizeTrue();

	}

	private void stageMove() {// https://ohtanja.tistory.com/90 참고함
		root_Left_VBox.setOnMousePressed((event) -> {
			x = event.getSceneX();
			y = event.getSceneY();
		});

		root_Left_VBox.setOnMouseDragged((event) -> {
			stage = (Stage) root.getScene().getWindow();
			stage.setX(event.getScreenX() - x);
			stage.setY(event.getScreenY() - y);
		});

		root_Left_VBox.setOnMouseReleased((event) -> {
			stage = (Stage) root.getScene().getWindow();
		});
	}

	private void stageMove2() {// https://ohtanja.tistory.com/90 참고함
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

	private void hiddingButton() {
		hideb.setOnAction(event -> {
			stage = (Stage) root.getScene().getWindow();
			stage.setIconified(true);
		});
	}

//	private void maxWindow() { //최대화
//		
//		maxb.setOnAction(event -> {
//			stage = (Stage) acp.getScene().getWindow();
//			stage.setMaximized(true);
//		});
//		
//	}

	public void changeFrame(ActionEvent e) {
		Button target = (Button) e.getSource();
		String data = target.getUserData().toString();
		System.out.println("[ListController] changeFrame() is started UserData: " + data);
		if (!nowView.equals(data)) {// 현제 표시된 화면과 같지 않을때
			switch (data) {
			case "friend":
				System.out.println("friend변경");
				stage = (Stage) root.getScene().getWindow();
				Member member = (Member) stage.getUserData();
				System.out.println(member.getId() + "님이 로그인 하셨습니다.");
				nowView = data;
				break;
			case "talk":
				System.out.println("talk변경");
				nowView = data;
				break;
			}
		}
	}

	public void openChat(ActionEvent e) throws IOException {
		System.out.println("[ListController] openChat() is Started");
		Stage chatStage = new Stage();
		stage = (Stage) root.getScene().getWindow();
		chatStage.setUserData(stage.getUserData());
		new ChatMain().start(chatStage);
	}

	public void openServer(ActionEvent e) throws IOException {
		System.out.println("[ListController] openServer() is Started");
		Stage serverStage = new Stage();
		stage = (Stage) root.getScene().getWindow();
		serverStage.setUserData(stage.getUserData());
		new LTalkServer().start(serverStage);
	}

	public void openVoiceChat(ActionEvent e) {
		System.out.println("[ListController] openVoiceChat() is Started");
		Thread scThread = new Thread( () -> {
			SoundClient.start();
		});
		
		System.out.println("[ListController] openVoiceChat() Create scThread");
		
		Thread ssThread = new Thread( () -> {
			SoundSender.start();
		});
		
		System.out.println("[ListController] openVoiceChat() Create ssThread");
		
		scThread.start();
		System.out.println("[ListController] openVoiceChat() in scThread.start() is Started");
		ssThread.start();
		System.out.println("[ListController] openVoiceChat() in ssThread.start() is Started");
		
		
	}

	public void openTestChat(ActionEvent e) throws IOException {
		System.out.println("[ListController] openTestChat() is Started");
		Stage chatStage = new Stage();
		Parent chat;

		chat = FXMLLoader.load(getClass().getResource("/view/Chat.fxml"));
		Scene chatScene = new Scene(chat);
		chatStage.setScene(chatScene);
		chatStage.setResizable(true);
		chatStage.initStyle(StageStyle.UNDECORATED);
		chatStage.show();

	}

}
