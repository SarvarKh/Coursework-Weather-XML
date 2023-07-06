package org.example.controller;

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
}
