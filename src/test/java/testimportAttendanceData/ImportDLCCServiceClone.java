package testimportAttendanceData;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.entity.RoleEmployee;
import com.example.qlchamcong.entity.Timekeeper;
import com.example.qlchamcong.exception.ConflictSavedAttendanceRecord;
import com.example.qlchamcong.exception.InvalidFileFormatException;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class TimekeeperRepository {

    public boolean checkTimeKeeperCodeExists(String code) {
        return code.equals("abc1") || code.equals("abc2");
    }


    public Timekeeper getTimekeeperByCode(String timekeeperCodesValue) {
        if (timekeeperCodesValue == "CI001") {
            return new Timekeeper(1, "CI001", "checkin");
        } else {
            return new Timekeeper(2, "CO001", "checkout");
        }
    }
}

class HRSystemAPIService {
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = null;
        for (int i = 0; i < 15; i++) {
            Employee employee = new Employee(20172020 + i, RoleEmployee.CONG_NHAN);
            employeeList.add(employee);
        }
        return employeeList;
    }
}

class AttendanceRecordRepository {

    public int getNumberOfRecordsInADayByDateAndEmployee(int employeeId, Date date) {
        if (employeeId == 1) {
            return 6;
        } else if (employeeId == 2) {
            return 2;
        } else if (employeeId == 3) {
            return 4;
        } else if (employeeId == 4) {
            return 2;
        } else {
            return 0;
        }
    }

    public void createANewRecord(AttendanceRecord newRecord) {
        System.out.println("Creating new attendance record...");
    }

    public AttendanceRecord getLatestAttendanceRecordByTimeKeeperId(int id) {
        return null;
    }
}

public class ImportDLCCServiceClone {

    private final TimekeeperRepository timekeeperRepository;
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final HRSystemAPIService hrSystemAPIService;

    public ImportDLCCServiceClone() {
        this.timekeeperRepository = new TimekeeperRepository();
        this.attendanceRecordRepository = new AttendanceRecordRepository();
        this.hrSystemAPIService = new HRSystemAPIService();
    }

    public List<AttendanceRecord> readAttendanceRecords(List<String[]> data, String timekeeperCodesValue) throws InvalidFileFormatException {
        if (data == null || data.size() < 2 || data.get(0).length < 2) {
            throw new InvalidFileFormatException("File không đủ 2 trường dữ liệu.");
        }

        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        Timekeeper timekeeper = timekeeperRepository.getTimekeeperByCode(timekeeperCodesValue);
        try {
            for (int i = 1; i < data.size(); i++) {
                String[] row = data.get(i);
                if (row.length != 2) {
                    throw new InvalidFileFormatException("Dòng " + (i + 1) + " không đúng định dạng. Cần có đúng 2 cột.");
                }

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
}
