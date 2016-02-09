
public class ScheduleDataTester {

    public static void printAllSchedules() {

        ScheduleService ss = ScheduleService.getInstance();

        // A listing of all schedules.
        for(String key : ss.getFacilityNames()){

            Schedule s = ss.getSchedule(key);

            System.out.println("========== " + key + "'s Schedule " + "==========");

            System.out.println(s);

        }

    }

    public static void printSchedule(String facName){

        ScheduleService ss = ScheduleService.getInstance();

        Schedule s = ss.getSchedule(facName);

        System.out.println(s);

    }

    public static void main(String[] args) {

        //printAllSchedules();
        String facName = "Phoenix, AZ";
        printSchedule(facName);

        ScheduleService ss = ScheduleService.getInstance();

        ss.scheduleWork(facName, 0,5);

        printSchedule(facName);

        System.out.println(ss.hasAvailabilityOnDay(facName, 0, 6));
        System.out.println(ss.hasAvailabilityOnDay(facName, 0, 1));

    }
}
