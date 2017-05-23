
package Labolatorium.addbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Labolatorium.database.DatabaseHandler;


public class LibraryAssistant extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("add_book.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.getIcons().add(new Image("Labolatorium/addbook/icon.png"));
        stage.show();
        new Thread(() -> {
            DatabaseHandler.getInstance();
        }).start();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
