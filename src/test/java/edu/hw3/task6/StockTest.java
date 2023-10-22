package edu.hw3.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StockTest {
    private TinkoffStock tinkoffStock;
    private final Stock stockForRemove = new Stock("Gazprom", 100.5);

    @BeforeEach public void makeStockMarket() {
        tinkoffStock = new TinkoffStock();
        tinkoffStock.add(new Stock("Aeroflot", 50.7));
        tinkoffStock.add(new Stock("X5 Group", 1245.3));
    }

    @Test
    @DisplayName("add method test")
    public void stock_shouldAddStockToMarket() {
        Stock stock = new Stock("Tinkoff", 10000);
        tinkoffStock.add(stock);
        assertTrue(tinkoffStock.getStockQueue().contains(stock));
    }

    @Test
    @DisplayName("remove method test")
    public void stock_shouldRemoveStockFromMarket() {
        tinkoffStock.remove(stockForRemove);
        assertFalse(tinkoffStock.getStockQueue().contains(stockForRemove));
    }

    @Test
    @DisplayName("mostValuableStock method test")
    public void stock_shouldReturnFirstStockFromMarket() {
        Stock stock = new Stock("Tinkoff", 10000);
        tinkoffStock.add(stock);
        assertThat(tinkoffStock.mostValuableStock()).isEqualTo(stock);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Operations with null test")
    public void stockInterfaceAdd_shouldThrowException_whenStockEmptyOrNull(Stock stock) {
        assertAll(
            () -> assertThatThrownBy(() -> tinkoffStock.add(stock)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> tinkoffStock.remove(stock)).isInstanceOf(IllegalArgumentException.class)
        );
    }
}
