package org.example.service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class XMLService {
    public static void transformXMLtoAnotherFileBasedOnXSL(String xslFileName) throws TransformerException, FileNotFoundException {
        String xslFilePath = "src/main/resources/"+xslFileName;
        String xmlFilePath = "src/main/resources/weather.xml";
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xslFilePath));

        String outputFile = "outputWeather.html";
        transformer.transform(new StreamSource(xmlFilePath), new StreamResult(new FileOutputStream(new File(outputFile))));
        System.out.println("=> "+ xslFileName +" have been transformed into " + outputFile);
        System.out.println("=> "+ outputFile +" file has been created in project's root directory");
    }

    private static String defineOutPutType(String xslFilePath, String outputType) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(xslFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("<xsl:param name=\"outputType\"")) {
                    int startIndex = line.indexOf("select=\"") + 8;
                    int endIndex = line.indexOf("\"", startIndex);
                    outputType = line.substring(startIndex, endIndex);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (outputType == null) {
            outputType = "txt";
        }
        return outputType;
    }

    public static String xquery(String query, Document doc) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        // Create a XPath factory
        XPathFactory factory = XPathFactory.newInstance();

        // Create a XPath object
        XPath xpath = factory.newXPath();

        // Get the input XML file
        File inputFile = new File("countries.xml");

        // Create a DocumentBuilderFactory object
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        // Create a DocumentBuilder object
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        // Parse the input XML file
        org.w3c.dom.Document document = documentBuilder.parse(inputFile);

        // Get the XPath expression
        String expression = "for $country in doc('countries.xml')/countries" +
                " for $city in $country/cities" +
                " for $day in $city/days" +
                " where $day/name = 'Monday'" +
                " return $day/temperature";

        // Evaluate the XPath expression
        Object result = xpath.evaluate(expression, document, XPathConstants.NODESET);

        // Print the result
        PrintWriter writer = new PrintWriter(System.out);
        for (org.w3c.dom.Node node : (Node[]) result) {
            writer.println(node.getTextContent());
        }
        writer.flush();

        return result.toString();
    }
}
