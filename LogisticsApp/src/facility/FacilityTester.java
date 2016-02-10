package facility;


import common.DataValidationException;



public class FacilityTester {

    public static void printFacilities(){

        FacilityService fs = FacilityService.getInstance();

        for(String k: fs.getFacilityNames()){
            System.out.println(k);
        }

    }

    public static void printFacilityInfo(String name)throws DataValidationException{
        FacilityService fs = FacilityService.getInstance();

        fs.getFacilityInfo(name);
    }

    public static void printAllFacilityInfo(){
        FacilityService fs = FacilityService.getInstance();
        for(String k: fs.getFacilityNames()){
            fs.getFacilityInfo(k);
        }

    }

    public static void getSP(String start, String end)throws DataValidationException{
        FacilityService fs = FacilityService.getInstance();
        String sP= ShortestPathHandler.returnSP(start, end, fs.getFacilities());
        System.out.println(sP);
    }

    public static void main(String[] args)throws DataValidationException {


        String facName = "Phoenix, AZ";


        //printFacilityInfo(facName);
        printAllFacilityInfo();

        System.out.println(System.lineSeparator()+System.lineSeparator()+System.lineSeparator());

        getSP("Santa Fe, NM","Chicago, IL");
        getSP("Atlanta, GA",  "St. Louis, MO");
        getSP("Seattle, WA", "Nashville, TN");

    }
}
