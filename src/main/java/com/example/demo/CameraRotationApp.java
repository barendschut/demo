package com.example.demo;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CameraRotationApp extends Application {

    private Parent createContent() throws Exception {
        Sphere sphere = new Sphere(2.5);
        sphere.setMaterial(new PhongMaterial(Color.ANTIQUEWHITE));
        sphere.setTranslateZ(7);
        sphere.setTranslateX(4);


        Sphere sphere2 = new Sphere(3.5);
        sphere2.setMaterial(new PhongMaterial(Color.RED));
        sphere2.setTranslateZ(16);
        sphere2.setTranslateX(-8);


        Sphere sphere3 = new Sphere(3);
        sphere3.setMaterial(new PhongMaterial(Color.BLUE));
        sphere3.setTranslateZ(8);
        sphere3.setTranslateX(-8);


        Box box = new Box(5, 5, 5);
        box.setMaterial(new PhongMaterial(Color.BROWN));

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
        root.getChildren().add(box);
        root.getChildren().add(sphere);
        root.getChildren().add(sphere2);
        root.getChildren().add(sphere3);
        // set the pivot for the camera position animation base upon mouse clicks on objects
        root.getChildren().stream()
                .filter(node -> !(node instanceof Camera))
                .forEach(node ->
                        node.setOnMouseClicked(event -> {
                            pivot.setX(node.getTranslateX());
                            pivot.setY(node.getTranslateY());
                            pivot.setZ(node.getTranslateZ());
                        })
                );


/*





        // Create a tetrahedron mesh
        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().addAll(
                0f, 0f, 0f, // point 0
                1f, 0f, 0f, // point 1
                0.5f, 0.87f, 0f, // point 2
                0.5f, 0.29f, 0.82f // point 3
        );
        mesh.getTexCoords().addAll(0f, 0f);
        mesh.getFaces().addAll(
                0, 0, 1, 0, 2, 0, // triangle 0
                0, 0, 2, 0, 3, 0, // triangle 1
                0, 0, 3, 0, 1, 0, // triangle 2
                3, 0, 2, 0, 1, 0 // triangle 3
        );

        // Create a mesh view to display the tetrahedron
        MeshView meshView = new MeshView(mesh);
        meshView.setDrawMode(DrawMode.FILL);
        meshView.setMaterial(new PhongMaterial(Color.RED));

        // Add the mesh view to the scene
        root.getChildren().add(meshView);

*/










        // Use a SubScene
        SubScene subScene = new SubScene(
                root,
                600,600,
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