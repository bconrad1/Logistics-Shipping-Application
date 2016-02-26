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
        if (name == null) throw new DataValidationException("Facility name cannot be null");
        this.facName = name;
    }

    private void setnumItems(int items) throws DataValidationException{
        if (items > 0) throw new DataValidationException("num Items must be greater than 0");
        this.numItems = items;
    }

    private void setEndProc(int endProc) throws DataValidationException{
        if (endProc > 0 ) throw new DataValidationException("end Proc must be greater than 0");
        this.endProc = endProc;
    }

    private void setTravelTime(int travelTime) throws DataValidationException{
        if (travelTime > 0) throw new DataValidationException("travel time must be greater than 0");
        this.travelTime = travelTime;
    }

    private void setArrivalDay(int arrivalDay) throws DataValidationException{
        if (arrivalDay > 0) throw new DataValidationException("arrival day must be greater than 0");
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
