package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    ArrayList<TodoLog> itemList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public TodoList(){
        int option = 0;
        TodoLog itemEntry = new TodoLog();
        while (true){
            System.out.println("Would you like to: [1] Add an item   "+
                    "[2] Cross off an item   "+
                    "[3] View all items");
            option = scanner.nextInt();
            if (option == 1 ) {
                addTask(itemEntry);
                System.out.println("Item has been added to list");
                itemList.add(itemEntry);
            }
            else if (option == 3) {
                System.out.println("Todo List:"+ itemList);
            }
        }
    }

        private void addTask(TodoLog todoEntry) {
            System.out.println("Enter new item");
            String item = scanner.nextLine();
            scanner.nextLine();
            todoEntry.addItem(item);
        }


        public static void main(String[] args) {
            new TodoList();
        }
}


