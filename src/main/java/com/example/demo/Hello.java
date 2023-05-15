package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Hello extends Application {
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



        // Define the positions of the methane atoms
        double[] positions = {
                0, 0, 0,     // carbon
                0, 1, 0,     // hydrogen 1
    0.943f, -0.333f, 0,   // hydrogen 2
    -0.471f, -0.333f, 0.816f, // hydrogen 3
    -0.471f, -0.333f, -0.816f // hydrogen 4
            };

        // Create the methane atoms as spheres
        PhongMaterial carbonMaterial = new PhongMaterial(Color.GRAY);
        carbonMaterial.setSpecularColor(Color.WHITE);
        carbonMaterial.setSpecularPower(20);
        Sphere carbon = new Sphere(1.0, 32);
        carbon.setMaterial(carbonMaterial);
        carbon.setDrawMode(DrawMode.FILL);
        carbon.setCullFace(CullFace.BACK);
        carbon.getTransforms().add(new Rotate(19.5, Rotate.Y_AXIS));

        PhongMaterial hydrogenMaterial = new PhongMaterial(Color.WHITE);
        hydrogenMaterial.setSpecularColor(Color.WHITE);
        hydrogenMaterial.setSpecularPower(20);
        Sphere[] hydrogens = new Sphere[4];
        for (int i = 0; i < hydrogens.length; i++) {
            hydrogens[i] = new Sphere(0.3, 32);
            hydrogens[i].setMaterial(hydrogenMaterial);
            hydrogens[i].setDrawMode(DrawMode.FILL);
            hydrogens[i].setCullFace(CullFace.BACK);
            hydrogens[i].getTransforms().add(new Rotate(19.5, Rotate.Y_AXIS));
        }

        // Place the methane atoms in their respective positions
        carbon.setTranslateX(positions[0]);
        carbon.setTranslateY(positions[1]);
        carbon.setTranslateZ(positions[2]);

        hydrogens[0].setTranslateX(positions[3]);
        hydrogens[0].setTranslateY(positions[4]);
        hydrogens[0].setTranslateZ(positions[5]);

        hydrogens[1].setTranslateX(positions[6]);
        hydrogens[1].setTranslateY(positions[7]);
        hydrogens[1].setTranslateZ(positions[8]);

        hydrogens[2].setTranslateX(positions[9]);
        hydrogens[2].setTranslateY(positions[10]);
        hydrogens[2].setTranslateZ(positions[11]);

        hydrogens[3].setTranslateX(positions[12]);
        hydrogens[3].setTranslateY(positions[13]);
        hydrogens[3].setTranslateZ(positions[14]);

        // Add the methane atoms to the root group
        root.getChildren().addAll(carbon, hydrogens[0],
                hydrogens[1], hydrogens[2], hydrogens[3]);



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