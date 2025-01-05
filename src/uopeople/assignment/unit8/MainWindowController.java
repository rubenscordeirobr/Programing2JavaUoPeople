package uopeople.assignment.unit8;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import uopeople.assignment.unit8.models.TemperatureUnit;
import uopeople.assignment.unit8.models.weatherstack.*;
import uopeople.assignment.unit8.services.*;
import uopeople.assignment.unit8.utils.*;

/**
 * The MainWindowController class manages the main interface of the Weather
 * Application.
 * It handles user interactions, updates the weather data display, and switches
 * between day and night modes based on the current time.
 */
public class MainWindowController {

    @FXML
    private VBox mainContainer; // Main container for the application's layout

    @FXML
    private RadioButton radioCelsius; // Radio button for selecting Celsius temperature unit

    @FXML
    private RadioButton radioFahrenheit; // Radio button for selecting Fahrenheit temperature unit

    @FXML
    private ToggleGroup temperatureGroup; // Toggle group for temperature unit selection

    @FXML
    private Text labelWeatherDescription; // Text label for displaying weather description

    @FXML
    private Text labelCity; // Text label for displaying the city name

    @FXML
    private Text labelTemperature; // Text label for displaying the current temperature

    @FXML
    private Text labelWind; // Text label for displaying wind speed and direction

    @FXML
    private Text labelHumidity; // Text label for displaying humidity percentage

    @FXML
    private Text labelPressure; // Text label for displaying atmospheric pressure

    @FXML
    private Text labelVisibility; // Text label for displaying visibility distance

    @FXML
    private ImageView weatherIcon; // Image view for displaying the weather icon

    @FXML
    private ListView<ForecastItem> forecastListView; // List view for displaying weather forecast items

    private String currentCity = "Guarapuava"; // Default city name
    private TemperatureUnit selectedUnit = TemperatureUnit.CELSIUS; // Default temperature unit
    private final WeatherService weatherService = new WeatherService(); // Service for fetching weather data

    private Current current; // Current weather data
    private Forecast forecast; // Forecast weather data

    private ObservableList<ForecastItem> forecastItems = FXCollections.observableArrayList(); // List of forecast items

    /**
     * Constructor for MainWindowController.
     */
    public MainWindowController() {
    }

