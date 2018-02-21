package com.codecool.cryptocurrencytracker.services;

import com.codecool.cryptocurrencytracker.currency.Currency;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class DataAccess implements Runnable{

    private Map<String, Currency> currencyMap = new HashMap<>();
    private RestTemplate restTemplate = new RestTemplate();
    private Thread thread;

    public DataAccess() {
        thread = new Thread(this);
        thread.start();
    }

    public void run(){

        boolean isRunning = true;

        while(isRunning){

            try {
                updateData();
                Thread.sleep(20000);
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
}
