package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemList implements Saveable, Loadable{
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
    }

    // EFFECTS: returns true if list contains item
    public boolean currentContains(Item i) {
        return currentItems.contains(i);
    }

    // EFFECTS: returns true if list contains item
    public boolean completedContains(Item i) {
        return completedItems.contains(i);
    }

    public ArrayList<Item> getCurrent() {
        return currentItems;
    }

    public void printCurrentItems() {
        if (currentItems.size() >= 1)
        for (Item i : currentItems) {
            System.out.println("Name: "+i.getName()+"   Status: "+i.getStatus()+"   DueDate: "+i.getDueDate());
        }
        else
            System.out.println("No items to do");
    }

    public void printCompletedItems(){
        if (completedItems.size() >= 1)
        for (Item i : completedItems) {
            System.out.println("Name: "+i.getName()+"   Status: "+i.getStatus());
        }
        else
            System.out.println("No items have been completed");
    }

    public void save(ItemList list) throws IOException {
        PrintWriter writer = new PrintWriter("MyToDoList.txt", "UTF-8");
        for (Item i : list.currentItems) {
            System.out.print("Name: " +i.getName());
            System.out.print("   Status: "+i.getStatus());
            System.out.println("   DueDate: " +i.getDueDate());
            writer.println(i.getName()+" "+i.getDueDate());
        }
        writer.close();
    }

    public void load(ItemList list) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("MyToDoList.txt"));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Item item = new Item(partsOfLine.get(0), partsOfLine.get(1));
            list.currentItems.add(item);
        }
    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] split = line.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    public void saveCompleted(ItemList list) throws IOException {
        PrintWriter writer = new PrintWriter("MyCompletedList.txt", "UTF-8");
        for (Item i : list.completedItems) {
//            System.out.print("Name: " +i.getName());
//            System.out.print("   Status: "+i.getStatus());
//            System.out.println("   DueDate: " +i.getDueDate());
            writer.println(i.getName()+" "+i.getDueDate());
        }
        writer.close();
    }

}
