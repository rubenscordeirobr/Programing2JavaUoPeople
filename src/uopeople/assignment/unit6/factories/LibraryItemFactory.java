package uopeople.assignment.unit6.factories;

import uopeople.assignment.unit6.enums.BookGenre;
import uopeople.assignment.unit6.enums.CatalogType;
import uopeople.assignment.unit6.enums.DvdGenre;
import uopeople.assignment.unit6.enums.EnumDescription;
import uopeople.assignment.unit6.enums.MagazineGenre;
import uopeople.assignment.unit6.models.*;

/**
 * Factory class for creating instances of library items.
 * 
 * This class demonstrates advanced use of generics by providing a method
 * to create library items of different types (Book, Magazine, DVD) based
 * on the specified catalog type and genre.
 * 
 * Key Concepts:
 * - **Generics**: The method leverages a bounded type parameter `TEnum` to
 * ensure
 * that the genre provided is an enumeration that implements the
 * `EnumDescription`
 * interface, ensuring compile-time type safety.
 * - **Factory Pattern**: Simplifies object creation by encapsulating the logic
 * within a single method, promoting code reusability and modularity.
 */
public class LibraryItemFactory {

    /**
     * Creates a library item based on the specified catalog type, title, author,
     * and genre.
     * 
     * This method uses generics to support various types of genres (`TEnum`),
     * ensuring
     * type safety while working with enums that implement `EnumDescription`.
     * 
     * @param <TEnum>     The type of genre, which must be an enum implementing
     *                    EnumDescription.
     * @param catalogType The catalog type of the item (e.g., BOOK, MAGAZINE, DVD).
     * @param title       The title of the library item.
     * @param author      The author of the library item.
     * @param genre       The genre of the library item, specified as a bounded
     *                    generic type.
     * @return A new instance of a library item (Book, Magazine, or DVD)
     *         based on the catalog type, or null if the catalog type is invalid.
     */
    public static <TEnum extends Enum<TEnum> & EnumDescription> LibraryItemBase createLibraryItem(
            CatalogType catalogType,
            String title,
            String author,
            TEnum genre) {

        // Create library item based on catalog type
        switch (catalogType) {
            case BOOK:
                return new Book(title, author, (BookGenre) genre);
            case MAGAZINE:
                return new Magazine(title, author, (MagazineGenre) genre);
            case DVD:
                return new Dvd(title, author, (DvdGenre) genre);
            case GLOBAL:
                // Throw exception for global catalog type
                throw new IllegalArgumentException("Global catalog type cannot be used to create library items.");
            default:
                // Throw exception for not supported catalog type
                throw new IllegalArgumentException("Invalid catalog type: " + catalogType);
        }
    }
}
