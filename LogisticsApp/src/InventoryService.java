import common.DataValidationException;

import java.util.HashMap;

public class InventoryService {

    public static HashMap<String, Inventory> inventories = new HashMap<String, Inventory>();

    public static void addInventory(String facilityName, Inventory inv) throws DataValidationException{
        if (inv.isEmpty()) throw new DataValidationException("Cannot add an empty inventory");
        inventories.put(facilityName, inv);
    }

    public static Inventory getInventory(String facilityName) throws DataValidationException {
        if (!inventories.containsKey(facilityName)) throw new DataValidationException("Facility does not exist by that name");

        return inventories.get(facilityName);
    }

    public static boolean facilityHasItem(String facilityName, String itemId) throws DataValidationException {
        if (!inventories.containsKey(facilityName)) throw new DataValidationException("Facility does not exist by that name");

        return inventories.get(facilityName).hasItem(itemId);

    }

    public static void init() { InventoryDataLoader.load("inventory.xml"); }

}