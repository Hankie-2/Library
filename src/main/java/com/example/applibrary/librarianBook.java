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

public class librarianBook implements Initializable, buttonsOnAction {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBooks;

    @FXML
    private Button btnRentedBooks;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnReaders;

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
        javafx.scene.image.Image brandingImage = new Image(brandingFile.toURI().toString());
        icon.setImage(brandingImage);

        //Show all books
        tableID.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,Integer>("id"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,Integer>("amount"));
        tableName.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,String>("book"));
        tableRent.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,Integer>("rentCost"));
        tableAuthor.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,String>("author"));
        tableSell.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,Integer>("sellCost"));
        tablePublicate.setCellValueFactory(new PropertyValueFactory<EncapsulatedBooks,Integer>("publicate"));

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

    public void addButtonOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddBook.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Buch hinzufügen");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void closeWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) btnExit.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openFirstWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("librarianMenu.fxml"));
        Stage window = (Stage) btnDashboard.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void openSecondWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("readersMenu.fxml"));
        Stage window = (Stage) btnReaders.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void openThirdWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("rentedBooks.fxml"));
        Stage window = (Stage) btnRentedBooks.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void openFourthWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("librarianBook.fxml"));
        Stage window = (Stage) btnBooks.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void openFifthWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InformationLibrarian.fxml"));
        Stage window = (Stage) btnInfo.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }
}
