package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.FileChooserUI;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.ThongKeDS_DAO;
import entity.LuongTK;

public class ThongKeDS_GUI extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnXuatFile;
	private JButton btnLamMoi;			
	private JComboBox<String> cboTKThangQuy;	
	private JPanel pnlBieuDo;	
	private ButtonGroup group;
	private JComboBox<String> cboNam;
	private JRadioButton rdbTatCa;
	private JRadioButton rdbQuy;
	private JRadioButton rdbThang;
	private JLabel lblTongSo;
	private JComboBox<String> cboTKTheo;
	private JLabel lblTongLuong;
	private JButton btnTongLuong;
	
	private DefaultTableModel modelDS;
	private JTable tbDanhSach;
	private boolean kt = true;
	private ArrayList<LuongTK> listTK;
	private ThongKeDS_DAO thongkeDAO = new ThongKeDS_DAO();
	
	public ThongKeDS_GUI() {
		listTK = new ArrayList<LuongTK>();
		
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		pnlBieuDo = new JPanel();
		pnlBieuDo.setBackground(Color.WHITE);
		pnlBieuDo.setBounds(204, 10, 1200, 535);
		add(pnlBieuDo);
		pnlBieuDo.setLayout(null);
		pnlBieuDo.setBorder(BorderFactory.createTitledBorder(""));
		
		JLabel lblTitle = new JLabel("<HTML><I>Danh sách thống kê lương</I><HTML>");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(460, 15, 250, 20);
		pnlBieuDo.add(lblTitle);

		JLabel lblNam = new JLabel("Năm:");
		lblNam.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNam.setBounds(36, 50, 117, 24);
		pnlBieuDo.add(lblNam);
		
		cboNam = new JComboBox<String>();
		cboNam.setBounds(80, 50, 117, 24);
		pnlBieuDo.add(cboNam);
		cboNam.addItem("");
		for(int i = LocalDateTime.now().getYear(); i>= LocalDateTime.now().getYear() - 4; i--) {
			cboNam.addItem("" +i);
		}
		
		rdbTatCa = new JRadioButton("");
		rdbTatCa.setBackground(Color.WHITE);
		rdbTatCa.setSelected(true);
		rdbTatCa.setBounds(325, 50, 20, 20);
		pnlBieuDo.add(rdbTatCa);
		
		
		JLabel lblTatCa = new JLabel("Tất cả");
		lblTatCa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTatCa.setBounds(353, 48, 117, 24);
		pnlBieuDo.add(lblTatCa);
		
		rdbQuy = new JRadioButton("");
		rdbQuy.setBackground(Color.WHITE);
		rdbQuy.setBounds(515, 50,  20, 20);
		pnlBieuDo.add(rdbQuy);
		
		JLabel lblThang = new JLabel("Tháng");
		lblThang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThang.setBounds(734, 48, 117, 24);
		pnlBieuDo.add(lblThang);

		rdbThang = new JRadioButton("");
		rdbThang.setBackground(Color.WHITE);
		rdbThang.setBounds(706, 50, 20, 20);
		pnlBieuDo.add(rdbThang);
		
		group = new ButtonGroup();
		group.add(rdbQuy);
		group.add(rdbThang);
		group.add(rdbTatCa);
		
		JLabel lblQuy= new JLabel("Quý");
		lblQuy.setBounds(543, 48, 117, 24);
		pnlBieuDo.add(lblQuy);
		lblQuy.setFont(new Font("Tahoma", Font.BOLD, 12));

		cboTKThangQuy = new JComboBox<String>();
		cboTKThangQuy.setBounds(830, 50, 117, 24);
		pnlBieuDo.add(cboTKThangQuy);
		
		modelDS = new DefaultTableModel(new String[] {"Mã bảng lương", "Mã nhân viên", "Tên nhân viên",
				"Tháng", "Năm", "Lương làm thêm", "Phụ cấp", "Thưởng",  "Lương (VND)"}, 0) {
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
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(30, 105, 1000, 360);
		pnlBieuDo.add(scrollPane1);
		
		tbDanhSach = new JTable(modelDS);
		scrollPane1.setViewportView(tbDanhSach);
		
		tbDanhSach.getTableHeader().setBackground(new Color(146, 200, 240));
		tbDanhSach.getTableHeader().setForeground(new Color(255, 255, 255));
		tbDanhSach.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		
		tbDanhSach.getColumnModel().getColumn(0).setPreferredWidth(40);
		tbDanhSach.getColumnModel().getColumn(1).setPreferredWidth(35);
		tbDanhSach.getColumnModel().getColumn(2).setPreferredWidth(90);
		tbDanhSach.getColumnModel().getColumn(3).setPreferredWidth(15);
		tbDanhSach.getColumnModel().getColumn(4).setPreferredWidth(15);
		tbDanhSach.getColumnModel().getColumn(5).setPreferredWidth(50);
		tbDanhSach.getColumnModel().getColumn(6).setPreferredWidth(10);
		tbDanhSach.getColumnModel().getColumn(7).setPreferredWidth(15);
		tbDanhSach.getColumnModel().getColumn(8).setPreferredWidth(40);
		tbDanhSach.setRowHeight(tbDanhSach.getRowHeight()+15);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(39, 93, 1249, 8);
		pnlBieuDo.add(separator1);

		
		lblTongSo = new JLabel("Tổng số:");
		lblTongSo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongSo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSo.setBounds(20, 470, 88, 23);
		pnlBieuDo.add(lblTongSo);
		
		lblTongSo = new JLabel("0");
		lblTongSo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongSo.setBounds(103, 472, 57, 20);
		pnlBieuDo.add(lblTongSo);
		
		lblTongSo.setText(listTK.size()+ "");
		
		//-----------------

		JPanel pnlCN = new JPanel();
		pnlCN.setBackground(Color.WHITE);
		pnlCN.setBounds(10, 10, 180, 630);
		add(pnlCN);
		pnlCN.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		pnlCN.setLayout(null);
		
		
		JLabel lblTKTheo = new JLabel("Thống kê theo:");
		lblTKTheo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTKTheo.setBounds(20, 40, 138, 44);
		pnlCN.add(lblTKTheo);
		
		cboTKTheo = new JComboBox<String>();
		cboTKTheo.setBounds(20, 85, 138, 30);
		pnlCN.add(cboTKTheo);
		cboTKTheo.addItem("Tất cả");
		cboTKTheo.addItem("Công nhân");
		cboTKTheo.addItem("Nhân viên");
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBounds(20, 150, 138, 44);
		btnLamMoi.setFocusPainted(false);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLamMoi.setBackground(new Color(228, 237, 225));
		btnLamMoi.setIcon(new ImageIcon(ThongKeDS_GUI.class.getResource("/images/lam_moi.png")));
		pnlCN.add(btnLamMoi);
		
	
		btnXuatFile = new JButton("Xuất File");
		btnXuatFile.setBounds(20, 240, 138, 44);
		btnXuatFile.setFocusPainted(false);
		btnXuatFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXuatFile.setBackground(new Color(228, 237, 225));
		btnXuatFile.setIcon(new ImageIcon(ThongKeDS_GUI.class.getResource("/images/thong_ke.png")));
		pnlCN.add(btnXuatFile);
		
		btnTongLuong = new JButton("Tổng lương");
		btnTongLuong.setBounds(20, 330, 138, 44);
		btnTongLuong.setFocusPainted(false);
		btnTongLuong.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnTongLuong.setBackground(new Color(228, 237, 225));
		btnTongLuong.setIcon(new ImageIcon(ThongKeDS_GUI.class.getResource("/images/dollarTongLuong.png")));
		pnlCN.add(btnTongLuong);
		
		lblTongLuong = new JLabel("0.0");
		lblTongLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongLuong.setBounds(20, 390, 237, 44);
		pnlCN.add(lblTongLuong);
		
		

		rdbThang.addActionListener(this);
		rdbQuy.addActionListener(this);
		rdbTatCa.addActionListener(this);
		
		cboNam.addActionListener(this);
		cboTKTheo.addActionListener(this);
		cboTKThangQuy.addActionListener(this);
		
		btnLamMoi.addActionListener(this);
		btnXuatFile.addActionListener(this);
		btnTongLuong.addActionListener(this);
		
	}
	
	public void docDuLieuVaoModelDS() {
		for(LuongTK tk : listTK) {
			modelDS.addRow(new Object[] {
					tk.getMaBL(), tk.getMaNV(), tk.getTenNV(),
					tk.getThang(), tk.getNam(), tk.getLuongLamThem(),
					tk.getPhuCap(), tk.getThuong(), tk.getLuong()
			});
		}
	}
	
	public void xoaAllModel() {
		DefaultTableModel m = (DefaultTableModel) tbDanhSach.getModel();
		m.getDataVector().removeAllElements();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		if(o.equals(rdbQuy)) {
			kt = false;
			cboTKThangQuy.removeAllItems();
			for(int i = 1; i<=4; i++)
				cboTKThangQuy.addItem("" +i);
			if(cboNam.getSelectedIndex() == 0)
				listTK = new ArrayList<LuongTK>();
			else {
				int check;
				if(rdbTatCa.isSelected())
					check = 0;
				else if (rdbQuy.isSelected())
					check = 1;
				else
					check = 2;
				
				listTK = thongkeDAO.getAllBLuong(Integer.parseInt(cboNam.getSelectedItem().toString()),
						rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboTKThangQuy.getSelectedItem().toString()), check,
								cboTKTheo.getSelectedIndex());
				
			}
			xoaAllModel();
			docDuLieuVaoModelDS();
			modelDS.fireTableDataChanged();
			kt = true;
			
		} else if(o.equals(rdbThang)) {
			kt = false;
			cboTKThangQuy.removeAllItems();
			for(int i = 1; i<13; i++)
				cboTKThangQuy.addItem("" +i);
			if(cboNam.getSelectedIndex() == 0)
				listTK = new ArrayList<LuongTK>();
			else {
				int check;
				if(rdbTatCa.isSelected())
					check = 0;
				else if(rdbQuy.isSelected())
					check = 1;
				else
					check = 2;
				
				listTK = thongkeDAO.getAllBLuong(Integer.parseInt(cboNam.getSelectedItem().toString()),
						rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboTKThangQuy.getSelectedItem().toString()), check,
								cboTKTheo.getSelectedIndex());
				
			}
			
			xoaAllModel();
			docDuLieuVaoModelDS();
			modelDS.fireTableDataChanged();
			kt = true;
			
		} else if(o.equals(rdbTatCa)) {
			kt = false;
			cboTKThangQuy.removeAllItems();
			int check;
			if(rdbTatCa.isSelected())
				check = 0;
			else if(rdbQuy.isSelected())
				check = 1;
			else
				check = 2;
			if(cboNam.getSelectedIndex() == 0)
				listTK = new ArrayList<LuongTK>();
			else
				
				listTK = thongkeDAO.getAllBLuong(Integer.parseInt(cboNam.getSelectedItem().toString()),
						rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboTKThangQuy.getSelectedItem().toString()), check,
								cboTKTheo.getSelectedIndex());
			
			xoaAllModel();
			docDuLieuVaoModelDS();
			modelDS.fireTableDataChanged();
			lblTongSo.setText(""+ listTK.size());
			kt = true;
		} else if (o.equals(cboNam) || o.equals(cboTKTheo) || o.equals(cboTKThangQuy)) {
			if(kt) {
				if(cboNam.getSelectedIndex() == 0) {
					listTK = new ArrayList<LuongTK>();
				} else {
					int check;
					if(rdbTatCa.isSelected())
						check = 0;
					else if(rdbQuy.isSelected())
						check = 1;
					else
						check = 2;
					
					listTK = thongkeDAO.getAllBLuong(Integer.parseInt(cboNam.getSelectedItem().toString()),
							rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboTKThangQuy.getSelectedItem().toString()), check,
									cboTKTheo.getSelectedIndex());
				}
				
				setDataTable(listTK);
				xoaAllModel();
				docDuLieuVaoModelDS();
				modelDS.fireTableDataChanged();
				lblTongSo.setText("" +listTK.size());
			}
		}
		else if(o.equals(btnXuatFile)) {
			if(listTK.size() == 0) {
				JOptionPane.showConfirmDialog(null, "Chưa có nội dung để xuất file");
				return;
			}
			JFileChooser fileChooser = new JFileChooser();
			 
			fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
				
				@Override
				public String getDescription() {
					return "Excel file (*.xls, *xlsx)";
				}
				
				@Override
				public boolean accept(File f) {
					if(f.isDirectory()) {
						return true;
					} else {
						return f.getName().toLowerCase().endsWith(".xls") || f.getName().toLowerCase().endsWith(".xlsx");
					}
				}
			});
			int i = fileChooser.showSaveDialog(this);
			if(i == 0) {
				String path = fileChooser.getSelectedFile().getAbsolutePath();
				
				if(!path.matches("(.)+(\\.xls|\\.xlsx)$")) {
					path += ".xlsx";
				}
				int xacNhanLai = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất file Excel", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if(xacNhanLai == JOptionPane.YES_NO_OPTION) {
					boolean t = ghiRaFileExcel(path);
					if(t) {
						JOptionPane.showConfirmDialog(null, "Xuất file thành công");
						xacNhanLai = JOptionPane.showConfirmDialog(null, "Bạn có muốn xem file", "Thông báo", JOptionPane.YES_NO_OPTION);
						if(xacNhanLai == JOptionPane.YES_OPTION) 
							try {
								Desktop.getDesktop().open(new File(fileChooser.getSelectedFile().getParent()));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
					} else
						JOptionPane.showMessageDialog(null, "Không xuất được file");
				}
			}
			
			
		} else if(o.equals(btnLamMoi)) {
			lamMoi();
		} else if(o.equals(btnTongLuong)) {
			int numrow = tbDanhSach.getRowCount();
			double total = 0;
			for(int i=0; i< numrow; i++) {
				double val = Double.valueOf(tbDanhSach.getValueAt(i, 8).toString());
				total += val;
			}
			Locale localeVN = new Locale("vi", "VN");
			NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
			String str = currencyVN.format(total);
			
			lblTongLuong.setText(str);
		}
			
	}
	
	private boolean ghiRaFileExcel(String path) {
		Workbook workbook = new XSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sh = workbook.createSheet("Sheet1");
		String header[] = {"Mã bảng lương", "Mã nhân viên", "Tên nhân viên", "Tháng",
				"Năm", "Lương làm thêm", "Phụ cấp", "Thưởng", " Tổng lương"};
		Row rowHeader = sh.createRow(0);
		for(int i=0; i< header.length; i++) {
			Cell cell = rowHeader.createCell(i);
			cell.setCellValue(header[i]);
		}
		int numberRow = 1;
		for(LuongTK tk : listTK) {
			Row row = sh.createRow(numberRow++);
			row.createCell(0).setCellValue(tk.getMaBL());
			row.createCell(1).setCellValue(tk.getMaNV());
			row.createCell(2).setCellValue(tk.getTenNV());
			row.createCell(3).setCellValue(tk.getThang());
			row.createCell(4).setCellValue(tk.getNam());
			row.createCell(5).setCellValue(tk.getLuongLamThem());
			row.createCell(6).setCellValue(tk.getPhuCap());
			row.createCell(7).setCellValue(tk.getThuong());
			row.createCell(8).setCellValue(tk.getLuong());
		}
		for(int i = 0; i < header.length; i++)
			sh.autoSizeColumn(i);
		try {
			FileOutputStream f = new FileOutputStream(path);
			workbook.write(f);
			f.close();
			workbook.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void lamMoi() {
		cboNam.setSelectedIndex(0);
		tbDanhSach.clearSelection();
		lblTongSo.setText("0");
		lblTongLuong.setText("0.0");
		cboTKTheo.setSelectedIndex(0);
		
	}
	

	public void setDataTable(ArrayList<LuongTK> list) {
		java.util.List<LuongTK> salarys = new ArrayList<LuongTK>();
		
		salarys = list;
		DefaultTableModel tableModel;
		tbDanhSach.getModel();
		tableModel = (DefaultTableModel) tbDanhSach.getModel();
		tableModel.setRowCount(0);
		
		salarys.forEach(info->{
			String mabl = info.getMaBL();
			String manv = info.getMaNV();
			String ten = info.getTenNV();
			int thang = info.getThang();
			int nam = info.getNam();
			double luonglt = info.getLuongLamThem();
			double phu = info.getPhuCap();
			double thuong = info.getThuong();
			double luong = info.getLuong();
			
			tableModel.addRow(new Object[] {
					mabl, manv, ten, thang, nam, luonglt, phu, thuong, luong
			});
		});
		
	}
}
