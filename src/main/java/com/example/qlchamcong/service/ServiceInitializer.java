package com.example.qlchamcong.service;

import com.example.qlchamcong.repository.RepositoryInitializer;

public class ServiceInitializer {
    private static IDangNhapService dangNhapService;
    private static IImportDLCCService importDLCCService;
    private static IQLNSHomeService qlnsHomeService;
    private static IAttendanceRecordService attendanceRecordService;
    private static IAttendanceDataService attendanceDataService;
    private static IReportAttendanceService departmentListService;
    public ServiceInitializer() {
        dangNhapService = new DangNhapService(RepositoryInitializer.getNguoiDungRepository());
        importDLCCService = new ImportDLCCService();
        qlnsHomeService = new QLNSHomeService();
        attendanceDataService = new AttendanceDataService();
        attendanceRecordService = new AttendanceRecordService();
        departmentListService = new ReportAttendanceService();
    }

    public static IDangNhapService getDangNhapService() {
        return dangNhapService;
    }

    public static IImportDLCCService getImportDLCCService() {
        return importDLCCService;
    }

    public static IQLNSHomeService getQlnsHomeService() {
        return qlnsHomeService;
    }

    public static IAttendanceDataService getAttendanceDataService() {
        return attendanceDataService;
    }
    public static IAttendanceRecordService getAttendanceRecordService() {return attendanceRecordService;}

    public static IReportAttendanceService getDepartmentListService() {
        return departmentListService;
    }
}
