package order;

import inventory.Item;

import java.util.List;

public interface Order extends Comparable<Order>{

    int getTime();
    List<Item> getItems();
    String getDest();
    String getId();

}
