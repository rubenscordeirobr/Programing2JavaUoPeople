package com.ecommerce.enums;

/**
 * The MenuOptions enum defines the available options in the application's menu.
 * Each option includes a name and a description for user-friendly display.
 * <p>
 * This enum implements {@link EnumDescription} to ensure that every option
 * provides meaningful information, which can be used for input validation
 * and display purposes.
 * </p>
 */
public enum MenuOptions implements EnumDescription {

    SHOW_PRODUCTS("Show Products", "Show all available products"),
    ADD_ITEM("Add Item", "Add a product to the order"),
    EDIT_ITEM("Edit Item", "Edit the quantity or remove a product from the order"),
    SHOW_ORDER_ITEMS("Show Order Items", "Show all items in the order"),
    CHECKOUT("Checkout", "Finish the order and proceed to payment"),
    EXIT("Exit", "Exit the application");

    private final String name;
    private final String description;

    /**
     * Constructs a MenuOption with a name and a description.
     *
     * @param name        the name of the menu option
     * @param description a brief description of the menu option
     */
    MenuOptions(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Retrieves the name of the menu option.
     *
     * @return the name of the menu option
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the description of the menu option.
     *
     * @return the description of the menu option
     */
    @Override
    public String getDescription() {
        return this.description;
    }
}
