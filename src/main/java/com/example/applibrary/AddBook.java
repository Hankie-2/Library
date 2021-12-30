package com.example.applibrary;

import com.example.applibrary.helper.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class AddBook implements Initializable
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBuy;

    @FXML
    private ImageView icon;

    @FXML
    private Label messageLabel;

    @FXML
    private Label messageLabelMoney;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtRentCost;

    @FXML
    private TextField txtSellCost;

    @FXML
    public void addBook() {
        if(this.txtBookName.getText().isBlank()==false & this.txtAmount.getText().isBlank()==false & this.txtAuthor.getText().isBlank()==false & this.txtDate.getText().isBlank()==false & this.txtRentCost.getText().isBlank()==false & this.txtSellCost.getText().isBlank()==false){
            DBConnection connection = new DBConnection();
            Connection conn = connection.getConnection();

            String sql = "INSERT INTO library (BookName,Author,PublishingDate,SellCost,RentCost,Amount) VALUES ('" + this.txtBookName.getText() +"','" + this.txtAuthor.getText() + "','" + this.txtDate.getText() + "','" + this.txtSellCost.getText() + "','" + this.txtRentCost.getText() + "','" + this.txtAmount.getText() + "')";

            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.execute();
                messageLabel.setText("Erfolgreich hinzugefügt!");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            messageLabel.setText("Füllen Sie alle Felder aus!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        javafx.scene.image.Image brandingImage = new Image(brandingFile.toURI().toString());
        icon.setImage(brandingImage);
    }
}
