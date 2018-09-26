package Model;

import java.util.ArrayList;

public class ItemList {
    String item = "";
    private ArrayList<Item> currentItems;
    private ArrayList<Item> completedItems;

    public ItemList() {
        currentItems = new ArrayList<>();
        completedItems = new ArrayList<>();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds item to current list
    public void addItem(Item addedItem) {
        currentItems.add(addedItem);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: removes item from current list and adds to completed
    public void completeItem(String completeItem) {
        for (Item i : currentItems) {
            if (i.getName().equals(completeItem)) {
                currentItems.remove(i);
                completedItems.add(i);
                i.changeStatus();
                return;
            }
        }
//            else if (completedContains(i)) { }
    }

    // EFFECTS: returns true if list contains item
    public boolean currentContains(Item i) {
        return currentItems.contains(i);
    }

    // EFFECTS: returns true if list contains item
    public boolean completedContains(Item i) {
        return completedItems.contains(i);
    }

//    public void printItems() {
//        for (Item i : currentItems)
//
//    }
}
