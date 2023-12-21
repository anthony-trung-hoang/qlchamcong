package com.example.qlchamcong.HRSystem;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.HRSystem.entity.Employee;
import com.example.qlchamcong.HRSystem.entity.RoleDepartment;
import com.example.qlchamcong.HRSystem.entity.RoleEmployee;

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
        generateEmployees();
    }

    private void generateEmployees() {
        int employeeIdCounter = 1;

        for (Department department : departmentList) {
            for (int i = 0; i < department.getTotalEmployee(); i++) { // Tạo 40 nhân viên cho mỗi phòng ban
                String employeeName = "Employee "+department.getName() + employeeIdCounter++;
                RoleEmployee role = department.getType()==RoleDepartment.VAN_PHONG?RoleEmployee.NHAN_VIEN:RoleEmployee.CONG_NHAN;
                Employee employee = new Employee(employeeIdCounter, employeeName, role, department,new Date("01/01/2022"));
                employeeList.add(employee);
            }
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

    @Override
    public List<Employee> getEmployeeList(int id, Date date) {
        List<Employee> employees = new ArrayList<>();
       for (Employee employee :employeeList){
           if(employee.getDepartment().getId()==id && employee.getDateStartWork().compareTo(date)<0) {
             employees.add(employee);
           }
       }
       return  employees;
    }

}
