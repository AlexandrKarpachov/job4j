package ru.job4j.pinpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PingPong extends Application {
	private static final String JOB4J = "Пинг-понг www.job4j.ru";

	@Override
	public void start(Stage stage) {
		int limitX = 300;
		int limitY = 300;
		Group group = new Group();
		Rectangle rect = new Rectangle(150, 150, 10, 10);
		group.getChildren().add(rect);
		var moving = new Thread(new RectangleMove(rect, limitY));
		moving.start();
		stage.setScene(new Scene(group, limitX, limitY));
		stage.setTitle(JOB4J);
		stage.setResizable(false);
		stage.show();
		stage.setOnCloseRequest(event -> moving.interrupt());
	}
}



