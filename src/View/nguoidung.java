/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.NguoiDungDao;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NguoiDung;

/**
 *
 * @author exfic
 */
public class nguoidung extends javax.swing.JFrame {

    int indexNgDung = 0;
    private int IdNG;
    ButtonGroup groupND;
    JFileChooser fileChooser = new JFileChooser();
    NguoiDungDao daoND = new NguoiDungDao();

    public nguoidung() {
        initComponents();
        groupND = new ButtonGroup();
        groupND.add(rdoNamND);
        groupND.add(rdoNuND);
        setLocationRelativeTo(null);
    }

    void loadNgDung() {
      DefaultTableModel model = (DefaultTableModel) tblNguoiDung.getModel();
        model.setRowCount(0);
        try {
            List<NguoiDung> list = daoND.select();
            for (NguoiDung kh : list) {
                Object[] row = {
                    kh.getIdNguoiDung(),
                    kh.getNameNguoiDung(),
                    kh.getUserName(),
                    kh.getPassWord(),
                    kh.getAddress(),
                    kh.getNumberPhone(),
                    kh.getEmail(),
                    kh.getGender(),
                    kh.getChucVu(),
                    kh.getHinhAnh(),
                    kh.getRole(),
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu");
        }
        model.setRowCount(8);
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
         lblAnhND.setToolTipText("");

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
 public static ImageIcon readLogo(String fileName) {
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
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
         lblAnhND.setIcon(readLogo(modelND.getHinhAnh()));
         
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
//        btnL_ND.setEnabled(!insertTable && first);
//        btnNR_ND.setEnabled(!insertTable && first);
//        btnR_ND.setEnabled(!insertTable && last);
//        btnNL_ND.setEnabled(!insertTable && last);

    }

    public void setAnhSize(JLabel lbl) {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filename = file.getName();
            ImageIcon icon = new ImageIcon(filename);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon newImc = new ImageIcon(newImg);
            lbl.setIcon(newImc);
            // lblAnhND.setIcon(readLogo(file.getName()));
            lblAnhND.setToolTipText(file.getName());  ///  /////
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

        jTabbQLnguoiDung = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNDName = new javax.swing.JTextField();
        txtNDtenDangNhap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNDpass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNDdiachi = new javax.swing.JTextField();
        txtNDsdt = new javax.swing.JTextField();
        txtNDemail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNDrole = new javax.swing.JTextField();
        txtNDchucVu = new javax.swing.JTextField();
        btnThemND = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        btnSuaND = new javax.swing.JButton();
        btnXoaND = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblAnhND = new javax.swing.JLabel();
        rdoNamND = new javax.swing.JRadioButton();
        rdoNuND = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNguoiDung = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbQLnguoiDung.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTabbQLnguoiDungAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel1.setText("ten");

        jLabel2.setText("ten dang nhap");

        jLabel3.setText("pass");

        jLabel4.setText("emai");

        jLabel5.setText("sdt");

        jLabel6.setText("dia chi");

        jLabel7.setText("chuc vu");

        jLabel8.setText("role");

        btnThemND.setText("them");
        btnThemND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNDActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnSuaND.setText("sua");
        btnSuaND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNDActionPerformed(evt);
            }
        });

        btnXoaND.setText("xoa");
        btnXoaND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNDActionPerformed(evt);
            }
        });

        jButton4.setText("moi");

        lblAnhND.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnhND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhNDMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(lblAnhND, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblAnhND, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        rdoNamND.setText("nam");

        rdoNuND.setText("nu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(655, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(rdoNamND)
                .addGap(51, 51, 51)
                .addComponent(rdoNuND)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(167, 167, 167)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(77, 77, 77)
                            .addComponent(txtNDName))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNDtenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                        .addComponent(txtNDsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNDpass)
                                        .addComponent(txtNDdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNDrole, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNDemail, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNDchucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(btnThemND)
                                            .addGap(56, 56, 56)
                                            .addComponent(btnSuaND)
                                            .addGap(32, 32, 32)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton4)
                                                .addComponent(btnXoaND)))))))
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(jButton1)))
                    .addContainerGap(547, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNamND)
                    .addComponent(rdoNuND))
                .addContainerGap(135, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtNDName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(txtNDtenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(txtNDpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(txtNDdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtNDsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtNDemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtNDchucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtNDrole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemND)
                        .addComponent(btnSuaND)
                        .addComponent(btnXoaND))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1)
                        .addComponent(jButton4))
                    .addContainerGap()))
        );

        jTabbQLnguoiDung.addTab("tab1", jPanel1);

        tblNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên người dùng", "Tên đăng nhập", "Mật khẩu", "Địa chỉ", "Số điện thoại", "Email", "Giới tính", "Chức vụ", "Hình ảnh", "Role"
            }
        ));
        jScrollPane2.setViewportView(tblNguoiDung);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1081, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
        );

        jTabbQLnguoiDung.addTab("tab2", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jTabbQLnguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 1086, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbQLnguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNDActionPerformed
        this.insertNgDung();
    }//GEN-LAST:event_btnThemNDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.insertNgDung();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbQLnguoiDungAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTabbQLnguoiDungAncestorAdded
        this.loadNgDung();
        this.setStatusND(true);
    }//GEN-LAST:event_jTabbQLnguoiDungAncestorAdded

    private void lblAnhNDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhNDMouseClicked

        setAnhSize(lblAnhND);
    }//GEN-LAST:event_lblAnhNDMouseClicked

    private void btnSuaNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNDActionPerformed
        this.updateND();
    }//GEN-LAST:event_btnSuaNDActionPerformed

    private void btnXoaNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNDActionPerformed
        this.deleteND();
    }//GEN-LAST:event_btnXoaNDActionPerformed

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
            java.util.logging.Logger.getLogger(nguoidung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nguoidung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nguoidung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nguoidung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nguoidung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaND;
    private javax.swing.JToggleButton btnThemND;
    private javax.swing.JButton btnXoaND;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbQLnguoiDung;
    private javax.swing.JLabel lblAnhND;
    private javax.swing.JRadioButton rdoNamND;
    private javax.swing.JRadioButton rdoNuND;
    private javax.swing.JTable tblNguoiDung;
    private javax.swing.JTextField txtNDName;
    private javax.swing.JTextField txtNDchucVu;
    private javax.swing.JTextField txtNDdiachi;
    private javax.swing.JTextField txtNDemail;
    private javax.swing.JPasswordField txtNDpass;
    private javax.swing.JTextField txtNDrole;
    private javax.swing.JTextField txtNDsdt;
    private javax.swing.JTextField txtNDtenDangNhap;
    // End of variables declaration//GEN-END:variables
}
