package com.example.qlchamcong.viewreportofficeattendance;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.changeGUIUtility.PassArgumentUtil;
import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.OfficerReportAttendanceRow;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.viewdepartmentlist.DepartmentListController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReportOfficeAttendanceViewManager implements Initializable {
    public TableView<OfficerReportAttendanceRow> reportAttendanceTableView;
    public TableColumn<OfficerReportAttendanceRow, Integer> employeeId;
    public TableColumn<OfficerReportAttendanceRow, String> employeeName;
    public TableColumn<OfficerReportAttendanceRow, String> departmentName;
    public TableColumn<OfficerReportAttendanceRow, String> month;
    public TableColumn<OfficerReportAttendanceRow, Double> totalWorkSession;
    public TableColumn<OfficerReportAttendanceRow, Double> totalHoursLate;
    public TableColumn<OfficerReportAttendanceRow, Double> totalHoursLeavingEarly;

    private ReportOfficeAttendanceController reportOfficeAttendanceController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        reportOfficeAttendanceController = new ReportOfficeAttendanceController(navUtil, argumentUtil);
        Department departmentCurrent = getInitialData();
        System.out.println(departmentCurrent.getName());
        fetchAndDisplayTableData(departmentCurrent.getId(),new Date());
    }

    private void fetchAndDisplayTableData(int id, Date date) {
//        employeeId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEmployeeId()).asObject());
//        employeeName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeName()));
//        departmentName.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDepartmentName()));
//        totalHoursLate.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalHoursLate()).asObject());
//        totalWorkSession.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalWorkSession()).asObject());
//        totalHoursLeavingEarly.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalHoursLeavingEarly()).asObject());
System.out.println(123);
        List<OfficerReportAttendanceRow> officerReportAttendanceList = reportOfficeAttendanceController.fetchListOfOfficerReportAttendance(id,date);
//        System.out.println(Arrays.toString(officerReportAttendanceList.toArray()));
//        ObservableList<OfficerReportAttendanceRow> observableList = FXCollections.observableArrayList(officerReportAttendanceList);
//        reportAttendanceTableView.setItems(observableList);
    }

    public Department getInitialData() {
        return  reportOfficeAttendanceController.getInitialData();
    }

}
