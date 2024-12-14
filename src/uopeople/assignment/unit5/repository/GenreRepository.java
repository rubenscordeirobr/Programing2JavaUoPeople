package uopeople.assignment.unit5.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uopeople.assignment.unit5.models.Genre;

/**
 * Repository class for managing CRUD operations on {@code Genre} entities.
 * 
 * This class provides methods for interacting with the database, including:
 * - Retrieving a genre by its ID.
 * - Retrieving all genres.
 * - Saving a genre (insert or update).
 * - Deleting a genre.
 */
public class GenreRepository extends RepositoryBase<Genre> {

    /**
     * Retrieves a {@code Genre} by its unique identifier.
     *
     * @param id The ID of the genre to retrieve.
     * @return An {@code Optional<Genre>} containing the genre if found, or
     *         {@code Optional.empty()} otherwise.
     */
    @Override
    public Optional<Genre> get(int id) {
        String sql = "SELECT genre_id, genre_name FROM genres WHERE genre_id = ?";
        try (Connection conn = this.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the ID parameter for the query
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                // If a genre is found, create and return the Genre object
                if (rs.next()) {
                    Long genreId = rs.getLong("genre_id");
                    String genreName = rs.getString("genre_name");
                    return Optional.of(new Genre(genreId, genreName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for better error handling
        }
        return Optional.empty();
    }

    /**
     * Retrieves all genres from the database.
     *
     * @return A {@code List<Genre>} containing all genres.
     */
    @Override
    public List<Genre> getAll() {
        ArrayList<Genre> genres = new ArrayList<>();
        String sql = "SELECT genre_id, genre_name FROM book_genres";

        try (Connection conn = this.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            // Iterate through the result set and populate the list of genres
            while (rs.next()) {
                Long genreId = rs.getLong("genre_id");
                String genreName = rs.getString("genre_name");
                genres.add(new Genre(genreId, genreName));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for better error handling
        }
        return genres;
    }

    /**
     * Saves a {@code Genre} entity to the database.
     * 
     * If the genre ID is greater than 0, the method updates the genre.
     * Otherwise, it inserts a new genre.
     *
     * @param entity The {@code Genre} entity to save.
     */
    @Override
    public void save(Genre entity) {
       
        if (entity.getGenreId() != null && entity.getGenreId() <= 0) {
            update(entity);
        } else {
            insert(entity);
        }
    }

    /**
     * Deletes a {@code Genre} entity from the database.
     *
     * @param entity The {@code Genre} entity to delete.
     */
    @Override
    public void delete(Genre entity) {
        String sql = "DELETE FROM book_genres WHERE genre_id = ?";
        try (Connection conn = this.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the genre ID parameter
            stmt.setLong(1, entity.getGenreId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for better error handling
        }
    }

    /**
     * Inserts a new {@code Genre} entity into the database.
     *
     * @param entity The {@code Genre} entity to insert.
     */
    private void insert(Genre entity) {
       
        String sql = "INSERT INTO book_genres (genre_name) VALUES (?)";
        try (Connection conn = this.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set the genre name parameter
            stmt.setString(1, entity.getGenreName());
            stmt.executeUpdate();

            // Retrieve the generated ID and set it in the entity
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setGenreId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for better error handling
        }
    }

    /**
     * Updates an existing {@code Genre} entity in the database.
     *
     * @param entity The {@code Genre} entity to update.
     */
    private void update(Genre entity) {
        String sql = "UPDATE book_genres SET genre_name = ? WHERE genre_id = ?";
        try (Connection conn = this.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the parameters for the update query
            stmt.setString(1, entity.getGenreName());
            stmt.setLong(2, entity.getGenreId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for better error handling
        }
    }
}
