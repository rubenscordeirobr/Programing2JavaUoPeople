package uopeople.assignment.unit6.models;

import uopeople.assignment.unit6.enums.*;

/**
 * Represents a magazine in the library catalog.
 * 
 * This class applies generics by specifying the type parameter `TGenre` 
 * from the `LibraryItem` superclass as `MagazineGenre`. 
 * 
 * Utilizing generics ensures type safety and flexibility in working with genres 
 * specific to magazines while leveraging the shared structure and functionality 
 * defined in the `LibraryItem` class.
 */
public class Magazine extends LibraryItem<MagazineGenre> {
    
    /**
     * Constructs a magazine with a title, author, and genre.
     * 
     * @param title  The title of the magazine.
     * @param author The author or publisher of the magazine.
     * @param genre  The genre of the magazine, specified as `MagazineGenre`.
     */
    public Magazine(String title, String author, MagazineGenre genre) {
        super(title, author, genre);
    }
 
    /**
     * Gets the catalog type for the magazine.
     * 
     * Overrides the method in the `LibraryItem` superclass to define the 
     * catalog type specific to magazines as `CatalogType.MAGAZINE`.
     * 
     * @return The catalog type for magazines.
     */
    @Override
    public CatalogType getCatalogType() {
        return CatalogType.MAGAZINE;
    }
}
