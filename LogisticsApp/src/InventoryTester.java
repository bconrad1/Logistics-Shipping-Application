
public class InventoryTester {

    public static void main(String[] args) {

            InventoryDataLoader.load("inventory.xml");

            for (String key : InventoryService.inventories.keySet()){
                Inventory inv = InventoryService.inventories.get(key);
                System.out.println(" ===== Facility: " + key + " ==========");

                for (String k : inv.getInventoryList().keySet()) {
                    Item itm = inv.getInventoryList().get(k);

                    System.out.println(itm);
                }
                System.out.println();
            }
    }
}
