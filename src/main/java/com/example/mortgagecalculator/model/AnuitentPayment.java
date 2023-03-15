package com.example.mortgagecalculator.model;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public final class AnuitentPayment {

    public final BigDecimal downPayment;
    public final BigDecimal price;
    public final BigDecimal mortgageInterest;
    public final BigDecimal month;

    public  AnuitentPayment(BigDecimal downPayment, BigDecimal price, BigDecimal mortgageInterest, BigDecimal month) {
        this.downPayment = downPayment;
        this.price = price;
        this.mortgageInterest = mortgageInterest;
        this.month = month;
    }

   public BigDecimal monthPayment() {
       BigDecimal mortgagePrice = price.subtract(downPayment);
       BigDecimal monthInterest = mortgageInterest.divide(new BigDecimal(1200), 10, HALF_UP);
       BigDecimal baseInterest = (BigDecimal.ONE.add(monthInterest)).pow(month.intValue());

       return mortgagePrice.multiply(monthInterest).multiply(baseInterest).divide(baseInterest.subtract(BigDecimal.ONE), 2, HALF_UP);
   };

}
