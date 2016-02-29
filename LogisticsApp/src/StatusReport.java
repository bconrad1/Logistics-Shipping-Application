import facility.FacilityService;
import inventory.InventoryService;
import scheduling.ScheduleService;

public class StatusReport {

    public static String facilityStatus(String facName){
        final String BR = System.lineSeparator();
        String str = "" + BR;

        FacilityService fs = FacilityService.getInstance();
        ScheduleService ss = ScheduleService.getInstance();
        InventoryService is = InventoryService.getInstance();

        str += fs.getFacilityInfo(facName) + BR;
        str += is.getInventoryInfo(facName) + BR + BR;
        str += ss.getScheduleInfo(facName) + BR;

        return str;
    }

    private static String generateExamples(){
        final String BR = System.lineSeparator();
        FacilityService fs = FacilityService.getInstance();

        String str = "";

        str += "A) " + fs.displayShortestPath("Santa Fe, NM", "Chicago, IL") + BR;
        str += "B) " + fs.displayShortestPath("Atlanta, GA", "St. Louis, MO") + BR;
        str += "C) " + fs.displayShortestPath("Seattle, WA", "Nashville, TN") + BR;
        str += "D) " + fs.displayShortestPath("New York City, NY", "Phoenix, AZ") + BR;
        str += "E) " + fs.displayShortestPath("Fargo, ND", "Austin, TX") + BR;
        str += "F) " + fs.displayShortestPath("Denver, CO", "Miami, FL") + BR;
        str += "G) " + fs.displayShortestPath("Austin, TX", "Norfolk, VA") + BR;
        str += "H) " + fs.displayShortestPath("Miami, FL", "Seattle, WA") + BR;
        str += "I) " + fs.displayShortestPath("Los Angeles, CA", "Chicago, IL") + BR;
        str += "J) " + fs.displayShortestPath("Detroit, MI", "Nashville, TN") + BR;



        return str;
    }

    public static String generateReport(){
        final String BR = System.lineSeparator();
        final String LN = "======";
        String report = "";

        report += "SE450 Logistics App Status Report\nAuthors: Eric Janowski & Ben Conrad";

        report += BR + BR + LN + " Facility Details " + LN + BR + BR;

        ScheduleService ss = ScheduleService.getInstance();

        for (String facName : ss.getFacilityNames()){
            report += facilityStatus(facName);
        }

        //report += BR + BR + BR + BR + "===== LINKS DETAILS =====" + BR + BR;

        //report += generateExamples();

        return report;
    }

    public static void main(String[] args) {

        String test = facilityStatus("Seattle, WA");
        String report = generateReport();

        System.out.println(report);
    }

}
