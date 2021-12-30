package interfaces;

import javafx.event.ActionEvent;

import java.io.IOException;

public interface buttonsOnAction {

    void closeWindow(ActionEvent event) throws IOException;

    void openFirstWindow(ActionEvent event) throws IOException;

    void openSecondWindow(ActionEvent event) throws IOException;

    void openThirdWindow(ActionEvent event) throws IOException;

    void openFourthWindow(ActionEvent event) throws IOException;

    void openFifthWindow(ActionEvent event) throws IOException;


}
