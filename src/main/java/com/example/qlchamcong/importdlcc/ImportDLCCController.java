package com.example.qlchamcong.importdlcc;

import com.example.qlchamcong.thanhdieuhuongqlns.IQLNSThanhDieuHuongDoiManHinh;
import com.example.qlchamcong.thanhdieuhuongqlns.NavigationManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ImportDLCCController {
    private IQLNSThanhDieuHuongDoiManHinh thanhDieuHuongDoiManHinh;

    public ImportDLCCController(IQLNSThanhDieuHuongDoiManHinh thanhDieuHuongDoiManHinh) {
        this.thanhDieuHuongDoiManHinh = thanhDieuHuongDoiManHinh;
    }

    public void handleReturnToHome() throws IOException {
        thanhDieuHuongDoiManHinh.changeGUI("/com/example/qlchamcong/qlnshome/qlns-home.fxml");
    }
}
