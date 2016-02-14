package facility;

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


public class FacilityLoader {




    public static void load(String fileName) {



        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** facility.FacilityLoader File '" + fileName + "' cannot be found");
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


                //ArrayList<String> links = new ArrayList<>();
                ArrayList<Link> connections = new ArrayList<>();
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


                    //Create new connection for each link in the XML.
                    Link connection = new LinkImpl(city, cityDistance);
                    //Add connection to Arraylist for each city.
                    connections.add(connection);


                    //System.out.println("The Connection is : "+connection.getCity());
                }

                // Here I would create a Store object using the data I just loaded from the facility.FacilityLoader
                // System.out.println("facility.Facility: " + facilityName + "ID: " + facilityId + "COST: " + costPerDay + "PPD: " + productPerDay + "\n" + links + "\n");


                int cost = Integer.parseInt(costPerDay);
                int product = Integer.parseInt(productPerDay);





                //Using the facilityService singleton, I create a new facility for each city.
                FacilityService fs = FacilityService.getInstance();
                Facility fac = new FacilityImpl(facilityName,cost,product,connections);

                //Then using the facade, I use the service to add the city to a collection.
                fs.addFacility(facilityName,fac);



        }



        } catch (ParserConfigurationException | SAXException | IOException | DOMException | DataValidationException e) {
            e.printStackTrace();
        }
    }




}


