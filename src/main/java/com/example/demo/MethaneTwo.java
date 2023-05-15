package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MethaneTwo extends Application {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private Parent createContent() throws Exception {
        Translate pivot = new Translate();
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);

        // Create and position camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                pivot,
                yRotate,
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -50)
        );

        // animate the camera position.
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(yRotate.angleProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(15),
                        new KeyValue(yRotate.angleProperty(), 360)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Build the Scene Graph
        Group root = new Group();
        root.getChildren().add(camera);





        // Set up the positions of the atoms
        float[] positions = {
                0, 0, 0,     // carbon
                0, 1, 0,     // hydrogen 1
                0.943f, -0.333f, 0,   // hydrogen 2
                -0.471f, -0.333f, 0.816f, // hydrogen 3
                -0.471f, -0.333f, -0.816f // hydrogen 4
        };

        // Set up the colors of the atoms
        Color[] colors = {
                Color.DARKGRAY,      // carbon
                Color.LIGHTGRAY,     // hydrogen 1
                Color.LIGHTGRAY,     // hydrogen 2
                Color.LIGHTGRAY,     // hydrogen 3
                Color.LIGHTGRAY      // hydrogen 4
        };

        // Create spheres for each atom and add them to the scene
        for (int i = 0; i < positions.length; i += 3) {
            Sphere sphere = new Sphere(0.5);
            sphere.setTranslateX(positions[i] * 5);
            sphere.setTranslateY(positions[i + 1] * 5);
            sphere.setTranslateZ(positions[i + 2] * 5);
            sphere.setMaterial(new PhongMaterial(colors[i / 3]));
            root.getChildren().add(sphere);
        }





        // Use a SubScene
        SubScene subScene = new SubScene(
                root,
                1000,1000,
                true,
                SceneAntialiasing.BALANCED
        );
        subScene.setFill(Color.ALICEBLUE);
        subScene.setCamera(camera);
        Group group = new Group();
        group.getChildren().add(subScene);

        return group;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
