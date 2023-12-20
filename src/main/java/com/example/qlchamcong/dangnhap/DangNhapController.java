package com.example.qlchamcong.dangnhap;

import com.example.qlchamcong.entity.RoleEmployee;
import com.example.qlchamcong.service.IDangNhapService;
import com.example.qlchamcong.service.ServiceInitializer;

public class DangNhapController implements IDangNhapController {
    private static IDangNhapService dangNhapService;

    public DangNhapController() {
        this.dangNhapService = ServiceInitializer.getDangNhapService();
    }

    @Override
    public RoleEmployee dangNhap(String username, String password) {
        return null;
    }
}
