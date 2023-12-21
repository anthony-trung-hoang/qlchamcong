package com.example.qlchamcong.viewdepartmentlist;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.passaargumentutility.PassArgumentUtil;
import com.example.qlchamcong.viewattendancerecord.AttendanceRecordController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentListViewManager implements Initializable {
    @FXML
    public TableView<Department> departmentListTableView;
    @FXML

    public TableColumn<Department, Integer> departmentId;

    @FXML
    public TableColumn<Department, String> departmentName;
    @FXML
    public TableColumn<Department, Date> founding;
    @FXML
    public TableColumn<Department, String> numberStaff;
    @FXML
    public TableColumn<Department, String> leader;
    @FXML
    public TableColumn<Department, Void> viewReportColumn;
    //    @FXML
//    public Label pageTitle;
    private DepartmentListController departmentListController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        departmentListController = new DepartmentListController(navUtil, argumentUtil);
        fetchAndDisplayTableData();
    }
    public void fetchAndDisplayTableData() {
        departmentId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        departmentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        founding.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFounding()));
        numberStaff.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTotalEmployee()).asObject().asString());
        leader.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        viewReportColumn.setCellFactory(param -> new TableCell<>() {
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

        List<Department> departmentList = departmentListController.fetchListOfDepartment();
        System.out.println(Arrays.toString(departmentList.toArray()));
        ObservableList<Department> observableList = FXCollections.observableArrayList(departmentList);
        departmentListTableView.setItems(observableList);
    }
    private void handleViewDetailsAction(Department department) throws IOException {
        System.out.println(department.getId());
        departmentListController.showReportAttendance(department);
//        qlnsHomeController.showDetails(workerAttendanceData);
    }
}
