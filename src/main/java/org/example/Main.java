package org.example;

import org.example.controller.ConsoleController;
import org.example.service.XMLService;

public class Main {
    public static final String DATADIR = "src/main/resources/";

    public static void main(String[] args) throws Exception {
        System.out.println("Starting application...\n-=-=-=-=-*****-=-=-=-=-");
        // 0. Select options from Menu
        Integer optionNumber = ConsoleController.getOptionNumber();

        // 1. Transform XML into another file based on XSL
        switch (optionNumber) {
            case 1:
                System.out.println("Producing HTML reports...");
                String xslHtmlName = "weather_html.xsl";
                // Transform XML into HTML reports based on XSL
                XMLService.transformXMLtoAnotherFileBasedOnXSL(xslHtmlName);
                break;
            case 2:
                System.out.println("Producing XML reports...");
                String xslXmlName = "weather_xml.xsl";
                // Transform XML into another XML reports based on XSL
                XMLService.transformXMLtoAnotherFileBasedOnXSL(xslXmlName);
                break;
            default:
                System.out.println("Sorry, this option is not available currently");
        }

        System.out.println("Ending application...\n-=-=-=-=-*****-=-=-=-=-");
    }
}