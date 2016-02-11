package facility;


import common.DataValidationException;

public class FacilityTester {

    public static void printFacilities(){

        FacilityService fs = FacilityService.getInstance();

        for(String k: fs.getFacilityNames()){
            System.out.println(k);
        }

    }



    public static void printAllFacilityInfo(){
        FacilityService fs = FacilityService.getInstance();
        for(String k: fs.getFacilityNames()){
            fs.getFacilityInfo(k);
        }

    }

    public static void main(String[] args)throws DataValidationException {

        // init the facility Service in main here
        FacilityService fs = FacilityService.getInstance();
        String facName = "Phoenix, AZ";

        fs.getFacilityInfo(facName);
        //printAllFacilityInfo();

        //System.out.println(System.lineSeparator()+System.lineSeparator()+System.lineSeparator());

        fs.displayShortestPath("Santa Fe, NM", "Chicago, IL");
        fs.displayShortestPath("Atlanta, GA", "St. Louis, MO");
        fs.displayShortestPath("Seattle, WA", "Nashville, TN");

        System.out.println(fs.getTotalDistance("Santa Fe, NM", "Chicago, IL"));
    }
}
