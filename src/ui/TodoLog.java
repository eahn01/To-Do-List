package ui;

import java.util.ArrayList;

public class TodoLog {
    String item = "";
    ArrayList<String> currentItems = new ArrayList<>();
    ArrayList<String> completedItems = new ArrayList<>();

    public void addItem(String addedItem) {
        currentItems.add(addedItem);
    }

}
