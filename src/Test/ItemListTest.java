package Test;

import Exceptions.ItemNotFound;
import Exceptions.TooManyItems;
import Model.Item;
import Model.ItemList;
import Model.RegularItem;
import Model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ItemListTest {
    private ItemList itmList;

    @BeforeEach
    public void runBefore(){
        itmList = new ItemList();
    }

    @Test
    public void testAddItem() {
        Item i = new RegularItem("homework");
        i.setStatus();
        i.setDueDate("09/10/2018");
        try {
            itmList.addItem(i);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        assertTrue(itmList.currentContains(i));
    }

    @Test
    public void testCompleteItem() {
        Item i = new RegularItem("homework");
        i.setStatus();
        i.setDueDate("23/09/2018");
        Item i2 = new RegularItem("laundry");
        i.setStatus();
        i.setDueDate("29/09/2018");
        try {
            itmList.addItem(i);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        try {
            itmList.addItem(i2);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        try {
            itmList.completeItem(i.getName());
        } catch (ItemNotFound itemNotFound) {
            fail("item wasn't found when should be able too");
        }
        assertFalse(itmList.currentContains(i));
        assertTrue(itmList.completedContains(i));
    }

    @Test
    public void testTooManyItems() {
        Item i1 = new UrgentItem("a");
        i1.setStatus();
        i1.setDueDate("");
        Item i2 = new UrgentItem("b");
        i2.setStatus();
        i2.setDueDate("");
        Item i3 = new UrgentItem("c");
        i3.setStatus();
        i3.setDueDate("");
        Item i4 = new UrgentItem("d");
        i4.setStatus();
        i4.setDueDate("");
        Item i5 = new UrgentItem("e");
        i5.setStatus();
        i5.setDueDate("");
        Item i6 = new UrgentItem("f");
        i6.setStatus();
        i6.setDueDate("");
        Item i7 = new UrgentItem("g");
        i7.setStatus();
        i7.setDueDate("");
        Item i8 = new UrgentItem("h");
        i8.setStatus();
        i8.setDueDate("");
        Item i9 = new UrgentItem("i");
        i9.setStatus();
        i9.setDueDate("");
        Item i10 = new UrgentItem("j");
        i10.setStatus();
        i10.setDueDate("");
        Item i11 = new UrgentItem("k");
        i10.setStatus();
        i10.setDueDate("");

        try {
            itmList.addItem(i1);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        try {
            itmList.addItem(i2);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        try {
            itmList.addItem(i3);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        try {
            itmList.addItem(i4);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        try {
            itmList.addItem(i5);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        try {
            itmList.addItem(i6);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }try {
            itmList.addItem(i7);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }try {
            itmList.addItem(i8);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        try {
            itmList.addItem(i9);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        try {
            itmList.addItem(i10);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        try {
            itmList.addItem(i11);
            fail("no exceptions thrown");
        } catch (TooManyItems tooManyItems) {
            // do nothing
        }


        assertTrue(itmList.currentContains(i1));
        assertTrue(itmList.currentContains(i2));
        assertTrue(itmList.currentContains(i3));
        assertTrue(itmList.currentContains(i4));
        assertTrue(itmList.currentContains(i5));
        assertTrue(itmList.currentContains(i6));
        assertTrue(itmList.currentContains(i7));
        assertTrue(itmList.currentContains(i8));
        assertTrue(itmList.currentContains(i8));
        assertTrue(itmList.currentContains(i10));
        assertFalse(itmList.currentContains(i11));
    }

    @Test
    public void testItemNotFound() {
        Item i = new UrgentItem("study");
        i.setStatus();
        i.setDueDate("");
        try {
            itmList.addItem(i);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        try {
            itmList.completeItem("homework");
            fail("no exceptions thrown");
        } catch (ItemNotFound itemNotFound) {
            // do nothing
        }

        assertTrue(itmList.currentContains(i));
        assertFalse(itmList.completedContains(i));
    }
}
