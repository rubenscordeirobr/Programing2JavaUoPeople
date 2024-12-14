package uopeople.assignment.unit5.tests;

import uopeople.assignment.unit5.tests.repository.GenreRepositoryTest;

public class MainTest {

    public static void main(String[] args) {

        TestRepositories();

    }

    private static void TestRepositories() {
        
        GenreRepositoryTest genreRepositoryTest = new GenreRepositoryTest();
        genreRepositoryTest.TestGetAll();
        
    }
}
