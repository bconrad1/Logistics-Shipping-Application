import common.DataValidationException;

/**
 * Created by ericj on 2/6/2016.
 */
public interface Item {

    int getPrice();
    void setPrice(int price) throws DataValidationException;
    public String getId();
    void setId(String id) throws DataValidationException;
    int getQuantity();
    void setQuantity(int quantity) throws DataValidationException;
}
