package com.example.qlchamcong.qlnshome;

import com.example.qlchamcong.HelloApplication;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class QLNSHomeViewManager implements Initializable {
    private QLNSHomeController qlnsHomeController;

    @FXML
    private Label homePageText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        qlnsHomeController = new QLNSHomeController();
        fetchAndDisplayTableData();
    }

    public void fetchAndDisplayTableData() {
        List<WorkerAttendanceData> workerAttendanceDataList = qlnsHomeController.fetchTableData();


    }
}
