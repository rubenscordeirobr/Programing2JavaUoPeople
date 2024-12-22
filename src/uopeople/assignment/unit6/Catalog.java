package uopeople.assignment.unit6;

import java.util.List;

import uopeople.assignment.unit6.models.*;
import uopeople.assignment.unit6.repositories.RepositoryItems;
import uopeople.assignment.unit6.enums.*;
import uopeople.assignment.unit6.factories.LibraryItemFactory;
import uopeople.assignment.utils.*;

/**
 * Catalog
 * 
 * A generic class for managing a catalog of library items.
 * 
 * This class uses generics to support operations on various types of
 * library items (`Book`, `Dvd`, `Magazine`, etc.), while providing a
 * consistent interface for adding, searching, listing, and removing items.
 * 
 * Key Features:
 * - **Generics**: Ensures type safety by restricting operations to a specific
 * type of `LibraryItemBase` or its subclasses.
 * - **Repository Pattern**: Integrates with `RepositoryItems` for storage
 * and retrieval of items.
 * - **Dynamic Catalog Management**: Handles item-specific functionality
 * based on the `CatalogType`.
 * 
 * @param <T> The type of library item managed by this catalog, extending
 *            LibraryItemBase.
 */
public class Catalog<T extends LibraryItemBase> {

    // Repository for managing items of type T
    private final RepositoryItems<T> repository;

    // The type of catalog (e.g., Book, DVD, Magazine, or Global)
    private final CatalogType catalogType;

    // Flag to indicate if the catalog is global
    private final boolean isGlobalCatalog;

    /**
     * Constructs a catalog for a specific catalog type.
     * 
     * @param catalogType The type of catalog (e.g., BOOK, DVD, MAGAZINE, GLOBAL).
     */
    public Catalog(CatalogType catalogType) {
        this.catalogType = catalogType;
        this.repository = new RepositoryItems<T>(this.getItemType());
        this.isGlobalCatalog = catalogType == CatalogType.GLOBAL;
    }

    /**
     * Main management interface for the catalog.
     * 
     * Provides a menu-driven system for users to perform operations
     * such as listing, searching, adding, or removing items.
     */
    public void manage() {

        while (true) {
            try {

                String type = this.catalogType.getName();

                System.out.println("\n--- This is the " + type + " Catalog Menu ---");

                RepositoryOptions options = InputUtil.getEnumSelectionFromUser(
                        RepositoryOptions.class,
                        "Please select an option:");

                switch (options) {
                    case LIST:
                        listItems();
                        break;
                    case SEARCH_BY_TILE:
                        searchByTitle();
                        break;
                    case SEARCH_BY_AUTHOR:
                        searchByAuthor();
                        break;
                    case ADD:
                        addItem();
                        break;
                    case REMOVE:
                        removeItem();
                        break;
                    case EXIT:
                        // Exit the catalog management and return to the main menu
                        return;

                    default:
                        System.out.println("Invalid option");
                        break;
                }
            } catch (Exception e) {
                PrintUtil.printFailMessage("An error occurred: " + e.getMessage());
            }
        }

    }

    /**
     * Lists all items in the catalog.
     * Prints the details of each item if the catalog is not empty.
     */
    private void listItems() {

        List<T> items = this.repository.getAll();
        if (items == null || items.isEmpty()) {
            System.out.println("The catalog is empty.");
        } else {
            displayItems(items);
        }
    }

    /**
     * Searches items by title.
     * Prompts the user for a title and displays matching items.
     */
    private void searchByTitle() {

        String title = InputUtil.getInputFromUser("Enter the title to search for:");
        List<T> items = this.repository.getItemsByTitle(title);

        if (items == null || items.isEmpty()) {
            System.out.println("No item found with the title: " + title);
        } else {
            displayItems(items);
        }
    }

