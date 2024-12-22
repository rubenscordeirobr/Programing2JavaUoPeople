package uopeople.assignment.unit6.tests;

import uopeople.assignment.unit6.models.LibraryItemBase;
import uopeople.assignment.unit6.models.Magazine;
import uopeople.assignment.utils.EnumUtils;
import uopeople.assignment.unit6.enums.MagazineGenre;

public class RepositoryMagazineTest extends RepositoryItensTest<Magazine> {

    @Override
    protected Magazine createTestItem() {
        String title = "Test Title " + LibraryItemBase.getNextItemId();
        String author = "Test Author " + LibraryItemBase.getNextItemId();

        MagazineGenre randomGenre = EnumUtils.randomEnum(MagazineGenre.class);
        return new Magazine(title, author, randomGenre);
    }

}