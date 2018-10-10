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
                    "[2] Complete an item   "+
                    "[3] View items to do   "+
                    "[4] View completed items   "+
                    "[5] Quit");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1 ) {
                System.out.println("Type of item: [1] Regular Item    "+
                            "[2] Urgent Item");
                int choose = scanner.nextInt();
                scanner.nextLine();
                if (choose == 1) {
                    addRegTask(todoList);
                }
                if (choose == 2) {
                    addUrgTask(todoList);
                }
                System.out.println("Item has been added to the list");
            }
            else if (option == 2) {
                completeTask(todoList );
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

    private void addRegTask(ItemList todoList) {
        System.out.println("Enter new item");
        String name = scanner.nextLine();
        System.out.println("Enter due date (dd/MM/yyyy)");
        String date = scanner.nextLine();
        Item item = new RegularItem(name);
        item.setStatus();
        item.setDueDate(date);
        todoList.addItem(item);
    }

    private void addUrgTask(ItemList todoList) {
        System.out.println("Enter new item");
        String name = scanner.nextLine();
        Item item = new UrgentItem(name);
        item.setStatus();
        item.setDueDate("");
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


