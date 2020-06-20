/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import AppPackage.AnimationClass;
import DAO.DoiTacDAO;
import DAO.DuAnDAO;
import DAO.KhachHangDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.Timer;
import DAO.NguoiDungDao;
import DAO.SanPhamDao;
import DAO.ThongKedAO;
import data.Excel;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DoiTac;
import model.DuAn;
import model.KhachHang;
import model.NguoiDung;
import model.SanPham;

/**
 *
 * @author exfic
 */
public class Main extends javax.swing.JFrame {

    NguoiDungDao daoND = new NguoiDungDao();
    KhachHangDAO daoKH = new KhachHangDAO();
    DoiTacDAO daoDT = new DoiTacDAO();
    DuAnDAO daoDA = new DuAnDAO();
    SanPhamDao daoSP = new SanPhamDao();
    ThongKedAO daoThKe = new ThongKedAO();

    ButtonGroup groupND;
    ButtonGroup groupKH;
    JFileChooser fileChooser = new JFileChooser();

    int indexNgDung = 0;   // id next table
    int indexKhachHang = 0;
    int indexDuAn = 0;
    int indexDoiTac = 0;
    int indexSP = 0;
    private String mk;
    private String TenNgDung;
    private int IdLogin;  // id ngdung login update password
    private int IdNG;      // id ngdung update
    private int IdKh;      // id KhachHang update
    private int IdDA;      // id DuAn update
    private int IdDT;      // id DuAn update
    private int IdSP;      // id sanPham update
    private int role;
    private int x = 0;
    private Timer tm;
    String[] list = {
        "E:/Users/NetBeansProjects/ThamKhao_Swing/Quan_Li_BDS_Form_2/src/imager/hinh/sl1.jpg",
        "E:/Users/NetBeansProjects/ThamKhao_Swing/Quan_Li_BDS_Form_2/src/imager/hinh/sl2.png",
        "E:/Users/NetBeansProjects/ThamKhao_Swing/Quan_Li_BDS_Form_2/src/imager/hinh/sl3.png",
        "E:/Users/NetBeansProjects/ThamKhao_Swing/Quan_Li_BDS_Form_2/src/imager/hinh/sl4.jpg",
        "E:/Users/NetBeansProjects/ThamKhao_Swing/Quan_Li_BDS_Form_2/src/imager/hinh/sl5.png",
        "E:/Users/NetBeansProjects/ThamKhao_Swing/Quan_Li_BDS_Form_2/src/imager/hinh/logoLogin.png",};

    public Main() {
        initComponents();

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // k cho chương trình chạy ngầm
        setResizable(false); // có thể thay đổi kích thước frame hay không, true có, false không
        this.lbl();
        this.lblHiden();
        this.time();
        this.JPaneSetOn();
        this.TextLogin();
        this.slide();
        this.group();
        txtUserName.setText("VanTan");
        txtPass.setText("123");

    }
    // in excel 

//    Excel excel = new Excel();
//    List<DuAn> projectList = new ArrayList<>();
//
//    public void printIntoExcel() throws IOException {
//        excel.setProjects(this.projectList);
//        excel.writeProjectToFile();
//    }
    void time() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date now = new Date(); //1
                SimpleDateFormat formater = new SimpleDateFormat(); //2
                formater.applyPattern("hh : mm : ss aa"); // 2
                lblTime.setText(formater.format(now));
            }

        }).start();
    }

    void slide() {
        slide.setBounds(24, 72, 758, 390);
        setImageSize(5);

        tm = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setImageSize(x);
                x += 1;
                if (x >= list.length) {
                    x = 0;
                }
            }
        });

        tm.start();
        //  setLayout(null);
    }
