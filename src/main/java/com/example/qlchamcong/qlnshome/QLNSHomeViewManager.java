package com.example.qlchamcong.qlnshome;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.changeGUIUtility.PassArgumentUtil;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class QLNSHomeViewManager implements Initializable {
    private QLNSHomeController qlnsHomeController;

    @FXML
    private TableView<WorkerAttendanceData> attendanceTableView;

    @FXML
    private TableColumn<WorkerAttendanceData, Integer> idColumn;

    @FXML
    private TableColumn<WorkerAttendanceData, Integer> employeeIdColumn;

    @FXML
    private TableColumn<WorkerAttendanceData, Date> dateColumn;

    @FXML
    private TableColumn<WorkerAttendanceData, Double> hoursShift1Column;

    @FXML
    private TableColumn<WorkerAttendanceData, Double> hoursShift2Column;

    @FXML
    private TableColumn<WorkerAttendanceData, Double> hoursShift3Column;

    @FXML
    private TableColumn<WorkerAttendanceData, Void> viewDetailsColumn;


    @FXML
    private Label homePageText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        qlnsHomeController = new QLNSHomeController(navUtil, argumentUtil);

        fetchAndDisplayTableData();
    }

    public void fetchAndDisplayTableData() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        employeeIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEmployeeId()).asObject());
        dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        hoursShift1Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHoursShift1()).asObject());
        hoursShift2Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHoursShift2()).asObject());
        hoursShift3Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHoursShift3()).asObject());

        viewDetailsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button viewDetailsButton = new Button("Xem Chi Tiết");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(viewDetailsButton);
                    viewDetailsButton.setOnAction(event -> {
                        try {
                            handleViewDetailsAction(getTableView().getItems().get(getIndex()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        });

        List<WorkerAttendanceData> workerAttendanceDataList = qlnsHomeController.fetchTableData();
        System.out.println(workerAttendanceDataList.size());
        ObservableList<WorkerAttendanceData> observableList = FXCollections.observableArrayList(workerAttendanceDataList);
        attendanceTableView.setItems(observableList);

    }

    private void handleViewDetailsAction(WorkerAttendanceData workerAttendanceData) throws IOException {
        System.out.println("Xem chi tiết cho nhân viên: " + workerAttendanceData.getId());
        qlnsHomeController.showDetails(workerAttendanceData.getId());
    }
}
