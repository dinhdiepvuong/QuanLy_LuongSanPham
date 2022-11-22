package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import dao.ChamCongNVHC_DAO;
import dao.DuLieuCongNhan_DAO;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;
import dao.TaiKhoan_Dao;
import entity.LuongCongNhan;
import entity.LuongNhanVien;
import entity.NhanVien;
import entity.PhongBan;


import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JTextArea;

public class TrangChuNhanVien_GUI extends JFrame  implements ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelLich;
	private JTable tableLich;
//	private ArrayList<CongTrinh> listCT = new ArrayList<CongTrinh>();
//	private ArrayList<ChiTietCV> listChiTiet = new ArrayList<ChiTietCV>();
//	private ChiTietCVDAO chiTietDAO = new ChiTietCVDAO();
	private JButton btnDMK;
	private JButton btnDX;
	private JLabel lblMSNV;
//	private LaoDong laoDong;
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
	public TrangChuNhanVien_GUI(NhanVien nv) {
//		laoDong = ld;
//		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		listCT = new ThongTinLaoDong_DAO().getCongTrinh(ld.getMaLaoDong());
		setResizable(false);
		setTitle("Chương trình quản lý lao động");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1100, 600);
		setBounds(0, 0, 1544, 830);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
//		panel.setBounds(0, 0, 1094, 227);
		panel.setBounds(0, 0, 1544, 300);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTen = new JLabel("lblTen");
		lblTen.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTen.setHorizontalAlignment(SwingConstants.LEFT);
		lblTen.setBounds(360, 75, 265, 45);
		panel.add(lblTen);
		
		//lblSDT
		JLabel lblChucVu1 = new JLabel("lblSDT");
		lblChucVu1.setHorizontalAlignment(SwingConstants.LEFT);
		lblChucVu1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblChucVu1.setBounds(810, 75, 100, 34);
		panel.add(lblChucVu1);
		// Ngày sinh
		JLabel lblNgaySinh = new JLabel("Ngày Sinh:");
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgaySinh.setBounds(1050, 135, 120, 33);
		panel.add(lblNgaySinh);
		//lblNgaySinh
		JLabel ngaySinh = new JLabel("lblNgaySinh");
		ngaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ngaySinh.setBounds(1210, 135, 120, 33);
		panel.add(ngaySinh);
		
		// Ngày vào làm
		JLabel lblGioiTinh = new JLabel("Ngày Vào Làm\r\n:");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGioiTinh.setBounds(1050, 75, 145, 33);
		panel.add(lblGioiTinh);
		//lblNgayVaoLam
		JLabel ngayVaolam = new JLabel("lblNgayVaoLam\r\n:");
		ngayVaolam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ngayVaolam.setBounds(1210, 75, 145, 33);
		panel.add(ngayVaolam);
		
		// MSNV
		JLabel msnv = new JLabel("MSNV:");
		msnv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		msnv.setBounds(360, 135, 85, 32);
		panel.add(msnv);
		//CMND
		JLabel cmnd = new JLabel("CMND:");
		cmnd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cmnd.setBounds(690, 135, 85, 32);
		panel.add(cmnd);
		//lblCMND
		JLabel lblCMND = new JLabel("lblCMND");
		lblCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCMND.setBounds(810, 135, 100, 32);
		panel.add(lblCMND);
		
