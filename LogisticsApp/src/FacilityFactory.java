import java.util.ArrayList;
import java.util.HashMap;

public class FacilityFactory {

    public Facility createFacility(String facName, int costPerDay, int productPerDay, ArrayList<LinkImpl> connections) {
        Facility facility = null;

        facility = new FacilityImpl(facName, costPerDay, productPerDay, connections);

        return facility;
    }
}