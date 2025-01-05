package uopeople.assignment.unit8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The WeatherApp class serves as the entry point for the Weather Information
 * Application.
 * It initializes the JavaFX application and loads the main window defined in
 * the FXML file.
 * 
 * Responsibilities:
 * - Launch the JavaFX application.
 * - Load and display the main window of the application.
 */
public class WeatherApp extends Application {

    /**
     * The main method serves as the entry point for the application.
     * 
     * @param args command-line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }

    /**
     * Starts the JavaFX application by setting up the primary stage.
     * 
     * @param primaryStage the main stage for the application, provided by the
     *                     JavaFX runtime.
     * @throws Exception if the FXML file cannot be loaded or other initialization
     *                   errors occur.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));

        // Set the title of the application window
        primaryStage.setTitle("UoPeople Weather App");

        // Set the scene dimensions and display the main window
        primaryStage.setScene(new Scene(root, 800, 600));

        // Display the primary stage
        primaryStage.show();
    }
}
