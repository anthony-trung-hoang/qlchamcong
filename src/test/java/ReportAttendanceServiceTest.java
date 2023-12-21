import com.example.qlchamcong.HRSystem.HRSystemInitializer;
import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.entity.RoleEmployee;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.entity.WorkerReportAttendance;
import com.example.qlchamcong.service.reportattendanceservice.ReportAttendanceService;
import com.example.qlchamcong.service.ServiceInitializer;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReportAttendanceServiceTest {

    private ReportAttendanceService reportAttendanceService;
    private SimpleDateFormat sdf;
    private List<Department> departmentList;
    private List<Employee> employees;
    private  Employee employee1;
    private  List<WorkerAttendanceData> attendances;
    @Before
    public void setUp() throws ParseException {
        new HRSystemInitializer();
        new ServiceInitializer();
        reportAttendanceService = new ReportAttendanceService();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        departmentList= reportAttendanceService.getDepartmentList();
        employees = new ArrayList<>();
        employee1= new Employee(1, "Bao", RoleEmployee.CONG_NHAN, departmentList.get(0), sdf.parse("2021-01-12"));
        attendances = new ArrayList();

    }


    @Test
    public void WhiteBoxTC1() throws ParseException {
        // dữ liệu đầu vào
        employees.add(employee1);
        WorkerAttendanceData workerAttendance= new WorkerAttendanceData(1, 1, sdf.parse("2021-01-12"), 5.0, 3.0, 1.0);
        attendances.add(workerAttendance);
        // hàm mình cần test
        List<WorkerReportAttendance> result= reportAttendanceService.getWorkerReportAttendance(employees,attendances,"1","2012");

        // kiểm tra tính đúng sai của test
        assertNotNull(result);
        assertEquals(1, result.size());
        WorkerReportAttendance workerReport = result.get(0);
        // Ví dụ: Kiểm tra xem thông tin trong WorkerReportAttendance có đúng không
        assertEquals(1, workerReport.getEmployeeId());
        assertEquals("1/2012", workerReport.getMonth());
        assertEquals(8.0, workerReport.getTotalWork(), 0.01);
        assertEquals(1.0, workerReport.getTotalOvertime(), 0.01);
    }
    @Test
    public void WhiteBoxTC2() throws ParseException {
        employees.add(employee1);
        WorkerAttendanceData workerAttendance= new WorkerAttendanceData(1, 2, sdf.parse("2021-01-12"), 5.0, 3.0, 1.0);
        attendances.add(workerAttendance);

        List<WorkerReportAttendance> result= reportAttendanceService.getWorkerReportAttendance(employees,attendances,"1","2012");

        assertNotNull(result);
        assertEquals(1, result.size());
        WorkerReportAttendance workerReport = result.get(0);
        // Ví dụ: Kiểm tra xem thông tin trong WorkerReportAttendance có đúng không
        assertEquals(1, workerReport.getEmployeeId());
        assertEquals("1/2012", workerReport.getMonth());
        assertEquals(0.0, workerReport.getTotalWork(), 0.01);
        assertEquals(0.0, workerReport.getTotalOvertime(), 0.01);
    }

    @Test
    public void BlackBoxTC1() throws ParseException {
        employees.add(employee1);
        WorkerAttendanceData workerAttendance= new WorkerAttendanceData(1, 1, sdf.parse("2021-01-12"), 5.0, 3.0, 1.0);
        attendances.add(workerAttendance);
        // hàm mình cần test
        List<WorkerReportAttendance> result= reportAttendanceService.getWorkerReportAttendance(employees,attendances,"1","2012");

        // kiểm tra tính đúng sai của test
        assertNotNull(result);
        assertEquals(1, result.size());
        WorkerReportAttendance workerReport = result.get(0);
        // Ví dụ: Kiểm tra xem thông tin trong WorkerReportAttendance có đúng không
        assertEquals(1, workerReport.getEmployeeId());
        assertEquals("1/2012", workerReport.getMonth());
        assertEquals(8.0, workerReport.getTotalWork(), 0.01);
        assertEquals(1.0, workerReport.getTotalOvertime(), 0.01);
    }
    @Test
    public void BlackBoxTC2() throws ParseException {
        employees.add(employee1);
        List<WorkerReportAttendance> result= reportAttendanceService.getWorkerReportAttendance(employees,attendances,"1","2012");

        assertNotNull(result);
        assertEquals(1, result.size());
        WorkerReportAttendance workerReport = result.get(0);
        // Ví dụ: Kiểm tra xem thông tin trong WorkerReportAttendance có đúng không
        assertEquals(1, workerReport.getEmployeeId());
        assertEquals("1/2012", workerReport.getMonth());
        assertEquals(0.0, workerReport.getTotalWork(), 0.01);
        assertEquals(0.0, workerReport.getTotalOvertime(), 0.01);
    }
    @Test
    public void BlackBoxTC3() throws ParseException {
        employees.add(employee1);
        WorkerAttendanceData workerAttendance= new WorkerAttendanceData(1, 2, sdf.parse("2021-01-12"), 5.0, 3.0, 1.0);
        attendances.add(workerAttendance);

        List<WorkerReportAttendance> result= reportAttendanceService.getWorkerReportAttendance(employees,attendances,"1","2012");

        assertNotNull(result);
        assertEquals(1, result.size());
        WorkerReportAttendance workerReport = result.get(0);
        // Ví dụ: Kiểm tra xem thông tin trong WorkerReportAttendance có đúng không
        assertEquals(1, workerReport.getEmployeeId());
        assertEquals("1/2012", workerReport.getMonth());
        assertEquals(0.0, workerReport.getTotalWork(), 0.01);
        assertEquals(0.0, workerReport.getTotalOvertime(), 0.01);
    }
    @Test
    public void BlackBoxTC4() throws ParseException {
        List<WorkerReportAttendance> result= reportAttendanceService.getWorkerReportAttendance(employees,attendances,"1","2012");
        assertNotNull(result);
        assertEquals(0, result.size());
    }

}
