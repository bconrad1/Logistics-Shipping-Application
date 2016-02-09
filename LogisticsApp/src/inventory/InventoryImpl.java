package inventory;

import common.DataValidationException;
import common.InventoryItemException;

import java.util.*;

public class InventoryImpl implements Inventory {

    private HashMap<String,Item> inv;

    InventoryImpl(Iterable<Item> invIn) throws DataValidationException{
        HashMap<String, Item> inventory = new HashMap<>();
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

    public boolean hasEnoughInventory(String itemId, int quantity) throws InventoryItemException {
        if(!hasItem(itemId)) throw new InventoryItemException(itemId + " is not in this inventory");

        int currentQuantity = getInventoryQuantity(itemId);

        return currentQuantity > quantity;
    }

    // returns an item and reduces inventory
    public void grabItem(String itemId, int quantity) throws InventoryItemException, DataValidationException {
        if (!hasEnoughInventory(itemId,quantity)) throw new InventoryItemException("Not enough of " + itemId);

        int newQuantity = getInventoryQuantity(itemId) - quantity;

        inv.get(itemId).setQuantity(newQuantity);
    }

    // Might not be needed
    public boolean isEmpty(){
        return inv.isEmpty();
    }

    public HashMap<String, Item> getInventoryList(){
        // for debugging only
        return inv;
    }

    @Override
    public String toString() {
        String str = "";
        for (String key: inv.keySet()){
            Item item = inv.get(key);
            str += item + "\n";

        }

        return str;
    }
}
