package registerList;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Labolatorium.database.DatabaseHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import registerForm.RegisterController;

public class RegisterListController implements Initializable {
  //  TextField login,password,email;   
   
    
   ObservableList<Register> list = FXCollections.observableArrayList();
    
    private TableView<Register> tableView;
    @FXML
    private TableColumn<Register , String> regLogin;
  
    @FXML
    private TableColumn<Register , String> regPassword;
    @FXML
    private TableColumn<Register , String> regEmail;
    @FXML
    private TableView<Register> tableView1;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton deleted; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();  
  //      clearFile();
    }    
     private void initCol() { 
    regLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
    regPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    regEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    
    }    private void loadData() {
        
              DatabaseHandler handler = DatabaseHandler.getInstance();
              String qu = "SELECT * FROM REGISTER";
               
                ResultSet rs = handler.execQuery(qu);
                try {
                    
                    
                    while(rs.next()){
                        String login = rs.getString("login");
                        String password = rs.getString("password");
                        String email = rs.getString("email");
                        list.add( new Register(login, password, email));
                }
                
            }   catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
                tableView1.getItems().setAll(list);
    }
 //   public void clearFile(){
   //     {  String name = null;
     //       try
       //     { 
//                Register user = (Register)tableView1.getSelectionModel().getSelectedItem();
//                String qu = "deleted from REGISTER where Login=?";
    //            String name = user.getName();
//               stmt = conn.createStatement(qu);
//                 stmt.setString(1,user.getLogin());
//                  stmt.executeUpdate();
//                  
//                   stmt.close();
//                   rls.close();
//            }
//            catch (SQLException e)
//            {
//                System.out.println(e);
//            }
//            DatabaseHandler.getInstance();
  //          RegisterListController.showInformationAlertBox("New User '"+name+"'Deletet!");
 //       }
 //   }
 //   public void clearFile(){
 //         login.clear();
 //         password.clear();
  //        email.clear();
//        }
    @FXML
    private void deletedClear(ActionEvent event) { 
         ObservableList<Register> initCol, loadData;
         loadData = tableView1.getItems();
        initCol = tableView1.getSelectionModel().getSelectedItems();
try
    {
        String Driver = "org.apache.derby.jdbc.EmbeddedDriver";
      String DB_URL = "jdbc:derby:database;create=true";
      Class.forName(Driver);
      Connection connection = DriverManager.getConnection(DB_URL); 
        for (Register r: initCol){
           String lodin1 = r.login.getValue();
           String lodin2 = r.password.getValue();
           String lodin3 = r.email.getValue();
           System.out.print(lodin1);
           String qu = "delete from REGISTER where  login =? and password=? and email= ? "; 
        PreparedStatement preparedStmt = connection.prepareStatement(qu);  
       preparedStmt.setString(1, lodin1);
       preparedStmt.setString(2, lodin2);
       preparedStmt.setString(3, lodin3);
      preparedStmt.executeUpdate(); 
        initCol.forEach(loadData::remove); 
      connection.close();
      return;
        }
        }
        catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }  
    
      

  
            DatabaseHandler.getInstance();
   
  }
 
    
      
@FXML
    private void EXIT(ActionEvent event) {
        
         Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
    
    public static class Register {
        private final SimpleStringProperty login;
        private final SimpleStringProperty password;
        private final SimpleStringProperty email;
        
        public Register(String login,String password,String email){
          this.login = new SimpleStringProperty(login);
          this.password = new SimpleStringProperty(password);
          this.email = new SimpleStringProperty(email);
         
        }

        public String  getLogin() {
            return login.get();
        }
        public String  getPassword() {
            return password.get();
        }

        public String  getEmail() {
            return email.get();
        }

        
    }
    
}
