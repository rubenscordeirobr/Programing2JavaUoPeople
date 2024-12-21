package uopeople.assignment.unit6.enums;
/**
 *  Represents types of items in a library catalog with a name and description.
 */
public enum CatalogType  implements EnumDescription
 {
    GLOBAL("Global", "All items in the library."),
    BOOK("Book", "Books that are available for reading."),
    MAGAZINE("Magazine", "Magazines that are available for reading."),
    DVD("DVD", "DVDs that are available for watching.");
    
    private final String name;
    private final String description;
    
    /**
     * Constructs a new catalog type with a name and description.
     * @param name the name of the catalog type
     * @param description the description of the catalog type
     */
    private CatalogType(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    /**
     * Returns the name of the catalog type.
     * @return the name of the catalog type
     */
    @Override
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the description of the catalog type.
     * @return the description of the catalog type
     */
    @Override
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Returns the name of the catalog type.
     * @return the name of the catalog type
     */
    @Override
    public String toString() {
        return this.name;
    }

    
}
