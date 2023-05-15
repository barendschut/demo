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

public class MethaneTetrahedron extends Application {

    private static final int SCENE_WIDTH = 2000;
    private static final int SCENE_HEIGHT = 2000;
    private static final double CAMERA_INITIAL_DISTANCE = -20;
    private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;

    private Group moleculeGroup;
    private PerspectiveCamera camera;
    private final double[] angles = new double[3];

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








        Sphere carbonAtom = createAtom(2, Color.GRAY);
        carbonAtom.setTranslateX(0);
        carbonAtom.setTranslateY(0);
        carbonAtom.setTranslateZ(0);

        Sphere hydrogenAtom1 = createAtom(1, Color.LIGHTBLUE);
        hydrogenAtom1.setTranslateX(3.5);
        hydrogenAtom1.setTranslateY(0);
        hydrogenAtom1.setTranslateZ(0);

        Sphere hydrogenAtom2 = createAtom(1, Color.LIGHTBLUE);
        hydrogenAtom2.setTranslateX(-1.75);
        hydrogenAtom2.setTranslateY(3.03);
        hydrogenAtom2.setTranslateZ(0);

        Sphere hydrogenAtom3 = createAtom(1, Color.LIGHTBLUE);
        hydrogenAtom3.setTranslateX(-1.75);
        hydrogenAtom3.setTranslateY(-1.01);
        hydrogenAtom3.setTranslateZ(3.03);

        Sphere hydrogenAtom4 = createAtom(1, Color.LIGHTBLUE);
        hydrogenAtom4.setTranslateX(-1.75);
        hydrogenAtom4.setTranslateY(-1.01);
        hydrogenAtom4.setTranslateZ(-3.03);

        moleculeGroup = new Group();
        moleculeGroup.getChildren().addAll(carbonAtom, hydrogenAtom1, hydrogenAtom2, hydrogenAtom3, hydrogenAtom4);

        /*
        camera = new PerspectiveCamera(true);
        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
*/


        angles[0] = CAMERA_INITIAL_X_ANGLE;
        angles[1] = CAMERA_INITIAL_Y_ANGLE;



        // Use a SubScene
        SubScene subScene = new SubScene(
                moleculeGroup,
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

    private Sphere createAtom(double radius, Color color) {
        Sphere atom = new Sphere(radius);
        atom.setMaterial(new PhongMaterial(color));
        return atom;
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
