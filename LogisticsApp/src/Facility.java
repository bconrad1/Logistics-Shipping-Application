import common.DataValidationException;

import java.util.ArrayList;


public interface Facility {

    String getName();
    int getCost();
    int getProductPerDay();
    String getFacilityInfo();
    ArrayList<LinkImpl> getConnections();

    void setName(String name) throws DataValidationException;
    void setCost(int cost)throws DataValidationException;
    void setProductPD(int productPerDay)throws DataValidationException;
    void setConnections(ArrayList<LinkImpl> connections)throws DataValidationException;
    void printConnections();

}