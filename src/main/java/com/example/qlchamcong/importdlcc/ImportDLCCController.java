package com.example.qlchamcong.importdlcc;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;

import java.io.IOException;

public class ImportDLCCController {
    IActionChangeGUI navUtil;

    IPassArgument argumentUtil;

    public ImportDLCCController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.navUtil = navUtil;
        this.argumentUtil = argumentUtil;
    }

    public void returnToHome() throws IOException {
        navUtil.changeGUI("/com/example/qlchamcong/qlnshome/qlns-home.fxml");
    }
}