    /**
     * Searches items by author.
     * Prompts the user for an author name and displays matching items.
     */
    private void searchByAuthor() {

        String author = InputUtil.getInputFromUser("Enter the author to search for:");
        List<T> items = this.repository.getItemByAuthor(author);

        if (items == null || items.isEmpty()) {
            System.out.println("No item found with the author: " + author);
        } else {
            displayItems(items);
        }
    }

    /**
     * Adds a new item to the catalog.
     * Prompts the user for title, author, and genre, then creates and adds the
     * item.
     * Prevents adding items to the global catalog.
     */
    @SuppressWarnings("unchecked")
    private <TEnum extends Enum<TEnum> & EnumDescription> void addItem() {

        if (this.catalogType == CatalogType.GLOBAL) {
            PrintUtil.printFailMessage("Cannot add items to the Global catalog.");
            return;
        }

        String title = InputUtil.getInputFromUser("Enter the title of the item:");
        String author = InputUtil.getInputFromUser("Enter the author of the item:");
        TEnum genre = (TEnum) getGenreSelection();
        if (genre == null) {
            PrintUtil.printFailMessage("Invalid genre selection.");
            return;
        }

        T item = (T) LibraryItemFactory.createLibraryItem(
                this.catalogType, title, author, genre);

        if (this.repository.addItem(item)) {
            PrintUtil.printSuccessMessage("Item added successfully.");
        } else {
            PrintUtil.printFailMessage("Failed to add the item.");
        }
    }

    /**
     * Prompts the user to select a genre for the item.
     * 
     * @return The selected genre.
     */
    private EnumDescription getGenreSelection() {

        String message = "Please select the genre of the item:";
        switch (catalogType) {

            case BOOK:
                return InputUtil.getEnumSelectionFromUser(BookGenre.class, message);
            case DVD:
                return InputUtil.getEnumSelectionFromUser(DvdGenre.class, message);
            case MAGAZINE:
                return InputUtil.getEnumSelectionFromUser(MagazineGenre.class, message);
            default:
                return null;
        }
    }


    /**
     * Removes an item by its ID.
     * Prompts the user for an ID and removes the corresponding item from the
     * catalog.
     */
    private void removeItem() {

        listItems();

        int itemId = InputUtil.getIntInputFromUser("Enter the ID of the item to remove:", 1,
                LibraryItemBase.getNextItemId() - 1);

        if (this.repository.removeItemById(itemId)) {
            PrintUtil.printSuccessMessage("Item removed successfully.");
        } else {
            PrintUtil.printFailMessage("The id " + itemId + " does not exist in the catalog.");
        }
    }
 
    /**
     * Displays the details of the items in the catalog.
     * 
     * @param items The list of items to display.
     */
    private void displayItems(List<T> items) {

        String header = String.format("%-5s | %-40s | %-30s | %-20s",
                "ID", "Title", "Author", "Genre");

        if (this.isGlobalCatalog) {
            header += " | Catalog Type ";
        }

        if (items == null || items.isEmpty()) {
            PrintUtil.printFailMessage("No items found.");
            return;
        }

        System.out.println(header);
        System.out.println("-".repeat(header.length()));

        for (T item : items) {

            String itemInfo = String.format("%-5d | %-40s | %-30s | %-20s",
                    item.getId(), item.getTitle(), item.getAuthor(), item.getGenreName());

            if (this.isGlobalCatalog) {
                itemInfo += " | " + item.getCatalogType().getName();
            }

            System.out.println(itemInfo);
        }
    }

    
    /**
     * Dynamically determines the item type based on the catalog type.
     * 
     * @return The class type of items in the catalog.
     */
    @SuppressWarnings("unchecked")
    private Class<T> getItemType() {

        return switch (this.catalogType) {
            case BOOK -> (Class<T>) Book.class;
            case DVD -> (Class<T>) Dvd.class;
            case MAGAZINE -> (Class<T>) Magazine.class;
            case GLOBAL -> (Class<T>) LibraryItemBase.class;
            default -> throw new IllegalArgumentException("Invalid catalog type.");
        };
    }
}