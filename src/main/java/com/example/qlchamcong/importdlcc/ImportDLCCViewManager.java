package com.example.qlchamcong.importdlcc;

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
        importDLCCController = new ImportDLCCController();
    }

    @FXML
    protected void onReturnHomeButtonAction() throws IOException {
        importDLCCController.handleReturnToHome();
    }

}
