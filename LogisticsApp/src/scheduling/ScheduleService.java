package scheduling;

import common.DataValidationException;
import common.SchedulingConflictException;

import java.util.HashMap;
import java.util.Set;

public class ScheduleService {

    private static ScheduleService instance = null;
    private HashMap<String, Schedule> schedules;

    private ScheduleService() {
        schedules = new HashMap<>();
    }

    public static ScheduleService getInstance() {
        if (instance == null) {
            instance = new ScheduleService();
            ScheduleDataLoader.load("schedule.xml");
        }
        return instance;
    }

    public void addSchedule(String facName, Schedule sch) throws DataValidationException {

        if (schedules.containsKey(facName)) throw new DataValidationException("Already have a schedule for " + facName);

        schedules.put(facName, sch);

    }

    public Schedule getSchedule(String facName) {
        return schedules.get(facName);
    }

    public boolean hasAvailabilityOnDay(String facName, int day, int quantity) {
        Schedule sch = schedules.get(facName);

        return sch.hasAvailability(day, quantity);
    }

    /**
     * Function that returns how many days are available for a facility on a given day.
     *
     * @param facName facility location associated with the desired schedule (e.g. Chicago, IL)
     * @param day     the day that available is needed for
     * @return how many packages are available for processing on the given day.
     */
    public int howMuchAvailabilityOnDay(String facName, int day) {
        Schedule sch = schedules.get(facName);

        return sch.getAvailability(day);
    }

    /**
     * Schedules work for a facility at a given day. If there is not enough days for the amount of work
     * scheduled, an error message is printed out.
     *
     * @param facName facility location associated with the desired schedule (e.g. Chicago, IL)
     * @param day     day the work should be scheduled on
     * @param units   how many units should be scheduled
     */
    public int scheduleWork(String facName, int day, int units) {

        Schedule sch = schedules.get(facName);

        try {
            return sch.scheduleWork(day, units);

        } catch (SchedulingConflictException e) {
            System.out.println("No Availability for " + facName + " on " + day);
            e.printStackTrace();
            return 0;
        }
    }

    public int getProcessEndDay(String facName, int startDay, int units) {
        Schedule s = getSchedule(facName);

        return s.getProcessingEndDay(startDay, units);
    }

    public Set<String> getFacilityNames() {
        return schedules.keySet();
    }


    public String getScheduleInfo(String facName) {
        return schedules.get(facName).toString();
    }


}