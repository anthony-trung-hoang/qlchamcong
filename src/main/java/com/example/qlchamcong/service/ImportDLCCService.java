package com.example.qlchamcong.service;

import com.example.qlchamcong.HRSystem.IHRSystemAPIService;
import com.example.qlchamcong.entity.*;
import com.example.qlchamcong.exception.InvalidFileFormatException;
import com.example.qlchamcong.repository.IOfficerAttendanceDataRepository;
import com.example.qlchamcong.repository.ITimekeeperRepository;
import com.example.qlchamcong.repository.IWorkerAttendanceDataRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImportDLCCService implements IImportDLCCService {

    private final ITimekeeperRepository timekeeperRepository;
    private final IWorkerAttendanceDataRepository workerAttendanceRepository;
    private final IOfficerAttendanceDataRepository officerAttendanceDataRepository;
    private final IHRSystemAPIService hrSystemAPIService;

    public ImportDLCCService(ITimekeeperRepository timekeeperRepository, IWorkerAttendanceDataRepository workerAttendanceRepository, IOfficerAttendanceDataRepository officerAttendanceDataRepository, IHRSystemAPIService hrSystemAPIService) {
        this.timekeeperRepository = timekeeperRepository;
        this.workerAttendanceRepository = workerAttendanceRepository;
        this.officerAttendanceDataRepository = officerAttendanceDataRepository;
        this.hrSystemAPIService = hrSystemAPIService;
    }

    @Override
    public List<AttendanceRecord> getAttendanceRecord(File attedanceRecordCheckInFile, File attedanceRecordCheckOutFile) throws InvalidFileFormatException {
        List<AttendanceRecord> checkInRecords = readAttendanceRecords(readFileData(attedanceRecordCheckInFile));
        List<AttendanceRecord> checkOutRecords = readAttendanceRecords(readFileData(attedanceRecordCheckOutFile));

        checkInRecords.forEach(record -> record.setType("checkin"));
        checkOutRecords.forEach(record -> record.setType("checkout"));

        List<AttendanceRecord> combinedRecords = new ArrayList<>();
        combinedRecords.addAll(checkInRecords);
        combinedRecords.addAll(checkOutRecords);

        List<Employee> employeeList = hrSystemAPIService.getEmployeeList();

        validateEmployeeCodes(combinedRecords, employeeList);

        return combinedRecords;
    }

    @Override
    public Tuple2<OfficerAttendanceData, WorkerAttendanceData> getTransformedData(List<AttendanceRecord> attendanceRecordList) {

        return null;
    }

    @Override
    public List<String> getAllTimekeeperCode() {
        List<Timekeeper> timekeeperList = timekeeperRepository.getAllTimekeeper();
        List<String> timekeeperCodes = new ArrayList<>();

        for (Timekeeper timekeeper : timekeeperList) {
            timekeeperCodes.add(timekeeper.getTimekeeperCode());
        }

        return timekeeperCodes;
    }

    @Override
    public List<String> getAllTimekeeperCheckInCode() {
        List<Timekeeper> timekeeperList = timekeeperRepository.getTimekeepersByType("checkin");
        List<String> timekeeperCodes = new ArrayList<>();

        for (Timekeeper timekeeper : timekeeperList) {
            timekeeperCodes.add(timekeeper.getTimekeeperCode());
        }
        return timekeeperCodes;
    }

    @Override
    public List<String> getAllTimekeeperCheckOutCode() {
        List<Timekeeper> timekeeperList = timekeeperRepository.getTimekeepersByType("checkout");
        List<String> timekeeperCodes = new ArrayList<>();

        for (Timekeeper timekeeper : timekeeperList) {
            timekeeperCodes.add(timekeeper.getTimekeeperCode());
        }
        return timekeeperCodes;
    }

    private static List<String[]> readCSVFile(File file) {
        List<String[]> data = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            data = csvReader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return data;
    }

    private static List<String[]> readXLSXFile(File file) {
        List<String[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            DataFormatter dataFormatter = new DataFormatter();

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                List<String> rowData = new ArrayList<>();

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    String formattedCellValue = dataFormatter.formatCellValue(cell);

                    rowData.add(formattedCellValue);
                }

                data.add(rowData.toArray(new String[0]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private List<AttendanceRecord> readAttendanceRecords(List<String[]> data) throws InvalidFileFormatException {
        if (data == null || data.size() < 2 || data.get(0).length < 2) {
            throw new InvalidFileFormatException("File không đủ 2 trường dữ liệu.");
        }

        List<AttendanceRecord> attendanceRecords = new ArrayList<>();

        try {
            for (int i = 1; i < data.size(); i++) {
                String[] row = data.get(i);

//                System.out.println(row[0].trim());
//                System.out.println(row[1].trim());

                String manhanvien = row[0].trim();
                long timestamp = Long.parseLong(row[1].trim());

                AttendanceRecord attendanceRecord = new AttendanceRecord(manhanvien, new Timestamp(timestamp));
                attendanceRecords.add(attendanceRecord);
            }
        } catch (NumberFormatException e) {
            throw new InvalidFileFormatException("Lỗi định dạng dữ liệu trong file.");
        }

        return attendanceRecords;
    }

    private List<String[]> readFileData(File attedanceRecordFile) throws InvalidFileFormatException {
        if (!attedanceRecordFile.getName().toLowerCase().endsWith(".csv") && !attedanceRecordFile.getName().toLowerCase().endsWith(".xlsx")) {
            throw new InvalidFileFormatException("Invalid file format. Expected CSV or XLSX file.");
        }

        List<String[]> fileData;
        if (attedanceRecordFile.getName().toLowerCase().endsWith(".csv")) {
            return readCSVFile(attedanceRecordFile);
        } else {
            return readXLSXFile(attedanceRecordFile);
        }
    }

    private void validateEmployeeCodes(List<AttendanceRecord> attendanceRecords, List<Employee> employeeList) throws InvalidFileFormatException {
        for (AttendanceRecord record : attendanceRecords) {
            String employeeCodeToCheck = record.getEmployeeCode();

            boolean employeeCodeExists = employeeList.stream()
                    .anyMatch(employee -> employee.getEmployeeToString().equals(employeeCodeToCheck));

            if (!employeeCodeExists) {
                throw new InvalidFileFormatException("EmployeeCode không tồn tại: " + employeeCodeToCheck);
            }
        }
    }
}
