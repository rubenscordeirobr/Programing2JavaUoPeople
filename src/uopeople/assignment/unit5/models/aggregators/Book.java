package uopeople.assignment.unit5.models.aggregators;
 
import java.util.Set;
import uopeople.assignment.unit5.annotation.*;
import uopeople.assignment.unit5.models.Author;
import uopeople.assignment.unit5.models.Genre;
import uopeople.assignment.unit5.models.Model;

/**
 * Represents a book.
 * Constraints:
 * - isbn: Primary key, maximum length 13.
 * - genre_id: Foreign key referencing Genre.
 * - title: Not null, maximum length 255.
 * - quantity: Not null, must be >= 0.
 */
public class Book extends Model {

    @PrimaryKey(false)
    @MaxLength(13) // ISBN has a maximum length of 13 characters
    private String isbn; // Primary Key

    @ForeignKey
    private Genre genre; // Many-to-One relationship with Genre

    @NotNull
    @MaxLength(255) // Max length specified in the schema
    private String title;

    @NotNull
    @Min(0) // Quantity must be greater than or equal to 0
    private int quantity;

    private Set<Author> authors; // Many-to-Many relationship with authors
    private Set<BookEdition> editions; // One-to-Many relationship with book editions,


    public Book(String isbn, Genre genre, String title, int quantity) {
        this.isbn = isbn;
        this.genre = genre;
        this.title = title;
        this.quantity = quantity;
    }

    // Getters and Setters

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Set<BookEdition> getEditions() {
        return editions;
    }

    
}
