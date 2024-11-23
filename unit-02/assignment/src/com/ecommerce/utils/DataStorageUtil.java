package com.ecommerce.utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.ecommerce.Product;

/**
 * The DataStorageUtil class provides utility methods for managing a static list of products.
 * It includes functionality to retrieve all products or a specific product by its ID.
 * <p>
 * The product data is initialized as an unmodifiable list to simulate a read-only data source.
 * </p>
 */
public class DataStorageUtil {

    // Static list of products initialized with sample data
    private static final List<Product> PRODUCTS = initializeProducts();

    /**
     * Retrieves the list of all available products.
     *
     * @return an unmodifiable list of products
     */
    public static List<Product> getProducts() {
        return PRODUCTS;
    }

    /**
     * Retrieves a product by its unique ID.
     *
     * @param productId the ID of the product to retrieve
     * @return the Product object if found, or null if no product matches the ID
     */
    public static Product getProductById(int productId) {
        for (Product product : PRODUCTS) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Initializes the product list with sample data.
     * <p>
     * This method is invoked only once to populate the static PRODUCTS list.
     * </p>
     *
     * @return an unmodifiable list of sample products
     */
    private static List<Product> initializeProducts() {
        List<Product> products = new ArrayList<>();

        // Sample product data
        products.add(new Product("Textbook", "Introduction to Algorithms", "MIT Press", "Books", 50, 600, 0));
        products.add(new Product("Notebook", "A4 Spiral Notebook - 200 Pages", "Camlin", "Stationery", 200, 150, 5));
        products.add(new Product("Backpack", "Waterproof Laptop Backpack", "Samsonite", "Accessories", 30, 250, 20));
        products.add(new Product("Calculator", "Scientific Calculator FX-991ES", "Casio", "Electronics", 40, 1500, 15));
        products.add(new Product("Tablet", "iPad Air 5th Gen", "Apple", "Electronics", 15, 5500, 8));
        products.add(new Product("Headphones", "Noise Cancelling Headphones", "Sony", "Electronics", 25, 12000, 12));
        products.add(new Product("Coffee Mug", "Thermal Coffee Mug", "Contigo", "Lifestyle", 100, 800, 25));
        products.add(new Product("Sports Shoes", "Running Shoes for Gym", "Adidas", "Footwear", 50, 400, 30));
        products.add(new Product("T-Shirt", "University Logo T-Shirt", "UniWear", "Clothing", 150, 500, 10));
        products.add(new Product("Lab Coat", "White Lab Coat for Experiments", "MedTech", "Clothing", 20, 100, 15));
        products.add(new Product("Water Bottle", "Stainless Steel Water Bottle", "Milton", "Lifestyle", 80, 700, 20));
        products.add(new Product("Laptop", "ThinkPad X1 Carbon Gen 11", "Lenovo", "Electronics", 10, 9000, 5));
        products.add(new Product("Projector", "Mini Projector for Presentations", "Epson", "Electronics", 5, 2500, 10));
        products.add(new Product("Pen", "Premium Gel Pen - Blue Ink", "Pilot", "Stationery", 300, 50, 5));
        products.add(new Product("Bicycle", "Hybrid Bicycle for Campus", "Schwinn", "Transport", 10, 15000, 20));

        // Return an unmodifiable list to prevent modification
        return Collections.unmodifiableList(products);
    }
}
