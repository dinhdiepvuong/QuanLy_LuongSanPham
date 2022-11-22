package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.poi.extractor.MainExtractorFactory;

public class TroGiup extends JFrame implements TreeSelectionListener {
	
	private static final long serialVerionUID = 1L;
	private JPanel contentPane;
	private JTree tree;
	private JEditorPane edit;
	private DefaultMutableTreeNode root;
	
	private static int page = 0;
	
	private DefaultMutableTreeNode nodeQLNhanVien;
	private DefaultMutableTreeNode nodeQLThemNV;
	private DefaultMutableTreeNode nodeQLNghiViecNV;
	private DefaultMutableTreeNode nodeQLNCapNhatNV;
	
	
	private DefaultMutableTreeNode nodeQLSanPham;
	private DefaultMutableTreeNode nodeQLThemSP;
	private DefaultMutableTreeNode nodeQLCapNhatSP;
	private DefaultMutableTreeNode nodeQLXemCongDSP;
	private DefaultMutableTreeNode nodeQLThemCongDSP;
	private DefaultMutableTreeNode nodeQLTCapNhatCongDSP;
	
	
	private DefaultMutableTreeNode nodeDLTinhLuong;
	private DefaultMutableTreeNode nodeDLThemDL;
	private DefaultMutableTreeNode nodeDLCapNhatDL;
	private DefaultMutableTreeNode nodeDLImportDL;
	
	
	private DefaultMutableTreeNode nodeTinhLuong;
	private DefaultMutableTreeNode nodeTinhLuongTL;
	private DefaultMutableTreeNode nodeXuatFileTL;
	
	
	private DefaultMutableTreeNode nodeThongKe;
	private DefaultMutableTreeNode nodeNVDoiMK;
	private DefaultMutableTreeNode nodeNVXemTT;
	
