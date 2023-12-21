package com.example.qlchamcong.service.login;

import com.example.qlchamcong.entity.RoleEmployee;

public interface IDangNhapService {
    RoleEmployee dangNhap(String username, String password);
}
