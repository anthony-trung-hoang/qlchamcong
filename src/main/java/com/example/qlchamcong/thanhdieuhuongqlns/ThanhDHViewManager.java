package com.example.qlchamcong.thanhdieuhuongqlns;

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

    private IQLNSThanhDieuHuongDoiManHinh thanhDieuHuongController;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thanhDieuHuongController = new IQLNSThanhDieuHuongDoiManHinh() {
            @Override
            public void changeGUI(String viewResource) throws IOException {
                NavigationManager.getInstance().setContentPane(contentPane);

                NavigationManager.getInstance().changeGUI(viewResource);
            }
        };
    }

    @FXML
    public void handleImportDLCCButtonAction() throws IOException {
        thanhDieuHuongController.changeGUI("/com/example/qlchamcong/importdlcc/import-dlcc.fxml");
    }

    @FXML
    public void handleHomeButtonAction() throws IOException
    {
        thanhDieuHuongController.changeGUI("/com/example/qlchamcong/qlnshome/qlns-home.fxml");
    }

}
