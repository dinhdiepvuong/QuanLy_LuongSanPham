/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package gui;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;


import connect.Connect;
import dao.ChamCongNVHC_DAO;
import dao.CongDoan_DAO;
import dao.DuLieuCongNhan_DAO;
import dao.NhanVien_DAO;
import dao.ThuNhapKhac_DAO;
import entity.BangChamCong;
import entity.ChamCongNVHC;
import entity.CongDoan;
import entity.CongNhan;
import entity.NhanVien;
import entity.NhanVien_CongDoan;
import entity.SanPham;
import entity.ThuNhapKhac;
import entity.*;
import entity.ListSearchNhanVien;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.naming.directory.SearchResult;

import javax.swing.DefaultListModel;
import javax.swing.JList;


/**
 *
 * @author PC PHAN TRONG
 */
public class DuLieuTinhLuong_GUI extends javax.swing.JPanel {
    
	private ChamCongNVHC_DAO dlieu = new ChamCongNVHC_DAO();
	public static DefaultTableModel dataNhanVien = new DefaultTableModel(
			new String[] { "Mã nhân viên", "Tên nhân viên", "Hệ số lương", "Nghĩ không phép", "Công ngày lễ",
					"Công ngày thường", "Số Công Chuẩn", "Tháng", "Năm", "Thu Nhập Khác" },
			0);

	public static DefaultTableModel dataCongNhan = new DefaultTableModel(new String[] { "Mã nhân viên", "Tên nhân viên", "Công đoạn",
			"Sản phẩm", "Sản phẩm thực tế", "Tháng", "Năm", "Thu nhập khác" }, 0);
//	private static final Set<String> generatedNumbers = new HashSet<String>();

	private NhanVien_DAO nvDao = new NhanVien_DAO();
	private ThuNhapKhac_DAO tnkDao = new ThuNhapKhac_DAO();
	private DuLieuCongNhan_DAO cnDao = new DuLieuCongNhan_DAO();
	private CongDoan_DAO cdDao = new CongDoan_DAO();
	private Connection con = new Connect().getConnect();
	private static final Set<String> generatedNumbers = new HashSet<String>();
	private Calendar instance = Calendar.getInstance();
        private DefaultListModel modelCongNhan = new DefaultListModel();
        private DefaultListModel modelNhanVien = new DefaultListModel();

    public DuLieuTinhLuong_GUI() {
        initComponents();
        jpnCongNhan.setVisible(true);
        jpnNhanVien.setVisible(false);
        menuCongNhan.add(pnSearchCongNhan);
        menuNhanVien.add(pnSearchNhanVien);

        
        //CÔNG NHÂN
      		cboCongDoan.removeAllItems();
      		cboTenSP.removeAllItems();
      		List<CongNhan> dss = cnDao.getallCongNhan();
      		for (int i = 0; i < dss.size(); i++) {
      			dataCongNhan.addRow(new Object[] { dss.get(i).getMaNV(), dss.get(i).getTenNV(), dss.get(i).getTenCongDoan(),
      					dss.get(i).getTenSP(), dss.get(i).getSoLuong(), dss.get(i).getThang(), dss.get(i).getNam(),
      					tach(dss.get(i).getThuNhapKhac()) });
      		}
      		jtableCongNhan.setModel(dataCongNhan);
      		jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
      		jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
      		jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
              
      		jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
      		jtableCongNhan.setFont(new Font("Arial", Font.PLAIN, 13));
      		jtableCongNhan.setRowHeight(jtableCongNhan.getRowHeight()+20);
      		jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      		jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      		jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
      		jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
      		jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
      		jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
      		jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
      		jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
      		jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
      		jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
      		DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
      		rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
      		DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
      		centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
      		jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
      		jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
      		jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
      		jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);

      		try {
      			String sql = "Select tenSP From SanPham";
      			PreparedStatement p = con.prepareStatement(sql);
      			ResultSet rs = p.executeQuery();
      			cboTenSP.removeAllItems();
      			while (rs.next()) {
      				cboTenSP.addItem(rs.getString("tenSP"));
      			}
      			rs.close();
      			p.close();
      			con.close();
      		} catch (Exception e) {
      			e.printStackTrace();
      		}

