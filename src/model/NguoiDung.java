package model;

public class NguoiDung {
    private int idNguoiDung;
    private String nameNguoiDung; // tên người dùng
    private String userName;      // tên đăng nhập
    private String passWord;
    private String address;
    private String numberPhone;
    private String email;
    private Boolean gender;
    private String chucVu;
    private String hinhAnh;
    private int role;

    public NguoiDung() {
    }

    public NguoiDung(int idNguoiDung, String nameNguoiDung, String userName, String passWord, String address, String numberPhone, String email, Boolean gender, String chucVu, String hinhAnh, int role) {
        this.idNguoiDung = idNguoiDung;
        this.nameNguoiDung = nameNguoiDung;
        this.userName = userName;
        this.passWord = passWord;
        this.address = address;
        this.numberPhone = numberPhone;
        this.email = email;
        this.gender = gender;
        this.chucVu = chucVu;
        this.hinhAnh = hinhAnh;
        this.role = role;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getNameNguoiDung() {
        return nameNguoiDung;
    }

    public void setNameNguoiDung(String nameNguoiDung) {
        this.nameNguoiDung = nameNguoiDung;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
