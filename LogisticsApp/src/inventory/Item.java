package inventory;

import common.DataValidationException;

public interface Item {

    int getPrice();
    String getId();
    int getQuantity();
    void setQuantity(int quantity) throws DataValidationException;
    int getValue();
}
