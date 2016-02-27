import common.*;
import facility.*;
import order.*;
import reports.*;
import inventory.*;
import scheduling.*;

import java.util.List;

public class OrderProcessor {

    public static void processOrderBatch(Iterable<Order> orders){
        for (Order o : orders) {
            processOrder(o);
        }
    }

    public static void processOrder(Order order){

        String orderDest = order.getDest();
        List<Item> orderItems = order.getItems();
        InventoryService is = InventoryService.getInstance();


        // Generate facility Records for each item on the item list
        for (Item i : orderItems){

            System.out.println(i);

            // First
            List<String> facWithItem = is.getFacilitiesWithItem(i.getId());

            // This would be an odd case. But defensive programming is best.
            if (facWithItem.contains(orderDest)) facWithItem.remove(orderDest);

            System.out.println(facWithItem);

        }




    }

    // Testing as I go.
    public static void main(String[] args) {


        OrderService os = OrderService.getInstance();

        Order o = os.getOrder("TO-001");

        processOrder(o);
    }




}
