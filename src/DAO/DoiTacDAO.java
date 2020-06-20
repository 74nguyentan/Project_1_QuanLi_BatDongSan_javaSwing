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
import model.DoiTac;

/**
 *
 * @author exfic
 */
public class DoiTacDAO {

    public void insertDT(DoiTac model) {
        String sql = " insert into DoiTac(TenDoiTac,LinhVuc,DiaChi,Sdt,Email,SoVonDaDauTu,AnhDT) values (?,?,?,?,?,?,?)";
        jdbcDao.executeUpdate(sql, model.getTenDoitac(), model.getLinhVuc(), model.getDiaChi(), model.getSdt(), model.getEmail(), model.getSoVonDaDauTu(), model.getAnhDT());
    }

    public void updateDT(DoiTac model) {
        String sql = "update DoiTac set TenDoiTac =? ,LinhVuc =?,DiaChi =?,Sdt=?,Email=?,SoVonDaDauTu=?,AnhDT = ? where IdDoiTac = ?";
        jdbcDao.executeUpdate(sql, model.getTenDoitac(), model.getLinhVuc(), model.getDiaChi(), model.getSdt(), model.getEmail(), model.getSoVonDaDauTu(), model.getAnhDT(), model.getIdDoiTac());
    }

    public void deleteDT(int id) {
        String sql = "delete from DoiTac where IdDoiTac =?";
        jdbcDao.executeUpdate(sql, id);
    }

    public DoiTac findByIdDT(int IdDoiTac) {
        String sql = "select * from DoiTac where IdDoiTac = ?";
        List<DoiTac> list = select(sql, IdDoiTac);
        return list.size() > 0 ? list.get(0) : null;
    }

    public DoiTac findByNameDT(String TenDoiTac) {
        String sql = "select * from DoiTac where TenDoiTac = ?";
        List<DoiTac> list = select(sql, TenDoiTac);
        return list.size() > 0 ? list.get(0) : null;
    }

    public DoiTac findBySdtDT(String Sdt) {
        String sql = " select * from DoiTac where Sdt =?";
        List<DoiTac> list = select(sql, Sdt);
        return list.size() > 0 ? list.get(0) : null;
    }

    public DoiTac findByEmailDT(String Email) {
        String sql = "select * from DoiTac where Email = ?";
        List<DoiTac> list = select(sql, Email);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<DoiTac> selectDT() {
        String sql = "select * from DoiTac";
        return select(sql);
    }

    private List<DoiTac> select(String sql, Object... args) {
        List<DoiTac> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcDao.executeQuery(sql, args);
                while (rs.next()) {
                    DoiTac model = readRSDoiTac(rs);
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

    public DoiTac readRSDoiTac(ResultSet rs) throws SQLException {
        DoiTac model = new DoiTac();
        model.setIdDoiTac(rs.getInt("IdDoiTac"));
        model.setTenDoitac(rs.getString("TenDoiTac"));
        model.setLinhVuc(rs.getString("LinhVuc"));
        model.setDiaChi(rs.getString("DiaChi"));
        model.setSdt(rs.getString("Sdt"));
        model.setEmail(rs.getString("Email"));
        model.setSoVonDaDauTu(rs.getDouble("SoVonDaDauTu"));
        model.setAnhDT(rs.getString("AnhDT"));
        return model;
    }
}
