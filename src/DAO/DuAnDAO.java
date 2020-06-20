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
import model.DuAn;

/**
 *
 * @author exfic
 */
public class DuAnDAO {

    public void insertDA(DuAn model) {
        String sql = "insert into DuAn(TenDuAn,LoaiHinh,DiaChi,DienTich,ChiPhi,MucTieu,NgayBatDau,NgayKetThuc,HinhThucQuanLi,HinhThucDauTu,IdDoiTac,TrangThai,HinhAnh) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcDao.executeUpdate(sql, model.getTenDuAn(),
                model.getLoaiHinh(),
                model.getDiaChi(),
                model.getDienTich(),
                model.getChiPhiDuAn(),
                model.getMucTieu(),
                model.getNgayBatDau(),
                model.getNgayKetThuc(),
                model.getHinhThucQuanLi(),
                model.getHinhThucDauTu(),
                model.getIdDoiTac(),
                model.getTrangThai(),
                model.getHinhAnh()
        );
    }

    public void updateDA(DuAn model) {
        String sql = "update DuAn set TenDuAn = ?,LoaiHinh = ?,DiaChi = ?,DienTich = ?,ChiPhi = ?,MucTieu= ?,NgayBatDau = ?,NgayKetThuc =?,HinhThucQuanLi = ?,HinhThucDauTu = ?,IdDoiTac = ?,TrangThai = ?,HinhAnh=? where IdDuAn = ? ";
        jdbcDao.executeUpdate(sql, model.getTenDuAn(),
                model.getLoaiHinh(),
                model.getDiaChi(),
                model.getDienTich(),
                model.getChiPhiDuAn(),
                model.getMucTieu(),
                model.getNgayBatDau(),
                model.getNgayKetThuc(),
                model.getHinhThucQuanLi(),
                model.getHinhThucDauTu(),
                model.getIdDoiTac(),
                model.getTrangThai(),
                model.getHinhAnh(),
                model.getIdDuAn()
        );
    }

    public void deleteDA(int id) {
        String sql = "delete from DuAn where IdDuAn = ?";
        jdbcDao.executeUpdate(sql, id);
    }

    public DuAn findByIdDuAn(int IdDuAn) {
        String sql = " select*from DuAn where IdDuAn = ?";
        List<DuAn> list = select(sql, IdDuAn);
        return list.size() > 0 ? list.get(0) : null;
    }

    public DuAn finByNameDA(String name) {
        String sql = " select * from DuAn where TenDuAn = ?";
        List<DuAn> list = select(sql, name);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<DuAn> selectDA() {
        String sql = " select * from DuAn ";
        return select(sql);
    }

    private List<DuAn> select(String sql, Object... args) {
        List<DuAn> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcDao.executeQuery(sql, args);
                while (rs.next()) {
                    DuAn model = readDuAn(rs);
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

    public DuAn readDuAn(ResultSet rs) throws SQLException {   // model or sql
        DuAn model = new DuAn();
        model.setIdDuAn(rs.getInt("IdDuAn"));
        model.setTenDuAn(rs.getString("TenDuAn"));
        model.setLoaiHinh(rs.getString("LoaiHinh"));
        model.setDiaChi(rs.getString("DiaChi"));
        model.setDienTich(rs.getDouble("DienTich"));
        model.setChiPhiDuAn(rs.getDouble("ChiPhi"));
        model.setMucTieu(rs.getString("MucTieu"));
        model.setNgayBatDau(rs.getDate("NgayBatDau"));
        model.setNgayKetThuc(rs.getDate("NgayKetThuc"));
        model.setHinhThucQuanLi(rs.getString("HinhThucQuanLi"));
        model.setHinhThucDauTu(rs.getString("hinhThucDauTu"));
        model.setIdDoiTac(rs.getInt("IdDoiTac"));
        model.setTrangThai(rs.getString("TrangThai"));
        model.setHinhAnh(rs.getString("HinhAnh"));
        return model;
    }
}
