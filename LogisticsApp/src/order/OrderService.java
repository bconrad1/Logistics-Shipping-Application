package order;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class OrderService {

    public static OrderService instance = null;
    HashMap<String, Order> orders;


    private OrderService() {
        orders = new HashMap<>();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
            OrderDataLoader.load("orders.xml");
        }

        return instance;
    }

    public void addOrder(String orderId, Order order) {
        orders.put(orderId, order);
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    public Set<String> getOrderIds() {
        return orders.keySet();
    }

    public PriorityQueue<Order> prepareOrderBatch() {

        PriorityQueue<Order> opq = new PriorityQueue<>();


        for (String key : getOrderIds()) {
            Order o = getOrder(key);
            opq.add(o);
        }

        return opq;
    }


}
