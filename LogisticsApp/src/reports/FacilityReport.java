package reports;

public interface FacilityReport {



    String getFacName();

    int getNumItems();

    int getEndProc();

    int getTravelTime();

    int getArrivalDay();

    int compareTo(FacilityReport fr);




}
