package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    Scanner scanner = new Scanner(System.in);

    public ToDoList(){
        int option;
        ItemList todoList = new ItemList();
        while (true){
            System.out.println("Would you like to: [1] Add an item   "+
                    "[2] Cross off an item   "+
                    "[3] View items to do   "+
                    "[4] View completed items");
            option = scanner.nextInt();
            if (option == 1 ) {
                addTask(todoList);
                System.out.println("Item has been added to the list");
            }
            else if (option == 2) {
                completeTask(todoList );
                System.out.println("Item has been crossed off");
            }
            else if (option == 3) {
                System.out.println("To-do List:" +todoList);
            }
        }
    }

    private void addTask(ItemList todoList) {
        System.out.println("Enter new item");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Enter due date (dd/MM/yyyy)");
        String date = scanner.nextLine();
        Item item = new Item(name, date);
        todoList.addItem(item);
    }

    private void completeTask(ItemList todoList) {
        System.out.println("Enter item to be crossed off");
        String name = scanner.nextLine();
        scanner.nextLine();
        todoList.completeItem(name);
    }
}


