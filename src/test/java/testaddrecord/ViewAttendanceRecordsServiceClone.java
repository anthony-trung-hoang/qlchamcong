package testaddrecord;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.service.viewattendancerecord.ViewAttendanceRecordsService;

import java.sql.Date;

class TimekeeperRepository {

    public boolean checkTimeKeeperCodeExists(String code) {
        return code.equals("abc1") || code.equals("abc2");
    }
}

class EmployeeRepository {
    public String getRoleById(int employeeId) {
        if (employeeId == 1 || employeeId == 2) {
            return "worker";
        } else {
            return "officer";
        }
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
}

public class ViewAttendanceRecordsServiceClone {

    private final TimekeeperRepository timekeeperRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceRecordRepository attendanceRecordRepository;

    public ViewAttendanceRecordsServiceClone() {
        this.timekeeperRepository = new TimekeeperRepository();
        this.employeeRepository = new EmployeeRepository();
        this.attendanceRecordRepository = new AttendanceRecordRepository();
    }

    private int getTimeKeeperIdByCode(String code) {
        if (code.equals("abc1")) {
            return 1;
        } else if (code.equals("abc2")) {
            return 2;
        } else {
            return 0;
        }
    }

    public void createANewRecord(AttendanceRecord newRecord) {
        boolean checkTimeKeeperCodeExists = timekeeperRepository.checkTimeKeeperCodeExists(newRecord.getTimeKeeperCode());
        if (checkTimeKeeperCodeExists) {
            String employeeRole = employeeRepository.getRoleById(newRecord.getEmployeeId());
            System.out.println("role" + employeeRole);
            int maxRecordAllowed = 0;
            if (employeeRole.equals("worker")) {
                maxRecordAllowed = 6;

            } else {
                maxRecordAllowed = 4;
            }
            int numberOfRecordsInDay = attendanceRecordRepository.getNumberOfRecordsInADayByDateAndEmployee(newRecord.getEmployeeId(), newRecord.getDateFromTimestamp());
            if (numberOfRecordsInDay >= maxRecordAllowed) {
                throw new ViewAttendanceRecordsService.MaxRecordsExceededException(employeeRole, maxRecordAllowed);
            } else {
                int timeKeeperId = getTimeKeeperIdByCode(newRecord.getTimeKeeperCode());
                System.out.println("Code: " + newRecord.getTimeKeeperCode() + ":" + timeKeeperId);
                newRecord.setTimeKeeperId(timeKeeperId);
                attendanceRecordRepository.createANewRecord(newRecord);
            }
        } else {
            throw new ViewAttendanceRecordsService.TimeKeeperNotFoundException("TimeKeeper with code " + newRecord.getTimeKeeperCode() + " not found.");
        }
    }

}


