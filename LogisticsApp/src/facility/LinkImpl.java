package facility;
import common.DataValidationException;

/**
 * Simple LinkImpl for each facility link. Name and distance. Can be easily changed if more info needs to be added.
 */
public class LinkImpl implements Link {

    private String name;
    private double distance;

    LinkImpl(String name, double distance) throws DataValidationException {
        setCity(name);
        setDistance(distance);
    }

    public String getCity() {
        return name;
    }

    private void setCity(String name) throws DataValidationException {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    private void setDistance(double distance) throws DataValidationException {
        this.distance = distance;
    }

}
