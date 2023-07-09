package org.example.service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.io.File;
import java.io.IOException;

public class XMLService {
    public static void transformXMLtoAnotherFileBasedOnXSL(String xslFileName) throws TransformerException, FileNotFoundException {
        String xslFilePath = "src/main/resources/"+xslFileName;
        String xmlFilePath = "src/main/resources/weather.xml";
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xslFilePath));

        String outputType = defineOutPutType(xslFilePath, null);

        String outputFile = "outputOf"+xslFileName.replaceAll(".xsl", "")+"."+outputType;
        transformer.transform(new StreamSource(xmlFilePath), new StreamResult(new FileOutputStream(new File(outputFile))));
        System.out.println("=> "+ xslFileName +" have been transformed into another format.");
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
}