      		// NHÂN VIÊN
      		List<BangChamCong> ds = dlieu.getBangChamCong();
      		for (int i = 0; i < ds.size(); i++) {
      			dataNhanVien.addRow(new Object[] { ds.get(i).getMaNV(), ds.get(i).getTenNV(),
      					tach(ds.get(i).getHeSoluong()), ds.get(i).getNghiKhongPhep(), ds.get(i).getSoCongNgayLe(),
      					ds.get(i).getSoCongngaythuong(), ds.get(i).getSoCongchuan(), ds.get(i).getThang(),
      					ds.get(i).getNam(), tach(ds.get(i).getThuNhapKhac()) });
      		}
      		jtableNhanVien.setModel(dataNhanVien);
      		jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
      		jtableNhanVien.setFont(new Font("Arial", Font.PLAIN, 13));
      		jtableNhanVien.setRowHeight(jtableNhanVien.getRowHeight()+20);
      		JTableHeader h = jtableNhanVien.getTableHeader();
      		h.setFont(new Font("Arial", Font.BOLD, 13));
      		h.setForeground(new Color(255, 255, 255));
      		h.setBackground(new Color(146, 200, 240));
      		jtableNhanVien.getTableHeader().setBackground(new Color(146, 200, 240));
      		jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
      		jtableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      		jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
      		jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
      		jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
      		jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
      		jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
      		jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
      		jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
      		jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
      		jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
      		jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
      		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
      		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
      		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
      		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
      		jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
      		jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);

      		cboTenSP.addActionListener(new java.awt.event.ActionListener() {
      			public void actionPerformed(java.awt.event.ActionEvent evt) {
      				cboTenSPActionPerformed(evt);
      			}
      		});
      		btnTimNV.addActionListener(new java.awt.event.ActionListener() {
      			public void actionPerformed(java.awt.event.ActionEvent evt) {
      				btnTimNVActionPerformed(evt);
      			}
      		});
      		btnTimKiemCN.addActionListener(new java.awt.event.ActionListener() {
      			public void actionPerformed(java.awt.event.ActionEvent evt) {
      				btnTimKiemCNActionPerformed(evt);
      			}
      		});
      		jMonthTimKiemNV2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
      			public void propertyChange(java.beans.PropertyChangeEvent evt) {
      				monthTuNhanVienPropertyChange(evt);
      			}
      		});
      		jMonthTimKiemNV1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
      			public void propertyChange(java.beans.PropertyChangeEvent evt) {
      				monthDenNhanVienPropertyChange(evt);
      			}
      		});

      		jMonthTimKiemCN2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
      			public void propertyChange(java.beans.PropertyChangeEvent evt) {
      				monthTuCongNhanPropertyChange(evt);
      			}
      		});
      		jMonthTimKiemCN1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
      			public void propertyChange(java.beans.PropertyChangeEvent evt) {
      				monthDenCongNhanPropertyChange(evt);
      			}
      		});
      		jPanel16.setBackground(new java.awt.Color(255,255,255));
      		
      		JTableHeader h1 = jtableCongNhan.getTableHeader();
      		h1.setFont(new Font("Arial", Font.BOLD, 13));
      		h1.setForeground(new Color(255, 255, 255));
      		h1.setBackground(new Color(146, 200, 240));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSearchCongNhan = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listCongNhan = new javax.swing.JList<>();
        menuCongNhan = new javax.swing.JPopupMenu();
        pnSearchNhanVien = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listNhanVien = new javax.swing.JList<>();
        menuNhanVien = new javax.swing.JPopupMenu();
        jpnImportCongNhan = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        txtPathCN = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        btnBrowseCN = new javax.swing.JButton();
        btnCloseCN = new javax.swing.JButton();
        btnUploadCN = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnCongNhan = new java.awt.Button();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnNhanVien = new java.awt.Button();
        jpn = new javax.swing.JPanel();
        jpnCongNhan = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        panelttNV3 = new javax.swing.JPanel();
        lblmaNV4 = new javax.swing.JLabel();
        txtMaCongNhan = new javax.swing.JTextField();
        lblmaNV5 = new javax.swing.JLabel();
        lblmaNV9 = new javax.swing.JLabel();
        lblmaNV10 = new javax.swing.JLabel();
        txtSanPhamThucTe = new javax.swing.JTextField();
        cboTenSP = new javax.swing.JComboBox<>();
        lblmaNV15 = new javax.swing.JLabel();
        jMonthCongNhan = new com.toedter.calendar.JMonthChooser();
        jYearCongNhan = new com.toedter.calendar.JYearChooser();
        cboCongDoan = new javax.swing.JComboBox<>();
        panelttNV4 = new javax.swing.JPanel();
        lblmaNV11 = new javax.swing.JLabel();
        txtLuongLamThemCongNhan = new javax.swing.JTextField();
        lblmaNV12 = new javax.swing.JLabel();
        txtPhuCapCongNhan = new javax.swing.JTextField();
        lblmaNV13 = new javax.swing.JLabel();
        txtThuongCongNhan = new javax.swing.JTextField();
        panelttNV5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtableCongNhan = new javax.swing.JTable();
        panel6 = new java.awt.Panel();
        jMonthTimKiemCN1 = new com.toedter.calendar.JMonthChooser();
        jLabel14 = new javax.swing.JLabel();
        jYearTimKiemCN2 = new com.toedter.calendar.JYearChooser();
        jLabel15 = new javax.swing.JLabel();
        jMonthTimKiemCN2 = new com.toedter.calendar.JMonthChooser();
        jYearCN2 = new com.toedter.calendar.JYearChooser();
        textField5 = new java.awt.TextField();
        btnTimKiemCN = new javax.swing.JButton();
        txtTimKiemCN = new gui.MyTextField();
        btnLamMoiCongNhan = new javax.swing.JButton();
        btnLamXoaRongCN = new javax.swing.JButton();
        btnThemThemCongNhan = new javax.swing.JButton();
        btnCapNhapCongNhan = new javax.swing.JButton();
        jpnNhanVien = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        panelttNV = new javax.swing.JPanel();
        lblmaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        lblmaNV1 = new javax.swing.JLabel();
        txtHeSoLuong = new javax.swing.JTextField();
        lblmaNV2 = new javax.swing.JLabel();
        lblmaNV3 = new javax.swing.JLabel();
        txtCongNgayLe = new javax.swing.JTextField();
        txtNghiKhongPhep = new javax.swing.JTextField();
        txtCongNgayThuong = new javax.swing.JTextField();
        lblmaNV14 = new javax.swing.JLabel();
        lblThoiGian = new javax.swing.JLabel();
        jMonthNhanVien = new com.toedter.calendar.JMonthChooser();
        jYearNhanVien = new com.toedter.calendar.JYearChooser();
        panelttNV1 = new javax.swing.JPanel();
        lblmaNV6 = new javax.swing.JLabel();
        txtLuongLamThemNV = new javax.swing.JTextField();
        lblmaNV7 = new javax.swing.JLabel();
        txtPhuCapNV = new javax.swing.JTextField();
        lblmaNV8 = new javax.swing.JLabel();
        txtThuongNV = new javax.swing.JTextField();
        panelttNV2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableNhanVien = new javax.swing.JTable();
        panel2 = new java.awt.Panel();
        jMonthTimKiemNV1 = new com.toedter.calendar.JMonthChooser();
        jLabel3 = new javax.swing.JLabel();
        jYearTimKiemNV1 = new com.toedter.calendar.JYearChooser();
        jLabel4 = new javax.swing.JLabel();
        jMonthTimKiemNV2 = new com.toedter.calendar.JMonthChooser();
        jYearTimKiemNV2 = new com.toedter.calendar.JYearChooser();
        textField1 = new java.awt.TextField();
        btnTimNV = new javax.swing.JButton();
        txtTimKiemNV = new gui.MyTextField();
        btnXoaRongNhanVien = new javax.swing.JButton();
        btnLamMoiNhanVien = new javax.swing.JButton();
        btnThemNhanVien = new javax.swing.JButton();
        btnCapNhapNhanVien = new javax.swing.JButton();

        pnSearchCongNhan.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        listCongNhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        listCongNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listCongNhanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                listCongNhanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listCongNhanMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(listCongNhan);

        javax.swing.GroupLayout pnSearchCongNhanLayout = new javax.swing.GroupLayout(pnSearchCongNhan);
        pnSearchCongNhan.setLayout(pnSearchCongNhanLayout);
        pnSearchCongNhanLayout.setHorizontalGroup(
            pnSearchCongNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );
        pnSearchCongNhanLayout.setVerticalGroup(
            pnSearchCongNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        menuCongNhan.setBackground(new java.awt.Color(255, 255, 255));
        menuCongNhan.setFocusable(false);

        pnSearchNhanVien.setBackground(new java.awt.Color(255, 255, 255));

        listNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        listNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listNhanVienMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(listNhanVien);

        javax.swing.GroupLayout pnSearchNhanVienLayout = new javax.swing.GroupLayout(pnSearchNhanVien);
        pnSearchNhanVien.setLayout(pnSearchNhanVienLayout);
        pnSearchNhanVienLayout.setHorizontalGroup(
            pnSearchNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
        );
        pnSearchNhanVienLayout.setVerticalGroup(
            pnSearchNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        menuNhanVien.setFocusable(false);

        jpnImportCongNhan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        txtPathCN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("File Path");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(txtPathCN)
                        .addGap(17, 17, 17))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPathCN, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnBrowseCN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBrowseCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/icons8-browse-folder-64_1.png"))); // NOI18N
        btnBrowseCN.setText("Browse");
        btnBrowseCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseCNActionPerformed(evt);
            }
        });

        btnCloseCN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCloseCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Cancel.png"))); // NOI18N
        btnCloseCN.setText("Close");
        btnCloseCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseCNActionPerformed(evt);
            }
        });

        btnUploadCN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUploadCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Upload.png"))); // NOI18N
        btnUploadCN.setText("Upload");
        btnUploadCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadCNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBrowseCN, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUploadCN, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCloseCN, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowseCN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCloseCN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUploadCN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnImportCongNhanLayout = new javax.swing.GroupLayout(jpnImportCongNhan);
        jpnImportCongNhan.setLayout(jpnImportCongNhanLayout);
        jpnImportCongNhanLayout.setHorizontalGroup(
            jpnImportCongNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnImportCongNhanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jpnImportCongNhanLayout.setVerticalGroup(
            jpnImportCongNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnImportCongNhanLayout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(23, 35, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(41, 57, 80));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        btnCongNhan.setBackground(new java.awt.Color(40, 57, 88));
        btnCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCongNhan.setForeground(new java.awt.Color(255, 255, 255));
        btnCongNhan.setLabel("Công Nhân");
        btnCongNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCongNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addComponent(btnCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 95, 182, -1));

        jPanel9.setBackground(new java.awt.Color(41, 57, 80));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnNhanVien.setActionCommand("Nhân Viên");
        btnNhanVien.setBackground(new java.awt.Color(40, 57, 88));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnNhanVien.setLabel("Nhân Viên");
        btnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhanVienMouseClicked(evt);
            }
        });
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 157, -1, -1));

        jpnCongNhan.setPreferredSize(new java.awt.Dimension(1351, 629));
        jpnCongNhan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(1341, 6));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Công Nhân");
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(493, 493, 493)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(504, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(120, 162, 252));

        panelttNV3.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));

        lblmaNV4.setText("Mã nhân viên");

        lblmaNV5.setText("Tên sản phẩm");

        lblmaNV9.setText("Công đoạn");

        lblmaNV10.setText("Sản phẩm thực tế");

        cboTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblmaNV15.setText("Thời Gian");

        cboCongDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelttNV3Layout = new javax.swing.GroupLayout(panelttNV3);
        panelttNV3.setLayout(panelttNV3Layout);
        panelttNV3Layout.setHorizontalGroup(
            panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelttNV3Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(lblmaNV4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtMaCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelttNV3Layout.createSequentialGroup()
                            .addComponent(lblmaNV9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cboCongDoan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panelttNV3Layout.createSequentialGroup()
                            .addComponent(lblmaNV5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelttNV3Layout.createSequentialGroup()
                        .addComponent(lblmaNV10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSanPhamThucTe, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNV3Layout.createSequentialGroup()
                        .addComponent(lblmaNV15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jMonthCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYearCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        panelttNV3Layout.setVerticalGroup(
            panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCongDoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSanPhamThucTe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jMonthCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jYearCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelttNV3Layout.createSequentialGroup()
                        .addComponent(lblmaNV15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );

        panelttNV4.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thu nhập khác"));
        panelttNV4.setPreferredSize(new java.awt.Dimension(345, 191));

        lblmaNV11.setText("Lương làm thêm");

        lblmaNV12.setText("Phụ cấp");

        lblmaNV13.setText("Thưởng");

        javax.swing.GroupLayout panelttNV4Layout = new javax.swing.GroupLayout(panelttNV4);
        panelttNV4.setLayout(panelttNV4Layout);
        panelttNV4Layout.setHorizontalGroup(
            panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelttNV4Layout.createSequentialGroup()
                        .addComponent(lblmaNV11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLuongLamThemCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNV4Layout.createSequentialGroup()
                        .addComponent(lblmaNV12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPhuCapCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNV4Layout.createSequentialGroup()
                        .addComponent(lblmaNV13, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtThuongCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        panelttNV4Layout.setVerticalGroup(
            panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLuongLamThemCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhuCapCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThuongCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelttNV5.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jtableCongNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Công đoạn", "Sản phẩm", "Sản phẩm thực tế", "Thu nhập khác"
            }
        ));
        jtableCongNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableCongNhanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtableCongNhan);

        panel6.setPreferredSize(new java.awt.Dimension(174, 58));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Đến");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Từ");

        textField5.setText("textField1");

        btnTimKiemCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Search.png"))); // NOI18N
        btnTimKiemCN.setText("Tìm ");
        btnTimKiemCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemCNActionPerformed(evt);
            }
        });

        txtTimKiemCN.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtTimKiemCN.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/search_NEW.png"))); // NOI18N
        txtTimKiemCN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemCNMouseClicked(evt);
            }
        });
        txtTimKiemCN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemCNKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMonthTimKiemCN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearTimKiemCN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMonthTimKiemCN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearCN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemCN, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiemCN)
                .addContainerGap())
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTimKiemCN, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTimKiemCN, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jYearCN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jYearTimKiemCN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMonthTimKiemCN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMonthTimKiemCN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout panelttNV5Layout = new javax.swing.GroupLayout(panelttNV5);
        panelttNV5.setLayout(panelttNV5Layout);
        panelttNV5Layout.setHorizontalGroup(
            panelttNV5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelttNV5Layout.setVerticalGroup(
            panelttNV5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelttNV5Layout.createSequentialGroup()
                .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3))
        );

        btnLamMoiCongNhan.setBackground(new java.awt.Color(228, 237, 225));
        btnLamMoiCongNhan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiCongNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/import.png"))); // NOI18N
        btnLamMoiCongNhan.setText("Import");
        btnLamMoiCongNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiCongNhanActionPerformed(evt);
            }
        });

        btnLamXoaRongCN.setBackground(new java.awt.Color(228, 237, 225));
        btnLamXoaRongCN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamXoaRongCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Black pin.png"))); // NOI18N
        btnLamXoaRongCN.setText("Xóa rỗng");
        btnLamXoaRongCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamXoaRongCNActionPerformed(evt);
            }
        });

        btnThemThemCongNhan.setBackground(new java.awt.Color(228, 237, 225));
        btnThemThemCongNhan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemThemCongNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Create.png"))); // NOI18N
        btnThemThemCongNhan.setText("Thêm");
        btnThemThemCongNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThemCongNhanActionPerformed(evt);
            }
        });

        btnCapNhapCongNhan.setBackground(new java.awt.Color(228, 237, 225));
        btnCapNhapCongNhan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapNhapCongNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Update.png"))); // NOI18N
        btnCapNhapCongNhan.setText("Cập nhập");
        btnCapNhapCongNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhapCongNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelttNV4, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(panelttNV3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamXoaRongCN, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemThemCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamMoiCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhapCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)))
                .addComponent(panelttNV5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(panelttNV3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelttNV4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnLamXoaRongCN, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemThemCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnCapNhapCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
            .addComponent(panelttNV5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnCongNhan.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 630));

        jpnNhanVien.setPreferredSize(new java.awt.Dimension(1250, 629));
        jpnNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setMinimumSize(new java.awt.Dimension(100, 70));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(846, 60));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nhân Viên");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(493, 493, 493)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(504, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 1340, -1));

        jPanel4.setBackground(new java.awt.Color(120, 162, 252));

        panelttNV.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));
        panelttNV.setPreferredSize(new java.awt.Dimension(345, 268));

        lblmaNV.setText("Mã nhân viên");

        lblmaNV1.setText("Hệ số lương");

        lblmaNV2.setText("Nghĩ không phép");

        lblmaNV3.setText("Công ngày lễ");

        lblmaNV14.setText("Công ngày thường");

        lblThoiGian.setText("Thời gian");

        javax.swing.GroupLayout panelttNVLayout = new javax.swing.GroupLayout(panelttNV);
        panelttNV.setLayout(panelttNVLayout);
        panelttNVLayout.setHorizontalGroup(
            panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelttNVLayout.createSequentialGroup()
                            .addComponent(lblmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelttNVLayout.createSequentialGroup()
                            .addComponent(lblmaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelttNVLayout.createSequentialGroup()
                            .addComponent(lblmaNV2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtNghiKhongPhep, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelttNVLayout.createSequentialGroup()
                        .addComponent(lblmaNV3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCongNgayLe, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNVLayout.createSequentialGroup()
                        .addComponent(lblmaNV14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCongNgayThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNVLayout.createSequentialGroup()
                        .addComponent(lblThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jMonthNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jYearNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        panelttNVLayout.setVerticalGroup(
            panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNVLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNghiKhongPhep, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCongNgayLe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCongNgayThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jMonthNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jYearNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        panelttNV1.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thu nhập khác"));

        lblmaNV6.setText("Lương làm thêm");

        lblmaNV7.setText("Phụ cấp");

        lblmaNV8.setText("Thưởng");

        javax.swing.GroupLayout panelttNV1Layout = new javax.swing.GroupLayout(panelttNV1);
        panelttNV1.setLayout(panelttNV1Layout);
        panelttNV1Layout.setHorizontalGroup(
            panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelttNV1Layout.createSequentialGroup()
                        .addComponent(lblmaNV6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLuongLamThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNV1Layout.createSequentialGroup()
                        .addComponent(lblmaNV7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPhuCapNV, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNV1Layout.createSequentialGroup()
                        .addComponent(lblmaNV8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtThuongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelttNV1Layout.setVerticalGroup(
            panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLuongLamThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhuCapNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThuongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelttNV2.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin "));

        jtableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Hệ số lương", "Nghĩ không phép", "Công ngày lễ", "Công ngày thường", "Tháng", "Năm", "Số công chuẩn", "Thu nhập khác"
            }
        ));
        jtableNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtableNhanVien);

        panel2.setPreferredSize(new java.awt.Dimension(174, 58));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Đến");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Từ");

        textField1.setText("textField1");

        btnTimNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Search.png"))); // NOI18N
        btnTimNV.setText("Tìm ");
        btnTimNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimNVActionPerformed(evt);
            }
        });

        txtTimKiemNV.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/search_NEW.png"))); // NOI18N
        txtTimKiemNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemNVMouseClicked(evt);
            }
        });
        txtTimKiemNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemNVKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMonthTimKiemNV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearTimKiemNV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMonthTimKiemNV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearTimKiemNV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addComponent(txtTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnTimNV)
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jYearTimKiemNV2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jYearTimKiemNV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMonthTimKiemNV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMonthTimKiemNV2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout panelttNV2Layout = new javax.swing.GroupLayout(panelttNV2);
        panelttNV2.setLayout(panelttNV2Layout);
        panelttNV2Layout.setHorizontalGroup(
            panelttNV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelttNV2Layout.setVerticalGroup(
            panelttNV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelttNV2Layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
        );

        btnXoaRongNhanVien.setBackground(new java.awt.Color(228, 237, 225));
        btnXoaRongNhanVien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaRongNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Black pin.png"))); // NOI18N
        btnXoaRongNhanVien.setText("Xóa rỗng");
        btnXoaRongNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaRongNhanVienActionPerformed(evt);
            }
        });

        btnLamMoiNhanVien.setBackground(new java.awt.Color(228, 237, 225));
        btnLamMoiNhanVien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/import.png"))); // NOI18N
        btnLamMoiNhanVien.setText("Import");
        btnLamMoiNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNhanVienActionPerformed(evt);
            }
        });

        btnThemNhanVien.setBackground(new java.awt.Color(228, 237, 225));
        btnThemNhanVien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Create.png"))); // NOI18N
        btnThemNhanVien.setText("Thêm");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        btnCapNhapNhanVien.setBackground(new java.awt.Color(228, 237, 225));
        btnCapNhapNhanVien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapNhapNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Update.png"))); // NOI18N
        btnCapNhapNhanVien.setText("Cập nhập");
        btnCapNhapNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhapNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelttNV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelttNV, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoaRongNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamMoiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhapNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)))
                .addComponent(panelttNV2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(panelttNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelttNV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaRongNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnLamMoiNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnCapNhapNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(panelttNV2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jPanel8.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1340, 560));

        jpnNhanVien.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 630));

        javax.swing.GroupLayout jpnLayout = new javax.swing.GroupLayout(jpn);
        jpn.setLayout(jpnLayout);
        jpnLayout.setHorizontalGroup(
            jpnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 1392, Short.MAX_VALUE)
            .addGroup(jpnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnLayout.createSequentialGroup()
                    .addComponent(jpnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 1382, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jpnLayout.setVerticalGroup(
            jpnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCongNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCongNhanActionPerformed
                 jpnNhanVien.setVisible(false);
        jpnCongNhan.setVisible(true);
         btnCongNhan.setBackground(new java.awt.Color(51,153,255));
		btnNhanVien.setBackground(new java.awt.Color(40,57,88));
    }//GEN-LAST:event_btnCongNhanActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        jpnNhanVien.setVisible(false);
    	jpnCongNhan.setVisible(true);
         jpnNhanVien.setVisible(true);
         jpnCongNhan.setVisible(false);
           btnCongNhan.setBackground(new java.awt.Color(40,57,88));
 		btnNhanVien.setBackground(new java.awt.Color(51,153,255));
    }//GEN-LAST:event_btnNhanVienActionPerformed
    public static String getRandomId(final int maxLength, final int maxTry) {
	    final Random random = new Random(System.nanoTime());
	    final int max = (int) Math.pow(10, maxLength);
	    final int maxMin = (int) Math.pow(10, maxLength-1);
	    int i = 0;
	    boolean unique = false;
	    int randomId = -1;
	    while (i < maxTry) {
	        randomId = random.nextInt(max - maxMin - 1) + maxMin;
	 
	        synchronized (generatedNumbers) {
	            if (generatedNumbers.contains(randomId) == false) {
	                unique = true;
	                break;
	            }
	        }
	        i++;
	    }
	    if (unique == false) {
	        throw new RuntimeException("Cannot generate unique id!");
	    }
	 
	    synchronized (generatedNumbers) {
	        generatedNumbers.add(String.valueOf(randomId));
	    }
	    String a = String.valueOf(randomId);
	    String code = "CC";
	    return code +a;
	}
//  MOUSECLICK NHÂN VIÊN
    private void jtableNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableNhanVienMouseClicked
        List<ChamCongNVHC> list = new ArrayList<ChamCongNVHC>();
		int row = jtableNhanVien.getSelectedRow();
		txtMaNV.setText(jtableNhanVien.getValueAt(row, 0).toString());
//		double heSoluong = (double) (jtableNhanVien.getValueAt(row,2));
//		double chuyenHSL = Double.parseDouble(heSoluong);
		txtHeSoLuong.setText(jtableNhanVien.getValueAt(row, 2).toString());
		txtNghiKhongPhep.setText(jtableNhanVien.getValueAt(row, 3).toString());
		txtCongNgayLe.setText(jtableNhanVien.getValueAt(row, 4).toString());
		txtCongNgayThuong.setText(jtableNhanVien.getValueAt(row, 5).toString());
		int x = Integer.parseInt(jtableNhanVien.getValueAt(row, 7).toString());
		jMonthNhanVien.setMonth(x - 1);
		int y = Integer.parseInt(jtableNhanVien.getValueAt(row, 8).toString());
		jYearNhanVien.setYear(y);
		ThuNhapKhac_DAO tnkDao = new ThuNhapKhac_DAO();

		String maNV = jtableNhanVien.getValueAt(row, 0).toString();

		List<ThuNhapKhac> tnk = tnkDao.getTNK(maNV, x, y);
		double luongLamThem = tnk.get(0).getLuongLamThem();
		String chuyenLLT = String.valueOf(tach(luongLamThem));
		txtLuongLamThemNV.setText(chuyenLLT);

		double phuCap = tnk.get(0).getPhuCap();
		String chuyenPC = String.valueOf(tach(phuCap));
		txtPhuCapNV.setText(chuyenPC);

		double Thuong = tnk.get(0).getThuong();
		String chuyenThuong = String.valueOf(tach(Thuong));
		txtThuongNV.setText((chuyenThuong));
//		ThuNhapKhac tnk = tnkDao.getluongLamThem(maNV,x,y);
		txtMaNV.setEditable(false);
		jYearNhanVien.setEnabled(false);
		jMonthNhanVien.setEnabled(false);
    }//GEN-LAST:event_jtableNhanVienMouseClicked
