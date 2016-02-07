public class FacilityFactory {

    public Facility createFacility(String facName, int costPerDay, int productPerDay) {
        Facility facility = null;

        facility = new FacilityImpl(facName, costPerDay, productPerDay);

        return facility;
    }
}