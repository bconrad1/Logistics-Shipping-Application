package reports;

public class FacilityReportTester {

    public static void main(String[] args) {

        try {
            FacilityReport fr = new FacilityReportImpl("Chicago, IL", 5, 3, 4);
            System.out.println(fr);
        } catch (Throwable e) {
            e.printStackTrace();
        }


    }

}
