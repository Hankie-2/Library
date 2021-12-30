package com.example.applibrary;

import com.example.applibrary.helper.DBConnection;
import com.example.applibrary.helper.GeneralData;
import interfaces.buttonsOnAction;
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

public class librarianMenu implements Initializable, buttonsOnAction {

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
    private Label labelAmountLogin;

    @FXML
    private Label labelEarnedMoney;

    @FXML
    private PieChart pieChart;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnReaders;

    @FXML
    private Button btnRentedBooks;

    @FXML
    private ImageView icon;

    @FXML
    private ImageView imageBook;

    @FXML
    private ImageView imageInput;

    @FXML
    private ImageView imageDollar;

    @FXML
    private ImageView imageDollar1;

    @FXML
    private ImageView imageTime;

    @FXML
    private ImageView imageUsers;

    @FXML
    private ImageView imageFavorite;

    @FXML
    private ImageView imageFrequently;

    @FXML
    private Label labelTotalBook;

    @FXML
    private Label labelTotalRendedBook;

    @FXML
    private Label labelTotalSoldBook;

    @FXML
    private Label labelTotalUser;

    @FXML
    private Label labelFavorite;

    @FXML
    private Label labelFrequently;

    @FXML
    private Label lblWelcome;



    //Method to initialize puctures
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        icon.setImage(brandingImage);

        File imagebookFile = new File("src/main/java/img/book.png");
        Image bookImage = new Image(imagebookFile.toURI().toString());
        imageBook.setImage(bookImage);

        File imagegroupFile = new File("src/main/java/img/group.png");
        Image groupImage = new Image(imagegroupFile.toURI().toString());
        imageUsers.setImage(groupImage);

        File imagedollarFile = new File("src/main/java/img/dollar.png");
        Image dollarImage = new Image(imagedollarFile.toURI().toString());
        imageDollar.setImage(dollarImage);

        File imagedollarFile2 = new File("src/main/java/img/dollar.png");
        Image dollarImage2 = new Image(imagedollarFile2.toURI().toString());
        imageDollar1.setImage(dollarImage2);

        File imagetimeFile = new File("src/main/java/img/time.png");
        Image timeImage = new Image(imagetimeFile.toURI().toString());
        imageTime.setImage(timeImage);

        File imageInputFile = new File("src/main/java/img/input.png");
        Image inputImage = new Image(imageInputFile.toURI().toString());
        imageInput.setImage(inputImage);

        File imageFavoriteFile = new File("src/main/java/img/favorite.png");
        Image favoriteImage = new Image(imageFavoriteFile.toURI().toString());
        imageFavorite.setImage(favoriteImage);

        File imageFrequentlyFile = new File("src/main/java/img/selled.png");
        Image frequentlyImage = new Image(imageFrequentlyFile.toURI().toString());
        imageFrequently.setImage(frequentlyImage);

        //Initialize Dashboard Statistics
        dashBoardStats();
    }
    //Main dashboard
    private void dashBoardStats(){
        lblWelcome.setText("Welcome to Librarium: " + GeneralData.getLoggedUser().getName() + " " + GeneralData.getLoggedUser().getSurname());
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();

        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT COUNT(*) FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                labelTotalUser.setText(resultSet.getString("COUNT(*)"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT COUNT(*) FROM soldbooks");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                labelTotalSoldBook.setText(resultSet.getString("COUNT(*)"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT SUM(Amount) from library");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                labelTotalBook.setText(resultSet.getString("SUM(Amount)"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT COUNT(*) FROM rentedbooks");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                labelTotalRendedBook.setText(resultSet.getString("COUNT(*)"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT amount FROM amountoflogins");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                labelAmountLogin.setText(resultSet.getString("amount"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT SUM(SellCost) FROM library ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                labelEarnedMoney.setText(resultSet.getString("SUM(SellCost)"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT BookName FROM soldbooks \n" +
                    "GROUP BY BookName ORDER BY BookName DESC LIMIT 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                labelFrequently.setText(resultSet.getString("BookName"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            PreparedStatement statement = connectDB.prepareStatement("SELECT BookName FROM favoritebooks \n" +
                    "GROUP BY BookName ORDER BY BookName DESC LIMIT 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                labelFavorite.setText(resultSet.getString("BookName"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

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
