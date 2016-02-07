import common.DataValidationException;

public class ItemImpl implements Item {

    private String id;
    private int price;
    private int quantity;

    ItemImpl(String idIn, int priceIn, int quantity) throws DataValidationException {
        setId(idIn);
        setPrice(priceIn);
        setQuantity(quantity);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws DataValidationException {
        if (price < 0) throw new DataValidationException("Price must be < 0");
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws DataValidationException {
        if (id == null) throw new DataValidationException("id cannot be null");
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws DataValidationException {
        if (quantity < 0) throw new DataValidationException("Quantity must be < 0");
        this.quantity = quantity;
    }
}