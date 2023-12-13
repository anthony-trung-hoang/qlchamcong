package com.example.qlchamcong.qlnshome;

import com.example.qlchamcong.HelloApplication;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class QLNSHomeViewManager implements Initializable {
    private QLNSHomeController qlnsHomeController;

    @FXML
    private Label homePageText;

    @FXML
    private Button suaBGCC;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        qlnsHomeController = new QLNSHomeController();
        fetchAndDisplayTableData();
    }

    @FXML
    protected void handleSuaBGCCAction() {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/qlchamcong/dangnhap/dang-nhap.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fetchAndDisplayTableData () {
        List<WorkerAttendanceData> workerAttendanceDataList = qlnsHomeController.fetchTableData();
    }
}
