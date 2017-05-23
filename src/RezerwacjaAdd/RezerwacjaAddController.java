
package RezerwacjaAdd;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Labolatorium.database.DatabaseHandler;


public class RezerwacjaAddController implements Initializable {
ObservableList<RezerwacjaSprzet2> list = FXCollections.observableArrayList();
DatabaseHandler handler = DatabaseHandler.getInstance();
    @FXML
    private TableView<RezerwacjaSprzet2> tableView;
   
    @FXML
    private TableColumn<RezerwacjaSprzet2, String> ime;
    @FXML
    private TableColumn<RezerwacjaSprzet2, String> Nazwisko;
    @FXML
    private TableColumn<RezerwacjaSprzet2, String> Misiac;
    @FXML
    private TableColumn<RezerwacjaSprzet2, String> zGodziny;
    @FXML
    private TableColumn<RezerwacjaSprzet2, String> doGodziny;
    @FXML
    private TableColumn<RezerwacjaSprzet2, String> Sprzet;
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private DatePicker misiac;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField nazwisko;  
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton deletedButton;
    @FXML
    private JFXButton cancelButton; 
     
    @FXML
    private JFXCheckBox sprzet;
    @FXML
    private JFXCheckBox CheckBox2;
    @FXML
    private JFXCheckBox CheckBox3;
    @FXML
    private JFXCheckBox CheckBox4;
    @FXML
    private JFXCheckBox CheckBox5; 
    ObservableList<String> JFXCheckBoxList = FXCollections.observableArrayList(); 
    @FXML
    private JFXDatePicker zgodziny;
    @FXML
    private JFXDatePicker dogodziny;
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData(); 
        
    }    
   
     private void initCol() { 
    ime.setCellValueFactory(new PropertyValueFactory<>("name"));
    Nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko")); 
    Sprzet.setCellValueFactory(new PropertyValueFactory<>("sprzet"));
    Misiac.setCellValueFactory(new PropertyValueFactory<>("misiac"));
    zGodziny.setCellValueFactory(new PropertyValueFactory<>("zgodziny")); 
    doGodziny.setCellValueFactory(new PropertyValueFactory<>("dogodziny"));
    
     tableView.getItems().setAll(list);
    }    
     private void loadData() {
              list.clear();
              String qu = "SELECT * FROM REZERWACJASPRZET";
               
                ResultSet rs = handler.execQuery(qu);
                try {
                    while(rs.next()){
                         String name = rs.getString("name");  
                         String nazwisko = rs.getString("nazwisko");
                         
                         String misiac = rs.getString("misiac"); 
                         String zgodziny = rs.getString("zgodziny");
                         String dogodziny = rs.getString("dogodziny");
                         String sprzet = rs.getString("sprzet");
                        list.add( new RezerwacjaSprzet2(name, nazwisko, misiac, zgodziny, dogodziny, sprzet ));
                }
                
            }   catch (SQLException ex) {
            Logger.getLogger(RezerwacjaAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
                tableView.getItems().setAll(list);
    }
   
    
   

    
    @FXML
    private void cancel(ActionEvent event) {
         Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void RezerwacjaSprzet(ActionEvent event) { 
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String mName = name.getText();
        String mNazwisko = nazwisko.getText();
        String mSprzet = JFXCheckBoxList.toString();
        LocalDate  mMisiac = misiac.getValue();
        LocalTime mZgodziny = zgodziny.getTime();
        LocalTime mDogodziny = dogodziny.getTime(); 
        Boolean flag = mName.isEmpty()|| mNazwisko.isEmpty()|| mSprzet.isEmpty()|| mMisiac.isBefore(mMisiac)|| mZgodziny.isBefore(mZgodziny)|| mDogodziny.isBefore(mDogodziny);
         
        if(flag){ 
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null); 
            alert.setContentText("Wprowadź we wszystkich polach");
            alert.showAndWait(); 
                  
                  return;
        }
        
        String st= "INSERT INTO REZERWACJASPRZET VALUES (" 
                + "'" + mName  +"',"
                + "'" + mNazwisko +"',"
                + "'" + mSprzet +"',"
                + "'" + mMisiac +"',"
                + "'" + mZgodziny +"',"
                + "'" + mDogodziny +"'"
                
                + ")"; 
                System.out.println("Save");
                
                if(handler.execAction(st))
                 {  name.clear(); 
                   nazwisko.clear();  
                   CheckBox2.setSelected(false);
                   CheckBox3.setSelected(false);
                   CheckBox4.setSelected(false);
                   CheckBox5.setSelected(false);
                   sprzet.setSelected(false);
                     loadData();
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
    
    @FXML
    private void deleted(ActionEvent event) {
          ObservableList<RezerwacjaSprzet2>   initCol, loadData;
        loadData = tableView.getItems();
        initCol = tableView.getSelectionModel().getSelectedItems();
    
    
try
    {
        String Driver = "org.apache.derby.jdbc.EmbeddedDriver";
      String DB_URL = "jdbc:derby:database;create=true";
      Class.forName(Driver);
      Connection connection = DriverManager.getConnection(DB_URL); 
        for (RezerwacjaSprzet2 r: initCol){
           String lodin1 = r.name.getValue();
           String lodin2 = r.nazwisko.getValue();   
           String lodin4 = r.misiac.getValue();
           String lodin = r.zgodziny.getValue();  
           String lodin5 = r.dogodziny.getValue();
           String lodin3 = r.sprzet.getValue();
           System.out.print(lodin1);
           System.out.print(lodin2);
           System.out.print(lodin3);
           System.out.print(lodin4);
           System.out.print(lodin);
           System.out.print(lodin5);
           String qu = "delete from REZERWACJASPRZET where  name=? and nazwisko=? and misiac=? and zgodziny=? and  dogodziny=? and sprzet=? "; 
        PreparedStatement preparedStmt = connection.prepareStatement(qu);  
       preparedStmt.setString(1, lodin1);
       preparedStmt.setString(2, lodin2);
       preparedStmt.setString(3, lodin3);
       preparedStmt.setString(4, lodin4); 
       preparedStmt.setString(5, lodin );
       preparedStmt.setString(6, lodin5 );
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
    private void CheckBox10(ActionEvent event) {
        JFXCheckBoxList.add(sprzet.getText());
    }

    @FXML
    private void CheckBox11(ActionEvent event) {
        JFXCheckBoxList.add(CheckBox2.getText());
    }

    @FXML
    private void CheckBox12(ActionEvent event) {
        JFXCheckBoxList.add(CheckBox3.getText());
    }

    @FXML
    private void CheckBox13(ActionEvent event) {
        JFXCheckBoxList.add(CheckBox4.getText());
    }

    @FXML
    private void CheckBox14(ActionEvent event) {
        JFXCheckBoxList.add(CheckBox5.getText());
    }
 
    public static class RezerwacjaSprzet2 {
        private final SimpleStringProperty name;
        private final SimpleStringProperty nazwisko;
        private final SimpleStringProperty sprzet;
        private final SimpleStringProperty misiac;
        private final SimpleStringProperty zgodziny;
        private final SimpleStringProperty dogodziny;
        
        public RezerwacjaSprzet2(String name,String nazwisko, String sprzet, String misiac, String zgodziny, String dogodziny){
          this.name = new SimpleStringProperty(name);
          this.nazwisko = new SimpleStringProperty(nazwisko);
           this.sprzet = new SimpleStringProperty(sprzet);
          this.misiac = new SimpleStringProperty(misiac);
          this.zgodziny = new SimpleStringProperty(zgodziny);
          this.dogodziny = new SimpleStringProperty(dogodziny);
         
        }

   
        public String  getName() {
            return name.get();
        }

        public String  getNazwisko() {
            return nazwisko.get();
        }
        public String  getSprzet() {
            return sprzet.get();
        }
        public String  getMisiac() {
            return misiac.get();
        }

        public String  getZgodziny() {
            return zgodziny.get();
        }
         public String  getDogodziny() {
            return dogodziny.get();
        }
        
    }
    
}
