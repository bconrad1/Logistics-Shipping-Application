package scheduling;

import common.DataValidationException;
import common.SchedulingConflictException;

import java.util.Arrays;
import java.util.HashMap;

public class ScheduleImpl implements Schedule {

    private HashMap<Integer, Integer> schedule = new HashMap<>();
    private int rate;

    ScheduleImpl(int rateIn) throws DataValidationException {
        setRate(rateIn);
    }

    public static void main(String[] args) {
        try {
            Schedule s = new ScheduleImpl(8);
            System.out.println(s);
        } catch (DataValidationException e) {
            e.printStackTrace();
        }
    }

    public int getRate() {
        return rate;
    }

    private void setRate(int rate) throws DataValidationException {
        if (rate < 0) throw new DataValidationException("rate must be >0");
        this.rate = rate;
    }

    private void setAvail(int day, int units) {
        schedule.put(day, units);
    }

    private int initDay(int day) {
        schedule.put(day, getRate());
        return getRate();
    }

    public int getAvailability(int day) {

        if (schedule.containsKey(day)) return schedule.get(day);
        else return initDay(day); // if we havent accessed it yet, init it.
        // initDay always returns the rate;
    }

    public boolean hasAvailability(int day, int units) {
        int avail = getAvailability(day);
        int newAvail = avail - units;

        return newAvail >= 0;

    }

    public int scheduleWork(int day, int numUnits) {
        int jobCost = (numUnits / rate) * 300;
        while (numUnits > 0) {

            int availAtDay = getAvailability(day);
            int availNeeded;

            if (availAtDay > numUnits) availNeeded = numUnits;
            else availNeeded = availAtDay;

            numUnits -= availNeeded;

            setAvail(day, availAtDay - availNeeded);

            day++;
        }
        return jobCost;
    }

    public int getProcessingEndDay(int startDay, int units) {
        int day = startDay;
        while (units > 0) {
            int avail = getAvailability(day);
            if (avail > units) break; // this is the day it will end
            units -= avail;
            day++;
        }
        return day;
    }

    @Override
    public String toString() {
        final String BR = System.lineSeparator();
        String str = "";
        // str += "Rate: " + rate + BR; // debugging
        str += "Schedule: " + BR;

        str += "\t      Day  ";

        for (int i = 0; i <= 20; i++) {
            str += i + "  ";
        }

        str += BR + "\tAvailable  ";

        for (int day = 0; day <= 20; day++) {
            int avail = getAvailability(day);
            if (rate >= 10) {
                str += avail + " ";
                if (day > 9) str += " ";

            } else {
                str += avail + "  ";
                if (day > 9) str += " ";
            }
        }


        return str;
    }
}
