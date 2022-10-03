package com.example.du_an_mau.data.model;

public class Top {
    public String tenSach;
    public int soLuong;

    public Top() {
    }

    public Top(String tenSach, int soLuong) {
        this.tenSach = tenSach;
        this.soLuong = soLuong;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong(int soLuong) {
        return this.soLuong;
    }
}
