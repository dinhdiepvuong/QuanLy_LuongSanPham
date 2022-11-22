package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class QuanLy_ThongKe extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JLabel label;
	private JPanel panel;
	private int page = 0;
	private int kt = 0;
	/**
	 * Create the frame 
	 */
	public QuanLy_ThongKe() {
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(100, 100, 1350, 750);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		add(contentPane);
		
		JLabel lblTieuDe = new JLabel("THỐNG KÊ LƯƠNG");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTieuDe.setBounds(600, 10, 200, 22);
		add(lblTieuDe);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 30, 1324, 600);
		tabbedPane.setBackground(new Color(255, 255, 255));
		add(tabbedPane);
		
		tabbedPane.addTab("Danh sách", new ImageIcon(QuanLy_ThongKe.class.getResource("/images/phan_cong.png")), new ThongKeDS_GUI(), "Hiển thị danh sách");
		tabbedPane.addTab("Biểu đồ", new ImageIcon(QuanLy_ThongKe.class.getResource("/images/thong_ke.png")), null, "Hiển thị biểu đồ");
		
		tabbedPane.addMouseListener(this);
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int index = tabbedPane.getSelectedIndex();
		page = index;
		if(index == 0 && kt != index)
			tabbedPane.insertTab("Danh sách", new ImageIcon(QuanLy_ThongKe.class.getResource("/images/phan_cong.png")), 
					new ThongKeDS_GUI(), "Hiển thị danh sách", index + 1);
		else if (index == 1 && kt != index)
			tabbedPane.insertTab("Biểu đô", new ImageIcon(QuanLy_ThongKe.class.getResource("/images/thong_ke.png")),
					new ThongKeBD_GUI(), "Hiển thị biểu đồ", index + 1);
		if(kt != index) {
			tabbedPane.remove(index);
			kt = index;
		}
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
	
}

























