package uopeople.assignment.unit6.enums;

/**
 * Represents genres of books with a name and description.
 */
public enum BookGenre implements EnumDescription {

    FICTION("Fiction", "A literary genre that is based on imaginary characters and events."),
    NON_FICTION("Non-Fiction", "A literary genre that is based on real events and facts."),
    SCIENCE_FICTION("Science Fiction", "A literary genre that is based on futuristic science and technology."),
    MYSTERY("Mystery", "A literary genre that is based on solving a crime or puzzle."),
    HORROR("Horror", "A literary genre that is based on fear and suspense."),
    ROMANCE("Romance", "A literary genre that is based on love and relationships."),
    BIOGRAPHY("Biography", "A literary genre that is based on a person's life story."),
    AUTOBIOGRAPHY("Autobiography", "A literary genre that is based on the author's life story."),
    HISTORY("History", "A literary genre that is based on past events and people."),
    POETRY("Poetry", "A literary genre that is based on the expression of feelings and ideas in verse.");

    private final String genre;
    private final String description;

    /**
     * Constructs a new book genre with a name and description.
     * @param genre the name of the genre
     * @param description the description of the genre
     */
    BookGenre(String genre, String description) {
        this.genre = genre;
        this.description = description;
    }

    /**
     * Returns the name of the genre.
     * @return the name of the genre
     */
    public String getGenre() {
        return this.genre;
    }

    /**
     * Returns the description of the genre.
     * @return the description of the genre
     */
    @Override
    public String getName() {
        return this.genre;
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
        return this.genre;
    }
}
