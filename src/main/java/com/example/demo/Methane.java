package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Methane extends Application {

    private static final double BOX_SIZE = 40;
    private static final double CYLINDER_RADIUS = 10;
    private static final double CYLINDER_HEIGHT = 60;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, true);

        // Create a perspective camera to view the 3D scene
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-300);
        scene.setCamera(camera);

        // Add the carbon atom (center of the tetrahedron)
        PhongMaterial carbonMaterial = new PhongMaterial();
        carbonMaterial.setDiffuseColor(Color.BLACK);
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

        primaryStage.setTitle("Methane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
