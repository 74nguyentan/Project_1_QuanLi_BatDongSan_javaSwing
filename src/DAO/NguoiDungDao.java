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
import model.NguoiDung;

/**
 *
 * @author exfic
 */
public class NguoiDungDao {

    public void insertND(NguoiDung model) {
        String sql = "insert into Userr (NameUser,UserName,Password,DiaChi,Sdt,Email,GioiTinh,ChucVu,HinhAnh,Role) values (?,?,?,?,?,?,?,?,?,?)";
        jdbcDao.executeUpdate(sql,
                model.getNameNguoiDung(),
                model.getUserName(),
                model.getPassWord(),
                model.getAddress(),
                model.getNumberPhone(),
                model.getEmail(),
                model.getGender(),
                model.getChucVu(),
                model.getHinhAnh(),
                model.getRole());
    }

    public void updateND(NguoiDung model) {
        String sql = " update Userr set NameUser = ? ,UserName = ? ,Password=?,DiaChi=?,Sdt=?,Email=?,GioiTinh=?,ChucVu=?,HinhAnh=?,Role=? where IdUser =?";
        jdbcDao.executeUpdate(sql,
                model.getNameNguoiDung(),
                model.getUserName(),
                model.getPassWord(),
                model.getAddress(),
                model.getNumberPhone(),
                model.getEmail(),
                model.getGender(),
                model.getChucVu(),
                model.getHinhAnh(),
                model.getRole(),
                model.getIdNguoiDung());
    }

    public void UpdateMK(NguoiDung model) {
        String sql = " update Userr set Password=? where IdUser =?";
        jdbcDao.executeUpdate(sql, model.getPassWord(), model.getIdNguoiDung());
    }

//    public void NguoiDungUpdate(NguoiDung model) {
//        String sql = "update Userr set UserName = ?, Password = ? where  IdUser = ?";
//        jdbcDao.executeUpdate(sql, model.getUserName(), model.getPassWord(), model.getIdNguoiDung());
//    }

    public void deleteND(int id) {
        String sql = "delete from Userr where IdUser = ? ";
        jdbcDao.executeUpdate(sql, id);
    }

    public NguoiDung findAllByIdND(int IdUser) {
        String sql = "select * from Userr where IdUser =?";
        List<NguoiDung> list = select(sql, IdUser);
        return list.size() > 0 ? list.get(0) : null;
    }

    public NguoiDung findByUsernameND(String UserName) {
        String sql = "select * from Userr where UserName = ?";
        List<NguoiDung> listtt = select(sql, UserName);
        return listtt.size() > 0 ? listtt.get(0) : null;
    }

    public NguoiDung findBySdtND(String Sdt) {
        String sql = "select * from Userr where Sdt = ?";
        List<NguoiDung> listtt = select(sql, Sdt);
        return listtt.size() > 0 ? listtt.get(0) : null;
    }

    public NguoiDung findByEmailND(String Email) {
        String sql = "select * from Userr where Email = ?";
        List<NguoiDung> listtt = select(sql, Email);
        return listtt.size() > 0 ? listtt.get(0) : null;
    }

    public List<NguoiDung> select() {
        String sql = "select * from Userr";
        return select(sql);
    }

    private List<NguoiDung> select(String sql, Object... args) {
        List<NguoiDung> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcDao.executeQuery(sql, args);
                while (rs.next()) {
                    NguoiDung model = readFromResultSet(rs);
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

    public NguoiDung readFromResultSet(ResultSet rs) throws SQLException {
        NguoiDung model = new NguoiDung();
        model.setIdNguoiDung(rs.getInt("IdUser"));
        model.setNameNguoiDung(rs.getString("NameUser"));  // tên người dùng
        model.setUserName(rs.getString("UserName"));           // tên đăng nhập
        model.setPassWord(rs.getString("Password"));
        model.setAddress(rs.getString("DiaChi"));
        model.setNumberPhone(rs.getString("Sdt"));
        model.setEmail(rs.getString("Email"));
        model.setGender(rs.getBoolean("GioiTinh"));
        model.setChucVu(rs.getString("ChucVu"));
        model.setHinhAnh(rs.getString("HinhAnh"));
        model.setRole(rs.getInt("Role"));
        return model;
    }
}
