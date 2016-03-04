package inventory;

import common.DataValidationException;

import java.util.List;

public class InventoryTester {

    public static void printAllInventories() {

        InventoryService invs = InventoryService.getInstance();

        try {
            for (String key : invs.getFacilityNames()) {
                Inventory inv = invs.getInventory(key);
                System.out.println(" ===== facility.Facility: " + key + " ==========");

                for (String k : inv.getInventoryList().keySet()) {
                    Item itm = inv.getInventoryList().get(k);

                    System.out.println(itm);
                }
                System.out.println();
            }
        } catch (DataValidationException e) {
            e.printStackTrace();
        }
    }

    public static void printInventory(String facName) {

        InventoryService invs = InventoryService.getInstance();

        try {
            Inventory inv = invs.getInventory(facName);


            System.out.println(inv);
        } catch (DataValidationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // Uncomment to see all inventories:
        //printAllInventories();


        String facName = "Fargo, ND";

        printInventory(facName);

        InventoryService invs = InventoryService.getInstance();

        System.out.println(invs.getInventoryQuantity(facName, "ABC123"));

        //invs.getItemsFromFacility(facName,"ABC123",25);

        printInventory(facName);

        System.out.println(invs.getFacilitiesWithItem("ABC123"));


    }
}