// set size slide

    public void setImageSize(int i) {
        ImageIcon icon = new ImageIcon(list[i]);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(slide.getWidth(), slide.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        slide.setIcon(newImc);
    }

    public static boolean saveLogo(File file) {
        File dir = new File("logos");
        // Tạo thư mục nếu chưa tồn tại
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            // Copy vào thư mục logos (đè nếu đã tồn tại)
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static ImageIcon readLogo(JLabel lbl, String fileName) {
        // đọc file ảnh
        File path = new File("logos", fileName);
        // setsize anh
        ImageIcon icon = new ImageIcon(path.getAbsolutePath());
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        //  return new ImageIcon(newImg);
        return newImc;
    }

    public void setAnhSize(JLabel lbl) {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
//            String filename = file.getName();
//            ImageIcon icon = new ImageIcon(filename);
//            Image img = icon.getImage();
//            Image newImg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
//            ImageIcon newImc = new ImageIcon(newImg);
            if (saveLogo(file)) {
                // Hiển thị hình lên form
                lbl.setIcon(readLogo(lbl, file.getName()));
                lbl.setToolTipText(file.getName());
            }

        }

    }

    void group() {

        groupND = new ButtonGroup();
        groupND.add(rdoNamND);
        groupND.add(rdoNuND);

        groupKH = new ButtonGroup();
        groupKH.add(rdoNamKH);
        groupKH.add(rdoNuKH);

    }

    void JPaneSetOn() {
        jPaneExit.setVisible(false);
        JPaneThoat.setVisible(false);
        JPaneLoogin.setVisible(false);
        JPaneLogout.setVisible(false);
        JPaneMenuLogin2.setVisible(false);
        JPaneHeThong.setVisible(false);
        JPaneQuanLi.setVisible(false);
        JPaneThongKe.setVisible(false);
        JPanePhanTich.setVisible(false);
        JPaneTaiKhoan.setVisible(false);
        JPaneGioiThieu.setVisible(false);
        JPaneAbout.setVisible(false);

        jTabbQLnguoiDung.setVisible(false);
        jTabbQLkhachHang.setVisible(false);
        jTabbQLdoiTac.setVisible(false);
        jTabbQLsanPham.setVisible(false);

    }

    void lbl() {
        lblTime.setIcon(new ImageIcon("E:\\Users\\NetBeansProjects\\ThamKhao_Swing\\Quan_Li_BDS_Form_2\\src\\imager\\Alarm.png"));
        lblGif.setIcon(new ImageIcon("E:\\Users\\NetBeansProjects\\ThamKhao_Swing\\Quan_Li_BDS_Form_2\\src\\imager\\icon\\a.gif"));
        lblGach.setSize(0, 0);
        lblBackG.setVisible(false);
        lblGach.setVisible(false);
        lblGif.setVisible(false);
        lblQLdoitac.setVisible(false);
        lblQLkhachHang.setVisible(false);
        lblQLnguoiDung.setVisible(false);
        lblQLsanPham.setVisible(false);

    }

    void lblHiden() {
        lblEye.setVisible(false);
        lblHide1.setVisible(false);
        lblHide2.setVisible(false);
        lblHide3.setVisible(false);
        lblHideLogout.setVisible(false);
        lbl_BackG_Home.setVisible(false);
    }

    void TextLogin() {
        if (txtUserName.getText().isEmpty()) {
            txtUserName.setForeground(Color.GRAY);
            txtUserName.setText("Nhập tên đăng nhập");
        }

        if (txtPass.getText().isEmpty()) {
            txtPass.setForeground(Color.GRAY);
            txtPass.setText("Nhập mật khẩu");
            txtPass.setEchoChar((char) 0);   // chuyển mật khẩu sang dạng String
        }

        if (txtUserName1.getText().isEmpty()) {
            txtUserName1.setForeground(Color.GRAY);
            txtUserName1.setText("Nhập tên đăng nhập");
        }

        if (txtPass1.getText().isEmpty()) {
            txtPass1.setForeground(Color.GRAY);
            txtPass1.setText("Nhập mật khẩu");
            txtPass1.setEchoChar((char) 0);
        }

        if (txtPassCu.getText().isEmpty()) {
            txtPassCu.setForeground(Color.GRAY);
            txtPassCu.setText("Nhập mật khẩu cũ");
            txtPassCu.setEchoChar((char) 0);
        }

        if (txtPassMoi.getText().isEmpty()) {
            txtPassMoi.setForeground(Color.GRAY);
            txtPassMoi.setText("Nhập mật khẩu mới");
            txtPassMoi.setEchoChar((char) 0);
        }

        if (txtPassXN.getText().isEmpty()) {
            txtPassXN.setForeground(Color.GRAY);
            txtPassXN.setText("Xác nhận mật khẩu mới");
            txtPassXN.setEchoChar((char) 0);
        }
    }

    void ExitPane() {

        jPaneExit.setSize(1120, 590);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        AnimationClass anim = new AnimationClass();

        anim.jLabelXLeft(left.getX(), -300, 2, 2, left);
        anim.jLabelXRight(right.getX(), 750, 2, 2, right);
        anim.jLabelYUp(top.getY(), -70, 4, 1, top);
        anim.jLabelYDown(bot.getY(), 400, 3, 1, bot);
    }

    void ThoatPane() {

        JPaneThoat.setSize(1120, 590);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        AnimationClass anim = new AnimationClass();

        anim.jLabelXLeft(left1.getX(), -300, 2, 2, left1);
        anim.jLabelXRight(right1.getX(), 750, 2, 2, right1);
        anim.jLabelYUp(top1.getY(), -70, 4, 1, top1);
        anim.jLabelYDown(bot1.getY(), 400, 3, 1, bot1);
    }

    void MenuThu() {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    for (int a = 0; a <= 20; a++) {
                        Thread.sleep(30);
                        if (a == 1) {
                            jPaneMenu.setSize(154, 590);
                        }
                        if (a == 2) {
                            jPaneMenu.setSize(148, 590);
                        }
                        if (a == 3) {
                            jPaneMenu.setSize(142, 590);
                        }
                        if (a == 4) {
                            jPaneMenu.setSize(136, 590);
                        }
                        if (a == 5) {
                            jPaneMenu.setSize(130, 590);
                        }
                        if (a == 6) {
                            jPaneMenu.setSize(124, 590);
                        }
                        if (a == 7) {
                            jPaneMenu.setSize(118, 590);
                        }
                        if (a == 8) {
                            jPaneMenu.setSize(112, 590);
                        }
                        if (a == 9) {
                            jPaneMenu.setSize(106, 590);
                        }
                        if (a == 10) {
                            jPaneMenu.setSize(100, 590);
                        }
                        if (a == 11) {
                            jPaneMenu.setSize(94, 590);
                        }
                        if (a == 12) {
                            jPaneMenu.setSize(88, 590);
                        }
                        if (a == 13) {
                            jPaneMenu.setSize(82, 590);
                        }
                        if (a == 14) {
                            jPaneMenu.setSize(76, 590);
                        }
                        if (a == 15) {
                            jPaneMenu.setSize(70, 590);
                        }
                        if (a == 16) {
                            jPaneMenu.setSize(64, 590);
                        }
                        if (a == 17) {
                            jPaneMenu.setSize(58, 590);
                        }
                        if (a == 18) {
                            jPaneMenu.setSize(52, 590);
                        }
                        if (a == 19) {
                            jPaneMenu.setSize(46, 590);
                        }

                        if (a == 20) {
                            jPaneMenu.setSize(40, 590);
                            lblGach.setSize(40, 50);
                            lblGif.setSize(0, 0);
                            lblBackG.setVisible(true);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        th.start();
    }

    // set get ngay thang
    static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("MM/dd/yyyy");

    public static String ToString(Date date, String... pattern) {
        if (pattern.length > 0) {
            DATE_FORMATER.applyPattern(pattern[0]);
        }
        return DATE_FORMATER.format(date);
    }

    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                DATE_FORMATER.applyPattern(pattern[0]);
            }
            return DATE_FORMATER.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    // login
    public void UserLogin() {
        String name = txtUserName.getText();
        String pass = new String(txtPass.getPassword());

        try {
            model.NguoiDung model = daoND.findByUsernameND(name);

            if (model != null) {
                mk = model.getPassWord();
                IdLogin = model.getIdNguoiDung();
                TenNgDung = model.getNameNguoiDung();
                if (mk.equals(pass)) {
                    role = model.getRole();
                    String nameRole = "";
                    if (role == 1) {
                        nameRole = " Quản Lí";
                    } else {
                        nameRole = " Nhân viên";
                    }
                    JOptionPane.showMessageDialog(this, " Bạn đã đăng nhập thành công với User : " + name + " " + "(" + nameRole + " )");
                    JPaneMenuLogin.setVisible(false);
                    JPaneMenuLogin2.setVisible(true);
                    // JPaneLoogin.setVisible(false);
                    JPaneHome.setVisible(true);
                    if (JPaneLoogin.isVisible() || JPaneLogout.isVisible() || jPaneExit.isVisible() || jPaneExit.isVisible() || JPaneHeThong.isVisible() || JPaneQuanLi.isVisible() || JPaneThongKe.isVisible() || JPanePhanTich.isVisible()) {
                        jPaneExit.setVisible(false);
                        JPaneThoat.setVisible(false);
                        JPaneLoogin.setVisible(false);
                        JPaneLogout.setVisible(false);
                        JPaneHeThong.setVisible(false);
                        JPaneQuanLi.setVisible(false);
                        JPaneThongKe.setVisible(false);
                        JPanePhanTich.setVisible(false);
                    }
                    if (!btnHome.isEnabled() || !btnHeThong.isEnabled() || !btnThongKe.isEnabled() || !btnPhanTich.isEnabled() || !btnQuanLi.isEnabled()) {
                        btnHome.setEnabled(true);
                        btnPhanTich.setEnabled(true);
                        btnThongKe.setEnabled(true);
                        btnQuanLi.setEnabled(true);
                        btnHeThong.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Sai password, vui lòng thử lại !");
                }
            } else {
                JOptionPane.showMessageDialog(this, " Sai tên đăng nhập, vui lòng thử lại !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Người Dùng
    public boolean checkND() {

        if (txtNDName.getText().equals("") || txtNDchucVu.getText().equals("") || txtNDdiachi.getText().equals("") || txtNDemail.getText().equals("")
                || txtNDpass.getText().equals("") || txtNDrole.getText().equals("") || txtNDsdt.getText().equals("") || txtNDtenDangNhap.getText().equals("")) {
            JOptionPane.showMessageDialog(this, " vui lòng nhập đủ thông tin");
            return false;
        } else if (!(txtNDsdt.getText()).matches("^0\\d{9}$")) {
            JOptionPane.showMessageDialog(this, "vui lòng nhập đúng định dạng SĐT 10 số");
            return false;
        } else if (!(txtNDemail.getText()).matches("\\w+@\\w+\\.\\w+")) {
            JOptionPane.showMessageDialog(this, " vui lòng nhập đúng định dạng email");
            return false;
        } else if (!rdoNamND.isSelected() && !rdoNuND.isSelected()) {
            JOptionPane.showMessageDialog(this, " vui lòng chọn giới tính !");
            return false;
        }

        return true;
    }

    void loadNgDung() {
        DefaultTableModel ngdung = (DefaultTableModel) tblNguoiDung.getModel();
        ngdung.setRowCount(0);
        try {
            List<NguoiDung> listnd = daoND.select();
            for (NguoiDung nd : listnd) {
                Object[] row = {
                    nd.getIdNguoiDung(),
                    nd.getNameNguoiDung(),
                    nd.getUserName(),
                    nd.getPassWord(),
                    nd.getAddress(),
                    nd.getNumberPhone(),
                    nd.getEmail(),
                    nd.getGender(),
                    nd.getChucVu(),
                    nd.getHinhAnh(),
                    nd.getRole()
                };
                ngdung.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu ", "Inane error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void insertNgDung() {
        NguoiDung model = getModelND();
        try {
            daoND.insertND(model);
            this.loadNgDung();
            JOptionPane.showMessageDialog(this, " Thêm mới thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " Thêm mới thất bại", "Inane error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void updateND() {
        NguoiDung model = getModelUpdateND();
        try {
            daoND.updateND(model);
            this.loadNgDung();
            JOptionPane.showMessageDialog(this, "Update thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Update thất bại, vui lòng thử lại !", "Inane error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void clearND() {
        txtNDName.setText("");
        txtNDchucVu.setText("");
        txtNDdiachi.setText("");
        txtNDemail.setText("");
        txtNDpass.setText("");
        txtNDrole.setText("");
        txtNDsdt.setText("");
        txtNDtenDangNhap.setText("");
        groupND.clearSelection();
        lblAnhND.setToolTipText(null);
        lblAnhND.setIcon(null);
    }

    void deleteND() {
        int kt = JOptionPane.showConfirmDialog(this, "bạn muốn xóa hay không ? ", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (kt == JOptionPane.YES_OPTION) {
            try {
                daoND.deleteND(IdNG);
                this.loadNgDung();
                this.clearND();
                this.setStatusND(true);
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Xóa thất bại !", "Inane error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void EditND() {
        try {
            int idngDung = (Integer) tblNguoiDung.getValueAt(this.indexNgDung, 0);
            NguoiDung modell1 = daoND.findAllByIdND(idngDung);
            IdNG = idngDung;
            if (modell1 != null) {
                this.setModelND(modell1);
                this.setStatusND(false);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setModelND(NguoiDung modelND) {
        txtNDName.setText(modelND.getNameNguoiDung());
        txtNDtenDangNhap.setText(modelND.getUserName());
        txtNDpass.setText(modelND.getPassWord());
        txtNDdiachi.setText(modelND.getAddress());
        txtNDsdt.setText(modelND.getNumberPhone());
        txtNDemail.setText(modelND.getEmail());
        txtNDchucVu.setText(modelND.getChucVu());
        txtNDrole.setText(String.valueOf(modelND.getRole()));

        lblAnhND.setToolTipText(modelND.getHinhAnh());
        if (modelND.getHinhAnh() != null) {
            lblAnhND.setIcon(readLogo(lblAnhND, modelND.getHinhAnh()));
        }

        if (modelND.getGender() == true) {
            rdoNamND.setSelected(true);
        } else {
            rdoNuND.setSelected(true);
        }
    }

    NguoiDung getModelND() {
        NguoiDung modelND = new NguoiDung();

        modelND.setNameNguoiDung(txtNDName.getText());
        modelND.setUserName(txtNDtenDangNhap.getText());
        modelND.setPassWord(txtNDpass.getText());
        modelND.setAddress(txtNDdiachi.getText());
        modelND.setNumberPhone(txtNDsdt.getText());
        modelND.setEmail(txtNDemail.getText());
        modelND.setChucVu(txtNDchucVu.getText());
        modelND.setRole(Integer.valueOf(txtNDrole.getText()));
        modelND.setHinhAnh(lblAnhND.getToolTipText());
        boolean gt;
        if (rdoNamND.isSelected()) {
            gt = true;
        } else {
            gt = false;
        }
        modelND.setGender(gt);
        return modelND;
    }

    NguoiDung getModelUpdateND() {
        NguoiDung modelND = new NguoiDung();
        modelND.setIdNguoiDung(IdNG);
        modelND.setNameNguoiDung(txtNDName.getText());
        modelND.setUserName(txtNDtenDangNhap.getText());
        modelND.setPassWord(txtNDpass.getText());
        modelND.setAddress(txtNDdiachi.getText());
        modelND.setNumberPhone(txtNDsdt.getText());
        modelND.setEmail(txtNDemail.getText());
        modelND.setChucVu(txtNDchucVu.getText());
        modelND.setRole(Integer.valueOf(txtNDrole.getText()));
        modelND.setHinhAnh(lblAnhND.getToolTipText());
        boolean gt;
        if (rdoNamND.isSelected()) {
            gt = true;
        } else {
            gt = false;
        }
        modelND.setGender(gt);
        return modelND;
    }

    void setStatusND(Boolean insertTable) {

        btnThemND.setEnabled(insertTable);
        btnSuaND.setEnabled(!insertTable);
        btnXoaND.setEnabled(!insertTable);
        boolean first = this.indexNgDung > 0;
        boolean last = this.indexNgDung < tblNguoiDung.getRowCount() - 1;
        btnL_ND.setEnabled(!insertTable && first);
        btnNL_ND.setEnabled(!insertTable && first);
        btnR_ND.setEnabled(!insertTable && last);
        btnNR_ND.setEnabled(!insertTable && last);

    }

    // đổi mật khẩu người dùng
    void DoiMK() {
        NguoiDung ng = getModelDoiMatKhau();
        if ((txtPassCu.getText()).equals(mk)) {
            if ((txtPassMoi.getText()).equals(txtPassXN.getText())) {
                try {
                    daoND.UpdateMK(ng);
                    //  this.loadNgDung();
                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, " Đổi mật khảu thất bại, vui lòng thử lại !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu mới chưa trùng khớp !");

            }
        } else {
            JOptionPane.showMessageDialog(this, "mật khẩu cũ chưa đúng !");
        }
    }

    NguoiDung getModelDoiMatKhau() {
        NguoiDung ngdung = new NguoiDung();
        ngdung.setIdNguoiDung(IdLogin);
        ngdung.setNameNguoiDung(TenNgDung);
        ngdung.setPassWord(txtPassXN.getText());
        return ngdung;
    }

    // khách hàng
    void loadKH() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            List<KhachHang> listKH = daoKH.selectKH();
            for (KhachHang kh : listKH) {
                Object[] row = {
                    kh.getIdKhachHang(),
                    kh.getTenKhachHang(),
                    kh.getDiaChi(),
                    kh.getSdt(),
                    kh.getEmail(),
                    kh.getGioiTinh(),
                    kh.getAnhKH(),};
                model.addRow(row);

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " lỗi truy vấn dữ liệu", "error", JOptionPane.ERROR_MESSAGE);
        }

    }

    void insertKH() {
        KhachHang model = getModelKH();
        try {
            daoKH.insertKH(model);
            this.loadKH();
            JOptionPane.showMessageDialog(this, " thêm mới thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " thêm mới thất bại !", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void updateKH() {
        KhachHang model = getModelUpdateKH();
        try {
            daoKH.updateKH(model);
            this.loadKH();
            JOptionPane.showMessageDialog(this, " update thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " update thất bại, vui lòng thử lại !", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void deleteKH() {
        int kt = JOptionPane.showConfirmDialog(this, " bạn thực sự muốn xóa ", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (kt == JOptionPane.YES_OPTION) {
            try {
                daoKH.deleteKH(IdKh);
                this.loadKH();
                this.clearKH();
                this.setStatusKH(true);
                JOptionPane.showMessageDialog(this, "Xóa thành công !");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, " xóa thất bại !", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void EditKH() {
        try {
            int id = (Integer) tblKhachHang.getValueAt(this.indexKhachHang, 0);
            KhachHang model = daoKH.findByIDKH(id);
            IdKh = id;
            if (model != null) {
                this.setModelKH(model);
                this.setStatusKH(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tru vấn dữ liệu", "error", JOptionPane.ERROR_MESSAGE);
        }

    }

    void setStatusKH(Boolean khTable) {
        btnKH_Them.setEnabled(khTable);
        btnKH_Sua.setEnabled(!khTable);
        btnKH_Xoa.setEnabled(!khTable);
        Boolean fir = this.indexKhachHang > 0;
        Boolean las = this.indexKhachHang < tblKhachHang.getRowCount() - 1;
        btnNL_KH.setEnabled(!khTable && fir);
        btnL_KH.setEnabled(!khTable && fir);
        btnR_KH.setEnabled(!khTable && las);
        btnNR_KH.setEnabled(!khTable && las);
    }

    void clearKH() {
        txtKH_Name.setText("");
        txtKH_SDT.setText("");
        txtKH_Email.setText("");
        txtKH_DiaChi1.setText("");
        lblAnhKH.setToolTipText(null);
        lblAnhKH.setIcon(null);
        groupKH.clearSelection();
    }

    void setModelKH(KhachHang modelKH) {
        txtKH_Name.setText(modelKH.getTenKhachHang());
        txtKH_DiaChi1.setText(modelKH.getDiaChi());
        txtKH_Email.setText(modelKH.getDiaChi());
        txtKH_SDT.setText(modelKH.getSdt());
        txtKH_Email.setText(modelKH.getEmail());
        if (modelKH.getGioiTinh() == true) {
            rdoNamKH.setSelected(true);
        } else {
            rdoNuKH.setSelected(true);
        }
        lblAnhKH.setToolTipText(modelKH.getAnhKH());
        if (modelKH.getAnhKH() != null) {
            lblAnhKH.setIcon(readLogo(lblAnhKH, modelKH.getAnhKH()));
        }
    }

    KhachHang getModelKH() {
        KhachHang model = new KhachHang();
        model.setTenKhachHang(txtKH_Name.getText());
        model.setDiaChi(txtKH_Email.getText());
        model.setSdt(txtKH_SDT.getText());
        model.setEmail(txtKH_Email.getText());
        boolean gt;
        if (rdoNamKH.isSelected()) {
            gt = true;
        } else {
            gt = false;
        }
        model.setGioiTinh(gt);
        model.setAnhKH(lblAnhKH.getToolTipText());
        return model;
    }

    KhachHang getModelUpdateKH() {
        KhachHang model = new KhachHang();
        model.setIdKhachHang(IdKh);
        model.setTenKhachHang(txtKH_Name.getText());
        model.setDiaChi(txtKH_Email.getText());
        model.setSdt(txtKH_SDT.getText());
        model.setEmail(txtKH_Email.getText());
        boolean gt;
        if (rdoNamKH.isSelected()) {
            gt = true;
        } else {
            gt = false;
        }
        model.setGioiTinh(gt);
        model.setAnhKH(lblAnhKH.getToolTipText());
        return model;
    }

    public Boolean checkKH() {

        if (txtKH_Email.getText().equals("") || txtKH_Email.getText().equals("") || txtKH_Name.getText().equals("") || txtKH_SDT.getText().equals("")) {
            JOptionPane.showMessageDialog(this, " không được để trống !", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!rdoNamKH.isSelected() && !rdoNuKH.isSelected()) {
            JOptionPane.showMessageDialog(this, "vui lòng chọn giới tính !", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!(txtKH_SDT.getText()).matches("^0\\d{9}$")) {
            JOptionPane.showMessageDialog(this, "vui lòng nhập đúng định dạng SĐT 10 số");
            return false;
        } else if (!(txtKH_Email.getText()).matches("\\w+@\\w+\\.\\w+")) {
            JOptionPane.showMessageDialog(this, " vui lòng nhập đúng định dạng email");
            return false;
        }
        return true;
    }

    // đối tác
    void loadDT() {
        DefaultTableModel model = (DefaultTableModel) tblDoiTac.getModel();
        model.setRowCount(0);
        try {
            List<DoiTac> list = daoDT.selectDT();
            for (DoiTac dt : list) {
                Object[] row = {
                    dt.getIdDoiTac(),
                    dt.getTenDoitac(),
                    dt.getLinhVuc(),
                    dt.getDiaChi(),
                    dt.getSdt(),
                    dt.getEmail(),
                    dt.getSoVonDaDauTu(),
                    dt.getAnhDT(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " Lỗi truy vấn dữ liệu", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void editDT() {
        try {
            Integer id = (Integer) tblDoiTac.getValueAt(this.indexDoiTac, 0);
            IdDT = id;
            DoiTac model = daoDT.findByIdDT(id);
            if (model != null) {
                this.setModelDT(model);
                this.setStatusDT(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " Lỗi truy vấn dữ liệu", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void insertDT() {
        DoiTac model = getModelDT();
        try {
            daoDT.insertDT(model);

            this.loadDT();
            JOptionPane.showMessageDialog(this, "thêm mới thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " thêm mới thất bại", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void updateDT() {
        DoiTac model = getModelUpdateDT();
        try {
            daoDT.updateDT(model);
            this.loadDT();
            JOptionPane.showMessageDialog(this, " update thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "update thất bại", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void deleteDT() {
        int kt = JOptionPane.showConfirmDialog(this, "bạn thực sự muốn xóa", "confirm", JOptionPane.YES_NO_OPTION); //JOptionPane.QUESTION_MESSAGE
        if (kt == JOptionPane.YES_OPTION) {
            try {
                daoDT.deleteDT(IdDT);
                this.loadDT();
                this.clearDT();
                this.setStatusDT(true);
                JOptionPane.showMessageDialog(this, "xóa thành công");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, " xóa thất bại, vui lòng thử lại", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void setStatusDT(boolean inserttable) {
        btnDT_Them.setEnabled(inserttable);
        btnDT_Sua.setEnabled(!inserttable);
        btnDT_Xoa.setEnabled(!inserttable);
        boolean fir = this.indexDoiTac > 0;
        boolean las = this.indexDoiTac < tblDoiTac.getRowCount() - 1;
        btnNL_DT.setEnabled(!inserttable && fir);
        btnL_DT.setEnabled(!inserttable && fir);
        btnNR_DT.setEnabled(!inserttable && las);
        btnR_DT.setEnabled(!inserttable && las);
    }

    void clearDT() {
        txtDT_DiaChi.setText("");
        txtDT_Email.setText("");
        txtDT_LinhVuc.setText("");
        txtDT_SDT.setText("");
        txtDT_SoVonDauTu.setText("");
        txtDT_name.setText("");
        lblAnhDoiTac.setToolTipText(null);
        lblAnhDoiTac.setIcon(null);
    }

    void setModelDT(DoiTac model) {
        txtDT_name.setText(model.getTenDoitac());
        txtDT_LinhVuc.setText(model.getLinhVuc());
        txtDT_DiaChi.setText(model.getDiaChi());
        txtDT_SDT.setText(model.getSdt());
        txtDT_Email.setText(model.getEmail());
        txtDT_SoVonDauTu.setText(String.valueOf(model.getSoVonDaDauTu()));
        lblAnhDoiTac.setToolTipText(model.getAnhDT());
        if (model.getAnhDT() != null) {
            lblAnhDoiTac.setIcon(readLogo(lblAnhDoiTac, model.getAnhDT()));
        }
    }

    DoiTac getModelDT() {
        DoiTac model = new DoiTac();
        model.setTenDoitac(txtDT_name.getText());
        model.setLinhVuc(txtDT_LinhVuc.getText());
        model.setDiaChi(txtDT_DiaChi.getText());
        model.setSdt(txtDT_SDT.getText());
        model.setEmail(txtDT_Email.getText());
        model.setSoVonDaDauTu(Double.parseDouble(txtDT_SoVonDauTu.getText()));
        model.setAnhDT(lblAnhDoiTac.getToolTipText());
        return model;
    }

    DoiTac getModelUpdateDT() {
        DoiTac model = new DoiTac();
        model.setIdDoiTac(IdDT);
        model.setTenDoitac(txtDT_name.getText());
        model.setLinhVuc(txtDT_LinhVuc.getText());
        model.setDiaChi(txtDT_DiaChi.getText());
        model.setSdt(txtDT_SDT.getText());
        model.setEmail(txtDT_Email.getText());
        model.setSoVonDaDauTu(Double.parseDouble(txtDT_SoVonDauTu.getText()));
        model.setAnhDT(lblAnhDoiTac.getToolTipText());
        return model;
    }

    public boolean checkDT() {

        double von = Double.parseDouble(txtDT_SoVonDauTu.getText());
        if (txtDT_DiaChi.getText().equals("") || txtDT_Email.getText().equals("") || txtDT_LinhVuc.getText().equals("") || txtDT_SDT.getText().equals("") || txtDT_SoVonDauTu.getText().equals("") || txtDT_name.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return false;
        } else if (von < 0) {
            JOptionPane.showMessageDialog(this, " Vốn đầu tư phải >= 0");
            return false;
        } else if (!(txtDT_SDT.getText()).matches("^0\\d{9}$")) {
            JOptionPane.showMessageDialog(this, "vui lòng nhập đúng định dạng SĐT 10 số");
            return false;
        } else if (!(txtDT_Email.getText()).matches("\\w+@\\w+\\.\\w+")) {
            JOptionPane.showMessageDialog(this, " vui lòng nhập đúng định dạng email");
            return false;
        }
        return true;
    }

    // du an 
    void loadDA() {
        DefaultTableModel model = (DefaultTableModel) tbl_DuAn.getModel();
        model.setRowCount(0);
        try {
            List<DuAn> listDA = daoDA.selectDA();
            for (DuAn kh : listDA) {
                Object[] row = {
                    kh.getIdDuAn(),
                    kh.getTenDuAn(),
                    kh.getLoaiHinh(),
                    kh.getDiaChi(),
                    kh.getDienTich(),
                    kh.getChiPhiDuAn(),
                    kh.getMucTieu(),
                    kh.getNgayBatDau(),
                    kh.getNgayKetThuc(),
                    kh.getHinhThucQuanLi(),
                    kh.getHinhThucDauTu(),
                    kh.getIdDoiTac(),
                    kh.getHinhAnh(),
                    kh.getTrangThai(),};
                model.addRow(row);

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " lỗi truy vấn dữ liệu", "error", JOptionPane.ERROR_MESSAGE);
        }

    }

    void insertDA() {
        DuAn model = getModelDA();
        try {
            daoDA.insertDA(model);
            this.loadDA();
            JOptionPane.showMessageDialog(this, " thêm mới thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " thêm mới thất bại !", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void updateDA() {
        DuAn model = getModelUpdateDA();
        try {
            daoDA.updateDA(model);
            this.loadDA();
            JOptionPane.showMessageDialog(this, " update thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " update thất bại, vui lòng thử lại !", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void deleteDA() {
        int kt = JOptionPane.showConfirmDialog(this, " bạn thực sự muốn xóa ", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (kt == JOptionPane.YES_OPTION) {
            try {
                daoDA.deleteDA(IdDA);
                this.loadDA();
                this.clearDA();
                this.setStatusDA(true);
                JOptionPane.showMessageDialog(this, "Xóa thành công !");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, " xóa thất bại !", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void EditDA() {
        try {
            int id = (Integer) tbl_DuAn.getValueAt(this.indexDuAn, 0);
            DuAn model = daoDA.findByIdDuAn(id);
            IdDA = id;
            if (model != null) {
                this.setModelDA(model);
                this.setStatusDA(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu", "error", JOptionPane.ERROR_MESSAGE);
        }

    }

    void setStatusDA(Boolean khTable) {
        btnDAthem.setEnabled(khTable);
        btnDAsua.setEnabled(!khTable);
        btnDAxoa.setEnabled(!khTable);
        Boolean fir = this.indexDuAn > 0;
        Boolean las = this.indexDuAn < tbl_DuAn.getRowCount() - 1;
        btnNL_DA.setEnabled(!khTable && fir);
        btnL_DA.setEnabled(!khTable && fir);
        btnR_DA.setEnabled(!khTable && las);
        btnNR_DA.setEnabled(!khTable && las);
//        projectList = daoDA.selectDA();
    }

    void clearDA() {
        txtDA_name.setText("");
        txtDA_loaiHinh.setText("");
        txtDA_diaChi.setText("");
        txtDA_dienTich.setText("");
        txtDA_chiPhi.setText("");
        txtDA_mucTieu.setText("");
        txtDA_ngayBatDau.setText("");
        txtDA_ngayKetThuc.setText("");
        txtDA_hinhThucQL.setText("");
        txtDA_hinhThucDauTu.setText("");
        txtDA_iddoiTac.setText("");
        txtDA_trangThai.setText("");
        lblDA_anh.setToolTipText(null);
        lblDA_anh.setIcon(null);
    }

    void setModelDA(DuAn modelDA) {
        txtDA_name.setText(modelDA.getTenDuAn());
        txtDA_loaiHinh.setText(modelDA.getLoaiHinh());
        txtDA_diaChi.setText(modelDA.getDiaChi());
        txtDA_dienTich.setText(modelDA.getDienTich().toString());  //c1
        txtDA_chiPhi.setText(String.valueOf(modelDA.getChiPhiDuAn())); // c2
        txtDA_mucTieu.setText(modelDA.getMucTieu());
        txtDA_ngayBatDau.setText(ToString(modelDA.getNgayBatDau()));
        txtDA_ngayKetThuc.setText(ToString(modelDA.getNgayKetThuc()));
        txtDA_hinhThucQL.setText(modelDA.getHinhThucQuanLi());
        txtDA_hinhThucDauTu.setText(modelDA.getHinhThucDauTu());
        txtDA_iddoiTac.setText(String.valueOf(modelDA.getIdDoiTac()));

        lblDA_anh.setToolTipText(modelDA.getHinhAnh());
        if (modelDA.getHinhAnh() != null) {
            lblDA_anh.setIcon(readLogo(lblDA_anh, modelDA.getHinhAnh()));
        }

        txtDA_trangThai.setText(modelDA.getTrangThai());
    }

    DuAn getModelDA() {
        DuAn model = new DuAn();

        model.setTenDuAn(txtDA_name.getText());
        model.setLoaiHinh(txtDA_loaiHinh.getText());
        model.setDiaChi(txtDA_diaChi.getText());
        model.setDienTich(Double.parseDouble(txtDA_dienTich.getText()));
        model.setChiPhiDuAn(Double.parseDouble(txtDA_chiPhi.getText()));
        model.setMucTieu(txtDA_mucTieu.getText());
        model.setNgayBatDau(toDate(txtDA_ngayBatDau.getText()));
        model.setNgayKetThuc(toDate(txtDA_ngayKetThuc.getText()));
        model.setHinhThucQuanLi(txtDA_hinhThucQL.getText());
        model.setHinhThucDauTu(txtDA_hinhThucDauTu.getText());
        model.setIdDoiTac(Integer.parseInt(txtDA_iddoiTac.getText()));
        model.setHinhAnh(lblDA_anh.getToolTipText());
        model.setTrangThai(txtDA_trangThai.getText());
        return model;
    }

    DuAn getModelUpdateDA() {
        DuAn model = new DuAn();
        model.setIdDuAn(IdDA);
        model.setTenDuAn(txtDA_name.getText());
        model.setLoaiHinh(txtDA_loaiHinh.getText());
        model.setDiaChi(txtDA_diaChi.getText());
        model.setDienTich(Double.parseDouble(txtDA_dienTich.getText()));
        model.setChiPhiDuAn(Double.parseDouble(txtDA_chiPhi.getText()));
        model.setMucTieu(txtDA_mucTieu.getText());
        model.setNgayBatDau(toDate(txtDA_ngayBatDau.getText()));
        model.setNgayKetThuc(toDate(txtDA_ngayKetThuc.getText()));
        model.setHinhThucQuanLi(txtDA_hinhThucQL.getText());
        model.setHinhThucDauTu(txtDA_hinhThucDauTu.getText());
        model.setIdDoiTac(Integer.parseInt(txtDA_iddoiTac.getText()));
        model.setHinhAnh(lblDA_anh.getToolTipText());
        model.setTrangThai(txtDA_trangThai.getText());
        return model;
    }

    public Boolean checkDA() {
        double s = Double.parseDouble(txtDA_dienTich.getText());
        double chiphi = Double.parseDouble(txtDA_chiPhi.getText());

        if (txtDA_chiPhi.getText().equals("") || txtDA_diaChi.getText().equals("") || txtDA_dienTich.getText().equals("") || txtDA_hinhThucDauTu.getText().equals("")
                || txtDA_hinhThucQL.getText().equals("") || txtDA_iddoiTac.getText().equals("") || txtDA_loaiHinh.getText().equals("") || txtDA_mucTieu.getText().equals("")
                || txtDA_mucTieu.getText().equals("") || txtDA_name.getText().equals("") || txtDA_ngayBatDau.getText().equals("") || txtDA_ngayKetThuc.getText().equals("")
                || txtDA_trangThai.getText().equals("")) {
            JOptionPane.showMessageDialog(this, " không được để trống !", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (s <= 0) {
            JOptionPane.showMessageDialog(this, " Diện tích dự án chưa đúng !", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (chiphi <= 0) {
            JOptionPane.showMessageDialog(this, " Chi phí dự án chưa đúng !", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // sản phẩm
    void loadSP() {
        DefaultTableModel model = (DefaultTableModel) tbl_SanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> listSP = daoSP.selectSP();
            for (SanPham kh : listSP) {
                Object[] row = {
                    kh.getIdSanPham(),
                    kh.getTenSanPham(),
                    kh.getIdDuAn(),
                    kh.getDiaChi(),
                    kh.getDienTich(),
                    kh.getGiaTien(),
                    kh.getMoTa(),
                    kh.getNgayTao(),
                    kh.getNgayBan(),
                    kh.getChiTiet(),
                    kh.getTrangThai(),
                    kh.getIdKhachHang(),
                    kh.getAnhSP(),};
                model.addRow(row);

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " lỗi truy vấn dữ liệu", "error", JOptionPane.ERROR_MESSAGE);
        }

    }

    void insertSP() {
        SanPham model = getModelSP();
        try {
            daoSP.insertSP(model);
            this.loadSP();
            JOptionPane.showMessageDialog(this, " thêm mới thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " thêm mới thất bại !", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void updateSP() {
        SanPham model = getModelUpdateSP();
        try {
            daoSP.updateSP(model);
            this.loadSP();
            JOptionPane.showMessageDialog(this, " update thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " update thất bại, vui lòng thử lại !", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void deleteSP() {
        int kt = JOptionPane.showConfirmDialog(this, " bạn thực sự muốn xóa ", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (kt == JOptionPane.YES_OPTION) {
            try {
                daoSP.deleteSP(IdSP);
                this.loadSP();
                this.clearSP();
                this.setStatusSP(true);
                JOptionPane.showMessageDialog(this, "Xóa thành công !");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, " xóa thất bại !", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void EditSP() {
        try {
            int id = (Integer) tbl_SanPham.getValueAt(this.indexSP, 0);
            SanPham model = daoSP.findByIdSP(id);
            IdSP = id;
            if (model != null) {
                this.setModelSP(model);
                this.setStatusSP(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tru vấn dữ liệu", "error", JOptionPane.ERROR_MESSAGE);
        }

    }

    void setStatusSP(Boolean SPTable) {
        btnSP_Them.setEnabled(SPTable);
        btnSP_Sua.setEnabled(!SPTable);
        btnSP_Xoa.setEnabled(!SPTable);
        Boolean fir = this.indexSP > 0;
        Boolean las = this.indexSP < tbl_SanPham.getRowCount() - 1;
        btnNL_SP.setEnabled(!SPTable && fir);
        btnL_SP.setEnabled(!SPTable && fir);
        btnR_SP.setEnabled(!SPTable && las);
        btnNR_SP.setEnabled(!SPTable && las);
    }

    void clearSP() {
        txtSP_name.setText("");
        txtSP_IdDuAn.setText("");
        txtSP_DiaChi.setText("");
        txtSP_DienTich.setText("");  //c1
        txtSP_GiaTien.setText("");
        txtSP_MoTa.setText("");
        txtSP_NgayTao.setText("");
        txtSP_NgayBan.setText("");
        txtSP_ChiTiet.setText("");
        txtSP_TrangThai.setText("");
        txtSP_IdKhachHang.setText("");
        lblAnh_SP.setToolTipText(null);
        lblAnh_SP.setIcon(null);
    }

    void setModelSP(SanPham model) {
        txtSP_name.setText(model.getTenSanPham());
        txtSP_IdDuAn.setText(String.valueOf(model.getIdDuAn()));
        txtSP_DiaChi.setText(model.getDiaChi());
        txtSP_DienTich.setText(model.getDienTich().toString());  //c1
        txtSP_GiaTien.setText(model.getGiaTien().toString());
        txtSP_MoTa.setText(model.getMoTa());
        txtSP_NgayTao.setText(ToString(model.getNgayTao()));
        txtSP_NgayBan.setText(ToString(model.getNgayBan()));
        txtSP_ChiTiet.setText(model.getChiTiet());
        txtSP_TrangThai.setText(model.getTrangThai());
        txtSP_IdKhachHang.setText(String.valueOf(model.getIdKhachHang()));
        lblAnh_SP.setToolTipText(model.getAnhSP());
        if (model.getAnhSP() != null) {
            lblAnh_SP.setIcon(readLogo(lblAnh_SP, model.getAnhSP()));
        }
    }

    SanPham getModelSP() {
        SanPham model = new SanPham();

        model.setTenSanPham(txtSP_name.getText());
        model.setIdDuAn(Integer.parseInt(txtSP_IdDuAn.getText()));
        model.setDiaChi(txtSP_DiaChi.getText());
        model.setDienTich(Double.parseDouble(txtSP_DienTich.getText()));
        model.setGiaTien(Double.parseDouble(txtSP_GiaTien.getText()));
        model.setMoTa(txtSP_MoTa.getText());
        model.setNgayTao(toDate(txtSP_NgayTao.getText()));
        model.setNgayBan(toDate(txtSP_NgayBan.getText()));
        model.setChiTiet(txtSP_ChiTiet.getText());
        model.setTrangThai(txtSP_TrangThai.getText());
        model.setIdKhachHang(Integer.parseInt(txtSP_IdKhachHang.getText()));
        model.setAnhSP(lblAnh_SP.getToolTipText());
        return model;
    }

    SanPham getModelUpdateSP() {
        SanPham model = new SanPham();
        model.setIdSanPham(IdSP);
        model.setTenSanPham(txtSP_name.getText());
        model.setIdDuAn(Integer.parseInt(txtSP_IdDuAn.getText()));
        model.setDiaChi(txtSP_DiaChi.getText());
        model.setDienTich(Double.parseDouble(txtSP_DienTich.getText()));
        model.setGiaTien(Double.parseDouble(txtSP_GiaTien.getText()));
        model.setMoTa(txtSP_MoTa.getText());
        model.setNgayTao(toDate(txtSP_NgayTao.getText()));
        model.setNgayBan(toDate(txtSP_NgayBan.getText()));
        model.setChiTiet(txtSP_ChiTiet.getText());
        model.setTrangThai(txtDA_trangThai.getText());
        model.setIdKhachHang(Integer.parseInt(txtSP_IdKhachHang.getText()));
        model.setAnhSP(lblAnh_SP.getToolTipText());
        return model;
    }

    public Boolean checkSP() {
        double s = Double.parseDouble(txtSP_DienTich.getText());
        double gia = Double.parseDouble(txtSP_GiaTien.getText());

        if (txtSP_ChiTiet.getText().equals("") || txtSP_DiaChi.getText().equals("") || txtSP_DienTich.getText().equals("") || txtSP_GiaTien.getText().equals("")
                || txtSP_IdDuAn.getText().equals("") || txtSP_IdKhachHang.getText().equals("") || txtSP_MoTa.getText().equals("") || txtSP_NgayBan.getText().equals("")
                || txtSP_NgayTao.getText().equals("") || txtSP_TrangThai.getText().equals("") || txtSP_name.getText().equals("")) {
            JOptionPane.showMessageDialog(this, " không được để trống !", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (s <= 0) {
            JOptionPane.showMessageDialog(this, " Diện tích sản phẩm chưa đúng !", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (gia <= 0) {
            JOptionPane.showMessageDialog(this, " Giá tiền sản phẩm chưa đúng !", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (txtSP_NgayTao.getText().matches("dd/mm/yyyy")) {
            JOptionPane.showMessageDialog(this, " sai định dạng ngày tạo,vui lòng nhập đúng định dạng ngày/tháng/năm");
        } else if (txtSP_NgayBan.getText().matches("dd/mm/yyyy")) {
            JOptionPane.showMessageDialog(this, " sai định dạng ngày tạo,vui lòng nhập đúng định dạng ngày/tháng/năm");
        }
        return true;
    }

    // thống kê
    // dự án
    void fillComboBoxNam() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();
        List<DuAn> list = daoDA.selectDA();
        for (DuAn kh : list) {
            int nam = kh.getNgayBatDau().getYear() + 1900;
            if (model.getIndexOf(nam) < 0) {
                model.addElement(nam);
            }
        }
        cboNam.setSelectedIndex(0);
    }

    void fillTableDuAn() {
        DefaultTableModel model = (DefaultTableModel) tblThongKe_DuAN.getModel();
        model.setRowCount(0);
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<Object[]> listt = daoThKe.getDuAn(nam);
        for (Object[] row : listt) {
            model.addRow(row);
        }
    }
// doi tac

    void fillTableDoiTac() {
        DefaultTableModel model = (DefaultTableModel) tblThongKe_DoiTac.getModel();
        model.setRowCount(0);
        List<Object[]> list = daoThKe.getDoiTac();
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    // doanh thu
    void fillTableDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblThongKe_DoanhThu.getModel();
        model.setRowCount(0);
        List<Object[]> list = daoThKe.getDoanhThu();
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPaneMenu = new javax.swing.JPanel();
        lblGif = new javax.swing.JLabel();
        lblGach = new javax.swing.JLabel();
        lblBackG = new javax.swing.JLabel();
        JPaneMenuLogin2 = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnPhanTich = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnQuanLi = new javax.swing.JButton();
        btnHeThong = new javax.swing.JButton();
        JPaneMenuLogin = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        JPaneTime = new javax.swing.JPanel();
        lblTime = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        jPaneExit = new javax.swing.JPanel();
        right = new javax.swing.JLabel();
        top = new javax.swing.JLabel();
        left = new javax.swing.JLabel();
        bot = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnCan = new javax.swing.JButton();
        btnYes = new javax.swing.JButton();
        jPaneForm = new javax.swing.JPanel();
        JPaneHome = new javax.swing.JPanel();
        slide = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JPaneQuanLi = new javax.swing.JPanel();
        lblQLduAn = new javax.swing.JLabel();
        lblQLsanPham = new javax.swing.JLabel();
        lblQLnguoiDung = new javax.swing.JLabel();
        lblQLdoitac = new javax.swing.JLabel();
        lblQLkhachHang = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        btnDuAn = new javax.swing.JButton();
        btnSP = new javax.swing.JButton();
        btnNguoiDung = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnDoiTac = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jTabbQLsanPham = new javax.swing.JTabbedPane();
        jPanelQLsanPham = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txtSP_name = new javax.swing.JTextField();
        txtSP_DiaChi = new javax.swing.JTextField();
        txtSP_MoTa = new javax.swing.JTextField();
        txtSP_ChiTiet = new javax.swing.JTextField();
        txtSP_GiaTien = new javax.swing.JTextField();
        txtSP_IdDuAn = new javax.swing.JTextField();
        txtSP_NgayTao = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnSP_Them = new javax.swing.JButton();
        btnSP_Sua = new javax.swing.JButton();
        btnSP_Xoa = new javax.swing.JButton();
        btnSP_Moi = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        lblDA_inEx1 = new javax.swing.JLabel();
        btnNL_SP = new javax.swing.JButton();
        btnL_SP = new javax.swing.JButton();
        btnNR_SP = new javax.swing.JButton();
        btnR_SP = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        lblAnh_SP = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtSP_DienTich = new javax.swing.JTextField();
        txtSP_IdKhachHang = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtSP_TrangThai = new javax.swing.JTextField();
        txtSP_NgayBan = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_SanPham = new javax.swing.JTable();
        jTabbQLDuAn = new javax.swing.JTabbedPane();
        jPanelQLduAn = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtDA_name = new javax.swing.JTextField();
        txtDA_diaChi = new javax.swing.JTextField();
        txtDA_loaiHinh = new javax.swing.JTextField();
        txtDA_dienTich = new javax.swing.JTextField();
        txtDA_chiPhi = new javax.swing.JTextField();
        txtDA_mucTieu = new javax.swing.JTextField();
        txtDA_hinhThucDauTu = new javax.swing.JTextField();
        txtDA_hinhThucQL = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btnDAthem = new javax.swing.JButton();
        btnDAsua = new javax.swing.JButton();
        btnDAxoa = new javax.swing.JButton();
        btnDAmoi = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lblDA_inEx = new javax.swing.JLabel();
        btnNL_DA = new javax.swing.JButton();
        btnL_DA = new javax.swing.JButton();
        btnNR_DA = new javax.swing.JButton();
        btnR_DA = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lblDA_anh = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtDA_ngayBatDau = new javax.swing.JTextField();
        txtDA_iddoiTac = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtDA_trangThai = new javax.swing.JTextField();
        txtDA_ngayKetThuc = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_DuAn = new javax.swing.JTable();
        jTabbQLdoiTac = new javax.swing.JTabbedPane();
        jPanelQLnguoiDung2 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtDT_name = new javax.swing.JTextField();
        txtDT_LinhVuc = new javax.swing.JTextField();
        txtDT_DiaChi = new javax.swing.JTextField();
        txtDT_SDT = new javax.swing.JTextField();
        txtDT_Email = new javax.swing.JTextField();
        txtDT_SoVonDauTu = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        btnDT_Them = new javax.swing.JButton();
        btnDT_Sua = new javax.swing.JButton();
        btnDT_Xoa = new javax.swing.JButton();
        btnDT_Moi = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        btnNL_DT = new javax.swing.JButton();
        btnL_DT = new javax.swing.JButton();
        btnNR_DT = new javax.swing.JButton();
        btnR_DT = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        lblAnhDoiTac = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDoiTac = new javax.swing.JTable();
        jTabbQLkhachHang = new javax.swing.JTabbedPane();
        jPanelQLnguoiDung1 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        txtKH_Name = new javax.swing.JTextField();
        txtKH_Email = new javax.swing.JTextField();
        txtKH_SDT = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        btnKH_Them = new javax.swing.JButton();
        btnKH_Sua = new javax.swing.JButton();
        btnKH_Xoa = new javax.swing.JButton();
        btnKH_Moi = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        btnNL_KH = new javax.swing.JButton();
        btnL_KH = new javax.swing.JButton();
        btnNR_KH = new javax.swing.JButton();
        btnR_KH = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        lblAnhKH = new javax.swing.JLabel();
        rdoNamKH = new javax.swing.JRadioButton();
        rdoNuKH = new javax.swing.JRadioButton();
        txtKH_DiaChi1 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jTabbQLnguoiDung = new javax.swing.JTabbedPane();
        jPanelQLnguoiDung = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtNDName = new javax.swing.JTextField();
        txtNDtenDangNhap = new javax.swing.JTextField();
        txtNDpass = new javax.swing.JPasswordField();
        txtNDdiachi = new javax.swing.JTextField();
        txtNDsdt = new javax.swing.JTextField();
        txtNDemail = new javax.swing.JTextField();
        txtNDchucVu = new javax.swing.JTextField();
        txtNDrole = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnThemND = new javax.swing.JButton();
        btnSuaND = new javax.swing.JButton();
        btnXoaND = new javax.swing.JButton();
        btnMoiND = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        btnNL_ND = new javax.swing.JButton();
        btnL_ND = new javax.swing.JButton();
        btnNR_ND = new javax.swing.JButton();
        btnR_ND = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblAnhND = new javax.swing.JLabel();
        rdoNamND = new javax.swing.JRadioButton();
        rdoNuND = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNguoiDung = new javax.swing.JTable();
        backg = new javax.swing.JLabel();
        JPaneThongKe = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblThongKe_DuAN = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblThongKe_DoiTac = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblThongKe_DoanhThu = new javax.swing.JTable();
        lblThongKe = new javax.swing.JLabel();
        lblTitle1 = new javax.swing.JLabel();
        backg1 = new javax.swing.JLabel();
        JPaneHeThong = new javax.swing.JPanel();
        lblLogout = new javax.swing.JLabel();
        lblThoat = new javax.swing.JLabel();
        lblGioiThieu = new javax.swing.JLabel();
        lblTaiKhoan = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        JPaneGioiThieu = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        JPaneTaiKhoan = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        lblNameTK = new javax.swing.JLabel();
        lblShow1 = new javax.swing.JLabel();
        lblHide1 = new javax.swing.JLabel();
        txtPassCu = new javax.swing.JPasswordField();
        lblShow2 = new javax.swing.JLabel();
        lblHide2 = new javax.swing.JLabel();
        txtPassMoi = new javax.swing.JPasswordField();
        lblShow3 = new javax.swing.JLabel();
        lblHide3 = new javax.swing.JLabel();
        txtPassXN = new javax.swing.JPasswordField();
        lblThemTK = new javax.swing.JLabel();
        lblNewTK = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JPaneAbout = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        backk = new javax.swing.JLabel();
        JPanePhanTich = new javax.swing.JPanel();
        JPaneThoat = new javax.swing.JPanel();
        right1 = new javax.swing.JLabel();
        top1 = new javax.swing.JLabel();
        left1 = new javax.swing.JLabel();
        bot1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCan1 = new javax.swing.JButton();
        btnYes1 = new javax.swing.JButton();
        JPaneLoogin = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        lblEyyy = new javax.swing.JLabel();
        lblEye = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        chkGhiNho = new javax.swing.JCheckBox();
        btnLoginOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblLogin = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_BackG_Home = new javax.swing.JLabel();
        JPaneLogout = new javax.swing.JPanel();
        txtUserName1 = new javax.swing.JTextField();
        lblShowLogout = new javax.swing.JLabel();
        lblHideLogout = new javax.swing.JLabel();
        txtPass1 = new javax.swing.JPasswordField();
        chkGhiNho1 = new javax.swing.JCheckBox();
        btnLoginOK1 = new javax.swing.JButton();
        btnEx = new javax.swing.JButton();
        lblLogin1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÍ BẤT ĐỘNG SẢN");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1280, 590));
        jPanel4.setLayout(null);

        jPaneMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPaneMenu.setMinimumSize(new java.awt.Dimension(160, 630));
        jPaneMenu.setPreferredSize(new java.awt.Dimension(160, 590));
        jPaneMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPaneMenuMouseClicked(evt);
            }
        });
        jPaneMenu.setLayout(null);

        lblGif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGif.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblGif.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblGif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGifMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblGifMousePressed(evt);
            }
        });
        jPaneMenu.add(lblGif);
        lblGif.setBounds(0, 100, 160, 50);

        lblGach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/icon/gach.png"))); // NOI18N
        lblGach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblGachMousePressed(evt);
            }
        });
        jPaneMenu.add(lblGach);
        lblGach.setBounds(0, 100, 40, 50);

        lblBackG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/bac.jpg"))); // NOI18N
        jPaneMenu.add(lblBackG);
        lblBackG.setBounds(0, 0, 40, 590);

        JPaneMenuLogin2.setOpaque(false);
        JPaneMenuLogin2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPaneMenuLogin2MouseClicked(evt);
            }
        });
        JPaneMenuLogin2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but.png"))); // NOI18N
        btnHome.setText("TRANG CHỦ");
        btnHome.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnHome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHome.setOpaque(false);
        btnHome.setPreferredSize(new java.awt.Dimension(73, 25));
        btnHome.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but1.png"))); // NOI18N
        btnHome.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but2.png"))); // NOI18N
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        JPaneMenuLogin2.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 177, 65));

        btnPhanTich.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnPhanTich.setForeground(new java.awt.Color(255, 255, 255));
        btnPhanTich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but.png"))); // NOI18N
        btnPhanTich.setText("PHÂN TÍCH");
        btnPhanTich.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnPhanTich.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPhanTich.setOpaque(false);
        btnPhanTich.setPreferredSize(new java.awt.Dimension(73, 25));
        btnPhanTich.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but1.png"))); // NOI18N
        btnPhanTich.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but2.png"))); // NOI18N
        btnPhanTich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPhanTichMouseClicked(evt);
            }
        });
        btnPhanTich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanTichActionPerformed(evt);
            }
        });
        JPaneMenuLogin2.add(btnPhanTich, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 177, 65));

        btnThongKe.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but.png"))); // NOI18N
        btnThongKe.setText("THỐNG KÊ");
        btnThongKe.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnThongKe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongKe.setOpaque(false);
        btnThongKe.setPreferredSize(new java.awt.Dimension(73, 25));
        btnThongKe.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but1.png"))); // NOI18N
        btnThongKe.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but2.png"))); // NOI18N
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongKeMouseClicked(evt);
            }
        });
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        JPaneMenuLogin2.add(btnThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 177, 65));

        btnQuanLi.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnQuanLi.setForeground(new java.awt.Color(255, 255, 255));
        btnQuanLi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but.png"))); // NOI18N
        btnQuanLi.setText("QUẢN LÍ");
        btnQuanLi.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnQuanLi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQuanLi.setOpaque(false);
        btnQuanLi.setPreferredSize(new java.awt.Dimension(73, 25));
        btnQuanLi.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but1.png"))); // NOI18N
        btnQuanLi.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but2.png"))); // NOI18N
        btnQuanLi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuanLiMouseClicked(evt);
            }
        });
        btnQuanLi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLiActionPerformed(evt);
            }
        });
        JPaneMenuLogin2.add(btnQuanLi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 177, 65));

        btnHeThong.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnHeThong.setForeground(new java.awt.Color(255, 255, 255));
        btnHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but.png"))); // NOI18N
        btnHeThong.setText("HỆ THỐNG");
        btnHeThong.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnHeThong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHeThong.setOpaque(false);
        btnHeThong.setPreferredSize(new java.awt.Dimension(73, 25));
        btnHeThong.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but1.png"))); // NOI18N
        btnHeThong.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but2.png"))); // NOI18N
        btnHeThong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHeThongMouseClicked(evt);
            }
        });
        btnHeThong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeThongActionPerformed(evt);
            }
        });
        JPaneMenuLogin2.add(btnHeThong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 177, 65));

        jPaneMenu.add(JPaneMenuLogin2);
        JPaneMenuLogin2.setBounds(0, 0, 160, 590);

        JPaneMenuLogin.setOpaque(false);
        JPaneMenuLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPaneMenuLoginMouseClicked(evt);
            }
        });
        JPaneMenuLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but.png"))); // NOI18N
        btnLogin.setText("LOGIN");
        btnLogin.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogin.setOpaque(false);
        btnLogin.setPreferredSize(new java.awt.Dimension(73, 25));
        btnLogin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but1.png"))); // NOI18N
        btnLogin.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but2.png"))); // NOI18N
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });
        JPaneMenuLogin.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 177, 65));

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but.png"))); // NOI18N
        btnExit.setText("EXIT");
        btnExit.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.setOpaque(false);
        btnExit.setPreferredSize(new java.awt.Dimension(73, 25));
        btnExit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but1.png"))); // NOI18N
        btnExit.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/button/but2.png"))); // NOI18N
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnExitMousePressed(evt);
            }
        });
        JPaneMenuLogin.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 177, 65));

        jPaneMenu.add(JPaneMenuLogin);
        JPaneMenuLogin.setBounds(0, 0, 160, 590);

        JPaneTime.setBackground(new java.awt.Color(0, 51, 255));
        JPaneTime.setOpaque(false);

        lblTime.setBackground(new java.awt.Color(0, 51, 255));
        lblTime.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel1.setBackground(new java.awt.Color(0, 0, 255));
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout JPaneTimeLayout = new javax.swing.GroupLayout(JPaneTime);
        JPaneTime.setLayout(JPaneTimeLayout);
        JPaneTimeLayout.setHorizontalGroup(
            JPaneTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPaneTimeLayout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(JPaneTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
        );
        JPaneTimeLayout.setVerticalGroup(
            JPaneTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPaneTimeLayout.createSequentialGroup()
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
            .addGroup(JPaneTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
        );

        jPaneMenu.add(JPaneTime);
        JPaneTime.setBounds(0, 556, 160, 34);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/logoIcon.png"))); // NOI18N
        jPaneMenu.add(logo);
        logo.setBounds(10, 0, 139, 114);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/bac.jpg"))); // NOI18N
        background.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backgroundMouseClicked(evt);
            }
        });
        jPaneMenu.add(background);
        background.setBounds(0, 0, 160, 590);

        jPanel4.add(jPaneMenu);
        jPaneMenu.setBounds(0, 0, 160, 590);

        jPaneExit.setBackground(new java.awt.Color(255, 255, 255));
        jPaneExit.setLayout(null);

        right.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/panRight.png"))); // NOI18N
        jPaneExit.add(right);
        right.setBounds(459, -10, 670, 610);

        top.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        top.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/panTopp.png"))); // NOI18N
        jPaneExit.add(top);
        top.setBounds(0, 0, 982, 261);

        left.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/panLeft.png"))); // NOI18N
        jPaneExit.add(left);
        left.setBounds(0, 0, 643, 590);

        bot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/panBot.png"))); // NOI18N
        jPaneExit.add(bot);
        bot.setBounds(126, 330, 1010, 270);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("Bạn muốn kết thúc chương trình ?");
        jPaneExit.add(jLabel6);
        jLabel6.setBounds(400, 220, 330, 45);

        btnCan.setText("Không");
        btnCan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanActionPerformed(evt);
            }
        });
        jPaneExit.add(btnCan);
        btnCan.setBounds(630, 310, 90, 34);

        btnYes.setText("Đồng ý");
        btnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYesActionPerformed(evt);
            }
        });
        jPaneExit.add(btnYes);
        btnYes.setBounds(390, 310, 90, 34);

        jPanel4.add(jPaneExit);
        jPaneExit.setBounds(160, 0, 1120, 590);

        jPaneForm.setBackground(new java.awt.Color(255, 255, 255));
        jPaneForm.setPreferredSize(new java.awt.Dimension(1120, 590));
        jPaneForm.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPaneFormAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPaneForm.setLayout(null);

        JPaneHome.setBackground(new java.awt.Color(255, 255, 255));
        JPaneHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        JPaneHome.add(slide, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 132, 788, 391));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/menu_bluee2.png"))); // NOI18N
        jLabel5.setText("TRANG CHỦ");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JPaneHome.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 70));

        jPaneForm.add(JPaneHome);
        JPaneHome.setBounds(160, 0, 1120, 590);

        JPaneQuanLi.setForeground(new java.awt.Color(255, 0, 51));
        JPaneQuanLi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQLduAn.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblQLduAn.setForeground(new java.awt.Color(255, 255, 255));
        lblQLduAn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQLduAn.setText("Quản lí dự án");
        JPaneQuanLi.add(lblQLduAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 370, 70));

        lblQLsanPham.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblQLsanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblQLsanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQLsanPham.setText("Quản lí sản phẩm");
        JPaneQuanLi.add(lblQLsanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 370, 70));

        lblQLnguoiDung.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblQLnguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        lblQLnguoiDung.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQLnguoiDung.setText("Quản lí người dùng");
        JPaneQuanLi.add(lblQLnguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 370, 70));

        lblQLdoitac.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblQLdoitac.setForeground(new java.awt.Color(255, 255, 255));
        lblQLdoitac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQLdoitac.setText("Quản lí đối tác");
        JPaneQuanLi.add(lblQLdoitac, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 370, 70));

        lblQLkhachHang.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblQLkhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblQLkhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQLkhachHang.setText("Quản lí khách hàng");
        JPaneQuanLi.add(lblQLkhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 370, 70));

        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/menu_bluee333.png"))); // NOI18N
        JPaneQuanLi.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 70));

        btnDuAn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDuAn.setText("DỰ ÁN");
        btnDuAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuAnActionPerformed(evt);
            }
        });
        JPaneQuanLi.add(btnDuAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 120, 70));

        btnSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSP.setText("SẢN PHẨM");
        btnSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPActionPerformed(evt);
            }
        });
        JPaneQuanLi.add(btnSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 120, 70));

        btnNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNguoiDung.setText("NGƯỜI DÙNG");
        btnNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNguoiDungActionPerformed(evt);
            }
        });
        JPaneQuanLi.add(btnNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 120, 70));

        btnKhachHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnKhachHang.setText("KHÁCH HÀNG");
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });
        JPaneQuanLi.add(btnKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 120, 70));

        btnDoiTac.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDoiTac.setText("ĐỐI TÁC");
        btnDoiTac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiTacActionPerformed(evt);
            }
        });
        JPaneQuanLi.add(btnDoiTac, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 120, 70));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/loxo.png"))); // NOI18N
        JPaneQuanLi.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 70, 520));

        jTabbQLsanPham.setBackground(new java.awt.Color(255, 255, 255));
        jTabbQLsanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTabbQLsanPham.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabbQLsanPham.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTabbQLsanPhamAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanelQLsanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelQLsanPham.setOpaque(false);
        jPanelQLsanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel49.setText("Tên sản phẩm :");
        jPanelQLsanPham.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 13, -1, -1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel50.setText("Địa chỉ :");
        jPanelQLsanPham.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 64, -1, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel51.setText("Mô tả :");
        jPanelQLsanPham.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 113, -1, -1));

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel52.setText("Chi tiết :");
        jPanelQLsanPham.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 163, -1, -1));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel53.setText("ID dự án :");
        jPanelQLsanPham.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 263, -1, -1));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel54.setText("Diện tích :");
        jPanelQLsanPham.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 313, -1, -1));

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel56.setText("Ngày tạo :");
        jPanelQLsanPham.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 363, -1, -1));

        txtSP_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLsanPham.add(txtSP_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 7, 627, 30));

        txtSP_DiaChi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLsanPham.add(txtSP_DiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 57, 627, 30));

        txtSP_MoTa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLsanPham.add(txtSP_MoTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 107, 627, 30));

        txtSP_ChiTiet.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLsanPham.add(txtSP_ChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 157, 627, 30));

        txtSP_GiaTien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLsanPham.add(txtSP_GiaTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 207, 627, 30));

        txtSP_IdDuAn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLsanPham.add(txtSP_IdDuAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 257, 230, 30));

        txtSP_NgayTao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLsanPham.add(txtSP_NgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 357, 230, 30));

        jPanel10.setOpaque(false);

        btnSP_Them.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnSP_Them.setText("Thêm");
        btnSP_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSP_ThemActionPerformed(evt);
            }
        });

        btnSP_Sua.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnSP_Sua.setText("Sửa");
        btnSP_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSP_SuaActionPerformed(evt);
            }
        });

        btnSP_Xoa.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnSP_Xoa.setText("Xóa");
        btnSP_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSP_XoaActionPerformed(evt);
            }
        });

        btnSP_Moi.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnSP_Moi.setText("Mới");
        btnSP_Moi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSP_MoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(btnSP_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSP_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSP_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSP_Moi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSP_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSP_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSP_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSP_Moi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanelQLsanPham.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 393, -1, -1));

        jPanel11.setOpaque(false);
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDA_inEx1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDA_inEx1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDA_inEx1.setText("In ex");
        lblDA_inEx1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.add(lblDA_inEx1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 42));

        btnNL_SP.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNL_SP.setText("|<");
        btnNL_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNL_SPActionPerformed(evt);
            }
        });
        jPanel11.add(btnNL_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, 30));

        btnL_SP.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnL_SP.setText("<<");
        btnL_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnL_SPActionPerformed(evt);
            }
        });
        jPanel11.add(btnL_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, 100, 30));

        btnNR_SP.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNR_SP.setText(">|");
        btnNR_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNR_SPActionPerformed(evt);
            }
        });
        jPanel11.add(btnNR_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 192, 100, 30));

        btnR_SP.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnR_SP.setText(">>");
        btnR_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnR_SPActionPerformed(evt);
            }
        });
        jPanel11.add(btnR_SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 100, 30));

        jPanelQLsanPham.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(865, 207, -1, 230));

        jPanel12.setOpaque(false);

        lblAnh_SP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAnh_SP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh_SP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnh_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnh_SPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblAnh_SP, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblAnh_SP, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        );

        jPanelQLsanPham.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(839, 8, 170, 180));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setText("ID Khách hàng :");
        jPanelQLsanPham.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 263, -1, -1));

        txtSP_DienTich.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLsanPham.add(txtSP_DienTich, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 307, 230, 30));

        txtSP_IdKhachHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLsanPham.add(txtSP_IdKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 257, 230, 30));

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setText("Trạng thái :");
        jPanelQLsanPham.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 313, -1, -1));

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setText("Ngày bán :");
        jPanelQLsanPham.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 363, -1, -1));

        txtSP_TrangThai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLsanPham.add(txtSP_TrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 307, 230, 30));

        txtSP_NgayBan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLsanPham.add(txtSP_NgayBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 357, 230, 30));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel55.setText("Giá tiền :");
        jPanelQLsanPham.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 213, -1, -1));

        jTabbQLsanPham.addTab("Quản Lí", jPanelQLsanPham);

        jPanel13.setOpaque(false);

        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên sản phẩm", "ID dự án", "Địa chỉ", "Diện tích", "Giá Tiền", "Mô tả", "Ngày tạo", "Ngày bán", "Chi tiết", "Trạng thái", "ID khách hàng", "Hình ảnh"
            }
        ));
        tbl_SanPham.setOpaque(false);
        tbl_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_SanPham);
        if (tbl_SanPham.getColumnModel().getColumnCount() > 0) {
            tbl_SanPham.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1091, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        jTabbQLsanPham.addTab("Thông Tin", jPanel13);

        JPaneQuanLi.add(jTabbQLsanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 1100, 500));

        jTabbQLDuAn.setBackground(new java.awt.Color(255, 255, 255));
        jTabbQLDuAn.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTabbQLDuAn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabbQLDuAn.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTabbQLDuAnAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanelQLduAn.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelQLduAn.setOpaque(false);
        jPanelQLduAn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel36.setText("Hình thức đầu tư :");
        jPanelQLduAn.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 363, -1, 20));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel37.setText("Tên dự án :");
        jPanelQLduAn.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 13, -1, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel38.setText("Loại hình :");
        jPanelQLduAn.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 64, -1, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel39.setText("Địa chỉ :");
        jPanelQLduAn.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 113, -1, -1));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel40.setText("Diện tích :");
        jPanelQLduAn.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 163, -1, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel41.setText("Chi phí :");
        jPanelQLduAn.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 213, -1, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel42.setText("Mục tiêu :");
        jPanelQLduAn.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 263, -1, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel43.setText("Ngày bắt đầu :");
        jPanelQLduAn.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 313, -1, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel44.setText("Hình thức QL :");
        jPanelQLduAn.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 363, -1, -1));

        txtDA_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 7, 627, 30));

        txtDA_diaChi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_diaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 107, 627, 30));

        txtDA_loaiHinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_loaiHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 57, 627, 30));

        txtDA_dienTich.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_dienTich, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 157, 627, 30));

        txtDA_chiPhi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_chiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 207, 230, 30));

        txtDA_mucTieu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_mucTieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 257, 230, 30));

        txtDA_hinhThucDauTu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_hinhThucDauTu, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 357, 230, 30));

        txtDA_hinhThucQL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_hinhThucQL, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 357, 230, 30));

        jPanel6.setOpaque(false);

        btnDAthem.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnDAthem.setText("Thêm");
        btnDAthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDAthemActionPerformed(evt);
            }
        });

        btnDAsua.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnDAsua.setText("Sửa");
        btnDAsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDAsuaActionPerformed(evt);
            }
        });

        btnDAxoa.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnDAxoa.setText("Xóa");
        btnDAxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDAxoaActionPerformed(evt);
            }
        });

        btnDAmoi.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnDAmoi.setText("Mới");
        btnDAmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDAmoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(btnDAthem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnDAsua, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnDAxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnDAmoi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDAthem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDAsua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDAxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDAmoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanelQLduAn.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 393, -1, -1));

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDA_inEx.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDA_inEx.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDA_inEx.setText("In ex");
        lblDA_inEx.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblDA_inEx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDA_inExMouseClicked(evt);
            }
        });
        jPanel7.add(lblDA_inEx, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 42));

        btnNL_DA.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNL_DA.setText("|<");
        btnNL_DA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNL_DAActionPerformed(evt);
            }
        });
        jPanel7.add(btnNL_DA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, 30));

        btnL_DA.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnL_DA.setText("<<");
        btnL_DA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnL_DAActionPerformed(evt);
            }
        });
        jPanel7.add(btnL_DA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, 100, 30));

        btnNR_DA.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNR_DA.setText(">|");
        btnNR_DA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNR_DAActionPerformed(evt);
            }
        });
        jPanel7.add(btnNR_DA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 192, 100, 30));

        btnR_DA.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnR_DA.setText(">>");
        btnR_DA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnR_DAActionPerformed(evt);
            }
        });
        jPanel7.add(btnR_DA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 100, 30));

        jPanelQLduAn.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(865, 207, -1, 230));

        jPanel8.setOpaque(false);

        lblDA_anh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDA_anh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDA_anh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblDA_anh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDA_anhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblDA_anh, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblDA_anh, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        );

        jPanelQLduAn.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(839, 8, 170, 180));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel46.setText("ID đối tác :");
        jPanelQLduAn.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 213, -1, -1));

        txtDA_ngayBatDau.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_ngayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 307, 230, 30));

        txtDA_iddoiTac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_iddoiTac, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 207, 230, 30));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel47.setText("Trạng thái :");
        jPanelQLduAn.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 263, -1, -1));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setText("Ngày kết thúc :");
        jPanelQLduAn.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 313, -1, -1));

        txtDA_trangThai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_trangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 257, 230, 30));

        txtDA_ngayKetThuc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLduAn.add(txtDA_ngayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 307, 230, 30));

        jTabbQLDuAn.addTab("Quản Lí", jPanelQLduAn);

        jPanel9.setOpaque(false);

        tbl_DuAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên dự án", "Loại hình", "Địa chỉ", "Diện tích", "Chi phí", "Mục tiêu", "Ngày bắt đầu", "Ngày kết thúc", "Hình thức QL", "Hình thức ĐT", "ID đối tác", "Hình ảnh", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_DuAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DuAnMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_DuAn);
        if (tbl_DuAn.getColumnModel().getColumnCount() > 0) {
            tbl_DuAn.getColumnModel().getColumn(0).setPreferredWidth(20);
            tbl_DuAn.getColumnModel().getColumn(11).setPreferredWidth(50);
            tbl_DuAn.getColumnModel().getColumn(13).setResizable(false);
        }

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1091, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        jTabbQLDuAn.addTab("Thông Tin", jPanel9);

        JPaneQuanLi.add(jTabbQLDuAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 1100, 500));

        jTabbQLdoiTac.setBackground(new java.awt.Color(255, 255, 255));
        jTabbQLdoiTac.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTabbQLdoiTac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabbQLdoiTac.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTabbQLdoiTacAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanelQLnguoiDung2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelQLnguoiDung2.setOpaque(false);
        jPanelQLnguoiDung2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel66.setText("Tên đối tác :");
        jPanelQLnguoiDung2.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 64, -1, -1));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel67.setText("Lĩnh vực :");
        jPanelQLnguoiDung2.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 113, -1, -1));

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel69.setText("Địa chỉ :");
        jPanelQLnguoiDung2.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 163, -1, -1));

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel70.setText("Số điện thoại :");
        jPanelQLnguoiDung2.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 213, -1, -1));

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel71.setText("Email :");
        jPanelQLnguoiDung2.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 263, -1, -1));

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel72.setText("Số vốn đã đầu tư :");
        jPanelQLnguoiDung2.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 313, -1, -1));

        txtDT_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung2.add(txtDT_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 57, 530, 30));

        txtDT_LinhVuc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung2.add(txtDT_LinhVuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 107, 530, 30));

        txtDT_DiaChi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung2.add(txtDT_DiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 157, 530, 30));

        txtDT_SDT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung2.add(txtDT_SDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 207, 530, 30));

        txtDT_Email.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung2.add(txtDT_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 257, 530, 30));

        txtDT_SoVonDauTu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung2.add(txtDT_SoVonDauTu, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 307, 530, 30));

        jPanel18.setOpaque(false);

        btnDT_Them.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnDT_Them.setText("Thêm");
        btnDT_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDT_ThemActionPerformed(evt);
            }
        });

        btnDT_Sua.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnDT_Sua.setText("Sửa");
        btnDT_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDT_SuaActionPerformed(evt);
            }
        });

        btnDT_Xoa.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnDT_Xoa.setText("Xóa");
        btnDT_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDT_XoaActionPerformed(evt);
            }
        });

        btnDT_Moi.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnDT_Moi.setText("Mới");
        btnDT_Moi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDT_MoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(btnDT_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnDT_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnDT_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnDT_Moi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDT_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDT_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDT_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDT_Moi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanelQLnguoiDung2.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 393, -1, -1));

        jPanel19.setOpaque(false);
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("In ex");
        jLabel74.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel19.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 42));

        btnNL_DT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNL_DT.setText("|<");
        btnNL_DT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNL_DTActionPerformed(evt);
            }
        });
        jPanel19.add(btnNL_DT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, 30));

        btnL_DT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnL_DT.setText("<<");
        btnL_DT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnL_DTActionPerformed(evt);
            }
        });
        jPanel19.add(btnL_DT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, 100, 30));

        btnNR_DT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNR_DT.setText(">|");
        btnNR_DT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNR_DTActionPerformed(evt);
            }
        });
        jPanel19.add(btnNR_DT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 192, 100, 30));

        btnR_DT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnR_DT.setText(">>");
        btnR_DT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnR_DTActionPerformed(evt);
            }
        });
        jPanel19.add(btnR_DT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 100, 30));

        jPanelQLnguoiDung2.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 207, -1, 230));

        jPanel20.setOpaque(false);

        lblAnhDoiTac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAnhDoiTac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhDoiTac.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnhDoiTac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhDoiTacMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblAnhDoiTac, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblAnhDoiTac, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        );

        jPanelQLnguoiDung2.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 8, 170, 180));

        jTabbQLdoiTac.addTab("Quản Lí", jPanelQLnguoiDung2);

        jPanel21.setOpaque(false);

        tblDoiTac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên đối tác", "Lĩnh vực", "Địa chỉ", "SĐT", "Email", "Số vốn ĐT", "Hình Ảnh"
            }
        ));
        tblDoiTac.setOpaque(false);
        tblDoiTac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoiTacMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblDoiTac);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1091, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        jTabbQLdoiTac.addTab("Thông Tin", jPanel21);

        JPaneQuanLi.add(jTabbQLdoiTac, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 1100, 500));

        jTabbQLkhachHang.setBackground(new java.awt.Color(255, 255, 255));
        jTabbQLkhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTabbQLkhachHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabbQLkhachHang.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTabbQLkhachHangAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanelQLnguoiDung1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelQLnguoiDung1.setOpaque(false);
        jPanelQLnguoiDung1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel60.setText("Họ và tên :");
        jPanelQLnguoiDung1.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel61.setText("Địa chỉ :");
        jPanelQLnguoiDung1.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel62.setText("Email :");
        jPanelQLnguoiDung1.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel63.setText("Số điện thoại :");
        jPanelQLnguoiDung1.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel64.setText("Giới tính :");
        jPanelQLnguoiDung1.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, -1, -1));

        txtKH_Name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung1.add(txtKH_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 530, 30));

        txtKH_Email.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung1.add(txtKH_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 530, 30));

        txtKH_SDT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung1.add(txtKH_SDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 530, 30));

        jPanel14.setOpaque(false);

        btnKH_Them.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnKH_Them.setText("Thêm");
        btnKH_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKH_ThemActionPerformed(evt);
            }
        });

        btnKH_Sua.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnKH_Sua.setText("Sửa");
        btnKH_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKH_SuaActionPerformed(evt);
            }
        });

        btnKH_Xoa.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnKH_Xoa.setText("Xóa");
        btnKH_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKH_XoaActionPerformed(evt);
            }
        });

        btnKH_Moi.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnKH_Moi.setText("Mới");
        btnKH_Moi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKH_MoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(btnKH_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnKH_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnKH_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnKH_Moi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKH_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKH_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKH_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKH_Moi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanelQLnguoiDung1.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 393, -1, -1));

        jPanel15.setOpaque(false);
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("In ex");
        jLabel68.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 42));

        btnNL_KH.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNL_KH.setText("|<");
        btnNL_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNL_KHActionPerformed(evt);
            }
        });
        jPanel15.add(btnNL_KH, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, 30));

        btnL_KH.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnL_KH.setText("<<");
        btnL_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnL_KHActionPerformed(evt);
            }
        });
        jPanel15.add(btnL_KH, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, 100, 30));

        btnNR_KH.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNR_KH.setText(">|");
        btnNR_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNR_KHActionPerformed(evt);
            }
        });
        jPanel15.add(btnNR_KH, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 192, 100, 30));

        btnR_KH.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnR_KH.setText(">>");
        btnR_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnR_KHActionPerformed(evt);
            }
        });
        jPanel15.add(btnR_KH, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 100, 30));

        jPanelQLnguoiDung1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 207, -1, 230));

        jPanel16.setOpaque(false);

        lblAnhKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAnhKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhKH.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnhKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhKHMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblAnhKH, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblAnhKH, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        );

        jPanelQLnguoiDung1.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 8, 170, 180));

        rdoNamKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoNamKH.setText("Nam");
        rdoNamKH.setOpaque(false);
        jPanelQLnguoiDung1.add(rdoNamKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, -1, -1));

        rdoNuKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoNuKH.setText("Nữ");
        rdoNuKH.setOpaque(false);
        jPanelQLnguoiDung1.add(rdoNuKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, -1, -1));

        txtKH_DiaChi1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung1.add(txtKH_DiaChi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 530, 30));

        jTabbQLkhachHang.addTab("Quản Lí", jPanelQLnguoiDung1);

        jPanel17.setOpaque(false);

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Email", "Giới tính", "Hình ảnh"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setMinWidth(35);
            tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(35);
            tblKhachHang.getColumnModel().getColumn(0).setMaxWidth(35);
        }

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1091, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        jTabbQLkhachHang.addTab("Thông Tin", jPanel17);

        JPaneQuanLi.add(jTabbQLkhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 1100, 500));

        jTabbQLnguoiDung.setBackground(new java.awt.Color(255, 255, 255));
        jTabbQLnguoiDung.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTabbQLnguoiDung.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabbQLnguoiDung.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTabbQLnguoiDungAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanelQLnguoiDung.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelQLnguoiDung.setOpaque(false);
        jPanelQLnguoiDung.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel34.setText("Giới tính :");
        jPanelQLnguoiDung.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(514, 360, -1, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Họ và tên :");
        jPanelQLnguoiDung.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 13, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel26.setText("Tên đăng nhập :");
        jPanelQLnguoiDung.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 64, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel28.setText("Mật khẩu :");
        jPanelQLnguoiDung.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 113, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel29.setText("Địa chỉ :");
        jPanelQLnguoiDung.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 163, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel30.setText("Số điện thoại :");
        jPanelQLnguoiDung.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 213, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel31.setText("Email :");
        jPanelQLnguoiDung.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 263, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel32.setText("Chức vụ :");
        jPanelQLnguoiDung.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 313, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel33.setText("Role :");
        jPanelQLnguoiDung.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 363, -1, -1));

        txtNDName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung.add(txtNDName, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 7, 530, 30));

        txtNDtenDangNhap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung.add(txtNDtenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 57, 530, 30));

        txtNDpass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNDpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNDpassActionPerformed(evt);
            }
        });
        jPanelQLnguoiDung.add(txtNDpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 107, 530, 30));

        txtNDdiachi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung.add(txtNDdiachi, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 157, 530, 30));

        txtNDsdt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung.add(txtNDsdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 207, 530, 30));

        txtNDemail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung.add(txtNDemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 257, 530, 30));

        txtNDchucVu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung.add(txtNDchucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 307, 530, 30));

        txtNDrole.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanelQLnguoiDung.add(txtNDrole, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 357, 250, 30));

        jPanel2.setOpaque(false);

        btnThemND.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnThemND.setText("Thêm");
        btnThemND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNDActionPerformed(evt);
            }
        });

        btnSuaND.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnSuaND.setText("Sửa");
        btnSuaND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNDActionPerformed(evt);
            }
        });

        btnXoaND.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnXoaND.setText("Xóa");
        btnXoaND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNDActionPerformed(evt);
            }
        });

        btnMoiND.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnMoiND.setText("Mới");
        btnMoiND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiNDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(btnThemND, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSuaND, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnXoaND, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnMoiND, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemND, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaND, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaND, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoiND, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanelQLnguoiDung.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 393, -1, -1));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("In ex");
        jLabel35.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 42));

        btnNL_ND.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNL_ND.setText("|<");
        btnNL_ND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNL_NDActionPerformed(evt);
            }
        });
        jPanel1.add(btnNL_ND, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, 30));

        btnL_ND.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnL_ND.setText("<<");
        btnL_ND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnL_NDActionPerformed(evt);
            }
        });
        jPanel1.add(btnL_ND, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, 100, 30));

        btnNR_ND.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnNR_ND.setText(">|");
        btnNR_ND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNR_NDActionPerformed(evt);
            }
        });
        jPanel1.add(btnNR_ND, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 192, 100, 30));

        btnR_ND.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnR_ND.setText(">>");
        btnR_ND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnR_NDActionPerformed(evt);
            }
        });
        jPanel1.add(btnR_ND, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 100, 30));

        jPanelQLnguoiDung.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 207, -1, 230));

        jPanel5.setOpaque(false);

        lblAnhND.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAnhND.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhND.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnhND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhNDMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblAnhND, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblAnhND, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        );

        jPanelQLnguoiDung.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 8, 170, 180));

        rdoNamND.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoNamND.setText("Nam");
        rdoNamND.setOpaque(false);
        jPanelQLnguoiDung.add(rdoNamND, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 360, -1, -1));

        rdoNuND.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoNuND.setText("Nữ");
        rdoNuND.setOpaque(false);
        jPanelQLnguoiDung.add(rdoNuND, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 360, -1, -1));

        jTabbQLnguoiDung.addTab("Quản Lí", jPanelQLnguoiDung);

        jPanel3.setOpaque(false);

        tblNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên người dùng", "Tên đăng nhập", "Mật khẩu", "Địa chỉ", "Số điện thoại", "Email", "Giới tính", "Chức vụ", "Hình ảnh", "Role"
            }
        ));
        tblNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguoiDungMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNguoiDung);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1091, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        jTabbQLnguoiDung.addTab("Thông Tin", jPanel3);

        JPaneQuanLi.add(jTabbQLnguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 1100, 500));

        backg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/6.png"))); // NOI18N
        JPaneQuanLi.add(backg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1240, 520));

        jPaneForm.add(JPaneQuanLi);
        JPaneQuanLi.setBounds(40, 0, 1240, 590);

        JPaneThongKe.setBackground(new java.awt.Color(255, 255, 0));
        JPaneThongKe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTabbedPane1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel22.setOpaque(false);

        tblThongKe_DuAN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên dự án", "Số sản phẩm", "Số nhà đầu tư", "Số khách hàng", "Doanh thu"
            }
        ));
        tblThongKe_DuAN.setOpaque(false);
        jScrollPane6.setViewportView(tblThongKe_DuAN);

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("Năm :");

        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel45)
                .addGap(34, 34, 34)
                .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dự án", jPanel22);

        jPanel23.setOpaque(false);

        tblThongKe_DoiTac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên đối tác", "Số dự án đầu tư", "Số vốn đã đầu tư"
            }
        ));
        tblThongKe_DoiTac.setOpaque(false);
        jScrollPane7.setViewportView(tblThongKe_DoiTac);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Đối tác", jPanel23);

        jPanel24.setOpaque(false);

        tblThongKe_DoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Năm", "Số dự án", "Số sản phẩm", "Tổng doanh thu", "Doanh thu cao nhất", "Doanh thu trung bình", "Doanh thu cao nhất"
            }
        ));
        tblThongKe_DoanhThu.setOpaque(false);
        jScrollPane8.setViewportView(tblThongKe_DoanhThu);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Doanh thu", jPanel24);

        JPaneThongKe.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1220, 510));

        lblThongKe.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblThongKe.setForeground(new java.awt.Color(255, 255, 255));
        lblThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThongKe.setText("Thống kê");
        JPaneThongKe.add(lblThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 370, 70));

        lblTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/menu_bluee333.png"))); // NOI18N
        JPaneThongKe.add(lblTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 70));

        backg1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/6.png"))); // NOI18N
        JPaneThongKe.add(backg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1240, 520));

        jPaneForm.add(JPaneThongKe);
        JPaneThongKe.setBounds(40, 0, 1240, 590);

        JPaneHeThong.setBackground(new java.awt.Color(255, 255, 255));
        JPaneHeThong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/Log out.png"))); // NOI18N
        lblLogout.setText("Logout");
        lblLogout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblLogout.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoutMouseClicked(evt);
            }
        });
        JPaneHeThong.add(lblLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 120, 40));

        lblThoat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThoat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/Right.png"))); // NOI18N
        lblThoat.setText("Thoát");
        lblThoat.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblThoat.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThoatMouseClicked(evt);
            }
        });
        JPaneHeThong.add(lblThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 90, 120, 40));

        lblGioiThieu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGioiThieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGioiThieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/Conference.png"))); // NOI18N
        lblGioiThieu.setText("Giới Thiệu");
        lblGioiThieu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblGioiThieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGioiThieuMouseClicked(evt);
            }
        });
        JPaneHeThong.add(lblGioiThieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 120, 40));

        lblTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/User.png"))); // NOI18N
        lblTaiKhoan.setText("Tài Khoản");
        lblTaiKhoan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaiKhoanMouseClicked(evt);
            }
        });
        JPaneHeThong.add(lblTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 120, 40));
        JPaneHeThong.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 260, 10));
        JPaneHeThong.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 140, 260, -1));

        JPaneGioiThieu.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        JPaneGioiThieu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JPaneGioiThieu.setOpaque(false);
        JPaneGioiThieu.setLayout(null);
        JPaneGioiThieu.add(jSeparator5);
        jSeparator5.setBounds(340, 80, 360, 10);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setText("Giới thiệu về công ty");
        JPaneGioiThieu.add(jLabel25);
        jLabel25.setBounds(420, 50, 200, 22);

        JPaneHeThong.add(JPaneGioiThieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 1050, 400));

        JPaneTaiKhoan.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        JPaneTaiKhoan.setOpaque(false);
        JPaneTaiKhoan.setLayout(null);
        JPaneTaiKhoan.add(jSeparator3);
        jSeparator3.setBounds(190, 80, 400, 10);

        lblNameTK.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblNameTK.setForeground(new java.awt.Color(255, 0, 0));
        lblNameTK.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblNameTKAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        JPaneTaiKhoan.add(lblNameTK);
        lblNameTK.setBounds(280, 50, 310, 40);

        lblShow1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShow1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/icon/show.png"))); // NOI18N
        lblShow1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblShow1MouseClicked(evt);
            }
        });
        JPaneTaiKhoan.add(lblShow1);
        lblShow1.setBounds(550, 130, 40, 20);

        lblHide1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHide1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/icon/hide.png"))); // NOI18N
        lblHide1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHide1MouseClicked(evt);
            }
        });
        JPaneTaiKhoan.add(lblHide1);
        lblHide1.setBounds(550, 130, 40, 20);

        txtPassCu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPassCu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassCuFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPassCuFocusLost(evt);
            }
        });
        JPaneTaiKhoan.add(txtPassCu);
        txtPassCu.setBounds(190, 120, 400, 40);

        lblShow2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShow2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/icon/show.png"))); // NOI18N
        lblShow2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblShow2MouseClicked(evt);
            }
        });
        JPaneTaiKhoan.add(lblShow2);
        lblShow2.setBounds(550, 210, 40, 20);

        lblHide2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHide2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/icon/hide.png"))); // NOI18N
        lblHide2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHide2MouseClicked(evt);
            }
        });
        JPaneTaiKhoan.add(lblHide2);
        lblHide2.setBounds(550, 210, 40, 20);

        txtPassMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPassMoi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassMoiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPassMoiFocusLost(evt);
            }
        });
        JPaneTaiKhoan.add(txtPassMoi);
        txtPassMoi.setBounds(190, 200, 400, 40);

        lblShow3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShow3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/icon/show.png"))); // NOI18N
        lblShow3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblShow3MouseClicked(evt);
            }
        });
        JPaneTaiKhoan.add(lblShow3);
        lblShow3.setBounds(550, 290, 40, 20);

        lblHide3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHide3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/icon/hide.png"))); // NOI18N
        lblHide3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHide3MouseClicked(evt);
            }
        });
        JPaneTaiKhoan.add(lblHide3);
        lblHide3.setBounds(550, 290, 40, 20);

        txtPassXN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPassXN.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassXNFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPassXNFocusLost(evt);
            }
        });
        JPaneTaiKhoan.add(txtPassXN);
        txtPassXN.setBounds(190, 280, 400, 40);

        lblThemTK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThemTK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThemTK.setText("Đổi MK");
        lblThemTK.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblThemTK.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblThemTKAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        lblThemTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThemTKMouseClicked(evt);
            }
        });
        JPaneTaiKhoan.add(lblThemTK);
        lblThemTK.setBounds(730, 240, 120, 40);

        lblNewTK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNewTK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewTK.setText("NEW");
        lblNewTK.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblNewTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewTKMouseClicked(evt);
            }
        });
        JPaneTaiKhoan.add(lblNewTK);
        lblNewTK.setBounds(730, 150, 120, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("UserName :");
        JPaneTaiKhoan.add(jLabel3);
        jLabel3.setBounds(190, 60, 80, 19);

        JPaneHeThong.add(JPaneTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 1050, 400));

        JPaneAbout.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        JPaneAbout.setOpaque(false);
        JPaneAbout.setLayout(null);
        JPaneAbout.add(jSeparator4);
        jSeparator4.setBounds(340, 80, 360, 10);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Thông Tin Phần Mềm");
        JPaneAbout.add(jLabel10);
        jLabel10.setBounds(420, 50, 200, 22);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/logoLogin.png"))); // NOI18N
        JPaneAbout.add(jLabel11);
        jLabel11.setBounds(120, 110, 330, 210);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Phần mềm : Quản lí bất động sản\n");
        JPaneAbout.add(jLabel12);
        jLabel12.setBounds(600, 110, 220, 30);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Version : 1.0\n\n\n");
        JPaneAbout.add(jLabel13);
        jLabel13.setBounds(600, 140, 90, 15);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("+ Phân tích dự án , sản phẩm ");
        JPaneAbout.add(jLabel14);
        jLabel14.setBounds(610, 340, 190, 15);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Nhà Phát triển : Nguyễn Văn Tân\n");
        JPaneAbout.add(jLabel15);
        jLabel15.setBounds(600, 200, 190, 15);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Cty sở hữu : Cty bất động sản Thăng Long Real");
        JPaneAbout.add(jLabel16);
        jLabel16.setBounds(600, 180, 270, 15);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Nhà Phát triển : Nguyễn Văn Tân\n");
        JPaneAbout.add(jLabel17);
        jLabel17.setBounds(600, 160, 190, 15);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("+ Quản lí thông tin chi nhánh ");
        JPaneAbout.add(jLabel18);
        jLabel18.setBounds(610, 300, 190, 15);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("+ Quản lý thông tin Sản phẩm");
        JPaneAbout.add(jLabel19);
        jLabel19.setBounds(610, 260, 190, 15);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("+ Quản lý tài khoản người dùng");
        JPaneAbout.add(jLabel20);
        jLabel20.setBounds(610, 240, 190, 15);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("+ Quản lý thông tin dự án");
        JPaneAbout.add(jLabel21);
        jLabel21.setBounds(610, 280, 190, 15);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("- Các chức năng chính :");
        JPaneAbout.add(jLabel22);
        jLabel22.setBounds(600, 220, 190, 15);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("+ Thống kê doanh thu , dự án , đối tác ");
        JPaneAbout.add(jLabel23);
        jLabel23.setBounds(610, 360, 190, 15);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("+ Quản lí thông tin đối tác");
        JPaneAbout.add(jLabel24);
        jLabel24.setBounds(610, 320, 190, 15);

        JPaneHeThong.add(JPaneAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 1050, 400));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/menu_bluee333.png"))); // NOI18N
        jLabel2.setText("HỆ THỐNG");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JPaneHeThong.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 70));

        backk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/6.png"))); // NOI18N
        JPaneHeThong.add(backk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1240, 520));

        jPaneForm.add(JPaneHeThong);
        JPaneHeThong.setBounds(40, 0, 1240, 590);

        JPanePhanTich.setBackground(new java.awt.Color(51, 255, 0));

        javax.swing.GroupLayout JPanePhanTichLayout = new javax.swing.GroupLayout(JPanePhanTich);
        JPanePhanTich.setLayout(JPanePhanTichLayout);
        JPanePhanTichLayout.setHorizontalGroup(
            JPanePhanTichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1240, Short.MAX_VALUE)
        );
        JPanePhanTichLayout.setVerticalGroup(
            JPanePhanTichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        jPaneForm.add(JPanePhanTich);
        JPanePhanTich.setBounds(40, 0, 1240, 590);

        JPaneThoat.setBackground(new java.awt.Color(255, 255, 255));
        JPaneThoat.setLayout(null);

        right1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        right1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/panRight.png"))); // NOI18N
        JPaneThoat.add(right1);
        right1.setBounds(459, -10, 670, 610);

        top1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        top1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/panTopp.png"))); // NOI18N
        JPaneThoat.add(top1);
        top1.setBounds(0, 0, 982, 261);

        left1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        left1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/panLeft.png"))); // NOI18N
        JPaneThoat.add(left1);
        left1.setBounds(0, 0, 643, 590);

        bot1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bot1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/panBot.png"))); // NOI18N
        JPaneThoat.add(bot1);
        bot1.setBounds(126, 330, 1010, 270);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 51));
        jLabel8.setText("Bạn muốn kết thúc chương trình ?");
        JPaneThoat.add(jLabel8);
        jLabel8.setBounds(400, 220, 330, 45);

        btnCan1.setText("Không");
        btnCan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCan1ActionPerformed(evt);
            }
        });
        JPaneThoat.add(btnCan1);
        btnCan1.setBounds(630, 310, 90, 34);

        btnYes1.setText("Đồng ý");
        btnYes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYes1ActionPerformed(evt);
            }
        });
        JPaneThoat.add(btnYes1);
        btnYes1.setBounds(390, 310, 90, 34);

        jPaneForm.add(JPaneThoat);
        JPaneThoat.setBounds(160, 0, 1120, 590);

        JPaneLoogin.setBackground(new java.awt.Color(255, 255, 255));
        JPaneLoogin.setPreferredSize(new java.awt.Dimension(1120, 590));
        JPaneLoogin.setLayout(null);

        txtUserName.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUserNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserNameFocusLost(evt);
            }
        });
        txtUserName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUserNameMouseClicked(evt);
            }
        });
        JPaneLoogin.add(txtUserName);
        txtUserName.setBounds(440, 220, 250, 40);

        lblEyyy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEyyy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/icon/show.png"))); // NOI18N
        lblEyyy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEyyyMouseClicked(evt);
            }
        });
        JPaneLoogin.add(lblEyyy);
        lblEyyy.setBounds(650, 300, 40, 20);

        lblEye.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/icon/hide.png"))); // NOI18N
        lblEye.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblEyeAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        lblEye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEyeMouseClicked(evt);
            }
        });
        JPaneLoogin.add(lblEye);
        lblEye.setBounds(650, 300, 40, 20);

        txtPass.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPassFocusLost(evt);
            }
        });
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPassMouseClicked(evt);
            }
        });
        JPaneLoogin.add(txtPass);
        txtPass.setBounds(440, 290, 250, 40);

        chkGhiNho.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkGhiNho.setForeground(new java.awt.Color(255, 255, 255));
        chkGhiNho.setText("Ghi nhớ thông tin");
        chkGhiNho.setOpaque(false);
        JPaneLoogin.add(chkGhiNho);
        chkGhiNho.setBounds(440, 340, 130, 23);

        btnLoginOK.setText("Login");
        btnLoginOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginOKActionPerformed(evt);
            }
        });
        JPaneLoogin.add(btnLoginOK);
        btnLoginOK.setBounds(600, 390, 90, 30);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        JPaneLoogin.add(btnCancel);
        btnCancel.setBounds(443, 390, 90, 30);

        lblLogin.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/menu_bluee2.png"))); // NOI18N
        lblLogin.setText("LOGIN");
        lblLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JPaneLoogin.add(lblLogin);
        lblLogin.setBounds(0, 0, 1120, 70);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/LoginBackgXX.png"))); // NOI18N
        jLabel4.setOpaque(true);
        JPaneLoogin.add(jLabel4);
        jLabel4.setBounds(230, 100, 700, 480);

        jPaneForm.add(JPaneLoogin);
        JPaneLoogin.setBounds(160, 0, 1130, 590);

        lbl_BackG_Home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_BackG_Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/menu_blue1.png"))); // NOI18N
        jPaneForm.add(lbl_BackG_Home);
        lbl_BackG_Home.setBounds(40, 0, 120, 70);

        JPaneLogout.setBackground(new java.awt.Color(255, 255, 255));
        JPaneLogout.setPreferredSize(new java.awt.Dimension(1120, 590));
        JPaneLogout.setLayout(null);

        txtUserName1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtUserName1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUserName1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserName1FocusLost(evt);
            }
        });
        JPaneLogout.add(txtUserName1);
        txtUserName1.setBounds(440, 220, 250, 40);

        lblShowLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShowLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/icon/show.png"))); // NOI18N
        lblShowLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblShowLogoutMouseClicked(evt);
            }
        });
        JPaneLogout.add(lblShowLogout);
        lblShowLogout.setBounds(650, 300, 40, 20);

        lblHideLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHideLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/icon/hide.png"))); // NOI18N
        lblHideLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHideLogoutMouseClicked(evt);
            }
        });
        JPaneLogout.add(lblHideLogout);
        lblHideLogout.setBounds(650, 300, 40, 20);

        txtPass1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPass1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPass1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPass1FocusLost(evt);
            }
        });
        JPaneLogout.add(txtPass1);
        txtPass1.setBounds(440, 290, 250, 40);

        chkGhiNho1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkGhiNho1.setForeground(new java.awt.Color(255, 255, 255));
        chkGhiNho1.setText("Ghi nhớ thông tin");
        chkGhiNho1.setOpaque(false);
        JPaneLogout.add(chkGhiNho1);
        chkGhiNho1.setBounds(440, 340, 130, 23);

        btnLoginOK1.setText("Login");
        btnLoginOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginOK1ActionPerformed(evt);
            }
        });
        JPaneLogout.add(btnLoginOK1);
        btnLoginOK1.setBounds(600, 390, 90, 30);

        btnEx.setText("Exit");
        btnEx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExActionPerformed(evt);
            }
        });
        JPaneLogout.add(btnEx);
        btnEx.setBounds(443, 390, 90, 30);

        lblLogin1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblLogin1.setForeground(new java.awt.Color(255, 255, 255));
        lblLogin1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/menu_bluee333.png"))); // NOI18N
        lblLogin1.setText("LOGIN");
        lblLogin1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JPaneLogout.add(lblLogin1);
        lblLogin1.setBounds(-120, 0, 1240, 70);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imager/hinh/LoginBackgXX.png"))); // NOI18N
        jLabel9.setOpaque(true);
        JPaneLogout.add(jLabel9);
        jLabel9.setBounds(230, 100, 700, 480);

        jPaneForm.add(JPaneLogout);
        JPaneLogout.setBounds(160, 0, 1130, 590);

        jPanel4.add(jPaneForm);
        jPaneForm.setBounds(0, 0, 1280, 590);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 590));

        setSize(new java.awt.Dimension(1286, 620));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPaneFormAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPaneFormAncestorAdded

        // TODO add your handling code here:
    }//GEN-LAST:event_jPaneFormAncestorAdded

    private void txtUserNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserNameFocusLost
        if (txtUserName.getText().isEmpty()) {
            txtUserName.setForeground(Color.GRAY);
            txtUserName.setText("Nhập tên đăng nhập");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameFocusLost

    private void txtPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassFocusLost
        if (txtPass.getText().isEmpty()) {
            txtPass.setForeground(Color.GRAY);
            txtPass.setText("Nhập mật khẩu");
            txtPass.setEchoChar((char) 0);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassFocusLost

    private void txtUserNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserNameFocusGained
        if (txtUserName.getText().equals("Nhập tên đăng nhập")) {
            txtUserName.setText("");
            txtUserName.setForeground(Color.BLACK);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameFocusGained

    private void txtPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassFocusGained
        if (txtPass.getText().equals("Nhập mật khẩu")) {
            txtPass.setText("");
            txtPass.setForeground(Color.BLACK);
            txtPass.setEchoChar('*');    // chuyển mật khẩu sang dạng *
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassFocusGained

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        JPaneHome.setVisible(true);
        JPaneLoogin.setVisible(false);
        btnLogin.setSelected(false);

    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnLoginOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginOKActionPerformed
        // TODO add your handling code here:
        UserLogin();
    }//GEN-LAST:event_btnLoginOKActionPerformed

    private void jPaneMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPaneMenuMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_jPaneMenuMouseClicked

    private void backgroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_backgroundMouseClicked

    private void JPaneMenuLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPaneMenuLoginMouseClicked

        // jPanel2.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_JPaneMenuLoginMouseClicked

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked

        // TODO add your handling code here:
        btnExit.setSelected(true);
        btnLogin.setSelected(false);
        jPaneExit.setVisible(true);
        JPaneLoogin.setVisible(false);
        if (JPaneHome.isVisible()) {
            JPaneHome.setVisible(false);
        }

        this.ExitPane();

    }//GEN-LAST:event_btnExitMouseClicked

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked

        btnExit.setSelected(false);
        btnLogin.setSelected(true);
        JPaneLoogin.setVisible(true);
        jPaneExit.setVisible(false);
        JPaneHome.setVisible(false);
        //  txtUserName.requestFocus();
    }//GEN-LAST:event_btnLoginMouseClicked

    private void JPaneMenuLogin2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPaneMenuLogin2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JPaneMenuLogin2MouseClicked

    private void btnHeThongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHeThongMouseClicked

    }//GEN-LAST:event_btnHeThongMouseClicked

    private void btnQuanLiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuanLiMouseClicked

    }//GEN-LAST:event_btnQuanLiMouseClicked

    private void btnThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseClicked

    }//GEN-LAST:event_btnThongKeMouseClicked

    private void btnPhanTichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPhanTichMouseClicked

    }//GEN-LAST:event_btnPhanTichMouseClicked

    private void lblGifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGifMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_lblGifMouseClicked

    private void lblGifMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGifMousePressed

        this.MenuThu();
    }//GEN-LAST:event_lblGifMousePressed

    private void lblGachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGachMousePressed
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    for (int a = 0; a <= 20; a++) {
                        Thread.sleep(30);
                        if (a == 1) {
                            jPaneMenu.setSize(46, 590);
                            lblGach.setSize(0, 0);
                            lblGif.setSize(160, 50);
                            lblBackG.setVisible(false);
                        }
                        if (a == 2) {
                            jPaneMenu.setSize(52, 590);
                        }
                        if (a == 3) {
                            jPaneMenu.setSize(58, 590);
                        }
                        if (a == 4) {
                            jPaneMenu.setSize(64, 590);
                        }
                        if (a == 5) {
                            jPaneMenu.setSize(70, 590);
                        }
                        if (a == 6) {
                            jPaneMenu.setSize(76, 590);
                        }
                        if (a == 7) {
                            jPaneMenu.setSize(82, 590);
                        }
                        if (a == 8) {
                            jPaneMenu.setSize(88, 590);
                        }
                        if (a == 9) {
                            jPaneMenu.setSize(94, 590);
                        }
                        if (a == 10) {
                            jPaneMenu.setSize(100, 590);

                        }
                        if (a == 11) {
                            jPaneMenu.setSize(106, 590);

                        }
                        if (a == 12) {
                            jPaneMenu.setSize(112, 590);

                        }
                        if (a == 13) {
                            jPaneMenu.setSize(118, 590);

                        }
                        if (a == 14) {
                            jPaneMenu.setSize(124, 590);

                        }
                        if (a == 15) {
                            jPaneMenu.setSize(130, 590);

                        }
                        if (a == 16) {
                            jPaneMenu.setSize(136, 590);

                        }
                        if (a == 17) {
                            jPaneMenu.setSize(142, 590);

                        }
                        if (a == 18) {
                            jPaneMenu.setSize(148, 590);

                        }
                        if (a == 19) {
                            jPaneMenu.setSize(154, 590);

                        }
                        if (a == 20) {
                            jPaneMenu.setSize(160, 590);

                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        th.start();
        // TODO add your handling code here:
    }//GEN-LAST:event_lblGachMousePressed

    private void btnCanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanActionPerformed
        // TODO add your handling code here:
        jPaneExit.setVisible(false);
        JPaneHome.setVisible(true);
        btnExit.setSelected(false);
//        if(JPaneHeThong.isVisible()){
//        JPaneHeThong.setVisible(true);
//        }
    }//GEN-LAST:event_btnCanActionPerformed

    private void btnYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYesActionPerformed
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnYesActionPerformed

    private void btnExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitMousePressed

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked

    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnCan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCan1ActionPerformed
        JPaneHeThong.setVisible(true);
        JPaneThoat.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCan1ActionPerformed

    private void btnYes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYes1ActionPerformed
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnYes1ActionPerformed

    private void lblThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThoatMouseClicked
        JPaneHeThong.setVisible(false);
        JPaneThoat.setVisible(true);

        this.ThoatPane();
        // TODO add your handling code here:
     
    }//GEN-LAST:event_lblThoatMouseClicked

    private void lblLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseClicked
 
        // JPaneLogout.setVisible(true);
        JPaneLoogin.setVisible(true);
        JPaneHeThong.setVisible(false);
        btnHome.setEnabled(false);
        btnThongKe.setEnabled(false);
        btnPhanTich.setEnabled(false);
        btnQuanLi.setEnabled(false);
        btnHeThong.setEnabled(false);
        JPaneMenuLogin2.setEnabled(false);
        JPaneMenuLogin2.setVisible(true);
        lblBackG.setVisible(false);
        lblGach.setVisible(false);
        lblGif.setVisible(false);

        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    for (int a = 0; a <= 1; a++) {
                        Thread.sleep(0);
                        if (a == 1) {
                            jPaneMenu.setSize(160, 590);

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        th.start();
    }//GEN-LAST:event_lblLogoutMouseClicked

    private void txtUserName1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserName1FocusGained
        // TODO add your handling code here:
        if (txtUserName1.getText().equals("Nhập tên đăng nhập")) {
            txtUserName1.setText("");
            txtUserName1.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtUserName1FocusGained

    private void txtUserName1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserName1FocusLost
        // TODO add your handling code here:
        if (txtUserName1.getText().isEmpty()) {
            txtUserName1.setForeground(Color.GRAY);
            txtUserName1.setText("Nhập tên đăng nhập");
        }
    }//GEN-LAST:event_txtUserName1FocusLost

    private void txtPass1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPass1FocusGained
        if (txtPass1.getText().equals("Nhập mật khẩu")) {
            txtPass1.setText("");
            txtPass1.setForeground(Color.BLACK);
            txtPass1.setEchoChar('*');
        }
    }//GEN-LAST:event_txtPass1FocusGained

    private void txtPass1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPass1FocusLost
        if (txtPass1.getText().isEmpty()) {
            setForeground(Color.GRAY);
            txtPass1.setText("Nhập mật khẩu");
            txtPass1.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_txtPass1FocusLost

    private void btnLoginOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginOK1ActionPerformed
        UserLogin();
    }//GEN-LAST:event_btnLoginOK1ActionPerformed

    private void btnExActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExActionPerformed
        System.exit(0);
