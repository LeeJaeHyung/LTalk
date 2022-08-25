package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import module.Login;
import module.Member;

public class MainController implements Initializable {
	
	@FXML
	Button button;
	@FXML
	Button closeb;
	@FXML
	Button hideb;
	@FXML
	TextField id;
	@FXML
	PasswordField pass;
	@FXML
	AnchorPane acp;

	private double x = 0;
	private double y = 0;
	private Stage stage;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

//		button.setOnAction(event -> System.out.println("람다로 실행됨"));

//		button.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				System.out.println("정석으로 이벤트 적용됨");
//				
//			}
//		});
//		bp.setBackground(new Background(new BackgroundFill(new Color(255, 235, 51, 0), null, null)));

		acp.setStyle("-fx-background-color:#FFEB33");
		closeb.setStyle("-fx-background-color:#FFEB33");
		hideb.setStyle("-fx-background-color:#FFEB33");
		button.setStyle("-fx-background-color:#F6F6F6");
		closeb.setOnAction(event -> Platform.exit());
		stageMove();
		hiddingButton();

//		acp.setBackground(new Background(new BackgroundFill(new Color(255, 235, 51, 0), null, null)));

	}

	public void handleBtn1Action(ActionEvent event) throws IOException {
		System.out.println(Thread.currentThread().getName()+" => [MainController] Login.login() 호출 di: ["+id.getText() + "] password: [ ****** ]");
		Member member = Login.login(id.getText(), pass.getText());
		if(member != null) {
			afterLogin(member);
		}
	}

	public void findId(MouseEvent event) {
		System.out.println("아이디 찾기 Event");
	}

	public void findPassword(MouseEvent event) {
		System.out.println("비밀번호 찾기 Event");
	}

	/* 로그인 화면 이동 */
	private void stageMove() {//https://ohtanja.tistory.com/90 참고함
		acp.setOnMousePressed((event) -> {
			x = event.getSceneX();
			y = event.getSceneY();
		});

		acp.setOnMouseDragged((event) -> {
			stage = (Stage) acp.getScene().getWindow();
			stage.setX(event.getScreenX() - x);
			stage.setY(event.getScreenY() - y);
		});

		acp.setOnMouseReleased((event) -> {
			stage = (Stage) acp.getScene().getWindow();
		});
	}
	
	public void afterLogin(Member member) throws IOException {
		System.out.println(Thread.currentThread().getName()+" => [MainController] afterLogin() is Started");
		stage = (Stage)acp.getScene().getWindow();//  현제 스테이지 객체불러옴 
		stage.setUserData(member);
		Parent afterlogin = FXMLLoader.load(getClass().getResource("/view/List.fxml"));
		Scene listScene = new Scene(afterlogin);
		stage.setScene(listScene);
		stage.setResizable(true);
		stage.show();
	}
	
	private void hiddingButton() {
		hideb.setOnAction(event -> {stage = (Stage) acp.getScene().getWindow(); stage.setIconified(true);});
	}
}
