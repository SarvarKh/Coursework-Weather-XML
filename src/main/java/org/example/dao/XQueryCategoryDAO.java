package org.example.dao;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;


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
        String sql = "SELECT xml_data FROM weather";
        ResultSet resultSet = statement.executeQuery(sql);

        // Iterate over the results and serialize each row to an XML document
        File file = new File("src/main/resources/weatherQueriedFromDb.xml");
        FileWriter fileWriter = new FileWriter(file);
        while (resultSet.next()) {
            String xmlData = resultSet.getString("xml_data");
            fileWriter.write(xmlData);
        }
        fileWriter.close();

        // Close the database connection
        conn.close();
    }
}