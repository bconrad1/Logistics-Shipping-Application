package order;

import common.DataValidationException;
import inventory.Item;
import inventory.ItemFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import scheduling.Schedule;
import scheduling.ScheduleImpl;
import scheduling.ScheduleService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderDataLoader {

    public static void load(String filename) {


        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(filename);

            Document doc = db.parse(xml);

            doc.getDocumentElement().normalize();

            NodeList orderNodeList = doc.getDocumentElement().getChildNodes();


            for (int i = 0; i < orderNodeList.getLength(); i++) {
                if (orderNodeList.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = orderNodeList.item(i).getNodeName();
                if (!entryName.equals("Order")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return;
                }

                // Get a node attribute
                NamedNodeMap aMap = orderNodeList.item(i).getAttributes();
                String orderId = aMap.getNamedItem("id").getNodeValue();

                //System.out.println(storeId);

                Element elem = (Element) orderNodeList.item(i);
                int time = new Integer(elem.getElementsByTagName("time").item(0).getTextContent());
                String dest = elem.getElementsByTagName("dest").item(0).getTextContent();

                // Get all the items for the itemlist
                List<Item> itemsToOrder = new ArrayList<>();

                NodeList itemList = elem.getElementsByTagName("item");
                for (int j = 0; j < itemList.getLength(); j++){

                    if (itemList.item(j).getNodeType() == Node.TEXT_NODE) { continue; }

                    entryName = itemList.item(j).getNodeName();

                    if (!entryName.equals("item")) {
                        System.err.println("Unexpected Node Found "+entryName);
                        return;
                    }

                    elem = (Element) itemList.item(j);

                    String itemId = elem.getElementsByTagName("id").item(0).getTextContent();
                    int quantity = Integer.parseInt(elem.getElementsByTagName("quantity").item(0).getTextContent());

                    Item itemToList = ItemFactory.build(itemId, quantity);

                    itemsToOrder.add(itemToList);


                }

                OrderService os = OrderService.getInstance();
                Order o = new OrderImpl(time, dest, orderId, itemsToOrder);
                os.addOrder(orderId,o);

            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException | DataValidationException e) {
            e.printStackTrace();
        }
    }
}
