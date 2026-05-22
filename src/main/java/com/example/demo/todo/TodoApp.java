package com.example.demo.todo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TodoApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        HBox buttonContainer = new HBox(20);


        Button addTask = new Button("Добавить задачу");

        buttonContainer.getChildren().addAll(addTask);


        root.setBottom(buttonContainer);







        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }




}
