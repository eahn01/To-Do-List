package Model;

import Exceptions.ItemNotFound;
import Exceptions.TooManyItems;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ItemList implements Saveable, Loadable{
    private HashMap<String, Item> currentItems;
    private HashMap<String, Item> completedItems;

    // EFFECTS: creates a list of items with a current list and completed list
    public ItemList() {
        currentItems = new HashMap<>();
        completedItems = new HashMap<>();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds item to current list
    public void addItem(Item addedItem) throws TooManyItems {
        if (currentItems.size() >= 10) {
            throw new TooManyItems();
        }
        currentItems.put(addedItem.getName(), addedItem);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: removes item from current list and adds to completed
    public void completeItem(String completeItem) throws ItemNotFound {
        if (!currentItems.containsKey(completeItem)) {
            throw new ItemNotFound();
        } else {currentItems.get(completeItem).changeStatus();
            completedItems.put(completeItem, currentItems.get(completeItem));
            currentItems.remove(completeItem);

//        for (int i=0; i <= currentItems.size(); i++) {
//            if (i == currentItems.size()) {
//                throw new ItemNotFound();
//            } else if (currentItems.get(i).getName().equals(completeItem)) {
//                currentItems.get(i).changeStatus();
//                completedItems.add(currentItems.get(i));
//                currentItems.remove(currentItems.get(i));
//            }

        }
    }

    // EFFECTS: returns true if list contains item
    public boolean currentContains(Item i) {
        return currentItems.containsValue(i);
    }

    // EFFECTS: returns true if list contains item
    public boolean completedContains(Item i) {
        return completedItems.containsValue(i);
    }

    public HashMap<String, Item> getCurrent() {
        return currentItems;
    }

    // EFFECTS: prints out the items in the currentItems list
    public void printCurrentItems() {
        if (currentItems.size() >= 1)
        for (Map.Entry<String, Item> entry : currentItems.entrySet()) {
            System.out.println("Name: "+entry.getKey()+
                    "   Status: "+entry.getValue().getStatus()+
                    "   DueDate: "+entry.getValue().getDueDate());
        }
        else
            System.out.println("No items to do");
    }

    // EFFECTS: prints out the items in the completedItems list
    public void printCompletedItems(){
        if (completedItems.size() >= 1)
        for (Map.Entry<String, Item> entry : completedItems.entrySet()) {
            System.out.println("Name: "+entry.getKey()+
                    "   Status: "+entry.getValue().getStatus());
        }
        else
            System.out.println("No items have been completed");
    }

    public void save(ItemList list) throws IOException {
        PrintWriter writer = new PrintWriter("MyToDoList.txt", "UTF-8");
        for (Map.Entry<String, Item> entry : currentItems.entrySet()) {
            System.out.print("Name: " +entry.getKey());
            System.out.print("   Status: "+entry.getValue().getStatus());
            System.out.println("   DueDate: " +entry.getValue().getDueDate());
            writer.println(entry.getKey()+" "+entry.getValue().getStatus()+" "+entry.getValue().getDueDate());
        }
        writer.close();
    }

    public void load(ItemList list) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("MyToDoList.txt"));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            if (partsOfLine.get(1).equals("URGENT")) {
                Item urgent = new UrgentItem(partsOfLine.get(0));
                urgent.setStatus();
                urgent.setDueDate("");
                list.currentItems.put(partsOfLine.get(0), urgent);
            } else {
                Item regular = new RegularItem(partsOfLine.get(0));
                regular.setStatus();
                regular.setDueDate(partsOfLine.get(3));
                list.currentItems.put(partsOfLine.get(0), regular);
            }
        }
    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] split = line.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

//    public void saveCompleted(ItemList list) throws IOException {
//        PrintWriter writer = new PrintWriter("MyCompletedList.txt", "UTF-8");
//        for (Item i : list.completedItems) {
//            writer.println(i.getName()+" "+i.getDueDate());
//        }
//        writer.close();
//    }

}
