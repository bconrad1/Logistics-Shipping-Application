package sch;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import common.DataValidationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ScheduleDataLoader {


    public static void load(String filename) {


        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(filename);

            Document doc = db.parse(xml);

            doc.getDocumentElement().normalize();

            NodeList facilities = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < facilities.getLength(); i++) {
                if (facilities.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = facilities.item(i).getNodeName();
                if (!entryName.equals("Facility")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return;
                }

                // Get a node attribute
                NamedNodeMap aMap = facilities.item(i).getAttributes();
                String facilityName = aMap.getNamedItem("loc").getNodeValue();

                //System.out.println(storeId);

                Element elem = (Element) facilities.item(i);
                int rate = new Integer(elem.getElementsByTagName("rate").item(0).getTextContent());



                ScheduleService schedules = ScheduleService.getInstance();
                Schedule sch = new ScheduleImpl(rate);
                schedules.addSchedule(facilityName, sch);

            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException | DataValidationException e) {
            e.printStackTrace();
        }
    }

}