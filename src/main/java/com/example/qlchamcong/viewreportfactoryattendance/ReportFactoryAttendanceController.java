package com.example.qlchamcong.viewreportfactoryattendance;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.entity.OfficerReportAttendance;
import com.example.qlchamcong.entity.WorkerReportAttendance;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.service.IReportAttendanceService;
import com.example.qlchamcong.service.ServiceInitializer;

import java.util.List;

public class ReportFactoryAttendanceController {
    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;
    private final IReportAttendanceService reportAttendanceService;
    public ReportFactoryAttendanceController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.argumentUtil = argumentUtil;
        this.navUtil = navUtil;
        this.reportAttendanceService= ServiceInitializer.getReportAttendanceService();
//        List<OfficerReportAttendanceRow> officerReportAttendanceRows= fetchListOfOfficerReportAttendance();
    }
    public List<WorkerReportAttendance> fetchListOfWorkerReportAttendance(int id, String month, String year)  {
        System.out.println(month);
        System.out.println(year);
        return reportAttendanceService.getWorkerReportAttendanceList(id,month,year);
    }
    public Department getInitialData() {
        return (Department) argumentUtil.getSharedData("fromDepartmentToReportAttendance");
    }
}
