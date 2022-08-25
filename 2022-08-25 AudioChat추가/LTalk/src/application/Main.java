package application;
	
import java.io.IOException;
import java.net.URL;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
	public Main() {
		System.out.println(Thread.currentThread().getName()+": Main()생성자 호출");
	}
	
	@Override
	public void init() {// Application 매개 데이터 처리 할때 -- javaFX Launcher<< Thread에서 데이터 처리 함으로 해당 쓰레드에서 UI 조작시 Exception 발생
		System.out.println(Thread.currentThread().getName()+": init() 호출");
	}
	
	@Override
	public void start(Stage primaryStage) { //실행 될때 처리
		System.out.println(Thread.currentThread().getName()+": start() 호출");
		
//		VBox root = new VBox();//컨테이너 생성
//		root.setPrefWidth(400);
//		root.setPrefHeight(600);
//		root.setAlignment(Pos.CENTER);//중앙에 배치
//		root.setSpacing(20);//간격 20
//		
//		Label label = new Label();//라벨 생성 글자를 표현하는 component
//		label.setText("LTalk - Gesta non verba");//Label글자 생성
//		label.setFont(new Font(34));//Label폰트 설정
//		
//		Button button = new Button();//버튼 생성
//		button.setText("Start");//Button 글자 생성
//		button.setOnAction(event -> Platform.exit());//액션 이벤트 설정
//		//Platform.exit();종료
//		
//		//root.getChildren(); => ObservableList 객체 반환
//		root.getChildren().add(label);//root컨테이너에 Label 추가
//		root.getChildren().add(button);//root컨테이너에 Button 추가
//		
//		Scene scene = new Scene(root);// 씬 생성
		//Scene에는 Container 
	

		try {
			URL path = getClass().getResource("/view/Main.fxml");
		FXMLLoader loader = new FXMLLoader(path);
		Parent parent = (Parent)loader.load();
		System.out.println("Main.start() in "+getClass().getResource("/view/Main.fxml")+" is Loading");
		Scene newScene = new Scene(parent);// Scene에 parent대입  <<< Scene의 생성자 rootContainer 대입  위에 코드에서 루트 컨테이너 하위에 있는 모든것을 객체로 만들어 반환 해주기 때문에 
		//Parent에 들어갈수 있음
		primaryStage.getIcons().add(new Image("file:src/images/icon.png"));
		primaryStage.setTitle("LTalk");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(newScene);//스테이지에 Scene 추가
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(event -> System.out.println("종료 됨"));//닫기 버튼을 눌렀을때 발생하는 이벤트 stop 보다 먼저 실행됨
		primaryStage.show();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void stop() {// Application 종료 직전 마무리 작업처리
		System.out.println(Thread.currentThread().getName()+": stop() 호출");
	}
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+": launch() 호출");
		launch(args);
	}
}
