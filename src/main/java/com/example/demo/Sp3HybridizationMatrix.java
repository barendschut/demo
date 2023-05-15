package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Sp3HybridizationMatrix extends Application {

    @Override
    public void start(Stage primaryStage) {
        int numRows = 3;
        int numCols = 3;
        double circleRadius = 30;

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Circle circle = new Circle(circleRadius, Color.WHITE);
                circle.setStroke(Color.BLACK);
                circle.setStrokeWidth(2);

                if ((row == 1 && col == 1) || (row == 0 && col == 2) || (row == 2 && col == 0)) {
                    circle.setFill(Color.YELLOW);
                } else {
                    circle.setFill(Color.GRAY);
                }

                gridPane.add(circle, col, row);
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
