package order;

import java.util.Arrays;
import java.util.List;

public class OrderTester {

    private static final List<String> orderIds = Arrays.asList("TO-001", "TO-002", "TO-003", "TO-004",
            "TO-005", "TO-006");

    public static void main(String[] args) {

        printAllOrders();

    }

    private static String getOrderPrintout(String orderId) {
        final String EOL = System.lineSeparator();
        OrderService os = OrderService.getInstance();

        Order o = os.getOrder(orderId);
        return o.toString() + EOL + EOL;
    }

    private static void printAllOrders() {

        String str = "";
        for (String key : orderIds)
            str += getOrderPrintout(key);
        System.out.println(str);


    }

}


