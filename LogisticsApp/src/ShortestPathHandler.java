import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ben on 2/7/2016.
 */
public class ShortestPathHandler {


    public static void returnSP(String start, String end, HashMap<String,Facility> facilities){
        FacilityLoader file = new FacilityLoader("FacilitiesXML.xml");

        HashMap<String,Facility> cities = file.returnFacilities();
        Vertex[] vertices = new Vertex[cities.size()];

        int i = 0;

        for(Map.Entry<String, Facility> entry : cities.entrySet()){

            String key = entry.getKey();
            Facility facility = entry.getValue();


            Vertex temp = new Vertex(key);
            vertices[i] = temp;

            i+=1;

        }


        ShortestPath.returnSP(start,end,facilities);





    }
}
