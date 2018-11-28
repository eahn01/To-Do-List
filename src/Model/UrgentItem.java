package Model;

public class UrgentItem extends Item{
    public UrgentItem(String name) {
        super(name);
    }

    // MODIFIES: this
    // EFFECTS: sets status to "urgent"
    @Override
    public void setStatus() {
        this.status = "URGENT";
    }

    // MODIFIES: this
    // EFFECTS: sets due date to "be completed asap"
    @Override
    public void setDueDate(String date) {
        this.dueDate = "TO BE COMPLETED ASAP";
    }
}
