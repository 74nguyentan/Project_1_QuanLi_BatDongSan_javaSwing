/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SanPham;

/**
 *
 * @author exfic
 */
public class SanPhamDao {

    public void insertSP(SanPham model) {
        String sql = "insert into SanPham(TenSanPham,IdDuAn,DiaChi,DienTich,GiaTien,MoTa,NgayTao,NgayBan,ChiTiet,trangThai,IdKhachHang,HinhAnh) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcDao.executeUpdate(sql, model.getTenSanPham(),
                model.getIdDuAn(),
                model.getDiaChi(),
                model.getDienTich(),
                model.getGiaTien(),
                model.getMoTa(),
                model.getNgayTao(),
                model.getNgayBan(),
                model.getChiTiet(),
                model.getTrangThai(),
                model.getIdKhachHang(),
                model.getAnhSP()
        );
    }

    public void updateSP(SanPham model) {
        String sql = "update SanPham set TenSanPham = ?,IdDuAn = ?,DiaChi = ?,DienTich = ?,GiaTien = ?,MoTa= ?,NgayTao = ?,NgayBan =?,ChiTiet = ?,trangThai = ?,IdKhachHang = ?,HinhAnh = ? where IdSanPham = ? ";
        jdbcDao.executeUpdate(sql, model.getTenSanPham(),
                model.getIdDuAn(),
                model.getDiaChi(),
                model.getDienTich(),
                model.getGiaTien(),
                model.getMoTa(),
                model.getNgayTao(),
                model.getNgayBan(),
                model.getChiTiet(),
                model.getTrangThai(),
                model.getIdKhachHang(),
                model.getAnhSP(),
                model.getIdSanPham()
        );
    }

    public void deleteSP(int id) {
        String sql = "delete from SanPham where IdSanPham = ?";
        jdbcDao.executeUpdate(sql, id);
    }

    public SanPham findByIdSP(int IdDuAn) {
        String sql = " select*from SanPham where IdSanPham = ?";
        List<SanPham> list = select(sql, IdDuAn);
        return list.size() > 0 ? list.get(0) : null;
    }

    public SanPham finByNameSP(String name) {
        String sql = " select * from SanPham where TenSanPham = ?";
        List<SanPham> list = select(sql, name);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<SanPham> selectSP() {
        String sql = " select * from SanPham ";
        return select(sql);
    }

    private List<SanPham> select(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcDao.executeQuery(sql, args);
                while (rs.next()) {
                    SanPham model = readSanPham(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public SanPham readSanPham(ResultSet rs) throws SQLException {   // sql
        SanPham model = new SanPham();
        model.setIdSanPham(rs.getInt("IdSanPham"));
        model.setTenSanPham(rs.getString("TenSanPham"));
        model.setIdDuAn(rs.getInt("IdDuAn"));
        model.setDiaChi(rs.getString("DiaChi"));
        model.setDienTich(rs.getDouble("DienTich"));
        model.setGiaTien(rs.getDouble("GiaTien"));
        model.setMoTa(rs.getString("MoTa"));
        model.setNgayTao(rs.getDate("NgayTao"));
        model.setNgayBan(rs.getDate("NgayBan"));
        model.setChiTiet(rs.getString("ChiTiet"));
        model.setTrangThai(rs.getString("trangThai"));
        model.setIdKhachHang(rs.getInt("IdKhachHang"));
        model.setAnhSP(rs.getString("HinhAnh"));
        return model;
    }

}
