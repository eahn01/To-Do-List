package Model;

import Exceptions.ItemNotFound;
import Exceptions.TooManyItems;
import Observer.WelcomeMsgPrinter;

import java.io.IOException;
import java.util.Observable;
import java.util.Scanner;

public class ToDoList extends Observable {
    Scanner scanner = new Scanner(System.in);
    ItemList todoList = new ItemList();

    public void run() throws IOException {
        int option;
        addObserver(new WelcomeMsgPrinter());
        loadList(todoList);
        while (true){
            System.out.println("\nWould you like to: \n"+
                    "[0] View welcome message \n"+
                    "[1] Add an item \n"+
                    "[2] Complete an item \n"+
                    "[3] View items to do \n"+
                    "[4] View completed items \n"+
                    "[5] Quit");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 0) {
                setChanged();
                notifyObservers();
            }
            if (option == 1 ) {
                System.out.println("Type of item: \n"+
                        "[1] Regular Item \n"+
                            "[2] Urgent Item");
                int choose = scanner.nextInt();
                scanner.nextLine();
                if (choose == 1) {
                    try {
                        addRegTask(todoList);
                    } catch (TooManyItems tooManyItems) {
                        System.out.println("Too many items on todo list. Please cross something off.");
                        continue;
                    }
                }
                if (choose == 2) {
                    try {
                        addUrgTask(todoList);
                    } catch (TooManyItems tooManyItems) {
                        System.out.println("Too many items on todo list. Please cross something off.");
                        continue;
                    }
                }
            }
            else if (option == 2) {
                try {
                    completeTask(todoList );
                } catch (ItemNotFound itemNotFound) {
                    System.out.println("Item could not be found. Please try again.");
                    continue;
                }
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

    private void addRegTask(ItemList todoList) throws TooManyItems {
        System.out.println("Enter new item:");
        String name = scanner.nextLine();
        System.out.println("Enter due date (dd/MM/yyyy):");
        String date = scanner.nextLine();
        Item item = new RegularItem(name);
        item.setStatus();
        item.setDueDate(date);
        todoList.addItem(item);
        System.out.println("Item has been added to the list.");
    }

    private void addUrgTask(ItemList todoList) throws TooManyItems {
        System.out.println("Enter new item:");
        String name = scanner.nextLine();
        Item item = new UrgentItem(name);
        item.setStatus();
        item.setDueDate("");
        todoList.addItem(item);
        System.out.println("Item has been added to the list.");
    }

    private void completeTask(ItemList todoList) throws ItemNotFound {
        System.out.println("Enter item to be crossed off:");
        String name = scanner.nextLine();
        todoList.completeItem(name);
        System.out.println("Item has been crossed off.");
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


