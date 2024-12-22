package uopeople.assignment.unit6;

import uopeople.assignment.unit6.enums.*;
import uopeople.assignment.utils.*;
import uopeople.assignment.unit6.models.*;
import uopeople.assignment.unit6.data.DataSeeder;

/**
 * Main
 * 
 * The entry point for the Library Catalog Management application.
 * 
 * This class initializes the application by seeding sample data into the
 * storage
 * and providing a menu-driven interface for users to interact with different
 * catalogs (Book, DVD, Magazine, and Global). The application is designed to
 * dynamically handle multiple types of library items using generics and
 * an object-oriented approach.
 */
public class Main {

    /**
     * The main method.
     * 
     * Seeds the initial data and launches the main menu.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        DataSeeder.seedData();
        displayMenu();
    }

    /**
     * Displays the main menu for catalog selection.
     * 
     * Allows users to choose which catalog to manage and dynamically
     * instantiates the appropriate catalog based on their selection.
     * Includes an option to quit the application.
     * 
     * @param <T> The generic type representing the library item class managed
     *            by the selected catalog.
     */
    private static <T> void displayMenu() {

        while (true) {

            System.out.println("\n--- Library Catalog Menu ---");
            System.out.println("Type q or quit to exit the program");

            CatalogType catalogType = InputUtil.getEnumSelectionFromUser(
                    CatalogType.class,
                    "Please select which catalog you would like to manage:");

            Catalog<?> catalog = getCatalog(catalogType);
            catalog.manage();
        }

    }

     /**
     * Returns a catalog instance for the selected catalog type.
     * 
     * Dynamically determines the appropriate type of catalog to create 
     * based on the user-selected catalog type.
     * 
     * @param catalogType The type of catalog to create (e.g., BOOK, DVD, MAGAZINE, GLOBAL).
     * @return A catalog instance of the appropriate type, or null if the type is invalid.
     */
    private static Catalog<?> getCatalog(CatalogType catalogType) {
        switch (catalogType) {
            case BOOK:
                return new Catalog<Book>(catalogType);
            case DVD:
                return new Catalog<Dvd>(catalogType);
            case MAGAZINE:
                return new Catalog<Magazine>(catalogType);
            case GLOBAL:
                return new Catalog<LibraryItemBase>(catalogType);
            default:
                return null;
        }
    }

}
