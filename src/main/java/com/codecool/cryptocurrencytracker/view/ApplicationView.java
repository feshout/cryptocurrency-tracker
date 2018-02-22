package com.codecool.cryptocurrencytracker.application;

import java.util.Scanner;

public class ApplicationView {

    private static Scanner scanner = new Scanner(System.in);


    public static String getInput(String message) {

        System.out.println(message);
        String input = scanner.nextLine();
        return input;
    }

    public static void printMessage(String message){
        System.out.println(message);
    }
}
