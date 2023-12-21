package com.example.qlchamcong.viewdepartmentlist;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.service.report.IReportAttendanceService;
import com.example.qlchamcong.service.ServiceInitializer;

import java.util.List;

public class DepartmentListController {


    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;
    private final IReportAttendanceService reportAttendanceService;
    public DepartmentListController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.argumentUtil = argumentUtil;
        this.navUtil = navUtil;
        this.reportAttendanceService= ServiceInitializer.getReportAttendanceService();
       List<Department> data= fetchListOfDepartment();
       System.out.println(data.get(0).getName());
    }
    public List<Department> fetchListOfDepartment() {
        return reportAttendanceService.getDepartmentList();
    }


}
