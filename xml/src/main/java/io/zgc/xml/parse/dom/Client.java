package io.zgc.xml.parse.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class Client {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputStream is = Client.class.getClassLoader().getResourceAsStream("applicationContext.xml");
        Document document = documentBuilder.parse(is);
        Element rootElement = document.getDocumentElement();
        System.out.println(rootElement);
    }
}
