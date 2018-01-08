package dream.development;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Boot class for application
 */
public class Main extends Application {

    /**
     * Method create primaryStage
     *
     * @param primaryStage Main stage
     * @throws IOException Resource not found
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent panel = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));

        Scene scene = new Scene(panel, 375, 300);

        primaryStage.setTitle("Weather Forecast");
        primaryStage.setMinHeight(350);
        primaryStage.setMinWidth(400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Boot method
     *
     * @param args Input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
