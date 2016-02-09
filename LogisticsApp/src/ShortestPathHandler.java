
import common.DataValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShortestPathHandler {


    public static ArrayList<Vertex> returnSP(String start, String end, HashMap<String,Facility> facilities)throws DataValidationException {
        FacilityLoader file = new FacilityLoader("FacilitiesXML.xml");

        HashMap<String,Facility> cities = file.returnFacilities();

        ArrayList<Vertex> path = ShortestPath.returnSP(start, end ,facilities);

        return path;
    }


}