//  MOUSELCICK CÔNG NHÂN
    private void jtableCongNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableCongNhanMouseClicked
        int row = jtableCongNhan.getSelectedRow();
		txtMaCongNhan.setText(jtableCongNhan.getValueAt(row, 0).toString());
		cboTenSP.setSelectedItem(jtableCongNhan.getValueAt(row, 3).toString());
		cboCongDoan.setSelectedItem(jtableCongNhan.getValueAt(row, 2).toString());
		txtSanPhamThucTe.setText(jtableCongNhan.getValueAt(row, 4).toString());

		int x = Integer.parseInt(jtableCongNhan.getValueAt(row, 5).toString());
		jMonthCongNhan.setMonth(x - 1);
		int y = Integer.parseInt(jtableCongNhan.getValueAt(row, 6).toString());
		jYearCongNhan.setYear(y);

		ThuNhapKhac_DAO tnkDao = new ThuNhapKhac_DAO();

		String maCN = jtableCongNhan.getValueAt(row, 0).toString();

		List<ThuNhapKhac> tnk = tnkDao.getTNK(maCN, x, y);
		double luongLamThem = tnk.get(0).getLuongLamThem();
		String chuyenLLT = String.valueOf((luongLamThem));
		txtLuongLamThemCongNhan.setText(chuyenLLT);

		double phuCap = tnk.get(0).getPhuCap();
		String chuyenPC = String.valueOf((phuCap));
		txtPhuCapCongNhan.setText(chuyenPC);

		double Thuong = tnk.get(0).getThuong();
		String chuyenThuong = String.valueOf((Thuong));
		txtThuongCongNhan.setText(chuyenThuong);

		txtMaCongNhan.setEditable(false);
		jYearCongNhan.setEnabled(false);
		jMonthCongNhan.setEnabled(false);
    }//GEN-LAST:event_jtableCongNhanMouseClicked
