-- DataBase StudentManagementSystem
-- Table Book

 -- All tables will be created in the public schema by default.

-- Create the genre table
CREATE TABLE book_genres (
    genre_id SERIAL PRIMARY KEY, 
    genre_name VARCHAR(50) UNIQUE NOT NULL
);

-- Create the authors table
CREATE TABLE authors (
    author_id SERIAL PRIMARY KEY,
    author_name VARCHAR(255) NOT NULL
);

-- Create the books table
CREATE TABLE books (
    isbn VARCHAR(13) PRIMARY KEY,
    genre_id INT REFERENCES book_genres(genre_id) NOT NULL,
    title VARCHAR(255) NOT NULL,
    quantity INT NOT NULL CHECK (quantity >= 0) -- Inline check constraint for clarity
);

-- Create the book_authors table (many-to-many relationship)
CREATE TABLE book_authors (
    isbn VARCHAR(13) REFERENCES books(isbn) ON DELETE CASCADE NOT NULL,
    author_id INT REFERENCES authors(author_id) ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (isbn, author_id)
);

-- Create the book_editions table
-- Using a composite primary key of (isbn, edition)
-- Also ensuring unique edition names per ISBN.
CREATE TABLE book_editions (
    isbn VARCHAR(13) REFERENCES books(isbn) ON DELETE CASCADE NOT NULL,
    edition INT NOT NULL,
    edition_name VARCHAR(50) NOT NULL,
    publication_year INT NOT NULL,
    PRIMARY KEY (isbn, edition),
    CONSTRAINT uq_book_editions_edition_name_per_isbn UNIQUE (isbn, edition_name)
);

-- Create the members table
CREATE TABLE members (
    member_id SERIAL PRIMARY KEY,
    member_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(15),
    member_password VARCHAR(255) NOT NULL
);

-- Create the loans table
-- Include a check constraint to ensure return_date is either NULL or after loan_date
CREATE TABLE loans (
    loan_id SERIAL PRIMARY KEY,
    member_id INT REFERENCES members(member_id) NOT NULL,
    isbn VARCHAR(13) REFERENCES books(isbn) ON DELETE CASCADE NOT NULL,
    loan_date DATE NOT NULL,
    return_date DATE,
    CHECK (return_date IS NULL OR return_date >= loan_date)
);

-- Indexes for better performance
-- Index on genre_id in books
CREATE INDEX idx_books_genre_id ON books(genre_id);

-- Index on author_id in book_authors to help with lookups by author
CREATE INDEX idx_book_authors_author_id ON book_authors(author_id);

-- Indexes for loans
CREATE INDEX idx_loans_member_id ON loans(member_id);
CREATE INDEX idx_loans_isbn ON loans(isbn);

-- Index on books title for faster search by title
CREATE INDEX idx_books_title ON books(title);

-- Index on genre_name (optional, unique constraint already creates an index)
CREATE INDEX idx_books_genre_name ON book_genres(genre_name);
