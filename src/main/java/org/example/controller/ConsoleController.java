package org.example.controller;

import org.example.service.XMLService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleController {
    public static Integer getOptionNumber() throws IOException {
        System.out.println("Welcome to XML console application!");
        System.out.println("Please, select option number from menu list:");
        System.out.println("" +
                "[1] - Produce HTML reports\n" +
                "[2] - Produce XML reports\n" +
                "    - Average min/max temperature for each city.\n" +
                "    - Average min/max humidity for each city.\n" +
                "    - Average min/max wind speed for each city.\n" +
                "    - The list of two “coldest” cities for each country.\n" +
                "    - The list of two “most windy” cities for each country.\n"
        );

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        Integer input = Integer.valueOf(reader.readLine());
        return input;
    }

    public static void transformToAnotherFile(Integer optionNumber) throws IOException, TransformerException, XPathExpressionException, ParserConfigurationException, SAXException {
        switch (optionNumber) {
            case 1:
                System.out.println("Producing HTML reports...");
                String xslHtmlName = "weather_transform_1.xsl";
                // Transform XML into HTML reports based on XSL
                XMLService.transformXMLtoHtmlFileBasedOnXSL(xslHtmlName);
                break;
            case 2:
                System.out.println("Producing XML reports...");
                transformToXMLFile();
                break;
            default:
                System.out.println("Sorry, this option is not available currently");
        }
    }

    private static void transformToXMLFile() throws IOException, TransformerException, XPathExpressionException, ParserConfigurationException, SAXException {
        System.out.println("Please, select option number from menu list:");
        System.out.println("" +
                "Produce XML reports\n" +
                "[1]  - Average min/max temperature for each city.\n" +
                "[2]  - Average min/max humidity for each city.\n" +
                "[3]  - Average min/max wind speed for each city.\n" +
                "[4]  - The list of two “coldest” cities for each country.\n" +
                "[5]  - The list of two “most windy” cities for each country.\n"
        );
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        Integer input = Integer.valueOf(reader.readLine());
        String xslHtmlName;
        String xquery = "";
        String xmlFile = "src/main/resources/weather.xml";

        switch (input) {
            case 1:
                System.out.println("Producing XML report on Average min/max temperature for each city.");
//                xquery = "weather_report_1.xquery";
                XMLService.transformXMLtoAnotherFileBasedOnXQuery(xmlFile, xquery);
                break;
            case 2:
                System.out.println("Producing XML report on Average min/max humidity for each city.");
                xslHtmlName = "weather_transform_xml_2.xsl";
                xquery = "";
                XMLService.transformXMLtoAnotherFileBasedOnXQuery(xslHtmlName, xquery);
                break;
            case 3:
                System.out.println("Producing XML report on Average min/max wind speed for each city.");
                xslHtmlName = "weather_transform_xml_3.xsl";
                xquery = "";
                XMLService.transformXMLtoAnotherFileBasedOnXQuery(xslHtmlName, xquery);
                break;
            case 4:
                System.out.println("Producing XML report on The list of two “coldest” cities for each country.");
                xslHtmlName = "weather_transform_xml_4.xsl";
                xquery = "";
                XMLService.transformXMLtoAnotherFileBasedOnXQuery(xslHtmlName, xquery);
                break;
            case 5:
                System.out.println("Producing XML report on The list of two “most windy” cities for each country.");
                xslHtmlName = "weather_transform_xml_5.xsl";
                xquery = "";
                XMLService.transformXMLtoAnotherFileBasedOnXQuery(xslHtmlName, xquery);
                break;
            default:
                System.out.println("Sorry, this option is not available currently");
        }
    }
}
