package com.rharj.merrymayflower.xmlparser;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.helpers.DefaultHandler;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.rharj.merrymayflower.model.XmlValueModels;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Raji Oladayo on 2/4/2015.
 */
public class XMLParser extends DefaultHandler {

    List<XmlValueModels> xmlModel;
    private XmlValueModels xModel;
    private String text;

    public XMLParser(){

        xmlModel = new ArrayList<XmlValueModels>();
    }

    public List<XmlValueModels> getXmlModel() {
        return xmlModel;
    }

    public List<XmlValueModels> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("item")) {
                            // create a new instance of employee
                            xModel = new XmlValueModels();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("item")) {
                            // add employee object to list
                            xmlModel.add(xModel);
                        } else if (tagname.equalsIgnoreCase("id")) {
                            xModel.setId(text);
                        } else if (tagname.equalsIgnoreCase("title")) {
                            xModel.setTitle(text);
                        } else if (tagname.equalsIgnoreCase("details")) {
                            xModel.setDetails(text);
                        } else if(tagname.equalsIgnoreCase("author")){
                            xModel.setAuthor(text);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return xmlModel;
    }

    public void search(String filename, String id) throws Exception {
        // Parse into a DOM tree
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(filename);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        XPathExpression expr = xPath.compile("/contents/item/id[text() = '" + id + "']");
        NodeList nodeList = (NodeList) (expr.evaluate(doc, XPathConstants.NODESET));
        if(nodeList.getLength() == 1){
            // we have found an element 'id'
            Node parent = nodeList.item(0).getParentNode();
            //This is the <item> node
        }else{
            //there is no such element
        }
        
    }

}
