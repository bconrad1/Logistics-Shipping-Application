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

        return currentQuantity >= quantity;
    }

    /**
     * Reduces an item's quantity in the inventory.
     * @param itemId item id to be reduced
     * @param quantity how much to reduce inventory by.
     * @throws InventoryItemException if there is not enough inventory
     * @throws DataValidationException if new quantity is <0
     */
    public void grabItem(String itemId, int quantity) throws InventoryItemException, DataValidationException {
        if (!hasEnoughInventory(itemId, quantity)) throw new InventoryItemException("Not enough of " + itemId);

        int newQuantity = getInventoryQuantity(itemId) - quantity;

        inv.get(itemId).setQuantity(newQuantity);
    }

    public boolean isEmpty(){
        return inv.isEmpty();
    }

    public HashMap<String, Item> getInventoryList(){
        // for debugging only
        return inv;
    }

    @Override
    public String toString() {
        final String BR = System.lineSeparator();
        String str = "";

        str += "Active Inventory: " + BR;
        str += "\tItem ID    Quantity" + BR;

        for (String itemId: inv.keySet()){

            Item item = inv.get(itemId);

            try {
                if (getInventoryQuantity(itemId) != 0){
                    // Items with 0 quantity will be reported in
                    // getDepletedItems()
                    str += item + BR;
                }


            } catch (InventoryItemException e) {e.printStackTrace();}

        }

        //final String descHtml = "<strong>Depleated (Used-up) Inventory: </strong>";
        final String desc = "Depleted (Used-up) Inventory: ";
        str += BR + desc;

        str += getDepletedItems();

        return str;
    }

    /**
     * A helper function which returns all items where inventory is 0.
     * @return A string containing each depleted inventory item.
     */
    private String getDepletedItems() {
        String depleted = "";
        boolean flag = false;

        for (String itemId : inv.keySet()){

            try {
                int itemQuantity = getInventoryQuantity(itemId);

                if (itemQuantity == 0) {

                    if (flag) depleted += ", ";
                    depleted += itemId;
                    flag = true;

                }

            } catch (InventoryItemException e) { e.printStackTrace(); }

        }

        return depleted;


    }

    public static void main(String[] args) {
        try {
            Item i1 = ItemFactory.build("ABC123", 100);
            Item i2 = ItemFactory.build("XTP202", 200);
            Item i3 = ItemFactory.build("JBL3100", 0);

            List<Item> ia = new ArrayList<>();

            ia.add(i1);
            ia.add(i2);
            ia.add(i3);

            Inventory i = new InventoryImpl(ia);

            System.out.println(i);

        } catch (DataValidationException e) {e.printStackTrace();}
    }
}
