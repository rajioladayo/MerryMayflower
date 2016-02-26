package com.rharj.merrymayflower.xmlparser;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

    public static XmlValueModels search(InputStream inputStream, String id){
        XmlValueModels xmlValueModels = null;
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "/contents/item[@id='" + id + "']";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc,XPathConstants.NODESET);// (expr.evaluate(doc, XPathConstants.NODE));
            for(int i= 0; i<nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element)node;
                    xmlValueModels = new XmlValueModels();
                    xmlValueModels.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
                    xmlValueModels.setDetails(element.getElementsByTagName("details").item(0).getTextContent());
                    xmlValueModels.setAuthor(element.getElementsByTagName("author").item(0).getTextContent());
                    xmlValueModels.setId(element.getAttribute("id"));
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return xmlValueModels;
    }
}
