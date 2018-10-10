package Model;

import java.io.IOException;
import java.util.Scanner;

public class ToDoList {
    Scanner scanner = new Scanner(System.in);
    ItemList todoList = new ItemList();

    public void run() throws IOException {
        int option;
        loadList(todoList);
        while (true){
            System.out.println("Would you like to: [1] Add an item   "+
                    "[2] Cross off an item   "+
                    "[3] View items to do   "+
                    "[4] View completed items   "+
                    "[5] Quit");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1 ) {
                addTask(todoList);
                System.out.println("Item has been added to the list");
            }
            else if (option == 2) {
                completeTask(todoList );
                System.out.println("Item has been crossed off");
            }
            else if (option == 3) {
                viewCurrent(todoList);
            }
            else if (option == 4) {
                viewCompleted(todoList);
            }
            else if (option == 5) {
                break;
            }
        }
        saveList(todoList);
    }

    private void addTask(ItemList todoList) {
        System.out.println("Enter new item");
        String name = scanner.nextLine();
        System.out.println("Enter due date (dd/MM/yyyy)");
        String date = scanner.nextLine();
        Item item = new Item(name, date);
        todoList.addItem(item);
    }

    private void completeTask(ItemList todoList) {
        System.out.println("Enter item to be crossed off");
        String name = scanner.nextLine();
        todoList.completeItem(name);
    }

    private void viewCurrent(ItemList todoList) {
        todoList.printCurrentItems();
    }

    private void viewCompleted(ItemList todoList) {
        todoList.printCompletedItems();
    }

    private void saveList(ItemList todoList) throws IOException {
        todoList.save(todoList);
    }

    private void loadList(ItemList todoList) throws IOException {
        todoList.load(todoList);
    }

}


