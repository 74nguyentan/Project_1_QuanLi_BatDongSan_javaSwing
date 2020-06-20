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
import model.KhachHang;

/**
 *
 * @author exfic
 */
public class KhachHangDAO {

    public void insertKH(KhachHang model) {
        String sql = "insert into KhachHang (TenKhachHang,GioiTinh ,DiaChi ,Sdt ,Email,AnhKH ) values (?,?,?,?,?,?)";
        jdbcDao.executeUpdate(sql, model.getTenKhachHang(), model.getGioiTinh(), model.getDiaChi(), model.getSdt(), model.getEmail(),model.getAnhKH());
    }

    public void updateKH(KhachHang model) {
        String sql = " update KhachHang set TenKhachHang = ?,GioiTinh =? ,DiaChi =?,Sdt =? ,Email =? ,AnhKH = ? where IdKhachHang=?";
        jdbcDao.executeUpdate(sql, model.getTenKhachHang(), model.getGioiTinh(), model.getDiaChi(), model.getSdt(), model.getEmail(),model.getAnhKH(),model.getIdKhachHang());
    }

    public void deleteKH(int IdKhachHang) {
        String sql = "delete from KhachHang where IdKhachHang=?";
        jdbcDao.executeUpdate(sql, IdKhachHang);
    }

    public List<KhachHang> selectKH() {
        String sql = "select * from KhachHang";
        return select(sql);
    }

    public KhachHang findByIDKH(int IdKhachHang) {    // String
        String sql = "select*from KhachHang where IdkhachHang =?";
        List<KhachHang> list = select(sql, IdKhachHang);
        return list.size() > 0 ? list.get(0) : null;
    }

    public KhachHang findByNameKH(String TenKhachHang) {    // String
        String sql = "select*from KhachHang where TenKhachHang =?";
        List<KhachHang> list = select(sql, TenKhachHang);
        return list.size() > 0 ? list.get(0) : null;
    }
    public KhachHang findBysdtKH(String Sdt) {    // String
        String sql = "select*from KhachHang where Sdt =?";
        List<KhachHang> list = select(sql, Sdt);
        return list.size() > 0 ? list.get(0) : null;
    }
    public KhachHang findByEmailKH(String Email) {    // String
        String sql = "select*from KhachHang where Email =?";
        List<KhachHang> list = select(sql, Email);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<KhachHang> select(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcDao.executeQuery(sql, args);
                while (rs.next()) {
                    KhachHang model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private KhachHang readFromResultSet(ResultSet rs) throws SQLException {
        KhachHang model = new KhachHang();
        model.setIdKhachHang(rs.getInt("IdKhachHang"));
        model.setTenKhachHang(rs.getString("TenKhachHang"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setDiaChi(rs.getString("DiaChi"));
        model.setSdt(rs.getString("Sdt"));
        model.setEmail(rs.getString("Email"));
        model.setAnhKH(rs.getString("AnhKH"));
        return model;
    }

}
