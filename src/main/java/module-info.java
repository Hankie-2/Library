module com.example.applibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
	requires javafx.base;
    requires javafx.media;


    //applibrary
    opens com.example.applibrary to javafx.fxml;
    exports com.example.applibrary;
    
    //interfaces
    exports interfaces;
    opens interfaces to javafx.fxml;
    
    //model
    opens com.example.applibrary.model to javafx.base;
    
}