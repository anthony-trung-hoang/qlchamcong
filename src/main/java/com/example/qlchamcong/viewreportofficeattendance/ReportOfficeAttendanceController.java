package com.example.qlchamcong.viewreportofficeattendance;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.entity.OfficerReportAttendance;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.service.reportattendanceservice.IReportAttendanceService;
import com.example.qlchamcong.service.ServiceInitializer;

import java.util.List;

public class ReportOfficeAttendanceController {
    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;
    private final IReportAttendanceService reportAttendanceService;
    public ReportOfficeAttendanceController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.argumentUtil = argumentUtil;
        this.navUtil = navUtil;
        this.reportAttendanceService= ServiceInitializer.getReportAttendanceService();
//        List<OfficerReportAttendanceRow> officerReportAttendanceRows= fetchListOfOfficerReportAttendance();
    }
    public List<OfficerReportAttendance> fetchListOfOfficerReportAttendance(int id, String month,String year)  {
        return reportAttendanceService.getOfficerReportAttendanceList(id,month,year);
    }
    public Department getInitialData() {
        return (Department) argumentUtil.getSharedData("fromDepartmentToReportAttendance");
    }
}
