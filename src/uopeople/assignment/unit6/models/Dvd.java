package uopeople.assignment.unit6.models;

import uopeople.assignment.unit6.enums.*;

/**
 * Represents a DVD in the library catalog.
 * 
 * This class demonstrates the application of generics by specifying the 
 * type parameter `TGenre` from the `LibraryItem` superclass as `DvdGenre`.
 * 
 * By utilizing generics, this class ensures type safety and flexibility in working with
 * genres specific to DVDs while leveraging the behavior defined in the abstract 
 * `LibraryItem` class.
 */
public class Dvd extends LibraryItem<DvdGenre> {

    /**
     * Constructs a DVD with a title, author, and genre.
     * 
     * @param title    The title of the DVD.
     * @param author   The author or creator of the DVD.
     * @param dvdGenre The genre of the DVD, specified as `DvdGenre`.
     */
    public Dvd(String title, String author, DvdGenre dvdGenre) {
        super(title, author, dvdGenre);
    }

    /**
     * Gets the catalog type for the DVD.
     * 
     * Overrides the method in the `LibraryItem` superclass to define the 
     * catalog type specific to DVDs as `CatalogType.DVD`.
     * 
     * @return The catalog type for DVDs.
     */
    @Override
    public CatalogType getCatalogType() {
        return CatalogType.DVD;
    }
}
