package com.ecommerce;

/**
 * Represents a customer in the e-commerce system.
 * Each customer has a unique ID, name, email, phone, address, and city.
 * The customer ID is auto-incremented to ensure uniqueness.
 */
public class Customer {

    // Static variable to auto-increment customer IDs
    private static int autoIncrementCustomerId = 1;

    // Instance variables
    private int customerId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;

    /**
     * Constructs a new Customer with the provided details.
     * The customer ID is assigned automatically.
     *
     * @param name    the name of the customer
     * @param email   the email address of the customer
     * @param phone   the phone number of the customer
     * @param address the address of the customer
     * @param city    the city of the customer
     */
    public Customer(String name, String email, String phone, String address, String city) {
        this.customerId = autoIncrementCustomerId++;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
    }

    // Getters

    /**
     * Gets the unique ID of the customer.
     *
     * @return the customer ID
     */
    public int getCustomerId() {
        return this.customerId;
    }

    /**
     * Gets the name of the customer.
     *
     * @return the customer's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the email address of the customer.
     *
     * @return the customer's email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the phone number of the customer.
     *
     * @return the customer's phone number
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Gets the address of the customer.
     *
     * @return the customer's address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Gets the city of the customer.
     *
     * @return the customer's city
     */
    public String getCity() {
        return this.city;
    }

    // Setters

    /**
     * Sets the name of the customer.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phone the new phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the city of the customer.
     *
     * @param city the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns a string representation of the customer.
     *
     * @return a string containing customer details
     */
    @Override
    public String toString() {
        return "Customer {" +
                "id=" + this.customerId +
                ", name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                ", phone='" + this.phone + '\'' +
                ", address='" + this.address + '\'' +
                ", city='" + this.city + '\'' +
                '}';
    }
}