//        JPaneLogout.setVisible(false);
//        JPaneHeThong.setVisible(true);
    }//GEN-LAST:event_btnExActionPerformed

    private void lblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoanMouseClicked

        JPaneTaiKhoan.setVisible(true);
        lblNameTK.setText(TenNgDung);
        if (JPaneGioiThieu.isVisible()) {
            JPaneGioiThieu.setVisible(false);
        }
        if (JPaneAbout.isVisible()) {
            JPaneAbout.setVisible(false);
        }
    }//GEN-LAST:event_lblTaiKhoanMouseClicked

    private void lblThemTKAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblThemTKAncestorAdded

        // TODO add your handling code here:
    }//GEN-LAST:event_lblThemTKAncestorAdded

    private void lblThemTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemTKMouseClicked
        if (txtPassCu.getPassword().equals("") || txtPassMoi.getPassword().equals("") || txtPassXN.getPassword().equals("")) {
            JOptionPane.showMessageDialog(this, " Vui lòng nhập đủ thông tin !");
        } else {
            this.DoiMK();
        }
    }//GEN-LAST:event_lblThemTKMouseClicked

    private void lblNameTKAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblNameTKAncestorAdded
        lblNameTK.setText(TenNgDung);
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNameTKAncestorAdded

    private void txtUserNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUserNameMouseClicked
