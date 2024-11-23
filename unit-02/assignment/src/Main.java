import com.ecommerce.Customer;
import com.ecommerce.Product;
import com.ecommerce.orders.Order;
import com.ecommerce.orders.OrderItem;
import com.ecommerce.utils.PrintUtil;
import com.ecommerce.utils.StringUtil;
import com.ecommerce.utils.DataStorageUtil;
import com.ecommerce.utils.FormatUtil;
import com.ecommerce.utils.InputUtil;
import com.ecommerce.enums.MenuOptions;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.enums.PaymentMethod;
import com.ecommerce.enums.ShippingMethod;

import java.util.List;

/**
 * Main class for the Black Friday E-commerce application.
 * <p>
 * This class handles the main flow of the e-commerce system, including customer
 * account creation, browsing products, adding/removing items from the cart,
 * and completing orders through a menu-driven interface.
 * </p>
 */
public class Main {

    // Debug flag for development purposes
    private static final boolean DEBUG = true;

    /**
     * Main method to start the e-commerce application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Black Friday E-commerce of University of the People");
        System.out.println("First, we need to create your account.");

        // Create a customer account
        Customer customer = getCustomerFromUserInput();

        // Start a new order for the customer
        showMenu(customer);
    }

    /**
     * Displays the main menu and handles user input for menu options.
     *
     * @param customer the Customer object associated with the order
     */
    private static void showMenu(Customer customer) {

        Order order = new Order(customer);

        while (true) {
            System.out.println();
            // Display menu options and get user selection
            MenuOptions option = InputUtil.getEnumSelectionFromUser(MenuOptions.class, "Please select an option: ");

            switch (option) {
                case SHOW_PRODUCTS:
                    showProducts();
                    break;
                case ADD_ITEM:
                    addItem(order);
                    break;
                case EDIT_ITEM:
                    editItem(order);
                    break;
                case SHOW_ORDER_ITEMS:
                    showItems(order);
                    break;
                case CHECKOUT:
                    checkout(order);
                    return; // Exit the loop after completing the order
                case EXIT:
                    System.out.println("Thank you for shopping with us. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    PrintUtil.printFailMessage("Invalid option. Please try again.");
                    break;
            }
        }
    }

    /**
     * Creates a customer account using user input or default debug data.
     *
     * @return the created Customer object
     */
    private static Customer getCustomerFromUserInput() {

        // Debug customer for development for faster testing
        if (DEBUG) {
            // Debug customer for development
            return new Customer("Rubens Cordeiro",
                    "rubenscordeiro@my.uopeople.com",
                    "42 99999-9999",
                    "Rua America Central, 134",
                    "Guarapuava, Brazil");
        }

        // Gather customer details from user input
        String name = InputUtil.getInputFromUser("Enter your name: ");
        String email = InputUtil.getInputFromUser("Enter your email: ");
        String phone = InputUtil.getInputFromUser("Enter your phone number: ");
        String address = InputUtil.getInputFromUser("Enter your address: ");
        String city = InputUtil.getInputFromUser("Enter your city: ");

        Customer newCustomer = new Customer(name, email, phone, address, city);

        PrintUtil.printSuccessMessage(
                "Welcome " + newCustomer.getName() + "! Your account has been created successfully.");
        PrintUtil.printSuccessMessage("--------------- ENJOY BLACK FRIDAY SALES -----------------");

        return newCustomer;
    }

    /**
     * Displays a list of all available products in a formatted table.
     * <p>
     * Each product includes details such as its ID, name, description, brand,
     * category, stock, price, discount, and final price.
     * </p>
     */
    private static void showProducts() {
        System.out.println("Browsing products...");
        List<Product> products = DataStorageUtil.getProducts();

        // Display header
        String header = StringUtil.padRight("Product ID", 12) + " | "
                + StringUtil.padRight("Name", 15) + " | "
                + StringUtil.padRight("Description", 30) + " | "
                + StringUtil.padRight("Brand", 15) + " | "
                + StringUtil.padRight("Category", 15) + " | "
                + StringUtil.padRight("Stock", 10) + " | "
                + StringUtil.padRight("Price", 10) + " | "
                + StringUtil.padRight("Discount", 10) + " | "
                + StringUtil.padRight("Final Price", 15);
        PrintUtil.printSuccessMessage(header.toUpperCase());

        // Display product data
        for (Product product : products) {
            String line = StringUtil.padRight(String.valueOf(product.getProductId()), 12) + " | "
                    + StringUtil.padRight(product.getName(), 15) + " | "
                    + StringUtil.padRight(product.getDescription(), 30) + " | "
                    + StringUtil.padRight(product.getBrand(), 15) + " | "
                    + StringUtil.padRight(product.getCategory(), 15) + " | "
                    + StringUtil.padRight(String.valueOf(product.getStock()), 10) + " | "
                    + StringUtil.padRight(FormatUtil.formatMoney(product.getPrice()), 10) + " | "
                    + StringUtil.padRight(FormatUtil.formatPercentage(product.getDiscountPercentage()), 10) + " | "
                    + StringUtil.padRight(FormatUtil.formatMoney(product.getFinalPrice()), 15);
            System.out.println(line);
        }
    }

    /**
     * Adds a product to the current order based on user input.
     *
     * @param order the current Order object
     */
    private static void addItem(Order order) {
        showProducts();

        int productId = InputUtil.getIntInputFromUser("Enter product ID: ", 1, Integer.MAX_VALUE);
        Product product = DataStorageUtil.getProductById(productId);

        if (product == null) {
            PrintUtil.printFailMessage("The product with ID " + productId + " was not found.");
            return;
        }

        // Check if the product is out of stock
        if (product.getStock() == 0) {
            PrintUtil.printWarningMessage("Sorry, the product " + product.getName() + " is out of stock.");
            return;

        }

        // Prompt user for quantity
        int quantity = InputUtil.getIntInputFromUser("Enter quantity: ", 1, Integer.MAX_VALUE);

        // check if the quantity exceeds the available stock
        if (quantity > product.getStock()) {
            PrintUtil.printFailMessage("Quantity exceeds available stock. Max is " + product.getStock());
            return;
        }

        order.addItem(product, quantity);
        PrintUtil.printSuccessMessage("Product added " + product.getName() + " to the cart.");
    }

    /**
     * Edits or removes an item from the order.
     *
     * @param order the current Order object
     */
    public static void editItem(Order order) {

        showItems(order);

        int productId = InputUtil.getIntInputFromUser("Enter Product ID: ", 1, Product.getMaxProductId());
        Product productItem = order.geProductById(productId);

        if (productItem == null) {
            PrintUtil.printFailMessage("The product with ID " + productId + " was not found in the cart.");
            return;
        }

        // Prompt user for new quantity or 0 to remove the item
        int quantity = InputUtil.getIntInputFromUser("Enter new quantity or 0 to remove: ");

        // if quantity is 0, remove the item from the order
        if (quantity == 0) {

            order.removeItem(productItem);
            PrintUtil.printSuccessMessage(
                    "The product " + productItem.getName() + " was removed from the cart.");
            return;
        }

        // Check if the quantity exceeds the available stock
        if (quantity > productItem.getStock()) {
            PrintUtil.printFailMessage("Quantity exceeds available stock. Max is " + productItem.getStock());
            return;
        }

        // Update the quantity of the product in the order
        order.updateItemQuantity(productItem, quantity);

        PrintUtil.printSuccessMessage("The quantity of " + productItem.getName() + " was updated.");

    }

    /**
     * Displays the items currently in the order.
     *
     * @param order the current Order object
     */
    private static void showItems(Order order) {
        List<OrderItem> items = order.getItems();

        if (items.isEmpty()) {
            PrintUtil.printWarningMessage("Your cart is empty.");
            return;
        }

        String header = StringUtil.padRight("Product ID", 12) + " | "
                + StringUtil.padRight("Name", 15) + " | "
                + StringUtil.padRight("Quantity", 10) + " | "
                + StringUtil.padRight("Unit Price", 10) + " | "
                + StringUtil.padRight("Total Price", 15);
        PrintUtil.printSuccessMessage(header.toUpperCase());

        for (OrderItem item : items) {
            String line = StringUtil.padRight(String.valueOf(item.getProduct().getProductId()), 12) + " | "
                    + StringUtil.padRight(item.getProduct().getName(), 15) + " | "
                    + StringUtil.padRight(String.valueOf(item.getQuantity()), 10) + " | "
                    + StringUtil.padRight(FormatUtil.formatMoney(item.getUnitPrice()), 10) + " | "
                    + StringUtil.padRight(FormatUtil.formatMoney(item.getTotalPrice()), 15);
            System.out.println(line);
        }

        // Display the order total
        PrintUtil.printSuccessMessage("Order total: " + FormatUtil.formatMoney(order.getOrderTotal()));
    }

    /**
     * Handles the checkout process for the current order.
     *
     * @param order the current Order object
     */
    private static void checkout(Order order) {

        PrintUtil.printSuccessMessage("Checking out...");
        PrintUtil.printSuccessMessage("Order total: " + FormatUtil.formatMoney(order.getOrderTotal()));

        PaymentMethod paymentMethod = InputUtil.getEnumSelectionFromUser(PaymentMethod.class,
                "Please select a payment method: ");
        ShippingMethod shippingMethod = InputUtil.getEnumSelectionFromUser(ShippingMethod.class,
                "Please select a shipping method: ");

        order.setPaymentMethod(paymentMethod);
        order.setShippingMethod(shippingMethod);
        order.setOrderStatus(OrderStatus.CONFIRMED);

        PrintUtil.printSuccessMessage("Order ID: " + order.getOrderId());
        PrintUtil.printSuccessMessage("Payment method: " + order.getPaymentMethod().getDescription());
        PrintUtil.printSuccessMessage("Shipping method: " + order.getShippingMethod().getDescription());
        PrintUtil.printSuccessMessage("Order total: " + FormatUtil.formatMoney(order.getOrderTotal()));
        PrintUtil.printSuccessMessage(
                "Estimated shipping date: " + FormatUtil.formatLongDate(order.getEstimateShippingDate()));
        PrintUtil.printSuccessMessage("Thank you for shopping with us. Your order has been placed.");

    }
}