// TÌM KIẾM NHÂN VIÊN
	// TỪ NHÂN VIÊN
	private void monthTuNhanVienPropertyChange(java.beans.PropertyChangeEvent evt) {
		
		dataNhanVien.getDataVector().removeAllElements();
		List<BangChamCong> ds = dlieu.getThangNam(jMonthTimKiemNV1.getMonth() + 1, jYearTimKiemNV1.getYear(),
				jMonthTimKiemNV2.getMonth() + 1, jYearTimKiemNV2.getYear());
		if (ds.size() > 0) {
			jtableNhanVien.setModel(dataNhanVien);
			jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
			jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
			jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
			jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
			jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
			jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
			jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
			for (int i = 0; i < ds.size(); i++) {
				dataNhanVien.addRow(new Object[] { ds.get(i).getMaNV(), ds.get(i).getTenNV(),
						tach(ds.get(i).getHeSoluong()), ds.get(i).getNghiKhongPhep(), ds.get(i).getSoCongNgayLe(),
						ds.get(i).getSoCongngaythuong(), ds.get(i).getSoCongchuan(), ds.get(i).getThang(),
						ds.get(i).getNam(), tach(ds.get(i).getThuNhapKhac()) });
			}
		}

		else {
    			
    	        dataNhanVien.getDataVector().removeAllElements();
    	        
    			jtableNhanVien.setModel(dataNhanVien);
    			jtableNhanVien.getTableHeader().setBackground(new Color(146, 200, 240));
    			jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
    			jtableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    			jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
    			jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
    			jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
    			jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
    			jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
    			jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
    			jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
    			jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
    			jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
    			jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
    			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
    			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    			jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    			jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
    			jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
    			jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
    			jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
    			jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
    			jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
    			jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
		}
	}

	// ĐẾN NHÂN VIÊN
	private void monthDenNhanVienPropertyChange(java.beans.PropertyChangeEvent evt) {
		DefaultTableModel newmodel2 = new DefaultTableModel(
				new String[] { "Mã nhân viên", "Tên nhân viên", "Hệ số lương", "Nghĩ không phép", "Công ngày lễ",
						"Công ngày thường", "Số Công Chuẩn", "Tháng", "Năm", "Thu Nhập Khác" },
				0);
		dataNhanVien.getDataVector().removeAllElements();
		List<BangChamCong> ds = dlieu.getThangNam(jMonthTimKiemNV1.getMonth() + 1, jYearTimKiemNV1.getYear(),
				jMonthTimKiemNV2.getMonth() + 1, jYearTimKiemNV2.getYear());

		if (ds.size() > 0) {
			jtableNhanVien.setModel(dataNhanVien);
			jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
			jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
			jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
			jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
			jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
			jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
			jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
			for (int i = 0; i < ds.size(); i++) {
				dataNhanVien.addRow(new Object[] { ds.get(i).getMaNV(), ds.get(i).getTenNV(),
						tach(ds.get(i).getHeSoluong()), ds.get(i).getNghiKhongPhep(), ds.get(i).getSoCongNgayLe(),
						ds.get(i).getSoCongngaythuong(), ds.get(i).getSoCongchuan(), ds.get(i).getThang(),
						ds.get(i).getNam(), tach(ds.get(i).getThuNhapKhac()) });
			}
		} else {

//         	JOptionPane.showMessageDialog(null, "Error");
//     		jtableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
//    	            new Object [][] {
//    	                {null, null, null, null},
//    	                {null, null, null, null},
//    	                {null, null, null, null},
//    	                {null, null, null, null}
//    	            },
//    	            new String [] {"Mã nhân viên","Tên nhân viên","Hệ số lương",
//    	     				"Nghĩ không phép","Công ngày lễ","Công ngày thường","Số Công Chuẩn","Tháng","Năm","Thu Nhập Khác"}
//    	        ));
			dataNhanVien.getDataVector().removeAllElements();
			jtableNhanVien.getTableHeader().setBackground(new Color(146, 200, 240));
			jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
			jtableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
			jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
			jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
			jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
			jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
			jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
			jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
			jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
			jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
		}
	}
//  TÌM NHÂN VIÊN
    private void btnTimNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimNVActionPerformed
       Object o = evt.getSource();
		if (o.equals(btnTimNV)) {
			DefaultTableModel newmodel = new DefaultTableModel(
					new String[] { "Mã nhân viên", "Tên nhân viên", "Hệ số lương", "Nghĩ không phép", "Công ngày lễ",
							"Công ngày thường", "Số Công Chuẩn", "Tháng", "Năm", "Thu Nhập Khác" },
					0);
			List<BangChamCong> ds = dlieu.TimKiem(txtTimKiemNV.getText(), jMonthTimKiemNV1.getMonth() + 1,
					jYearTimKiemNV1.getYear(), jMonthTimKiemNV2.getMonth() + 1, jYearTimKiemNV2.getYear());
//    	jtableNhanVien.setModel(newmodel);
			if (ds.size() > 0) {
				jtableNhanVien.setModel(newmodel);
				jtableNhanVien.getTableHeader().setBackground(new Color(146, 200, 240));
				jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
				jtableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
				jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
				jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
				jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
				jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
				jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
				for (int i = 0; i < ds.size(); i++) {
					newmodel.addRow(new Object[] { ds.get(i).getMaNV(), ds.get(i).getTenNV(), ds.get(i).getHeSoluong(),
							ds.get(i).getNghiKhongPhep(), ds.get(i).getSoCongNgayLe(), ds.get(i).getSoCongngaythuong(),
							ds.get(i).getSoCongchuan(), ds.get(i).getThang(), ds.get(i).getNam(),
							ds.get(i).getThuNhapKhac() });
				}
			} else {
				jtableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Mã nhân viên", "Tên nhân viên", "Hệ số lương", "Nghĩ không phép",
								"Công ngày lễ", "Công ngày thường", "Số Công Chuẩn", "Tháng", "Năm",
								"Thu Nhập Khác" }));
				jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
				jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
				jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
				jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
				jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
				jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
			}
		}
    }//GEN-LAST:event_btnTimNVActionPerformed
    private void monthTuCongNhanPropertyChange(java.beans.PropertyChangeEvent evt) {
//	    	DefaultTableModel newmodel = new DefaultTableModel(new String[] {"Mã nhân viên","Tên nhân viên","Công đoạn",
//					"Sản phẩm","Sản phẩm thực tế","Tháng","Năm","Thu nhập khác"},0);
			dataCongNhan.getDataVector().removeAllElements();
			List<CongNhan> dss = cnDao.getThangNam(jMonthTimKiemCN1.getMonth() + 1, jYearTimKiemCN2.getYear(),
					jMonthTimKiemCN2.getMonth() + 1, jYearCN2.getYear());
			if (dss.size() > 0) {
				jtableCongNhan.setModel(dataCongNhan);
				jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
				jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
				jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
				jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
				DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
				rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
				centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
				for (int i = 0; i < dss.size(); i++) {
					dataCongNhan.addRow(new Object[] { dss.get(i).getMaNV(), dss.get(i).getTenNV(),
							dss.get(i).getTenCongDoan(), dss.get(i).getTenSP(), dss.get(i).getSoLuong(),
							dss.get(i).getThang(), dss.get(i).getNam(), tach(dss.get(i).getThuNhapKhac()) });
				}
			}

			else {
				jtableCongNhan.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

				}, new String[] { "Mã nhân viên", "Tên nhân viên", "Công đoạn", "Sản phẩm", "Sản phẩm thực tế", "Tháng",
						"Năm", "Thu nhập khác" }));
				jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
				jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
				jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
				jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
				DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
				rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
				centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
//	 	   		 JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên ");
			}
		}

// ĐẾN CÔNG NHÂN
    private void monthDenCongNhanPropertyChange(java.beans.PropertyChangeEvent evt) {
//	    	DefaultTableModel newmodel = new DefaultTableModel(new String[] {"Mã nhân viên","Tên nhân viên","Công đoạn",
//					"Sản phẩm","Sản phẩm thực tế","Tháng","Năm","Thu nhập khác"},0);
			dataCongNhan.getDataVector().removeAllElements();
			List<CongNhan> dss = cnDao.getThangNam(jMonthTimKiemCN1.getMonth() + 1, jYearTimKiemCN2.getYear(),
					jMonthTimKiemCN2.getMonth() + 1, jYearCN2.getYear());
			if (dss.size() > 0) {
				jtableCongNhan.setModel(dataCongNhan);
				jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
				jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
				jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
				jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
				DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
				rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
				centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
				for (int i = 0; i < dss.size(); i++) {
					dataCongNhan.addRow(new Object[] { dss.get(i).getMaNV(), dss.get(i).getTenNV(),
							dss.get(i).getTenCongDoan(), dss.get(i).getTenSP(), dss.get(i).getSoLuong(),
							dss.get(i).getThang(), dss.get(i).getNam(), tach(dss.get(i).getThuNhapKhac()) });
				}
			} else {
				jtableCongNhan.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
						new String[] { "Mã nhân viên", "Tên nhân viên", "Công đoạn", "Sản phẩm", "Sản phẩm thực tế",
								"Tháng", "Năm", "Thu nhập khác" }));
				jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
				jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
				jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
				jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
				DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
				rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
				centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
