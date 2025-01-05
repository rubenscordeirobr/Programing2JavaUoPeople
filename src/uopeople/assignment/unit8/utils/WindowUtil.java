package uopeople.assignment.unit8.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;

import javafx.stage.Stage;

/**
 * The WindowUtil class provides utility methods for managing and opening new
 * JavaFX windows.
 * It simplifies the process of loading FXML layouts, creating stages, and
 * configuring window properties.
 */
public class WindowUtil {

    /**
     * Opens a new JavaFX window based on the provided FXML file and configuration.
     * 
     * @param fxmlFile    the path to the FXML file that defines the window's
     *                    layout.
     * @param windowTitle the title of the window.
     * @param isModal     true to make the window modal (blocks interaction with the
     *                    parent window),
     *                    false for a non-modal window.
     */
    protected void openWindow(String fxmlFile, String windowTitle, boolean isModal) {
        try {
            // Load the FXML layout for the new window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(new Scene(root));

            // Set the window modality if it's meant to be modal
            if (isModal) {
                stage.initModality(Modality.APPLICATION_MODAL); // Set the modality to block parent window
                stage.showAndWait(); // Wait for the window to close before returning
            } else {
                stage.show(); // Show non-modal window
            }

        } catch (Exception e) {
            // Handle potential exceptions by printing the stack trace
            e.printStackTrace();
        }
    }

}
