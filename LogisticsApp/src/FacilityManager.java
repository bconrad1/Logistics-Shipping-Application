import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ben on 2/6/2016.
 */
public class FacilityManager {


    public static void main(String[] args) {

    }
    public void loadFacilities(){
        String facFile = "FacilitiesXML.xml";

        FacilityLoader file = new FacilityLoader(facFile);
        HashMap<String, Facility> cities = file.returnFacilities();
        ArrayList<Facility> facilities = getFacilities(cities);

        ShortestPathHandler.returnSP("Chicago, IL", "Boston, MA", cities);
    }

    public ArrayList<Facility> getFacilities(HashMap<String, Facility> cities){





        ArrayList<Facility> facilities = new ArrayList<>();
        for(Facility f: cities.values()){
            facilities.add(f);
        }
        return facilities;

    }
}
