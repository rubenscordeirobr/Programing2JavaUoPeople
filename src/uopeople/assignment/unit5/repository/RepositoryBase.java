package uopeople.assignment.unit5.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import uopeople.assignment.unit5.models.Model;

public abstract class RepositoryBase<T extends Model> {

    public abstract Optional<T> get(int id);

    public abstract List<T> getAll();

    public abstract void save(T entity);

    public abstract void delete(T entity);

    protected Connection createConnection() throws SQLException {

        // Define the connection URL
        String url = "jdbc:postgresql://localhost:5432/library_management";
        String user = "uopeople";
        String password = "UoPeople2024";

        return internalCreateConnection(url, user, password);
    }

    /**
     * Establishes a connection to the PostgreSQL database.
     *
     * @param url      The URL of the database (e.g.,
     *                 "jdbc:postgresql://localhost:5432/mydatabase").
     * @param user     The username for the database.
     * @param password The password for the database.
     * @return A {@code Connection} object for interacting with the database.
     * @throws SQLException If a database access error occurs.
     */
    private Connection internalCreateConnection(String url, String user, String password) throws SQLException {
        
        try {
            // Load the PostgreSQL JDBC driver (optional with modern JDBC, but good for
            // clarity)
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found", e);
        }

        // Return a new connection using the provided credentials
        return DriverManager.getConnection(url, user, password);
    }

}
