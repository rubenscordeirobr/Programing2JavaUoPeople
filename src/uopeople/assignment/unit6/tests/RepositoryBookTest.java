package uopeople.assignment.unit6.tests;

import uopeople.assignment.unit6.models.Book;
import uopeople.assignment.unit6.models.LibraryItemBase;
import uopeople.assignment.unit6.enums.BookGenre;
import uopeople.assignment.utils.EnumUtils;

public class RepositoryBookTest extends RepositoryItensTest<Book> {

    @Override
    protected Book createTestItem() {
        
        String title = "Test Title " + LibraryItemBase.getNextItemId();
        String author = "Test Author " + LibraryItemBase.getNextItemId();

        BookGenre randomGenre =EnumUtils.randomEnum(BookGenre.class);
        return new Book(title,author, randomGenre);
    }
    
}

