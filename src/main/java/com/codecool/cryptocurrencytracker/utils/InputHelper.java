package com.codecool.cryptocurrencytracker.utils;

public class InputHelper {

    public static boolean validateInput(String input){

        String[] inputParts = input.split(" ");

        if(inputParts.length == 2){
            return true;
        }
        return false;
    }

    public static String[] handleInput(String input){

        String[] inputParts = input.split(" ");
        return inputParts;

    }

}
