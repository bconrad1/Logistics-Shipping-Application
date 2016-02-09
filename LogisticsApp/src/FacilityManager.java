import common.DataValidationException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class FacilityManager {


    public static void main(String[] args) {

    }
    public void loadFacilities()throws DataValidationException{
        String facFile = "FacilitiesXML.xml";

        FacilityLoader file = new FacilityLoader(facFile);
        HashMap<String, Facility> cities = file.returnFacilities();

        ArrayList<Vertex> shortestPath = ShortestPathHandler.returnSP("Boston, MA","Chicago, IL", cities);


        String printout = spOut(shortestPath);

    }


    public String spOut(ArrayList<Vertex> path){

        Vertex end = path.get(path.size()-1);
        double totalDistance = end.minDistance;

        StringBuilder finalPrint = new StringBuilder();
        String startCity = path.get(0).name;
        String endCity = end.name;
        finalPrint.append(startCity + " to "+endCity+":\n\n");


        for (int i =0; i<path.size(); i++){



            String city = path.get(i).name;



            if(i!=path.size()-1){
                finalPrint.append(city + "--> ");
            }else{
                finalPrint.append(city + " = "+ totalDistance + " Miles\n");
            }

        }


        //Since its a static 8hours * 50mph it equals 400 miles a day.

        double totalDays = totalDistance/400;
        DecimalFormat df = new DecimalFormat("#.##");
        totalDays = Double.valueOf(df.format(totalDays));

        finalPrint.append(totalDistance + " / (8 hours per day * 50mph) = " +totalDays+"\n");

        return finalPrint.toString();



    }

    public void facilityOutput(Facility facility){

        StringBuilder finalOut = new StringBuilder();

        String CRLF = "\n\n";

        //Name of Current City
        finalOut.append(facility.getName()+ CRLF);
        finalOut.append("DIRECT LINKS:  ");


        ArrayList<LinkImpl> connections = facility.getConnections();


        for(LinkImpl l: connections){

            double totalDays = l.getDistance()/400;
            DecimalFormat df = new DecimalFormat("#.##");
            totalDays = Double.valueOf(df.format(totalDays));

            finalOut.append(l.getCity() + " ("+totalDays+"d); ");

        }
        finalOut.append("\n\n");
        System.out.println(finalOut.toString());
    }
}
