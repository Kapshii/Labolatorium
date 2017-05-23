package Labolatorium.addmember;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Labolatorium.database.DatabaseHandler;


public class MemberAddController implements Initializable {

    DatabaseHandler handler;
    
    private JFXTextField name;
    private JFXTextField id;
    private JFXTextField mobile;
    private JFXTextField email;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private AnchorPane rootPane1;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       handler =  DatabaseHandler.getInstance();
    }    


    @FXML
    private void cancel(ActionEvent event) {
       Stage stage = (Stage) rootPane1.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void addMember(ActionEvent event) {
        
        String mName = name.getText();
        String mID = id.getText();
        String mMobile = mobile.getText();
        String mEmail = email.getText();
        
        Boolean flag = mName.isEmpty()||mID.isEmpty()||mMobile.isEmpty()||mEmail.isEmpty();
               
            if(flag){ 
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Wprowadź we wszystkich polach");
            alert.showAndWait();
//            (((Node) event.getSource()).getScene()).getWindow().hide();
                  return;
        }
        
        String st= "INSERT INTO MEMBER1 VALUES (" 
                + "'" + mName  +"',"
                + "'" + mID +"',"
                + "'" + mMobile +"',"
                + "'" + mEmail +"'"
                + ")"; 
                System.out.println("Save");
                 if(handler.execAction(st))
                 {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Zapisano");
                    alert.showAndWait();
                     
                 }
                 else
                 {
                     Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("Wystąpił błąd");
            alert1.showAndWait();
    }
    }
}
