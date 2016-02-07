import common.DataValidationException;
import common.InventoryItemException;

import java.util.HashMap;

/**
 * Created by ericj on 2/6/2016.
 */
public interface Inventory {

    boolean hasItem(String itemId);
    Item grabItem(String itemId, int quantity) throws  InventoryItemException, DataValidationException;
    int getInventoryQuantity(String itemId) throws  InventoryItemException;
    boolean isEmpty();
    public HashMap<String, Item> getInventoryList();

}
