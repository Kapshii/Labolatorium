
package Setting.listmember;

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
import Labolatorium.addmember.MemberAddController;

public class MemberListController implements Initializable {
    
    ObservableList<Member> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member , String> nameCol;
    @FXML
    private TableColumn<Member , String> idCol;
    @FXML
    private TableColumn<Member , String> mobileCol;
    @FXML
    private TableColumn<Member , String> emailCol;
    @FXML
    private AnchorPane rootPane;  
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
 //       clearFields();
    }    
     private void initCol() { 
    idCol.setCellValueFactory(new PropertyValueFactory<>("id")); 
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
     tableView.getItems().setAll(list);
    }    
     private void loadData() {
              DatabaseHandler handler = DatabaseHandler.getInstance();
              String qu = "SELECT * FROM MEMBER1";
               
                ResultSet rs = handler.execQuery(qu);
                try {
                    while(rs.next()){
                         String id = rs.getString("id");
                        String name = rs.getString("name");  
                        String mobile = rs.getString("mobile");
                        String email = rs.getString("email");
                     
                        list.add( new Member(name, id, mobile, email));
                }
                
            }   catch (SQLException ex) {
            Logger.getLogger(MemberAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
                tableView.getItems().setAll(list);
    }

    @FXML
    private void deleted(ActionEvent event) {
        ObservableList<Member> initCol, loadData;
         loadData = tableView.getItems();
        initCol = tableView.getSelectionModel().getSelectedItems();
try
    {
        String Driver = "org.apache.derby.jdbc.EmbeddedDriver";
      String DB_URL = "jdbc:derby:database;create=true";
      Class.forName(Driver);
      Connection connection = DriverManager.getConnection(DB_URL); 
        for (Member r: initCol){
           String lodin1 = r.id.getValue();
           String lodin2 = r.name.getValue(); 
           String lodin3 = r.mobile.getValue();
           String lodin4 = r.email.getValue();
           System.out.print(lodin1);
           System.out.print(lodin2);
           System.out.print(lodin3);
           System.out.print(lodin4);
           String qu = "delete from MEMBER1 where  id=? and name =? and mobile=? and email=?  "; 
        PreparedStatement preparedStmt = connection.prepareStatement(qu);  
       preparedStmt.setString(1, lodin1);
       preparedStmt.setString(2, lodin2);
       preparedStmt.setString(3, lodin3);
       preparedStmt.setString(4, lodin4);
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
    public static class Member {
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;
        private final SimpleStringProperty mobile;
        private final SimpleStringProperty email;
        
        public Member(String name,String id, String mobile, String email){
          this.name = new SimpleStringProperty(name);
          this.id = new SimpleStringProperty(id);
          this.mobile = new SimpleStringProperty(mobile);
          this.email = new SimpleStringProperty(email);
         
        }

        public String  getName() {
            return name.get();
        }

        public String  getId() {
            return id.get();
        }

        public String  getMobile() {
            return mobile.get();
        }

        public String  getEmail() {
            return email.get();
        }

        
    }
}
