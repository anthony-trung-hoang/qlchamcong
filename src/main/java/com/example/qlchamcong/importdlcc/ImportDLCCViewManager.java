package com.example.qlchamcong.importdlcc;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.changeGUIUtility.PassArgumentUtil;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportDLCCViewManager implements Initializable {
    ImportDLCCController importDLCCController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        importDLCCController = new ImportDLCCController(navUtil, argumentUtil);
    }

    public void onReturnHomeButtonAction() throws IOException {
        importDLCCController.returnToHome();
    }
}
