package model;

import java.util.Date;

public class SanPham {

    private int idSanPham;
    private String tenSanPham;
    private int idDuAn;
    // private String tenDuAn;
    private String diaChi;
    private Double dienTich;
    private Double giaTien;
    private String moTa;      // phòng ở tầng bao nhiêu, vị thế, thuận lợi…
    private Date ngayTao;
    private Date ngayBan;
    private String chiTiet;   // đang xây dựng , đã hoàn thành..
    private String trangThai; // đã bán, chưa bán
    private int idKhachHang; // nguoi mua sp
    private String AnhSP;

    public SanPham() {
    }

    public SanPham(int idSanPham, String tenSanPham, int idDuAn, String diaChi, Double dienTich, Double giaTien, String moTa, Date ngayTao, Date ngayBan, String chiTiet, String trangThai, int idKhachHang, String AnhSP) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idDuAn = idDuAn;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
        this.giaTien = giaTien;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.ngayBan = ngayBan;
        this.chiTiet = chiTiet;
        this.trangThai = trangThai;
        this.idKhachHang = idKhachHang;
        this.AnhSP = AnhSP;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getIdDuAn() {
        return idDuAn;
    }

    public void setIdDuAn(int idDuAn) {
        this.idDuAn = idDuAn;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Double getDienTich() {
        return dienTich;
    }

    public void setDienTich(Double dienTich) {
        this.dienTich = dienTich;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getAnhSP() {
        return AnhSP;
    }

    public void setAnhSP(String AnhSP) {
        this.AnhSP = AnhSP;
    }

}
