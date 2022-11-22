package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import dao.CongDoan_DAO;
import dao.SanPham_DAO;
import entity.CongDoan;
import entity.SanPham;

public class CongDoan_GUI extends JFrame implements ActionListener,MouseListener{
	
	
	
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblMacd;
	private JLabel lblMasp;
	private JTextField txtmasp;
	private JTextField txtmacd;
	private JLabel lblTen;
	private JLabel lblDG;
	private JTextField txtTen;
	private JTextField txtDG;
	private JLabel lblTieude;
	private JTable table;
	private JButton jbnThem;
	private JButton jbnXoa;
	private JButton jbnCapnhat;
	private DefaultTableModel model;
	CongDoan_DAO cdDao = new CongDoan_DAO();



	public CongDoan_GUI(SanPham sanpham)
	{	setBounds(0,0,700,500);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		lblMacd = new JLabel("Mã công đoạn");
		lblMacd.setBounds(20, 90, 100, 25);
		lblMasp = new JLabel("Mã sản phẩm");
		lblMasp.setBounds(20, 50, 100, 25);
		txtmasp = new JTextField();
		txtmasp.setBounds(140,50,150,25);
		txtmacd = new JTextField();
		txtmacd.setBounds(140,90,150,25);
		
		lblTen = new JLabel("Tên công đoạn");
		lblTen.setBounds(360, 50, 100, 25);
		
		txtTen = new JTextField();
		txtTen.setBounds(490,50,150,25);
		
		lblDG = new JLabel("Đơn giá");
		lblDG.setBounds(360, 90, 100, 25);
		
		txtDG = new JTextField();
		txtDG.setBounds(490,90,150,25);
		
		jbnThem = new JButton("Thêm");
		jbnThem.setBounds(250,405,85,30);
		jbnThem.setBackground(new Color(228, 237, 225));
		jbnThem.setFocusPainted(false);
		panel.add(jbnThem);
		
		jbnXoa = new JButton("Xóa");
		jbnXoa.setBounds(310,405,85,30);
		jbnXoa.setBackground(new Color(228, 237, 225));
		jbnXoa.setFocusPainted(false);
//		panel.add(jbnXoa);
		
		jbnCapnhat = new JButton("Cập nhật");
		jbnCapnhat.setBounds(370,405,85,30);
		jbnCapnhat.setBackground(new Color(228, 237, 225));
		jbnCapnhat.setFocusPainted(false);
		panel.add(jbnCapnhat);
		
		
		
		lblTieude = new JLabel("Công đoạn sản phẩm");
		lblTieude.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTieude.setBounds(270,5,500,25);
		Object columns[]= {"Mã sản phẩm","Mã công đoạn","Tên công đoạn","Đơn giá"};
		model = new DefaultTableModel(columns,0);
		CongDoan_DAO cddao = new CongDoan_DAO();
		List<CongDoan> dscd = cddao.getCDtheoma(sanpham.getMaSP());
		for(int i=0;i<dscd.size();i++)
		{
			model.addRow(new Object [] { dscd.get(i).getSanpham().getMaSP(),dscd.get(i).getMaCongDoan(),dscd.get(i).getTenCongDoan(),dscd.get(i).getDonGia() });
		}
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		
//		table.setBounds(5,150,670,300);
		sp.setBounds(5,150,670,240);
		
		panel.add(lblMacd);
		panel.add(lblMasp);
		panel.add(txtmasp);
		panel.add(txtmacd);
		panel.add(lblTen);
		panel.add(lblDG);
		panel.add(txtTen);
		panel.add(txtDG);
		panel.add(lblTieude);
		panel.add(sp);

		jbnThem.addActionListener(this);
		jbnXoa.addActionListener(this);
		jbnCapnhat.addActionListener(this);
		table.addMouseListener(this);
		txtmasp.setText(sanpham.getMaSP());
		txtmasp.setEnabled(false);
		txtmacd.setEnabled(false);
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(jbnThem)) {
			if(txtTen.getText().equals("") || txtDG.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"Vui lòng nhập dữ liệu công đoạn");
			}
			else if(ktraten()&&ktradongia()){
			String macd,ten;
			macd = taoMaCongDoan();
			ten = txtTen.getText();
			Float donGia = Float.parseFloat(txtDG.getText());
			SanPham_DAO spDao = new SanPham_DAO();
			SanPham sp = spDao.getmaSp(txtmasp.getText()).get(0);
			CongDoan cd = new CongDoan(macd, ten, donGia);
			cd.setSanpham(sp);
			CongDoan_DAO cdDao = new CongDoan_DAO();
			cdDao.themCD(cd);
			model.addRow(new Object [] {cd.getSanpham().getMaSP(),cd.getMaCongDoan(),cd.getTenCongDoan(),cd.getDonGia() });
			JOptionPane.showMessageDialog(null,"Thêm công đoạn thành công");
			}
		}
		else if(obj.equals(jbnXoa)) {
			if(table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null,"Vui lòng chọn công đoạn cần xóa");
			}
			else {
			int result = JOptionPane.showConfirmDialog(this,"Xác nhận xóa ?", "Xóa công đoạn",
		               JOptionPane.YES_NO_OPTION);
			CongDoan_DAO cd_Dao = new CongDoan_DAO();
		    if(result == JOptionPane.YES_OPTION)
			{
			int row =table.getSelectedRow();
			String ma= table.getValueAt(row,1).toString();
			if(cd_Dao.XoaCD(ma))
			{
				model.removeRow(row);
				txtmacd.setText(null);
				txtTen.setText(null);
				txtDG.setText(null);
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			}
			}}
		}
		else if(obj.equals(jbnCapnhat)) {
			int row = table.getSelectedRow();
			String macd,ten;
			macd = txtmacd.getText();
			ten = txtTen.getText();
			Float donGia = Float.parseFloat(txtDG.getText());
			if(table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null,"Vui lòng chọn công đoạn cần cập nhật");
			}
			else if(txtTen.getText().equals("") || txtDG.getText().equals("")) 
				JOptionPane.showMessageDialog(null,"Vui lòng nhập dữ liệu công đoạn");
			else if(ktraten()&&ktradongia()){
				int result = JOptionPane.showConfirmDialog(this,"Xác nhận cập nhật ?", "Cập nhật sản phẩm",
			               JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION){
			SanPham_DAO spDao = new SanPham_DAO();
			SanPham sp = spDao.getmaSp(txtmasp.getText()).get(0);
			CongDoan cd = new CongDoan(macd, ten, donGia);
			cd.setSanpham(sp);
			if(cdDao.SuaCD(cd)) {
				model.setValueAt(txtTen.getText(), row, 2);
				model.setValueAt(txtDG.getText(), row, 3);
				JOptionPane.showMessageDialog(null, "Cập nhật công đoạn thành công");
				}
			}
		}
	}}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtmacd.setText(table.getValueAt(row, 1).toString());
		txtTen.setText(table.getValueAt(row, 2).toString());
		txtDG.setText(table.getValueAt(row, 3).toString());
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public String taoMaCongDoan() {
		String maCuoi = cdDao.getCDCuoi().getMaCongDoan();
		long so = Long.parseLong(maCuoi.substring(2)) + 1000 + 1;
		String soMoi = ("" + so).substring(1);
		return "CD" + soMoi;
	}
	public boolean ktraten()
	{
		String reg="([AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]*)(\\s[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]*)*";
		if(!txtTen.getText().matches(reg))
		{
			JOptionPane.showMessageDialog(this,"Tên công đoạn viết hoa chữ đầu");
			return false;
		}return true;
	}
	public boolean ktradongia()
	{
		String reg="[0-9]*";
		if(!txtDG.getText().matches(reg))
		{
			JOptionPane.showMessageDialog(this,"Đơn giá là chữ số");
			return false;
		}return true;
	}

}
