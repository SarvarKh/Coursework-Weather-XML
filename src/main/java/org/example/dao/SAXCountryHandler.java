package org.example.dao;

import org.example.dto.Country;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXCountryHandler extends DefaultHandler {
    private List<Country> data;
    private Country country;
    private String currentElement = "";
    private StringBuilder currentText;


    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start reading document");
        data = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End reading document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Start element: " + qName);
        currentElement = qName;
        switch (currentElement) {
            case "countries":
                break;
            case "country":
                String name = attributes.getValue("name");
                country = new Country(name);
                data.add(country);
//            case "cities":
//                boolean recommended = Boolean.parseBoolean(attributes.getValue("value"));
//                product.setRecommended(recommended);
            default:
                currentText = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println("End element: " + qName);
        switch (currentElement) {
            case "categories":
            case "category":
            case "products":
            case "product":
            case "price":
                break;
//            default:
//                String content = currentText.toString();
//                switch (currentElement) {
//                    case "name":
//                        product.setName(content);
//                        break;
//                    case "currency":
//                        product.setCurrency(Currency.valueOf(content));
//                        break;
//                    case "amount":
//                        product.setAmount(Double.valueOf(content));
//                        break;
//                    case "availability":
//                        product.setAvailability(Integer.parseInt(content));
//                        break;
//                    case "onSales":
//                        product.setOnSales(content);
//                        break;
//                }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentText != null) {
            currentText.append(ch, start, length);
        }
//        System.out.println("Characters");
    }
}
