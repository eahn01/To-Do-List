package Test;

import Model.Item;
import Model.RegularItem;
import Model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    private Item testItem;
    private Item testItem2;
    public String NAME = "Homework";
    public String DUEDATE = "17/04/2018";
    public String INITIAL_STATUS = "not completed";
    public String URGENT = "URGENT";
    public String ASAP = "TO BE COMPLETED ASAP";

    @BeforeEach
    public void runBefore() {
        testItem = new RegularItem(NAME);
        testItem.setDueDate(DUEDATE);
        testItem.setStatus();
        testItem2 = new UrgentItem(NAME);
        testItem2.setStatus();
        testItem2.setDueDate("");
    }
    @Test
    public void testRegConstructor() {
        assertEquals(testItem.getName(), NAME);
        assertEquals(testItem.getStatus(), INITIAL_STATUS);
        assertEquals(testItem.getDueDate(), DUEDATE);
    }

    @Test
    public void testUrgConstructor() {
        assertEquals(testItem2.getName(), NAME);
        assertEquals(testItem2.getStatus(), URGENT);
        assertEquals(testItem2.getDueDate(), ASAP);
    }

    @Test
    public void testChangeStatus() {
        testItem.changeStatus();
        assertEquals(testItem.getStatus(), "completed");
    }
}