//	 	   		 JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên ");
			}
		}
 //  TÌM KIẾM CÔNG NHÂN 
    private void btnTimKiemCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemCNActionPerformed
        Object o = evt.getSource();
			if (o.equals(btnTimKiemCN)) {
//		   	 DefaultTableModel datanew = new DefaultTableModel(new String[] {"Mã nhân viên","Tên nhân viên","Công đoạn",
//						"Sản phẩm","Sản phẩm thực tế","Tháng","Năm","Thu nhập khác"},0);
				dataCongNhan.getDataVector().removeAllElements();
				List<CongNhan> dss = cnDao.timKiem(txtTimKiemCN.getText(), jMonthTimKiemCN1.getMonth() + 1,
						jYearTimKiemCN2.getYear(), jMonthTimKiemCN2.getMonth() + 1, jYearCN2.getYear());
//		   	 JOptionPane.showMessageDialog(null, dss);
				if (dss.size() > 0) {
					jtableCongNhan.setModel(dataCongNhan);
					jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
					jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
					jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
					jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
					jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
					jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
					jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
					jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
					jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
					jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
					DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
					rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
					DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
					centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
					jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
					jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
					jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
					jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
					for (int i = 0; i < dss.size(); i++) {
						dataCongNhan.addRow(new Object[] { dss.get(i).getMaNV(), dss.get(i).getTenNV(),
								dss.get(i).getTenCongDoan(), dss.get(i).getTenSP(), dss.get(i).getSoLuong(),
								dss.get(i).getThang(), dss.get(i).getNam(), tach(dss.get(i).getThuNhapKhac()) });
					}
				} else {
					jtableCongNhan.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

					}, new String[] { "Mã nhân viên", "Tên nhân viên", "Công đoạn", "Sản phẩm", "Sản phẩm thực tế", "Tháng",
							"Năm", "Thu nhập khác" }));
					jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
					jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
					jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
					jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
					jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
					jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
					jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
					jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
					jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
					jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
					DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
					rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
					DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
					centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
					jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
					jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
					jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
					jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
					JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên ");

				}
			}
    }//GEN-LAST:event_btnTimKiemCNActionPerformed
//  XÓA RỖNG CÔNG NHÂN
    private void btnLamXoaRongCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamXoaRongCNActionPerformed
        txtMaCongNhan.setText("");
		cboTenSP.setSelectedItem(null);
		cboCongDoan.setSelectedItem(null);
		txtSanPhamThucTe.setText("");
		int month = instance.get(Calendar.MONTH);
		jMonthCongNhan.setMonth(month);
		int year = instance.get(Calendar.YEAR);
		jYearCongNhan.setYear(year);
		txtLuongLamThemCongNhan.setText("");
		txtThuongCongNhan.setText("");
		txtPhuCapCongNhan.setText("");
		txtThuongNV.setText("");
		txtPhuCapNV.setText("");
		txtMaCongNhan.requestFocus();
		txtMaCongNhan.setEditable(true);
		jMonthCongNhan.setEnabled(true);
		jYearCongNhan.setEnabled(true);
		jtableCongNhan.clearSelection();
    }//GEN-LAST:event_btnLamXoaRongCNActionPerformed
//  THÊM CÔNG NHÂN
    private void btnThemThemCongNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThemCongNhanActionPerformed
        Object o = evt.getSource();
		
		if (o.equals(btnThemThemCongNhan)) {
			if (jtableCongNhan.getSelectedRow() > -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng làm mới trước khi thêm");
				return;
			}
			if (validataCongNhan() && thuNhapKhac(txtLuongLamThemCongNhan) && thuNhapKhac(txtPhuCapCongNhan) && thuNhapKhac(txtThuongCongNhan)  ) {
				if (dlieu.kiemTraNhanVien(txtMaCongNhan.getText())) {
					if(dlieu.kiemTraNghiViec(txtMaCongNhan.getText())) {
						if (cnDao.rangBuocThangNam(txtMaCongNhan.getText(), jMonthCongNhan.getMonth() + 1,
								jYearCongNhan.getYear())) {
							String maNv = txtMaCongNhan.getText();
							NhanVien nv = nvDao.getNVtheoma(maNv);
							String tensp = cboTenSP.getSelectedItem().toString();
							SanPham sp = cdDao.getSPtheoMaVaTen(tensp);

							// JOptionPane.showMessageDialog(null, sp);
							String maSP = sp.getMaSP();
							String tenCongDoan = cboCongDoan.getSelectedItem().toString();
							CongDoan cd = cdDao.getmaCongDoanTheoTenCD(maSP, tenCongDoan);
//	    			        JOptionPane.showMessageDialog(null, cd);
							String maCongDoan = cd.getMaCongDoan();
							int soLuong = Integer.parseInt(txtSanPhamThucTe.getText().toString());

							int thang = jMonthCongNhan.getMonth() + 1;
							int nam = jYearCongNhan.getYear();

							String maThuNhapKhac = taoMaThuNhapKhac();
							double luongLamThem = Double.parseDouble(txtLuongLamThemCongNhan.getText());
							double phuCap = Double.parseDouble(txtPhuCapCongNhan.getText());
							double thuong = Double.parseDouble(txtThuongCongNhan.getText());
							double thuNhapKhac = (luongLamThem + phuCap + thuong);
							NhanVien_CongDoan nvcd = new NhanVien_CongDoan(nv, cd, soLuong, thang, nam);
							ThuNhapKhac tnhap = new ThuNhapKhac(maThuNhapKhac, luongLamThem, phuCap, thuong, nv, thang,
									nam);
							if (cnDao.themCongNhan(nvcd) == true && cnDao.themThuNhapKhac(tnhap)) {
								JOptionPane.showMessageDialog(null, "Thành công ");
								dataCongNhan.addRow(new Object[] { maNv, nv.getTenNV(), tenCongDoan, tensp, soLuong,
										thang, nam, thuNhapKhac });
							} else {
								JOptionPane.showMessageDialog(null, "Fail");
							}
						} else {
							JOptionPane.showMessageDialog(null, "da co");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nhân viên đã thôi việc!!!");
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Không tồn tại nhân viên!!!");
				}
			}
		}
    }//GEN-LAST:event_btnThemThemCongNhanActionPerformed
//  IMPORT CÔNG NHÂN
    private void btnLamMoiCongNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiCongNhanActionPerformed
        new ImportDataCongNhan_GUI().setVisible(true);
    }//GEN-LAST:event_btnLamMoiCongNhanActionPerformed
//  CẬP NHẬP CÔNG NHÂN
    private void btnCapNhapCongNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhapCongNhanActionPerformed
        Object o = evt.getSource();
		if (o.equals(btnCapNhapCongNhan)) {
			int row = jtableCongNhan.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
				return;
			} else {
				if(cnDao.kiemTraThanhToanCN(txtMaCongNhan.getText(), jMonthCongNhan.getMonth()+1, jYearCongNhan.getYear())) {
					int thang = jMonthCongNhan.getMonth() + 1;
					int nam = jYearCongNhan.getYear();
					String maNv = txtMaCongNhan.getText();
					NhanVien nv = nvDao.getNVtheoma(maNv);
					String tenNV = nv.getTenNV();

					String tensp = cboTenSP.getSelectedItem().toString();
					SanPham sp = cdDao.getSPtheoMaVaTen(tensp);

					String maSP = sp.getMaSP();
//					JOptionPane.showMessageDialog(null, maSP);
					String tenCongDoan = cboCongDoan.getSelectedItem().toString();
//					JOptionPane.showMessageDialog(null, tenCongDoan);
					CongDoan cd = cdDao.getCDTheoMaSpvaTenCD(maSP, tenCongDoan);

					String maCongDoan = cd.getMaCongDoan();
					String tenSP = cboTenSP.getSelectedItem().toString();
					int soLuong = Integer.parseInt(txtSanPhamThucTe.getText().toString());

					double luongLamThem = Double.parseDouble(txtLuongLamThemCongNhan.getText());
					double phuCap = Double.parseDouble(txtPhuCapCongNhan.getText());
					double thuong = Double.parseDouble(txtThuongCongNhan.getText());
					double thuNhapKhac = (luongLamThem + thuong + phuCap);
					ChamCongNVHC_DAO cc_dao = new ChamCongNVHC_DAO();

					NhanVien_CongDoan nvcd = new NhanVien_CongDoan(nv, cd, soLuong, thang, nam);
					ThuNhapKhac tnk = new ThuNhapKhac(luongLamThem, phuCap, thuong, nv, thang, nam);
//					JOptionPane.showMessageDialog(null, nvcd);

					if (cc_dao.updateThuNhapKhac(tnk) == true && cnDao.updateNhanVienCongDoan(nvcd) == true) {
						jtableCongNhan.setValueAt(maNv, row, 0);
						jtableCongNhan.setValueAt(tenNV, row, 1);
						jtableCongNhan.setValueAt(tenCongDoan, row, 2);
						jtableCongNhan.setValueAt(tensp, row, 3);
						jtableCongNhan.setValueAt(soLuong, row, 4);
						jtableCongNhan.setValueAt(thang, row, 5);
						jtableCongNhan.setValueAt(nam, row, 6);
						jtableCongNhan.setValueAt(tach(thuNhapKhac), row, 7);
						JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					} else {
						JOptionPane.showMessageDialog(null, "FAIL");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Đã thanh toán không thể cập nhập");
				}
			}
		}
    }//GEN-LAST:event_btnCapNhapCongNhanActionPerformed
