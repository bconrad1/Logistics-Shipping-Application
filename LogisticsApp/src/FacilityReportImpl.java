import common.DataValidationException;



public class FacilityReportImpl implements FacilityReport, Comparable<FacilityReportImpl>{

    private String facName;
    private int numItems;
    private int endProc;
    private int travelTime;
    private int arrivalDay;

    FacilityReportImpl(String name, int numItems, int endProc, int travelTime, int arrivalDay) throws DataValidationException{
        setFacName(name);
        setnumItems(numItems);
        setEndProc(endProc);
        setTravelTime(travelTime);
        setArrivalDay(arrivalDay);

    }


    private void setFacName(String name) throws DataValidationException{
        this.facName = name;
    }

    private void setnumItems(int items) throws DataValidationException{
        this.numItems = items;
    }

    private void setEndProc(int endProc) throws DataValidationException{
        this.endProc = endProc;
    }

    private void setTravelTime(int travelTime) throws DataValidationException{
        this.travelTime = travelTime;
    }

    private void setArrivalDay(int arrivalDay) throws DataValidationException{
        this.arrivalDay= arrivalDay;
    }

    public String getFacName(){return facName;}

    public int getNumItems(){return numItems;}

    public int getEndProc(){return endProc;}

    public int getTravelTime(){return travelTime;}

    public int getArrivalDay(){return arrivalDay;}

    public int compareTo(FacilityReportImpl other) {

        return Integer.compare(this.arrivalDay, other.arrivalDay);

    }


}
