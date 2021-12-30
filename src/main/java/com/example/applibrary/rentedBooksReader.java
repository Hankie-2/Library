package com.example.applibrary;

import com.example.applibrary.model.EncapsulatedRentedBooks;
import com.example.applibrary.service.RentedBooksService;
import interfaces.buttonsOnAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class rentedBooksReader implements Initializable, buttonsOnAction {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Button btnRentedBooks;

    @FXML
    private TextField fieldSearch;

    @FXML
    private ImageView icon;

    @FXML
    private TableColumn<EncapsulatedRentedBooks, String> tableBook;

    @FXML
    private TableColumn<EncapsulatedRentedBooks, Integer> tableCost;

    @FXML
    private TableColumn<EncapsulatedRentedBooks, String> tableEnd;

    @FXML
    private TableColumn<EncapsulatedRentedBooks, Integer> tableID;

    @FXML
    private TableView<EncapsulatedRentedBooks> tableRentedBooks;

    @FXML
    private TableColumn<EncapsulatedRentedBooks,EncapsulatedRentedBooks> tableAction;

    @FXML
    private TableColumn<EncapsulatedRentedBooks, String> tableStart;


    private RentedBooksService service = new RentedBooksService();

    ObservableList<EncapsulatedRentedBooks> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        icon.setImage(brandingImage);

        UpdateTable();
    }

    public void UpdateTable(){
        tableID.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, Integer>("id"));
        tableBook.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, String>("name"));
        tableCost.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, Integer>("rentCost"));
        tableStart.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, String>("startDate"));
        tableEnd.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, String>("expirationDate"));

        data = FXCollections.observableArrayList(service.findByUser());
        tableRentedBooks.setItems(data);
    }


    public void rentedBooksOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("rentedBooksReader.fxml"));
        Stage window = (Stage) btnRentedBooks.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void closeWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) btnExit.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void openFirstWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("readerBook.fxml"));
        Stage window = (Stage) btnBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openSecondWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("readedBooks.fxml"));
        Stage window = (Stage) btnReadedBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openThirdWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("favoriteBooks.fxml"));
        Stage window = (Stage) btnFavoriteBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openFourthWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("purchasedBooks.fxml"));
        Stage window = (Stage) btnPurchasedBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openFifthWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InformationReader.fxml"));
        Stage window = (Stage) btnInfo.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }
}