	public TroGiup(int page) {
		setTitle("Hướng dẫn sử dụng phần mềm");
		setResizable(false);
		setBounds(100, 100, 1411, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon img = new ImageIcon(Login_GUI.class.getResource("/Photo for Design Form/ao.png"));
    	setIconImage(img.getImage());
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 251, 618);
		contentPane.add(panel);
		panel.setLayout(null);
		
		root = new DefaultMutableTreeNode("Hướng dẫn sử dụng");
		
		nodeQLNhanVien = new DefaultMutableTreeNode("Quản lý nhân viên");
		nodeQLThemNV = new DefaultMutableTreeNode("Thêm nhân viên");
		nodeQLNghiViecNV = new DefaultMutableTreeNode("Nghỉ việc");
		nodeQLNCapNhatNV = new DefaultMutableTreeNode("Cập nhật thông tin");
		
		nodeQLSanPham = new DefaultMutableTreeNode("Quản lý sản phẩm");
		nodeQLThemSP = new DefaultMutableTreeNode("Thêm sản phẩm");
		nodeQLCapNhatSP = new DefaultMutableTreeNode("Cập nhật sản phẩm");
		nodeQLThemCongDSP = new DefaultMutableTreeNode("Thêm công đoạn sản phẩm");
		nodeQLTCapNhatCongDSP = new DefaultMutableTreeNode("Cập nhật công đoạn");
		nodeQLXemCongDSP = new DefaultMutableTreeNode("Xem công đoạn");
		
		nodeDLTinhLuong = new DefaultMutableTreeNode("Dữ liệu tính lương");
		nodeDLThemDL = new DefaultMutableTreeNode("Thêm dữ liệu");
		nodeDLCapNhatDL = new DefaultMutableTreeNode("Cập nhật dữ liệu");
		nodeDLImportDL = new DefaultMutableTreeNode("Import dữ liệu");
		
		nodeTinhLuong = new DefaultMutableTreeNode("Tính lương");
		nodeTinhLuongTL = new DefaultMutableTreeNode("Tính lương nhân viên");
		nodeXuatFileTL = new DefaultMutableTreeNode("Xuất file tính lương");
		
		nodeThongKe = new DefaultMutableTreeNode("Thống kê");
		nodeNVDoiMK = new DefaultMutableTreeNode("Đổi mật khẩu");
		nodeNVXemTT = new DefaultMutableTreeNode("Xem thông tin");
		
		
		
		nodeQLNhanVien.add(nodeQLThemNV);
		nodeQLNhanVien.add(nodeQLNghiViecNV);
		nodeQLNhanVien.add(nodeQLNCapNhatNV);
		root.add(nodeQLNhanVien);
		
		nodeQLSanPham.add(nodeQLThemSP);
		nodeQLSanPham.add(nodeQLCapNhatSP);
		nodeQLSanPham.add(nodeQLThemCongDSP);
		nodeQLSanPham.add(nodeQLTCapNhatCongDSP);
		nodeQLSanPham.add(nodeQLXemCongDSP);
		root.add(nodeQLSanPham);
		
		nodeDLTinhLuong.add(nodeDLThemDL);
		nodeDLTinhLuong.add(nodeDLCapNhatDL);
		nodeDLTinhLuong.add(nodeDLImportDL);
		root.add(nodeDLTinhLuong);
		
		nodeTinhLuong.add(nodeTinhLuongTL);
		nodeTinhLuong.add(nodeXuatFileTL);
		root.add(nodeTinhLuong);
		
		root.add(nodeThongKe);
		root.add(nodeNVDoiMK);
		root.add(nodeNVXemTT);
		
		tree = new JTree(root);
		tree.setBounds(0, 45, 248, 500);
		panel.add(tree);
		
		JLabel lblNewLabel = new JLabel("Hướng dẫn sử dụng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 0, 248, 43);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(249, 0, 1046, 618);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		edit = new JEditorPane();
		edit.setEditable(false);

		JScrollPane sc = new JScrollPane(edit);
		panel_1.add(sc);

		edit.setContentType("text/html");
		edit.setBackground(Color.white);
		tree.addTreeSelectionListener(this);
		if(page == 0)
			tree.setSelectionPath(new TreePath(nodeQLThemNV.getPath()));
		else if(page == 1)
			tree.setSelectionPath(new TreePath(nodeQLThemSP.getPath()));
		else if(page == 2)
			tree.setSelectionPath(new TreePath(nodeDLThemDL.getPath()));
		else if(page == 3)
			tree.setSelectionPath(new TreePath(nodeTinhLuongTL.getPath()));
		else if(page == 4)
			tree.setSelectionPath(new TreePath(nodeThongKe.getPath()));
		else if(page == 5)
			tree.setSelectionPath(new TreePath(nodeNVDoiMK.getPath()));
		else
			tree.setSelectionPath(new TreePath(nodeNVXemTT.getPath()));
			
	}
	
	public static void main(String[] args) {
		new TroGiup(page).setVisible(true);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		String sa = e.getNewLeadSelectionPath().getLastPathComponent().toString();
		if (sa.equals("Thêm nhân viên")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 20px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 20px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Nhập thông tin của nhân viên cần thêm vào (Tên, phòng ban, giới tính, ngày sinh,"+ "\r\n"+ "ngày vào làm, số điện thoại, CMND)</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/themnv1.png")+"\">\r\n" + 
					"			<h2>Bước 2: Nhấn nút thêm</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/themnv2.png")+"\">\r\n" + 
					"			<h2>Bước 3: Bạn xác nhận lại</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/themnv3.PNG")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");

		}
		else if(sa.equals("Nghỉ việc")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn nhân viên nghỉ việc</h2>" +  
					"			<h2>Bước 2: Nhấn nút nghỉ việc</h2>" +  
					"			<h2>Bước 3: Bạn xác nhận lại</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/nghiviec1.PNG")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		}
		else if(sa.equals("Cập nhật thông tin")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn nhân viên cần cập nhật thông tin</h2>" + 
					"			<h2>Bước 2: Cập nhật lại thông tin nhân viên </h2>" + 
					"			<h2>Bước 3: Nhấn nút cập nhật</h2>" + 
					"			<h2>Bước 4: Xác nhận cập nhật</h2>" + 
					"			<img src=\""+TroGiup.class.getResource("/images/capnhat1.PNG")+"\">\r\n" + 
					"			<h2>Bước 5: Nhận thông báo cập nhật thành công</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/capnhat2.PNG")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		}
		else if(sa.equals("Thêm sản phẩm")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Nhập thông tin sản phẩm (Tên sản phẩm, màu, mô tả)</h2>" + 
					"			<h2>Bước 2: Nhấn nút thêm</h2>\r\n" + 
					"			<img src=\""+TroGiup.class.getResource("/images/themsp.PNG")+"\">\r\n" +
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		}
		else if(sa.equals("Thêm công đoạn sản phẩm")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Nhập thông tin của công đoạn sản phẩm (Tên công đoạn, đơn giá)</h2>" + 
					"			<h2>Bước 2: Nhấn nút thêm </h2>" + 
					"			<img src=\""+TroGiup.class.getResource("/images/themcd.PNG")+"\">\r\n" +
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		
		}
		else if(sa.equals("Cập nhật sản phẩm")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn sản phẩm cần cập nhật</h2>" + 
					"			<h2>Bước 2: Cập nhật lại thông tin sản phẩm (Tên sản phẩm, màu sắc, mô tả)</h2>" + 
					"			<h2>Bước 3: Nhấn nút cập nhật</h2>" + 
					"			<h2>Bước 4: Xác nhận cập nhật</h2>" + 
					"			<img src=\""+TroGiup.class.getResource("/images/capnhatsp1.PNG")+"\">\r\n" +
					"			<h2>Bước 5: Nhận thông báo cập nhật sản phẩm thành công</h2>" + 
					"			<img src=\""+TroGiup.class.getResource("/images/capnhatsp2.PNG")+"\">\r\n" +
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		}
		else if(sa.equals("Cập nhật công đoạn")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn công đoạn cần cập nhật thông tin</h2>" + 
					"			<h2>Bước 2: Cập nhật thông tin của công đoạn (Tên công đoạn, đơn giá)</h2>" + 
					"			<h2>Bước 3: Nhấn nút cập nhật</h2>" + 
					"			<img src=\""+TroGiup.class.getResource("/images/capnhatcd.PNG")+"\">\r\n" +
					"			<h2>Bước 4: Xác nhận cập nhật công đoạn của sản phẩm thành công</h2>" + 
					"			<img src=\""+TroGiup.class.getResource("/images/capnhatcd1.PNG")+"\">\r\n" +
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		
		
		}
		else if(sa.equals("Xem công đoạn")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn sản phẩm cần xem công đoạn</h2>" + 
					"			<h2>Bước 2: Nhấn nút công đoạn</h2>" + 
					"			<img src=\""+TroGiup.class.getResource("/images/xemcd.PNG")+"\">\r\n" +
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		}
		else if(sa.equals("Thêm dữ liệu")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn công nhân cần thêm dữ liệu tính lương</h2>" + 
					"			<h2>Bước 2: Thêm thông tin dữ liệu tính lương </h2>" + 
					"			<h2>Bước 3: Nhấn nút thêm </h2>" + 
					"			<img src=\""+TroGiup.class.getResource("/images/themdlcn.PNG")+"\">\r\n" +
					"			<h2>Bước 4: Xác nhận thêm thành công </h2>" + 
					"			<img src=\""+TroGiup.class.getResource("/images/themdlcn1.PNG")+"\">\r\n" +
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		}
		else if(sa.equals("Cập nhật dữ liệu")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Chú ý: Công nhân đã được tính lương hay đã nghỉ việc thì không thể cập nhật</h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/capnhatdlcn1.PNG")+"\">\r\n" +
					"			<h2>Bước 1: Chọn nhân viên cần cập nhật lương </h2>" + 
					"			<h2>Bước 2: Cập nhật lại thông tin nhân viên và thu nhập khác </h2>" + 
					"			<h2>Bước 3: Nhấn nút cập nhật </h2>" + 
					"			<h2>Bước 4: Xác nhận lại việc cập nhật thành công </h2>" + 
					"			<h2>Bước 4: Xác nhận thêm thành công </h2>" + 
					"			<img src=\""+TroGiup.class.getResource("/images/capnhatdlcn2.PNG")+"\">\r\n" +
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		}
		else if(sa.equals("Import dữ liệu")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Nhấn nút Import</h2>" +
					"			<h2>Bước 2: Cách 1(Bỏ đường dẫn muốn import ra vào file Path, sau đó nhấn Upload)</h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/import1.PNG")+"\">\r\n" + 
					"			<h2>Bước 2: Cách 2 (Nhấn Browser, sau đó chọn foder muốn import, đặt tên cho Filename và nhấn chọn Open) </h2>" + 
					"			<img src=\""+TroGiup.class.getResource("/images/import2.PNG")+"\">\r\n" +
					"			<h2></h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/import3.PNG")+"\">\r\n" +
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		}
		else if(sa.equals("Tính lương nhân viên")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn nhân viên cần tính lương (trạng thái “chưa tính”)</h2>" +
					"			<h2>Bước 2: Nhập thời gian cần tính lương (Từ tháng/năm đến tháng/năm)</h2>" +
					"			<h2>Bước 3: Nhấn nút tính lương</h2>" +
					"			<h2>Bước 4: Xác nhận tính lương thành công</h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/tinhluongcn.PNG")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		}
		else if(sa.equals("Xuất file tính lương")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Nhấn nút Xuất File</h2>" +
					"			<h2>Bước 2: Chọn foder để xuất ra file, đặt tên filename và chọn nút Open</h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/xuatfiletlcn.PNG")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		
		}
		else if(sa.equals("Đổi mật khẩu")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Nhấn nút đổi mật khẩu</h2>" +
					"			<h2>Bước 2: Nhập thông tin của mật khẩu mới (nhập mật khẩu hiện tại, nhập mật khẩu mới, nhập lại mật khẩu mới)</h2>" +
					"			<h2>Bước 3: Nhấn chọn đồng ý</h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/doimatkhau.PNG")+"\">\r\n" + 
					"			<h2>Bước 4: Xác nhận lại việc đổi mật khẩu thành công</h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/xacnhandmk.PNG")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		}
		else if(sa.equals("Xem thông tin")) {
			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Để xem thông tin cần đăng nhập vào hệ thống</h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/dangnhap.PNG")+"\">\r\n" + 
					"			<h2> Xem thông tin cá nhân của nhân viên</h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/xemtt.PNG")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		
		}
		else if(sa.equals("Thống kê")) {

			edit.setText("<html>\r\n" + 
					"	<head>\r\n" + 
					"		<title></title>\r\n" + 
					"		<style type=\"text/css\">\r\n" + 
					"			div{\r\n" + 
					"				margin-left: 10px;\r\n" + 
					"			}\r\n" + 
					"			h2{\r\n" + 
					"				padding: 5px;\r\n" + 
					"			}\r\n" + 
					"		</style>\r\n" + 
					"	</head>\r\n" + 
					"	<body>\r\n" + 
					"		<div>\r\n" + 
					"			<h2>Bước 1: Chọn các tiêu chí cần thống kê</h2>" +
					"			<h2>Bước 2: Nhấn nút chọn file</h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/thongke1.PNG")+"\">\r\n" + 
					"			<h2>Bước 3: Chọn đường dẫn và nhập tên file</h2>" +
					"			<h2>Bước 4: Nhấn nút Save để lưu</h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/thongke2.PNG")+"\">\r\n" + 
					"			<h2>Bước 5: Xác nhận lại</h2>" +
					"			<img src=\""+TroGiup.class.getResource("/images/thongke3.PNG")+"\">\r\n" + 
					"		</div>\r\n" + 
					"	</body>\r\n" + 
					"</html>");
		
		
		}
		
	}

}
