
package Rezerwacja;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Labolatorium.database.DatabaseHandler;


public class RezerwacjaController implements Initializable {
ObservableList<Rezerwacja> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Rezerwacja> tableView;
   
    @FXML
    private TableColumn<Rezerwacja, String> ime;
    @FXML
    private TableColumn<Rezerwacja, String> Nazwisko;
    @FXML
    private TableColumn<Rezerwacja, String> Misiac;
    @FXML
    private TableColumn<Rezerwacja, String> zGodziny;
    @FXML
    private TableColumn<Rezerwacja, String> doGodziny;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField nazwisko;
    @FXML
    private JFXDatePicker misiac;
    @FXML
    private JFXDatePicker zgodziny;
    @FXML
    private JFXDatePicker dogodziny;
    @FXML
    private JFXButton deletedButton;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton; 
    @FXML
    private VBox root;
    
       

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();  
//       dogodziny.setTime(LocalTime.of(9, 00));
//     zgodziny.setTime(LocalTime.of(10, 00));
   
      misiac.setOnAction(event -> {
    LocalDate date = misiac.getValue();
    System.out.println("Selected date: " + date);
    
});
    }    
        
     private void initCol() { 
    ime.setCellValueFactory(new PropertyValueFactory<>("name"));
    Nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));  
    Misiac.setCellValueFactory(new PropertyValueFactory<>("misiac"));
    zGodziny.setCellValueFactory(new PropertyValueFactory<>("zgodziny")); 
    doGodziny.setCellValueFactory(new PropertyValueFactory<>("dogodziny"));
     tableView.getItems().setAll(list);
    }    
     
     private void loadData() {
         list.clear();
              DatabaseHandler handler = DatabaseHandler.getInstance();
              String qu = "SELECT * FROM REZERWACJA";
               
                ResultSet rs = handler.execQuery(qu);
                try {
                    while(rs.next()){
                         String name = rs.getString("name");  
                         String nazwisko = rs.getString("nazwisko");
                         String misiac = rs.getString("misiac"); 
                         String zgodziny = rs.getString("zgodziny");
                         String dogodziny = rs.getString("dogodziny");
                     
                        list.add( new Rezerwacja(name, nazwisko, misiac, zgodziny, dogodziny));
                }
                
            }   catch (SQLException ex) {
            Logger.getLogger(RezerwacjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
                tableView.getItems().setAll(list);
    }

    @FXML
    private void cancel(ActionEvent event) {
         Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void RezerwacjaAdd(ActionEvent  event ) { 
          
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String mName = name.getText();
        String mNazwisko = nazwisko.getText();
        LocalDate  mMisiac = misiac.getValue();
        LocalTime mZgodziny = zgodziny.getTime();
        LocalTime mDogodziny = dogodziny.getTime();
        Boolean flag = mName.isEmpty()|| mNazwisko.isEmpty()|| mMisiac.isBefore(mMisiac)||mZgodziny.isBefore(mZgodziny)|| mDogodziny.isBefore(mDogodziny);
         System.out.print(flag);
           
//           System.out.print(mMisiac);
//           System.out.print(mZgodziny);
//           System.out.print(mDogodziny);
        if(flag){ 
//            misiac.getEditor().clear();
//                   misiac.clear();
//                   zgodziny.getEditor().clear();
                   name.clear(); 
                   nazwisko.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null); 
            alert.setContentText("Wprowadź we wszystkich polach");
            alert.showAndWait(); 
           return;
                 
        }
        
        String st= "INSERT INTO REZERWACJA VALUES (" 
                + "'" + mName  +"',"
                + "'" + mNazwisko +"',"
                + "'" + mMisiac +"',"
                + "'" + mZgodziny +"',"
                + "'" + mDogodziny +"'"
                + ")"; 
                System.out.println("Save");
                System.out.print(st);
//                System.out.print(mName);
//           System.out.print(mNazwisko);
//           System.out.print(mMisiac);
//           System.out.print(mZgodziny);
//           System.out.print(mDogodziny);
                if(handler.execAction(st))
                 {   
                     misiac.getEditor().clear();
                   dogodziny.getEditor().clear();
                   zgodziny.getEditor().clear();
                   name.clear(); 
                   nazwisko.clear();  
                      loadData();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null); 
                     alert.setContentText("Zapisano"); 
//                    alert.showAndWait();  
//                    System.out.print(mName);
//           System.out.print(mNazwisko);
//           System.out.print(mMisiac);
//           System.out.print(mZgodziny);
//           System.out.print(mDogodziny);
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
          ObservableList<Rezerwacja>   initCol, loadData;
        loadData = tableView.getItems();
        initCol = tableView.getSelectionModel().getSelectedItems();
        
try
    {
        String Driver = "org.apache.derby.jdbc.EmbeddedDriver";
      String DB_URL = "jdbc:derby:database;create=true";
      Class.forName(Driver);
      Connection connection = DriverManager.getConnection(DB_URL); 
        for (Rezerwacja r: initCol){
           String lodin1 = r.name.getValue();
           String lodin2 = r.nazwisko.getValue(); 
           String lodin3 = r.misiac.getValue();
           String lodin4 = r.zgodziny.getValue();  
           String lodin = r.dogodziny.getValue();
           System.out.print(lodin1);
           System.out.print(lodin2);
           System.out.print(lodin3);
           System.out.print(lodin4);
           System.out.print(lodin);
           String qu = "delete from REZERWACJA where  name=? and nazwisko=? and misiac=? and zgodziny=? and  dogodziny=? "; 
        PreparedStatement preparedStmt = connection.prepareStatement(qu);  
       preparedStmt.setString(1, lodin1);
       preparedStmt.setString(2, lodin2);
       preparedStmt.setString(3, lodin3);
       preparedStmt.setString(4, lodin4); 
       preparedStmt.setString(5, lodin );
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

    
 
    public static class Rezerwacja {
        private final SimpleStringProperty name;
        private final SimpleStringProperty nazwisko;
        private final SimpleStringProperty misiac;
        private final SimpleStringProperty zgodziny;
        private final SimpleStringProperty dogodziny;
        public Rezerwacja(String name,String nazwisko,  String misiac, String zgodziny, String dogodziny){
          this.name = new SimpleStringProperty(name);
          this.nazwisko = new SimpleStringProperty(nazwisko);
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
