package com.example.demo.todo;

import java.util.Objects;

public class Task {
    String name;
    String description;
    Boolean isCompleted;

    public Task(String name, String description){
        this.name = name;
        this.description = description;
        isCompleted = false;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) && Objects.equals(description, task.description) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

}
