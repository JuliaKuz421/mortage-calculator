package com.example.mortgagecalculator.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class DifferentiatedPaymentTest {
        @Test
    void monthPayment() {
            BigDecimal price = new BigDecimal("300000");
            BigDecimal downPayment = new BigDecimal("0");
            BigDecimal mortgageInterest = new BigDecimal("20");
            int month = 6;
            YearMonth startDate = YearMonth.of(2023, Month.JULY);

            DifferentiatedPayment mortgage = new DifferentiatedPayment(downPayment, price, mortgageInterest, month, startDate);

            List<BigDecimal> expected =List.of(
                    new BigDecimal("55095.89"),
                    new BigDecimal("54246.58"),
                    new BigDecimal("53287.67"),
                    new BigDecimal("52547.95"),
                    new BigDecimal("51643.84"),
                    new BigDecimal("50849.32"))
            ;

            try {
                List<BigDecimal> actual = mortgage.monthPaymentDifferentiated();
                assertIterableEquals(expected, actual, "Anuitent payments should be equal.");
            } catch (ArithmeticException ae) {
                System.out.println("поймали ошибку ArithmeticException для DifferentiatedPayment" + downPayment + " " + price + " " + mortgageInterest + " " + month);
                throw ae;
            }
    }
}