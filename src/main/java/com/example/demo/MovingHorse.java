package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovingHorse extends Application {

    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;

    private static final int HORSE_WIDTH = 200;
    private static final int HORSE_HEIGHT = 200;

    private static final int HORSE_SPEED = 5;

    private Image horseImage;
    private ImageView horseImageView;

    @Override
    public void start(Stage stage) {
        // Load horse image
        horseImage = new Image(getClass().getResourceAsStream("horse.png"));
        horseImageView = new ImageView(horseImage);
        horseImageView.setFitWidth(HORSE_WIDTH);
        horseImageView.setFitHeight(HORSE_HEIGHT);
        horseImageView.setLayoutX(SCENE_WIDTH / 2 - HORSE_WIDTH / 2);
        horseImageView.setLayoutY(SCENE_HEIGHT / 2 - HORSE_HEIGHT / 2);

        // Create animation to move horse
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            horseImageView.setLayoutX(horseImageView.getLayoutX() + HORSE_SPEED);
            if (horseImageView.getLayoutX() > SCENE_WIDTH) {
                horseImageView.setLayoutX(-HORSE_WIDTH);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Create scene with horse
        Pane pane = new Pane();
        pane.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
        pane.getChildren().add(horseImageView);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
