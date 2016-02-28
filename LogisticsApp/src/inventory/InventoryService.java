package inventory;

import common.DataValidationException;
import common.InventoryItemException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * Adds an inventory object to the HashMap inventories. Inventories are indexed by facility locations.
     * @param facName location of the facility the inventory belongs to. (e.g. Chicago, IL)
     * @param inv the Inventory object to be added.
     * @throws DataValidationException
     */
    public void addInventory(String facName, Inventory inv) throws DataValidationException{
        if (inv.isEmpty()) throw new DataValidationException("Cannot add an empty inventory");
        inventories.put(facName, inv);
    }

    /**
     * Get an inventory object related to a facility by it's location
     * @param facName location of the facility the inventory belongs to. (e.g. Chicago, IL)
     * @return an Inventory object which belongs
     */
    public Inventory getInventory(String facName) throws DataValidationException {
        if (!inventories.containsKey(facName)) throw new DataValidationException("Inventory does not exist by that name: " + facName);

        return inventories.get(facName);
    }

    /**
     * Querty to see if a facility has an item. Will be used for order processing.
     * @param facName location of the facility the inventory belongs to. (e.g. Chicago, IL)
     * @param itemId Item id to search for.
     * @return true if the facility has the item.
     */
    public boolean facilityHasItem(String facName, String itemId)  {

        try {
            return getInventory(facName).hasItem(itemId);
        }
        catch (DataValidationException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Updates the inventory for an item at a given factory and quantity. If there is not enough
     * inventory to use, an error message will be thrown.
     * @param facName location of the facility the inventory belongs to. (e.g. Chicago, IL)
     * @param itemId Item id to update the inventory.
     * @param quantity How much to decrement the inventory.
     */
    public void getItemsFromFacility(String facName, String itemId, int quantity){

        try {
            Inventory inv = getInventory(facName);
            inv.grabItem(itemId, quantity);
        }
        catch (DataValidationException | InventoryItemException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    /**
     * Helper function to get a Set of all facility names (mostly for debugging).
     * @return A set of all Facility Names.
     */
    public Set<String> getFacilityNames(){
        return inventories.keySet();
    }

    /**
     * Helper function for creating reports related to inventories.
     * @param facName Facility to get inventory info for.
     * @return A string which contains the current status of the inventory.
     */
    public String getInventoryInfo(String facName) {
        try {
            return getInventory(facName).toString();
        } catch (DataValidationException e){
            e.printStackTrace();
            return "";
        }
    }

    public List<String> getFacilitiesWithItem(String itemId){
        List<String> result = new ArrayList<>();

        // Maybe use stream + collect if feeling wild
        for (String key : getFacilityNames()) {
            if (facilityHasItem(key,itemId)) result.add(key);
        }

        return result;
    }

    public int getInventoryQuantity(String facName, String itemId){
        try {
            return getInventory(facName).getInventoryQuantity(itemId);

        } catch (Throwable e) {
            e.printStackTrace();
            return 0;
        }
    }

}