package Test;

import Exceptions.TooManyItems;
import Model.Item;
import Model.ItemList;
import Model.Loadable;
import Model.RegularItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class SaveAndLoadTest {
    private ItemList testList;
    private ItemList loadedList;

    @BeforeEach
    public void runBefore() {
        testList = new ItemList();
        loadedList = new ItemList();
    }

    @Test
    public void testSaveAndLoad() throws IOException {
        Item i1 = new RegularItem("homework");
        i1.setStatus();
        i1.setDueDate("23/09/2018");
        try {
            testList.addItem(i1);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        Item i2 = new RegularItem("laundry");
        i2.setStatus();
        i2.setDueDate("14/10/2018");
        try {
            testList.addItem(i2);
        } catch (TooManyItems tooManyItems) {
            fail("too many things when shouldn't be");
        }
        testList.save(testList);
        loadedList.load(loadedList);
        for (int i = 0; i <= testList.getCurrent().size()-1; i++) {
            Item testItem = testList.getCurrent().get(i);
            Item loadItem = loadedList.getCurrent().get(i);
            assertTrue(testItem.getName().equals(loadItem.getName()));
        }
    }

    @Test
    public void testLoad() throws IOException {
        Loadable l = new ItemList();
        l.load(testList);

    }
}
