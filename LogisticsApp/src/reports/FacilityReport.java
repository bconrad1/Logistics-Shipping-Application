package reports;

public interface FacilityReport extends Comparable<FacilityReport> {


    String getFacName();

    int getNumItems();

    int getArrivalDay();

    int compareTo(FacilityReport fr);


}
