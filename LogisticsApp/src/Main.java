import common.DataValidationException;

public class Main {

    public static void main(String[] args)throws DataValidationException {

        FacilityManager runFac = new FacilityManager();
        runFac.loadFacilities();
    }
}
