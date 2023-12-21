package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.repository.*;
import com.example.qlchamcong.service.workhourscalculator.OfficerWorkHoursCalculator;
import com.example.qlchamcong.service.workhourscalculator.WorkerWorkHoursCalculator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ViewAttendanceRecordsService implements IViewAttendanceRecordsService {
    private final IAttendanceRecordRepository attendanceRecordRepository;
    private final ITimekeeperRepository timekeeperRepository;
    private final IEmployeeRepository employeeRepository;

    private final IWorkerAttendanceDataRepository workerAttendanceDataRepository;

    private final IOfficerAttendanceDataRepository officerAttendanceDataRepository;


    public ViewAttendanceRecordsService() {
        this.attendanceRecordRepository = RepositoryInitializer.getAttendanceRecordRepository();
        this.timekeeperRepository = RepositoryInitializer.getTimekeeperRepository();
        this.employeeRepository = RepositoryInitializer.getEmployeeRepository();
        this.workerAttendanceDataRepository = RepositoryInitializer.getWorkerAttendanceDataRepository();
        this.officerAttendanceDataRepository = RepositoryInitializer.getOfficerAttendanceDataRepository();
    }

    @Override
    public List<AttendanceRecord> getRecordsOfAnEmployeeInADay(int employeeId, Date date) {
        return attendanceRecordRepository.getAttendanceRecordsByEmployeeAndDate(employeeId, (java.sql.Date) date);
    }

    @Override
    public void deleteRecord(AttendanceRecord currentRecord) {
        attendanceRecordRepository.deleteRecordById(currentRecord.getId());
    }

    @Override
    public void createANewRecord(AttendanceRecord newRecord) {
        boolean checkTimekeeperIdExists = timekeeperRepository.checkTimeKeeperIdExists(newRecord.getTimeKeeperId());
        if (checkTimekeeperIdExists) {
            String employeeRole = employeeRepository.getRoleById(newRecord.getEmployeeId());
            System.out.println("role" + employeeRole);
            if (employeeRole.equals("worker")) {
                int maxRecordAllowed = 6;
                int numberOfRecordsInDay = attendanceRecordRepository.getNumberOfRecordsInADayByDateAndEmployee(newRecord.getEmployeeId(), newRecord.getDateFromTimestamp());
                if (numberOfRecordsInDay >= maxRecordAllowed) {
                    throw new MaxRecordsExceededException(employeeRole, maxRecordAllowed);
                } else {
                    attendanceRecordRepository.createANewRecord(newRecord);
                    // update changes in attendance data of that worker
                    updateWorkerAttendanceData(newRecord);
                }
            } else if (employeeRole.equals("officer")) {
                int maxRecordAllowed = 4;
                int numberOfRecordsInDay = attendanceRecordRepository.getNumberOfRecordsInADayByDateAndEmployee(newRecord.getEmployeeId(), newRecord.getDateFromTimestamp());
                if (numberOfRecordsInDay >= maxRecordAllowed) {
                    throw new MaxRecordsExceededException(employeeRole, maxRecordAllowed);
                } else {
                    attendanceRecordRepository.createANewRecord(newRecord);
                    // update changes in attendance data of that officer
                    updateOfficerAttendanceData(newRecord);
                }
            }

        } else {
            throw new TimeKeeperNotFoundException("TimeKeeper with id " + newRecord.getTimeKeeperId() + " not found.");
        }
    }

    private void updateOfficerAttendanceData(AttendanceRecord newRecord) {
        int employeeId = 3;
        Date date = newRecord.getDateFromTimestamp();
        OfficerAttendanceData officerAttendanceData = officerAttendanceDataRepository.getOfficerAttendanceDataByEmployeeAndDate(employeeId, date);
        System.out.println(officerAttendanceData);
        List<AttendanceRecord> attendanceRecordList = getRecordsOfAnEmployeeInADay(employeeId, date);
        attendanceRecordList.sort(Comparator.comparing(AttendanceRecord::getTimestamp));
        List<Double> calculatedResult = List.of(OfficerWorkHoursCalculator.calculateOfficeWorkHours(attendanceRecordList));
        System.out.println(Arrays.toString(calculatedResult.toArray()));
        boolean morningSession = calculatedResult.get(0) == 1;
        boolean afternoonSession = calculatedResult.get(1) == 1;
        double lateHours = calculatedResult.get(2);
        double earlyLeaveHours = calculatedResult.get(3);

        officerAttendanceDataRepository.updateOfficerAttendanceData(officerAttendanceData.getId(), morningSession, afternoonSession, lateHours, earlyLeaveHours);

    }

    private void updateWorkerAttendanceData(AttendanceRecord newRecord) {
        int employeeId = newRecord.getEmployeeId();
        Date date = newRecord.getDateFromTimestamp();
        WorkerAttendanceData workerAttendanceData = workerAttendanceDataRepository.getWorkerAttendanceDataByEmployeeAndDate(employeeId, date);
        List<AttendanceRecord> attendanceRecordList = getRecordsOfAnEmployeeInADay(employeeId, date);
        attendanceRecordList.sort(Comparator.comparing(AttendanceRecord::getTimestamp));
        List<Double> calculatedResult = WorkerWorkHoursCalculator.calculateWorkerWorkHours(attendanceRecordList);
        workerAttendanceDataRepository.updateWorkerAttendanceData(workerAttendanceData.getId(), calculatedResult.get(0), calculatedResult.get(1), calculatedResult.get(2));
    }

    @Override
    public void updateRecord(AttendanceRecord newRecord) {
        boolean checkTimekeeperIdExists = timekeeperRepository.checkTimeKeeperIdExists(newRecord.getTimeKeeperId());
        if (checkTimekeeperIdExists) {
            String employeeRole = employeeRepository.getRoleById(newRecord.getEmployeeId());
            System.out.println("role" + employeeRole);
            if (employeeRole.equals("worker")) {

                attendanceRecordRepository.updateRecordById(newRecord);
                // update changes in attendance data of that worker
                updateWorkerAttendanceData(newRecord);
            } else if (employeeRole.equals("officer")) {
                attendanceRecordRepository.updateRecordById(newRecord);
                // update changes in attendance data of that officer
                updateOfficerAttendanceData(newRecord);
            }


        } else {
            throw new TimeKeeperNotFoundException("TimeKeeper with id " + newRecord.getTimeKeeperId() + " not found.");
        }
    }

    public static class TimeKeeperNotFoundException extends RuntimeException {
        public TimeKeeperNotFoundException(String message) {
            super(message);
        }
    }

    public static class MaxRecordsExceededException extends RuntimeException {
        public MaxRecordsExceededException(String role, int maxRecordsAllowed) {
            super("Exceeded the maximum number of records allowed for role '" + role + "'. Maximum allowed: " + maxRecordsAllowed);
        }
    }
}
