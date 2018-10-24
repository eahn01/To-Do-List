package Model;

public class RegularItem extends Item {
    public RegularItem (String name) {
        super(name);
    }

    @Override
    public void setStatus() {
        this.status = "not completed";
    }

    @Override
    public void setDueDate(String date) {
        this.dueDate = date;
    }
}
