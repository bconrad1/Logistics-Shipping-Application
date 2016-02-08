import common.DataValidationException;
import common.SchedulingConflictException;

import java.util.HashMap;
public class ScheduleService {

    private static ScheduleService instance = null;
    private HashMap<String, Schedule> schedules;

    private ScheduleService(){
        schedules = new HashMap<>();
        // Add data loader here
    }

    public void addSchedule(String facName, Schedule sch) throws DataValidationException {

        if (schedules.containsKey(facName)) throw new DataValidationException("Already have a schedule for " + facName);

        schedules.put(facName, sch);

    }

    public static ScheduleService getInstance(){
        if (instance == null) instance = new ScheduleService();
        return instance;
    }

    public Schedule getSchedule(String facName) {
        return schedules.get(facName);
    }

    public boolean hasAvailability(String facName, int day, int quantity){
        Schedule sch = schedules.get(facName);

        return sch.hasAvailability(day, quantity);
    }

    public void scheduleWork(String facName, int day, int units){

        Schedule sch = schedules.get(facName);

        try {
            sch.scheduleWork(day, units);

        } catch (SchedulingConflictException e) {
            System.out.println("No Availability for " + facName + " on " + day );
            e.printStackTrace();
        }
    }


}
