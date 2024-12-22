package uopeople.assignment.unit6.models;

import uopeople.assignment.unit6.enums.EnumDescription;

/**
 * Represents a library item with a title, author, and a genre of a generic
 * type.
 * 
 * This class demonstrates the use of generics by allowing flexibility in
 * defining
 * the type of genre (`TGenre`) for a library item. The generic type must
 * implement
 * the `EnumDescription` interface to ensure consistent structure for name and
 * description.
 * 
 * Key Concepts of Generics:
 * - Allows creating flexible and reusable code by enabling parameterization of
 * types.
 * - Provides compile-time type safety, ensuring that the genre matches the
 * expected type.
 * 
 * @param <TGenre> The type of genre for the library item, which must be an
 *                 enumeration (`enum`) and implement the `EnumDescription`
 *                 interface.
 */
public abstract class LibraryItem<TGenre extends Enum<TGenre> & EnumDescription> extends LibraryItemBase {

    // The genre of the library item, using the generic type TGenre
    private TGenre genre;

    /**
     * Constructs a library item with the specified title, author, and genre.
     * 
     * @param title  The title of the library item.
     * @param author The author of the library item.
     * @param genre  The genre of the library item, defined as a generic type
     *               TGenre.
     */
    public LibraryItem(String title, String author, TGenre genre) {
        super(title, author);
        this.genre = genre;
    }

    /**
     * Gets the genre of the library item.
     * 
     * Demonstrates the power of generics by returning a strongly typed genre
     * instance.
     * 
     * @return The genre as an instance of TGenre.
     */
    public TGenre getGenre() {
        return this.genre;
    }

    /**
     * Gets the name of the genre.
     * 
     * Utilizes the generic type TGenre's implementation of `EnumDescription`
     * to retrieve the name of the genre.
     * 
     * @return The name of the genre.
     */
    @Override
    public String getGenreName() {
        return this.genre.getName();
    }

    /**
     * Gets the description of the genre.
     * 
     * Uses the generic type TGenre's implementation of `EnumDescription`
     * to retrieve the description of the genre.
     * 
     * @return The description of the genre.
     */
    @Override
    public String getGenreDescription() {
        return this.genre.getDescription();
    }

}
