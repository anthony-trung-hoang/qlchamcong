package com.example.qlchamcong.dangnhap;

import com.example.qlchamcong.entity.Role;

public interface IDangNhapController {
    Role dangNhap(String username, String password);
}
