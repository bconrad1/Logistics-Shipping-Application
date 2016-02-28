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

    public static String processOrder(Order order){

        String resPrintout = "";
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
                    // Add the newly created facility report to the list
                    facReports.add(fr);
                } catch (Throwable e) {e.printStackTrace(); }
            }

            // Sort the facility reports (by arrival Day)
            Collections.sort(facReports);

            if (!canFufilOrder(facReports,i)) return "Not enough inventory for Order: " ;

            // Process the item (which lowers inventory) and get the results for formatting.
            ItemProcessResult res = processItem(facReports, i);
            itemResults.add(res); // add it to the order's List of results


        } // end foreach item

        return resPrintout;

    }// end process order

    // could have this throw an error
    public static ItemProcessResult processItem(List<FacilityReportImpl> facReps, Item item) {

        int numSources = 0;
        int quantityNeeded = item.getQuantity();
        int q_idx = 0;
        InventoryService is = InventoryService.getInstance();
        int firstDay = facReps.get(0).getArrivalDay();
        int lastDay = 999; // initialize
        int cost = item.getValue();
        String itemId = item.getId();
        int itemQuantity = item.getQuantity();

        // go through each facility record and
        while (quantityNeeded > 0){

            FacilityReportImpl currentReport = facReps.get(q_idx);
            String currFacName = currentReport.getFacName();

            q_idx++; numSources++;

            int quantityAvail = currentReport.getNumItems();

            // If the facility report can fufil the entire order
            // The order is a success and we will want to return
            if ( quantityNeeded < quantityAvail ) {

                // Set the last day to this arrival day
                lastDay = currentReport.getArrivalDay();

                // remove the items from the facility
                is.getItemsFromFacility(currFacName, item.getId(), quantityNeeded);

                quantityNeeded = 0; // Set quantity needed to 0 to indicate a fufilled order
            }

            // Not enough inventory from this Facility Report, but we will take the items and
            // continue on... (Remember: we are guaranteed to have enough inventory).
            else {
                is.getItemsFromFacility(currFacName, itemId, quantityAvail);
                quantityNeeded -= quantityAvail;
            }


        } // end quantity needed loop

        return new ItemProcessResult(itemId,itemQuantity,cost,numSources,firstDay,lastDay);

    }

    /** This is so ugly...
     * This function sees if we can fufill the order. This needs to be run BEFORE we
     * begin taking stuff out of inventory.
     * @param facReps The reports for the facilities which have the inventory needed.
     *                This should have all inventory possibly available for given item.
     * @param item the item and quantity we are checking for.
     * @return true if our facilities have enough inventory
     */
    private static boolean canFufilOrder(List<FacilityReportImpl> facReps, Item item) {

        int availInv = 0;
        int invNeeded = item.getQuantity();

        // Iterate through all the facilityreports counting how much total inventory is needed
        for (FacilityReportImpl fr : facReps){

            availInv += fr.getNumItems();

        }

        return availInv >= invNeeded;

    }

    // Testing as I go.
    public static void main(String[] args) {


        OrderService os = OrderService.getInstance();

        Order o = os.getOrder("TO-001");

        processOrder(o);
    }




}
