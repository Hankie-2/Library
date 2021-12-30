package com.example.applibrary;

import interfaces.buttonsOnAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class InformationLibrarian implements Initializable,buttonsOnAction {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnReaders;

    @FXML
    private Button btnBooks;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnDashboard1;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnInfo;

    @FXML
    private ImageView emirlanLOH;

    @FXML
    private Hyperlink hyperLink;

    @FXML
    private ImageView kamila;

    @FXML
    private ImageView librarium;

    @FXML
    private ImageView marsel;

    @FXML
    private ImageView saadat;

    @FXML
    private ImageView icon;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        librarium.setImage(brandingImage);

        File iconFile = new File("src/main/java/img/logo121.png");
        Image iconImage = new Image(iconFile.toURI().toString());
        icon.setImage(iconImage);

        File saadatFile = new File("C:\\Users\\User\\Desktop\\Скрины\\11111.png");
        Image saadatImage = new Image(saadatFile.toURI().toString());
        saadat.setImage(saadatImage);

        File kamilaFile = new File("C:\\Users\\User\\Desktop\\Скрины\\222222.png");
        Image kamilaImage = new Image(kamilaFile.toURI().toString());
        kamila.setImage(kamilaImage);

        File emirlanFile = new File("C:\\Users\\User\\Desktop\\Скрины\\333333.png");
        Image emirlanImage = new Image(emirlanFile.toURI().toString());
        emirlanLOH.setImage(emirlanImage);

        File marselFile = new File("C:\\Users\\User\\Desktop\\Скрины\\44444.png");
        Image marselImage = new Image(marselFile.toURI().toString());
        marsel.setImage(marselImage);
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
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openFirstWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("librarianMenu.fxml"));
        Stage window = (Stage) btnDashboard.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openSecondWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("readersMenu.fxml"));
        Stage window = (Stage) btnReaders.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openThirdWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("rentedBooks.fxml"));
        Stage window = (Stage) btnRentedBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openFourthWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("librarianBook.fxml"));
        Stage window = (Stage) btnBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openFifthWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InformationLibrarian.fxml"));
        Stage window = (Stage) btnInfo.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    public void openLink(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("http://127.0.0.1:5500/index.html"));
    }


}
