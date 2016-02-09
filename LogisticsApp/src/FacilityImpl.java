import common.DataValidationException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class FacilityImpl implements Facility {

    private String name;
    private int cost;
    private int productPD;
    private ArrayList<LinkImpl> connections;


    FacilityImpl(String facName, int costPerDay, int productPerDay, ArrayList<LinkImpl> connections )throws DataValidationException {

        setName(facName);
        setCost(costPerDay);
        setProductPD(productPerDay);
        setConnections(connections);

    }
    public void setName(String name)throws DataValidationException {
        this.name = name;
    }

    public void setCost(int cost)throws DataValidationException {
        this.cost = cost;
    }


   public void setProductPD(int productPerDay)throws DataValidationException {
        this.productPD = productPerDay;
    }

    public void setConnections(ArrayList<LinkImpl> connections)throws DataValidationException {
        this.connections = connections;
    }
    public String getName(){
        return name;
    }

    public int getCost(){
        return cost;
    }

    public int getProductPerDay(){
        return productPD;
    }

    public void printConnections(){
        for(LinkImpl i: connections){
            System.out.println(i.getCity());
        }

    }

    public ArrayList<LinkImpl> getConnections(){
       return connections;

    }
    public String getFacilityInfo(){

        //Print out facility info. Format later
        return "TEMP";



    }

}