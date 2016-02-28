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

    public static String processOrderBatch(Iterable<Order> orders){
        String orderOutput = "";
        int orderNumber = 0;
        for (Order o : orders) {
            orderOutput += processOrder(o, orderNumber);
            orderNumber++;
        }

        return orderOutput;
    }

    public static String processOrder(Order order, int orderNumber){

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

            // If no items were found reject the item
            if (facReports.isEmpty()) {
                ItemProcessResult rejected = rejectedItemResult(i);
                itemResults.add(rejected);
            }
            else {
                // Sort the facility reports (by arrival Day)
                Collections.sort(facReports);

                // Process the item (which lowers inventory) and get the results for formatting.
                ItemProcessResult res = processItem(facReports, i, order.getTime());
                itemResults.add(res); // add it to the order's List of results
            }

        } // end foreach item

        // After going through each line of the order we now have the results of all orders
        // We now need to format the report

        return generateOrderReport(order, itemResults, orderNumber);

    }// end process order

    private static String generateOrderReport(Order o, List<ItemProcessResult> resLines, int orderNum){
        final String EOL = System.lineSeparator();

        // Get the total cost of the invoice
        // And summarize begin and end dates

        int totalCost = 0; // change this to a dollar?
        int firstDeliveryDay = -1;
        int lastDeliveryDay = -1;

        // Prepare the Bottom grid-like report
        // Define the column headers
        String itemLineDetail = "      ";
        itemLineDetail += "Item ID        ";
        itemLineDetail += "Quantity       ";
        itemLineDetail += "Cost           ";
        itemLineDetail += "Num. Sources   ";
        itemLineDetail += "First Day      ";
        itemLineDetail += "Last Day       " + EOL;

        for (ItemProcessResult ipr : resLines) {
            itemLineDetail += "      "; // initial indent

            // collect data for the reportHead first...
            totalCost += ipr.getCost();

            if (firstDeliveryDay == -1 || ipr.getFirstDay() < firstDeliveryDay ) {
                firstDeliveryDay = ipr.getFirstDay();
            }

            if (ipr.getLastDay() > lastDeliveryDay) {
                lastDeliveryDay = ipr.getLastDay();
            }

            // Now, let's start building the Data line by line

            // ItemID and Spacing
            itemLineDetail += ipr.getItemId();
            itemLineDetail += spacingHelper(ipr.getItemId());

            // Quantity and Spacing
            itemLineDetail += ipr.getQuantity();
            itemLineDetail += spacingHelper(ipr.getQuantity());

            // Cost and spacing
            itemLineDetail += ipr.getCost();
            itemLineDetail += spacingHelper(ipr.getCost());

            // Num Sources and spacing
            itemLineDetail += ipr.getNumSorces();
            itemLineDetail += spacingHelper(ipr.getNumSorces());

            // First Day and spacing
            itemLineDetail += ipr.getFirstDay();
            itemLineDetail += spacingHelper(ipr.getFirstDay());

            // Last Day and Spacing

            itemLineDetail += ipr.getLastDay();
            itemLineDetail += spacingHelper(ipr.getLastDay());

            itemLineDetail += EOL;

        }


        String reportHead = "";
        String reportBody = "";

        reportHead += "Order #1" + EOL;
        reportHead += "* Order Id:      " + o.getId() + EOL; // Order Id:     <Order Id>
        reportHead += "* Order Time:    Day " + o.getTime() + EOL; // Order Time:    Day <getTime)
        reportHead += "* Destination:   " + o.getDest() + EOL;

        List<Item> itemList = o.getItems();

        reportHead += "* List of Order Items:" + EOL;
        for (Item li : itemList){
            reportHead += "    * Item ID: " + li.getId() + ", Quantity: " + li.getQuantity() + EOL;
        }

        reportHead += EOL + EOL;
        // End report Head

        reportBody += "Processing Solution:" + EOL;
        reportBody += "Order Id: " + o.getId() + EOL + EOL;

        reportBody += "* Destination:       " + o.getDest() + EOL;
        reportBody += "* Total Cost :       $" + totalCost + EOL;
        reportBody += "* 1st Delivery Day:  " + firstDeliveryDay + EOL;
        reportBody += "* Last Delivery Day: " + lastDeliveryDay + EOL;
        reportBody += "* Order Items:" + EOL;


        return reportHead + reportBody + itemLineDetail;
    }

    private static String spacingHelper(int i){

        int colSize = 14;
        String spaces = " ";
        int numSpaces;

        if (i >= 100000) numSpaces = colSize-6;
        else if (i >= 10000) numSpaces = colSize-5;
        else if (i >= 1000) numSpaces = colSize-4;
        else if (i >= 100) numSpaces = colSize-3;
        else if (i >=10) numSpaces = colSize-2;
        else numSpaces = colSize-1;

        for (int j=0; j < numSpaces; j++) {
            spaces += " ";
        }
        return spaces;
    }

    private static String spacingHelper(String s){
        String spaces = " ";
        int numSpaces = 14 - s.length();

        for (int i=0; i < numSpaces; i++) {

            spaces += " ";

        }
        return spaces;
    }

    public static ItemProcessResult rejectedItemResult(Item i){

        String rejId = i.getId() + " - REJECTED";

        return new ItemProcessResult(rejId,0,0,0,0,0,0);
    }

    // could have this throw an error
    public static ItemProcessResult processItem(List<FacilityReportImpl> facReps, Item item, int startDay) {

        int numSources = 0;
        int quantityNeeded = item.getQuantity();
        int q_idx = 0;
        InventoryService is = InventoryService.getInstance();
        ScheduleService ss = ScheduleService.getInstance();
        int firstDay = facReps.get(0).getArrivalDay();
        int lastDay = 999; // initialize
        String itemId = item.getId();
        int itemQuantity = item.getQuantity();
        int backOrdered = 0;

        int cost = 0;

        // go through each facility record and
        while (quantityNeeded > 0){

            // No items left and quantity is still needed, we need to set it to backordered
            if (q_idx > facReps.size()) {
                backOrdered = quantityNeeded;
                break; // break off the loop;
            }
            FacilityReportImpl currentReport = facReps.get(q_idx);
            String currFacName = currentReport.getFacName();

            q_idx++; numSources++;
            lastDay = currentReport.getArrivalDay();

            int quantityAvail = currentReport.getNumItems();

            // If the facility report can fufil the entire order
            // The order is a success and we will want to return
            if ( quantityNeeded < quantityAvail ) {

                // remove the items from the facility
                is.getItemsFromFacility(currFacName, item.getId(), quantityNeeded);

                int processingCosts = ss.scheduleWork(currFacName, startDay, quantityNeeded);

                cost = processingCosts + quantityNeeded * item.getPrice();

                quantityNeeded = 0; // Set quantity needed to 0 to indicate a fufilled order

                // Loop breaks here.
            }

            // Not enough inventory from this Facility Report, but we will take the items and
            // continue on... (Remember: we are guaranteed to have enough inventory).
            else {
                is.getItemsFromFacility(currFacName, itemId, quantityAvail);
                quantityNeeded -= quantityAvail;
            }


        } // end quantity needed loop

        // Reduce the cost if theres backordered inventory
        if (backOrdered > 0) cost -= backOrdered * item.getPrice();

        return new ItemProcessResult(itemId,itemQuantity, backOrdered, cost, numSources,firstDay,lastDay);

    }

    // Testing as I go.
    public static void main(String[] args) {


        OrderService os = OrderService.getInstance();

        Order o = os.getOrder("TO-001");

        String test = processOrder(o,99);

        System.out.println(test);
    }




}
