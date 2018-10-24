package Model;

public class UrgentItem extends Item{
    public UrgentItem(String name) {
        super(name);
    }

    @Override
    public void setStatus() {
        this.status = "URGENT";
    }

    @Override
    public void setDueDate(String date) {
        this.dueDate = "TO BE COMPLETED ASAP";
    }
}
