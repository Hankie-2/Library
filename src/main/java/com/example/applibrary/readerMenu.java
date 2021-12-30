package com.example.applibrary;

import com.example.applibrary.helper.DBConnection;
import com.example.applibrary.helper.GeneralData;
import interfaces.buttonsOnAction;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class readerMenu implements Initializable, buttonsOnAction {

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
    private ImageView icon;

    @FXML
    private ImageView imageDollar;

    @FXML
    private Label labelBalance;

    @FXML
    private Label lblYourLogin;

    @FXML
    private Label lblYourName;

    @FXML
    private Label lblYourName1;

    @FXML
    private Label lblYourPasswort;

    @FXML
    private Label lblYourSurname;

    @FXML
    private Label lblWelcome;

    @FXML
    private PieChart pieChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        icon.setImage(brandingImage);

        File imagedollarFile = new File("src/main/java/img/dollar.png");
        Image dollarImage = new Image(imagedollarFile.toURI().toString());
        imageDollar.setImage(dollarImage);

        File imagetimeFile = new File("src/main/java/img/time.png");
        Image timeImage = new Image(imagetimeFile.toURI().toString());
        imageDollar.setImage(timeImage);

        dashBoardStats();
    }


    public void dashBoardStats() {
        lblWelcome.setText("Welcome to Librarium: " + GeneralData.getLoggedUser().getName() + " " + GeneralData.getLoggedUser().getSurname());
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            PreparedStatement statement = connectDB.prepareStatement(
                    "SELECT Ewallet from users WHERE Login='" + GeneralData.getLoggedUser().getLogin() + "'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                labelBalance.setText(resultSet.getString("Ewallet"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //label information

        lblYourLogin.setText(GeneralData.getLoggedUser().getLogin());
        lblYourName.setText(GeneralData.getLoggedUser().getName());
        lblYourPasswort.setText(GeneralData.getLoggedUser().getPassword());
        lblYourSurname.setText(GeneralData.getLoggedUser().getSurname());


        //PieChart
        String login = GeneralData.getLoggedUser().getLogin();

        //get count of book
        Integer soldBook = 0;
        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT COUNT(*) AS COUNT FROM soldbooks WHERE UserLogin = '" + login + "'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                soldBook = Integer.parseInt(resultSet.getString("COUNT"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        Integer readBook = 0;
        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT COUNT(*) AS COUNT FROM readbooks WHERE UserLogin = '" + login + "'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                readBook = Integer.parseInt(resultSet.getString("COUNT"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        Integer favoriteBook = 0;
        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT COUNT(*) AS COUNT FROM favoritebooks WHERE UserLogin = '" + login + "'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                favoriteBook = Integer.parseInt(resultSet.getString("COUNT"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        Integer rentedBook = 0;
        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT COUNT(*) AS COUNT FROM favoritebooks WHERE UserLogin = '" + login + "'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                rentedBook = Integer.parseInt(resultSet.getString("COUNT"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Gekauften Bücher",soldBook),
                new PieChart.Data("Lieblingsbücher",favoriteBook),
                new PieChart.Data("Ausgeliehene Bücher",rentedBook),
                new PieChart.Data("Gelesene Bücher",readBook));

        pieData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName() , " " , data.pieValueProperty()
                        )
                ));
        pieChart.getData().addAll(pieData);
    }


    @FXML
    private Button btnRentedBooks;
    public void rentedBooksOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("rentedBooksReader.fxml"));
        Stage window = (Stage) btnRentedBooks.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    //Interface methods

    @Override
    public void closeWindow(ActionEvent event) throws IOException { // Close Window
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) btnExit.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }

    @Override
    public void openFirstWindow(ActionEvent event) throws IOException { // Open book Window
        Parent root = FXMLLoader.load(getClass().getResource("readerBook.fxml"));
        Stage window = (Stage) btnBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openSecondWindow(ActionEvent event) throws IOException { // Open list of readed books
        Parent root = FXMLLoader.load(getClass().getResource("readedBooks.fxml"));
        Stage window = (Stage) btnReadedBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openThirdWindow(ActionEvent event) throws IOException { // Open list of favourite books
        Parent root = FXMLLoader.load(getClass().getResource("favoriteBooks.fxml"));
        Stage window = (Stage) btnFavoriteBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openFourthWindow(ActionEvent event) throws IOException { // List of purchased books
        Parent root = FXMLLoader.load(getClass().getResource("purchasedBooks.fxml"));
        Stage window = (Stage) btnPurchasedBooks.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }

    @Override
    public void openFifthWindow(ActionEvent event) throws IOException{ // InformationReader about program
        Parent root = FXMLLoader.load(getClass().getResource("InformationReader.fxml"));
        Stage window = (Stage) btnInfo.getScene().getWindow();
        window.setScene(new Scene(root, 1000, 500));
    }
}