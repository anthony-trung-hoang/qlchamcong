package com.example.qlchamcong.service;

import com.example.qlchamcong.HRSystem.HRSystemAPIService;
import com.example.qlchamcong.HRSystem.HRSystemInitializer;
import com.example.qlchamcong.HRSystem.IHRSystemAPIService;
import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.OfficerReportAttendance;
import com.example.qlchamcong.entity.WorkerReportAttendance;
import com.example.qlchamcong.repository.IOfficerReportAttendanceRepository;
import com.example.qlchamcong.repository.RepositoryInitializer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportAttendanceService implements IReportAttendanceService {
    private final IOfficerReportAttendanceRepository officerReportAttendanceRepository;
    private IHRSystemAPIService hRSystemAPIService;
    private final HRSystemInitializer hrSystemInitializer;
    public ReportAttendanceService() {
        hrSystemInitializer= new HRSystemInitializer();
        hRSystemAPIService= hrSystemInitializer.getIhrSystemAPIService();
        this.officerReportAttendanceRepository = RepositoryInitializer.getOfficerReportAttendanceRepository();
    }


    @Override
    public List<Department> getDepartmentList() {
        return hRSystemAPIService.getDeparmentList();
    }

    @Override
    public List<OfficerReportAttendance> getOfficerReportAttendanceList(int id, Date date)  {
        String start=getStartDateOfMonth(date);
        String end=getEndDateOfMonth(date);
        List<Integer> employeeIds=new ArrayList<>();
        List<Employee> employeeList=hRSystemAPIService.getEmployeeList(id,date);
        for (Employee employee :employeeList){
            employeeIds.add(employee.getEmployeeId());
        }
        List<OfficerAttendanceData> dataList= officerReportAttendanceRepository.getOfficerReportAttendance(start,end,employeeIds);
        return getOfficerReportAttendance(employeeList,dataList,date);
    }

    @Override
    public List<WorkerReportAttendance> getWorkerReportAttendanceList(int id, Date date) {
        return null;
    }
    public static String getStartDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }

    private static String getEndDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }
    private List<OfficerReportAttendance> getOfficerReportAttendance(List<Employee> employeeList, List<OfficerAttendanceData> attendanceData,Date date)  {

        System.out.println(date.getYear());
        String month= String.valueOf(date.getMonth()+1)+"/"+String.valueOf(date.getYear()+1900);
        List<OfficerReportAttendance> reportAttendanceList = new ArrayList<OfficerReportAttendance>();
        for (Employee employee:employeeList){
            int employeeId= employee.getEmployeeId();
            String employeeName= employee.getName();
            String departmentName=employee.getDepartment().getName();
            int totalWorkSession =0;
            double totalHoursLate=0.0;
            double totalHoursLeavingEarly=0.0;
            for (OfficerAttendanceData attendance:attendanceData){
                if(attendance.getEmployeeId()==employeeId){
                    totalWorkSession +=attendance.isAfternoonSession()?1:0 ;
                    totalWorkSession +=attendance.isMorningSession()?1:0 ;
                    totalHoursLate+=attendance.getHoursLate();
                    totalHoursLeavingEarly+=attendance.getHoursEarlyLeave();
                }
            }
            OfficerReportAttendance reportEmployee= new OfficerReportAttendance(employeeId,employeeName,departmentName,month,totalWorkSession,totalHoursLate,totalHoursLeavingEarly);
            reportAttendanceList.add(reportEmployee);
        }
        return reportAttendanceList;
    }

}
