package model;

import java.io.InputStream;

public class DoiTac {
    private int idDoiTac;
    private String tenDoitac;
    private String linhVuc;
    private String diaChi;
    private String sdt;
    private String email;
 //   private InputStream logo;
    private double soVonDaDauTu;  // số vốn đã đầu tư
    private String AnhDT;

    public DoiTac() {
    }

    public DoiTac(int idDoiTac, String tenDoitac, String linhVuc, String diaChi, String sdt, String email, double soVonDaDauTu, String AnhDT) {
        this.idDoiTac = idDoiTac;
        this.tenDoitac = tenDoitac;
        this.linhVuc = linhVuc;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.soVonDaDauTu = soVonDaDauTu;
        this.AnhDT = AnhDT;
    }

    public int getIdDoiTac() {
        return idDoiTac;
    }

    public void setIdDoiTac(int idDoiTac) {
        this.idDoiTac = idDoiTac;
    }

    public String getTenDoitac() {
        return tenDoitac;
    }

    public void setTenDoitac(String tenDoitac) {
        this.tenDoitac = tenDoitac;
    }

    public String getLinhVuc() {
        return linhVuc;
    }

    public void setLinhVuc(String linhVuc) {
        this.linhVuc = linhVuc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSoVonDaDauTu() {
        return soVonDaDauTu;
    }

    public void setSoVonDaDauTu(double soVonDaDauTu) {
        this.soVonDaDauTu = soVonDaDauTu;
    }

    public String getAnhDT() {
        return AnhDT;
    }

    public void setAnhDT(String AnhDT) {
        this.AnhDT = AnhDT;
    }

}
