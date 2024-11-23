package com.ecommerce.orders;

import com.ecommerce.Product;

/**
 * Represents an item in an order, containing a specific product, its quantity, and price details.
 * Each order item is uniquely identified and associated with a parent order.
 */
public class OrderItem {

    // Static variable to auto-increment order item IDs
    private static int autoIncrementOrderItemId = 1;

    // Instance variables
    private int orderItemId;
    private Order order;
    private Product product;
    private int quantity;
    private double unitPrice;

    /**
     * Constructs a new OrderItem for a given order, product, and quantity.
     *
     * @param order     the parent order this item belongs to
     * @param product   the product associated with this order item
     * @param quantity  the quantity of the product in the order
     * @param unitPrice the price per unit of the product
     */
    public OrderItem(Order order, Product product, int quantity, double unitPrice) {
        this.orderItemId = autoIncrementOrderItemId++;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Getters

    /**
     * Gets the unique ID of the order item.
     *
     * @return the order item ID
     */
    public int getOrderItemId() {
        return this.orderItemId;
    }

    /**
     * Gets the parent order associated with this item.
     *
     * @return the parent order
     */
    public Order getOrder() {
        return this.order;
    }

    /**
     * Gets the product associated with this order item.
     *
     * @return the product
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Gets the quantity of the product in this order item.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Gets the unit price of the product in this order item.
     *
     * @return the unit price
     */
    public double getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * Calculates the total price for this order item.
     *
     * @return the total price (unit price * quantity)
     */
    public double getTotalPrice() {
        return this.unitPrice * this.quantity;
    }

    // Setters

    /**
     * Sets the unique ID of the order item.
     * 
     * @param orderItemId the new order item ID
     */
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    /**
     * Updates the quantity of the product in this order item.
     *
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Updates the unit price of the product in this order item.
     *
     * @param unitPrice the new unit price
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Returns a string representation of the order item, including details about the product and quantity.
     *
     * @return a formatted string with order item details
     */
    @Override
    public String toString() {
        return "OrderItem{" +
                "OrderItemId=" + this.orderItemId +
                ", product=" + this.product +
                ", quantity=" + this.quantity +
                ", unitPrice=" + this.unitPrice +
                ", totalPrice=" + this.getTotalPrice() +
                '}';
    }
}
