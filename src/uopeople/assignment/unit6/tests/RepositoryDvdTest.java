package uopeople.assignment.unit6.tests;

import uopeople.assignment.unit6.models.Dvd;
import uopeople.assignment.unit6.models.LibraryItemBase;
import uopeople.assignment.utils.EnumUtils;
import uopeople.assignment.unit6.enums.DvdGenre;

public class RepositoryDvdTest extends RepositoryItensTest<Dvd> {

    @Override
    protected Dvd createTestItem() {
        
        String title = "Test Title " + LibraryItemBase.getNextItemId();
        String author = "Test Author " + LibraryItemBase.getNextItemId();

        DvdGenre randomGenre = EnumUtils.randomEnum(DvdGenre.class);
        return new Dvd(title, author, randomGenre);
    }

}
