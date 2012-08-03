package se.exsolvi.maven.plugin.eclipseconfigurator.configuration;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class EclipseCodeFormatter {

    private String name;
    private String content;

    public String getContent() {
        return content;
    }

    public EclipseCodeFormatter setContent(String content) {
        this.content = content;
        return this;
    }

    public String getName() {
        return name;
    }

    public EclipseCodeFormatter setName(String name) {
        this.name = name;
        return this;
    }

    public EclipseCodeFormatter importFormXML(String xml) {
        return this;
    }

    public static Map<String, EclipseCodeFormatter> parseCodeFormatters(String formatterProfilesXML) {
        Map<String, EclipseCodeFormatter> formatters = new HashMap<String, EclipseCodeFormatter>();

        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder build = fact.newDocumentBuilder();
            Document doc = build.parse(new InputSource(new StringReader(formatterProfilesXML)));
            Node node = doc.getDocumentElement();
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node childNode = children.item(i);
                if ("profile".equals(childNode.getNodeName())
                        && "CodeFormatterProfile".equals(childNode.getAttributes().getNamedItem("kind").getNodeValue())) {
                    EclipseCodeFormatter formatter = new EclipseCodeFormatter().setName(childNode.getAttributes()
                            .getNamedItem("name").getNodeValue());
                    formatter.setContent(childNode.toString());
                    formatters.put(formatter.getName(), formatter);
                }
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return formatters;
    }
}
