import common.DataValidationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class FacilityLoader implements XMLLoader{
    HashMap<String,Facility> facilities = new HashMap<>();

    private String name;


    FacilityLoader(String name){

        readXML(name);
        Facility temp = facilities.get("Chicago, IL");
        //System.out.println(temp.getCost());

    }

    public void readXML(String fileName) {



        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** FacilityLoader File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList storeEntries = doc.getDocumentElement().getChildNodes();


            for (int i = 0; i < storeEntries.getLength(); i++) {
                if (storeEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = storeEntries.item(i).getNodeName();
                if (!entryName.equals("Facility")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return;
                }


                NamedNodeMap aMap = storeEntries.item(i).getAttributes();
                String facilityId = aMap.getNamedItem("Id").getNodeValue();


                Element elem = (Element) storeEntries.item(i);
                String facilityName = elem.getElementsByTagName("Name").item(0).getTextContent();



                String productPerDay = elem.getElementsByTagName("ProductPerDay").item(0).getTextContent();
                String costPerDay = elem.getElementsByTagName("CostPerDay").item(0).getTextContent();


                ArrayList<String> links = new ArrayList<>();
                ArrayList<LinkImpl> connections = new ArrayList<>();
                NodeList cityList = elem.getElementsByTagName("Link");

                for (int j = 0; j < cityList.getLength(); j++) {
                    if (cityList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = cityList.item(j).getNodeName();
                    if (!entryName.equals("Link")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

                    // Get some named nodes
                    elem = (Element) cityList.item(j);
                    String city = elem.getElementsByTagName("City").item(0).getTextContent();
                    String distance = elem.getElementsByTagName("Distance").item(0).getTextContent();
                    double cityDistance = Integer.parseInt(distance);

                    links.add("City: " + city + " --- " + "Distance: " + distance);

                    LinkImpl connection = new LinkImpl(city, cityDistance);

                    connections.add(connection);
                    //System.out.println("The Connection is : "+connection.getCity());
                }

                // Here I would create a Store object using the data I just loaded from the FacilityLoader
               // System.out.println("Facility: " + facilityName + "ID: " + facilityId + "COST: " + costPerDay + "PPD: " + productPerDay + "\n" + links + "\n");

                FacilityFactory facility = new FacilityFactory();



                int cost = Integer.parseInt(costPerDay);
                int product = Integer.parseInt(productPerDay);



                //Create new Facility.
                Facility newFac = facility.createFacility(facilityName,cost,product,connections);

                //Check if the value is in the HashMap already.

                if(facilities.containsKey(facilityName) == false){
                    facilities.put(facilityName,newFac);
                }



        }



        } catch (ParserConfigurationException | SAXException | IOException | DOMException | DataValidationException e) {
            e.printStackTrace();
        }
    }


    HashMap<String,Facility> returnFacilities(){

        return facilities;

    }

}


