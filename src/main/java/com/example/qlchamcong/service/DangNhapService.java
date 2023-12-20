package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.repository.IEmployeeRepository;
import com.example.qlchamcong.entity.Role;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DangNhapService implements IDangNhapService {
    private IEmployeeRepository nguoiDungRepository;

    public DangNhapService(IEmployeeRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    public Role dangNhap(String username, String password) {
        Employee nguoiDung = nguoiDungRepository.timKiemTheoTenDangNhap(username);
//        if (nguoiDung != null && xacThucMatKhau(password, nguoiDung.getSalt(), nguoiDung.getMatKhau())) {
//            return nguoiDung.getVaiTro();
//        }
        return null;
    }

    private boolean xacThucMatKhau(String password, String salt, String hashedPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedBytes = md.digest(password.getBytes());
            String hashedPasswordToCompare = bytesToHex(hashedBytes);

            return hashedPassword.equals(hashedPasswordToCompare);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
