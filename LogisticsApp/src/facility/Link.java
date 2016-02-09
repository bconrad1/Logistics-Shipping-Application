package facility;

import common.DataValidationException;

public interface Link {

    void setCity(String city)throws DataValidationException;
    void setDistance(double distance) throws DataValidationException;
    double getDistance();
    String getCity();
}
