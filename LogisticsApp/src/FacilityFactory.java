import common.DataValidationException;

import java.util.ArrayList;

public class FacilityFactory {

    public Facility createFacility(String facName, int costPerDay, int productPerDay, ArrayList<LinkImpl> connections)throws DataValidationException {
        Facility facility;

        facility = new FacilityImpl(facName, costPerDay, productPerDay, connections);

        return facility;
    }
}