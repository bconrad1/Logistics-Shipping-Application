import common.DataValidationException;

public class Main {

    public static void main(String[] args)throws DataValidationException {

        FacilityServer runFac = new FacilityServer();
        runFac.loadFacilities();
    }
}
