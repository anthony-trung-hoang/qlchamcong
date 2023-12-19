package com.example.qlchamcong.entity;

public class NguoiDung {
    private String tenDangNhap;
    private String matKhau;
    private String salt;
    private Role vaiTro;

    public NguoiDung(String tenDangNhap, String matKhau, String salt, Role vaiTro) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.salt = salt;
        this.vaiTro = vaiTro;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Role getVaiTro() {
        return vaiTro;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setVaiTro(Role vaiTro) {
        this.vaiTro = vaiTro;
    }
}
