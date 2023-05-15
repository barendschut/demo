package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MethaneSp3 extends Application {
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






        // Create a Phong material with a green color
        PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.GREEN);

        // Create a Phong material with a gray color
        PhongMaterial grayMaterial = new PhongMaterial();
        grayMaterial.setDiffuseColor(Color.GRAY);

        // Create a cylinder to represent the carbon atom
        Cylinder carbon = new Cylinder(2, 10);
        carbon.setMaterial(grayMaterial);

        // Create four boxes to represent the hydrogen atoms
        Box hydrogen1 = new Box(2, 2, 2);
        hydrogen1.setMaterial(greenMaterial);
        hydrogen1.setTranslateX(-5);

        Box hydrogen2 = new Box(2, 2, 2);
        hydrogen2.setMaterial(greenMaterial);
        hydrogen2.setTranslateX(5);

        Box hydrogen3 = new Box(2, 2, 2);
        hydrogen3.setMaterial(greenMaterial);
        hydrogen3.setTranslateZ(-5);

        Box hydrogen4 = new Box(2, 2, 2);
        hydrogen4.setMaterial(greenMaterial);
        hydrogen4.setTranslateZ(5);

        // Add the atoms to the root group
        root.getChildren().addAll(carbon, hydrogen1, hydrogen2, hydrogen3, hydrogen4);

        // Set the rotation axis and angle for the sp3 hybridization
        Rotate rotationX = new Rotate(109.5, Rotate.X_AXIS);
        Rotate rotationY = new Rotate(45, Rotate.Y_AXIS);

        // Apply the rotation to each atom
        carbon.getTransforms().addAll(rotationX, rotationY);
        hydrogen1.getTransforms().addAll(rotationX, rotationY);
        hydrogen2.getTransforms().addAll(rotationX, rotationY);
        hydrogen3.getTransforms().addAll(rotationX, rotationY);
        hydrogen4.getTransforms().addAll(rotationX, rotationY);








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