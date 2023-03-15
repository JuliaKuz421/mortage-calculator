package com.example.mortgagecalculator.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AnuitentPaymentTest {

    @Test
    void monthPayment() {
        BigDecimal price = new BigDecimal("3500000");
        BigDecimal downPayment = new BigDecimal("900000");
        BigDecimal mortgageInterest = new BigDecimal("10.5");
        BigDecimal month = new BigDecimal(240);

        AnuitentPayment mortgage = new AnuitentPayment(downPayment, price, mortgageInterest, month);

        BigDecimal expected = new BigDecimal("25957.88");
        BigDecimal actual = mortgage.monthPayment();

        assertEquals(expected, actual, "Anuitent payments should be equal.");
    }

    @Test
    void monthPayment2() {
        BigDecimal price = new BigDecimal("7654321");
        BigDecimal downPayment = new BigDecimal("1234567");
        BigDecimal mortgageInterest = new BigDecimal("9.8");
        BigDecimal month = new BigDecimal(252);

        AnuitentPayment mortgage = new AnuitentPayment(downPayment, price, mortgageInterest, month);

        BigDecimal expected = new BigDecimal("60177.74");
        BigDecimal actual = mortgage.monthPayment();

        assertEquals(expected, actual, "Anuitent payments should be equal.");
    }
}