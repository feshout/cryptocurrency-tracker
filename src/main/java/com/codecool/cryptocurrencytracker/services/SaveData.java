package com.codecool.cryptocurrencytracker.services;

import com.codecool.cryptocurrencytracker.currency.Currency;
import com.codecool.cryptocurrencytracker.currency.CurrencyRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SaveData implements Runnable {

    private CurrencyRepository currencyRepository;
    private DataAccess dataAccess;
    private static Map<String, Currency> currentCurrencyMap = new HashMap<>();

    public SaveData(CurrencyRepository currencyRepository, DataAccess dataAccess) {
        this.currencyRepository = currencyRepository;
        this.dataAccess = dataAccess;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        boolean isRunning = true;
        currentCurrencyMap = dataAccess.getData();

        while(isRunning){

            try {
                Thread.sleep(30000);
                if(!currentCurrencyMap.get("BTC").equals(dataAccess.getData().get("BTC"))){
                    saveDataToDb();
                    currentCurrencyMap = dataAccess.getData();
                }
            } catch (InterruptedException e) {
                isRunning = false;
            }
        }
    }

    private void saveDataToDb() {

        System.out.println("pizda");
        for (Currency currency : dataAccess.getData().values()){
            currencyRepository.save(currency);
        }
    }
}