//  XÓA RỖNG NHÂN VIÊN
    private void btnXoaRongNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaRongNhanVienActionPerformed
        Object o = evt.getSource();
		if (o.equals(btnXoaRongNhanVien)) {
			jtableNhanVien.clearSelection();
			xoaRongNhanVien();
		}
		jYearNhanVien.setEnabled(true);
		jMonthNhanVien.setEnabled(true);
    }//GEN-LAST:event_btnXoaRongNhanVienActionPerformed
//  IMPORT NHÂN VIÊN
    private void btnLamMoiNhanVienActionPerformed(java.awt.event.ActionEvent evt) {
        new ImprotDataNhanVien_GUI().setVisible(true);
    }
//  THÊM NHÂN VIÊN
    
    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        Object o = evt.getSource();
		if (o.equals(btnThemNhanVien)) {
			if (jtableNhanVien.getSelectedRow() > -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng làm mới trước khi thêm");
				return;
			}
			if (validataNhanVien() && thuNhapKhac(txtLuongLamThemNV) && thuNhapKhac(txtPhuCapNV) && thuNhapKhac(txtThuongNV) ) {
				if (dlieu.kiemTraNhanVien(txtMaNV.getText())) {
					if(dlieu.kiemTraNghiViec(txtMaNV.getText())) {
						if (dlieu.rangBuocThangNam(txtMaNV.getText(), jMonthNhanVien.getMonth() + 1,
								jYearNhanVien.getYear())) {
							String ma = taoMaNhanVienHanhChinh();
							String maNv = txtMaNV.getText();

							NhanVien nv = nvDao.getNVtheoma(maNv);
							int thang = jMonthNhanVien.getMonth();
							int nam = jYearNhanVien.getYear();
							double heSoLuong = Double.parseDouble(txtHeSoLuong.getText());
							int congNgayLe = Integer.parseInt(txtCongNgayLe.getText());
							int congNgayThuong = Integer.parseInt(txtCongNgayThuong.getText());
							int nghiKhongPhep = Integer.parseInt(txtNghiKhongPhep.getText());
							int soCongChuan = 2 * congNgayLe + congNgayThuong - nghiKhongPhep;

							String maThuNhapKhac = taoMaThuNhapKhac();
							double luongLamThem = Double.parseDouble(txtLuongLamThemNV.getText());
							double phuCap = Double.parseDouble(txtPhuCapNV.getText());
							double thuong = Double.parseDouble(txtThuongNV.getText());

							double thuNhapKhac = luongLamThem + phuCap + thuong;
							ChamCongNVHC nvien = new ChamCongNVHC(ma, nv, heSoLuong, congNgayLe, congNgayThuong,
									nghiKhongPhep, thang + 1, nam);
							ThuNhapKhac tnhap = new ThuNhapKhac(maThuNhapKhac, luongLamThem, phuCap, thuong, nv,
									thang + 1, nam);
							
							if(dlieu.themChamCongNhanVienHC(nvien) && dlieu.themThuNhapKhac(tnhap) ) {
								dataNhanVien.addRow(new Object[] { maNv, nv.getTenNV(), tach(heSoLuong), nghiKhongPhep,
										congNgayLe, congNgayThuong, soCongChuan, thang + 1, nam, tach(thuNhapKhac) });
								JOptionPane.showMessageDialog(null, "Thành công");
							}
							else {
								JOptionPane.showMessageDialog(null, "Fail");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Nhân viên đã được chấm công!!!");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nhân viên đã thôi việc!!!");
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Nhân viên không tồn tại!!!");
				}
			}
		}
    }//GEN-LAST:event_btnThemNhanVienActionPerformed
//  CẬP NHẬP NHÂN VIÊN
    private void btnCapNhapNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhapNhanVienActionPerformed
        Object o = evt.getSource();
		if (o.equals(btnCapNhapNhanVien)) {
			int row = jtableNhanVien.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
				return;
			} else {
				if(dlieu.kiemTraThanhToanNV(txtMaNV.getText(), jMonthNhanVien.getMonth()+1, jYearNhanVien.getYear())) {
					if (validataNhanVien() && thuNhapKhac(txtLuongLamThemNV) && thuNhapKhac(txtPhuCapNV) && thuNhapKhac(txtThuongNV) ) {
						int thang = jMonthNhanVien.getMonth();
						int nam = jYearNhanVien.getYear();
						NhanVien_DAO nv_dao = new NhanVien_DAO();
						NhanVien nv = nv_dao.getNVtheoma(txtMaNV.getText());
						String maNV = txtMaNV.getText();
						String tenNV = nv.getTenNV();
						double hsl = Double.parseDouble(txtHeSoLuong.getText());
						int congNgayThuong = Integer.parseInt(txtCongNgayThuong.getText());
						int nghiKhongPhep = Integer.parseInt(txtNghiKhongPhep.getText());
						int congNgayLe = Integer.parseInt(txtCongNgayLe.getText());
						int soCongChuan = 2 * congNgayLe + congNgayThuong - nghiKhongPhep;
						String maThuNhapKhac = taoMaThuNhapKhac();
						double luongLamThem = Double.parseDouble(txtLuongLamThemNV.getText());
						double phuCap = Double.parseDouble(txtPhuCapNV.getText());
						double thuong = Double.parseDouble(txtThuongNV.getText());
						double thuNhapKhac = (luongLamThem + phuCap + thuong);
						ChamCongNVHC_DAO cc_dao = new ChamCongNVHC_DAO();
						ChamCongNVHC cc = new ChamCongNVHC(hsl, congNgayLe, congNgayThuong, nghiKhongPhep, thang + 1, nam);
						cc.setMaNV(nv);
						ThuNhapKhac tnk = new ThuNhapKhac(luongLamThem, phuCap, thuong, nv, thang + 1, nam);
						// JOptionPane.showMessageDialog(null, tnk);
						// cc_dao.updateNhanVienHanhChinh(cc);
						if (cc_dao.updateThuNhapKhac(tnk) && cc_dao.updateNhanVienHanhChinh(cc)) {
							jtableNhanVien.setValueAt(maNV, row, 0);
							jtableNhanVien.setValueAt(tenNV, row, 1);
							jtableNhanVien.setValueAt(tach(hsl), row, 2);
							jtableNhanVien.setValueAt(nghiKhongPhep, row, 3);
							jtableNhanVien.setValueAt(congNgayLe, row, 4);
							jtableNhanVien.setValueAt(congNgayThuong, row, 5);
							jtableNhanVien.setValueAt(soCongChuan, row, 6);
							jtableNhanVien.setValueAt(thang, row, 7);
							jtableNhanVien.setValueAt(nam, row, 8);
							jtableNhanVien.setValueAt(tach(thuNhapKhac), row, 9);
							JOptionPane.showMessageDialog(null, "Cập nhật thành công");
						} else {
							JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Đã thanh toán không thể cập nhập!!!");
				}
			}
		}
    }//GEN-LAST:event_btnCapNhapNhanVienActionPerformed
//  LIST GỢI Ý
    private void listCongNhanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listCongNhanMousePressed
        if(!(listCongNhan.isSelectionEmpty())){
           ListSearchCongNhan ls = (ListSearchCongNhan) modelCongNhan.getElementAt(listCongNhan.getSelectedIndex());
           String maNv = ls.getMaNV();
           String tenNv = ls.getTenNV();
           String tenSP = ls.getTenSP();
           String tenCD = ls.getTenCongDoan();
           int year = instance.get(Calendar.YEAR);
           int thang = instance.get(Calendar.MONTH);
           dataCongNhan.getDataVector().removeAllElements();
			List<CongNhan> dss = cnDao.timKiem(txtTimKiemCN.getText(), jMonthTimKiemCN1.getMonth() + 1,
					jYearTimKiemCN2.getYear(), jMonthTimKiemCN2.getMonth() + 1, jYearCN2.getYear());
           for (int i = 0; i < dss.size(); i++) {
     			dataCongNhan.addRow(new Object[] { dss.get(i).getMaNV(), dss.get(i).getTenNV(), dss.get(i).getTenCongDoan(),
     					dss.get(i).getTenSP(), dss.get(i).getSoLuong(), dss.get(i).getThang(), dss.get(i).getNam(),
     					tach(dss.get(i).getThuNhapKhac()) });
     		}
     		jtableCongNhan.setModel(dataCongNhan);
     		jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
     		jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
     		jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
             
     		jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
     		jtableCongNhan.setFont(new Font("Arial", Font.PLAIN, 13));
//     		jtableCongNhan.setRowHeight(jtableCongNhan.getRowHeight()+20);
     		jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     		jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     		jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
     		jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
     		jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
     		jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
     		jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
     		jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
     		jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
     		jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
     		DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
     		rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
     		DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
     		centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
     		jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
     		jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
     		jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
     		jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
     		menuCongNhan.setVisible(false);

           
       }
    }//GEN-LAST:event_listCongNhanMousePressed
//  GỢI Ý CÔNG NHÂN
    private void txtTimKiemCNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemCN2KeyReleased
         addMouseEvent(listCongNhan);
         String search = txtTimKiemCN.getText().toString();
         String tk = txtTimKiemCN.getText();
         Connection con = new Connect().getConnect();
         listCongNhan.setModel(modelCongNhan);
         modelCongNhan.removeAllElements();
         PreparedStatement p = null;
         if(!(search.equals(""))){
             menuCongNhan.show(txtTimKiemCN, 0, txtTimKiemCN.getHeight());
             try {
                 p = con.prepareStatement("Select maNV,tenNV,tenSP,tenCongDoan From LuongCN1 where maNV like N'%"+tk+"%' or tenNV like N'%"+tk+"%' or tenSP like N'%"+tk+"%' or tenCongDoan like N'%"+tk+"%' group by maNV,tenNV,tenSP,tenCongDoan " );
                 ResultSet rs = p.executeQuery();
                 while (rs.next()) {
                     ListSearchCongNhan obj = new ListSearchCongNhan(rs.getString("maNV"), rs.getString("tenNV"), rs.getString("tenSP"),rs.getString("tenCongDoan") );
                     modelCongNhan.addElement(obj);
                   
                     if(modelCongNhan.size() ==4) {
                    	 break;
                     }
                    
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }       
         }
    }//GEN-LAST:event_txtTimKiemCN2KeyReleased

    /**
     *
     * @param com
     */
    public void addMouseEvent(JList com){
         com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(215, 216, 216));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(Color.WHITE);
            }

        });
    }
    private void txtTimKiemNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemNVMouseClicked
       
    }//GEN-LAST:event_txtTimKiemNVMouseClicked
