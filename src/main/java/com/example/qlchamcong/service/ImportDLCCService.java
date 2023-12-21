package com.example.qlchamcong.service;

import com.example.qlchamcong.HRSystem.IHRSystemAPIService;
import com.example.qlchamcong.entity.*;
import com.example.qlchamcong.exception.*;
import com.example.qlchamcong.repository.IAttendanceRecordRepository;
import com.example.qlchamcong.repository.IOfficerAttendanceDataRepository;
import com.example.qlchamcong.repository.ITimekeeperRepository;
import com.example.qlchamcong.repository.IWorkerAttendanceDataRepository;
import com.example.qlchamcong.service.workhourscalculator.OfficerWorkHoursCalculator;
import com.example.qlchamcong.service.workhourscalculator.WorkerWorkHoursCalculator;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class ImportDLCCService implements IImportDLCCService {

    private final ITimekeeperRepository timekeeperRepository;
    private final IAttendanceRecordRepository attendanceRecordRepository;
    private final IWorkerAttendanceDataRepository workerAttendanceRepository;
    private final IOfficerAttendanceDataRepository officerAttendanceDataRepository;
    private final IHRSystemAPIService hrSystemAPIService;

    public ImportDLCCService(ITimekeeperRepository timekeeperRepository, IWorkerAttendanceDataRepository workerAttendanceRepository, IOfficerAttendanceDataRepository officerAttendanceDataRepository, IHRSystemAPIService hrSystemAPIService, IAttendanceRecordRepository attendanceRecordRepository) {
        this.timekeeperRepository = timekeeperRepository;
        this.workerAttendanceRepository = workerAttendanceRepository;
        this.officerAttendanceDataRepository = officerAttendanceDataRepository;
        this.hrSystemAPIService = hrSystemAPIService;
        this.attendanceRecordRepository = attendanceRecordRepository;
    }

    @Override
    public List<AttendanceRecord> getAttendanceRecord(File attedanceRecordCheckInFile, String timekeeperCheckInCodesValue, File attedanceRecordCheckOutFile, String timekeeperCheckOutCodesValue) throws InvalidFileFormatException, ConflictSavedAttendanceRecord {
        List<AttendanceRecord> checkInRecords = readAttendanceRecords(readFileData(attedanceRecordCheckInFile), timekeeperCheckInCodesValue);
        List<AttendanceRecord> checkOutRecords = readAttendanceRecords(readFileData(attedanceRecordCheckOutFile), timekeeperCheckOutCodesValue);

        List<AttendanceRecord> combinedRecords = new ArrayList<>();
        combinedRecords.addAll(checkInRecords);
        combinedRecords.addAll(checkOutRecords);

        List<Employee> employeeList = hrSystemAPIService.getEmployeeList();

        validateEmployeeCodes(combinedRecords, employeeList);

        checkIfRecordDataExist(checkInRecords, timekeeperCheckInCodesValue);
        checkIfRecordDataExist(checkOutRecords, timekeeperCheckOutCodesValue);

        return combinedRecords;
    }

    private void checkIfRecordDataExist(List<AttendanceRecord> attendanceRecords, String timekeeperCodesValue) throws ConflictSavedAttendanceRecord {
        Timekeeper timekeeper = timekeeperRepository.getTimekeeperByCode(timekeeperCodesValue);
        AttendanceRecord latestRecord = attendanceRecordRepository.getLatestAttendanceRecordByTimeKeeperId(timekeeper.getId());
        if (latestRecord == null) return;
        for (AttendanceRecord record : attendanceRecords) {
            if (record.getTimestamp().before(latestRecord.getTimestamp()) || record.getTimestamp().equals(latestRecord.getTimestamp())) {
                throw new ConflictSavedAttendanceRecord("Error: Some records have timestamps less than or equal to the latest record's timestamp. Employee: " + record.getEmployeeId() + ", date: " + record.getFormattedDate());
            }
        }
    }

    @Override
    public Tuple2<WorkerAttendanceData, OfficerAttendanceData> getTransformedData(List<AttendanceRecord> attendanceRecordList) throws TransformException {
        List<Employee> employeeList = hrSystemAPIService.getEmployeeList();

        List<WorkerAttendanceData> workerAttendanceList = transformWorkerAttendanceData(convertToWorkerAttendanceDataList(attendanceRecordList, employeeList), attendanceRecordList);
        List<OfficerAttendanceData> officerAttendanceList = transformOfficerAttendanceData(convertToOfficerAttendanceDataList(attendanceRecordList, employeeList), attendanceRecordList);

        return new Tuple2<>(workerAttendanceList, officerAttendanceList);
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

    @Override
    public void saveAttendanceData(Tuple2<WorkerAttendanceData, OfficerAttendanceData> transformedData, List<AttendanceRecord> attendanceRecordList) {
        // save record
        attendanceRecordRepository.saveAttendanceRecords(attendanceRecordList);

        // save worker data
        if (transformedData.getWorkerAttendanceDataList() != null)
            workerAttendanceRepository.saveWorkerAttendanceDataList(transformedData.getWorkerAttendanceDataList());

        // save officer data
        if (transformedData.getOfficerAttendanceDataList() != null)
            officerAttendanceDataRepository.saveOfficerAttendanceDataList(transformedData.getOfficerAttendanceDataList());
        System.out.println("Success");
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

    private List<WorkerAttendanceData> transformWorkerAttendanceData(List<WorkerAttendanceData> workerAttendanceDataList, List<AttendanceRecord> attendanceRecordList) throws TransformException {
        if (workerAttendanceDataList.size() != 0) {
            Map<String, Map<String, List<AttendanceRecord>>> workerRecordsMap = new HashMap<>();

            for (AttendanceRecord record : attendanceRecordList) {
                String employeeId = String.valueOf(record.getEmployeeId());
                String dateString = record.getFormattedDate();

                if (!workerRecordsMap.containsKey(employeeId)) {
                    workerRecordsMap.put(employeeId, new HashMap<>());
                }

                if (!workerRecordsMap.get(employeeId).containsKey(dateString)) {
                    workerRecordsMap.get(employeeId).put(dateString, new ArrayList<>());
                }

                workerRecordsMap.get(employeeId).get(dateString).add(record);
            }

            workerAttendanceDataList = new ArrayList<>();

            for (Map.Entry<String, Map<String, List<AttendanceRecord>>> entry : workerRecordsMap.entrySet()) {
                String employeeId = entry.getKey();

                for (Map.Entry<String, List<AttendanceRecord>> dateEntry : entry.getValue().entrySet()) {
                    String dateString = dateEntry.getKey();
                    List<AttendanceRecord> dailyRecords = dateEntry.getValue();

                    Collections.sort(dailyRecords, Comparator.comparingLong(AttendanceRecord::getTimestampInLong));

                    WorkerAttendanceData worker = new WorkerAttendanceData(Integer.parseInt(employeeId));
                    worker.setDate(dateEntry.getValue().get(0).getDateFromTimestamp());
                    try {
                        List<Double> res = WorkerWorkHoursCalculator.calculateWorkerWorkHours(dailyRecords);
                        worker.setHoursShift1(res.get(0));
                        worker.setHoursShift2(res.get(1));
                        worker.setHoursShift3(res.get(2));

                        workerAttendanceDataList.add(worker);
                    } catch (UnknownShiftException ex) {
                        throw new TransformException("Wrong check in/out time: " + worker.getEmployeeId() + ", date: " + worker.getDate());
                    }
                }
            }
        } else {
            workerAttendanceDataList = null;
        }

        return workerAttendanceDataList;
    }

    private List<OfficerAttendanceData> transformOfficerAttendanceData(List<OfficerAttendanceData> officerAttendanceDataList, List<AttendanceRecord> attendanceRecordList) throws TransformException {
        if (officerAttendanceDataList.size() != 0) {
            Map<String, Map<String, List<AttendanceRecord>>> officerRecordsMap = new HashMap<>();

            for (AttendanceRecord record : attendanceRecordList) {
                String employeeId = String.valueOf(record.getEmployeeId());
                String dateString = record.getFormattedDate();

                if (!officerRecordsMap.containsKey(employeeId)) {
                    officerRecordsMap.put(employeeId, new HashMap<>());
                }

                if (!officerRecordsMap.get(employeeId).containsKey(dateString)) {
                    officerRecordsMap.get(employeeId).put(dateString, new ArrayList<>());
                }

                officerRecordsMap.get(employeeId).get(dateString).add(record);
            }

            officerAttendanceDataList = new ArrayList<>();

            for (Map.Entry<String, Map<String, List<AttendanceRecord>>> entry : officerRecordsMap.entrySet()) {
                String employeeId = entry.getKey();

                for (Map.Entry<String, List<AttendanceRecord>> dateEntry : entry.getValue().entrySet()) {
//                    String dateString = dateEntry.getKey();
                    List<AttendanceRecord> dailyRecords = dateEntry.getValue();

                    dailyRecords.sort(Comparator.comparingLong(AttendanceRecord::getTimestampInLong));

                    OfficerAttendanceData officer = new OfficerAttendanceData(Integer.parseInt(employeeId));
                    officer.setDate(dateEntry.getValue().get(0).getDateFromTimestamp());

                    try {
                        Double[] res = OfficerWorkHoursCalculator.calculateOfficeWorkHours(dailyRecords);
                        officer.setMorningSession(res[0] > 0.0);
                        officer.setAfternoonSession(res[0] > 0.0);
                        officer.setHoursLate(res[2]);
                        officer.setHoursEarlyLeave(res[3]);

                        officerAttendanceDataList.add(officer);
                    } catch (UnknownShiftException ex) {
                        throw new TransformException("Wrong check in/out time: " + officer.getEmployeeId() + ", date: " + officer.getDate());
                    }
                }
            }
        } else {
            officerAttendanceDataList = null;
        }

        return officerAttendanceDataList;
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

                // Kiểm tra xem hàng có chứa dữ liệu không
                if (!hasDataInRow(row)) {
                    // Nếu không có dữ liệu, bỏ qua và chuyển sang hàng tiếp theo
                    break;
                }

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

    private static boolean hasDataInRow(Row row) {
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell.getCellType() != CellType.BLANK) {
                return true; // Nếu có ít nhất một ô có dữ liệu, trả về true
            }
        }
        return false; // Nếu tất cả các ô đều trống, trả về false
    }

    private List<AttendanceRecord> readAttendanceRecords(List<String[]> data, String timekeeperCodesValue) throws InvalidFileFormatException {
        if (data == null || data.size() < 2 || data.get(0).length < 2) {
            throw new InvalidFileFormatException("File không đủ 2 trường dữ liệu.");
        }

        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        Timekeeper timekeeper = timekeeperRepository.getTimekeeperByCode(timekeeperCodesValue);
//        System.out.println(data.size());
        try {
            for (int i = 1; i < data.size(); i++) {
                String[] row = data.get(i);
                // Kiểm tra xem dòng dữ liệu có đúng số lượng cột không
                if (row.length != 2) {
                    throw new InvalidFileFormatException("Dòng " + (i + 1) + " không đúng định dạng. Cần có đúng 2 cột.");
                }

//                System.out.println(row[0].trim());
//                System.out.println(row[1].trim());

                String employeeId = row[0].trim();
                long timestamp = Long.parseLong(row[1].trim());

                AttendanceRecord attendanceRecord = new AttendanceRecord(Integer.parseInt(employeeId), timekeeper.getId(), new Timestamp(timestamp), timekeeper.getType());
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

        if (attedanceRecordFile.getName().toLowerCase().endsWith(".csv")) {
            return readCSVFile(attedanceRecordFile);
        } else {
            return readXLSXFile(attedanceRecordFile);
        }
    }

    private void validateEmployeeCodes(List<AttendanceRecord> attendanceRecords, List<Employee> employeeList) throws InvalidFileFormatException {
        for (AttendanceRecord record : attendanceRecords) {
            int employeeCodeToCheck = record.getEmployeeId();

            boolean employeeCodeExists = employeeList.stream()
                    .anyMatch(employee -> employee.getEmployeeId() == (employeeCodeToCheck));

            if (!employeeCodeExists) {
                throw new InvalidFileFormatException("EmployeeCode không tồn tại: " + employeeCodeToCheck);
            }
        }
    }

    private List<WorkerAttendanceData> convertToWorkerAttendanceDataList(List<AttendanceRecord> attendanceRecordList, List<Employee> employeeList) {
        List<WorkerAttendanceData> workerAttendanceList = new ArrayList<>();

        Map<String, Map<String, WorkerAttendanceData>> workerAttendanceMap = new HashMap<>();

        for (AttendanceRecord record : attendanceRecordList) {
            String employeeId = String.valueOf(record.getEmployeeId());
            String dateString = record.getFormattedDate();

            if (!workerAttendanceMap.containsKey(employeeId)) {
                workerAttendanceMap.put(employeeId, new HashMap<>());
            }

            if (!workerAttendanceMap.get(employeeId).containsKey(dateString)) {
                Optional<Employee> matchingEmployee = employeeList.stream()
                        .filter(employee -> employee.getEmployeeId() == record.getEmployeeId())
                        .findFirst();

                if (matchingEmployee.isPresent()) {
                    Employee employee = matchingEmployee.get();
                    RoleEmployee role = employee.getRole();

                    if (RoleEmployee.CONG_NHAN.equals(role)) {
                        WorkerAttendanceData workerAttendanceData = new WorkerAttendanceData(
                                Integer.parseInt(record.getEmployeeIdInString())
                        );
                        workerAttendanceMap.get(employeeId).put(dateString, workerAttendanceData);
                        workerAttendanceData.setDate(record.getDateFromTimestamp());
                        workerAttendanceList.add(workerAttendanceData);
                    }
                }
            }
        }

        return workerAttendanceList;
    }

    private List<OfficerAttendanceData> convertToOfficerAttendanceDataList(List<AttendanceRecord> attendanceRecordList, List<Employee> employeeList) {
        List<OfficerAttendanceData> officerAttendanceList = new ArrayList<>();

        Map<String, Map<String, OfficerAttendanceData>> officerAttendanceMap = new HashMap<>();

        for (AttendanceRecord record : attendanceRecordList) {
            String employeeId = String.valueOf(record.getEmployeeId());
            String dateString = record.getFormattedDate();

            if (!officerAttendanceMap.containsKey(employeeId)) {
                officerAttendanceMap.put(employeeId, new HashMap<>());
            }

            if (!officerAttendanceMap.get(employeeId).containsKey(dateString)) {
                Optional<Employee> matchingEmployee = employeeList.stream()
                        .filter(employee -> employee.getEmployeeId() == record.getEmployeeId())
                        .findFirst();

                if (matchingEmployee.isPresent()) {
                    Employee employee = matchingEmployee.get();
                    RoleEmployee role = employee.getRole();

                    if (RoleEmployee.NHAN_VIEN.equals(role)) {
                        OfficerAttendanceData officerAttendanceData = new OfficerAttendanceData(
                                record.getEmployeeId()
                        );
                        officerAttendanceMap.get(employeeId).put(dateString, officerAttendanceData);
                        officerAttendanceData.setDate(record.getDateFromTimestamp());
                        officerAttendanceList.add(officerAttendanceData);
                    }
                }
            }
        }

        return officerAttendanceList;
    }

    private List<AttendanceRecord> getAttendanceRecordsForEmployee(WorkerAttendanceData worker, List<AttendanceRecord> allAttendanceRecords) {
        List<AttendanceRecord> result = new ArrayList<>();

        for (AttendanceRecord record : allAttendanceRecords) {
            if (worker.getEmployeeId() == record.getEmployeeId()) {
                result.add(record);
            }
        }

        return result;
    }
}
