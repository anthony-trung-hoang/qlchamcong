package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.RoleEmployee;

public interface IDangNhapService {
    RoleEmployee dangNhap(String username, String password);
}
