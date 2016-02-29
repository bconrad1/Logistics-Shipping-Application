package order;

import common.DataValidationException;
import inventory.Item;
import inventory.ItemFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class OrderImpl implements Order, Comparable<Order> {

    int time;
    String dest;
    List<Item> items;
    String id;

    OrderImpl(int time, String dest, String id, Iterable<Item> items) throws DataValidationException {
        setTime(time);
        setDest(dest);
        setItems(items);
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTime()  {

        return time;
    }

    public void setTime(int time) throws DataValidationException {

        if (time < 0) throw new DataValidationException("time cannot be <0");
        this.time = time;
    }

    public String getDest() {

        return dest;
    }

    private void setDest(String dest) throws DataValidationException {
         List<String> validDests = Arrays.asList(
                "Seattle, WA",
                "San Francisco, CA",
                "Los Angeles, CA",
                "Phoenix, AZ",
                "Denver, CO",
                "Santa Fe, NM",
                "Fargo, ND",
                "Austin, TX",
                "St. Louis, MO",
                "Chicago, IL",
                "Nashville, TN",
                "Detroit, MI",
                "Boston, MA",
                "New York City, NY",
                "Norfolk, VA",
                "Atlanta, GA",
                "Miami, FL" );

        if (!validDests.contains(dest)) throw new DataValidationException("Invalid Destination for order:  " + dest);

        this.dest = dest;
    }

    public List<Item> getItems() {

        return items;
    }

    private void setItems(Iterable<Item> itemsIn) throws DataValidationException {
        // Items will already be checked to be valid through the
        // Item Factory. No double handling of exceptions needed.
        List<Item> itemList = new ArrayList<>();

        for (Item i : itemsIn) {
            itemList.add(i);
        }

        if (itemList.isEmpty()) throw new DataValidationException("itemsIn is empty");

        this.items = itemList;
    }

    @Override
    public String toString() {
        return "OrderImpl{" +
                "time=" + time +
                ", dest='" + dest + '\'' +
                ", id='" + id + '\'' +
                ", items=" + items +
                '}';
    }

    @Override
    public int compareTo(Order o) {
        return Integer.compare(getTime(),o.getTime());
    }

    public static void main(String[] args) {

        try {

            Item i1 = ItemFactory.build("ABC123", 10);
            Item i2 = ItemFactory.build("CT1928", 5);
            List<Item> il = new ArrayList<>();
            il.add(i1);
            il.add(i2);


            Order o = new OrderImpl(1, "Chicago, IL", "I AM AN ID", il);

            System.out.println(o);

        } catch (Throwable e) {e.printStackTrace(); }
    }











}
