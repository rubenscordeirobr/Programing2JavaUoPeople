package uopeople.assignment.unit6.enums;

/**
 * Represents genres of DVDs with a name and description.
 */
public enum DvdGenre implements EnumDescription {

    ACTION("Action", "Action movies"),
    COMEDY("Comedy", "Comedy movies"),
    DRAMA("Drama", "Drama movies"),
    HORROR("Horror", "Horror movies"),
    SCIENCE_FICTION("Science Fiction", "Science Fiction movies");

    private final String name;
    private final String description;

    /**
     * Constructs a new DVD genre with a name and description.
     * @param name the name of the genre
     * @param description the description of the genre
     */
    private DvdGenre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the name of the genre.
     * @return the name of the genre
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the genre.
     * @return the description of the genre
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the name of the genre.
     * @return the name of the genre
     */
    @Override
    public String toString() {
        return this.name;
    }
}
