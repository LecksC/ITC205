package datamanagement;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.JDOMException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;

public class XMLManager
{
    private static XMLManager instance_ = null;
    private Document document_;



    public static XMLManager getInstance()
    {
        if (instance_ == null) {
            instance_ = new XMLManager();
        }
        return instance_;
    }



    private XMLManager()
    {
        initialize();
    }



    public void initialize()
    {
        String xmlFilename = AppProperties.getInstance().getProperties().getProperty("XMLFILE");
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setExpandEntities(true);
            document_ = saxBuilder.build(xmlFilename);
        }
        catch (JDOMException jdomException) {
            System.err.printf("%s", "DBMD: XMLManager : init : caught JDOMException\n");
            throw new RuntimeException("DBMD: XMLManager : init : JDOMException");
        }
        catch (IOException exception) {
            System.err.printf("%s", "DBMD: XMLManager : init : caught IOException\n");
            throw new RuntimeException("DBMD: XMLManager : init : IOException");
        }
    }



    public Document getDocument()
    {
        return document_;
    }



    public Element[] getDatabaseRecords(String tableName, String childName)
    {
        Object[] records = document_.getRootElement().getChild(tableName).getChildren(childName).toArray();
        Element[] elementArray = Arrays.copyOf(records, records.length, Element[].class);
        return elementArray;
    }



    public void saveDocument()
    {
        String xmlFilename = AppProperties.getInstance().getProperties().getProperty("XMLFILE");
        try (FileWriter outputFile = new FileWriter(xmlFilename)) {
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(document_, outputFile);
            outputFile.close();
        }
        catch (IOException inputOutputException) {
            System.err.printf("%s\n", "DBMD : XMLManager : saveDocument : error saving XML to " + xmlFilename);
            throw new RuntimeException("DBMD: XMLManager : saveDocument : error writing to file");
        }
    }
}
