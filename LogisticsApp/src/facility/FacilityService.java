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


    public static FacilityService getInstance(){
        if(instance == null){
            instance = new FacilityService();
            FacilityLoader.load("facilities.xml");
        }
        return instance;
    }

    public void addFacility(String name, Facility fac)throws DataValidationException{

        if(!facilities.containsKey(name)){
            facilities.put(name, fac);
        }

    }

    public ArrayList<Facility> getFacilities(){
        ArrayList<Facility> facs = new ArrayList<>();
        for(Facility f: facilities.values()){
            facs.add(f);
        }
       return facs;
    }

    public Set<String> getFacilityNames(){
        return facilities.keySet();
    }

    public String getFacilityInfo(String name){

        return facilities.get(name).toString();
    }

    public double getTotalDistance(String start, String end){
        return ShortestPathHandler.totalDistance(start, end, getFacilities());
    }

    public String displayShortestPath(String start, String end){
        try {
            return ShortestPathHandler.returnSP(start, end, getFacilities());
            //System.out.println(sp);

        } catch (DataValidationException e) { e.printStackTrace(); return null;}

    }







}
