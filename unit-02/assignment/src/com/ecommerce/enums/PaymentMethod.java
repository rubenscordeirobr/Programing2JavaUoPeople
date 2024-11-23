package com.ecommerce.enums;

/**
 * The PaymentMethod enum represents the different payment options available for customers.
 * Each payment method includes a name and a description for clear communication.
 * <p>
 * This enum implements {@link EnumDescription}, making it compatible with utilities that
 * require detailed enum descriptions for user interaction or display purposes.
 * </p>
 */
public enum PaymentMethod implements EnumDescription {

    CREDIT_CARD("Credit Card", "Pay with a credit card"),
    DEBIT_CARD("Debit Card", "Pay with a debit card"),
    PAYPAL("PayPal", "Pay with a PayPal account"),
    CASH("Cash", "Pay with cash on delivery");

    private final String name;
    private final String description;

    /**
     * Constructs a PaymentMethod enum constant with a name and description.
     *
     * @param name        the name of the payment method
     * @param description a brief description of the payment method
     */
    PaymentMethod(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Retrieves the name of the payment method.
     *
     * @return the name of the payment method
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the description of the payment method.
     *
     * @return the description of the payment method
     */
    @Override
    public String getDescription() {
        return this.description;
    }
}
