/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.DoiTac;
import model.DuAn;
import model.KhachHang;
import model.SanPham;

/**
 *
 * @author exfic
 */
public class Excel {

    public List<DoiTac> doiTacs = new ArrayList<DoiTac>();
    public List<KhachHang> khachHangs = new ArrayList<KhachHang>();
    public List<DuAn> projects = new ArrayList<DuAn>();
    public List<SanPham> products = new ArrayList<SanPham>();

    public List<DoiTac> getDoiTacs() {
        return doiTacs;
    }

    public void setDoiTacs(List<DoiTac> doiTacs) {
        this.doiTacs = doiTacs;
    }

    public List<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public void setKhachHangs(List<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }

    public List<DuAn> getProjects() {
        return projects;
    }

    public void setProjects(List<DuAn> projects) {
        this.projects = projects;
    }

    public List<SanPham> getProducts() {
        return products;
    }

    public void setProducts(List<SanPham> products) {
        this.products = products;
    }
    
    public void writeProjectToFile() throws IOException {
        File pathName = new File("data/data.txt");
        String sProject;
        try {
            FileWriter wf = new FileWriter(pathName);
            String title = "Id Dự án|Tên dự án|Loại hình|Địa chỉ|Diện tích|Chi phí dự án|Ngày bắt đầu|Ngày kết thúc|Hình thức quản lý|Hình thức đầu tư|Id đối tác|Trạng thái|Hình ảnh";
            wf.write(title + "\n");
            for (DuAn e : projects
            ) {
                sProject = e.getIdDuAn() + "|" + e.getTenDuAn() + "|" + e.getLoaiHinh() + "|" + e.getDiaChi() + "|" +
                        e.getDienTich() + "|" + e.getChiPhiDuAn() + "|" + e.getNgayBatDau() + "|" + e.getNgayKetThuc() + "|" +
                        e.getHinhThucQuanLi() + "|" + e.getHinhThucDauTu() + "|" + e.getIdDoiTac() + "|" + e.getTrangThai() + "|" + e.getHinhAnh()
                ;
                wf.write(sProject + "\n");
            }
            wf.close();
        } catch (Exception e) {
        }
        Runtime.getRuntime().exec("data/printExcel.exe", null, new File("data/"));
    }

    public void writeProductToFile() throws IOException {
            File pathName = new File("data/data.txt");
        String sProduct;
        try {
            FileWriter wf = new FileWriter(pathName);
            String title = "Id sản phẩm|Tên sản phẩm|idDuAn|Địa chỉ|Diện tích|Giá tiền|Mô tả|Ngày tạo|Ngày bán|Chi tiết|Trạng thái|ID khách hàng|Hình ảnh";
            wf.write(title + "\n");
            for (SanPham e : products
            ) {
                sProduct = e.getIdSanPham() + "|" + e.getTenSanPham() + "|" + e.getIdDuAn() + "|" + e.getDiaChi() + "|" +
                        e.getDienTich() + "|" + e.getGiaTien() + "|" + e.getMoTa() + "|" + e.getNgayTao() + "|" +
                        e.getNgayBan() +  "|" + e.getTrangThai() + "|" + e.getIdKhachHang() + "|" + e.getAnhSP()
                ;
                wf.write(sProduct + "\n");
            }
            wf.close();
        } catch (Exception e) {
        }
        Runtime.getRuntime().exec("data/printExcel.exe", null, new File("data/"));
    }

    public void writeKhachHangToFile() throws IOException {
        File pathName = new File("data/data.txt");
        String sKhachHang;
        try {
            FileWriter wf = new FileWriter(pathName);
            String title = "Id |Tên khách hàng|Giới tính|Số điện thoại|Email|Địa chỉ|Hình ảnh";
            wf.write(title + "\n");
            for (KhachHang e : khachHangs
            ) {
                sKhachHang = e.getIdKhachHang() + "|" + e.getTenKhachHang() + "|" + e.getGioiTinh() + "|" + e.getSdt() + "|" +
                        e.getEmail() + "|" + e.getDiaChi() + "|" + e.getAnhKH()
                ;
                wf.write(sKhachHang + "\n");
            }
            wf.close();
        } catch (Exception e) {
        }
        Runtime.getRuntime().exec("data/printExcel.exe", null, new File("data/"));
    }

    public void writeDoiTacToFile() throws IOException {
        File pathName = new File("data/data.txt");
        String sDoiTac;
        try {
            FileWriter wf = new FileWriter(pathName);
            String title = "Id |Tên đối tác|Lĩnh vực|Địa chỉ|Số điện thoại|Email|Số vốn đầu tư|Hình ảnh|";
            wf.write(title + "\n");
            for (DoiTac e : doiTacs
            ) {
                sDoiTac = e.getIdDoiTac() + "|" + e.getTenDoitac() + "|" + e.getLinhVuc() + "|" + e.getDiaChi() + "|" +
                        e.getSdt() + "|" + e.getEmail()+ "|" + e.getSoVonDaDauTu() + "|" + e.getAnhDT()
                ;
                wf.write(sDoiTac + "\n");
            }
            wf.close();
        } catch (Exception e) {
        }
        Runtime.getRuntime().exec("data/printExcel.exe", null, new File("data/"));
    }
}
