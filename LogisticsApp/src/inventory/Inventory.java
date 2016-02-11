package inventory;

import common.DataValidationException;
import common.InventoryItemException;

import java.util.HashMap;

public interface Inventory {

    boolean hasItem(String itemId);
    void grabItem(String itemId, int quantity) throws  InventoryItemException, DataValidationException;
    int getInventoryQuantity(String itemId) throws  InventoryItemException;
    boolean isEmpty();
    HashMap<String, Item> getInventoryList();
}
