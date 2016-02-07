import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

public class InventoryDataLoader {

    public static void load(String filename) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File("inventory.xml");

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
                String facilityName = aMap.getNamedItem("id").getNodeValue();

                //System.out.println(storeId);

                Element elem = (Element) facilities.item(i);

                List<Item> itemList = new ArrayList<>();

                NodeList itemsInFac = elem.getElementsByTagName("item");

                for (int j = 0; j < itemsInFac.getLength(); j++) {

                    elem = (Element) itemsInFac.item(j);

                    String itemid = elem.getElementsByTagName("id").item(0).getTextContent();
                    int itemQ = new Integer(elem.getElementsByTagName("quantity").item(0).getTextContent());

                    //System.out.println(itemid);
                    //System.out.println(itemQ);

                    Item testItem = ItemFactory.build(itemid, itemQ);
                    itemList.add(testItem);
                }


                Inventory inv = new InventoryImpl(itemList);
                InventoryService.addInventory(facilityName, inv);

            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException | DataValidationException e) {
            e.printStackTrace();
        }
    }
}