// MOUSECLICK BUTTON TÌM CÔNG NHÂN
    private void txtTimKiemCNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemCNMouseClicked
//        menuCongNhan.show(txtTimKiemCN, 0, txtTimKiemCN.getHeight());
    }//GEN-LAST:event_txtTimKiemCNMouseClicked

    private void listCongNhanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listCongNhanMouseEntered
            setBackground(new Color(215,216,216));
    }//GEN-LAST:event_listCongNhanMouseEntered

    private void listCongNhanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listCongNhanMouseExited
        setBackground(Color.WHITE);
    }//GEN-LAST:event_listCongNhanMouseExited
//  GỢI Ý NHÂN VIÊN
    private void txtTimKiemNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemNVKeyReleased
        String search = txtTimKiemNV.getText().trim();
        String tk = txtTimKiemNV.getText();
        Connection con = new Connect().getConnect();
        listNhanVien.setModel(modelNhanVien);
        modelNhanVien.removeAllElements();
        PreparedStatement p = null;
        if(!(search.equals(""))){
            menuNhanVien.show(txtTimKiemNV, 0, txtTimKiemNV.getHeight());
            try {
                p = con.prepareStatement("Select maNV,tenNV From LuongNV1 where maNV like N'%"+tk+"%' or tenNV like N'%"+tk+"%' Group by maNV,tenNV" );
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    ListSearchNhanVien obj = new ListSearchNhanVien(rs.getString("maNV"), rs.getString("tenNV"));
                    modelNhanVien.addElement(obj); 
                    if(modelNhanVien.size() ==5) {
                    	break;
                    }
                   
                }
            } catch (Exception e) {
                e.printStackTrace();
            }       
        }
    }//GEN-LAST:event_txtTimKiemNVKeyReleased
//  LIST NHÂN VIÊN
    private void listNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listNhanVienMousePressed
         if(!(listNhanVien.isSelectionEmpty())) {
        	ListSearchNhanVien ls = (ListSearchNhanVien) modelNhanVien.getElementAt(listNhanVien.getSelectedIndex());
        	String maNV = ls.getMaNV();
        	String tenNV = ls.getTenNV();
//        	int tuThang  = jMonthTimKiemNV1.getMonth();
//        	int tuNam = jYearTimKiemNV2.getYear();
//        	int denThang = jMonthTimKiemNV2.getMonth();
//        	int denNam = jYearTimKiemNV2.getYear();
        	dataCongNhan.getDataVector().removeAllElements();
        	List<BangChamCong> ds = dlieu.TimKiem(txtTimKiemNV.getText(), jMonthTimKiemNV1.getMonth() + 1,
					jYearTimKiemNV1.getYear(), jMonthTimKiemNV2.getMonth() + 1, jYearTimKiemNV2.getYear()); 
        	for (int i = 0; i < ds.size(); i++) {
      			dataNhanVien.addRow(new Object[] { ds.get(i).getMaNV(), ds.get(i).getTenNV(),
      					tach(ds.get(i).getHeSoluong()), ds.get(i).getNghiKhongPhep(), ds.get(i).getSoCongNgayLe(),
      					ds.get(i).getSoCongngaythuong(), ds.get(i).getSoCongchuan(), ds.get(i).getThang(),
      					ds.get(i).getNam(), tach(ds.get(i).getThuNhapKhac()) });
      		}
       
      		jtableNhanVien.setModel(dataNhanVien);
      		jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
      		jtableNhanVien.setFont(new Font("Arial", Font.PLAIN, 13));
      		JTableHeader h = jtableNhanVien.getTableHeader();
      		h.setFont(new Font("Arial", Font.BOLD, 13));
      		h.setForeground(new Color(255, 255, 255));
      		h.setBackground(new Color(146, 200, 240));
      		jtableNhanVien.getTableHeader().setBackground(new Color(146, 200, 240));
      		jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
      		jtableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      		jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
      		jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
      		jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
      		jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
      		jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
      		jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
      		jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
      		jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
      		jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
      		jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
      		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
      		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
      		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
      		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
      		jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
      		jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
      		jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
      		menuNhanVien.setVisible(false);
        	
        	
        }
    }//GEN-LAST:event_listNhanVienMousePressed

    private void btnNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMouseClicked
           
    }//GEN-LAST:event_btnNhanVienMouseClicked
/// BROWSE CÔNG NHÂN
    private void btnBrowseCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseCNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBrowseCNActionPerformed
//  CLOSE CÔNG NHÂN
    private void btnCloseCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseCNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCloseCNActionPerformed
//  UPLOAD CÔNG NHÂN
    private void btnUploadCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadCNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUploadCNActionPerformed
