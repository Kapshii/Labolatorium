
package Labolatorium.listmember;

import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
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
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));  
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
                         String name = rs.getString("name");  
                         String id = rs.getString("id"); 
                        String mobile = rs.getString("mobile");
                        String email = rs.getString("email");
                     
                        list.add( new Member(name, id, mobile, email));
                }
                
            }   catch (SQLException ex) {
            Logger.getLogger(MemberAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
                tableView.getItems().setAll(list);
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
