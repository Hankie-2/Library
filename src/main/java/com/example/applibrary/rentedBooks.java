package com.example.applibrary;


import com.example.applibrary.helper.DBConnection;
import com.example.applibrary.model.EncapsulatedRentedBooks;
import com.example.applibrary.service.RentedBooksService;
import interfaces.buttonsOnAction;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class rentedBooks implements Initializable, buttonsOnAction {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label textMessage;

    @FXML
    private Button btnBookReserve;

    @FXML
    private Button btnBookSearch;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnInfo;

    @FXML
    private Button rentBook;

    @FXML
    private Button btnReaders;

    @FXML
    private Button btnRentedBooks;

    @FXML
    private Button btnBooks;

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
    private TableColumn<EncapsulatedRentedBooks, String> tableLogin;

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

    public void Delete(EncapsulatedRentedBooks book) {
        try {
            service.delete(book);
            textMessage.setText("Erfolgreich gelöscht!");
            textMessage.setTextFill(Color.GREEN);

            DBConnection connection = new DBConnection();
            Connection conn = connection.getConnection();
            PreparedStatement sql = conn.prepareStatement("UPDATE library SET Amount = Amount + 1 WHERE BookName LIKE '" + book.getName() +"'");
            sql.execute();

            UpdateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void UpdateTable() {

        tableID.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, Integer>("id"));
        tableLogin.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, String>("login"));
        tableBook.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, String>("name"));
        tableCost.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, Integer>("rentCost"));
        tableStart.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, String>("startDate"));
        tableEnd.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, String>("expirationDate"));

        data = FXCollections.observableArrayList(service.findAll());
        tableRentedBooks.setItems(data);

        tableAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableAction.setCellFactory(param -> new TableCell<EncapsulatedRentedBooks, EncapsulatedRentedBooks>() {
            private final Button deleteButton = new Button("Löschen");

            public void UpdateTable() {
                // Show readed books
                tableID.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, Integer>("id"));
                tableLogin.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, String>("login"));
                tableBook.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, String>("name"));
                tableCost.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, Integer>("rentCost"));
                tableStart.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, String>("startDate"));
                tableEnd.setCellValueFactory(new PropertyValueFactory<EncapsulatedRentedBooks, String>("expirationDate"));
                tableAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
                tableAction.setCellFactory(param -> new TableCell<EncapsulatedRentedBooks, EncapsulatedRentedBooks>() {
                    private final Button deleteButton = new Button("Löschen");


                    @Override
                    protected void updateItem(EncapsulatedRentedBooks book, boolean empty) {
                        super.updateItem(book, empty);

                        if (book == null) {
                            setGraphic(null);
                            return;
                        }

                        setGraphic(deleteButton);

                        deleteButton.setOnAction(event -> {
                            Delete(book);
                            UpdateTable();
                        });
                    }
                });

                tableRentedBooks.setItems(FXCollections.observableList(service.findAll()));
            }

            @Override
            protected void updateItem(EncapsulatedRentedBooks book, boolean empty) {
                super.updateItem(book, empty);

                if (book == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);

                deleteButton.setOnAction(event -> {
                    Delete(book);
                    UpdateTable();
                });
            }
        });

        tableRentedBooks.setItems(FXCollections.observableList(service.findAll()));
    }

    public void rentButtonOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RentBook.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Buch ausleihen");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
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
}
