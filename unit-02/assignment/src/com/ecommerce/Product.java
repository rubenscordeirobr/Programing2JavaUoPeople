package com.ecommerce;

/**
 * Represents a product in the e-commerce system.
 * Each product has a unique ID, name, description, brand, category, stock, price, and discount percentage.
 * The product ID is auto-incremented to ensure uniqueness.
 */
public class Product {

    // Static variable to auto-increment product IDs
    private static int autoIncrementProductId = 1;

    // Instance variables
    private int productId;
    private String name;
    private String description;
    private String brand;
    private String category;
    private int stock;
    private double price;
    private double discountPercentage;

    /**
     * Constructs a new Product with the provided details.
     * The product ID is assigned automatically.
     *
     * @param name              the name of the product
     * @param description       a brief description of the product
     * @param brand             the brand of the product
     * @param category          the category of the product
     * @param stock             the available stock of the product
     * @param price             the base price of the product
     * @param discountPercentage the discount percentage applied to the product
     */
    public Product(String name, String description, String brand, String category, int stock, double price, double discountPercentage) {
        this.productId = autoIncrementProductId++;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.discountPercentage = discountPercentage;
    }

    // Getters

    /**
     * Gets the unique ID of the product.
     *
     * @return the product ID
     */
    public int getProductId() {
        return this.productId;
    }

    /**
     * Gets the name of the product.
     *
     * @return the product name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the description of the product.
     *
     * @return the product description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the brand of the product.
     *
     * @return the product brand
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Gets the category of the product.
     *
     * @return the product category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Gets the available stock of the product.
     *
     * @return the product stock
     */
    public int getStock() {
        return this.stock;
    }

    /**
     * Gets the base price of the product.
     *
     * @return the product price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Gets the discount percentage applied to the product.
     *
     * @return the discount percentage
     */
    public double getDiscountPercentage() {
        return this.discountPercentage;
    }

    /**
     * Calculates the final price after applying the discount.
     *
     * @return the discounted price
     */
    public double getFinalPrice() {
        return this.price - (this.price * this.discountPercentage / 100);
    }

    // Setters

    /**
     * Sets the name of the product.
     *
     * @param name the new product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the product.
     *
     * @param description the new product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the brand of the product.
     *
     * @param brand the new product brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Sets the category of the product.
     *
     * @param category the new product category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets the available stock of the product.
     *
     * @param stock the new stock value
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Sets the base price of the product.
     *
     * @param price the new price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the discount percentage applied to the product.
     *
     * @param discountPercentage the new discount percentage
     */
    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    // Static Method

    /**
     * Gets the next auto-incremented product ID.
     *
     * @return the next product ID
     */
    public static int getMaxProductId() {
        return autoIncrementProductId;
    }

    // Override toString

    /**
     * Returns a string representation of the product, including its details.
     *
     * @return a formatted string with product information
     */
    @Override
    public String toString() {
        return "Product {" +
                "id=" + this.productId +
                ", name='" + this.name + '\'' +
                ", description='" + this.description + '\'' +
                ", brand='" + this.brand + '\'' +
                ", category='" + this.category + '\'' +
                ", stock=" + this.stock +
                ", price=" + this.price +
                ", finalPrice=" + this.getFinalPrice() +
                '}';
    }
}
