package scheduling;

import common.DataValidationException;
import common.SchedulingConflictException;

import java.util.Arrays;

public class ScheduleImpl implements Schedule {

    private final int SCH_SIZE = 20;
    private int[] sch = new int[SCH_SIZE]; // for now.
    private int rate;

    ScheduleImpl(int rateIn) throws DataValidationException{
        setRate(rateIn);

        // initialize the schedule
        for(int i = 0; i < SCH_SIZE; i++){

            sch[i] = this.rate;
        }
    }

    public int getRate() {
        return rate;
    }

    private void setRate(int rate) throws DataValidationException {
        if (rate < 0) throw new DataValidationException("rate must be >0");
        this.rate = rate;
    }

    public int getAvailability(int day){
        return sch[day];
    }

    public boolean hasAvailability(int day, int units) {
        int avail = getAvailability(day);
        int newAvail = avail - units;

        return newAvail >= 0;

    }

    public void scheduleWork(int day, int units) throws SchedulingConflictException{

        if(!hasAvailability(day,units)) throw new SchedulingConflictException("Not enough availability");

        sch[day] -= units;

    }

    @Override
    public String toString() {
        return "ScheduleImpl{" +
                "rate=" + rate +
                //"SCH_SIZE=" + SCH_SIZE +
                ", sch=" + Arrays.toString(sch) +
                '}';
    }
}
