package uopeople.assignment.unit5.models;

import uopeople.assignment.unit5.annotation.*;

/**
 * Represents the genre of a book.
 * 
 * Constraints:
 * - {@code genreId}: Auto-incremented primary key.
 * - {@code genreName}: Unique, not null, and must have a maximum length of 50
 * characters.
 * 
 * This class extends {@code Model}, allowing validation of its fields through
 * annotations
 * and the {@code validate} method.
 */
public class Genre extends Model {

    // Auto-incremented primary key for the Genre entity
    @PrimaryKey
    private Long genreId;

    // The name of the genre, which must be unique, not null, and within the
    // specified length
    @NotNull
    @Unique
    @MaxLength(50) // Maximum length as per schema constraints
    private String genreName;

    /**
     * Constructs a new Genre object with the specified genre ID and name.
     * Typically used when the genre ID is known (e.g., retrieved from the
     * database).
     * 
     * @param genreId   The ID of the genre.
     * @param genreName The name of the genre.
     */
    public Genre(long genreId, String genreName) {
        super();
        this.genreId = genreId;
        this.genreName = genreName;
    }

    /**
     * Constructs a new Genre object with the specified genre name.
     * The genre ID is set to {@code null}, indicating that it will be
     * auto-incremented.
     * 
     * @param genreName The name of the genre.
     */
    public Genre(String genreName) {
        super();
        this.genreId = null; // Auto-incremented
        this.genreName = genreName;
    }

    /**
     * Retrieves the ID of the genre.
     * 
     * @return The genre ID, or {@code null} if it has not been set (e.g., for new
     *         objects).
     */
    public Long getGenreId() {
        return genreId;
    }

    /**
     * Retrieves the name of the genre.
     * 
     * @return The genre name.
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * Updates the ID of the genre.
     * 
     * @param genreId The new ID of the genre.
     */
    public void setGenreId(Long genreId) {

        if (this.genreId != null && this.genreId > 0 && this.genreId != genreId) {
            throw new IllegalArgumentException("Cannot change the genre ID");
        }
        this.genreId = genreId;
    }

    /**
     * Updates the name of the genre.
     * 
     * @param genreName The new name of the genre.
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
