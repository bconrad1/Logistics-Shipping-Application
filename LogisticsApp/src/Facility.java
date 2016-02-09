import common.DataValidationException;

import java.util.ArrayList;


public interface Facility {

    String getName();
    int getCost();
    int getProductPerDay();
    String getFacilityInfo();
    ArrayList<Link> getConnections();

    void printConnections();

}