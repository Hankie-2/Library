package com.example.applibrary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;




public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        scene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("/styles.css")).toExternalForm());
        stage.setResizable(false);
        stage.setTitle("Librarium");
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.show();
    }


    public static void main(String[] args){
        launch(args);
    }
}