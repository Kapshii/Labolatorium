package login1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import Labolatorium.database.DatabaseHandler;

public class Login extends Application {
     @Override
    public void start(Stage stage) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("LOGIN1.fxml"));
        Scene  scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Wejście w Labolatorium");
        stage.getIcons().add(new Image("login1/icon.png"));
        new Thread(() -> {
            DatabaseHandler.getInstance();
        }).start();
    }
    public static void main(String[] args) {
        launch(args);
    }

  
}