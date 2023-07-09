package org.example.dao;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class XQueryCategoryDAO {
    private static String url = "jdbc:postgresql://localhost:5432/myWeatherXML";
    private static String dbUserName = "postgres";
    private static String dbPassword = null;

    public static void getCategories() throws SQLException, ClassNotFoundException, TransformerException {
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

        // Process the result set to get the XML data.
        while (resultSet.next()) {
            // Get the XML data as a string.
            String xmlData = resultSet.getString("xml_data");

            // Transform the XML data into a document.
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamSource source = new StreamSource(new StringReader(xmlData));
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
        }

        System.out.println("XML data were selected and transformed....");

        // Close the database connection
        conn.close();
    }
}