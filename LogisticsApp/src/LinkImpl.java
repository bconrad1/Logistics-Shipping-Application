/**
 * Created by Ben on 2/7/2016.
 */
public class LinkImpl implements Link {

    private String name;
    private double distance;

    LinkImpl(String name, double distance){
        setCity(name);
        setDistance(distance);
    }


    public void setCity(String name){
        this.name = name;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }


    public String getCity(){
        return name;
    }

    public double getDistance(){
        return distance;
    }



}
