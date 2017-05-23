
package login1;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Labolatorium.database.DatabaseHandler;
import Labolatorium.main.MainController;


public class FXMLDocumentController implements Initializable {
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    private JFXTextField user;

    @FXML
    private JFXButton signup;
         DatabaseHandler databaseHandler;   
         
    @FXML
    void makeLogin(ActionEvent event) {
       
         String username = user.getText();
         String pass = password.getText();
         if   (username.equals("admin")&&pass.equals("admin"))
             
         {
             try {
                
                 System.out.println("Witamy");
                 Parent parent = FXMLLoader.load(getClass().getResource("/Setting/Setting.fxml"));
                 Stage stage = new Stage();
                 Scene scene = new Scene (parent);
                 stage.getIcons().add(new Image("Setting/icon.png"));
                 stage.setScene(scene);
                 stage.setTitle("Ustawienia");
                 stage.show();
                  user.clear();
                  password.clear();
       (((Node) event.getSource()).getScene()).getWindow().hide();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }else
             System.out.println("WRONG Pass");
    }
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    void loadWindows(String loc, String title)
    {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
    
        }
        
    }
    
}
