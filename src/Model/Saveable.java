package Model;

import java.io.IOException;

public interface Saveable {
    void save(ItemList list) throws IOException;
}
