package edu.hw3.task6;

import java.util.PriorityQueue;

public class TinkoffStock implements StockMarket {
    private final PriorityQueue<Stock> stockQueue = new PriorityQueue<>(new StockComparator());
    private static final String EXCEPTION_MESSAGE = "Null stock";

    @Override
    public void add(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        stockQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        stockQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockQueue.peek();
    }

    public PriorityQueue<Stock> getStockQueue() {
        return stockQueue;
    }
}
