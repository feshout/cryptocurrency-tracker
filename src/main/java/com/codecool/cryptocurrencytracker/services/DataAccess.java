package com.codecool.cryptocurrencytracker.services;

import com.codecool.cryptocurrencytracker.currency.Currency;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class DataAccess implements Runnable{

    private List<String> selectedCurrenciesSymbols = new LinkedList<>();
    private Map<String, Currency> currencyMap = new HashMap<>();
    private RestTemplate restTemplate = new RestTemplate();
    private PrintData printData;
    private Thread thread;

    public DataAccess(PrintData printData) {
        this.printData = printData;
        thread = new Thread(this);
        thread.start();
    }

    public void run(){

        boolean isRunning = true;
        while(isRunning){

            try {
                updateData();
                Thread.sleep(8000);
                setDataToPrint();
            } catch (InterruptedException e) {
                isRunning = false;
            }
        }
    }

    private void updateData(){

        ResponseEntity<Currency[]> responseEntity = restTemplate.getForEntity("https://api.coinmarketcap.com/v1/ticker/", Currency[].class);
        Currency[] currencies = responseEntity.getBody();
        for (int i = 0; i < currencies.length ; i++) {
            currencyMap.put(currencies[i].getSymbol(), currencies[i]);
        }
    }

    public Map<String, Currency> getData() {
        return this.currencyMap;
    }

    public void addSelectedCurrenciesSymbol(String symbol){
        this.selectedCurrenciesSymbols.add(symbol);
    }

    public void deleteSymbolFromSelectedCurrenciesSymbols(String symbol){
        this.selectedCurrenciesSymbols.remove(symbol);
    }

    private void setDataToPrint(){

        LinkedList<Currency> selectedCurrencies = new LinkedList<>();
        for (String symbol : selectedCurrenciesSymbols){
            selectedCurrencies.add(currencyMap.get(symbol));
            printData.setDataToPrint(selectedCurrencies);
        }


    }
}
