package com.example.test_soap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

@RestController
public class Controller {

    @GetMapping("/")
    public String start(@RequestParam(value = "xml") String xml) throws Exception {
        System.out.println(xml);
       // Document doc = convertToCdata(xml);
        return convertToCdata(xml);

    }
//    private Document convertToCdata(String data) throws ParserConfigurationException, IOException, SAXException {
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        dbf.setValidating(true);
//        DocumentBuilder db = dbf.newDocumentBuilder();
//        Document doc = db.parse(new InputSource(new StringReader(data)));
//        NodeList el = doc.getElementsByTagName("person");
//
//        //System.out.println(el.item(0));
//
//        NodeList elements = doc.getDocumentElement().getChildNodes();
//
//        for (int i = 0; i < elements.getLength(); i++) {
//            Node node = elements.item(i);
//           // System.out.println(node.getNodeName());
//
//            if(node.hasChildNodes()){
////                for (int j = 0; j < node.getChildNodes().getLength(); j++) {
////                    CDATASection cdata = doc.createCDATASection(node.getChildNodes().item(j).getTextContent());
////                    node.getChildNodes().item(j).setTextContent(String.valueOf(cdata));
////                }
//            }
//            if(!node.hasChildNodes()){
//                String name = node.getNodeName();
//               // doc.getDocumentElement().setAttribute("a", "aboba");
////                CDATASection cdata = doc.createCDATASection(node.getTextContent());
////                elements.item(i).setTextContent(String.valueOf(cdata));
//            }
//            if(!node.getNodeName().equals("#text")){
//                ///System.out.println("aaaa");
//                doc.getDocumentElement().setAttribute(node.getNodeName(), node.getTextContent());
//                doc.getDocumentElement().removeChild(elements.item(i));
//            }
//        }
//        // System.out.println(data);
//
//        try {
//            prettyPrint(doc);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return doc;
//
//    }

  /*  public static String prettyPrint(Document xml) throws Exception {
        StringWriter writer = new StringWriter();
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));

        tf.transform(new DOMSource(xml), new StreamResult(writer));
        System.out.println(out);
        return writer.toString();
    }
*/

    private String convertToCdata(String data) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new InputSource(new StringReader(data)));

        NodeList elements = doc.getDocumentElement().getChildNodes();

        for (int i = 0; i < elements.getLength(); i++) {
            convertNode(elements.item(i), doc);
        }

        StringWriter writer = new StringWriter();
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(doc), new StreamResult(out));
        tf.transform(new DOMSource(doc), new StreamResult(writer));
        System.out.println(data);
        return writer.toString();
    }

    private void convertNode(Node node, Document doc){
        if(!node.hasChildNodes()){
            Node parent = node.getParentNode();
            System.out.println(node.getNodeName());
            ((Element) parent).setAttribute(node.getNodeName(), node.getTextContent());
            parent.removeChild(node);
        }
        else {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                convertNode(node.getChildNodes().item(i), doc);
            }
        }
    }

    /*TransformerFactory factory = TransformerFactory.newInstance();
    Source xslt = new StreamSource(new File("article1a.xsl"));
    Transformer transformer = factory.newTransformer(xslt);
    Source xml = new StreamSource(new File("article1.xml"));
    transformer.transform(xml, new StreamResult(new File("output.xml")));*/
}
