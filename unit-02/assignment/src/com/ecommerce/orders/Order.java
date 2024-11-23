package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.enums.PaymentMethod;
import com.ecommerce.enums.ShippingMethod;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents an order placed by a customer in the e-commerce system.
 * <p>
 * An order includes details such as the customer, items, order status,
 * payment and shipping information, and calculated totals.
 * </p>
 */
public class Order {

    // Static variable to auto-increment order IDs
    private static int autoIncrementOrderId = 1;

    // Order attributes
    private int orderId;
    private Customer customer;
    private OrderStatus orderStatus;
    private LocalDate orderDate;
    private double orderTotal;

    private PaymentMethod paymentMethod;
    private boolean isPaid;

    private ShippingMethod shippingMethod;
    private LocalDate estimateShippingDate;

    // List of items in the order
    private ArrayList<OrderItem> items = new ArrayList<>();

    /**
     * Constructs a new Order for a given customer.
     * The order is initialized with a pending status and the current date.
     *
     * @param customer the customer placing the order
     */
    public Order(Customer customer) {
        this.orderId = autoIncrementOrderId++;
        this.customer = customer;
        this.orderStatus = OrderStatus.PENDING;
        this.orderDate = LocalDate.now();
        this.paymentMethod = null;
        this.shippingMethod = null;
        this.isPaid = false;
    }

    // Getters

    /**
     * Gets the unique ID of the order.
     *
     * @return the order ID
     */
    public int getOrderId() {
        return this.orderId;
    }

    /**
     * Gets the customer associated with the order.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Gets the current status of the order.
     *
     * @return the order status
     */
    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    /**
     * Gets the date the order was placed.
     *
     * @return the order date
     */
    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    /**
     * Gets the estimated shipping date based on the shipping method.
     *
     * @return the estimated shipping date
     */
    public LocalDate getEstimateShippingDate() {
        return this.estimateShippingDate;
    }

    /**
     * Gets the total price of the order.
     *
     * @return the order total
     */
    public double getOrderTotal() {
        return this.orderTotal;
    }

    /**
     * Gets the payment method for the order.
     *
     * @return the payment method
     */
    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    /**
     * Gets the shipping method for the order.
     *
     * @return the shipping method
     */
    public ShippingMethod getShippingMethod() {
        return this.shippingMethod;
    }

    /**
     * Checks if the order has been paid.
     *
     * @return true if the order is paid, false otherwise
     */
    public boolean isPaid() {
        return this.isPaid;
    }

    /**
     * Gets the list of items in the order.
     *
     * @return the list of order items
     */
    public ArrayList<OrderItem> getItems() {
        return this.items;
    }

    // Setters

    /**
     * Sets the status of the order.
     *
     * @param orderStatus the new order status
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Sets the total price of the order.
     *
     * @param orderTotal the total price of the order
     */
    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * Sets the payment method and marks the order as paid if a payment method is provided.
     *
     * @param paymentMethod the payment method for the order
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        this.isPaid = paymentMethod != null;
    }

    /**
     * Sets the shipping method and calculates the estimated shipping date.
     *
     * @param shippingMethod the shipping method for the order
     */
    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
        this.estimateShippingDate = LocalDate.now().plusDays(shippingMethod.getDaysToDelivery());
    }

    // Order Operations

    /**
     * Adds an item to the order and updates the order total.
     *
     * @param product  the product to add
     * @param quantity the quantity of the product
     */
    public void addItem(Product product, int quantity) {
        OrderItem item = new OrderItem(this, product, quantity, product.getFinalPrice());
        this.items.add(item);
        this.updateOrderTotal();
    }

    /**
     * Removes an item from the order based on the product.
     * 
     * @param product the product to remove
     * @return true if the item was removed successfully
     */
    public boolean removeItem(Product product) {
        OrderItem item = this.getOrderItemByProductId(product.getProductId());
        if (item == null) {
            throw new IllegalArgumentException("Product ID " + product.getProductId() + " not found in order items.");
        }
        this.items.remove(item);
        this.updateOrderTotal();
        return true;
    }

    /**
     * Updates the quantity of an item in the order and recalculates the total.
     *
     * @param product  the product to update
     * @param quantity the new quantity
     */
    public void updateItemQuantity(Product product, int quantity) {
        OrderItem item = this.getOrderItemByProductId(product.getProductId());
        if (item == null) {
            throw new IllegalArgumentException("Product ID " + product.getProductId() + " not found in order items.");
        }
        item.setQuantity(quantity);
        this.updateOrderTotal();
    }

    /**
     * Gets a product from the order by its ID.
     *
     * @param productId the product ID
     * @return the Product object, or null if not found
     */
    public Product geProductById(int productId) {
        OrderItem item = this.getOrderItemByProductId(productId);
        return item != null ? item.getProduct() : null;
    }

    /**
     * Updates the total price of the order by summing up the total prices of all items.
     */
    public void updateOrderTotal() {
        double total = 0;
        for (OrderItem item : this.items) {
            total += item.getTotalPrice();
        }
        this.orderTotal = total;
    }

    /**
     * Finds an OrderItem in the order based on the product ID.
     *
     * @param productId the product ID
     * @return the OrderItem object, or null if not found
     */
    private OrderItem getOrderItemByProductId(int productId) {
        for (OrderItem item : this.items) {
            if (item.getProduct().getProductId() == productId) {
                return item;
            }
        }
        return null;
    }
}
