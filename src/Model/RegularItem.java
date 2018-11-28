package Model;

public class RegularItem extends Item {
    public RegularItem (String name) {
        super(name);
    }

    // MODIFIES: this
    // EFFECTS: sets status to "not complete"
    @Override
    public void setStatus() {
        this.status = "not complete";
    }

    // MODIFIES: this
    // EFFECTS: sets due date to given string
    @Override
    public void setDueDate(String date) {
        this.dueDate = date;
    }
}
