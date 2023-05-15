package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MazeGrid extends Application {

    private final int SIZE = 11;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(1);
        grid.setVgap(1);

        int center = (SIZE - 1) / 2;
        int[][] maze = new int[SIZE][SIZE];
        maze[center][center] = 1;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == center && j == center) {
                    grid.add(new Rectangle(20, 20, Color.GREEN), j, i);
                } else if (i == center && j != center) {
                    if (j % 2 == 0) {
                        maze[i][j] = 1;
                        grid.add(new Rectangle(20, 20, Color.BLACK), j, i);
                    } else {
                        grid.add(new Rectangle(20, 20, Color.RED), j, i);
                    }
                } else if (j == center && i != center) {
                    if (i % 2 == 0) {
                        maze[i][j] = 1;
                        grid.add(new Rectangle(20, 20, Color.BLACK), j, i);
                    } else {
                        grid.add(new Rectangle(20, 20, Color.BLUE), j, i);
                    }
                } else {
                    grid.add(new Rectangle(20, 20, Color.YELLOW), j, i);
                }
            }
        }

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
