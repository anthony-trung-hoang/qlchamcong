package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.Role;

public interface IDangNhapService {
    Role dangNhap(String username, String password);
}
