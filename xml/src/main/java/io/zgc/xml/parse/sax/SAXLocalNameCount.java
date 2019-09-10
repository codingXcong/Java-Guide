package io.zgc.xml.parse.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SAXLocalNameCount extends DefaultHandler {

    private Map<String,Integer> tags;

    @Override
    public void startDocument() throws SAXException {
        tags = new ConcurrentHashMap();
    }

    @Override
    public void endDocument() throws SAXException {
        Set<String> keySet = tags.keySet();
        for (String tag : keySet){
            Integer count = tags.get(tag);
            System.out.println("Local Name \"" + tag + "\" occurs "
                    + count + " times");
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String key = localName;
        Integer count = tags.get(key);
        if (count == null) {
            tags.put(key,1);
        } else {
            tags.put(key,count++);
        }
    }

    public static void main(String[] args) throws Exception {
        String filename = null;
        for (int i = 0; i < args.length; i++) {
            filename = args[i];
            if (i != args.length - 1) {
                usage();
            }
        }
        if (filename == null) {
            usage();
        }

        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(new SAXLocalNameCount());
        xmlReader.setErrorHandler(new MyErrorHandler(System.err));
        xmlReader.parse(convertToFileURL(filename));
        //xmlReader.parse("file:///D:/Java-Guide/xml/target/classes/rich.xml");
    }



    private static void usage() {
        System.err.println("Usage: SAXLocalNameCount <file.xml>");
        System.err.println("       -usage or -help = this message");
        System.exit(1);
    }

    private static String convertToFileURL(String filename) {
        String path = SAXLocalNameCount.class.getClassLoader().getResource(filename).getPath();
        //String path = new File(filename).getAbsolutePath();
        System.out.println(path);
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }
}
