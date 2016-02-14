package facility;

import common.DataValidationException;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FacilityImpl implements Facility {

    private String name;
    private int cost;
    private int productPD;
    private ArrayList<Link> connections;


    FacilityImpl(String facName, int costPerDay, int productPerDay, ArrayList<Link> connections )throws DataValidationException {

        setName(facName);
        setCost(costPerDay);
        setProductPD(productPerDay);
        setConnections(connections);

    }
    private void setName(String name)throws DataValidationException {
        this.name = name;
    }

    private void setCost(int cost)throws DataValidationException {
        this.cost = cost;
    }


   public void setProductPD(int productPerDay)throws DataValidationException {
        this.productPD = productPerDay;
    }

    private void setConnections(ArrayList<Link> connections)throws DataValidationException {
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
        for(Link i: connections){
            System.out.println(i.getCity());
        }

    }

    public ArrayList<Link> getConnections(){
       return connections;

    }
    @Override
    public String toString() {
        String str = "";
        str+= name +"\n";
        str+= "DIRECT LINKS: ";


        //Scan the links for each city.
        for (Link l: connections){

            //8 hours * 50mph = totaldistance/400 for total days
            double totalDays = l.getDistance()/400;
            DecimalFormat df = new DecimalFormat("#.##");
            totalDays = Double.valueOf(df.format(totalDays));

            str+=l.getCity() +" ("
                   + totalDays
                   +") ";
        }
        str+="\n";
        return str;
    }



}