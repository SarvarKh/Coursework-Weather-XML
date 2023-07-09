package org.example.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class XQueryCategoryDAO {
    private static String url = "jdbc:postgresql://localhost:5432/myWeatherXML";
    private static String dbUserName = "postgres";
    private static String dbPassword = null;

    public static void getCategories() throws SQLException, ClassNotFoundException, TransformerException, IOException, ParserConfigurationException {
        // Connect to the Postgresql database
        Class.forName("org.postgresql.Driver");
        System.out.println("Postgres JDBC Driver Registered!");

        // get hold of the DriverManager
        Connection conn = DriverManager.getConnection(url, dbUserName, dbPassword);

        // Create a statement object.
        Statement statement = conn.createStatement();

        // Execute the statement to select the XML data.
        String sql = "SELECT * FROM weather";
        ResultSet resultSet = statement.executeQuery(sql);

        // Create the `countriesDb.xml` file and add content select from DB
        createCountryDbXML(resultSet);

        // Close the database connection
        conn.close();
    }

    private static void createCountryDbXML(ResultSet resultSet) throws IOException, ParserConfigurationException, SQLException {
        // Create the `countries.xml` file.
        File file = new File("countriesDb.xml");
        FileWriter writer = new FileWriter(file);

        // Create a DOM document.
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        // Create the root element.
        Element rootElement = document.createElement("countries");
        document.appendChild(rootElement);

        // Iterate over the result set and add countries to the XML document.
        while (resultSet.next()) {
            String xmlData = resultSet.getString("xml_data");
            System.out.println(xmlData);
            Element countriesElement = document.createElement("countries");
            rootElement.appendChild(countriesElement);

            // Add the country name to the XML document.
            Element nameElement = document.createElement("name");
            nameElement.appendChild(document.createTextNode(xmlData));
            countriesElement.appendChild(nameElement);

            // Add the city names to the XML document.
            String[] cities = xmlData.split(",");
            for (String city : cities) {
                Element cityElement = document.createElement("city");
                countriesElement.appendChild(cityElement);

                // Add the city name to the XML document.
                Element cityNameElement = document.createElement("name");
                cityNameElement.appendChild(document.createTextNode(city));
                cityElement.appendChild(cityNameElement);
            }
        }

        // Write the XML document to the file.
        writer.write(document.toString());
        writer.close();
    }


}