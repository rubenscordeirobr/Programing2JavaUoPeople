package uopeople.assignment.unit6.tests;

  
public class Test {

    public static void main(String[] args) {

        // tests
        testRepositories();

    }

    private static void testRepositories() {
       

        // test repository book
        RepositoryBookTest repositoryBookTest = new RepositoryBookTest();
        repositoryBookTest.run();
        
        // test repository dvd
        RepositoryDvdTest repositoryDvdTest = new RepositoryDvdTest();
        repositoryDvdTest.run();
 
        // test repository magazine
        RepositoryMagazineTest repositoryMagazineTest = new RepositoryMagazineTest();
        repositoryMagazineTest.run();
    
    }
}
