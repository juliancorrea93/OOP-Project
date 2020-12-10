package com.correajulian.oopgroup19;

public class CreditCard {
    /**
     * @param number credit card number
     * @param exp credit card expiration date
     * @param cvc credit card cvc number
     */
    public CreditCard(long number, String exp, int cvc) {
        this.number = number;
        this.exp = exp;
        this.cvc = cvc;
    }
    /**
     * @param number credit card number
     * @param exp credit card expiration date
     * @param cvc credit card cvc number
     * @return true if inputs match the cedit card
     */
    private boolean Authenticate(long number, String exp, int cvc) {
        return this.number == number && this.exp.equals(exp) && this.cvc == cvc;
    }
    /**
     * @param number credit card number
     * @param exp credit card expiration date
     * @param cvc credit card cvc number
     * @param s shopping cart that the purchase is being made in
     * @return true if Authenticate() is true and false if false
     */
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

