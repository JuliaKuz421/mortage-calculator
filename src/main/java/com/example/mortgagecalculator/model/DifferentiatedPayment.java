package com.example.mortgagecalculator.model;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

public final class DifferentiatedPayment {

    private final BigDecimal downPayment;
    private final BigDecimal price;
    private final BigDecimal mortgageInterest;
    private final int month;
    private final YearMonth startDate;

    public DifferentiatedPayment (BigDecimal downPayment, BigDecimal price, BigDecimal mortgageInterest, int month, YearMonth startDate) {
        this.downPayment = downPayment;
        this.price = price;
        this.mortgageInterest = mortgageInterest;
        this.month = month;
        this.startDate = startDate;
    }

    public List<BigDecimal> monthPaymentDifferentiated() {

        BigDecimal monthBigDecimal = new BigDecimal(month);

        // массив с кол вом месяцев
        List<BigDecimal> paymentSchedule = new ArrayList<>(month);

        //1. Ежемесячный платеж по основному долгу, заполняем массив одинаковыми платежами
        BigDecimal monthPaymentMainDebt = price.subtract(downPayment).divide(monthBigDecimal, 2, HALF_UP);

        //Ежемесячная сумма начисленных процентов
        for (int i = 0; i < month; i++) {

            BigDecimal iBigDecimal = new BigDecimal(i);
            //остаток суммы основого долга по кредиту
           BigDecimal remainder =  price.subtract(iBigDecimal.multiply(monthPaymentMainDebt));
//
            //начисленные проценты
            BigDecimal interest = mortgageInterest.divide(new BigDecimal(100), 10, HALF_UP);

            YearMonth currentMonth = startDate.plusMonths(i);
            BigDecimal lengthOfMonth = new BigDecimal(currentMonth.lengthOfMonth());
            BigDecimal lengthOfYear = new BigDecimal(currentMonth.lengthOfYear());
            BigDecimal yearPart = lengthOfMonth.divide(lengthOfYear,10, HALF_UP);
            BigDecimal accruedInterest = remainder.multiply(interest).multiply(yearPart).setScale(2, HALF_UP);
//

            //ежемесячные платежи в массиве
            paymentSchedule.add(i, monthPaymentMainDebt.add(accruedInterest));
            System.out.println("Schedule" + paymentSchedule);
        }

        return  paymentSchedule;
    }
}
