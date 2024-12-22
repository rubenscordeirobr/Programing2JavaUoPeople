package uopeople.assignment.unit6.data;

import uopeople.assignment.unit6.repositories.RepositoryItems;
import uopeople.assignment.unit6.models.*;
import uopeople.assignment.unit6.enums.*;

public class DataSeeder {

    public static void seedData() {
        // seed data

        // seed books
        seedBooks();
        seedDvds();
        seedMagazines();
    }

    private static void seedBooks() {
        RepositoryItems<Book> repository = new RepositoryItems<>(Book.class);
    
        // Fiction
        repository.addItem(new Book("The Great Gatsby", "F. Scott Fitzgerald", BookGenre.FICTION));
        repository.addItem(new Book("The Great Gatsby", "John Doe", BookGenre.FICTION)); 
        repository.addItem(new Book("To Kill a Mockingbird", "Harper Lee", BookGenre.FICTION));
        repository.addItem(new Book("To Kill a Mockingbird", "Jane Smith", BookGenre.FICTION)); 
    
        // Non-Fiction
        repository.addItem(new Book("The Diary of Charles Darwin", "Charles Darwin", BookGenre.NON_FICTION));
        repository.addItem(new Book("The Diary of Charles Darwin", "David Attenborough", BookGenre.NON_FICTION)); 
    
        // Science Fiction
        repository.addItem(new Book("The War of the Worlds", "H.G. Wells", BookGenre.SCIENCE_FICTION));
        repository.addItem(new Book("The War of the Worlds", "Isaac Asimov", BookGenre.SCIENCE_FICTION)); 
        repository.addItem(new Book("The Time Machine", "H.G. Wells", BookGenre.SCIENCE_FICTION));
        repository.addItem(new Book("The Time Machine", "Arthur C. Clarke", BookGenre.SCIENCE_FICTION)); 
    
        // Mystery
        repository.addItem(new Book("The Hound of the Baskervilles", "Arthur Conan Doyle", BookGenre.MYSTERY));
        repository.addItem(new Book("The Hound of the Baskervilles", "Agatha Christie", BookGenre.MYSTERY)); 
    
        // History
        repository.addItem(new Book("The History of Roman Empire", "Edward Gibbon", BookGenre.HISTORY));
        repository.addItem(new Book("The History of Roman Empire", "Peter Heather", BookGenre.HISTORY)); 
        repository.addItem(new Book("The History of the Peloponnesian War", "Thucydides", BookGenre.HISTORY));
        repository.addItem(new Book("The History of the Peloponnesian War", "Donald Kagan", BookGenre.HISTORY)); 

        repository.addItem(new Book("The History of the Decline and Fall of the Roman Empire", "Edward Gibbon", BookGenre.HISTORY));

    }
    
    
    private static void seedDvds() {

        RepositoryItems<Dvd> repository = new RepositoryItems<>(Dvd.class);
    
        // Add DVDs for Action genre
        repository.addItem(new Dvd("The Godfather", "Francis Ford Coppola", DvdGenre.ACTION));
        repository.addItem(new Dvd("The Godfather", "Mario Puzo", DvdGenre.ACTION)); 
        repository.addItem(new Dvd("Mad Max: Fury Road", "George Miller", DvdGenre.ACTION));
        repository.addItem(new Dvd("Mad Max: Fury Road", "Brendan McCarthy", DvdGenre.ACTION)); 
    
        // Add DVDs for Comedy genre
        repository.addItem(new Dvd("Superbad", "Greg Mottola", DvdGenre.COMEDY));
        repository.addItem(new Dvd("Superbad", "Seth Rogen", DvdGenre.COMEDY)); 
        repository.addItem(new Dvd("The Big Lebowski", "Joel Coen", DvdGenre.COMEDY));
        repository.addItem(new Dvd("The Big Lebowski", "Ethan Coen", DvdGenre.COMEDY)); 
    
        // Add DVDs for Drama genre
        repository.addItem(new Dvd("Schindler's List", "Steven Spielberg", DvdGenre.DRAMA));
        repository.addItem(new Dvd("Schindler's List", "Thomas Keneally", DvdGenre.DRAMA)); 
        repository.addItem(new Dvd("The Shawshank Redemption", "Frank Darabont", DvdGenre.DRAMA));
        repository.addItem(new Dvd("The Shawshank Redemption", "Stephen King", DvdGenre.DRAMA)); 
    
        // Add DVDs for Horror genre
        repository.addItem(new Dvd("The Exorcist", "William Friedkin", DvdGenre.HORROR));
        repository.addItem(new Dvd("The Exorcist", "William Peter Blatty", DvdGenre.HORROR)); 
        repository.addItem(new Dvd("Get Out", "Jordan Peele", DvdGenre.HORROR));
        repository.addItem(new Dvd("Get Out", "Sean McKittrick", DvdGenre.HORROR)); 
    
        // Add DVDs for Science Fiction genre
        repository.addItem(new Dvd("Inception", "Christopher Nolan", DvdGenre.SCIENCE_FICTION));
        repository.addItem(new Dvd("Inception", "Emma Thomas", DvdGenre.SCIENCE_FICTION)); 
        repository.addItem(new Dvd("Interstellar", "Christopher Nolan", DvdGenre.SCIENCE_FICTION));
        repository.addItem(new Dvd("Interstellar", "Jonathan Nolan", DvdGenre.SCIENCE_FICTION)); 
    }
     
    private static void seedMagazines() {
        RepositoryItems<Magazine> repository = new RepositoryItems<>(Magazine.class);
    
        // Art genre
        repository.addItem(new Magazine("Art Monthly", "Art Publisher", MagazineGenre.ART));
        repository.addItem(new Magazine("Modern Art Review", "Modern Arts Inc.", MagazineGenre.ART));
        repository.addItem(new Magazine("Design Today", "Creative Media", MagazineGenre.ART));
        repository.addItem(new Magazine("Sculpture World", "Art Pro", MagazineGenre.ART)); 
    
        // Business genre
        repository.addItem(new Magazine("Forbes", "Forbes Media", MagazineGenre.BUSINESS));
        repository.addItem(new Magazine("Harvard Business Review", "HBR Group", MagazineGenre.BUSINESS));
        repository.addItem(new Magazine("The Economist", "Economist Group", MagazineGenre.BUSINESS));
        repository.addItem(new Magazine("Startup Insights", "Startup Media", MagazineGenre.BUSINESS));  
    
        // Computers genre
        repository.addItem(new Magazine("PC Magazine", "Ziff Davis", MagazineGenre.COMPUTERS));
        repository.addItem(new Magazine("TechRadar", "Future Publishing", MagazineGenre.COMPUTERS));
        repository.addItem(new Magazine("Wired", "Condé Nast", MagazineGenre.COMPUTERS));
        repository.addItem(new Magazine("AI Digest", "Tech Innovations", MagazineGenre.COMPUTERS)); 
    
        // Science genre
        repository.addItem(new Magazine("Scientific American", "Springer Nature", MagazineGenre.SCIENCE));
        repository.addItem(new Magazine("National Geographic", "National Geographic Society", MagazineGenre.SCIENCE));
        repository.addItem(new Magazine("New Scientist", "New Scientist Ltd.", MagazineGenre.SCIENCE));
        repository.addItem(new Magazine("Space Explorers", "Cosmos Media", MagazineGenre.SCIENCE));  

        
    }
}
