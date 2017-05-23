
package login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Labolatorium.database.DatabaseHandler;
import Labolatorium.main.MainController;

public class LoginController implements Initializable {
   private static  DatabaseHandler handler ;
   private static final String DB_URL = "jdbc:derby:database;create=true";
	   PreparedStatement pst = null;
	   ResultSet rs = null;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField login;
    private JFXTextField user;
    DatabaseHandler databaseHandler;   
    private JFXPasswordField haslo;
    @FXML
    private JFXButton signup;
    @FXML
    private JFXButton zaloguj;
    @FXML
    private Text text;
         
    @FXML
    void makeLogin(ActionEvent event)throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    String qu = "select * from REGISTER where login = ? and password = ?";
    	try 
            (Connection conn = DriverManager.getConnection(DB_URL);
    			PreparedStatement prpstmt = conn.prepareStatement(qu); )
    			{
    			prpstmt.setString(1, login.getText());
    			prpstmt.setString(2, password.getText()); 
    			rs = prpstmt.executeQuery();
    			if(rs.next()){
    				loadWindows("/Labolatorium/main/main.fxml", "Labolatorium");
    			}else {
    				 text.setText("Nieprawidłowa ime lub hasło!!!");
    			}
                        login.clear();
                  password.clear(); 
    			pst.close();
    			rs.close();
                        
    	}catch(Exception e){
    		System.err.println(e);
    	}
   }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void makeSingUp(ActionEvent event) {
        loadWindows("/registerForm/Register.fxml", " Zarejestrować Się");
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
