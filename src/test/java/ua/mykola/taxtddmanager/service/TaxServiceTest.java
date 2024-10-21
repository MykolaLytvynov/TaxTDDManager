package ua.mykola.taxtddmanager.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxServiceTest {

    private final TaxService taxService = new TaxService();

    @Test
    @DisplayName("Calculating tax with correct params")
    void shouldReturnCalculatedTax() {
        int income = 10;
        int months = 5;
        BigDecimal expectedTax = BigDecimal.valueOf(15).setScale(2);

        BigDecimal actualResult = taxService.calculateTax(income, months);

        assertEquals(expectedTax, actualResult);
    }

    @Test
    @DisplayName("Calculating tax when months is zero")
    void shouldReturnZeroTaxWhenMonthsIsZero() {
        int income = 100;
        int months = 0;
        BigDecimal expectedTax = BigDecimal.ZERO.setScale(2);

        BigDecimal actualResult = taxService.calculateTax(income, months);

        assertEquals(expectedTax, actualResult);
    }

    @Test
    @DisplayName("Calculating tax when income is zero")
    void shouldReturnZeroTaxWhenIncomeIsZero() {
        int income = 0;
        int months = 5;
        BigDecimal expectedTax = BigDecimal.ZERO.setScale(2);

        BigDecimal actualResult = taxService.calculateTax(income, months);

        assertEquals(expectedTax, actualResult);
    }

    @Test
    @DisplayName("Rounded Tax")
    void shouldReturnRoundedTax() {
        int income = 4587;
        int months = 5;
        BigDecimal expectedTax = BigDecimal.valueOf(6880.5).setScale(2);

        BigDecimal actualResult = taxService.calculateTax(income, months);

        assertEquals(expectedTax, actualResult);
    }
}
