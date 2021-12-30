package com.example.applibrary;

import com.example.applibrary.model.EncapsulatedPurchasedBooks;
import com.example.applibrary.service.PurchasedBooksService;
import interfaces.buttonsOnAction;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class purchasedBooks implements Initializable, buttonsOnAction {

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
    private ImageView icon;

    @FXML
    private TableView<EncapsulatedPurchasedBooks> tableBook;

    @FXML
    private TableColumn<EncapsulatedPurchasedBooks, String> tableDay;

    @FXML
    private TableColumn<EncapsulatedPurchasedBooks, Integer> tableID;

    @FXML
    private TableColumn<EncapsulatedPurchasedBooks, String> tableName;

    @FXML
    private TableColumn<EncapsulatedPurchasedBooks, Integer> tablePrice;

    private PurchasedBooksService service = new PurchasedBooksService();
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        icon.setImage(brandingImage);

        tableID.setCellValueFactory(new PropertyValueFactory<EncapsulatedPurchasedBooks,Integer>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<EncapsulatedPurchasedBooks,String>("book"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<EncapsulatedPurchasedBooks,Integer>("price"));
        tableDay.setCellValueFactory(new PropertyValueFactory<EncapsulatedPurchasedBooks,String>("day"));

        tableBook.setItems(FXCollections.observableArrayList(service.findByUser()));
    }

    @FXML
    private Button btnRentedBooks;
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
        Parent root = FXMLLoader.load(getClass().getResource("reader_menu.fxml"));
        Stage window = (Stage) btnDashboard.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openSecondWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("readerBook.fxml"));
        Stage window = (Stage) btnBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openThirdWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("readedBooks.fxml"));
        Stage window = (Stage) btnReadedBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openFourthWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("favoriteBooks.fxml"));
        Stage window = (Stage) btnFavoriteBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openFifthWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InformationReader.fxml"));
        Stage window = (Stage) btnInfo.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }
}