//		Giới tính
		JLabel lblGioiTinhNV = new JLabel("Giới tính:");
		lblGioiTinhNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGioiTinhNV.setBounds(360, 195, 98, 32);
		panel.add(lblGioiTinhNV);
		//phongBan
		JLabel gioiTinh = new JLabel("lblGioiTinh");
		gioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		gioiTinh.setBounds(465, 195, 140, 32);
		panel.add(gioiTinh);
		
		
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Thông Tin Nhân Viên");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(635, 11, 298, 45);
		panel.add(lblNewLabel_3);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(127, 521, 89, 23);
		panel.add(btnNewButton);
		
		if(nv.isGioiTinh()== false)
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(TrangChuNhanVien_GUI.class.getResource("/Photo for Design Form/New_Female.png")));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(170, 75, 150, 150);
			panel.add(label);
		}
		else {
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(TrangChuNhanVien_GUI.class.getResource("/Photo for Design Form/male_news.png")));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(170, 75, 150, 150);
			panel.add(label);
		}
		
		// Số điện thoại
		JLabel lblChucVu = new JLabel("SDT:");
		lblChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		lblNgaySinh.setBounds(1050, 135, 120, 33);
		lblChucVu.setBounds(690, 75, 73, 34);
		panel.add(lblChucVu);
		//Phòng ban
		JLabel lblPhongBan = new JLabel("Phòng Ban:");
		lblPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		lblNgaySinh.setBounds(1050, 135, 120, 33);
		lblPhongBan.setBounds(690, 195, 120, 32);
		panel.add(lblPhongBan);
		//lblPhongban
		JLabel phongban = new JLabel("lblPhongBan");
		phongban.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		lblNgaySinh.setBounds(1050, 135, 120, 33);
		phongban.setBounds(810, 195, 180, 32);
		panel.add(phongban);

		// lblMSNV
		lblMSNV = new JLabel("lblMSNV");
		lblMSNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		lblCMND.setBounds(360, 135, 85, 32);
		lblMSNV.setBounds(465, 135, 185, 32);
		panel.add(lblMSNV);

		JLabel lblNgaySinh1 = new JLabel();
		lblNgaySinh1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNgaySinh1.setBounds(832, 91, 115, 33);
		panel.add(lblNgaySinh1);
		
		//Button Đỗi mật khẩu
		btnDMK = new JButton("Đổi mật khẩu");
		btnDMK.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDMK.setBounds(1200, 195, 140, 40);
		btnDMK.setBackground(new Color(65, 115, 201));
		btnDMK.setForeground(Color.white);
		panel.add(btnDMK);
		//Button Đăng xuất
		btnDX = new JButton("Đăng xuất");
		btnDX.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDX.setBounds(1360, 195, 140, 40);
		btnDX.setBackground(new Color(65, 115, 201));
		btnDX.setForeground(Color.white);
		panel.add(btnDX);


		JLabel lblGioiTinh1 = new JLabel();
		lblGioiTinh1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGioiTinh1.setBounds(832, 59, 115, 32);
		panel.add(lblGioiTinh1);

		JLabel lblSDT1 = new JLabel();
		lblSDT1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSDT1.setBounds(549, 91, 100, 32);
		panel.add(lblSDT1);

