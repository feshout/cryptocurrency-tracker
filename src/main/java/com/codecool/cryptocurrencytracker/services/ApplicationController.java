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

    private List<String> selectedCurrenciesSymbols = new LinkedList<>();
    private List<Currency> selectedCurrencies = new LinkedList<>();
    private CurrencyRepository currencyRepository;
    private DataAccess dataAccess;
    private SaveData saveData;
    private PrintData printData;
    private TableView tableView;

    public ApplicationController(CurrencyRepository currencyRepository, DataAccess dataAccess, SaveData saveData, PrintData printData, TableView tableView) {
        this.currencyRepository = currencyRepository;
        this.dataAccess = dataAccess;
        this.saveData = saveData;
        this.printData = printData;
        this.tableView = tableView;
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {

        startController();

    }

    public void startController(){

        String input = "";

        while(true) {

            input = ApplicationView.getInput("Enter command: ").toUpperCase();
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

                    List<Currency> currencyHistory = currencyRepository.findAllBySymbol(symbol);
                    tableView.printActualData(currencyHistory);
                } else if (operation.equals("SORT")) {
                    if(symbol.matches("/^\\d+$/")) {
                        int nrOfColumn = Integer.valueOf(symbol);
                        System.out.println(nrOfColumn);
                        if(nrOfColumn > 1 && nrOfColumn < 11) {
                            tableView.setNrOfColumnToSort(nrOfColumn - 1);
                        }
                    }

                }
            }
            if(input.equals("EXIT")){
                System.exit(0);
            }
        }
    }

    public List<Currency> getSelectedCurrencies() {


        for (String symbol : selectedCurrenciesSymbols) {
            Currency currency = dataAccess.getData().get(symbol);
            selectedCurrencies.add(currency);
        }
        return selectedCurrencies;
    }
}
