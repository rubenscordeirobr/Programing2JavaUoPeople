package uopeople.assignment.unit8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

 
public class WeatherApp extends Application {

    
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
  
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        

        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        
        // Set the title of the application window
        primaryStage.setTitle("Student Management System");
        
        // Set the scene dimensions and display the main window
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


}
