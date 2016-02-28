package order;

import inventory.Item;

import java.util.List;

public interface Order {

    int getTime();
    List<Item> getItems();
    String getDest();
    String getId();

}
