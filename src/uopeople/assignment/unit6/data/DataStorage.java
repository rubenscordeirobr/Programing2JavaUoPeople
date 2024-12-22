package uopeople.assignment.unit6.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import uopeople.assignment.unit6.models.LibraryItemBase;

/**
 * DataStorage
 * 
 * A static class for managing library items. Provides functionality for adding,
 * removing, and retrieving library items from a central in-memory storage.
 * 
 * Key Concepts:
 * - **Generics**: Uses generics to filter and retrieve items by type
 * dynamically.
 * - **Java Collections**: Utilizes a HashMap for efficient item storage and
 * retrieval.
 * - **Streams**: Leverages Java Streams for filtering and transforming
 * collections.
 */
public class DataStorage {

    // In-memory storage for library items, indexed by their unique ID
    private static Map<Integer, LibraryItemBase> libraryItems = new HashMap<>();

    /**
     * Adds a library item to the storage.
     * 
     * @param item The library item to add.
     * @return true if the item was added successfully; false if the item already
     *         exists.
     */
    public static boolean addItem(LibraryItemBase item) {

        if (libraryItems.containsKey(item.getId())) {
            return false;
        }
        libraryItems.put(item.getId(), item);
        return true;
    }

    /**
     * Removes a library item by its unique ID.
     * 
     * @param id The ID of the item to remove.
     * @return true if the item was removed; false if the item does not exist.
     */
    public static boolean removeItem(int id) {

        if (!libraryItems.containsKey(id)) {
            return false;
        }
        libraryItems.remove(id);
        return true;
    }

    /**
     * Retrieves a library item by its unique ID.
     * 
     * @param id The ID of the item to retrieve.
     * @return The library item with the specified ID, or null if it does not exist.
     */
    public static LibraryItemBase getItemById(int id) {
        return libraryItems.get(id);
    }

    /**
     * Retrieves all library items of a specified type using generics.
     * 
     * This method filters the stored library items and returns a list of items
     * that match the specified type. The generic type parameter `<T>` ensures
     * type safety, allowing only items that extend `LibraryItemBase` to be
     * processed.
     * 
     * @param <T>   The type of library items to retrieve, extending
     *              LibraryItemBase.
     * @param clazz The class representing the desired type.
     * @return A list of library items of the specified type.
     * @throws IllegalArgumentException if the class type is null.
     */
    public static <T extends LibraryItemBase> List<T> getItemsByType(Class<T> clazz) {

        if (clazz == null) {
            throw new IllegalArgumentException("Class type cannot be null.");
        }

        return libraryItems.values().stream()
                .filter(clazz::isInstance) // Check if the item is an instance of the provided class
                .map(clazz::cast) // Cast the item to the desired type
                .collect(Collectors.toList());
    }

    /**
     * Clears all library items of a specified type from the storage.
     * 
     * @param itemType The class representing the type of items to clear.
     */
    public static void clearItemsByType(Class<?> itemType) {

        /**
         * The values() method returns a Collection view of the values contained in the map.
         * This view is directly backed by the original map, meaning any changes made to
         * this Collection (e.g., removing items) will also affect the underlying map.
         * The removeIf method, introduced in Java 8, allows us to iterate over this
         * view and remove items based on a specified condition. Here, we use it to
         * remove all  items of the specified type from the map.
         * Note: Initially, it might seem like values() creates a new collection
         * instance, but it does not. Instead, it provides a live view of the map's
         * values, ensuring the original map is modified directly when changes are made to this view.
         */
        libraryItems.values().removeIf(itemType::isInstance);
    }

}
