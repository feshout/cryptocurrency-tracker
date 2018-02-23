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
    private Map<String, Currency> currentCurrencyMap;
    private Thread thread;

    public SaveData(CurrencyRepository currencyRepository, DataAccess dataAccess) {
        this.currencyRepository = currencyRepository;
        this.dataAccess = dataAccess;
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        boolean isRunning = true;

        while(isRunning){

            try {
                Thread.sleep(5000);
                if(currentCurrencyMap == null) {
                    currentCurrencyMap = cloneMapWithCurrentData();
                    saveDataToDb();
                }
                if(!currentCurrencyMap.get("ETH").getPriceBTC().equals(dataAccess.getData().get("ETH").getPriceBTC())){
                    currentCurrencyMap = cloneMapWithCurrentData();
                    saveDataToDb();
                }
            } catch (InterruptedException e) {
                isRunning = false;
            }
        }
    }

    private void saveDataToDb() {

        for (Currency currency : dataAccess.getData().values()){
            currencyRepository.save(currency);
        }
    }

    public void stopThread(){
        this.thread.interrupt();
    }

    private Map<String, Currency> cloneMapWithCurrentData(){

        Map<String, Currency> coppedMap = new HashMap<>();

        try {
            for(Currency currency : dataAccess.getData().values()) {
                coppedMap.put(currency.getSymbol(), currency.clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return coppedMap;
    }
}
