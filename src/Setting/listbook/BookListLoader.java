package Setting.listbook;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;

/**
 *
 * @author Андрій
 */
public class BookListLoader extends Application {
    
    @Override
    public void start(Stage stage)throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("book_list.fxml"));
       
        Scene scene= new Scene(root);
        stage.getIcons().add(new Image("Setting/listbook/icon.png"));
        stage.setScene(scene);
        stage.show();
       
    }
    public static void main (String[]args){
        launch(args);
    }
}
