package com.example.qlchamcong.dangnhap;

import com.example.qlchamcong.entity.RoleEmployee;

public interface IDangNhapController {
    RoleEmployee dangNhap(String username, String password);
}
