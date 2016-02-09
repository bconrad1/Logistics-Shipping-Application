import common.DataValidationException;
import facility.FacilityService;

public class Main {

    public static void main(String[] args)throws DataValidationException {

        FacilityService runFac = new FacilityService();
        runFac.loadFacilities();
    }
}
