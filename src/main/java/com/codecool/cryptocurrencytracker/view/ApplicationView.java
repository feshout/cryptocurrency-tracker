package com.codecool.cryptocurrencytracker.view;

import java.util.Scanner;

public class ApplicationView {

    private static Scanner scanner = new Scanner(System.in);


    public static String getInput() {

        String input = scanner.nextLine();
        return input;
    }

    public static void pause(){
        System.out.println("Press Enter to continue");
        scanner.next();
    }

    public static void printMessage(String message){
        System.out.println(message);
    }
}
