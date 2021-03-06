package facility;

import common.DataValidationException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ShortestPathHandler {


    public static String returnSP(String start, String end, ArrayList<Facility> facilities) throws DataValidationException {

        String spFinal = spOut(ShortestPath.returnSP(start, end, facilities));
        return spFinal;
    }


    //Prints the total distance for shortest path.
    public static double totalDistance(String start, String end, ArrayList<Facility> facilities) {
        ArrayList<Vertex> total = ShortestPath.returnSP(start, end, facilities);
        Vertex last = total.get(total.size() - 1);
        double totalDistance = last.minDistance;
        return totalDistance;
    }

    public static String spOut(ArrayList<Vertex> path) {

        //Get the shortest path last node.
        Vertex end = path.get(path.size() - 1);
        //Use that node to get stored distance.
        double totalDistance = end.minDistance;


        //Formatting the print statement for shortest path.
        StringBuilder finalPrint = new StringBuilder();
        String startCity = path.get(0).name;
        String endCity = end.name;
        finalPrint.append(startCity + " to " + endCity + ":\n\n");


        for (int i = 0; i < path.size(); i++) {


            String city = path.get(i).name;


            if (i != path.size() - 1) {
                finalPrint.append(city + "--> ");
            } else {
                finalPrint.append(city + " = " + totalDistance + " Miles\n");
            }

        }


        //Since its a static 8hours * 50mph it equals 400 miles a day.

        double totalDays = totalDistance / 400;
        DecimalFormat df = new DecimalFormat("#.##");
        totalDays = Double.valueOf(df.format(totalDays));

        finalPrint.append(totalDistance + " / (8 hours per day * 50mph) = " + totalDays + " Days\n\n");

        return finalPrint.toString();


    }

}
