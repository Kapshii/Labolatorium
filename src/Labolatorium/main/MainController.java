 
package Labolatorium.main;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import Labolatorium.database.DatabaseHandler;

 
public class MainController implements Initializable {
     HashMap<Integer, Integer> mapMouth = new HashMap<Integer, Integer>();
	LocalDate dayTarget;
          Stage stage;
        Boolean isReadyForSubmission = false;
    @FXML
    private HBox book_info;
    @FXML
    private HBox member_info;  
    @FXML
    private TextField bookIDInput;
    @FXML
    private Text bookName;
    @FXML
    private Text bookAuthor;
    @FXML
    private Text bookStatus; 
    @FXML
    private TextField memberIDInput;
    
    @FXML
    private Text memberName;
    @FXML
    private Text memberMobile;
    @FXML
    private JFXTextField bookID;
    @FXML
    private ListView <String> issueDataList;
     DatabaseHandler databaseHandler;
    @FXML
    private Label day1;
    @FXML
    private Label day2;
    @FXML
    private Label day3;
    @FXML
    private Label day4;
    @FXML
    private Label day5;
    @FXML
    private Label day6;
    @FXML
    private Label day7;
    @FXML
    private Label lblMouthYear;
    @FXML
    private Label case01;
    @FXML
    private Label case08;
    @FXML
    private Label case15;
    @FXML
    private Label case22;
    @FXML
    private Label case29;
    @FXML
    private Label case36;
    @FXML
    private Label case37;
    @FXML
    private Label case30;
    @FXML
    private Label case23;
    @FXML
    private Label case16;
    @FXML
    private Label case09;
    @FXML
    private Label case02;
    @FXML
    private Label case04;
    @FXML
    private Label case11;
    @FXML
    private Label case18;
    @FXML
    private Label case25;
    @FXML
    private Label case32;
    @FXML
    private Label case39;
    @FXML
    private Label case38;
    @FXML
    private Label case31;
    @FXML
    private Label case24;
    @FXML
    private Label case17;
    @FXML
    private Label case10;
    @FXML
    private Label case03;
    @FXML
    private Label case07;
    @FXML
    private Label case14;
    @FXML
    private Label case21;
    @FXML
    private Label case28;
    @FXML
    private Label case35;
    @FXML
    private Label case42;
    @FXML
    private Label case06;
    @FXML
    private Label case13;
    @FXML
    private Label case20;
    @FXML
    private Label case27;
    @FXML
    private Label case34;
    @FXML
    private Label case41;
    @FXML
    private Label case40;
    @FXML
    private Label case33;
    @FXML
    private Label case26;
    @FXML
    private Label case19;
    @FXML
    private Label case12;
    @FXML
    private Label case05;
    @FXML
    private ImageView btnMonthPrec;
    @FXML
    private ImageView btnMonthSuiv;
    @FXML
    private Label label;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXDepthManager.setDepth(book_info, 1);
         JFXDepthManager.setDepth(member_info, 1);
         dayTarget=LocalDate.now();
		initDaysOfMounth(dayTarget);
		doStyle(0,0,0);
         databaseHandler = DatabaseHandler.getInstance();
          KeyFrame keyframe = new KeyFrame(Duration.millis(1000), e -> {
            // update the label with current time
            label.setText(getCurrentTime());
            
        });
        Timeline timeline = new Timeline(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }    

        @FXML
    private void loadAddMember(ActionEvent event) {
        loadWindows("/Labolatorium/addmember/member_add.fxml", " Dodaj nowego Użytkownika ");
    }

    @FXML
    private void loadAddBook(ActionEvent event) {
        loadWindows("/Labolatorium/addbook/add_book.fxml", " Dodaj nową książkę ");
    }

    @FXML
    private void loadMembersTable(ActionEvent event) {
        loadWindows("/Labolatorium/listmember/member_list.fxml", " Lista Użytkowników ");
    }

