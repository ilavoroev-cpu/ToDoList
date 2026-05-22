package com.example.demo.todo;

import javafx.application.Application;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;


import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TodoApp extends Application {
    private static int numPage = -1;
    private static HBox tasks = new HBox(10);
    private static VBox page = new VBox(10);
    private static VBox thisPage = new VBox();
    private static ArrayList<VBox> pages = new ArrayList<>();
    private static VBox registrationtaskmenu = new VBox();
    private static HashMap<Task, VBox> mapPicture = new HashMap<>();
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        VBox other = new VBox();

        InitRegistrationTaskMenu(other);
        root.setStyle("-fx-background-color: white;");

        HBox buttonContainer = new HBox(20);
        buttonContainer.setId("buttonContainer");
        buttonContainer.setAlignment(Pos.CENTER);

        Button addTask = new Button("Добавить задачу");
        addTask.setOnAction(event -> {
            registrationtaskmenu.setVisible(true);
            other.setVisible(false);
            other.setDisable(true);


        });


        VBox forPage = new VBox(5);

        TextField numberOfpage = new TextField();
        numberOfpage.setMaxWidth(120);
        numberOfpage.setPromptText("Введите номер...");



        Button searchPage = new Button("Открыть страницу");
        searchPage.setOnAction(event -> {
            try {
                numPage = Integer.parseInt(numberOfpage.getText());
                if ((numPage - 1 == pages.size()) && pages.size() == 0){return;}
                else if(numPage - 1 == pages.size()){

                    other.getChildren().remove(page);
                    page = thisPage;
                    other.getChildren().add(page);
                    return;
                }
                other.getChildren().remove(page);

                page = pages.get(numPage - 1);
                other.getChildren().add(page);
            }catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Неверный ввод");
                alert.showAndWait();
            }
            catch (IndexOutOfBoundsException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Неверный cтраница");
                alert.showAndWait();
            }
        });

        forPage.setStyle("-fx-padding: 20px; -fx-background-color:");
        forPage.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 5);");

        forPage.getChildren().addAll(numberOfpage, searchPage);


        buttonContainer.getChildren().addAll(addTask, forPage);
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
            if (numPage - 1 != pages.size() && numPage > 0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Перейдите на последнюю страницу");
                alert.showAndWait();
                return;
            }
            if (mapPicture.containsKey(task)){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Такая задача уже есть");
                alert.showAndWait();
                return;
            }

            VBox pictureTask = createTask(name.getText());
            tasks.getChildren().add(pictureTask);



            mapPicture.put(task, pictureTask);


            registrationtaskmenu.setVisible(false);


            name.setText("");
            description.setText("");


            if (page.getChildren().size() == 2 && tasks.getChildren().size() == 4){


                pages.add(page);
                other.getChildren().remove(page);
                page = new VBox(10);
                other.getChildren().add(page);
            }
            if (tasks.getChildren().size() == 4){
                tasks = new HBox(10);
                tasks.getChildren().add(pictureTask);
                page.getChildren().add(tasks);
            }
            thisPage = page;
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

        pictureTask.setStyle(
                "-fx-background-color: black; " +
                        "-fx-padding: 20px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0, 0, 0);"
        );
        Label taskName = new Label(name);
        taskName.setTextFill(Color.WHITE);
        pictureTask.getChildren().add(taskName);


        return pictureTask;
    }
}
