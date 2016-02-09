/**
 * Created by Ben on 2/6/2016.
 */
public class XMLFactory {

    public void xmlFactory(String filename){

        String name = filename.toLowerCase();


        if(filename.equals("facilitiesxml")|| filename.contains("facilitiesxml")){
                FacilityLoader facLoad =  new FacilityLoader(name);
                facLoad.readXML(name);

        }else if(filename.equals("itemxml")|| filename.contains("itemxml")){


        }else if(filename.equals("orderxml")|| filename.contains("orderxml")){


        }else if(filename.equals("facilityinvxml")|| filename.contains("facilityinvxml")){

        }





    }


}
