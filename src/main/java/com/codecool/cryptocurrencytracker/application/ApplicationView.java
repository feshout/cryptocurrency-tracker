package com.codecool.cryptocurrencytracker.application;

import java.util.Scanner;

public class ApplicationView {

    private static Scanner scanner = new Scanner(System.in);

    public static void printMainMenu(){

        System.out.println("1. Show all currencies info");
        System.out.println("2. Show selected currencies info");
        System.out.println("3. Select currencies to track");
        System.out.println("4. Show currency history");
        System.out.println("5. Exit");
    }

    public static String getInput(String message) {

        System.out.println(message);
        String input = scanner.next();
        return input;
    }

    public static void printMessage(String message){
        System.out.println(message);
    }

}
