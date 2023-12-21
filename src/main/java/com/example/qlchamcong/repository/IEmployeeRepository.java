package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.Employee;

import java.util.List;

public interface IEmployeeRepository {
    void themEmployee(Employee item);
    void xoaEmployee(Employee item);
    void suaEmployee(Employee item);
    void capNhatEmployee(Employee item);
    List<Employee> layTatCaEmployee();
    Employee timKiemTheoTen(String name);
    Employee timKiemTheoTenDangNhap(String username);
    String getRoleById(int employeeId);
}
