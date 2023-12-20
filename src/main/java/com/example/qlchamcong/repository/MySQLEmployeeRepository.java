package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.Employee;

import java.sql.Connection;
import java.util.List;

public class MySQLEmployeeRepository implements IEmployeeRepository {
    private static Connection connection;

    public MySQLEmployeeRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void themEmployee(Employee item) {

    }

    @Override
    public void xoaEmployee(Employee item) {

    }

    @Override
    public void suaEmployee(Employee item) {

    }

    @Override
    public void capNhatEmployee(Employee item) {

    }

    @Override
    public List<Employee> layTatCaEmployee() {
        return null;
    }

    @Override
    public Employee timKiemTheoTen(String name) {
        return null;
    }

    @Override
    public Employee timKiemTheoTenDangNhap(String username) {
        return null;
    }
}
