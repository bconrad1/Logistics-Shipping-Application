import facility.FacilityService;
import inventory.InventoryService;
import scheduling.ScheduleService;

public class StatusReport {

    public static void facilityStatus(String facName){

        FacilityService fs = FacilityService.getInstance();
        ScheduleService ss = ScheduleService.getInstance();
        InventoryService is = InventoryService.getInstance();

    }

}
