package com.correajulian.oopgroup19;

public class CreditCard {
    public CreditCard(long number, String exp, int cvc) {
        this.number = number;
        this.exp = exp;
        this.cvc = cvc;
    }

    private boolean Authenticate(long number, String exp, int cvc) {
        return this.number == number && this.exp.equals(exp) && this.cvc == cvc;
    }

    public boolean Charge (ShoppingCart s, long number, String exp, int cvc) {
        if (Authenticate(number, exp, cvc)) {
            System.out.println("Purchase Successful!");
            return true;
        }
        System.out.println("Invalid credit card credentials.");
        return false;
    }

    private final long number;
    private final String exp;
    private final int cvc;
}

