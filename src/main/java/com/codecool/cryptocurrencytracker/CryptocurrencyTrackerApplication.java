package com.codecool.cryptocurrencytracker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class CryptocurrencyTrackerApplication {

    public static void main(String[] args) {

        SpringApplication.run(CryptocurrencyTrackerApplication.class, args);

	}
}
