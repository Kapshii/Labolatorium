package login;
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
//        Thread.sleep();
        Parent root  = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene  scene = new Scene(root);
        stage.getIcons().add(new Image("login/icon.png"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Wejście w Labolatorium");
        new Thread(() -> {
            DatabaseHandler.getInstance();
        }).start();
        
    }
    public static void main(String[] args) {
        launch(args);
    }

   
    
}