package org.example;

import org.example.controller.ConsoleController;

public class Main {
    public static final String DATADIR = "src/main/resources/";

    public static void main(String[] args) throws Exception {
        System.out.println("Starting application...\n-=-=-=-=-*****-=-=-=-=-");
        // 0. Select options from Menu
        Integer optionNumber = ConsoleController.getOptionNumber();




        System.out.println("Ending application...\n-=-=-=-=-*****-=-=-=-=-");
    }
}