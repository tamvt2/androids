package com.example.du_an_mau.data.model;

public class Sach {
    public int maSach, giaThue, maLoai;
    public String tenSach;

    public Sach() {
    }

    public Sach(int maSach, int giaThue, int maLoai, String tenSach) {
        this.maSach = maSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
        this.tenSach = tenSach;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