    @FXML
    private void loadBookTable(ActionEvent event) {
        loadWindows("/Labolatorium/listbook/book_list.fxml", " Lista książek ");
    }
    void loadWindows(String loc, String title)
    {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
             stage.getIcons().add(new Image("Labolatorium/main/icon.png"));
            stage.setScene(new Scene(parent)); 
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadBookInfo(ActionEvent event) {
        clearBookCache();
        
        String id = bookIDInput.getText();
        String qu = "SELECT * FROM BOOK WHERE id = '" + id + "'";
        ResultSet rs = databaseHandler.execQuery(qu);
        Boolean flag = false;
        try {
            while(rs.next())
            {
                String bName = rs.getString("title");
                String bAuthor = rs.getString("author");
                Boolean bStatus = rs.getBoolean("isAvail");
                
                bookName.setText(bName);
                bookAuthor.setText(bAuthor);
                String status = (bStatus)?"Dostępny" : "Nie jest dostępny";
                bookStatus.setText(status);
                flag = true;
            }  
            
            if(!flag){
                bookName.setText("Nie ma takiej książki");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    void clearBookCache()
    {
        bookName.setText("");
        bookAuthor.setText("");
        bookStatus.setText("");
    }
     void clearMemberCache()
    {
        memberName.setText("");
        memberMobile.setText("");
    }
    @FXML
    private void loadMemberInfo(ActionEvent event) {
        clearMemberCache();
        
        String id = memberIDInput.getText();
        String qu = "SELECT * FROM MEMBER1 WHERE id = '" + id + "'";
        ResultSet rs = databaseHandler.execQuery(qu);
        Boolean flag = false;
        try {
            while(rs.next())
            {
                String mName = rs.getString("name");
                String mMobile = rs.getString("mobile");
                memberName.setText(mName);
                memberMobile.setText(mMobile);
                
                flag = true;
            }  
            
            if(!flag){
                memberName.setText("Brak takiego członka");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadIssueOperation(ActionEvent event) {
        String memberID = memberIDInput.getText();
        String bookID = bookIDInput.getText();
     
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Isuue Operation");
        alert.setHeaderText(null);
        alert.setContentText("Na pewno chcesz wydać książkę " + bookName.getText() + "\n to " + memberName.getText() + " ?");
        
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get()== ButtonType.OK){
            String str = "INSERT INTO ISSUE (memberID,bookID) VALUES ("
                    + "'" + memberID + "',"
                    + "'" + bookID + "')";
            String str2 = "UPDATE BOOK SET isAvail = false WHERE id = '" + bookID + "'";
            System.out.println(str + " and " + str2);
            
            if(databaseHandler.execAction(str) && databaseHandler.execAction(str2)){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Powodzenie");
                alert1.setHeaderText(null);
                alert1.setContentText(" Zakończono wydanie książki");
                alert1.showAndWait();
            } else
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Skasowany");
                alert1.setHeaderText(null);
                alert1.setContentText(" Wydanie operacji nie powiodło się");
                alert1.showAndWait();
            }
    } else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Odwołany");
            alert1.setHeaderText(null);
            alert1.setContentText("Wydanie operacji zostało anulowane");
            alert1.showAndWait();
        }
    }

    @FXML
    private void loadBookInfo2(ActionEvent event) {
        ObservableList<String> issueData = FXCollections.observableArrayList();
        isReadyForSubmission = false; 
        String id = bookID.getText();
        String qu = "SELECT * FROM ISSUE WHERE bookID = '" + id + "'";
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            while(rs.next()){
                String mBookID = id;
                String mMemberID = rs.getString("memberID");
                Timestamp mIssueTime = rs.getTimestamp("issueTime");
                int mRenewCount = rs.getInt("renew_count");
                
                issueData.add("Data wydania i czas :" + mIssueTime.toGMTString());
                issueData.add("Odnowiona liczba :" + mRenewCount);
               
                issueData.add("Informacje o książce:-");
                qu = "SELECT * FROM BOOK WHERE ID = '" + mBookID + "'";
                ResultSet rl = databaseHandler.execQuery(qu);
                while(rl.next()){
                issueData.add("\tNazwa książki :" + rl.getString("title"));
                issueData.add("\tIdentyfikator książki:" + rl.getString("id"));
                issueData.add("\tAutor książki :" + rl.getString("author"));
                issueData.add("\tWydawca książek :" + rl.getString("publisher"));
                }
                 qu = "SELECT * FROM MEMBER1 WHERE ID = '" + mMemberID + "'";
                rl = databaseHandler.execQuery(qu);
                issueData.add("Informacje o użytkowniku:-");
                while(rl.next()){
                issueData.add("\tImię :" + rl.getString("name"));
                issueData.add("\tMobile :" + rl.getString("mobile"));
                issueData.add("\tEmail :" + rl.getString("email"));
                
                 }
                isReadyForSubmission = true; 
            }
            } 
            catch (SQLException ex) { 
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    issueDataList.getItems().setAll(issueData);
    }

    @FXML
    private void loadSubmissionOP(ActionEvent event) {
       if(!isReadyForSubmission){
           Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Skasowany");
                alert.setHeaderText(null);
                alert.setContentText(" Wybierz książkę do wysłania");
                alert.showAndWait();
       
           return;
       }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdź operację przesyłania");
        alert.setHeaderText(null);
        alert.setContentText("Czy na pewno chcesz zwrócić książkę ?");
        
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get()==ButtonType.OK){
        String id = bookID.getText();
        String ac1 = "DELETED FROMISSUE WHERE BOOKID= '" + id + "'";
        String ac2 = "UPDATE BOOK SET ISAVAIL = TRUE WHERE ID = '" + id + "'";
        
        if (databaseHandler.execAction(ac1)&&databaseHandler.execAction(ac2))
        {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Powodzenie");
                alert.setHeaderText(null);
                alert.setContentText(" Książka została przekazana");
                alert.showAndWait();
        }else
        {
             Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Skasowany");
                alert.setHeaderText(null);
                alert.setContentText(" Książka została niepowodzona");
                alert.showAndWait();
     } 
        }else {
                 Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Odwołany");
            alert1.setHeaderText(null);
            alert1.setContentText("Operacja składania została anulowana");
            alert1.showAndWait(); 
                
                }

   
    }

    @FXML
    private void loadRenewOp(ActionEvent event) {
        if(!isReadyForSubmission){
           Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Skasowany");
                alert.setHeaderText(null);
                alert.setContentText(" Proszę wybrać książkę do odnowienia");
                alert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie operacji odnawiania");
        alert.setHeaderText(null);
        alert.setContentText("Czy na pewno chcesz odnowić książkę ?");
        
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get()==ButtonType.OK){
            String ac = "UPDATE ISSUE SET issueTime = CURENT TIMESTAMP, renew_count = renew_count+1 WHERE BOOKID ='" + bookID.getText() + "'";
             System.out.println("ac");
             if (databaseHandler.execAction(ac)){
                 Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Powodzenie");
                alert.setHeaderText(null);
                alert.setContentText(" Książka została odnowiona");
                alert.showAndWait();
             }else{
                  Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Faild");
                alert.setHeaderText(null);
                alert.setContentText(" Odnowienie nie powiodło się");
                alert.showAndWait();
             }
    }    else{
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Odwołany");
            alert1.setHeaderText(null);
            alert1.setContentText("Odnowiona operacja została anulowana");
            alert1.showAndWait(); 
            
        }  
    
    }
public String getMounth(int mouth){
		switch(mouth){
		case 1:
				return "Styczeń";
		case 2:
				return "Luty";
		case 3:
				return "Marzec";
		case 4:
				return "Kwiecień";
		case 5:
				return "Maj";
		case 6:
				return "Czerwiec";
		case 7:
				return "Lipiec";
		case 8:
				return "Sierpień";
		case 9:
				return "Wrzesień";
		case 10:
				return "Październik";
		case 11:
				return "Listopad";
		case 12:
				return "Grudzień";
		}
		return null;
	}
	
	private int getMonthLength(LocalDate date){
		int monthLength=30;
		if((date.getMonthValue()==1)||(date.getMonthValue()==3)||(date.getMonthValue()==5)||(date.getMonthValue()==7)||(date.getMonthValue()==8)||(date.getMonthValue()==10)||(date.getMonthValue()==12)){
			monthLength=31;
	    }else if(date.getMonthValue()==2){
	    	if(date.isLeapYear()){
	    		monthLength=29;
	    	}else{
	    		monthLength=28;
	    	}
	    }else{
	    	monthLength=30;
	    }
		return monthLength;
	}
	
	private int getPrecMonthLength(LocalDate date){
		int monthLength=30;
		if((date.getMonthValue()-1==1)||(date.getMonthValue()-1==3)||(date.getMonthValue()-1==5)||(date.getMonthValue()-1==7)||(date.getMonthValue()-1==8)||(date.getMonthValue()-1==10)||(date.getMonthValue()-1==12)){
			monthLength=31;
	    }else if(date.getMonthValue()-1==2){
	    	if(date.isLeapYear()){
	    		monthLength=29;
	    	}else{
	    		monthLength=28;
	    	}
	    }else{
	    	monthLength=30;
	    }
		return monthLength;
	}
	
	private int getFirstDayCase(LocalDate date){
		int firstCase = 1;
		LocalDate firstDate;
		if(date.getMonthValue()<10){
			firstDate=LocalDate.parse(date.getYear()+"-0"+date.getMonthValue()+"-01");
		}else{
			firstDate=LocalDate.parse(date.getYear()+"-"+date.getMonthValue()+"-01");
		}
		firstCase=firstDate.getDayOfWeek().getValue();
		return firstCase;
	}
	
	private void intiDaysOfPrecMonth(LocalDate today){
		int monthLength=getPrecMonthLength(today);

		int dayOfMonth=monthLength;
		for(int i=getFirstDayCase(today)-1;i>0;i--){
			mapMouth.put(i,dayOfMonth);
			dayOfMonth--;
		}
	}
	
	private void intiDaysOfThisMonth(LocalDate today){
		int monthLength=getMonthLength(today);
		int dayOfMonth=1;
		
		for(int i=getFirstDayCase(today);i<=getFirstDayCase(today)+monthLength;i++){
			mapMouth.put(i,dayOfMonth);
			dayOfMonth++;
		}
	}
	
	private void intiDaysOfNextMonth(LocalDate today){
		int dayOfMonth=1;
		int monthLength=getMonthLength(today);

		for(int i=monthLength+getFirstDayCase(today);i<=42;i++){
			mapMouth.put(i,dayOfMonth);
			dayOfMonth++;
		}
	}
	
	
	public void initDaysOfMounth(LocalDate today){
		intiDaysOfPrecMonth(today);
		intiDaysOfThisMonth(today);
		intiDaysOfNextMonth(today);
		lblMouthYear.setText(getMounth(today.getMonthValue())+", "+today.getYear());
		System.out.println("Date: "+today.toString());
		case01.setText(mapMouth.get(1)+"");
		case02.setText(mapMouth.get(2)+"");
		case03.setText(mapMouth.get(3)+"");
		case04.setText(mapMouth.get(4)+"");
		case05.setText(mapMouth.get(5)+"");
		case06.setText(mapMouth.get(6)+"");
		case07.setText(mapMouth.get(7)+"");
		case08.setText(mapMouth.get(8)+"");
		case09.setText(mapMouth.get(9)+"");
		case10.setText(mapMouth.get(10)+"");
		case11.setText(mapMouth.get(11)+"");
		case12.setText(mapMouth.get(12)+"");
		case13.setText(mapMouth.get(13)+"");
		case14.setText(mapMouth.get(14)+"");
		case15.setText(mapMouth.get(15)+"");
		case16.setText(mapMouth.get(16)+"");
		case17.setText(mapMouth.get(17)+"");
		case18.setText(mapMouth.get(18)+"");
		case19.setText(mapMouth.get(19)+"");
		case20.setText(mapMouth.get(20)+"");
		case21.setText(mapMouth.get(21)+"");
		case22.setText(mapMouth.get(22)+"");
		case23.setText(mapMouth.get(23)+"");
		case24.setText(mapMouth.get(24)+"");
		case25.setText(mapMouth.get(25)+"");
		case26.setText(mapMouth.get(26)+"");
		case27.setText(mapMouth.get(27)+"");
		case28.setText(mapMouth.get(28)+"");
		case29.setText(mapMouth.get(29)+"");		
		case30.setText(mapMouth.get(30)+"");
		case31.setText(mapMouth.get(31)+"");
		case32.setText(mapMouth.get(32)+"");
		case33.setText(mapMouth.get(33)+"");
		case34.setText(mapMouth.get(34)+"");		
		case35.setText(mapMouth.get(35)+"");
		case36.setText(mapMouth.get(36)+"");
		case37.setText(mapMouth.get(37)+"");
		case38.setText(mapMouth.get(38)+"");
		case39.setText(mapMouth.get(39)+"");
		case40.setText(mapMouth.get(40)+"");
		case41.setText(mapMouth.get(41)+"");
		case42.setText(mapMouth.get(42)+"");
	}
	
	@FXML
	private void nextMonth(){
		int m=dayTarget.getMonthValue();
		int a=dayTarget.getYear();
		m++;
		String mois=m+"";
		String ann=a+"";
		if(m<10){
			mois="0"+mois;
		}else if(m>12){
			m=1;
			mois="01";
			a++;
			ann=a+"";
		}
		dayTarget=LocalDate.parse(ann+"-"+mois+"-01");
		initDaysOfMounth(dayTarget);
	}
	
	
	@FXML
	private void precMonth(){
		int m=dayTarget.getMonthValue();
		int a=dayTarget.getYear();
		m--;
		String mois=m+"";
		String ann=a+"";
		if(m<10){
			mois="0"+mois;
		}
		if(m==0){
			m=12;
			mois="12";
			a--;
			ann=a+"";
		}
		dayTarget=LocalDate.parse(ann+"-"+mois+"-01");
		initDaysOfMounth(dayTarget);
	}
	
	
	private void doStyle(int first,int last,int today){
		
	}
	 private String getCurrentTime(){
        LocalTime time = LocalTime.now();
        String timeString = time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        return timeString;
    }

 
    private double stageX;
    private double stageY;
    
    private void handleOnMouseDrag(javafx.scene.input.MouseEvent event) {
        stage.setX(event.getScreenX() + stageX);
        stage.setY(event.getScreenY() + stageY);
    }

    private void handleOnMousePressed(javafx.scene.input.MouseEvent event) {
        stageX = stage.getX() - event.getScreenX();
        stageY = stage.getY() - event.getScreenY();
        
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }



    @FXML
    private void Setting(ActionEvent event) {
         loadWindows("/login1/LOGIN1.fxml", " Ustawienia ");
    } 

    @FXML
    private void loadAddRezerwacja(ActionEvent event) {
        loadWindows("/Rezerwacja/Rezerwacja.fxml", " Rezerwacja ");
    }

    @FXML
    private void loadRezerwacjaTable(ActionEvent event) {
        loadWindows("/RezerwacjaAdd/RezerwacjaAdd.fxml", " Lista Zarezorwanych ");
    }

    
}

