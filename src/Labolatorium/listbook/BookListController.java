 
package Labolatorium.listbook;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Labolatorium.database.DatabaseHandler;
import Labolatorium.addbook.BookAddController;

 
public class BookListController implements Initializable {

     ObservableList<Book1> list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Book1> tableView;
    @FXML
    private TableColumn<Book1, String> titleCol;
    @FXML
    private TableColumn<Book1, String> idCol;
    @FXML
    private TableColumn<Book1, String> authorCol;
    @FXML
    private TableColumn<Book1, String> publisherCol;
    @FXML
    private TableColumn<Book1, Boolean> availabilityCol;
    

      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        
        loadData();
    }    

    private void initCol() { 
    titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
    publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
    availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }

    private void loadData() {
              DatabaseHandler handler = DatabaseHandler.getInstance();
              String qu = "SELECT * FROM BOOK1";
                ResultSet rs = handler.execQuery(qu);
                try {
                    while(rs.next()){
                        String titlex = rs.getString("title");
                        String id = rs.getString("id");
                        String author = rs.getString("author");
                        String publisher = rs.getString("publisher");
                        Boolean avail = rs.getBoolean("isAvail");
                        list.add( new Book1(titlex, id, author, publisher, avail));
                    
                }
                
            }   catch (SQLException ex) {
            Logger.getLogger(BookAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
                tableView.getItems().setAll(list);
    }

     
    
        
    public static class Book1{
        private final SimpleStringProperty title;
        private final SimpleStringProperty id;
        private final SimpleStringProperty author;
        private final SimpleStringProperty publisher;
        private final SimpleBooleanProperty availabilty;
        public Book1(String title,String id, String author, String pub, Boolean avail){
          this.title = new SimpleStringProperty(title);
          this.id = new SimpleStringProperty(id);
          this.author = new SimpleStringProperty(author);
          this.publisher = new SimpleStringProperty(pub);
          this.availabilty = new SimpleBooleanProperty(avail);
        }

        public String  getTitle() {
            return title.get();
        }

        public String  getId() {
            return id.get();
        }

        public String  getAuthor() {
            return author.get();
        }

        public String  getPublisher() {
            return publisher.get();
        }

        public Boolean getAvalilability() {
            return availabilty.get();
        }
        
            
    }
}
     