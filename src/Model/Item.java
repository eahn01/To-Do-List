package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Item {
    protected String name;
    protected String status;
    protected String dueDate;
    //private SimpleDateFormat dueDate = new SimpleDateFormat("dd/MM/yyyy");
    //private Date date;

    // MODIFIES: this
    // EFFECTS: creates a new item
    public Item(String name) {
        this.name = name;
        this.status = "";
        this.dueDate = "";
    }

    public abstract void setStatus();

    public abstract void setDueDate(String date);

    // MODIFIES: this
    // EFFECTS: changes status of item to completed
    public void changeStatus() {
        this.status = "completed";
    }

    // EFFECT: return item name
    public String getName(){
        return name;
    }

    // EFFECT: return item status
    public String getStatus() {
        return status;
    }

    //EFFECT: return item due date
    public String getDueDate() {
        return dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
