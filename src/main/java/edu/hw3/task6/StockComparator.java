package edu.hw3.task6;

import java.util.Comparator;

public class StockComparator implements Comparator<Stock> {
    @Override
    public int compare(Stock firstStock, Stock secondStock) {
        return -Double.compare(firstStock.price(), secondStock.price());
    }
}
