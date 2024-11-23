package com.ecommerce.enums;

/**
 * The ShippingMethod enum represents the various shipping options available for orders.
 * Each shipping method includes a name, a description, and a method to calculate delivery time.
 * <p>
 * This enum implements {@link EnumDescription}, making it compatible with utilities
 * that require detailed enum descriptions for user interaction or display purposes.
 * </p>
 */
public enum ShippingMethod implements EnumDescription {

    NONE("None", "No shipping method selected"),
    STANDARD("Standard", "Standard shipping, 5-10 business days"),
    EXPRESS("Express", "Express shipping, 2-3 business days");

    private final String name;
    private final String description;

    /**
     * Constructs a ShippingMethod enum constant with a name and description.
     *
     * @param name        the name of the shipping method
     * @param description a brief description of the shipping method
     */
    ShippingMethod(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Retrieves the name of the shipping method.
     *
     * @return the name of the shipping method
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the description of the shipping method.
     *
     * @return the description of the shipping method
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Determines the maximum number of days required for delivery based on the shipping method.
     *
     * @return the number of days for delivery (0 if no shipping method is selected)
     */
    public int getDaysToDelivery() {
        switch (this) {
            case STANDARD:
                return 10;
            case EXPRESS:
                return 3;
            default:
                return 0; // NONE
        }
    }
}
