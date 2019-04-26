package com.canhdinh.bai4_tuan7;

public class SanPham {

    String tensp;
    private Integer gia;
    byte[] hinhanh;

    SanPham(String tensp, Integer gia, byte[] hinhanh) {
        this.tensp = tensp;
        this.gia = gia;
        this.hinhanh = hinhanh;
    }
}
