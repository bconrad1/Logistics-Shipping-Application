import java.util.ArrayList;
import java.util.HashMap;

public interface Facility {

    String getName();
    int getCost();
    int getProductPerDay();
    String getFacilityInfo();
    ArrayList<LinkImpl> getConnections();

    void setName(String name);
    void setCost(int cost);
    void setProductPD(int productPerDay);
    void setConnections(ArrayList<LinkImpl> connections);
    void printConnections();

}