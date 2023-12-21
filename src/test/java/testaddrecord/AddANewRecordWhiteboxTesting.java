package testaddrecord;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.service.viewattendancerecord.ViewAttendanceRecordsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddANewRecordWhiteboxTesting {
    static ViewAttendanceRecordsServiceClone viewAttendanceRecordsService;

    @BeforeAll
    public static void beforeAll() {
        viewAttendanceRecordsService = new ViewAttendanceRecordsServiceClone();
    }

    @Test
    public void timeKeeperNotFoundTest() throws ParseException {
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2023-01-01");
        Timestamp timestamp = new Timestamp(date.getTime());
        attendanceRecord.setTimestamp(timestamp);


        attendanceRecord.setTimeKeeperCode("abc");
        attendanceRecord.setEmployeeId(1);

        Assertions.assertThrows(ViewAttendanceRecordsService.TimeKeeperNotFoundException.class, () -> viewAttendanceRecordsService.createANewRecord(attendanceRecord));
    }

    @Test
    void MaxRecordsExceededExceptionTest() throws ParseException {
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2023-01-01");
        Timestamp timestamp = new Timestamp(date.getTime());
        attendanceRecord.setTimestamp(timestamp);

        attendanceRecord.setTimeKeeperCode("abc1");
        attendanceRecord.setEmployeeId(1);

        Assertions.assertThrows(ViewAttendanceRecordsService.MaxRecordsExceededException.class, () -> viewAttendanceRecordsService.createANewRecord(attendanceRecord));
    }

    @Test
    void positiveCase() throws ParseException {
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2023-01-01");
        Timestamp timestamp = new Timestamp(date.getTime());
        attendanceRecord.setTimestamp(timestamp);

        attendanceRecord.setTimeKeeperCode("abc1");
        attendanceRecord.setEmployeeId(4);

        Assertions.assertDoesNotThrow(() -> viewAttendanceRecordsService.createANewRecord(attendanceRecord));
    }
}
