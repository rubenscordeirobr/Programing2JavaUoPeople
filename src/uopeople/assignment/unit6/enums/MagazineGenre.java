package uopeople.assignment.unit6.enums;

/**
 * Represents genres of magazines with a name and description.
 */
public enum MagazineGenre  implements EnumDescription{
 
    ART("Art", "Magazines that focus on art and design."),
    BUSINESS("Business", "Magazines that focus on business and finance."),
    COMPUTERS("Computers", "Magazines that focus on technology and computers."),
    HEALTH("Health", "Magazines that focus on health and wellness."),
    LIFESTYLE("Lifestyle", "Magazines that focus on lifestyle and culture."),
    SCIENCE("Science", "Magazines that focus on science and nature.");
    
    private final String name;
    private final String description;
    
    /**
     * Constructs a new magazine genre with a name and description.
     * @param name the name of the genre
     * @param description the description of the genre
     */
    private MagazineGenre(String name, String description) {
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
    

