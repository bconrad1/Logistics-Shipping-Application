package inventory;

import common.DataValidationException;
import common.InventoryItemException;

import java.util.HashMap;
import java.util.Set;

public class InventoryService {

    private static InventoryService instance = null;
    private HashMap<String, Inventory> inventories;

    private InventoryService(){
        inventories = new HashMap<>();
    }

    public static InventoryService getInstance(){
        if (instance == null){
            instance = new InventoryService();
            InventoryDataLoader.load("inventory.xml");
        }

        return instance;
    }

    public void addInventory(String facName, Inventory inv) throws DataValidationException{
        if (inv.isEmpty()) throw new DataValidationException("Cannot add an empty inventory");
        inventories.put(facName, inv);
    }

    public Inventory getInventory(String facName)  {
        if (!inventories.containsKey(facName)) System.out.println("Inventory does not exist by that name: " + facName);

        return inventories.get(facName);
    }

    public boolean facilityHasItem(String facName, String itemId)  {

        return getInventory(facName).hasItem(itemId);

    }

    public void getItemsFromFacility(String facName, String itemId, int quantity){

        Inventory inv = getInventory(facName);

        try {
            inv.grabItem(itemId, quantity);
        }
        catch (DataValidationException | InventoryItemException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    public Set<String> getFacilityNames(){
        return inventories.keySet();
    }

}