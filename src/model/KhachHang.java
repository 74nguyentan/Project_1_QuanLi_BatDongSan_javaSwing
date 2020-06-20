package model;

public class KhachHang {
   private int idKhachHang;
   private String tenKhachHang;
   private Boolean gioiTinh;
   private String sdt;
   private String email;
   private String diaChi;
   private String AnhKH;


    public KhachHang() {
    }

    public KhachHang(int idKhachHang, String tenKhachHang, Boolean gioiTinh, String sdt, String email, String diaChi, String AnhKH) {
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.AnhKH = AnhKH;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getAnhKH() {
        return AnhKH;
    }

    public void setAnhKH(String AnhKH) {
        this.AnhKH = AnhKH;
    }

    
}
