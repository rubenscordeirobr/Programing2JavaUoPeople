package uopeople.assignment.unit5.tests.repository;

import uopeople.assignment.unit5.models.Genre;
import uopeople.assignment.unit5.repository.GenreRepository;
import uopeople.assignment.utils.AssertUtil;

public class GenreRepositoryTest {

    private final GenreRepository genreRepository = new GenreRepository();
    
    public GenreRepositoryTest()
    {

    }

    public void TestGetAll() {

        TestAdd();

    }

    private void TestAdd() {

        String genreName = "Test Genre " + System.currentTimeMillis();
        Genre genre = new Genre(genreName);
        genreRepository.save(genre);
        AssertUtil.greaterThan(genre.getGenreId(), 0L, "Genre ID should be greater than 0");
    }

}
