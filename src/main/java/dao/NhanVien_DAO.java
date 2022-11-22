package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connect;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVien_DAO {
	Connection con = new Connect().getConnect();
	public NhanVien getNVtheoma(String username)
	{	
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from NhanVien where userName =?");
			p.setString(1, username);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				NhanVien nv = new NhanVien(r.getString("maNV"), r.getString("tenNV"),r.getDate("ngaySinh"), r.getDate("ngayVaolam"), r.getInt("CMND"),r.getInt("SDT"), r.getBoolean("gioiTinh"));
				nv.setPhongban(new PhongBan(r.getString("maPB"), null));
				return nv;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
		
	}
	public List<NhanVien> getNVall()
	{	
		List ds = new ArrayList<NhanVien>();
		PhongBan_DAO pbdao = new PhongBan_DAO();
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from NhanVien");
			
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				PhongBan pb = pbdao.getPBma(r.getString("maPB"));
				NhanVien nv = new NhanVien(r.getString("maNV"), r.getString("tenNV"),r.getDate("ngaySinh"), r.getDate("ngayVaolam"), r.getInt("CMND"),r.getInt("SDT"), r.getBoolean("gioiTinh"));
				nv.setPhongban(new PhongBan(r.getString("maPB"), pb.getTenPB()));
				ds.add(nv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return ds;
		
	}
	public NhanVien getNVCuoiLD() {
		NhanVien nv = new NhanVien();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select max(maNV) as maCuoi from NhanVien where maNV like 'LD%' ");
			ResultSet r = p.executeQuery();
			r.next();
			nv = new NhanVien(r.getString("maCuoi"));
		} catch (Exception e) {
		}
		return nv;
	}
	public NhanVien getNVCuoiNV() {
		NhanVien nv = new NhanVien();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select max(maNV) as maCuoi from NhanVien where maNV like 'NV%' ");
			ResultSet r = p.executeQuery();
			r.next();
			nv = new NhanVien(r.getString("maCuoi"));
		} catch (Exception e) {
		}
		return nv;
	}
	
	public boolean themNV(NhanVien nv) {
		PreparedStatement p=null;
		try {
			p = con.prepareStatement("insert into NhanVien values (?,?,?,?,?,?,?,?,?,?,?)");
			p.setString(1, nv.getMaNV());
			p.setString(2, nv.getTenNV());
			p.setDate(3, nv.getNgaySinh());
			p.setDate(4, nv.getNgayVaolam());
			p.setString(5, nv.getPhongban().getMaPB());
			p.setString(6, nv.getTaikhoan().getUsername());
			p.setInt(7, nv.getCMND());
			p.setInt(8, nv.getSDT());
			p.setBoolean(9, false); //0
			p.setBoolean(10, true); //1
			p.setBoolean(11, nv.isGioiTinh());
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean capNhatTrangThai(String maNV) {
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("update NhanVien set trangThai = 0 where maNV = ?");
			p.setString(1, maNV);
			p.execute();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public List<NhanVien> getNVDangLam()
	{	
		List ds = new ArrayList<NhanVien>();
		PhongBan_DAO pbdao = new PhongBan_DAO();
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from NhanVien where trangThai = 1");
			
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				PhongBan pb = pbdao.getPBma(r.getString("maPB"));
				NhanVien nv = new NhanVien(r.getString("maNV"), r.getString("tenNV"),r.getDate("ngaySinh"), r.getDate("ngayVaolam"), r.getInt("CMND"),r.getInt("SDT"), r.getBoolean("gioiTinh"));
				nv.setPhongban(new PhongBan(r.getString("maPB"), pb.getTenPB()));
				ds.add(nv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return ds;
		
	}
	public List<NhanVien> getNVNghiviec()
	{	
		List ds = new ArrayList<NhanVien>();
		PhongBan_DAO pbdao = new PhongBan_DAO();
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from NhanVien where trangThai = 0");
			
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				PhongBan pb = pbdao.getPBma(r.getString("maPB"));
				NhanVien nv = new NhanVien(r.getString("maNV"), r.getString("tenNV"),r.getDate("ngaySinh"), r.getDate("ngayVaolam"), r.getInt("CMND"),r.getInt("SDT"), r.getBoolean("gioiTinh"));
				nv.setPhongban(new PhongBan(r.getString("maPB"), pb.getTenPB()));
				ds.add(nv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return ds;
		
	}
	
	public boolean SuaNV(NhanVien nv) {
		PreparedStatement p=null;
		int n=0;
		try {
			p = con.prepareStatement("update NhanVien set tenNV=?,ngaySinh=?,ngayVaolam=?,maPB=?,CMND=?,SDT=?,gioiTinh=? where maNV=?");
			p.setString(1, nv.getTenNV());
			p.setDate(2, nv.getNgaySinh());
			p.setDate(3, nv.getNgayVaolam());
			p.setString(4, nv.getPhongban().getMaPB());
			p.setInt(5, nv.getCMND());
			p.setInt(6, nv.getSDT());
			p.setBoolean(7, nv.isGioiTinh());
			p.setString(8, nv.getMaNV());
			n = p.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<NhanVien> getLCN()
	{	
		List ds = new ArrayList<NhanVien>();
		PhongBan_DAO pbdao = new PhongBan_DAO();
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from NhanVien where maPB = N'LD' and trangThai = N'1' ");
			
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				PhongBan pb = pbdao.getPBma(r.getString("maPB"));
				NhanVien nv = new NhanVien(r.getString("maNV"), r.getString("tenNV"),r.getDate("ngaySinh"), r.getDate("ngayVaolam"), r.getInt("CMND"),r.getInt("SDT"), r.getBoolean("gioiTinh"));
				nv.setPhongban(new PhongBan(r.getString("maPB"), pb.getTenPB()));
				ds.add(nv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return ds;
		
	}
	public List<NhanVien> getLNV()
	{	
		List ds = new ArrayList<NhanVien>();
		PhongBan_DAO pbdao = new PhongBan_DAO();
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from NhanVien where maPB = N'HC' and trangThai = N'1' ");
			
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				PhongBan pb = pbdao.getPBma(r.getString("maPB"));
				NhanVien nv = new NhanVien(r.getString("maNV"), r.getString("tenNV"),r.getDate("ngaySinh"), r.getDate("ngayVaolam"), r.getInt("CMND"),r.getInt("SDT"), r.getBoolean("gioiTinh"));
				nv.setPhongban(new PhongBan(r.getString("maPB"), pb.getTenPB()));
				ds.add(nv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return ds;
		
	}
	public List<NhanVien> getTimNVtheoma(String maNV)
	{	
		List ds = new ArrayList<NhanVien>();
		PhongBan_DAO pbdao = new PhongBan_DAO();
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from NhanVien where (maNV =? or maNV like N'%"+maNV+"%'or tenNV like N'%"+maNV+"%' or CMND like '%"+maNV+"%') and trangThai = N'1'");
			p.setString(1, maNV);
		
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				PhongBan pb = pbdao.getPBma(r.getString("maPB"));
				NhanVien nv = new NhanVien(r.getString("maNV"), r.getString("tenNV"),r.getDate("ngaySinh"), r.getDate("ngayVaolam"), r.getInt("CMND"),r.getInt("SDT"), r.getBoolean("gioiTinh"));
				nv.setPhongban(new PhongBan(r.getString("maPB"), pb.getTenPB()));
				ds.add(nv);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return ds;
		
	}
}
