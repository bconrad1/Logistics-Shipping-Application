import order.Order;
import order.OrderImpl;
import order.OrderService;
import scheduling.ScheduleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {


    public static void main(String[] args) {


        System.out.println(StatusReport.generateReport());

        //List<Order> orderBatch = new ArrayList<>();
        OrderService os = OrderService.getInstance();

        int i = 0;
        for (String orderId : os.getOrderIds()){
            String printMe = OrderProcessor.processOrder(os.getOrder(orderId), i);
            i++;
            System.out.println(printMe);
        }

        System.out.println(StatusReport.generateReport());

    }



}
