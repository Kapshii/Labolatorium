
package Setting;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SettingController implements Initializable {

    @FXML
    private StackPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void memberList(ActionEvent event) {
      loadWindows("/Setting/listmember/member_list.fxml", "  Lista członków ");
    }

    @FXML
    private void registerList(ActionEvent event) {
       loadWindows("/registerList/Register.fxml", " Register listę ");
    }

    @FXML
    private void bookList(ActionEvent event) {
        loadWindows("/Setting/listbook/book_list.fxml", " Lista książek ");
    }

    @FXML
    private void Exit(ActionEvent event) {
         Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
        
    }
    void loadWindows(String loc, String title)
    {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.getIcons().add(new Image("Setting/icon.png"));
            stage.setScene(new Scene(parent)); 
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
