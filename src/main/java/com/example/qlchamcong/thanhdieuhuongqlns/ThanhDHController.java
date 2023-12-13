package com.example.qlchamcong.thanhdieuhuongqlns;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;

import java.io.IOException;

public class ThanhDHController {

    private final IActionChangeGUI navUtil;

    public ThanhDHController(IActionChangeGUI navUtil) {
        this.navUtil = navUtil;
    }

    public void toHomeScreen() throws IOException {
        navUtil.changeGUI("/com/example/qlchamcong/qlnshome/qlns-home.fxml");
    }

    public void toAttendanceTrackingImportScreen() throws IOException {
        navUtil.changeGUI("/com/example/qlchamcong/importdlcc/import-dlcc.fxml");
    }


    public void setHomeScreen() throws IOException {
        navUtil.changeGUI("/com/example/qlchamcong/qlnshome/qlns-home.fxml");
    }
}
