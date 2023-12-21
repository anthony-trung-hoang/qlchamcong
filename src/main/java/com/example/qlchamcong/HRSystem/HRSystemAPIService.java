package com.example.qlchamcong.HRSystem;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.HRSystem.entity.RoleDepartment;
import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.entity.RoleEmployee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HRSystemAPIService implements IHRSystemAPIService {
    List<Department> departmentList = new ArrayList<>();
    List<Employee> employeeList = new ArrayList<>();

    public HRSystemAPIService() {
        departmentList.add(new Department(1, "IT", 50, new Date("12/01/2022"), RoleDepartment.VAN_PHONG));
        departmentList.add(new Department(2, "HR", 20, new Date("10/01/2022"), RoleDepartment.VAN_PHONG));
        departmentList.add(new Department(3, "Finance", 20, new Date("10/01/2022"), RoleDepartment.VAN_PHONG));
        departmentList.add(new Department(4, "Factory", 80, new Date("10/01/2022"), RoleDepartment.NHA_MAY));
        departmentList.add(new Department(5, "Design", 30, new Date("10/01/2022"), RoleDepartment.VAN_PHONG));
        generateEmployeeHung();
    }

    private void generateEmployees() {
        int employeeIdCounter = 1;

        for (Department department : departmentList) {
            for (int i = 0; i < department.getTotalEmployee(); i++) { // Tạo 40 nhân viên cho mỗi phòng ban
                String employeeName = "Employee "+department.getName() + employeeIdCounter++;
                Employee employee = new Employee(employeeIdCounter, employeeName, RoleEmployee.CONG_NHAN, department,new Date("12/01/2022"));
                employeeList.add(employee);
            }
        }
    }

    private void generateEmployeeHung() {
        for (int i = 0; i < 15; i++) {
            Employee employee = new Employee(20172020 + i, RoleEmployee.CONG_NHAN);
            employeeList.add(employee);
        }
    }

    @Override
    public List<Department> getDeparmentList() {
        return departmentList;
    }

    @Override
    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
