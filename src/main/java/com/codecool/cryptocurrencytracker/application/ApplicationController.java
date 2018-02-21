package com.codecool.cryptocurrencytracker.application;

import com.codecool.cryptocurrencytracker.currency.Currency;
import com.codecool.cryptocurrencytracker.services.DataAccess;

import java.util.LinkedList;
import java.util.List;

public class ApplicationController {

    private List<String> selectedCurrenciesSymbols = new LinkedList<>();
    private List<Currency> selectedCurrencies = new LinkedList<>();

    public void start(){

        DataAccess dataAccess = new DataAccess();
        String input = "";

        while(input.equals("5")){

            input = ApplicationView.getInput("Select option: ");

            if(input.equals("1")){
//                Tutaj printowanie całości
            }

            if(input.equals("2")){

                for (String currencySymbol : selectedCurrenciesSymbols){
                    Currency currency = dataAccess.getData().get(currencySymbol);
                    selectedCurrencies.add(currency);
                }
                //Tutaj printowanie

            }

            if(input.equals("3")){

                String selectedCurrency = ApplicationView.getInput("Enter currency symbol eg. BTC, ETH").toUpperCase();
                if(dataAccess.getData().containsKey(selectedCurrency)){
                    selectedCurrenciesSymbols.add(selectedCurrency);
                } else {
                    ApplicationView.printMessage("No such currency in database");
                }
            }

            if(input.equals("4")){
//                tutaj show history
            }

        }
    }
}