//        txtUserName.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameMouseClicked

    private void txtPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMouseClicked
        //     txtPass.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassMouseClicked

    private void lblNewTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewTKMouseClicked
        txtPassCu.setText("");
        txtPassMoi.setText("");
        txtPassXN.setText("");
    }//GEN-LAST:event_lblNewTKMouseClicked

    private void lblEyyyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEyyyMouseClicked
        lblEye.setVisible(true);
        lblEyyy.setVisible(false);
        txtPass.setEchoChar((char) 0);
    }//GEN-LAST:event_lblEyyyMouseClicked

    private void lblEyeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblEyeAncestorAdded

    }//GEN-LAST:event_lblEyeAncestorAdded

    private void lblEyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEyeMouseClicked
        lblEye.setVisible(false);
        lblEyyy.setVisible(true);
        txtPass.setEchoChar('*');
    }//GEN-LAST:event_lblEyeMouseClicked

    private void txtPassCuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassCuFocusLost
        if (txtPassCu.getText().isEmpty()) {
            txtPassCu.setForeground(Color.GRAY);
            txtPassCu.setText("Nhập mật khẩu cũ");
            txtPassCu.setEchoChar((char) 0);
        }

    }//GEN-LAST:event_txtPassCuFocusLost

    private void txtPassMoiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassMoiFocusLost
        if (txtPassMoi.getText().isEmpty()) {
            txtPassMoi.setForeground(Color.GRAY);
            txtPassMoi.setText("Nhập mật khẩu mới");
            txtPassMoi.setEchoChar((char) 0);
        }

    }//GEN-LAST:event_txtPassMoiFocusLost

    private void txtPassXNFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassXNFocusLost
        if (txtPassXN.getText().isEmpty()) {
            txtPassXN.setForeground(Color.GRAY);
            txtPassXN.setText("Xác nhận mật khẩu mới");
            txtPassXN.setEchoChar((char) 0);
        }

    }//GEN-LAST:event_txtPassXNFocusLost

    private void txtPassCuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassCuFocusGained
        if (txtPassCu.getText().equals("Nhập mật khẩu cũ")) {
            txtPassCu.setText("");
            txtPassCu.setForeground(Color.BLACK);
            txtPassCu.setEchoChar('*');  // chuyển mật khẩu từ String sang *
        }

    }//GEN-LAST:event_txtPassCuFocusGained

    private void txtPassMoiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassMoiFocusGained
        if (txtPassMoi.getText().equals("Nhập mật khẩu mới")) {
            txtPassMoi.setText("");
            txtPassMoi.setForeground(Color.BLACK);
            txtPassMoi.setEchoChar('*');
        }
    }//GEN-LAST:event_txtPassMoiFocusGained

    private void txtPassXNFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassXNFocusGained
        if (txtPassXN.getText().equals("Xác nhận mật khẩu mới")) {
            txtPassXN.setText("");
            txtPassXN.setForeground(Color.BLACK);
            txtPassXN.setEchoChar('*');
        }

    }//GEN-LAST:event_txtPassXNFocusGained

    private void lblShow1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShow1MouseClicked
        lblShow1.setVisible(false);
        lblHide1.setVisible(true);
        txtPassCu.setEchoChar((char) 0);
    }//GEN-LAST:event_lblShow1MouseClicked

    private void lblHide1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHide1MouseClicked
        lblHide1.setVisible(false);
        lblShow1.setVisible(true);
        txtPassCu.setEchoChar('*');
    }//GEN-LAST:event_lblHide1MouseClicked

    private void lblShow2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShow2MouseClicked
        lblShow2.setVisible(false);
        lblHide2.setVisible(true);
        txtPassMoi.setEchoChar((char) 0);
    }//GEN-LAST:event_lblShow2MouseClicked

    private void lblHide2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHide2MouseClicked
        lblHide2.setVisible(false);
        lblShow2.setVisible(true);
        txtPassMoi.setEchoChar('*');
    }//GEN-LAST:event_lblHide2MouseClicked

    private void lblShow3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShow3MouseClicked
        lblShow3.setVisible(false);
        lblHide3.setVisible(true);
        txtPassXN.setEchoChar((char) 0);
    }//GEN-LAST:event_lblShow3MouseClicked

    private void lblHide3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHide3MouseClicked
        lblShow3.setVisible(true);
        lblHide3.setVisible(false);
        txtPassXN.setEchoChar('*');
    }//GEN-LAST:event_lblHide3MouseClicked

    private void lblShowLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowLogoutMouseClicked
        lblShowLogout.setVisible(false);
        lblHideLogout.setVisible(true);
        txtPass1.setEchoChar((char) 0);
    }//GEN-LAST:event_lblShowLogoutMouseClicked

    private void lblHideLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHideLogoutMouseClicked
        lblHideLogout.setVisible(false);
        lblShowLogout.setVisible(true);
        txtPass1.setEchoChar('*');
    }//GEN-LAST:event_lblHideLogoutMouseClicked

    private void lblGioiThieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGioiThieuMouseClicked
        JPaneGioiThieu.setVisible(true);
        if (JPaneAbout.isVisible()) {
            JPaneAbout.setVisible(false);
        }
        if (JPaneTaiKhoan.isVisible()) {
            JPaneTaiKhoan.setVisible(false);
        }

    }//GEN-LAST:event_lblGioiThieuMouseClicked

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        lbl_BackG_Home.setVisible(true);
        btnHome.setSelected(true);
        btnHeThong.setSelected(false);
        btnPhanTich.setSelected(false);
        btnThongKe.setSelected(false);
        btnQuanLi.setSelected(false);
        JPaneMenuLogin2.setVisible(true);
        JPaneHome.setVisible(true);

        if (JPanePhanTich.isVisible()) {
            JPanePhanTich.setVisible(false);
        }
        if (JPaneThongKe.isVisible()) {
            JPaneThongKe.setVisible(false);
        }
        if (JPaneQuanLi.isVisible()) {
            JPaneQuanLi.setVisible(false);
        }
        if (JPaneHeThong.isVisible()) {
            JPaneHeThong.setVisible(false);
        }

