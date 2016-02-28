package scheduling;

import common.SchedulingConflictException;

public interface Schedule {
    int getAvailability(int day);
    boolean hasAvailability(int day, int units);
    int scheduleWork(int day, int units) throws SchedulingConflictException;
    int getProcessingEndDay(int startDay, int units);
}
