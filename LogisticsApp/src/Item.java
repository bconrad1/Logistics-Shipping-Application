import common.DataValidationException;

public interface Item {

    int getPrice();
    void setPrice(int price) throws DataValidationException;
    public String getId();
    void setId(String id) throws DataValidationException;
    int getQuantity();
    void setQuantity(int quantity) throws DataValidationException;
}