// CBO TÊN SẢN PHẨM
 	private void cboTenSPActionPerformed(java.awt.event.ActionEvent evt) {
 		String tenSP = (String) cboTenSP.getSelectedItem();
 		loadTenCongDoan(tenSP);
 	}

 	// CBO TÊN CÔNG ĐOẠN
 	public void loadTenCongDoan(String tensp) {
 		Connection con = new Connect().getConnect();
 		PreparedStatement p;
 		try {
 			p = con.prepareStatement(
 					"Select tenCongDoan From [dbo].[SanPham] sp join [dbo].[CongDoan] cd on sp.maSP = cd.maSanPham where tenSP = N'"
 							+ tensp + "'  ");
// 			p.setString(1, tensp);
 			ResultSet rs = p.executeQuery();
 			cboCongDoan.removeAllItems();
 			while (rs.next()) {
 				cboCongDoan.addItem(rs.getString("tenCongDoan"));
 			}
 			rs.close();
 			p.close();
 			con.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 			System.out.println("CongDoanLoi");
 		}
 	}

 	public String tach(double luong) {
 		int chucnghin, tramnghin, trieu, nghin, tram, chuc, dvi;
 		trieu = (int) (luong / 1000000);
 		tramnghin = (int) ((luong - (trieu * 1000000)) / 100000);
 		chucnghin = (int) ((luong - (trieu * 1000000) - (tramnghin * 100000)) / 10000);
 		nghin = (int) ((luong - (trieu * 1000000) - (tramnghin * 100000) - (chucnghin * 10000)) / 1000);
 		tram = (int) ((luong - (trieu * 1000000) - (tramnghin * 100000) - (chucnghin * 10000) - (nghin * 1000)) / 100);
 		chuc = (int) ((luong - (trieu * 1000000) - (tramnghin * 100000) - (chucnghin * 10000) - (nghin * 1000)
 				- (tram * 100)) / 10);
 		dvi = (int) ((luong - (trieu * 1000000) - (tramnghin * 100000) - (chucnghin * 10000) - (nghin * 1000)
 				- (tram * 100) - (chuc * 10)));

 		if (trieu > 0) {
 			return ("" + trieu + "." + tramnghin + "" + chucnghin + "" + nghin + "." + tram + "" + chuc + "" + dvi);
 		} else if (trieu == 0 && tramnghin > 0) {
 			return ("" + tramnghin + chucnghin + nghin + "." + tram + chuc + dvi);
 		} else if (trieu == 0 && tramnghin == 0 && chucnghin > 0) {
 			return ("" + chucnghin + nghin + "." + tram + chuc + dvi);
 		} else if (trieu == 0 && tramnghin == 0 && chucnghin == 0 && nghin > 0) {
 			return ("" + nghin + "." + tram + chuc + dvi);
 		} else if (trieu == 0 && tramnghin == 0 && chucnghin == 0 && nghin == 0 && tram > 0) {
 			return ("" + tram + chuc + dvi);
 		} else if (trieu == 0 && tramnghin == 0 && chucnghin == 0 && nghin == 0 && tram == 0 && chuc > 0) {
 			return ("" + chuc + dvi);
 		} else {
 			return ("" + dvi);
 		}
 	}

 	// TẠO MÃ THU NHẬP KHÁC
 	public String taoMaThuNhapKhac() {

 		String maCuoi = dlieu.getTHCuoi().getMaThuNhapKhac();
 		long so = Long.parseLong(maCuoi.substring(2)) + 100000 + 1;
 		String soMoi = ("" + so).substring(1);
 		return "TN" + soMoi;
 	}

 //TẠO MÃ NHÂN VIÊN
 	public String taoMaNhanVienHanhChinh() {

 		String maCuoi = dlieu.getNVCuoi().getMaChamCong();
 		long so = Long.parseLong(maCuoi.substring(2)) + 100000 + 1;
 		String soMoi = ("" + so).substring(1);
 		return "CC" + soMoi;
 	}

 // XÓA RỖNG NHÂN VIÊN
 	public void xoaRongNhanVien() {
 		txtMaNV.setText("");
		txtMaNV.setEditable(true);
		txtHeSoLuong.setText("");
		txtNghiKhongPhep.setText("");
		txtCongNgayLe.setText("");
		txtCongNgayThuong.setText("");
		txtNghiKhongPhep.setText("");
		txtLuongLamThemNV.setText("");
		txtPhuCapNV.setText("");
		txtThuongNV.setText("");
		txtMaNV.requestFocus();
 	}

 // Validata NHÂN VIÊN
 	private boolean validataNhanVien() {
 		if (txtMaNV.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Mã nhân viên không được rỗng");
 			txtMaNV.requestFocus();
 			return false;
 		}

 		if (!(txtMaNV.getText().matches("[N][V][0-9]{1,6}"))) {
 			JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ");
 			txtMaNV.requestFocus();
 			txtMaNV.selectAll();
 			return false;
 		}
 		if (txtHeSoLuong.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Hệ số lương không được rỗng");
 			txtHeSoLuong.requestFocus();
 			return false;
 		}
 		if (!(txtHeSoLuong.getText().matches("[0-9]{2,12}"))) {
 			JOptionPane.showMessageDialog(null, "Hệ số lương không hợp lệ");
 			txtHeSoLuong.requestFocus();
 			txtHeSoLuong.selectAll();
 			return false;
 		}
 		if (txtNghiKhongPhep.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Nghỉ không phép không được rỗng");
 			txtNghiKhongPhep.requestFocus();
 			return false;
 		}
 		if (!(txtNghiKhongPhep.getText().matches("[0-9]{1}"))) {
 			JOptionPane.showMessageDialog(null, "Nghĩ không phép không hợp lệ");
 			txtNghiKhongPhep.requestFocus();
 			txtNghiKhongPhep.selectAll();
 			return false;
 		}
 		if (txtCongNgayLe.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Công ngày lễ không được rỗng");
 			txtCongNgayLe.requestFocus();
 			return false;
 		}
 		if (!(txtCongNgayLe.getText().matches("[0-9]{1,5}"))) {
 			JOptionPane.showMessageDialog(null, "Công ngày lễ không hợp lệ");
 			txtCongNgayLe.requestFocus();
 			txtCongNgayLe.selectAll();
 			return false;
 		}
 		if (txtCongNgayThuong.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Công ngày thường không được rỗng");
 			txtCongNgayThuong.requestFocus();
 			return false;
 		}
 		if (!(txtCongNgayThuong.getText().matches("[0-9]{1,3}"))) {
 			JOptionPane.showMessageDialog(null, "Công ngày thường không hợp lệ");
 			txtCongNgayThuong.requestFocus();
 			txtCongNgayThuong.selectAll();
 			return false;
 		}
 		if (!(txtLuongLamThemNV.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Lương làm thêm không hợp lệ");
 			txtLuongLamThemNV.requestFocus();
 			txtLuongLamThemNV.selectAll();
 			return false;
 		}
 		if (!(txtPhuCapNV.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Phụ cấp không hợp lệ");
 			txtPhuCapNV.requestFocus();
 			txtPhuCapNV.selectAll();
 			return false;
 		}
 		if (!(txtThuongNV.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Thưởng không hợp lệ");
 			txtThuongNV.requestFocus();
 			txtThuongNV.selectAll();
 			return false;
 		}
//     	if(txtLuongLamThemNV.getText().trim().equals("")) {
//     		int a =0;
//     		String b = String.valueOf(a);
//     		txtLuongLamThemNV.setText(b);
//     		
//     		if(txtPhuCapNV.getText().trim().equals("")) {
//         		int c =0;
//         		String d = String.valueOf(c);
//         		txtPhuCapNV.setText(b);
//         	}
//     		if(txtThuongNV.getText().trim().equals("")) {
// 				int e =0;
//         		String f = String.valueOf(e);
//         		txtThuongNV.setText(b);
// 			}
//     	}
 		return true;
 	}
 	public boolean thuNhapKhac(javax.swing.JTextField txt) {
 		if(txt.getText().trim().equals("")) {
     		int a =0;
     		String b = String.valueOf(a);
     		txt.setText(b);
//     		JOptionPane.showMessageDialog(null, txt.getText());
     		return true;
 		}
 		
 		return true;
 	}
 // VALIDATA CÔNG NHÂN
 	private boolean validataCongNhan() {
 		if (txtMaCongNhan.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Mã công nhân không được rỗng");
 			txtMaCongNhan.requestFocus();
 			return false;
 		}

 		if (!(txtMaCongNhan.getText().matches("[L][D][0-9]{1,6}"))) {
 			JOptionPane.showMessageDialog(null, "Mã công nhân không hợp lệ");
 			txtMaCongNhan.requestFocus();
 			txtMaCongNhan.selectAll();
 			return false;
 		}
 		if (cboTenSP.getSelectedItem() == null) {
 			JOptionPane.showMessageDialog(null, "Vui lòng chọn tên sản phẩm");
 			cboTenSP.requestFocus();
 			return false;
 		}
 		if (cboCongDoan.getSelectedItem() == null) {
 			JOptionPane.showMessageDialog(null, "Vui lòng chọn tên công đoạn");
 			return false;
 		}
 		if (txtSanPhamThucTe.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không được rỗng");
 			txtSanPhamThucTe.requestFocus();
 			return false;
 		}
 		if (!(txtSanPhamThucTe.getText().matches("[0-9]{0,4}"))) {
 			JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không hợp lệ");
 			txtSanPhamThucTe.requestFocus();
 			txtSanPhamThucTe.selectAll();
 			return false;
 		}
 		if (!(txtLuongLamThemCongNhan.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Lương làm thêm không hợp lệ");
 			txtLuongLamThemCongNhan.requestFocus();
 			txtLuongLamThemCongNhan.selectAll();
 			return false;
 		}

 		if (!(txtPhuCapCongNhan.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Phụ cấp không hợp lệ");
 			txtPhuCapCongNhan.requestFocus();
 			txtPhuCapCongNhan.selectAll();
 			return false;
 		}
 		if (!(txtThuongCongNhan.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Thưởng không hợp lệ");
 			txtThuongCongNhan.requestFocus();
 			txtThuongCongNhan.selectAll();
 			return false;
 		}
 		if (txtLuongLamThemCongNhan.getText().trim().equals("")) {
 			int a = 0;
 			String b = String.valueOf(a);
 			txtLuongLamThemCongNhan.setText(b);

 			if (txtPhuCapCongNhan.getText().trim().equals("")) {
 				int c = 0;
 				String d = String.valueOf(c);
 				txtPhuCapCongNhan.setText(b);
 			}
 			if (txtThuongCongNhan.getText().trim().equals("")) {
 				int e = 0;
 				String f = String.valueOf(e);
 				txtThuongCongNhan.setText(b);
 			}
 		}
 		return true;
 	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseCN;
    private javax.swing.JButton btnCapNhapCongNhan;
    private javax.swing.JButton btnCapNhapNhanVien;
    private javax.swing.JButton btnCloseCN;
    private java.awt.Button btnCongNhan;
    private javax.swing.JButton btnLamMoiCongNhan;
    private javax.swing.JButton btnLamMoiNhanVien;
    private javax.swing.JButton btnLamXoaRongCN;
    private java.awt.Button btnNhanVien;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnThemThemCongNhan;
    private javax.swing.JButton btnTimKiemCN;
    private javax.swing.JButton btnTimNV;
    private javax.swing.JButton btnUploadCN;
    private javax.swing.JButton btnXoaRongNhanVien;
    private javax.swing.JComboBox<String> cboCongDoan;
    private javax.swing.JComboBox<String> cboTenSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthCongNhan;
    private com.toedter.calendar.JMonthChooser jMonthNhanVien;
    private com.toedter.calendar.JMonthChooser jMonthTimKiemCN1;
    private com.toedter.calendar.JMonthChooser jMonthTimKiemCN2;
    private com.toedter.calendar.JMonthChooser jMonthTimKiemNV1;
    private com.toedter.calendar.JMonthChooser jMonthTimKiemNV2;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private com.toedter.calendar.JYearChooser jYearCN2;
    private com.toedter.calendar.JYearChooser jYearCongNhan;
    private com.toedter.calendar.JYearChooser jYearNhanVien;
    private com.toedter.calendar.JYearChooser jYearTimKiemCN2;
    private com.toedter.calendar.JYearChooser jYearTimKiemNV1;
    private com.toedter.calendar.JYearChooser jYearTimKiemNV2;
    private javax.swing.JPanel jpn;
    private javax.swing.JPanel jpnCongNhan;
    private javax.swing.JPanel jpnImportCongNhan;
    private javax.swing.JPanel jpnNhanVien;
    public 	static javax.swing.JTable jtableCongNhan;
    public static javax.swing.JTable jtableNhanVien;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JLabel lblmaNV;
    private javax.swing.JLabel lblmaNV1;
    private javax.swing.JLabel lblmaNV10;
    private javax.swing.JLabel lblmaNV11;
    private javax.swing.JLabel lblmaNV12;
    private javax.swing.JLabel lblmaNV13;
    private javax.swing.JLabel lblmaNV14;
    private javax.swing.JLabel lblmaNV15;
    private javax.swing.JLabel lblmaNV2;
    private javax.swing.JLabel lblmaNV3;
    private javax.swing.JLabel lblmaNV4;
    private javax.swing.JLabel lblmaNV5;
    private javax.swing.JLabel lblmaNV6;
    private javax.swing.JLabel lblmaNV7;
    private javax.swing.JLabel lblmaNV8;
    private javax.swing.JLabel lblmaNV9;
    private javax.swing.JList<String> listCongNhan;
    private javax.swing.JList<String> listNhanVien;
    private javax.swing.JPopupMenu menuCongNhan;
    private javax.swing.JPopupMenu menuNhanVien;
    private java.awt.Panel panel2;
    private java.awt.Panel panel6;
    private javax.swing.JPanel panelttNV;
    private javax.swing.JPanel panelttNV1;
    private javax.swing.JPanel panelttNV2;
    private javax.swing.JPanel panelttNV3;
    private javax.swing.JPanel panelttNV4;
    private javax.swing.JPanel panelttNV5;
    private javax.swing.JPanel pnSearchCongNhan;
    private javax.swing.JPanel pnSearchNhanVien;
    private java.awt.TextField textField1;
    private java.awt.TextField textField5;
    private javax.swing.JTextField txtCongNgayLe;
    private javax.swing.JTextField txtCongNgayThuong;
    private javax.swing.JTextField txtHeSoLuong;
    private javax.swing.JTextField txtLuongLamThemCongNhan;
    private javax.swing.JTextField txtLuongLamThemNV;
    private javax.swing.JTextField txtMaCongNhan;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNghiKhongPhep;
    private javax.swing.JTextField txtPathCN;
    private javax.swing.JTextField txtPhuCapCongNhan;
    private javax.swing.JTextField txtPhuCapNV;
    private javax.swing.JTextField txtSanPhamThucTe;
    private javax.swing.JTextField txtThuongCongNhan;
    private javax.swing.JTextField txtThuongNV;
    private gui.MyTextField txtTimKiemCN;
    private gui.MyTextField txtTimKiemNV;
    // End of variables declaration//GEN-END:variables

    
}
