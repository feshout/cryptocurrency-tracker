package com.codecool.cryptocurrencytracker.services;

import com.codecool.cryptocurrencytracker.currency.Currency;
import com.codecool.cryptocurrencytracker.view.ApplicationView;
import com.codecool.cryptocurrencytracker.view.TableView;
import org.springframework.stereotype.Component;


import java.util.LinkedList;
import java.util.List;

@Component
public class PrintData implements Runnable {

    private List<Currency> dataToPrint = new LinkedList<>();
    private TableView tableView;
    private Thread thread;
    private boolean suspend = false;

    PrintData(TableView tableView){
        this.tableView = tableView;
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        boolean isRunning = true;

        while(isRunning){

            try {
                Thread.sleep(10000);
                clearScreen();
                if(suspend){
//                    ApplicationView.pause();
                    Thread.sleep(10000);
                    suspend = false;
                }
                tableView.printActualData(dataToPrint);
            } catch (InterruptedException e) {
                isRunning = false;
            }
        }
    }

    public void setDataToPrint(List<Currency> dataToPrint){
        this.dataToPrint = dataToPrint;
    }

    public void stopThread(){
        this.thread.interrupt();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void suspend(){
        suspend = true;
    }
}
