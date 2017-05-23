package registerList;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import static javafx.application.Application.launch;
import javafx.scene.image.Image;

import Labolatorium.database.DatabaseHandler;

public class RegisterList extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene  scene = new Scene(root);
        stage.getIcons().add(new Image("registerList/icon.png"));
        stage.setScene(scene);
        stage.show();
        new Thread(
            () -> {
                DatabaseHandler.getInstance();
            }).start();
    }
}
 
