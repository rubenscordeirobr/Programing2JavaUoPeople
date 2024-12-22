package uopeople.assignment.unit6.models;

import uopeople.assignment.unit6.enums.CatalogType;

/**
 * Represents a library item with a title, author, and genre.
 */
public abstract class LibraryItemBase {

    private static int nextItemId = 1;
    private int itemId;
    private String title;
    private String author;

    /**
     * Constructs a new library item with a title and author.
     * 
     * @param title  the title of the library item
     * @param author the author of the library item
     */
    protected LibraryItemBase(String title, String author) {
        this.itemId = nextItemId++;
        this.title = title;
        this.author = author;
    }

    /**
     * Returns the ID of the library item.
     * 
     * @return the ID of the library item
     */
    public int getId() {
        return this.itemId;
    }

    /**
     * Returns the title of the library item.
     * 
     * @return the title of the library item
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the author of the library item.
     * 
     * @return the author of the library item
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Returns the genre of the library item.
     * 
     * @return the genre of the library item
     */
    public abstract String getGenreName();

    /**
     * Returns the description of the genre of the library item.
     * 
     * @return the description of the genre of the library item
     */
    public abstract String getGenreDescription();

    /**
     * Returns the type of the library item.
     * 
     * @return the type of the library item
     */
    public abstract CatalogType getCatalogType();

    /**
     * Returns a string representation of the library item.
     * 
     * @return a string representation of the library item
     */
    @Override
    public String toString() {

        return "{" +
                " title='" + getTitle() + "',\n" +
                " author='" + getAuthor() + "',\n" +
                " genre='" + getGenreName() + "',\n" +
                "}";
    }

    /**
     * Returns the next available item ID.
     * 
     * @return the next available item ID
     */
    public static int getNextItemId() {
        return nextItemId;
    }

}