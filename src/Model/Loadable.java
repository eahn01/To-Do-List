package Model;

import Exceptions.TooManyItems;

import java.io.IOException;

public interface Loadable {
    void load(ItemList list) throws IOException;
}
