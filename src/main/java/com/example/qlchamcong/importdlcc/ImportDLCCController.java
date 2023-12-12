package com.example.qlchamcong.importdlcc;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;

import java.io.IOException;

public class ImportDLCCController {
    private final IActionChangeGUI navUtil;

    public ImportDLCCController(IActionChangeGUI navUtil) {
        this.navUtil = navUtil;
    }

    public void handleReturnToHome() throws IOException {
        navUtil.changeGUI("/com/example/qlchamcong/qlnshome/qlns-home.fxml");
    }
}
