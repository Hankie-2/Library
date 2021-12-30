package com.example.applibrary;


import com.example.applibrary.helper.DBConnection;
import com.example.applibrary.helper.GeneralData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.print.*;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class BuyBook implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView icon;

    @FXML
    private Label messageLabel;

    @FXML
    private Label messageLabelMoney;

    @FXML
    private TextField txtName;

    @FXML
    private Button buttonBuy;

    DBConnection connection = new DBConnection();
    Connection conn = connection.getConnection();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        javafx.scene.image.Image brandingImage = new Image(brandingFile.toURI().toString());
        icon.setImage(brandingImage);
    }


    public void buyBook() throws SQLException {
        if(this.txtName.getText().isBlank()==false) {
            DBConnection connection = new DBConnection();
            Connection conn = connection.getConnection();
            String sql = "INSERT INTO soldbooks(UserLogin,BookName,SellCost,SellDay) VALUES ('"+ GeneralData.getLoggedUser().getLogin() +"','"+this.txtName.getText()+"',(SELECT SellCost FROM library WHERE BookName LIKE '"+this.txtName.getText()+"'),current_date());";

            String spendMoney = "UPDATE users SET Ewallet = Ewallet - (SELECT SellCost FROM library WHERE BookName = '"+this.txtName.getText()+"') WHERE login = '"+GeneralData.getLoggedUser().getLogin()+"'";

            Statement boolState = conn.createStatement();
            ResultSet boolRes = boolState.executeQuery("SELECT (SELECT Ewallet from users WHERE Login LIKE '"+GeneralData.getLoggedUser().getLogin()+"') > (SELECT SellCost from library WHERE BookName LIKE '"+this.txtName.getText()+"')");

            Statement stateIsBookHave = conn.createStatement();
            ResultSet isBookHave = stateIsBookHave.executeQuery("SELECT Amount FROM library WHERE BookName LIKE " + "'" + this.txtName.getText() + "'");
            Statement sqlStatement = conn.createStatement();
            ResultSet sqlResCount = sqlStatement.executeQuery("select COUNT(1) from library where BookName='"+this.txtName.getText()+"'");
                while (sqlResCount.next()) {
                    if (sqlResCount.getInt(1) == 1) {
                        while(isBookHave.next()) {
                            if(isBookHave.getInt(1)>0) {
                            while (boolRes.next()) {
                                if (boolRes.getInt(1) == 1) {
                                    try {
                                        int price = 0;
                                        int balance = 0;

                                        PreparedStatement priceState = conn.prepareStatement(
                                                "SELECT SellCost from library WHERE BookName = '" + this.txtName.getText() + "'");
                                        ResultSet resultSet3 = priceState.executeQuery();
                                        while (resultSet3.next()) {
                                            price = resultSet3.getInt(1);
                                            System.out.println(price);
                                        }

                                        PreparedStatement balanceState = conn.prepareStatement(
                                                "SELECT Ewallet from users WHERE Login = '" + GeneralData.getLoggedUser().getLogin() + "'");
                                        ResultSet resultSet2 = balanceState.executeQuery();
                                        while(resultSet2.next()){
                                            balance = resultSet2.getInt(1);
                                            System.out.println(balance);
                                        }


                                        PreparedStatement statement = conn.prepareStatement(sql);
                                        PreparedStatement moneyState = conn.prepareStatement(spendMoney);
                                        PreparedStatement substractionBook = conn.prepareStatement("UPDATE library\n" +
                                                "SET Amount = Amount-1\n" +
                                                "WHERE BookName LIKE '" + this.txtName.getText() + "';");
                                        statement.execute();
                                        moneyState.execute();
                                        substractionBook.execute();
                                        messageLabel.setText("Erfolgreich hinzugefügt!");
                                        messageLabel.setTextFill(Color.GREEN);

                                        try {
                                            PreparedStatement statementMoney = conn.prepareStatement(
                                                    "SELECT SellCost from library WHERE BookName = '" + this.txtName.getText() + "'");
                                            ResultSet resultSet = statementMoney.executeQuery();
                                            while (resultSet.next()) {
                                                messageLabelMoney.setText("Aus deiner Bilanze wird " + resultSet.getString("SellCost") + " som abgenommen");
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        java.util.Date obj = new Date();
                                        String date = obj.toString();

                                        String text =""+
                                                "\n           LIBRARIUM           \n\n" +
                                                "-------------------------------\n" +
                                                "   No 00000 Address Line One   \n" +
                                                " Address Maldybaeva 34B Bishkek\n" +
                                                "     +996 (702) 16-48-23       \n" +
                                                "-------------------------------\n" +
                                                date +"\n" +
                                                "Buch Name                 Preis\n" +
                                                "-------------------------------\n" +
                                                txtName.getText() + "             " + price +"\n" +
                                                "-------------------------------\n" +
                                                "Ihr Bilanz:             "+ (balance-price) + "\n" +
                                                "*******************************\n" +
                                                "DANKE IHNEN. KOMMEN SIE WIEDER \n" +
                                                "*******************************\n" +
                                                "     SOFTWARE BY: LIBRARIUM    \n" +
                                                "CONTACT: librariumcompany@gmail.com\n";
                                        BuyBook buyBook = new BuyBook();
                                        buyBook.printCard(text);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    messageLabel.setText("Sie haben nicht genug Geld!");
                                }
                            }
                        } else if(isBookHave.getInt(1)==0){
                                messageLabel.setText("Es sind keine Bücher mehr in der Bibliothek vorhanden!");
                            }
                    }
                }else {
                        messageLabel.setText("Es gibt kein solches Buch!");
                    }
            }
        }else{
            messageLabel.setText("Schreiben Sie den Name des Buches!");
        }
    }


    public static boolean printCard(final String bill ){
        final PrinterJob job = PrinterJob.getPrinterJob();

        Printable contentToPrint = new Printable(){
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {


                Graphics2D g2d = (Graphics2D) graphics;

                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                g2d.setFont(new Font("Monospaced", Font.BOLD, 10));

                String[] billz = bill.split("\n");
                int y = 15;
                //draw each String in a separate line
                for (int i = 0; i < billz.length; i++) {
                    graphics.drawString(billz[i], 5, y);
                    y = y + 15;
                }

                if (pageIndex >0){return NO_SUCH_PAGE;} //Only one page
                return PAGE_EXISTS;
            }


        }; PageFormat pageFormat = new PageFormat();
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        Paper pPaper = pageFormat.getPaper();



        pPaper.setImageableArea(0, 0, pPaper.getWidth() , pPaper.getHeight() -2);
        pageFormat.setPaper(pPaper);

        job.setPrintable(contentToPrint, pageFormat);





        try {
            job.print();

        } catch (PrinterException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }

}

