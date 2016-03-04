package facility;


import common.DataValidationException;

public class FacilityTester {



    /*Simple test for Facilites/Shortest Path.

     Tests retrieval of information, shortest path, and Total Distance.

     */

    //Print out the facilities using the Overidden toString for formatting.
    public static void printFacilities() {

        FacilityService fs = FacilityService.getInstance();

        for (String k : fs.getFacilityNames()) {
            System.out.println(k);
        }

    }

    public static void printAllFacilityInfo() {
        FacilityService fs = FacilityService.getInstance();
        for (String k : fs.getFacilityNames()) {
            fs.getFacilityInfo(k);
        }

    }

    public static void main(String[] args) throws DataValidationException {

        // init the facility Service in main here
        FacilityService fs = FacilityService.getInstance();
        String facName = "Phoenix, AZ";

        fs.getFacilityInfo(facName);
        //printAllFacilityInfo();

        //System.out.println(System.lineSeparator()+System.lineSeparator()+System.lineSeparator());

        System.out.println(fs.displayShortestPath("Santa Fe, NM", "Chicago, IL"));
        System.out.println(fs.displayShortestPath("Atlanta, GA", "St. Louis, MO"));
        System.out.println(fs.displayShortestPath("Seattle, WA", "Nashville, TN"));

        System.out.println(fs.getTotalDistance("Santa Fe, NM", "Chicago, IL"));


        System.out.println(fs.getDaysTraveled("Santa Fe, NM", "Chicago, IL"));


    }
}