//        if (lblGach.isVisible() && lblGif.isVisible()) {
//            lblGach.setVisible(false);
//            lblGif.setVisible(false);
//        }
        lblGach.setVisible(true);
        lblGif.setVisible(true);
        this.MenuThu();

    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnPhanTichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanTichActionPerformed
        if (role == 2 || role == 3 || role == 4) {
            JPanePhanTich.setVisible(false);
        } else {
            btnPhanTich.setSelected(true);
            btnHome.setSelected(false);
            btnHeThong.setSelected(false);
            btnThongKe.setSelected(false);
            btnQuanLi.setSelected(false);

            JPanePhanTich.setVisible(true);
            if (JPaneHome.isVisible()) {
                JPaneHome.setVisible(false);
            }
            if (JPaneThongKe.isVisible()) {
                JPaneThongKe.setVisible(false);
            }
            if (JPaneQuanLi.isVisible()) {
                JPaneQuanLi.setVisible(false);
            }
            if (JPaneHeThong.isVisible()) {
                JPaneHeThong.setVisible(false);
            }

            lblGach.setVisible(true);
            lblGif.setVisible(true);
            this.MenuThu();
        }
    }//GEN-LAST:event_btnPhanTichActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed

//         this.add(JPaneThongKe, new Integer(20), 0);
//         this.add(JPaneMenuLogin2, new Integer(25), 0);
        if (role == 2 || role == 3 || role == 4) {
            JPaneThongKe.setVisible(false);
        } else {
            btnThongKe.setSelected(true);
            btnHome.setSelected(false);
            btnHeThong.setSelected(false);
            btnPhanTich.setSelected(false);
            btnQuanLi.setSelected(false);

            JPaneThongKe.setVisible(true);
            if (JPaneHome.isVisible()) {
                JPaneHome.setVisible(false);
            }
            if (JPanePhanTich.isVisible()) {
                JPanePhanTich.setVisible(false);
            }

            if (JPaneQuanLi.isVisible()) {
                JPaneQuanLi.setVisible(false);
            }

            if (JPaneHeThong.isVisible()) {
                JPaneHeThong.setVisible(false);
            }

            lblGach.setVisible(true);
            lblGif.setVisible(true);
            this.MenuThu();
        }
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnQuanLiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLiActionPerformed
        if (role == 2 || role == 3 || role == 4) {
            btnNguoiDung.setEnabled(false);
        }

        btnQuanLi.setSelected(true);
        btnThongKe.setSelected(false);
        btnHome.setSelected(false);
        btnHeThong.setSelected(false);
        btnPhanTich.setSelected(false);

        JPaneQuanLi.setVisible(true);
        if (JPaneThongKe.isVisible()) {
            JPaneThongKe.setVisible(false);
        }
        if (JPaneHome.isVisible()) {
            JPaneHome.setVisible(false);
        }

        if (JPanePhanTich.isVisible()) {
            JPanePhanTich.setVisible(false);
        }
        if (JPaneHeThong.isVisible()) {
            JPaneHeThong.setVisible(false);
        }

        lblGach.setVisible(true);
        lblGif.setVisible(true);
        this.MenuThu();
    }//GEN-LAST:event_btnQuanLiActionPerformed

    private void btnHeThongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHeThongActionPerformed

        JPaneAbout.setVisible(true);
        if (JPaneGioiThieu.isVisible()) {
            JPaneGioiThieu.setVisible(false);
        }
        if (JPaneTaiKhoan.isVisible()) {
            JPaneTaiKhoan.setVisible(false);
        }
        btnHeThong.setSelected(true);
        btnQuanLi.setSelected(false);
        btnThongKe.setSelected(false);
        btnHome.setSelected(false);
        btnPhanTich.setSelected(false);

        JPaneHeThong.setVisible(true);
        if (JPaneQuanLi.isVisible()) {
            JPaneQuanLi.setVisible(false);
        }
        if (JPaneThongKe.isVisible()) {
            JPaneThongKe.setVisible(false);
        }

        if (JPaneHome.isVisible()) {
            JPaneHome.setVisible(false);
        }
        if (JPanePhanTich.isVisible()) {
            JPanePhanTich.setVisible(false);
        }
