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

    // MODIFIES: this
    // EFFECTS: adds item to current list
    public void addItem(Item addedItem) throws TooManyItems {
        if (currentItems.size() >= 10) {
            throw new TooManyItems();
        }
        currentItems.put(addedItem.getName(), addedItem);
    }

    // MODIFIES: this
    // EFFECTS: removes item from current list and adds to completed
    public void completeItem(String completeItem) throws ItemNotFound {
        if (!currentItems.containsKey(completeItem)) {
            throw new ItemNotFound();
        } else {currentItems.get(completeItem).changeStatus();
            completedItems.put(completeItem, currentItems.get(completeItem));
            currentItems.remove(completeItem);
        }
    }

    // EFFECTS: print the given item
    public String printItem(Item i) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nName: ").append(i.getName()).append("    Status: ")
                .append(i.getStatus()).append("    DueDate: ").append(i.getDueDate());
        return sb.toString();
    }

    // EFFECTS: prints the item that was completed
    public String printCompletedItem(String name) {
        Item i = completedItems.get(name);
        StringBuilder sb = new StringBuilder();
        sb.append("\nName: ").append(i.getName())
                .append("    DueDate: ").append(i.getDueDate());
        return sb.toString();
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
    public String printCurrentItems() {
        StringBuilder sb = new StringBuilder();
        if (currentItems.size() >= 1)
        for (Map.Entry<String, Item> entry : currentItems.entrySet()) {
            sb.append("\nName: ").append(entry.getKey()).append("    Status: ")
                    .append(entry.getValue().getStatus()).append("    DueDate: ")
                    .append(entry.getValue().getDueDate());
        }
        else
            sb.append("\nNo items to do");

        return sb.toString();
    }

    // EFFECTS: prints out the items in the completedItems list
    public String printCompletedItems(){
        StringBuilder sb = new StringBuilder();
        if (completedItems.size() >= 1)
        for (Map.Entry<String, Item> entry : completedItems.entrySet()) {
            sb.append("Name: ").append(entry.getKey()).append("   Status: ")
                    .append(entry.getValue().getStatus());
        }
        else
            sb.append("\nNo items have been completed");

        return sb.toString();
    }

    // MODIFIES: todoList text file
    // EFFECTS: saves the items on todoList on to a text file
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

    // MODIFIES: this
    // EFFECTS: loads the items from the text file and adds them to the todoList
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

    private static ArrayList<String> splitOnSpace(String line) {
        String[] split = line.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }
}
