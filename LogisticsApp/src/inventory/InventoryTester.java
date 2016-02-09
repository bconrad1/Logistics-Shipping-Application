package inventory;

public class InventoryTester {

    public static void printAllInventories() {

            InventoryService invs = InventoryService.getInstance();

            for (String key : invs.getFacilityNames()){
                Inventory inv = invs.getInventory(key);
                System.out.println(" ===== facility.Facility: " + key + " ==========");

                for (String k : inv.getInventoryList().keySet()) {
                    Item itm = inv.getInventoryList().get(k);

                    System.out.println(itm);
                }
                System.out.println();
            }
    }

    public static void printInventory(String facName){

        InventoryService invs = InventoryService.getInstance();

        Inventory inv = invs.getInventory(facName);

        System.out.println(inv);
    }

    public static void main(String[] args) {

        // Uncomment to see all inventories:
        //printAllInventories();



        String facName = "Phoenix, AZ";

        printInventory(facName);

        InventoryService invs = InventoryService.getInstance();

        String itemId = "RX100-3";
        final int quantity = 10;

        invs.getItemsFromFacility(facName, itemId, quantity);

        printInventory(facName);

        // Exception properly thrown here
        //invs.getItemsFromFacility(facName, itemId, quantity);
        //printInventory(facName);


    }
}
