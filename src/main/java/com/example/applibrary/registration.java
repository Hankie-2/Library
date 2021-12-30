package com.example.applibrary;

import com.example.applibrary.helper.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class registration implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_enter;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private PasswordField field_confirmPassword;

    @FXML
    private TextField field_name;

    @FXML
    private PasswordField field_password;

    @FXML
    private TextField field_surname;

    @FXML
    private TextField field_username;

    @FXML
    private ImageView imageView;

    @FXML
    private Label registrationMessageLabel;

    //Method for initialize pictures and so on
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        imageView.setImage(brandingImage);
    }

    public void buttonBackOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void enterButtonOnAction(ActionEvent event){
        if(field_password.getText().equals(field_confirmPassword.getText())){
            registerUser();
            confirmPasswordLabel.setText("");
        }else{
            confirmPasswordLabel.setText("Passwort stimmt nicht überein!");
        }
    }


    public void registerUser(){
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();


        //Data entry fields
        String firstName = field_name.getText();
        String lastName = field_surname.getText();
        String username = field_username.getText();
        String password = field_password.getText();
        int wallet = 1000;
        int accType=1;

        //Requests for adding data to DB
        String insertFields = "INSERT INTO users(accType,Name,Surname,Login,Ewallet,Password) VALUES ('";
        String insertValues = accType +"','"+firstName +"','"+ lastName +"','"+ username +"','"+ wallet +"','"+ password+"')";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registrationMessageLabel.setText("Erfolgreiche Registrierung!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }
}
