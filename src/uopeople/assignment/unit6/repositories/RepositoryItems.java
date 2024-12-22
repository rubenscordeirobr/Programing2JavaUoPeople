
package uopeople.assignment.unit6.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uopeople.assignment.unit6.data.DataStorage;
import uopeople.assignment.unit6.models.LibraryItemBase;

/**
 * RepositoryItems
 * 
 * A generic repository class for managing collections of library items.
 * 
 * This class implements the Repository pattern, providing methods to add,
 * remove, search, and retrieve library items from an in-memory list
 * while maintaining synchronization with the centralized `DataStorage`.
 * 
 * Key Concepts:
 * - **Generics**: The class uses a bounded type parameter `<T>` to ensure
 * it only works with types that extend `LibraryItemBase`.
 * - **Repository Pattern**: Centralizes data operations for specific item
 * types.
 * - **Stream API**: Utilized for filtering and searching items efficiently.
 * 
 * @param <T> The type of library item, which must extend LibraryItemBase.
 */
public class RepositoryItems<T extends LibraryItemBase> {

    // In-memory collection of library items
    private final ArrayList<T> items = new ArrayList<T>();

    // The class type of the items managed by this repository
    private final Class<T> itemType;

    /**
     * Constructs a repository for the specified item type.
     * 
     * Initializes the repository by fetching all items of the specified type
     * from the `DataStorage` and adding them to the in-memory collection.
     * 
     * @param itemType The class type of the items to manage.
     */
    public RepositoryItems(Class<T> itemType) {

        // I tried to get itemType without passing it as an argument from the T generic
        // parameter of the class, but all my attempts failed.
        // I managed to do it by extending the RepositoryItems class (e.g.,
        // RepositoryBook). I'm trying to figure out how to get itemType from the T
        // generic parameter without extending the class.

        this.itemType = itemType;
        List<T> itens = DataStorage.getItemsByType(this.itemType);
        items.addAll(itens);
    }

    /**
     * Retrieves all items in the repository.
     * 
     * @return A list of all items in the repository.
     */
    public List<T> getAll() {
        return this.items;
    }

    /**
     * Retrieves an item by its unique ID.
     * 
     * @param id The ID of the item to retrieve.
     * @return The item with the specified ID, or null if not found.
     */
    public T getItemById(int id) {
        for (T item : this.items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * Retrieves all items with a title matching or containing the specified text.
     * 
     * @param title The title text to search for.
     * @return A list of items with matching titles.
     */
    public List<T> getItemsByTitle(String title) {
        return this.items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all items with an author matching or containing the specified text.
     * 
     * @param author The author text to search for.
     * @return A list of items with matching authors.
     */
    public List<T> getItemByAuthor(String author) {

        return this.items.stream()
                .filter(item -> item.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Adds a new item to the repository.
     * 
     * Synchronizes the addition with the central `DataStorage`.
     * 
     * @param item The item to add.
     * @return true if the item was successfully added; false otherwise.
     */
    public boolean addItem(T item) {

        if (DataStorage.addItem(item)) {
            items.add(item);
            return true;
        }
        return false;
    }

    /**
     * Removes an item from the repository.
     * 
     * Synchronizes the removal with the central `DataStorage`.
     * 
     * @param item The item to remove.
     * @return true if the item was successfully removed; false otherwise.
     */
    public boolean removeItem(T item) {

        if (DataStorage.removeItem(item.getId())) {
            items.remove(item);
            return true;
        }
        return false;
    }

    /**
     * Removes an item by its unique ID from the repository.
     * 
     * @param id The ID of the item to remove.
     * @return true if the item was successfully removed; false otherwise.
     */
    public boolean removeItemById(int id) {
        for (T item : this.items) {
            if (item.getId() == id) {
                return this.removeItem(item);
            }
        }
        return false;
    }

    /**
     * Clears all items from the repository and the central `DataStorage`.
     */
    public void clear() {

        this.items.clear();
        DataStorage.clearItemsByType(this.itemType);
    }

}