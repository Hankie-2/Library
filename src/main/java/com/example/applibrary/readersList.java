package com.example.applibrary;

import com.example.applibrary.model.EncapsulatedUsers;
import com.example.applibrary.service.UserService;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class readersList implements Initializable, buttonsOnAction {

    @FXML
    private ImageView icon;

    @FXML
    private Button btnBookReserve;

    @FXML
    private Button btnBookSearch;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnReaders;

    @FXML
    private Button btnRentedBooks;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnBooks;

    @FXML
    private TableColumn<EncapsulatedUsers, Integer> tableID;

    @FXML
    private TableColumn<EncapsulatedUsers, String> tableLogin;

    @FXML
    private TableColumn<EncapsulatedUsers, String> tableName;

    @FXML
    private TableColumn<EncapsulatedUsers, String> tablePassword;

    @FXML
    private TableView<EncapsulatedUsers> tableReaders;

    @FXML
    private TableColumn<EncapsulatedUsers, String> tableSurname;

    @FXML
    private TableColumn<EncapsulatedUsers, Integer> tableWallet;

    @FXML
    private TextField fieldSearch;

    ObservableList<EncapsulatedUsers> data = FXCollections.observableArrayList();
    
    UserService service = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        icon.setImage(brandingImage);

        UpdateTable();
        search_user();
    }


    //Method to search EncapsulatedUsers
    @FXML
    void search_user() {
        tableID.setCellValueFactory(new PropertyValueFactory<EncapsulatedUsers,Integer>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<EncapsulatedUsers,String>("name"));
        tableSurname.setCellValueFactory(new PropertyValueFactory<EncapsulatedUsers,String>("surname"));
        tableLogin.setCellValueFactory(new PropertyValueFactory<EncapsulatedUsers,String>("login"));
        tableWallet.setCellValueFactory(new PropertyValueFactory<EncapsulatedUsers,Integer>("wallet"));
        tablePassword.setCellValueFactory(new PropertyValueFactory<EncapsulatedUsers,String>("password"));

        tableReaders.setItems(FXCollections.observableArrayList(service.findAll()));
        FilteredList<EncapsulatedUsers> filteredData = new FilteredList<>(data, b -> true);
        fieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(users -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (users.getLogin().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                } else if (users.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if (users.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (users.getSurname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<EncapsulatedUsers> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableReaders.comparatorProperty());
        tableReaders.setItems(sortedData);
    }


    public void UpdateTable(){
        data = FXCollections.observableArrayList(service.findAllNotLibrarian());
        tableReaders.setItems(data);
    }

    @Override
    public void closeWindow(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
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
