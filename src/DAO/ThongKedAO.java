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

/**
 *
 * @author exfic
 */
public class ThongKedAO {
    
    public List<Object[]> getDuAn(int nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDA (?)}";
                rs = jdbcDao.executeQuery(sql,nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("DuAn"),
                        rs.getInt("SoSP"),
                        rs.getInt("SoDT"),
                        rs.getInt("SoKH"),
                       rs.getDouble("DoanhThu")
                    };
                    list.add(model);

                }
            } finally {
                rs.getStatement().getConnection().close();

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
     public List<Object[]> getDoiTac() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoiTac}";
                rs = jdbcDao.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("DoiTac"),
                        rs.getInt("SoDuAnDauTu"),
                        rs.getDouble("SoVonDaDauTu")
                    };
                    list.add(model);

                }
            } finally {
                rs.getStatement().getConnection().close();

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
     public List<Object[]> getDoanhThu() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoanhThu}";
                rs = jdbcDao.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getInt("Nam"),
                        rs.getInt("SoDuAn"),
                        rs.getInt("SoSP"),
                        rs.getDouble("DoanhThu"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("TrungBinh"),
                    };
                    list.add(model);

                }
            } finally {
                rs.getStatement().getConnection().close();

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }

}
