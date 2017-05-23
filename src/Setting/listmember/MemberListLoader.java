 
package Setting.listmember;
  
 import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import Labolatorium.database.DatabaseHandler;


public class MemberListLoader extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("member_list.fxml"));
        Scene  scene = new Scene(root);
        stage.getIcons().add(new Image("Setting/listmember/icon.png"));
        stage.setScene(scene);
        stage.show();
        new Thread(
            () -> {
                DatabaseHandler.getInstance();
            }).start();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
   
   
