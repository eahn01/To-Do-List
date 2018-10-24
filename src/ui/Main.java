package ui;

import Model.ToDoList;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ToDoList toDoList = new ToDoList();
        toDoList.run();
    }
}
