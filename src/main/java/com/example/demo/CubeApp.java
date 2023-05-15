package com.example.demo;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class CubeApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a new box shape
        Box box = new Box(100, 100, 100);

        // Set the material and color for the box
        PhongMaterial material = new PhongMaterial(Color.ORANGE);
        box.setMaterial(material);

        // Add the box to the scene graph
        Group root = new Group();
        root.getChildren().add(box);

        // Create a new scene with the box
        Scene scene = new Scene(root, 800, 600, true);

        // Set the camera position for the scene
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-500);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        scene.setCamera(camera);

        // Show the stage with the scene
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
