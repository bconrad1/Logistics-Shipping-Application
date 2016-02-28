package reports;


public class ItemProcessResult {

    String itemId;
    int quantity;
    int cost;
    int numSorces;
    int firstDay;
    int lastDay;
    int backOrdered;

    public ItemProcessResult(String itemId, int quantity, int backOrdered, int cost, int numSorces, int firstDay, int lastDay) {

        this.itemId = itemId;
        this.quantity = quantity;
        this.cost = cost;
        this.numSorces = numSorces;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.backOrdered = backOrdered;
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

    public int getBackOrdered() { return backOrdered; }

    public void setBackOrdered(int i){

        // REPLACE WITH GOOD ERROR HANDLING LATER
        if ( i < 0 ) i = 0;

        this.backOrdered = i;
    }

    public boolean hasBackorderedItems() { return backOrdered > 0; }
}
