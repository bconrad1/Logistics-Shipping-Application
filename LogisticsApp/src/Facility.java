import common.DataValidationException;

import java.util.ArrayList;


public interface Facility {

    String getName();
    int getCost();
    int getProductPerDay();
    ArrayList<Link> getConnections();

    void printConnections();

}