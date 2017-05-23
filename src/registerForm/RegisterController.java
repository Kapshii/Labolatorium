
package registerForm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Labolatorium.database.DatabaseHandler;

public class RegisterController implements Initializable {
     DatabaseHandler handler; 
    
    @FXML
    private JFXButton regSave;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton regCancel;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXCheckBox checkPas;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       handler =  DatabaseHandler.getInstance(); 
    }    
//    private boolean validatePass(){
//Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
//Matcher m =p.matcher(password.getText());
//if(m.find() && m.group().equals(password.getText())){
//return true;
//} 
//else {
//Alert alert1 = new Alert(Alert.AlertType.ERROR);
//                    alert1.setHeaderText(null);
//                    alert1.setContentText("Popraw Hasło Min 6 Max 15");
//                    alert1.showAndWait();
//                   return false;
//        }
//}
//    private boolean validateName(){
//Pattern p = Pattern.compile("[a-zA-Z]+");
//Matcher m =p.matcher(login.getText());
//if(m.find() && m.group().equals(login.getText())){
//return true;
//} 
//else {
//Alert alert1 = new Alert(Alert.AlertType.ERROR);
//                    alert1.setHeaderText(null);
//                    alert1.setContentText("Popraw Ime");
//                    alert1.showAndWait();
//                   return false;
//        }
//}
//private boolean validateEmail(){
//Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
//Matcher m =p.matcher(email.getText());
//if(m.find() && m.group().equals(email.getText())){
//return true;
//} 
//else {
//Alert alert2 = new Alert(Alert.AlertType.ERROR);
//                    alert2.setHeaderText(null);
//                    alert2.setContentText("Popraw Email");
//                    alert2.showAndWait();
//                   return false;
//        }
//}

    @FXML
    private void RegisterSave(ActionEvent event) {
//        if( validateEmail() & validateName() & validatePass()){ 
        String mLogin = login.getText();
        String mPassword = password.getText();
        String mEmail = email.getText();
        
        Boolean flag = mLogin.isEmpty()|| mPassword.isEmpty()|| mEmail.isEmpty();
           
        if(flag){ 
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Wprowadź we wszystkich polach");
            alert.showAndWait();
                  return;
        }
        
        String st= "INSERT INTO REGISTER VALUES  (" 
                + "'" + mLogin  +"',"
                + "'" + mPassword +"',"
                + "'" + mEmail +"'"
                + ")"; 
                System.out.println("st");
                 if(handler.execAction(st))
                 {
                    Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
                    alert5.setHeaderText(null);
                    alert5.setContentText("Zapisano");
                    alert5.showAndWait();
                     
                 }
                 else
                 {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Błąd Zapisano");
            alert.showAndWait();
    }
//        }
    }

    @FXML
    private void Cancel(ActionEvent event) {
        
         Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void checkPassword(ActionEvent event) {
//      if (checkPas.isSelected()){ 
//         password.setEchoChar((char)0);
//      }
//      else{ 
//         password.setEchoChar("*");
//          
//      }
      }
    
      
    
    
}
    
