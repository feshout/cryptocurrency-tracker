package com.codecool.cryptocurrencytracker.view;

import com.codecool.cryptocurrencytracker.currency.Currency;
import dnl.utils.text.table.TextTable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableView {

    private int nrOfColumnToSort = 0;

    private static final String[] NAMES_OF_COLUMNS_WITH_TIME = {"Time", "Name", "Symbol", "Price USD", "Price BTC",
            "Market cap USD", "Available supply", "Total supply",
            "% change 1h", "% change 24h", "% change 7d"};


    private static final String[] NAMES_OF_COLUMNS_WITHOUT_TIME = {"Name", "Symbol", "Price USD", "Price BTC",
            "Market cap USD", "Available supply", "Total supply",
            "% change 1h", "% change 24h", "% change 7d"};


    public void printActualData(List<Currency> currencies) {
        int nrOfRows = currencies.size();
        int nrOfColumns = NAMES_OF_COLUMNS_WITHOUT_TIME.length;
        String[][] data = new String[nrOfRows][nrOfColumns];

        for(int i = 0; i < nrOfRows; i++) {
            data[i] = currencies.get(i).getAllDataWithoutTime();
        }

        TextTable table = new TextTable(NAMES_OF_COLUMNS_WITHOUT_TIME, data);
        table.setSort(nrOfColumnToSort);
        table.printTable();

    }

    public void setNrOfColumnToSort(int nrOfColumnToSort) {
        this.nrOfColumnToSort = nrOfColumnToSort;
    }
}
