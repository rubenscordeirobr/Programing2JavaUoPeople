package uopeople.assignment.unit6.models;

import uopeople.assignment.unit6.enums.*;

/**
 * Represents a book in the library catalog.
 * 
 * This class demonstrates the application of generics by specifying the
 * type parameter `TGenre` from the `LibraryItem` superclass as `BookGenre`.
 * 
 * By using generics, this class ensures type safety and flexibility in working
 * with
 * different genres specific to books while leveraging the structure and
 * behavior
 * defined in the abstract `LibraryItem` class.
 */
public class Book extends LibraryItem<BookGenre> {

    /**
     * Constructs a book with a title, author, and genre.
     * 
     * @param title  The title of the book.
     * @param author The author of the book.
     * @param genre  The genre of the book, specified as `BookGenre`.
     */
    public Book(String title, String author, BookGenre genre) {

        super(title, author, genre);
    }

    /**
     * Gets the catalog type for the book.
     * 
     * Overrides the method in the `LibraryItem` superclass to define the
     * catalog type specific to books as `CatalogType.BOOK`.
     * 
     * @return The catalog type for books.
     */
    @Override
    public CatalogType getCatalogType() {
        return CatalogType.BOOK;
    }

}
