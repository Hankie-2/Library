package com.example.applibrary;

import com.example.applibrary.helper.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RentBook implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBuy;

    @FXML
    private ImageView icon;

    @FXML
    private Label messageLabelBook;

    @FXML
    private Label messageLabelLogin;

    @FXML
    private Label messageLabelResult;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtLogin;

    @FXML
    void rentBook() throws SQLException {
        if (this.txtBookName.getText().isBlank() == false & this.txtLogin.getText().isBlank() == false) {
            DBConnection connection = new DBConnection();
            Connection conn = connection.getConnection();
            Statement stateCountBook = conn.createStatement();
            Statement stateCountLogin = conn.createStatement();

            String sqlInsertRentedBooks = "INSERT INTO rentedbooks (UserLogin,BookName,RentCost,StartDate,ExpirationDate) \n" +
                    "VALUES('" + this.txtLogin.getText() + "','" + this.txtBookName.getText() + "',(SELECT RentCost from library WHERE BookName LIKE '" + this.txtBookName.getText() + "'),current_date(), date_add(current_date(), INTERVAL 5 DAY));";
            String sqlTakeMoney = "UPDATE users SET Ewallet = Ewallet-(SELECT RentCost FROM library WHERE BookName = '" + this.txtBookName.getText() + "') WHERE Login = '" + this.txtLogin.getText() + "';";

            ResultSet isBookExists = stateCountBook.executeQuery("SELECT COUNT(*) FROM library WHERE BookName LIKE '" + this.txtBookName.getText() + "'");
            ResultSet isLoginExists = stateCountLogin.executeQuery("SELECT COUNT(*) FROM users WHERE Login LIKE '" + this.txtLogin.getText() + "';");

            Statement statementCheckMoney = conn.createStatement();
            ResultSet isMoneyHave = statementCheckMoney.executeQuery("SELECT (SELECT Ewallet from users WHERE Login LIKE '"+ this.txtLogin.getText() +"') > (SELECT RentCost FROM library WHERE BookName LIKE '" + this.txtBookName.getText()+ "')");

            while (isLoginExists.next()) {
                if (isLoginExists.getInt(1) == 1) {

                    while (isBookExists.next()) {
                        if (isBookExists.getInt(1) == 1) {

                            while(isMoneyHave.next()) {
                                if(isMoneyHave.getInt(1) == 1) {

                                    try {
                                        PreparedStatement statement = conn.prepareStatement(sqlInsertRentedBooks);
                                        statement.execute();
                                        PreparedStatement statement1 = conn.prepareStatement(sqlTakeMoney);
                                        statement1.execute();
                                        PreparedStatement statement2 = conn.prepareStatement("UPDATE library SET Amount = Amount - 1 WHERE BookName LIKE '" + this.txtBookName.getText() + "'");
                                        statement2.execute();

                                        messageLabelResult.setText("Erfolgreich hinzugefügt!");
                                        messageLabelResult.setTextFill(Color.GREEN);
                                        messageLabelBook.setText("");
                                        messageLabelLogin.setText("");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    messageLabelResult.setText("Давай заново!");
                                }
                            }
                        } else {
                            messageLabelBook.setText("Es gibt kein solches Buch!");
                            messageLabelLogin.setText("");
                        }
                    }
                } else {
                    messageLabelLogin.setText("Kein solcher Benutzer!");
                    messageLabelBook.setText("");
                }
            }
        } else {
            messageLabelResult.setText("Schreiben Sie den Titel des Buches oder den Benutzernamen!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        javafx.scene.image.Image brandingImage = new Image(brandingFile.toURI().toString());
        icon.setImage(brandingImage);
    }
}
