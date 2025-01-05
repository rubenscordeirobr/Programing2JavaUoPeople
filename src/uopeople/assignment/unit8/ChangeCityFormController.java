package uopeople.assignment.unit8;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import uopeople.assignment.unit8.utils.AlertUtils;

/**
 * The ChangeCityFormController class manages the "Change City" dialog.
 * It allows the user to input a new city name, validates the input,
 * and returns the result to the main application.
 */
public class ChangeCityFormController {

    @FXML
    private TextField cityNameTextField;

    private boolean isSuccess = false;
    private String cityName;

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Checks if the city change was successful.
     * 
     * @return true if the user successfully entered a new city, false otherwise.
     */
    public boolean isSuccess() {
        return this.isSuccess;
    }

    /**
     * Retrieves the name of the city entered by the user.
     * 
     * @return the city name as a String, or null if the action was canceled.
     */
    public String getCityName() {
        return this.cityName;
    }

    /**
     * Handles the action triggered when the user clicks the "Change City" button.
     * Validates the input and closes the dialog if successful.
     */
    @FXML
    private void handleChangeCity() {

        String newCity = cityNameTextField.getText();
        if (newCity.isBlank()) {
            AlertUtils.showError("City name cannot be empty");
            return;
        }
        this.isSuccess = true;
        this.cityName = this.cityNameTextField.getText();
        this.closeWindow();
    }

    /**
     * Handles the action triggered when the user clicks the "Cancel" button.
     * Closes the dialog without saving any changes.
     */
    @FXML
    private void handleCancel() {
        // close
        this.closeWindow();
    }

    /**
     * Closes the dialog window.
     */
    private void closeWindow() {
        Stage stage = (Stage) cityNameTextField.getScene().getWindow();
        stage.close();
    }

}
