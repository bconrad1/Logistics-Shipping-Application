import common.*;
import facility.*;
import order.*;
import reports.*;
import inventory.*;
import scheduling.*;

import java.util.ArrayList;
import java.util.Collections;
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
        FacilityService fs = FacilityService.getInstance();
        ScheduleService ss = ScheduleService.getInstance();

        int startDay = order.getTime();


        List<ItemProcessResult> itemResults = new ArrayList<>();
        // Generate facility Records for each item on the item list
        for (Item i : orderItems){

            System.out.println(i);

            // First get a list of the facilities with each item
            List<String> facWithItem = is.getFacilitiesWithItem(i.getId());
            // This would be an odd case. But defensive programming is best.
            if (facWithItem.contains(orderDest)) facWithItem.remove(orderDest);

            // Iterate through each facility and make a Facility Report
            // Create a List of Facility Reports to hold these

            // BUG: Need to use Impl for sorting...
            List<FacilityReportImpl> facReports = new ArrayList<>();
            for (String fac : facWithItem){

                int invQuantity = is.getInventoryQuantity(fac, i.getId());
                int endProc = ss.getProcessEndDay(fac, startDay, invQuantity);
                int travelTime = fs.getDaysTraveled(fac, orderDest);

                try {
                    FacilityReportImpl fr = new FacilityReportImpl(fac, invQuantity, endProc, travelTime);
                    facReports.add(fr);
                } catch (Throwable e) {e.printStackTrace(); }
            }

            // Sort the facility reports (by arrival Day)
            Collections.sort(facReports);

            // Go through each facility report until the order
            // is fufilled, and build a result report

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
