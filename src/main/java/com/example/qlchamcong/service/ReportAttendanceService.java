package com.example.qlchamcong.service;

import com.example.qlchamcong.HRSystem.HRSystemAPIService;
import com.example.qlchamcong.HRSystem.IHRSystemAPIService;
import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.HRSystem.entity.Employee;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.OfficerReportAttendanceRow;
import com.example.qlchamcong.entity.WorkerReportAttendanceRow;
import com.example.qlchamcong.repository.IAttendanceRecordRepository;
import com.example.qlchamcong.repository.IOfficerReportAttendanceRepository;
import com.example.qlchamcong.repository.RepositoryInitializer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportAttendanceService implements IReportAttendanceService {
    private final IOfficerReportAttendanceRepository officerReportAttendanceRepository;
    private IHRSystemAPIService hRSystemAPIService;
    public ReportAttendanceService() {
        hRSystemAPIService= new HRSystemAPIService();
        this.officerReportAttendanceRepository = RepositoryInitializer.getOfficerReportAttendanceRepository();
    }


    @Override
    public List<Department> getDepartmentList() {
        return hRSystemAPIService.getDeparmentList();
    }

    @Override
    public List<OfficerReportAttendanceRow> getOfficerReportAttendanceList(int id, Date date) {
        System.out.println(1);
        Date start=getStartDateOfMonth(date);
        Date end=getEndDateOfMonth(date);
        List<Integer> employeeIds=new ArrayList<>();
        System.out.println(1);
        List<Employee> employeeList=hRSystemAPIService.getEmployeeList(id,date);
        System.out.println(employeeList);
        for (Employee employee :employeeList){
            employeeIds.add(employee.getEmployeeId());
        }
        List<OfficerAttendanceData> dataList= officerReportAttendanceRepository.getOfficerReportAttendance(start,end,employeeIds);
        System.out.println(dataList);
        return null;
    }

    @Override
    public List<WorkerReportAttendanceRow> getWorkerReportAttendanceList(int id, Date date) {
        return null;
    }
    private static Date getStartDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    private static Date getEndDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

}
