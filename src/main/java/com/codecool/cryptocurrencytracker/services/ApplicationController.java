package com.codecool.cryptocurrencytracker.services;

import com.codecool.cryptocurrencytracker.view.ApplicationView;
import com.codecool.cryptocurrencytracker.currency.Currency;
import com.codecool.cryptocurrencytracker.currency.CurrencyRepository;
import com.codecool.cryptocurrencytracker.utils.InputHelper;
import com.codecool.cryptocurrencytracker.view.TableView;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ApplicationController implements Runnable {

    private CurrencyRepository currencyRepository;
    private DataAccess dataAccess;
    private SaveData saveData;
    private PrintData printData;
    private TableView tableView;
    private Thread thread;

    public ApplicationController(CurrencyRepository currencyRepository, DataAccess dataAccess, SaveData saveData, PrintData printData, TableView tableView) {
        this.currencyRepository = currencyRepository;
        this.dataAccess = dataAccess;
        this.saveData = saveData;
        this.printData = printData;
        this.tableView = tableView;
        this.thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {

        startController();

    }

    public void startController(){

        String input = "";

        while(!input.equals("EXIT")) {

            input = ApplicationView.getInput().toUpperCase();
            if (InputHelper.validateInput(input)) {

                String operation = InputHelper.handleInput(input)[0];
                String symbol = InputHelper.handleInput(input)[1];

                if (operation.equals("SELECT")) {

                    if (dataAccess.getData().containsKey(symbol)) {
                        dataAccess.addSelectedCurrenciesSymbol(symbol);
                    }

                } else if (operation.equals("UNSELECT")) {

                    if (dataAccess.getData().containsKey(symbol)) {
                        dataAccess.deleteSymbolFromSelectedCurrenciesSymbols(symbol);
                    }
                } else if (operation.equals("HISTORY")) {

                    List<Currency> currencyHistory = currencyRepository.findBySymbol(symbol);
                    printData.suspend();
                    tableView.printHistoricalData(currencyHistory);

                } else if (operation.equals("SORT")) {
                    if(symbol.matches("^([0-9]\\d*|0)$")) {
                        int nrOfColumn = Integer.valueOf(symbol);
                            tableView.setNrOfColumnToSort(nrOfColumn);
                    }
                }
            }
        }
        dataAccess.stopThread();
        printData.stopThread();
        saveData.stopThread();
    }
}
