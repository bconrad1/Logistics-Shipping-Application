import common.DataValidationException;


public class XMLFactory {

    public void xmlFactory(String filename)throws DataValidationException{

        String name = filename.toLowerCase();


        if(filename.equals("facilitiesxml")|| filename.contains("facilitiesxml")){
                FacilityLoader facLoad =  new FacilityLoader(name);
                facLoad.readXML(name);

        }else if(filename.equals("itemxml")|| filename.contains("itemxml")){


        }else if(filename.equals("orderxml")|| filename.contains("orderxml")){


        }else if(filename.equals("facilityinvxml")|| filename.contains("facilityinvxml")){

        }else{
            System.out.println("You didn't specifiy the correct filename.");
        }





    }


}
