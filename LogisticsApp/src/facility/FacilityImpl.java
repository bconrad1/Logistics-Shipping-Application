package facility;

import common.DataValidationException;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FacilityImpl implements Facility {

    private String name;
    private int cost;
    private int productPD;
    private ArrayList<Link> connections;


    FacilityImpl(String facName, int costPerDay, int productPerDay, ArrayList<Link> connections) throws DataValidationException {

        setName(facName);
        setCost(costPerDay);
        setProductPD(productPerDay);
        setConnections(connections);

    }

    public void setProductPD(int productPerDay) throws DataValidationException {
        if (productPerDay < 0) throw new DataValidationException("product per day cannot be > 0");
        this.productPD = productPerDay;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) throws DataValidationException {
        if (name == null) throw new DataValidationException("name cannot be null");
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    private void setCost(int cost) throws DataValidationException {
        if (cost < 0) throw new DataValidationException("cost cannot be > 0");
        this.cost = cost;
    }

    public int getProductPerDay() {
        return productPD;
    }

    public void printConnections() {
        for (Link i : connections) {
            System.out.println(i.getCity());
        }

    }

    public ArrayList<Link> getConnections() {
        return connections;

    }

    private void setConnections(ArrayList<Link> connections) throws DataValidationException {
        if (connections == null) throw new DataValidationException("connections cannot be null");
        this.connections = connections;
    }

    @Override
    public String toString() {
        String str = "";
        str += name + "\n";
        str += "DIRECT LINKS: ";


        //Scan the links for each city.
        for (Link l : connections) {

            //8 hours * 50mph = totaldistance/400 for total days
            double totalDays = l.getDistance() / 400;
            DecimalFormat df = new DecimalFormat("#.##");
            totalDays = Double.valueOf(df.format(totalDays));

            str += l.getCity() + " ("
                    + totalDays
                    + ") ";
        }
        str += "\n";
        return str;
    }


}