    /**
     * Initializes the controller, setting up event listeners and loading initial
     * data.
     */
    @FXML
    public void initialize() {

        // Add a listener for temperature unit changes
        temperatureGroup.selectedToggleProperty()
                .addListener((observable, oldValue, newValue) -> onTemperatureUnitChanged(newValue));
        // Set initial background
        setBackgroundInitial();

        // Configure the custom cell factory for the forecast list view
        forecastListView.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(ForecastItem item, boolean empty) {

                super.updateItem(item, empty);

                setStyle("-fx-background-color: transparent;");

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {

                    // Render each forecast item
                    String labelStyle = "-fx-text-fill: white; -fx-font-weight: bold;";

                    // Create a VBox to hold all elements
                    VBox vBox = new VBox(5);
                    vBox.setPadding(new Insets(5));
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

                    Label labelTime = new Label(item.getFormattedTime());
                    labelTime.setStyle(labelStyle);
                    labelTime.setAlignment(Pos.CENTER);
                    labelTime.setMaxWidth(Double.MAX_VALUE);

                    ImageView imageView = new ImageView(new Image(item.getWeatherIcon()));
                    imageView.setFitWidth(50); // Adjust size as needed
                    imageView.setFitHeight(50);
                    imageView.setStyle("-fx-background-color: transparent;");

                    Label labelDescription = new Label(item.getWeatherDescription());
                    labelDescription.setStyle(labelStyle);
                    labelDescription.setAlignment(Pos.CENTER);
                    labelDescription.setMaxWidth(Double.MAX_VALUE);

                    Label labelTemperature = new Label(item.getTemperature());
                    labelTemperature.setStyle(labelStyle);
                    labelTemperature.setAlignment(Pos.CENTER);
                    labelTemperature.setMaxWidth(Double.MAX_VALUE);

                    VBox hBox = new VBox(5, labelTime, imageView, labelDescription, labelTemperature);
                    hBox.setPadding(new Insets(5));
                    hBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
                    hBox.setPrefWidth(80);
                    setGraphic(hBox);
                }
            }
        });

        // Load initial weather data
        updateWeatherData();
    }

    /**
     * Handles changes in the selected temperature unit (Celsius/Fahrenheit).
     */
    private void onTemperatureUnitChanged(Toggle selectedToggle) {

        if (selectedToggle != null) {

            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String text = selectedRadioButton.getText();

            if (text.equals(TemperatureUnit.CELSIUS.getSymbol())) {
                selectedUnit = TemperatureUnit.CELSIUS;
            } else if (text.equals(TemperatureUnit.FAHRENHEIT.getSymbol())) {
                selectedUnit = TemperatureUnit.FAHRENHEIT;
            }

            updateCurrentWeatherUI();
            updateForecastUI();
        }
    }

    /**
     * Updates the weather data for the current city.
     */
    public void updateWeatherData() {

        Current current = this.weatherService.getWeatherCurrent(this.currentCity);
        Forecast forecast = this.weatherService.getWeatherForecast(this.currentCity);

        this.current = current;
        this.forecast = forecast;
        updateCurrentWeatherUI();
        updateForecastUI();

    }

    /**
     * Updates the UI with the current weather data.
     */
    public void updateCurrentWeatherUI() {

        if (this.current == null) {
            return;
        }

        String weatherDescription = current.getWeather_descriptions().get(0);
        String iconPath = WeatherIconManager.getIconPath(weatherDescription);

        this.labelCity.setText(this.currentCity);
        this.labelWeatherDescription.setText(weatherDescription);
        this.labelTemperature
                .setText(TemperatureUtil.formatTemperature(this.selectedUnit, this.current.getTemperature()));
        this.labelWind
                .setText(String.format("Wind: %d km/h %s", this.current.getWind_speed(), this.current.getWind_dir()));
        this.labelHumidity.setText(String.format("Humidity: %d%%", this.current.getHumidity()));
        this.labelPressure.setText(String.format("Pressure: %d hPa", this.current.getPressure()));
        this.labelVisibility.setText(String.format("Visibility: %d km", this.current.getVisibility()));

        Image image = new Image(iconPath);
        this.weatherIcon.setImage(image);
    }

    /**
     * Updates the UI with the weather forecast data.
     */
    public void updateForecastUI() {

        if (this.forecast == null) {
            return;
        }

        // Clear existing items
        this.forecastItems.clear();
        int currentHour = LocalTime.now().getHour();

        // Flatten all daily forecasts into a single list of HourlyForecast
        List<HourlyForecast> allHourlyForecasts = this.forecast.getDailyForecasts()
                .values()
                .stream()
                // In case getHourly() might be null in some daily forecast
                .filter(daily -> daily.getHourly() != null)
                .flatMap(daily -> daily.getHourly().stream())
                .toList();

        for (HourlyForecast hourlyForecast : allHourlyForecasts) {

            int hour = Integer.parseInt(hourlyForecast.getTime()) / 100;
            if (hour < currentHour) {
                continue;
            }

            currentHour = hour;

            ForecastItem forecastItem = new ForecastItem(hourlyForecast, selectedUnit);
            this.forecastItems.add(forecastItem);

            // Reset currentHour to 0 if we reach 23
            if ((currentHour + 1) == 24) {
                currentHour = 0;

            }

            // Stop if we have 8 items
            if (this.forecastItems.size() >= 8) {

                break;
            }
        }

        // Set the updated list
        this.forecastListView.setItems(this.forecastItems);

    }

    // #endregion

    // #region background

    /**
     * Sets the initial background based on the current time of day.
     */
    private void setBackgroundInitial() {

        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();


        if (hour >= 6 && hour < 18) {
            onDayModeSelected();
        } else {
            onNightModeSelected();
        }
    }

    /**
     * Changes the background to a daytime image.
     */
    private void onDayModeSelected() {
        changeBackground("/uopeople/assignment/unit8/resources/images/background_day.jpg");
    }

    /**
     * Changes the background to a nighttime image.
     */
    private void onNightModeSelected() {
        changeBackground("/uopeople/assignment/unit8/resources/images/background_night.jpg");
    }

    /**
     * Changes the background image of the main container.
     * 
     * @param imagePath the path to the image file
     */
    private void changeBackground(String imagePath) {

        String style = String.format("-fx-background-image: url('%s'); ", imagePath)
                + "-fx-background-size: cover; "
                + "-fx-background-repeat: no-repeat; "
                + "-fx-background-position: center center;";

        mainContainer.setStyle(style);
    }

    /*
     * Handles the event when the user clicks the "Change City" button.
     */
    @FXML
    private void handleChangeCity() throws IOException {

        // Load the ChangeCityForm.fxml layout
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeCityForm.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Change City");
        stage.setScene(new Scene(root));

        ChangeCityFormController controller = fxmlLoader.getController();
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Update the current city if the user has selected a new city
        if (controller.isSuccess()) {

            this.currentCity = controller.getCityName();

            // Update the weather data for the new city
            updateWeatherData();
        }
    }

    // #endregion
}
