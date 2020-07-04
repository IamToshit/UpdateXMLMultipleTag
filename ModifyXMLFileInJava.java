import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ModifyXMLFileInJava {
    public void modify(String input, String output) {
        String filePath = input;
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();

            // parse xml file and load into document
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            Document doc_1 = dBuilder.parse(xmlFile);
            doc_1.getDocumentElement().normalize();

            // update Element value
            updateElementValue(doc);
            updateElementValue(doc_1);

            // delete element
            //deleteElement(doc);

            // add new element
            //addElement(doc);

            // write the updated document to file or console
            writeXMLFile(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeXMLFile(Document doc)
            throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        //String output_folder_path = out
        StreamResult result = new StreamResult(new File("C:\\Folder_A\\D\\F\\input_updated_xml.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        System.out.println("XML file updated successfully");
    }
    public static String getDateTime()
    {
        //System.out.println("getDateTime in xmlreplace");
        java.util.Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy HH:mm:ss.$");
        String format = formatter.format(date);
        return format;
    }


    /**
     * Update firstName element value to Upper case.
     * @param doc
     */
    private static void updateElementValue(Document doc) {

        NodeList Users = doc.getElementsByTagName("Users");
        Element emp = null;

        for(int z=0; z<Users.getLength();z++){
            emp = (Element) Users.item(z);

            emp.setAttribute("ASOF_DATE", getDateTime());
            emp.setAttribute("CREATE_DATE", getDateTime());

            NodeList users = doc.getElementsByTagName("User");
            Element user = null;

            // loop for each user
       for (int i = 0; i < users.getLength(); i++) {
            //Element e1 = (Element) nodes.item();
            user = (Element) users.item(i);
            //Node name = user.getElementsByTagName("firstName").item(0).getFirstChild();
            //name.setNodeValue(name.getNodeValue().toUpperCase());
            String name = user.getElementsByTagName("version").item(0).getTextContent();
            Node nodeArr = user.getElementsByTagName("version").item(0);
            nodeArr.setTextContent(String.valueOf(Integer.parseInt(name)+1));

            String name_1 = user.getElementsByTagName("touchcount").item(0).getTextContent();
            Node nodeArr_1 = user.getElementsByTagName("touchcount").item(0);
            nodeArr_1.setTextContent(String.valueOf(Integer.parseInt(name_1)+1));

            String name_2 = user.getElementsByTagName("SEC_DESC1").item(0).getTextContent();
            Node nodeArr_2 = user.getElementsByTagName("SEC_DESC1").item(0);
            nodeArr_2.setTextContent(name_2 + " " + getDateTime());

           NodeList odr_hist = doc.getElementsByTagName("odr_hist");
           Element odr_hist_1 = null;

           odr_hist_1 = (Element) odr_hist.item(i);
           String name_3 = odr_hist_1.getElementsByTagName("SEC_DESC1").item(0).getTextContent();
           Node nodeArr_3 = odr_hist_1.getElementsByTagName("SEC_DESC1").item(0);
           nodeArr_3.setTextContent(name_3 + " " + getDateTime());

            }

        }

        }
    }

