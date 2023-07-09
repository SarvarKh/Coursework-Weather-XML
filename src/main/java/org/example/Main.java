package org.example;

import org.example.controller.ConsoleController;
import org.example.dao.XQueryCategoryDAO;
import org.example.service.XMLService;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {
    public static final String DATADIR = "src/main/resources/";

    public static void main(String[] args) throws Exception {
        System.out.println("Starting application...\n-=-=-=-=-*****-=-=-=-=-");

        // 0. Accessing database to select XML data with XQuery...
        System.out.println("> Accessing database to select XML data with XQuery...");
        XQueryCategoryDAO.getCategories();
        System.out.println("> Creating XML file based on selected xml_data from database...\n" +
                "> New XML file has been created at 'src/main/resources/weatherQueriedFromDb.xml'");

        // 1. Select options from Menu
        Integer optionNumber = ConsoleController.getOptionNumber();

        // 2. Transform XML into another file based on XSL
        switch (optionNumber) {
            case 1:
                System.out.println("Producing HTML reports...");
                String xslHtmlName = "weather_transform_1.xsl";
                // Transform XML into HTML reports based on XSL
                XMLService.transformXMLtoAnotherFileBasedOnXSL(xslHtmlName);
                break;
            case 2:
                System.out.println("Producing XML reports...");
                String xslXmlName = "weather_transform_2.xsl";
                // Transform XML into another XML reports based on XSL
                XMLService.transformXMLtoAnotherFileBasedOnXSL(xslXmlName);
                break;
            default:
                System.out.println("Sorry, this option is not available currently");
        }

        System.out.println("Ending application...\n-=-=-=-=-*****-=-=-=-=-");
    }
}