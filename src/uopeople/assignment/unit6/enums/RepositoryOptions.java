package uopeople.assignment.unit6.enums;

/**
 * Represents options for managing a library repository with a name and description.
 */
public enum RepositoryOptions implements EnumDescription {
    
    LIST("List", "List all items in the library."),
    SEARCH_BY_TILE("Search by Title", "Search for an item by title."),
    SEARCH_BY_AUTHOR("Search by Author", "Search for an item by author."),
    ADD("Add", "Add a new item to the library."),
    REMOVE("Remove", "Remove an item from the library."),
    EXIT("Exit", "Return to the main menu.");

    private final String name;
    private final String description;

    /**
     * Constructs a new repository option with a name and description.
     * @param name the name of the option
     * @param description the description of the option
     */
    private RepositoryOptions(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the name of the option.
     * @return the name of the option
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the option.
     * @return the description of the option
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the name of the option.
     * @return the name of the option
     */
    @Override
    public String toString() {
        return this.name;
    }
    
}
