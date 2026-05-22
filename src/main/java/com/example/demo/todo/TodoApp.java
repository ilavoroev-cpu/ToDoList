package com.example.demo.todo;

import javafx.application.Application;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;


import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javafx.stage.Stage;

import java.io.IOException;

public class TodoApp extends Application {
    public static VBox registrationtaskmenu = new VBox();
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
            buttonContainer.setVisible(false);
            other.setDisable(true);

        });




        buttonContainer.getChildren().addAll(addTask);
        buttonContainer.setStyle("-fx-padding: 20px; -fx-background-color: lightgray;");
        other.getChildren().addAll(buttonContainer);





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

            //создание задачи и отображение

            other.setDisable(false);
        });

        Button cancel = new Button("Отмена");
        cancel.setOnAction(event -> {
            registrationtaskmenu.setVisible(false);
            HBox buttonContainer = (HBox) other.lookup("#buttonContainer");
            if (buttonContainer != null) {
                buttonContainer.setVisible(true);
                other.setDisable(false);
            }
        });

        forButton.getChildren().addAll(confirmation, cancel);
        registrationtaskmenu.getChildren().addAll(name, description, forButton);


    }






}
