package com.example.applibrary;

import com.example.applibrary.model.EncapsulatedBooks;
import com.example.applibrary.model.EncapsulatedReadedBooks;
import com.example.applibrary.service.ReadedBooksService;
import interfaces.buttonsOnAction;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class readedBooks implements Initializable, buttonsOnAction {
	@FXML
	private Label textMessage;

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
	private TableView<EncapsulatedReadedBooks> tableBook;

	@FXML
	private TableColumn<EncapsulatedReadedBooks, Integer> tableID;

	@FXML
	private TableColumn<EncapsulatedReadedBooks, String> tableName;

	@FXML
	private TableColumn<EncapsulatedReadedBooks, EncapsulatedReadedBooks> tableAction;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnDelete;

	@FXML
	private TextField txtBook;

	@FXML
	private ComboBox<EncapsulatedBooks> comboBook;

	private ReadedBooksService service = new ReadedBooksService();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		File brandingFile = new File("src/main/java/img/logo121.png");
		Image brandingImage = new Image(brandingFile.toURI().toString());
		icon.setImage(brandingImage);

		UpdateTable();
	}

	public void addBook() {
		if (this.comboBook.getValue() != null) {

			try {
				service.add(comboBook.getValue());
				textMessage.setText("Erfolgreich hinzugefügt!");
				textMessage.setTextFill(Color.GREEN);
				UpdateTable();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			textMessage.setText("Wähle einen Wert !");
		}
	}

	public void Delete(EncapsulatedReadedBooks book) {
		try {
			service.delete(book);
			textMessage.setText("Erfolgreich gelöscht!");
			textMessage.setTextFill(Color.GREEN);
			UpdateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	public void UpdateTable() {
		// Show readed books
		tableID.setCellValueFactory(new PropertyValueFactory<EncapsulatedReadedBooks, Integer>("id"));
		tableName.setCellValueFactory(new PropertyValueFactory<EncapsulatedReadedBooks, String>("book"));
		tableAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableAction.setCellFactory(param -> new TableCell<EncapsulatedReadedBooks, EncapsulatedReadedBooks>() {
			private final Button deleteButton = new Button("Löschen");

			@Override
			protected void updateItem(EncapsulatedReadedBooks book, boolean empty) {
				super.updateItem(book, empty);

				if (book == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);

				deleteButton.setOnAction(event -> {
					Delete(book);
					UpdateTable();
					//getTableView().getItems().remove(book);
				});
			}
		});

		comboBook.setItems(FXCollections.observableList(service.findBooksDontReadedByUser()));
		tableBook.setItems(FXCollections.observableList(service.findByUser()));
	}

	@FXML
	private Button btnRentedBooks;
	public void rentedBooksOnAction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("rentedBooksReader.fxml"));
		Stage window = (Stage) btnRentedBooks.getScene().getWindow();
		window.setScene(new Scene(root,1000,500));
	}

	// Interface methods
	@Override
	public void closeWindow(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Stage window = (Stage) btnExit.getScene().getWindow();
		window.setScene(new Scene(root, 1000, 500));
	}

	@Override
	public void openFirstWindow(ActionEvent event) throws IOException { // Open dashboard
		Parent root = FXMLLoader.load(getClass().getResource("reader_menu.fxml"));
		Stage window = (Stage) btnDashboard.getScene().getWindow();
		window.setScene(new Scene(root, 1000, 500));
	}

	@Override
	public void openSecondWindow(ActionEvent event) throws IOException { // Open list of books
		Parent root = FXMLLoader.load(getClass().getResource("readerBook.fxml"));
		Stage window = (Stage) btnBooks.getScene().getWindow();
		window.setScene(new Scene(root, 1000, 500));
	}

	@Override
	public void openThirdWindow(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("favoriteBooks.fxml"));
		Stage window = (Stage) btnFavoriteBooks.getScene().getWindow();
		window.setScene(new Scene(root, 1000, 500));
	}

	@Override
	public void openFourthWindow(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("purchasedBooks.fxml"));
		Stage window = (Stage) btnPurchasedBooks.getScene().getWindow();
		window.setScene(new Scene(root, 1000, 500));
	}

	@Override
	public void openFifthWindow(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("InformationReader.fxml"));
		Stage window = (Stage) btnInfo.getScene().getWindow();
		window.setScene(new Scene(root, 1000, 500));
	}

	// getters and setters
	public ReadedBooksService getService() {
		return service;
	}

	public void setService(ReadedBooksService service) {
		this.service = service;
	}

}
