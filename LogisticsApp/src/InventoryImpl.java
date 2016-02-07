import common.DataValidationException;
import common.InventoryItemException;

import java.util.*;

public class InventoryImpl implements Inventory {

    public HashMap<String,Item> inv;

    InventoryImpl(Iterable<Item> invIn) throws DataValidationException{
        HashMap<String, Item> inventory = new HashMap<String, Item>();
        for (Item i : invIn) {
            if (inventory.containsKey(i.getId())) throw new DataValidationException("Error loading inventory");
            inventory.put(i.getId(), i);
        }

        inv = inventory;
    }

    public boolean hasItem(String id) {
        return inv.containsKey(id);
    }

    public int getInventoryQuantity(String itemId) throws InventoryItemException {
        if (!hasItem(itemId)) throw new InventoryItemException(itemId + " is not in this inventory");
        return inv.get(itemId).getQuantity();
    }

    // returns an item and reduces inventory
    public Item grabItem(String itemId, int quantity) throws InventoryItemException, DataValidationException {
        if (!hasItem(itemId)) throw new InventoryItemException(itemId + " is not in this inventory");
        if (getInventoryQuantity(itemId) < quantity) throw new InventoryItemException("Not enough of " + itemId);

        int newQuantity = getInventoryQuantity(itemId) - quantity;

        inv.get(itemId).setQuantity(newQuantity);
        return ItemFactory.build(itemId, quantity);
    }

    // Might not be needed
    public boolean isEmpty(){
        return inv.isEmpty();
    }

    public HashMap<String, Item> getInventoryList(){

        return inv;

    }
}