//		JTextArea txtDiaChi = new JTextArea();
//		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//		txtDiaChi.setLineWrap(true);
//		txtDiaChi.setWrapStyleWord(true);
//		txtDiaChi.setBounds(271, 126, 298, 57);
//		txtDiaChi.setEditable(false);
//		panel.add(txtDiaChi);
		
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setBounds(505, 161, 46, 14);
//		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Lịch làm việc");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(665, 176, 232, 200);
		panel.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 300, 1528, 485);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.getViewport().setBackground(Color.WHITE);

		String[] colHeader = { "Tên Công Trình", "Công Việc", "Ngày Thực Hiện", "Ngày Hoàn thành", "Địa Chỉ" };
		modelLich = new DefaultTableModel(colHeader, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableLich = new JTable(modelLich);
		tableLich.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		tableLich.setBounds(0, 263, 894, 308);
		JTableHeader h = tableLich.getTableHeader();
		h.setFont(new Font("Arial", Font.BOLD, 13));
		h.setForeground(new Color(255, 255, 255));
		h.setBackground(new Color(146, 200, 240));
		
		tableLich.setRowHeight(tableLich.getRowHeight() + 20);

		tableLich.getColumnModel().getColumn(0).setPreferredWidth(280);
		tableLich.getColumnModel().getColumn(1).setPreferredWidth(40);
		tableLich.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableLich.getColumnModel().getColumn(3).setPreferredWidth(40);
		tableLich.getColumnModel().getColumn(4).setPreferredWidth(300);


		scrollPane.setViewportView(tableLich);

		
		
//		for (CongTrinh congTrinh : listCT) {
//			String tenCongTrinh = congTrinh.getTenCongTrinh();
//			DiaDiem dd = congTrinh.getDiaDiem();
//			String Dia_diem = dd.getPhuongXa() + " " + dd.getQuanHuyen() + " " + dd.getTinhTP();
//			listChiTiet = chiTietDAO.timDanhSachCongViecDangLam(ld.getMaLaoDong(), congTrinh.getMaCongTrinh());
//			for (ChiTietCV chiTiet : listChiTiet) {
//				String tenCV = new QLCongViec_DAO().getCongViec(chiTiet.getCongViec().getMaCongViec()).getTenCongViec();
//				modelLich.addRow(new Object[] { tenCongTrinh, tenCV,
//						df.format(chiTiet.getNgayThucHien()), df.format(chiTiet.getNgayHoanThanh()), Dia_diem });
//			}
//		}

//		setLocationRelativeTo(null);
		btnDMK.addActionListener(this);
		btnDX.addActionListener(this);
		
		
		
		ChamCongNVHC_DAO dlieu = new ChamCongNVHC_DAO();
        PhongBan_DAO pbDao = new PhongBan_DAO();
        DuLieuCongNhan_DAO cnDao = new DuLieuCongNhan_DAO();
        NhanVien_DAO nvDao = new NhanVien_DAO();
        lblTen.setText(nv.getTenNV());
        lblMSNV.setText(nv.getMaNV());
        if(nv.isGioiTinh() == false)
        {
        	gioiTinh.setText("Nữ");
        }
        else {
        	gioiTinh.setText("Nam");
        }
        int sdt = nv.getSDT();
        String s = String.valueOf(sdt);
        lblChucVu1.setText(s);
        int cmnd2 = nv.getCMND();
        String c = String.valueOf(cmnd2);
        lblCMND.setText(c);
        
        PhongBan pb =pbDao.getPBma(nv.getPhongban().getMaPB());

        String tenPB = pb.getTenPB();
        
//        NhanVien nv = nvDao.getNVTheoMaPB(maPb,nv.getMaNV() );
        phongban.setText(tenPB);
        
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date ngaySinh2 = nv.getNgaySinh();
        String nsinh = df.format(ngaySinh2);
        ngaySinh.setText(nsinh);
        
        Date ngayVaoLam2 = nv.getNgayVaolam();
        String nvl = df.format(ngayVaoLam2);
        ngayVaolam.setText(nvl);
        String maPb = pb.getMaPB();
       
        
    	DefaultTableModel dataCongNhan = new DefaultTableModel(new String[] { "Tên nhân viên", "Công đoạn",
    			"Sản phẩm", "Sản phẩm thực tế","Phụ Cấp","Thưởng","Lương làm thêm", "Thời gian", "Thu nhập khác","Tổng lương" ,"Tình Trạng"}, 0);
    	
    	DefaultTableModel dataNhanVien = new DefaultTableModel(
    			new String[] { "Tên nhân viên", "Hệ số lương", "Nghĩ không phép", "Công ngày lễ",
    					"Công ngày thường","Phụ cấp","Thưởng","Lương làm thêm", "Thời gian", "Số Công Chuẩn", "Thu Nhập Khác","Tổng lương","Tình trạng" },
    			0);
        if(maPb.equals("HC")) {
        	List<LuongNhanVien> ds = dlieu.getLuongNhanVien(nv.getMaNV());
        	
    		for (int i = 0; i < ds.size(); i++) {
    			dataNhanVien.addRow(new Object[] {ds.get(i).getTenNV(),tach(ds.get(i).getHeSoLuong()),ds.get(i).getNghiKhongPhep(),ds.get(i).getCongNgayLe(),
    					ds.get(i).getCongNgayThuong(),tach(ds.get(i).getPhuCap()),tach(ds.get(i).getThuong()),tach(ds.get(i).getLuongLamThem()),ds.get(i).getThang()
    					+" - "+ds.get(i).getNam(),ds.get(i).getSoCongChuan(),tach(ds.get(i).getThuNhapKhac()),tach(ds.get(i).getTongLuong()),ds.get(i).isTinhTrang() ? "Đã thanh toán":"Chưa thanh toán"});
    		}
    		tableLich.setModel(dataNhanVien);
    		tableLich.getTableHeader().setBackground(new Color(146, 200, 240));
    		tableLich.setFont(new Font("Times New Roman", Font.PLAIN, 16));
    		tableLich.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    		tableLich.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    		tableLich.getColumnModel().getColumn(0).setPreferredWidth(200);
    		tableLich.getColumnModel().getColumn(1).setPreferredWidth(100);
    		tableLich.getColumnModel().getColumn(2).setPreferredWidth(120);
    		tableLich.getColumnModel().getColumn(3).setPreferredWidth(120);
    		tableLich.getColumnModel().getColumn(4).setPreferredWidth(120);
    		tableLich.getColumnModel().getColumn(5).setPreferredWidth(100);
    		tableLich.getColumnModel().getColumn(6).setPreferredWidth(100);
    		tableLich.getColumnModel().getColumn(7).setPreferredWidth(120);
    		tableLich.getColumnModel().getColumn(8).setPreferredWidth(80);
    		tableLich.getColumnModel().getColumn(9).setPreferredWidth(100);
    		tableLich.getColumnModel().getColumn(10).setPreferredWidth(100);
    		tableLich.getColumnModel().getColumn(11).setPreferredWidth(150);
    		tableLich.getColumnModel().getColumn(12).setPreferredWidth(115);
    		DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
    		rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
    		DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
    		centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    		tableLich.getColumnModel().getColumn(1).setCellRenderer(centerRendererCN);
    		tableLich.getColumnModel().getColumn(2).setCellRenderer(centerRendererCN);
    		tableLich.getColumnModel().getColumn(3).setCellRenderer(centerRendererCN);
    		tableLich.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
    		tableLich.getColumnModel().getColumn(5).setCellRenderer(rightRendererCN);
    		tableLich.getColumnModel().getColumn(6).setCellRenderer(rightRendererCN);
    		tableLich.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
    		tableLich.getColumnModel().getColumn(8).setCellRenderer(centerRendererCN);
    		tableLich.getColumnModel().getColumn(9).setCellRenderer(centerRendererCN);
    		tableLich.getColumnModel().getColumn(10).setCellRenderer(rightRendererCN);
    		tableLich.getColumnModel().getColumn(11).setCellRenderer(rightRendererCN);
        }
        else {
        	List<LuongCongNhan> dss = dlieu.getLuongCongNhan(nv.getMaNV());
    		for (int i = 0; i < dss.size(); i++) {
    			dataCongNhan.addRow(new Object[] { dss.get(i).getTenNV(),dss.get(i).getTenCongDoan(),dss.get(i).getTenSP(),
    					dss.get(i).getSoluong(),tach(dss.get(i).getPhuCap()),tach(dss.get(i).getThuong()),tach(dss.get(i).getLuongLamThem())
    					,dss.get(i).getThang()+" - "+dss.get(i).getNam(),tach(dss.get(i).getThuNhapKhac()),tach(dss.get(i).getTongLuong())
    					,dss.get(i).isTinhTrang() ? "Đã thanh toán":"Chưa thanh toán"});
    		}
    		tableLich.setModel(dataCongNhan);
    		tableLich.setModel(dataCongNhan);
    		tableLich.getTableHeader().setBackground(new Color(146, 200, 240));
    		tableLich.setFont(new Font("Times New Roman", Font.PLAIN, 16));
    		tableLich.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    		tableLich.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    		tableLich.getColumnModel().getColumn(0).setPreferredWidth(250);
    		tableLich.getColumnModel().getColumn(1).setPreferredWidth(120);
    		tableLich.getColumnModel().getColumn(2).setPreferredWidth(120);
    		tableLich.getColumnModel().getColumn(3).setPreferredWidth(120);
    		tableLich.getColumnModel().getColumn(4).setPreferredWidth(120);
    		tableLich.getColumnModel().getColumn(5).setPreferredWidth(120);
    		tableLich.getColumnModel().getColumn(6).setPreferredWidth(120);
    		tableLich.getColumnModel().getColumn(7).setPreferredWidth(100);
    		tableLich.getColumnModel().getColumn(8).setPreferredWidth(130);
    		tableLich.getColumnModel().getColumn(9).setPreferredWidth(175);
    		tableLich.getColumnModel().getColumn(10).setPreferredWidth(150);
    		DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
    		rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
    		DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
    		centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    		tableLich.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
    		tableLich.getColumnModel().getColumn(4).setCellRenderer(rightRendererCN);
    		tableLich.getColumnModel().getColumn(5).setCellRenderer(rightRendererCN);
    		tableLich.getColumnModel().getColumn(6).setCellRenderer(rightRendererCN);
    		tableLich.getColumnModel().getColumn(7).setCellRenderer(centerRendererCN);
    		tableLich.getColumnModel().getColumn(8).setCellRenderer(rightRendererCN);
    		tableLich.getColumnModel().getColumn(9).setCellRenderer(rightRendererCN);
    		
//    		btnDX.addActionListener(this);
	}
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnDX)) {
			int yn = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất", "Thông báo", JOptionPane.YES_NO_OPTION);
			if(yn== JOptionPane.YES_OPTION) {
				setVisible(false);
				new Login_GUI().setVisible(true);
			}
			
		}
		if(o.equals(btnDMK)) {
			TaiKhoan_Dao tk = new TaiKhoan_Dao();
			new ChangePassWord(tk.getTaiKhoanTheoMa(lblMSNV.getText())).setVisible(true);
		}
		
	}
	
	
	
	

}

