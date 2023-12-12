package com.example.qlchamcong.thanhdieuhuongqlns;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThanhDHViewManager implements Initializable {

    @FXML
    public Pane contentPane;
    @FXML
    public Button homePage;
    @FXML
    public Button importDLCC;
    @FXML
    public Button dangXuat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void addImportCCButtonAction(EventHandler<ActionEvent> event) {
        importDLCC.setOnAction(event);
    }

    public void addHomePageButtonAction(EventHandler<ActionEvent> event) throws IOException {
        homePage.setOnAction(event);
    }

    public void displayContentPane(Parent root) {
        contentPane.getChildren().setAll(root);
    }
}
