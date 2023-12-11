package com.example.qlchamcong.importdlcc;

import com.example.qlchamcong.thanhdieuhuongqlns.IQLNSThanhDieuHuongDoiManHinh;
import com.example.qlchamcong.thanhdieuhuongqlns.NavigationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
        IQLNSThanhDieuHuongDoiManHinh thanhDieuHuongDoiManHinh = new IQLNSThanhDieuHuongDoiManHinh() {
            @Override
            public void changeGUI(String viewResource) throws IOException {
                        NavigationManager.getInstance().changeGUI(viewResource);
            }
        };

        importDLCCController = new ImportDLCCController(thanhDieuHuongDoiManHinh);
    }

    @FXML
    protected void onReturnHomeButtonAction(ActionEvent event) throws IOException {
        importDLCCController.handleReturnToHome();
    }

}
