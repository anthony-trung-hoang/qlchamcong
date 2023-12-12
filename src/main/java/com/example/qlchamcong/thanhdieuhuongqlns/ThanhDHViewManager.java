package com.example.qlchamcong.thanhdieuhuongqlns;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.NavigationManager;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private ThanhDHController thanhDHController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NavigationManager.getInstance().setContentPane(contentPane);
        IActionChangeGUI navigationUtil = new NavigationUtil();
        thanhDHController = new ThanhDHController(navigationUtil);
    }

    @FXML
    public void handleImportDLCCButtonAction() throws IOException {
        thanhDHController.toAttendanceTrackingImportScreen();
    }

    @FXML
    public void handleHomeButtonAction() throws IOException {
        thanhDHController.toHomeScreen();
    }

}
