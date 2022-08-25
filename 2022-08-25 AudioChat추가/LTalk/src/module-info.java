module LTalk {
	requires javafx.controls;
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml, javafx.controls, javafx.base, javafx.media, java.sql;
	opens controller to javafx.graphics, javafx.fxml, javafx.controls, javafx.base, javafx.media, java.sql;
}
