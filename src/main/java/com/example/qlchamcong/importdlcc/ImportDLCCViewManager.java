package com.example.qlchamcong.importdlcc;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportDLCCViewManager implements Initializable {
    private ImportDLCCController importDLCCController;
    @FXML
    private Button returnHomePage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        importDLCCController = new ImportDLCCController(navUtil);
    }

    @FXML
    protected void onReturnHomeButtonAction() throws IOException {
        importDLCCController.handleReturnToHome();
    }

}
