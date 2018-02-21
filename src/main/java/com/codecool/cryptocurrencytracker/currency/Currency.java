package com.codecool.cryptocurrencytracker.currency;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Currency {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String symbol;
    @JsonProperty("price_usd")
    private String priceUSD;
    @JsonProperty("price_btc")
    private String priceBTC;
    @JsonProperty("market_cap_usd")
    private String marketCapUSD;
    @JsonProperty("available_supply")
    private String availableSupply;
    @JsonProperty("total_supply")
    private String totalSupply;
    @JsonProperty("percent_change_1h")
    private String percentChange1h;
    @JsonProperty("percent_change_24h")
    private String percentChange24h;
    @JsonProperty("percent_change_7d")
    private String percentChange7d;
    private String timeStamp;

    public Currency() {

        timeStamp = Timestamp.valueOf(LocalDateTime.now()).toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(String priceUSD) {
        this.priceUSD = priceUSD;
    }

    public String getPriceBTC() {
        return priceBTC;
    }

    public void setPriceBTC(String priceBTC) {
        this.priceBTC = priceBTC;
    }

    public String getMarketCapUSD() {
        return marketCapUSD;
    }

    public void setMarketCapUSD(String marketCapUSD) {
        this.marketCapUSD = marketCapUSD;
    }

    public String getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(String availableSupply) {
        this.availableSupply = availableSupply;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(String percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(String percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

}
