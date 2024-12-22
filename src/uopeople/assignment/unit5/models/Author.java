package uopeople.assignment.unit5.models;

import uopeople.assignment.unit5.annotation.*;

/**
 * Represents an author in the system.
 * 
 * Constraints:
 * - {@code authorId}: Auto-incremented primary key.
 * - {@code authorName}: Not null, and must have a maximum length of 255 characters.
 * 
 * This class extends {@code Model}, allowing validation of its fields through annotations
 * and the {@code validate} method.
 */
public class Author extends Model {

    // Auto-incremented primary key for the Author entity
    @PrimaryKey
    private Long authorId;

    // Name of the author, which must not be null and have a maximum length of 255 characters
    @NotNull
    @MaxLength(255)
    private String authorName;

    /**
     * Constructs an empty Author object.
     * Used when the values will be set later using setters.
     */
    public Author() {
        super();
    }

    /**
     * Constructs a new Author object with the specified author name.
     * The author ID is set to {@code null}, indicating that it will be auto-incremented.
     * 
     * @param authorName The name of the author.
     */
    public Author(String authorName) {
        super();
        this.authorId = null; // Auto-incremented
        this.authorName = authorName;
    }

    /**
     * Constructs a new Author object with the specified author ID and name.
     * Typically used when the author ID is known (e.g., retrieved from the database).
     * 
     * @param authorId   The ID of the author.
     * @param authorName The name of the author.
     */
    public Author(Long authorId, String authorName) {
        super();
        this.authorId = authorId;
        this.authorName = authorName;
    }

    /**
     * Retrieves the ID of the author.
     * 
     * @return The author ID, or {@code null} if it has not been set (e.g., for new objects).
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * Retrieves the name of the author.
     * 
     * @return The author's name.
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Updates the name of the author.
     * 
     * @param authorName The new name of the author.
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
