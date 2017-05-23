package Labolatorium.main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import Labolatorium.database.DatabaseHandler;

public class Main extends Application {
     @Override
    public void start(Stage stage) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("main.fxml")); 
        Scene  scene = new Scene(root);  
        stage.setScene(scene);
        stage.getIcons().add(new Image("Labolatorium/main/icon.png"));
        stage.show(); 
        new Thread(() -> {
            DatabaseHandler.getInstance();
        }).start();
    }
    public static void main(String[] args) {
        launch(args);
    }

   
    
}


