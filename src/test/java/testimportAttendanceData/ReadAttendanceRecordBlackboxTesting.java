package testimportAttendanceData;

import com.example.qlchamcong.entity.AttendanceRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReadAttendanceRecordBlackboxTesting {
    static ImportDLCCServiceClone importDLCCServiceClone;


    @BeforeAll
    public static void beforeAll() {
        importDLCCServiceClone = new ImportDLCCServiceClone();
    }

    @Test
    public void testCase1() throws ParseException {
        String[] row = new String[2];
        row[0] = "manhanvien";
        row[1] = "timestamp";
        List<String[]> list = new ArrayList<>();
        list.add(row);
        row[0] = "20172020";
        row[1] = "1672549200000";
        list.add(row);

        Assertions.assertDoesNotThrow(() -> importDLCCServiceClone.readAttendanceRecords(list, "CI001"));
    }
}
