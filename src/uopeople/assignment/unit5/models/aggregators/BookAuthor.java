package uopeople.assignment.unit5.models.aggregators;

import uopeople.assignment.unit5.annotation.*;
import uopeople.assignment.unit5.models.Model;

/**
 * Represents the many-to-many relationship between books and authors.
 * Constraints:
 * - isbn: Foreign key referencing books, maximum length 13.
 * - author_id: Foreign key referencing authors.
 * - Primary key: (isbn, author_id).
 */
public class BookAuthor extends Model {

    @ForeignKey
    @MaxLength(13) // Matches ISBN length in the books table
    private String isbn; // References Book

    @ForeignKey
    private Long authorId; // References Author

    // Getters and Setters
}
