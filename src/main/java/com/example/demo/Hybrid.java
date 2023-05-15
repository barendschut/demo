package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

import java.io.IOException;

public class Hybrid extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400, true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
       // Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // Create a perspective camera to view the 3D scene
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setTranslateZ(-1000.0);
        scene.setCamera(camera);

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

        // Show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}