package facility;

import common.DataValidationException;


public class LinkImpl implements Link {

    private String name;
    private double distance;

    LinkImpl(String name, double distance)throws DataValidationException{
        setCity(name);
        setDistance(distance);
    }


    public void setCity(String name)throws DataValidationException{
        this.name = name;
    }

    public void setDistance(double distance)throws DataValidationException{
        this.distance = distance;
    }


    public String getCity(){
        return name;
    }

    public double getDistance(){
        return distance;
    }



}
