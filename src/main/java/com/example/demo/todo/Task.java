package com.example.demo.todo;

public class Task {
    String name;
    String description;
    Boolean isCompleted;

    public Task(String name, String description){
        this.name = name;
        this.description = description;
        isCompleted = false;
    }

}
