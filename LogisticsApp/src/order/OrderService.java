package order;

import java.util.HashMap;

public class OrderService {

    HashMap<String,Order> orders;
    public static OrderService instance = null;


    private OrderService(){
        orders = new HashMap<>();
    }

    public static OrderService getInstance(){
        if (instance == null) {
            instance = new OrderService();
            OrderDataLoader.load("orders.xml");
        }

        return instance;
    }

    public void addOrder(String orderId, Order order){
        orders.put(orderId,order);
    }

    public Order getOrder(String orderId){
        return orders.get(orderId);
    }


}