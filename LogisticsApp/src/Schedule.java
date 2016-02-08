import common.SchedulingConflictException;

public interface Schedule {
    int getRate();
    int getCost();
    int getAvailability(int day);
    boolean hasAvailability(int day, int units);
    void scheduleWork(int day, int units) throws SchedulingConflictException;

}
