package reports;


public class ItemProcessResult {

    String itemId;
    int quantity;
    int cost;
    int numSorces;
    int firstDay;
    int lastDay;

    public ItemProcessResult(String itemId, int quantity, int cost, int numSorces, int firstDay, int lastDay) {

        this.itemId = itemId;
        this.quantity = quantity;
        this.cost = cost;
        this.numSorces = numSorces;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
    }

    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }

    public int getNumSorces() {
        return numSorces;
    }

    public int getFirstDay() {
        return firstDay;
    }

    public int getLastDay() {
        return lastDay;
    }
}
