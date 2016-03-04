package reports;


import common.DataValidationException;

import java.util.Arrays;
import java.util.List;

public class ItemProcessResult {

    private String itemId;
    private int quantity;
    private int cost;
    private int numSources;
    private int firstDay;
    private int lastDay;
    private int backOrdered;

    public ItemProcessResult(String itemId, int quantity, int backOrdered, int cost,
                             int numSources, int firstDay, int lastDay) throws DataValidationException {

        setItemId(itemId);
        setQuantity(quantity);
        setCost(cost);
        setNumSources(numSources);
        setFirstDay(firstDay);
        setLastDay(lastDay);
        setBackOrdered(backOrdered);
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

    public int getNumSources() {
        return numSources;
    }

    public int getFirstDay() {
        return firstDay;
    }

    public int getLastDay() {
        return lastDay;
    }

    public int getBackOrdered() {
        return backOrdered;
    }

    public void setBackOrdered(int i) throws DataValidationException {
        if (i < 0) throw new DataValidationException("back ordered must be >0");

        this.backOrdered = i;
    }

    private void setItemId(String itemId) throws DataValidationException {
        List<String> itemIds = Arrays.asList(
                "ABC123",
                "JBL3100",
                "PL132-C",
                "PU238",
                "RL123A",
                "XLK200B",
                "SR71-D",
                "CT1928",
                "RX100-3",
                "E241i",
                "XTP202",
                "SN-241-L",
                "ZTF109",
                "RTF110",
                "CR2032"
        );

        if (!itemIds.contains(itemId)) throw new DataValidationException("itemId: " + itemId + " not recognized");
        this.itemId = itemId;
    }

    private void setQuantity(int quantity) throws DataValidationException {
        if (quantity < 0) throw new DataValidationException("quantity must be >0");
        this.quantity = quantity;
    }

    private void setCost(int cost) throws DataValidationException {
        if (cost < 0) throw new DataValidationException("Cost must be >0");
        this.cost = cost;
    }

    private void setNumSources(int numSources) throws DataValidationException {
        if (numSources < 0) throw new DataValidationException("numSources must be > 0");
        this.numSources = numSources;
    }

    private void setFirstDay(int firstDay) throws DataValidationException {
        if (firstDay < 0) throw new DataValidationException("firstDay must be > 0");
        this.firstDay = firstDay;
    }

    private void setLastDay(int lastDay) throws DataValidationException {
        if (lastDay < 0) throw new DataValidationException("lastDay must be > 0");
        this.lastDay = lastDay;
    }
}
