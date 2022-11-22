/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author tcao2
 */
public class TrangChu_GUI extends javax.swing.JPanel {

    /**
     * Creates new form Trangchuform
     */
    public TrangChu_GUI() {
        initComponents();
        JLabel lblbg = new JLabel("");
        lblbg.setIcon(new ImageIcon(Login_GUI.class.getResource("/images/trangchu.jpg")));
        
        add(lblbg);
        
		
        
        
    }

    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255,255,255));

        jPanel1.setBackground(new java.awt.Color(255,255,255));
        jPanel1.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHẦN MỀM QUẢN LÝ LƯƠNG NHÂN VIÊN THEO SẢN PHẨM");
        
        
       
    }// </editor-fold>//GEN-END:initComponents
    public void BackgroundPanel() {
    	
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
