package com.example.applibrary;

import com.example.applibrary.model.EncapsulatedBooks;
import com.example.applibrary.service.LibraryService;
import interfaces.buttonsOnAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class readerBook implements Initializable,buttonsOnAction{
    @FXML
    private Button btnBooks;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnFavoriteBooks;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnPurchasedBooks;

    @FXML
    private Button btnReadedBooks;

    @FXML
    private Button btnBuy;

    @FXML
    private TextField fieldSearch;

    @FXML
    private ImageView icon;

    @FXML
    private TableColumn<EncapsulatedBooks, Integer> tableAmount;

    @FXML
    private TableColumn<EncapsulatedBooks, String> tableAuthor;

    @FXML
    private TableView<EncapsulatedBooks> tableBook;

    @FXML
    private TableColumn<EncapsulatedBooks, Integer> tableID;

    @FXML
    private TableColumn<EncapsulatedBooks, String> tableName;

    @FXML
    private TableColumn<EncapsulatedBooks, Integer> tablePublicate;

    @FXML
    private TableColumn<EncapsulatedBooks, Integer> tableRent;

    @FXML
    private TableColumn<EncapsulatedBooks, Integer> tableSell;

    private LibraryService service = new LibraryService();
    
    ObservableList<EncapsulatedBooks> listOfBook = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        icon.setImage(brandingImage);

        listOfBook = FXCollections.observableArrayList(service.findAll());
        tableBook.setItems(listOfBook);
        search_user();
    }
    @FXML
    void search_user() {
        ObservableList<EncapsulatedBooks> data = FXCollections.observableArrayList();
        tableID.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,Integer>("id"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,Integer>("amount"));
        tableName.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,String>("book"));
        tableRent.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,Integer>("rentCost"));
        tableAuthor.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,String>("author"));
        tableSell.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,Integer>("sellCost"));
        tablePublicate.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,Integer>("publicate"));


        data = FXCollections.observableArrayList(service.findAll());
        tableBook.setItems(data);
        FilteredList<EncapsulatedBooks> filteredData = new FilteredList<>(data, b -> true);
        fieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(users -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (users.getBook().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                } else if (users.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<EncapsulatedBooks> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableBook.comparatorProperty());
        tableBook.setItems(sortedData);
    }

    public void buyButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BuyBook.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Buy a book");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private Button btnListen;
    public void listenButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListenAudioBooks.fxml"));
        Stage window = (Stage) btnListen.getScene().getWindow();
        window.setScene(new Scene(root,689,190));
    }

    @FXML
    private Button btnRentedBooks;
    public void rentedBooksOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("rentedBooksReader.fxml"));
        Stage window = (Stage) btnRentedBooks.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void closeWindow(ActionEvent event) throws IOException { // Close the Window
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) btnExit.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void openFirstWindow(ActionEvent event) throws IOException { // Open Dashboard of Reader Account
        Parent root = FXMLLoader.load(getClass().getResource("reader_menu.fxml"));
        Stage window = (Stage) btnDashboard.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openSecondWindow(ActionEvent event) throws IOException { // Open list of readed books
        Parent root = FXMLLoader.load(getClass().getResource("readedBooks.fxml"));
        Stage window = (Stage) btnReadedBooks.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void openThirdWindow(ActionEvent event) throws IOException { // Open favorite books
        Parent root = FXMLLoader.load(getClass().getResource("favoriteBooks.fxml"));
        Stage window = (Stage) btnFavoriteBooks.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void openFourthWindow(ActionEvent event) throws IOException { // Open purchased books
        Parent root = FXMLLoader.load(getClass().getResource("purchasedBooks.fxml"));
        Stage window = (Stage) btnPurchasedBooks.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void openFifthWindow(ActionEvent event) throws IOException { // Open information about program
        Parent root = FXMLLoader.load(getClass().getResource("InformationReader.fxml"));
        Stage window = (Stage) btnInfo.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }
	public LibraryService getService() {
		return service;
	}
	public void setService(LibraryService service) {
		this.service = service;
	}
    
    

}
