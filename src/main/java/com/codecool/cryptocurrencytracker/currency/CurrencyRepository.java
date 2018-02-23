package com.codecool.cryptocurrencytracker.currency;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Integer> {

    List<Currency> findBySymbol(String symbol);
}
