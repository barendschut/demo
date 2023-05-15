package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CameraRotationAppMethane extends Application {

    private static final double BOX_SIZE = 4;
    private static final double CYLINDER_RADIUS = 1;
    private static final double CYLINDER_HEIGHT = 6;
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






        // Add the carbon atom (center of the tetrahedron)
        PhongMaterial carbonMaterial = new PhongMaterial();
        carbonMaterial.setDiffuseColor(Color.BLUE);
        Box carbonAtom = new Box(BOX_SIZE, BOX_SIZE, BOX_SIZE);
        carbonAtom.setMaterial(carbonMaterial);
        root.getChildren().add(carbonAtom);

        // Add the hydrogen atoms
        PhongMaterial hydrogenMaterial = new PhongMaterial();
        hydrogenMaterial.setDiffuseColor(Color.WHITE);
        for (int i = 0; i < 4; i++) {
            Box hydrogenAtom = new Box(BOX_SIZE / 2, BOX_SIZE / 2, BOX_SIZE / 2);
            hydrogenAtom.setMaterial(hydrogenMaterial);
            // Position the hydrogen atom at the end of the bond
            hydrogenAtom.setTranslateX(CYLINDER_HEIGHT * Math.sin(Math.toRadians(i * 90)));
            hydrogenAtom.setTranslateZ(CYLINDER_HEIGHT * Math.cos(Math.toRadians(i * 90)));
            // Rotate the hydrogen atom to align with the bond
            hydrogenAtom.getTransforms().add(new Rotate(i * 90, new Point3D(0, 1, 0)));
            root.getChildren().add(hydrogenAtom);

            // Add the bond between the carbon and hydrogen atoms
            PhongMaterial bondMaterial = new PhongMaterial();
            bondMaterial.setDiffuseColor(Color.GRAY);
            Cylinder bond = new Cylinder(CYLINDER_RADIUS, CYLINDER_HEIGHT);
            bond.setMaterial(bondMaterial);
            // Position the bond between the carbon and hydrogen atoms
            bond.setTranslateX(CYLINDER_HEIGHT / 2 * Math.sin(Math.toRadians(i * 90)));
            bond.setTranslateZ(CYLINDER_HEIGHT / 2 * Math.cos(Math.toRadians(i * 90)));
            // Rotate the bond to align with the hydrogen atom
            bond.getTransforms().add(new Rotate(i * 90, new Point3D(0, 1, 0)));
            root.getChildren().add(bond);
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