package com.example.qlchamcong.service.reportattendanceservice;

import com.example.qlchamcong.HRSystem.HRSystemInitializer;
import com.example.qlchamcong.HRSystem.IHRSystemAPIService;
import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.entity.*;
import com.example.qlchamcong.repository.reportattendancerepositorry.IOfficerReportAttendanceRepository;
import com.example.qlchamcong.repository.workerreportattendancereporitory.IWorkerReportAttendanceRepository;
import com.example.qlchamcong.repository.RepositoryInitializer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportAttendanceService implements IReportAttendanceService {
    private final IOfficerReportAttendanceRepository officerReportAttendanceRepository;
    private final IWorkerReportAttendanceRepository workerReportAttendanceRepository;
    private IHRSystemAPIService hRSystemAPIService;
    public ReportAttendanceService() {
        hRSystemAPIService= HRSystemInitializer.getIhrSystemAPIService();
        this.officerReportAttendanceRepository = RepositoryInitializer.getOfficerReportAttendanceRepository();
        this.workerReportAttendanceRepository= RepositoryInitializer.getWorkerReportAttendanceRepository();
    }


    @Override
    public List<Department> getDepartmentList() {
        return hRSystemAPIService.getDeparmentList();
    }
    @Override
    public List<OfficerReportAttendance> getOfficerReportAttendanceList(int id, String month,String year)  {
        String start= year + "-" + month + "-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(start);
            String end=getEndDateOfMonth(startDate);
            List<Integer> employeeIds=new ArrayList<>();
            List<Employee> employeeList=hRSystemAPIService.getEmployeeList(id,sdf.parse(end));
            for (Employee employee :employeeList){
                employeeIds.add(employee.getEmployeeId());
            }
            List<OfficerAttendanceData> dataList= officerReportAttendanceRepository.getOfficerReportAttendance(start,end,employeeIds);
            return getOfficerReportAttendance(employeeList,dataList,month,year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<WorkerReportAttendance> getWorkerReportAttendanceList(int id, String month,String year) {
        String start= year + "-" + month + "-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(start);
            String end=getEndDateOfMonth(startDate);
            List<Integer> employeeIds=new ArrayList<>();
            List<Employee> employeeList=hRSystemAPIService.getEmployeeList(id,sdf.parse(end));
            for (Employee employee :employeeList){
                employeeIds.add(employee.getEmployeeId());
            }
            List<WorkerAttendanceData> dataList= workerReportAttendanceRepository.getWorkerReportAttendance(start,end,employeeIds);
            return getWorkerReportAttendance(employeeList,dataList,month,year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

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
    private List<OfficerReportAttendance> getOfficerReportAttendance(List<Employee> employeeList, List<OfficerAttendanceData> attendanceData,String month,String year)  {
        String monthA=month+"/"+year;
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
            OfficerReportAttendance reportEmployee= new OfficerReportAttendance(employeeId,employeeName,departmentName,monthA,totalWorkSession,totalHoursLate,totalHoursLeavingEarly);
            reportAttendanceList.add(reportEmployee);
        }
        return reportAttendanceList;
    }
    public List<WorkerReportAttendance> getWorkerReportAttendance(List<Employee> employeeList, List<WorkerAttendanceData> attendanceData, String month, String year)  {
        String monthA=month+"/"+year;
        List<WorkerReportAttendance> reportAttendanceList = new ArrayList<WorkerReportAttendance>();
        for (Employee employee:employeeList){
            int employeeId= employee.getEmployeeId();
            String employeeName= employee.getName();
            String departmentName=employee.getDepartment().getName();
            double totalWork=0.0;
            double totalOvertime=0.0;
            for (WorkerAttendanceData attendance:attendanceData){
                if(attendance.getEmployeeId()==employeeId){
                    totalWork+=attendance.getHoursShift1()+attendance.getHoursShift2();
                    totalOvertime+=attendance.getHoursShift3();
                }
            }
            WorkerReportAttendance reportEmployee= new WorkerReportAttendance(employeeId,employeeName,departmentName,monthA,totalWork,totalOvertime);
            reportAttendanceList.add(reportEmployee);
        }
        return reportAttendanceList;
    }

}
