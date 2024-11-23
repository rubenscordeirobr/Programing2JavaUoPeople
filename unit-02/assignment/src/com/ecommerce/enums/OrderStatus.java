package com.ecommerce.enums;

/**
 * Represents the various statuses that an order can have in the e-commerce system.
 * Each status includes a name and a description to provide clarity about the order's state.
 * <p>
 * This enum implements the {@link EnumDescription} interface, allowing seamless
 * integration with utilities that require standardized descriptions for enum values.
 * </p>
 */
public enum OrderStatus implements EnumDescription {

    /**
     * The order has been placed but not confirmed yet.
     */
    PENDING("Pending", "The order has been placed but not confirmed yet"),

    /**
     * The order has been confirmed and is being processed.
     */
    CONFIRMED("Confirmed", "The order has been confirmed and is being processed"),

    /**
     * The order has been shipped to the customer.
     */
    SHIPPED("Shipped", "The order has been shipped to the customer"),

    /**
     * The order has been canceled by the customer.
     */
    CANCELED("Canceled", "The order has been canceled by the customer");

    private final String name;
    private final String description;

    /**
     * Constructs an OrderStatus enum value with a name and description.
     *
     * @param name        the name of the order status
     * @param description a brief description of the order status
     */
    OrderStatus(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Retrieves the name of the order status.
     *
     * @return the name of the order status
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the description of the order status.
     *
     * @return the description of the order status
     */
    @Override
    public String getDescription() {
        return this.description;
    }
}
