package testimportAttendanceData;

import com.example.qlchamcong.exception.InvalidFileFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReadAttendanceRecordWhiteboxTesting {

    static ImportDLCCServiceClone importDLCCServiceClone;


    @BeforeAll
    public static void beforeAll() {
        importDLCCServiceClone = new ImportDLCCServiceClone();
    }

    @Test
    public void NoRecordInData() throws ParseException {
        String[] row = new String[2];
        row[0] = "manhanvien";
        row[1] = "timestamp";
        List<String[]> list = new ArrayList<>();

        Assertions.assertThrows(InvalidFileFormatException.class, () -> importDLCCServiceClone.readAttendanceRecords(list, "CI001"));
    }
}
