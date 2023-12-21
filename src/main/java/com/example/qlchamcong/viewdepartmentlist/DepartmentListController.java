package com.example.qlchamcong.viewdepartmentlist;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.HRSystem.entity.RoleDepartment;
import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.service.IReportAttendanceService;
import com.example.qlchamcong.service.ServiceInitializer;

import java.io.IOException;
import java.util.List;

public class DepartmentListController {


    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;
    private final IReportAttendanceService reportAttendanceService;

    public DepartmentListController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.argumentUtil = argumentUtil;
        this.navUtil = navUtil;
        this.reportAttendanceService = ServiceInitializer.getReportAttendanceService();
        List<Department> data = fetchListOfDepartment();
        System.out.println(data.get(0).getName());
    }

    public List<Department> fetchListOfDepartment() {
        return reportAttendanceService.getDepartmentList();
    }

    public void showReportAttendance(Department department) throws IOException {
        argumentUtil.setSharedData("fromDepartmentToReportAttendance", department);
        if(department.getType()== RoleDepartment.VAN_PHONG)
         navUtil.changeGUI("/com/example/qlchamcong/viewreportofficeattendance/view-report-office-attendance.fxml");
        else if(department.getType()== RoleDepartment.NHA_MAY)
            navUtil.changeGUI("/com/example/qlchamcong/viewreportfactoryattendance/view-report-factory-attendance.fxml");
    }
}
