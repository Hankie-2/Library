package com.example.applibrary;

import com.example.applibrary.helper.DBConnection;
import com.example.applibrary.helper.GeneralData;
import com.example.applibrary.model.EncapsulatedUsers;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.accType;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class login implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label checkBoxLabel;

    @FXML
    private Button btn_enter;

    @FXML
    private PasswordField field_password;

    @FXML
    private TextField field_username;

    @FXML
    private ImageView brandingImageView;

    @FXML
    private Label loginMessage;

    // Password encapsulation
    private String login;
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    DBConnection connection = new DBConnection();
    Connection conn = connection.getConnection();

    // Method for pictures initialization
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
    }

    // Method if registration button is pushed
    public void registrationButtomPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("registration.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    // Method if login button is pushed
    public void loginButtonOnAction(ActionEvent event) throws IOException{
        try {
            if (this.field_username.getText().isBlank() == false && this.field_password.getText().isBlank() == false) {
                Integer type = validateLogin();

                if (type.equals(accType.LIBRARIAN.number)) {
                    Parent parent = FXMLLoader.load(getClass().getResource("librarianMenu.fxml"));
                    Scene scene = new Scene(parent);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();


                    window.setScene(scene);
                    window.show();
                } else if (type.equals(accType.READER.number)) {
                    Parent parent = FXMLLoader.load(getClass().getResource("reader_menu.fxml"));
                    Scene scene = new Scene(parent);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();


                    //========Amount of logins==================
                    Statement statement = conn.createStatement();
                    ResultSet getDate = statement.executeQuery("SELECT current_date() = date from amountoflogins");

                    while(getDate.next()){
                        if(getDate.getInt(1)==1){
                            PreparedStatement countLogin = conn.prepareStatement("UPDATE amountoflogins SET Amount = Amount + 1 WHERE id = 1;");
                            countLogin.execute();
                        }
                        else if(getDate.getInt(1)==0){
                            PreparedStatement dropValue = conn.prepareStatement("UPDATE amountoflogins SET Amount = 1 WHERE id = 1");
                            dropValue.execute();

                            PreparedStatement setCurrentDay = conn.prepareStatement("UPDATE amountoflogins SET date = current_date() WHERE id = 1");
                            setCurrentDay.execute();
                        }
                    }
                    //========Amount of logins==================

                    window.setScene(scene);
                    window.show();
                } else {
                    System.out.println("Nothing happened!");
                }
            } else {
                loginMessage.setText("Bitte Benutzername und Passwort eingeben");
            }
        }catch (Exception e){
            System.out.println("Incorrect login or password!");
        }
    }

    static MediaPlayer mediaPlayer;
    public static void welcomeAudio(){
        String audio = "C:\\Users\\User\\Desktop\\AppLibrary\\src\\main\\java\\welcomeAudio\\welcome.mp3";
        Media media = new Media(Paths.get(audio).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }

    // Method for entering the program
    private Integer validateLogin() throws IOException {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "select * from users where Login='" + this.field_username.getText() + "' AND Password ='"
                + this.field_password.getText() + "'";
        String verifyLoginCount = "select COUNT(1) from users where Login='" + this.field_username.getText() + "' AND Password ='" + this.field_password.getText() +"'";


        setPassword(field_password.getText());
        setLogin(field_username.getText());

        System.out.println(getLogin());
        try {
            Statement statement = connectDB.createStatement();
            Statement statementCount = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            ResultSet queryResultCount = statementCount.executeQuery(verifyLoginCount);

            while(queryResultCount.next()) {

                if(queryResultCount.getInt(1)==1) {
                    while (queryResult.next()) {

                        EncapsulatedUsers user = new EncapsulatedUsers();
                        user.setId(queryResult.getInt("id"));
                        user.setAccType(queryResult.getInt("accType"));
                        user.setLogin(queryResult.getString("Login"));
                        user.setName(queryResult.getString("Name"));
                        user.setPassword(queryResult.getString("Password"));
                        user.setSurname(queryResult.getString("Surname"));
                        user.setWallet(queryResult.getInt("Ewallet"));

                        GeneralData.logUser(user);

                        welcomeAudio();
                        loginMessage.setText("Erfolgreiche Anmeldung!");
                        loginMessage.setTextFill(Color.GREEN);
                        return user.getAccType();
                    }
                }else{
                    loginMessage.setText("Ungültiger Login. Bitte versuche es erneut!");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return null;
    }

}


