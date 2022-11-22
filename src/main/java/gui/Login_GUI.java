/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import connect.Connect;
import dao.Dangnhap_DAO;
import dao.NhanVien_DAO;
import entity.NhanVien;





public class Login_GUI extends javax.swing.JFrame {

    private Object Object;
    public Login_GUI() {
        initComponents();
        setLocationRelativeTo(null);
//        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    // Giao diện 
    private void initComponents() {
    	ImageIcon img = new ImageIcon(Login_GUI.class.getResource("/Photo for Design Form/ao.png"));
    	setIconImage(img.getImage());

    	jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTendn = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        btnDangnhap = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/user-icon1.png"))); // NOI18N
        jLabel1.setText("Đăng nhập");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Tên  đăng nhập:");

        txtTendn.setText("QL000001");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Mật khẩu:");

        txtPass.setText("123456");

        btnDangnhap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDangnhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Key1.png"))); // NOI18N
        btnDangnhap.setText("Đăng nhập");
        btnDangnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangnhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPass)
                            .addComponent(txtTendn, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 243, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(218, 218, 218))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(260, 260, 260))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTendn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(btnDangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        pack();
        txtTendn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnDangnhap.setFocusable(false);
        btnDangnhap.setBorderPainted(false);
        btnDangnhap.setBackground(new Color(51,153,255));
        JLabel lblbg = new JLabel("");
		lblbg.setBounds(0, 0, 700, 380);
		lblbg.setIcon(new ImageIcon(Login_GUI.class.getResource("/images/dangnhap.jpg")));
		getContentPane().add(lblbg);
		
    }

    public static void main(String[] args) {
		new Login_GUI().setVisible(true);
	}
    // Xử lý button Đăng nhập
    private void btnDangnhapActionPerformed(java.awt.event.ActionEvent evt) {
        
    Object = evt.getSource();  
    if(Object.equals(btnDangnhap))
    {
    	Dangnhap_DAO dao = new Dangnhap_DAO();
    	NhanVien_DAO nvdao = new NhanVien_DAO();
    	boolean dn , qly, trangthai;
    	dn=dao.ktraDangnhap(txtTendn.getText(), txtPass.getText());
    	qly = dao.KtraQuanLy(txtTendn.getText());
    	trangthai = dao.KtraTrangthai(txtTendn.getText());
    	if(!dn||!trangthai)
    	{
    		JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng");
    	}
    	else if(dn&&qly)
    	{
    		NhanVien nv = nvdao.getNVtheoma(txtTendn.getText());
    		new QuanLy_GUI(nv).setVisible(true);
    		setVisible(false);
    	}
    	else if(dn&&!qly) {
    		NhanVien nv = nvdao.getNVtheoma(txtTendn.getText());
    		new TrangChuNhanVien_GUI(nv).setVisible(true);
    		setVisible(false);
    	}
    	
    }
        
        
        
        
    }

    private javax.swing.JButton btnDangnhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtTendn;
    
}