//
//        if (lblGach.isVisible() && lblGif.isVisible()) {
//            lblGach.setVisible(false);
//            lblGif.setVisible(false);
//        }

        lblGach.setVisible(true);
        lblGif.setVisible(true);
        this.MenuThu();
    }//GEN-LAST:event_btnHeThongActionPerformed

    private void btnDuAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuAnActionPerformed
        lblQLduAn.setVisible(true);
        if (lblQLdoitac.isVisible()) {
            lblQLdoitac.setVisible(false);
        }
        if (lblQLnguoiDung.isVisible()) {
            lblQLnguoiDung.setVisible(false);
        }
        if (lblQLsanPham.isVisible()) {
            lblQLsanPham.setVisible(false);
        }
        if (lblQLkhachHang.isVisible()) {
            lblQLkhachHang.setVisible(false);
        }
        jTabbQLnguoiDung.setVisible(false);
        jTabbQLDuAn.setVisible(true);
        jTabbQLkhachHang.setVisible(false);
        jTabbQLdoiTac.setVisible(false);
        jTabbQLsanPham.setVisible(false);
    }//GEN-LAST:event_btnDuAnActionPerformed

    private void btnSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPActionPerformed
        lblQLsanPham.setVisible(true);
        if (lblQLdoitac.isVisible()) {
            lblQLdoitac.setVisible(false);
        }
        if (lblQLnguoiDung.isVisible()) {
            lblQLnguoiDung.setVisible(false);
        }
        if (lblQLduAn.isVisible()) {
            lblQLduAn.setVisible(false);
        }
        if (lblQLkhachHang.isVisible()) {
            lblQLkhachHang.setVisible(false);
        }
        jTabbQLsanPham.setVisible(true);
        jTabbQLnguoiDung.setVisible(false);
        jTabbQLDuAn.setVisible(false);
        jTabbQLkhachHang.setVisible(false);
        jTabbQLdoiTac.setVisible(false);
    }//GEN-LAST:event_btnSPActionPerformed

    private void btnNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNguoiDungActionPerformed
        lblQLnguoiDung.setVisible(true);

        if (lblQLdoitac.isVisible()) {
            lblQLdoitac.setVisible(false);
        }
        if (lblQLsanPham.isVisible()) {
            lblQLsanPham.setVisible(false);
        }
        if (lblQLduAn.isVisible()) {
            lblQLduAn.setVisible(false);
        }

        if (lblQLkhachHang.isVisible()) {
            lblQLkhachHang.setVisible(false);
        }

        jTabbQLnguoiDung.setVisible(true);
        jTabbQLDuAn.setVisible(false);
        jTabbQLkhachHang.setVisible(false);
        jTabbQLdoiTac.setVisible(false);
        jTabbQLsanPham.setVisible(false);
    }//GEN-LAST:event_btnNguoiDungActionPerformed

    private void btnDoiTacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiTacActionPerformed
        lblQLdoitac.setVisible(true);
        if (lblQLnguoiDung.isVisible()) {
            lblQLnguoiDung.setVisible(false);
        }
        if (lblQLsanPham.isVisible()) {
            lblQLsanPham.setVisible(false);
        }
        if (lblQLduAn.isVisible()) {
            lblQLduAn.setVisible(false);
        }
        if (lblQLkhachHang.isVisible()) {
            lblQLkhachHang.setVisible(false);
        }
        jTabbQLdoiTac.setVisible(true);
        jTabbQLnguoiDung.setVisible(false);
        jTabbQLDuAn.setVisible(false);
        jTabbQLkhachHang.setVisible(false);
        jTabbQLsanPham.setVisible(false);
    }//GEN-LAST:event_btnDoiTacActionPerformed

    private void txtNDpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNDpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNDpassActionPerformed

    private void lblAnhNDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhNDMouseClicked

        setAnhSize(lblAnhND);
    }//GEN-LAST:event_lblAnhNDMouseClicked

    private void jTabbQLnguoiDungAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTabbQLnguoiDungAncestorAdded
        this.loadNgDung();
        this.setStatusND(true);
    }//GEN-LAST:event_jTabbQLnguoiDungAncestorAdded

    private void btnMoiNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiNDActionPerformed

        this.clearND();
        this.setStatusND(true);
    }//GEN-LAST:event_btnMoiNDActionPerformed

    private void btnXoaNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNDActionPerformed
        this.deleteND();
    }//GEN-LAST:event_btnXoaNDActionPerformed

    private void btnSuaNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNDActionPerformed
        if (checkND()) {
            this.updateND();
        }
    }//GEN-LAST:event_btnSuaNDActionPerformed

    private void btnThemNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNDActionPerformed
        NguoiDung nd = daoND.findByUsernameND(txtNDtenDangNhap.getText());
        NguoiDung sdt = daoND.findBySdtND(txtNDsdt.getText());
        NguoiDung mail = daoND.findByEmailND(txtNDemail.getText());
        if (nd != null) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại", "error", JOptionPane.ERROR_MESSAGE);

        } else if (sdt != null) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại", "error", JOptionPane.ERROR_MESSAGE);

        } else if (mail != null) {
            JOptionPane.showMessageDialog(this, "Email đã tồn tại", "error", JOptionPane.ERROR_MESSAGE);

        } else {
            if (checkND()) {
                this.insertNgDung();
            }
        }


    }//GEN-LAST:event_btnThemNDActionPerformed

    private void btnL_NDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnL_NDActionPerformed
        this.indexNgDung--;
        this.EditND();
    }//GEN-LAST:event_btnL_NDActionPerformed

    private void btnNL_NDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNL_NDActionPerformed
        this.indexNgDung = 0;
        this.EditND();
    }//GEN-LAST:event_btnNL_NDActionPerformed

    private void btnR_NDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnR_NDActionPerformed
        this.indexNgDung++;
        this.EditND();
    }//GEN-LAST:event_btnR_NDActionPerformed

    private void btnNR_NDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNR_NDActionPerformed
        this.indexNgDung = tblNguoiDung.getRowCount() - 1;
        this.EditND();
    }//GEN-LAST:event_btnNR_NDActionPerformed

    private void btnDAthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAthemActionPerformed
        DuAn name = daoDA.finByNameDA(txtDA_name.getText());
        if (name != null) {
            JOptionPane.showMessageDialog(this, " Dự án đã tồn tại !", "error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (checkDA()) {
                this.insertDA();
            }
        }
    }//GEN-LAST:event_btnDAthemActionPerformed

    private void btnDAsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAsuaActionPerformed
        if (checkDA()) {
            this.updateDA();
        }
    }//GEN-LAST:event_btnDAsuaActionPerformed

    private void btnDAxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAxoaActionPerformed
        this.deleteDA();
    }//GEN-LAST:event_btnDAxoaActionPerformed

    private void btnDAmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAmoiActionPerformed
        this.clearDA();
        this.setStatusDA(true);
    }//GEN-LAST:event_btnDAmoiActionPerformed

    private void btnNL_DAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNL_DAActionPerformed
        this.indexDuAn = 0;
        this.EditDA();
    }//GEN-LAST:event_btnNL_DAActionPerformed

    private void btnL_DAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnL_DAActionPerformed
        this.indexDuAn--;
        this.EditDA();
    }//GEN-LAST:event_btnL_DAActionPerformed

    private void btnNR_DAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNR_DAActionPerformed
        this.indexDuAn = tbl_DuAn.getRowCount() - 1;
        this.EditDA();
    }//GEN-LAST:event_btnNR_DAActionPerformed

    private void btnR_DAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnR_DAActionPerformed
        this.indexDuAn++;
        this.EditDA();
    }//GEN-LAST:event_btnR_DAActionPerformed

    private void lblDA_anhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDA_anhMouseClicked
        this.setAnhSize(lblDA_anh);
    }//GEN-LAST:event_lblDA_anhMouseClicked

    private void jTabbQLDuAnAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTabbQLDuAnAncestorAdded
        this.loadDA();
        this.setStatusDA(true);
    }//GEN-LAST:event_jTabbQLDuAnAncestorAdded

    private void tblNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiDungMouseClicked
        if (evt.getClickCount() == 1) {
            this.indexNgDung = tblNguoiDung.rowAtPoint(evt.getPoint());
            if (this.indexNgDung >= 0) {
                this.EditND();
                jTabbQLnguoiDung.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblNguoiDungMouseClicked

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        lblQLkhachHang.setVisible(true);

        if (lblQLdoitac.isVisible()) {
            lblQLdoitac.setVisible(false);
        }
        if (lblQLnguoiDung.isVisible()) {
            lblQLnguoiDung.setVisible(false);
        }
        if (lblQLduAn.isVisible()) {
            lblQLduAn.setVisible(false);
        }
        if (lblQLsanPham.isVisible()) {
            lblQLsanPham.setVisible(false);
        }
        jTabbQLnguoiDung.setVisible(false);
        jTabbQLDuAn.setVisible(false);
        jTabbQLkhachHang.setVisible(true);
        jTabbQLdoiTac.setVisible(false);
        jTabbQLsanPham.setVisible(false);
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnSP_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSP_ThemActionPerformed
        SanPham name = daoSP.finByNameSP(txtSP_name.getText());
        if (name != null) {
            JOptionPane.showMessageDialog(this, " Sản phẩm đã tồn tại !", "error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (checkSP()) {
                this.insertSP();
            }
        }

    }//GEN-LAST:event_btnSP_ThemActionPerformed

    private void btnSP_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSP_SuaActionPerformed
        if (checkSP()) {
            this.updateSP();
        }
    }//GEN-LAST:event_btnSP_SuaActionPerformed

    private void btnSP_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSP_XoaActionPerformed
        this.deleteSP();
    }//GEN-LAST:event_btnSP_XoaActionPerformed

    private void btnSP_MoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSP_MoiActionPerformed
        this.clearSP();
        this.setStatusSP(true);
    }//GEN-LAST:event_btnSP_MoiActionPerformed

    private void btnNL_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNL_SPActionPerformed
        this.indexSP = 0;
    }//GEN-LAST:event_btnNL_SPActionPerformed

    private void btnL_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnL_SPActionPerformed
        this.indexSP--;
    }//GEN-LAST:event_btnL_SPActionPerformed

    private void btnNR_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNR_SPActionPerformed
        this.indexSP = tbl_SanPham.getRowCount() - 1;
    }//GEN-LAST:event_btnNR_SPActionPerformed

    private void btnR_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnR_SPActionPerformed
        this.indexSP++;
    }//GEN-LAST:event_btnR_SPActionPerformed

    private void lblAnh_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnh_SPMouseClicked
        this.setAnhSize(lblAnh_SP);
    }//GEN-LAST:event_lblAnh_SPMouseClicked

    private void jTabbQLsanPhamAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTabbQLsanPhamAncestorAdded
        this.loadSP();
        this.setStatusSP(true);
    }//GEN-LAST:event_jTabbQLsanPhamAncestorAdded

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        if (evt.getClickCount() == 1) {
            this.indexKhachHang = tblKhachHang.rowAtPoint(evt.getPoint());
            if (this.indexKhachHang >= 0) {
                this.EditKH();
                jTabbQLkhachHang.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void jTabbQLkhachHangAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTabbQLkhachHangAncestorAdded
        this.loadKH();
        this.setStatusKH(true);
    }//GEN-LAST:event_jTabbQLkhachHangAncestorAdded

    private void lblAnhKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhKHMouseClicked
        this.setAnhSize(lblAnhKH);
    }//GEN-LAST:event_lblAnhKHMouseClicked

    private void btnR_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnR_KHActionPerformed
        this.indexKhachHang++;
        this.EditKH();
    }//GEN-LAST:event_btnR_KHActionPerformed

    private void btnNR_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNR_KHActionPerformed
        this.indexKhachHang = tblKhachHang.getRowCount() - 1;
        this.EditKH();
    }//GEN-LAST:event_btnNR_KHActionPerformed

    private void btnL_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnL_KHActionPerformed
        this.indexKhachHang--;
        this.EditKH();
    }//GEN-LAST:event_btnL_KHActionPerformed

    private void btnNL_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNL_KHActionPerformed
        this.indexKhachHang = 0;
        this.EditKH();
    }//GEN-LAST:event_btnNL_KHActionPerformed

    private void btnKH_MoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKH_MoiActionPerformed
        this.clearKH();
        this.setStatusKH(true);
    }//GEN-LAST:event_btnKH_MoiActionPerformed

    private void btnKH_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKH_XoaActionPerformed
        this.deleteKH();
    }//GEN-LAST:event_btnKH_XoaActionPerformed

    private void btnKH_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKH_SuaActionPerformed
        if (checkKH()) {
            this.updateKH();
        }
    }//GEN-LAST:event_btnKH_SuaActionPerformed

    private void btnKH_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKH_ThemActionPerformed
        KhachHang sdt = daoKH.findBysdtKH(txtKH_SDT.getText());
        KhachHang maill = daoKH.findByEmailKH(txtKH_Email.getText());
        if (sdt != null) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại !", "error", JOptionPane.ERROR_MESSAGE);
        } else if (maill != null) {
            JOptionPane.showMessageDialog(this, " Email đã tồn tại !", "error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (checkKH()) {
                this.insertKH();
            }
        }

    }//GEN-LAST:event_btnKH_ThemActionPerformed

    private void btnDT_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDT_ThemActionPerformed

        DoiTac mail = daoDT.findByEmailDT(txtDT_Email.getText());
        DoiTac sdt = daoDT.findBySdtDT(txtDT_SDT.getText());

        if (mail != null) {
            JOptionPane.showMessageDialog(this, "Email này đã tồn tại", "error", JOptionPane.ERROR_MESSAGE);
        } else if (sdt != null) {
            JOptionPane.showMessageDialog(this, "SĐT này đã tồn tại", "error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (checkDT()) {
                this.insertDT();
            }
        }

    }//GEN-LAST:event_btnDT_ThemActionPerformed

    private void btnDT_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDT_SuaActionPerformed
        if (checkDT()) {
            this.updateDT();
        }
    }//GEN-LAST:event_btnDT_SuaActionPerformed

    private void btnDT_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDT_XoaActionPerformed
        this.deleteDT();
    }//GEN-LAST:event_btnDT_XoaActionPerformed

    private void btnDT_MoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDT_MoiActionPerformed
        this.clearDT();
        this.setStatusDT(true);
    }//GEN-LAST:event_btnDT_MoiActionPerformed

    private void btnNL_DTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNL_DTActionPerformed
        this.indexDoiTac = 0;
        this.editDT();
    }//GEN-LAST:event_btnNL_DTActionPerformed

    private void btnL_DTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnL_DTActionPerformed
        this.indexDoiTac--;
        this.editDT();
    }//GEN-LAST:event_btnL_DTActionPerformed

    private void btnNR_DTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNR_DTActionPerformed
        this.indexDoiTac = tblDoiTac.getRowCount() - 1;
        this.editDT();
    }//GEN-LAST:event_btnNR_DTActionPerformed

    private void btnR_DTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnR_DTActionPerformed
        this.indexDoiTac++;
    }//GEN-LAST:event_btnR_DTActionPerformed

    private void lblAnhDoiTacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhDoiTacMouseClicked
        this.setAnhSize(lblAnhDoiTac);
    }//GEN-LAST:event_lblAnhDoiTacMouseClicked

    private void jTabbQLdoiTacAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTabbQLdoiTacAncestorAdded
        this.loadDT();
        this.setStatusDT(true);
    }//GEN-LAST:event_jTabbQLdoiTacAncestorAdded

    private void tblDoiTacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoiTacMouseClicked
        if (evt.getClickCount() == 1) {
            this.indexDoiTac = tblDoiTac.rowAtPoint(evt.getPoint());
            if (this.indexDoiTac >= 0) {
                this.editDT();
                jTabbQLdoiTac.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblDoiTacMouseClicked

    private void tbl_DuAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DuAnMouseClicked
        if (evt.getClickCount() == 1) {
            this.indexDuAn = tbl_DuAn.rowAtPoint(evt.getPoint());
            if (this.indexDuAn >= 0) {
                this.EditDA();
                jTabbQLDuAn.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tbl_DuAnMouseClicked

    private void tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPhamMouseClicked

        if (evt.getClickCount() == 1) {
            this.indexSP = tbl_SanPham.rowAtPoint(evt.getPoint());
            if (this.indexSP >= 0) {
                this.EditSP();
                jTabbQLsanPham.setSelectedIndex(0);
            }
        }


    }//GEN-LAST:event_tbl_SanPhamMouseClicked

    private void jTabbedPane1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTabbedPane1AncestorAdded
        this.fillComboBoxNam();
        this.fillTableDuAn();
        this.fillTableDoiTac();
        this.fillTableDoanhThu();

    }//GEN-LAST:event_jTabbedPane1AncestorAdded

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        this.fillTableDuAn();
    }//GEN-LAST:event_cboNamActionPerformed

    private void lblDA_inExMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDA_inExMouseClicked
// this.printIntoExcel();
    }//GEN-LAST:event_lblDA_inExMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPaneAbout;
    private javax.swing.JPanel JPaneGioiThieu;
    private javax.swing.JPanel JPaneHeThong;
    private javax.swing.JPanel JPaneHome;
    private javax.swing.JPanel JPaneLogout;
    private javax.swing.JPanel JPaneLoogin;
    private javax.swing.JPanel JPaneMenuLogin;
    private javax.swing.JPanel JPaneMenuLogin2;
    private javax.swing.JPanel JPanePhanTich;
    private javax.swing.JPanel JPaneQuanLi;
    private javax.swing.JPanel JPaneTaiKhoan;
    private javax.swing.JPanel JPaneThoat;
    private javax.swing.JPanel JPaneThongKe;
    private javax.swing.JPanel JPaneTime;
    private javax.swing.JLabel backg;
    private javax.swing.JLabel backg1;
    private javax.swing.JLabel background;
    private javax.swing.JLabel backk;
    private javax.swing.JLabel bot;
    private javax.swing.JLabel bot1;
    private javax.swing.JButton btnCan;
    private javax.swing.JButton btnCan1;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDAmoi;
    private javax.swing.JButton btnDAsua;
    private javax.swing.JButton btnDAthem;
    private javax.swing.JButton btnDAxoa;
    private javax.swing.JButton btnDT_Moi;
    private javax.swing.JButton btnDT_Sua;
    private javax.swing.JButton btnDT_Them;
    private javax.swing.JButton btnDT_Xoa;
    private javax.swing.JButton btnDoiTac;
    private javax.swing.JButton btnDuAn;
    private javax.swing.JButton btnEx;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHeThong;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnKH_Moi;
    private javax.swing.JButton btnKH_Sua;
    private javax.swing.JButton btnKH_Them;
    private javax.swing.JButton btnKH_Xoa;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnL_DA;
    private javax.swing.JButton btnL_DT;
    private javax.swing.JButton btnL_KH;
    private javax.swing.JButton btnL_ND;
    private javax.swing.JButton btnL_SP;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLoginOK;
    private javax.swing.JButton btnLoginOK1;
    private javax.swing.JButton btnMoiND;
    private javax.swing.JButton btnNL_DA;
    private javax.swing.JButton btnNL_DT;
    private javax.swing.JButton btnNL_KH;
    private javax.swing.JButton btnNL_ND;
    private javax.swing.JButton btnNL_SP;
    private javax.swing.JButton btnNR_DA;
    private javax.swing.JButton btnNR_DT;
    private javax.swing.JButton btnNR_KH;
    private javax.swing.JButton btnNR_ND;
    private javax.swing.JButton btnNR_SP;
    private javax.swing.JButton btnNguoiDung;
    private javax.swing.JButton btnPhanTich;
    private javax.swing.JButton btnQuanLi;
    private javax.swing.JButton btnR_DA;
    private javax.swing.JButton btnR_DT;
    private javax.swing.JButton btnR_KH;
    private javax.swing.JButton btnR_ND;
    private javax.swing.JButton btnR_SP;
    private javax.swing.JButton btnSP;
    private javax.swing.JButton btnSP_Moi;
    private javax.swing.JButton btnSP_Sua;
    private javax.swing.JButton btnSP_Them;
    private javax.swing.JButton btnSP_Xoa;
    private javax.swing.JButton btnSuaND;
    private javax.swing.JButton btnThemND;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnXoaND;
    private javax.swing.JButton btnYes;
    private javax.swing.JButton btnYes1;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JCheckBox chkGhiNho;
    private javax.swing.JCheckBox chkGhiNho1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPaneExit;
    private javax.swing.JPanel jPaneForm;
    private javax.swing.JPanel jPaneMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelQLduAn;
    private javax.swing.JPanel jPanelQLnguoiDung;
    private javax.swing.JPanel jPanelQLnguoiDung1;
    private javax.swing.JPanel jPanelQLnguoiDung2;
    private javax.swing.JPanel jPanelQLsanPham;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbQLDuAn;
    private javax.swing.JTabbedPane jTabbQLdoiTac;
    private javax.swing.JTabbedPane jTabbQLkhachHang;
    private javax.swing.JTabbedPane jTabbQLnguoiDung;
    private javax.swing.JTabbedPane jTabbQLsanPham;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAnhDoiTac;
    private javax.swing.JLabel lblAnhKH;
    private javax.swing.JLabel lblAnhND;
    private javax.swing.JLabel lblAnh_SP;
    private javax.swing.JLabel lblBackG;
    private javax.swing.JLabel lblDA_anh;
    private javax.swing.JLabel lblDA_inEx;
    private javax.swing.JLabel lblDA_inEx1;
    private javax.swing.JLabel lblEye;
    private javax.swing.JLabel lblEyyy;
    private javax.swing.JLabel lblGach;
    private javax.swing.JLabel lblGif;
    private javax.swing.JLabel lblGioiThieu;
    private javax.swing.JLabel lblHide1;
    private javax.swing.JLabel lblHide2;
    private javax.swing.JLabel lblHide3;
    private javax.swing.JLabel lblHideLogout;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblLogin1;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblNameTK;
    private javax.swing.JLabel lblNewTK;
    private javax.swing.JLabel lblQLdoitac;
    private javax.swing.JLabel lblQLduAn;
    private javax.swing.JLabel lblQLkhachHang;
    private javax.swing.JLabel lblQLnguoiDung;
    private javax.swing.JLabel lblQLsanPham;
    private javax.swing.JLabel lblShow1;
    private javax.swing.JLabel lblShow2;
    private javax.swing.JLabel lblShow3;
    private javax.swing.JLabel lblShowLogout;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblThemTK;
    private javax.swing.JLabel lblThoat;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JLabel lbl_BackG_Home;
    private javax.swing.JLabel left;
    private javax.swing.JLabel left1;
    private javax.swing.JLabel logo;
    private javax.swing.JRadioButton rdoNamKH;
    private javax.swing.JRadioButton rdoNamND;
    private javax.swing.JRadioButton rdoNuKH;
    private javax.swing.JRadioButton rdoNuND;
    private javax.swing.JLabel right;
    private javax.swing.JLabel right1;
    private javax.swing.JLabel slide;
    private javax.swing.JTable tblDoiTac;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblNguoiDung;
    private javax.swing.JTable tblThongKe_DoanhThu;
    private javax.swing.JTable tblThongKe_DoiTac;
    private javax.swing.JTable tblThongKe_DuAN;
    private javax.swing.JTable tbl_DuAn;
    private javax.swing.JTable tbl_SanPham;
    private javax.swing.JLabel top;
    private javax.swing.JLabel top1;
    private javax.swing.JTextField txtDA_chiPhi;
    private javax.swing.JTextField txtDA_diaChi;
    private javax.swing.JTextField txtDA_dienTich;
    private javax.swing.JTextField txtDA_hinhThucDauTu;
    private javax.swing.JTextField txtDA_hinhThucQL;
    private javax.swing.JTextField txtDA_iddoiTac;
    private javax.swing.JTextField txtDA_loaiHinh;
    private javax.swing.JTextField txtDA_mucTieu;
    private javax.swing.JTextField txtDA_name;
    private javax.swing.JTextField txtDA_ngayBatDau;
    private javax.swing.JTextField txtDA_ngayKetThuc;
    private javax.swing.JTextField txtDA_trangThai;
    private javax.swing.JTextField txtDT_DiaChi;
    private javax.swing.JTextField txtDT_Email;
    private javax.swing.JTextField txtDT_LinhVuc;
    private javax.swing.JTextField txtDT_SDT;
    private javax.swing.JTextField txtDT_SoVonDauTu;
    private javax.swing.JTextField txtDT_name;
    private javax.swing.JTextField txtKH_DiaChi1;
    private javax.swing.JTextField txtKH_Email;
    private javax.swing.JTextField txtKH_Name;
    private javax.swing.JTextField txtKH_SDT;
    private javax.swing.JTextField txtNDName;
    private javax.swing.JTextField txtNDchucVu;
    private javax.swing.JTextField txtNDdiachi;
    private javax.swing.JTextField txtNDemail;
    private javax.swing.JPasswordField txtNDpass;
    private javax.swing.JTextField txtNDrole;
    private javax.swing.JTextField txtNDsdt;
    private javax.swing.JTextField txtNDtenDangNhap;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JPasswordField txtPassCu;
    private javax.swing.JPasswordField txtPassMoi;
    private javax.swing.JPasswordField txtPassXN;
    private javax.swing.JTextField txtSP_ChiTiet;
    private javax.swing.JTextField txtSP_DiaChi;
    private javax.swing.JTextField txtSP_DienTich;
    private javax.swing.JTextField txtSP_GiaTien;
    private javax.swing.JTextField txtSP_IdDuAn;
    private javax.swing.JTextField txtSP_IdKhachHang;
    private javax.swing.JTextField txtSP_MoTa;
    private javax.swing.JTextField txtSP_NgayBan;
    private javax.swing.JTextField txtSP_NgayTao;
    private javax.swing.JTextField txtSP_TrangThai;
    private javax.swing.JTextField txtSP_name;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtUserName1;
    // End of variables declaration//GEN-END:variables
}
