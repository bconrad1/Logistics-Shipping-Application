package facility;

import common.DataValidationException;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class FacilityService {

    private static FacilityService instance = null;
    private HashMap<String, Facility> facilities;

    private FacilityService(){facilities = new HashMap<>();}


    //If no instance of singleton  FacilityService exists, create one. Then return it regardless. Also loads XML upon creation.
    public static FacilityService getInstance(){
        if(instance == null){
            instance = new FacilityService();
            FacilityLoader.load("facilities.xml");
        }
        return instance;
    }

    //Add facility to hashmap.
    public void addFacility(String name, Facility fac)throws DataValidationException{

        if(!facilities.containsKey(name)){
            facilities.put(name, fac);
        }

    }

    //Return list of facilities.
    public ArrayList<Facility> getFacilities(){
        ArrayList<Facility> facs = new ArrayList<>();
        for(Facility f: facilities.values()){
            facs.add(f);
        }
       return facs;
    }

    //Return single facility
    public Set<String> getFacilityNames(){
        return facilities.keySet();
    }

    public String getFacilityInfo(String name){

        return facilities.get(name).toString();
    }

    //Get the total distance for the shortest path.
    public double getTotalDistance(String start, String end){
        return ShortestPathHandler.totalDistance(start, end, getFacilities());
    }

    public int getDaysTraveled(String start, String end){

        // Get the total distance and divide by 400
        // and round up with ceil
        Double d = Math.ceil(getTotalDistance(start,end) / 400);

        // return the int value
        return d.intValue();
    }

    //Return the formatted string for shortest path.
    public String displayShortestPath(String start, String end){
        try {
            return ShortestPathHandler.returnSP(start, end, getFacilities());
            //System.out.println(sp);

        } catch (DataValidationException e) { e.printStackTrace(); return null;}

    }







}
