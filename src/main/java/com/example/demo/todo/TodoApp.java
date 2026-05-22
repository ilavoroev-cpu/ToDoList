package com.example.demo.todo;

import javafx.application.Application;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;


import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TodoApp extends Application {
    private static HBox tasks = new HBox();
    private static VBox page = new VBox();
    private static ArrayList<VBox> pages = new ArrayList<>();
    private static VBox registrationtaskmenu = new VBox();
    private static HashMap<Task, VBox> mapPicture = new HashMap<>();
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        VBox other = new VBox();
        InitRegistrationTaskMenu(other);
        root.setStyle("-fx-background-color: black;");

        HBox buttonContainer = new HBox(20);
        buttonContainer.setId("buttonContainer");
        buttonContainer.setAlignment(Pos.CENTER);

        Button addTask = new Button("Добавить задачу");
        addTask.setOnAction(event -> {
            registrationtaskmenu.setVisible(true);
            other.setVisible(false);
            other.setDisable(true);


        });


        buttonContainer.getChildren().addAll(addTask);
        buttonContainer.setStyle("-fx-padding: 20px; -fx-background-color: lightgray;");
        other.getChildren().addAll(buttonContainer);

        page.getChildren().add(tasks);
        other.getChildren().add(page);


        root.getChildren().addAll(other,registrationtaskmenu);




        Scene scene = new Scene(root, 1000, 600);

        stage.setScene(scene);
        stage.show();
    }


    public static void InitRegistrationTaskMenu(VBox other){


        registrationtaskmenu.setStyle("-fx-background-color: grey; -fx-max-width: 400px; -fx-padding: 20px; -fx-border-radius: 20px;" +
                "-fx-background-radius: 20px;");
        registrationtaskmenu.setVisible(false);
        registrationtaskmenu.setSpacing(15);



        registrationtaskmenu.setTranslateX(300);
        TextArea description = new TextArea();
        description.setPromptText("Введите описание");
        description.setFont(new Font(14));
        description.setMinHeight(150);
        description.setId("description");


        TextField name = new TextField();
        name.setPromptText("Введите название задачи");
        name.setMinHeight(50);
        name.setFont(new Font(20));
        name.setId("name");




        HBox forButton = new HBox(20);

        forButton.setAlignment(Pos.CENTER);
        Button confirmation = new Button("Создать задачу");
        confirmation.setOnAction(event -> {

            other.setDisable(false);
            other.setVisible(true);

            Task task = new Task(name.getText(), description.getText());
            VBox pictureTask = createTask(name.getText());
            tasks.getChildren().add(pictureTask);


            mapPicture.put(task, pictureTask);


            registrationtaskmenu.setVisible(false);


            name.setText("");
            description.setText("");


            if (page.getChildren().size() == 1 && tasks.getChildren().size() == 7){
                pages.add(page);
                other.getChildren().remove(page);
                page = new VBox();
                other.getChildren().add(page);
            }
            if (tasks.getChildren().size() == 7){
                tasks = new HBox(pictureTask);
                page.getChildren().add(tasks);
            }

        });

        Button cancel = new Button("Отмена");
        cancel.setOnAction(event -> {
            registrationtaskmenu.setVisible(false);
            HBox buttonContainer = (HBox) other.lookup("#buttonContainer");
            if (buttonContainer != null) {
                other.setVisible(true);
                other.setDisable(false);
            }
        });

        forButton.getChildren().addAll(confirmation, cancel);
        registrationtaskmenu.getChildren().addAll(name, description, forButton);


    }

    public static VBox createTask(String name){
        VBox pictureTask = new VBox();
        pictureTask.setMinSize(150, 100);
        pictureTask.setPrefSize(150, 100);
        pictureTask.setMaxSize(150, 100);

        pictureTask.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-background-radius: 5px;" +
                "-fx-border-radius: 5px");
        Label taskName = new Label(name);
        pictureTask.getChildren().add(taskName);

        return pictureTask;
    }
}
