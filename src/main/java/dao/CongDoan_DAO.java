package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connect;
import entity.CongDoan;
import entity.NhanVien;
import entity.PhongBan;
import entity.SanPham;

public class CongDoan_DAO {
	Connection con = new Connect().getConnect();
	public boolean themCD(CongDoan cd) {
		PreparedStatement p=null;
		try {
			p = con.prepareStatement("insert into CongDoan values (?,?,?,?)");
			p.setString(1, cd.getMaCongDoan());
			p.setString(2, cd.getTenCongDoan());
			p.setString(3, cd.getSanpham().getMaSP());
			p.setDouble(4, cd.getDonGia());
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean XoaCD(String maCD)
	{
		PreparedStatement stmt =null;
		int n=0;
		try {
			stmt=con.prepareStatement("delete from CongDoan where maCongDoan=?");
			stmt.setString(1, maCD);
			n=stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean SuaCD(CongDoan cd) {
		PreparedStatement p=null;
		int n=0;
		try {
			p = con.prepareStatement("update CongDoan set tenCongDoan=?,donGia=? where maCongDoan=?");
			
			p.setString(1, cd.getTenCongDoan());
			p.setDouble(2, cd.getDonGia());
			p.setString(3, cd.getMaCongDoan());
			n = p.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<CongDoan> getCDtheoma(String maSP)
	{	
		List<CongDoan> dscd = new ArrayList<CongDoan>();
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from CongDoan where maSanPham =?");
			p.setString(1, maSP);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				CongDoan cd = new CongDoan(r.getString("maCongDoan"),r.getString("tenCongDoan"),r.getDouble("donGia"));
				cd.setSanpham(new SanPham(maSP));
				dscd.add(cd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return dscd;
		
	}
	public CongDoan getCDCuoi() {
		CongDoan cd = new CongDoan();
		try {
			PreparedStatement p = con.prepareStatement("select max(maCongDoan) as maCuoi from CongDoan where maCongDoan like 'CD%' ");
			ResultSet r = p.executeQuery();
			r.next();
			cd = new CongDoan(r.getString("maCuoi"));
		} catch (Exception e) {
		}
		return cd;
	}
	public CongDoan getmaCongDoanTheoTenCD(String maSanPham , String tenCongDoan)
	{	
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("Select * From CongDoan where maSanPham like '"+maSanPham+"' and tenCongDoan like N'"+tenCongDoan+"'");
			ResultSet r=p.executeQuery();
			while(r.next())
			{
//				NhanVien nv = new NhanVien(r.getString("maNV"), r.getString("tenNV"),r.getDate("ngaySinh"), r.getDate("ngayVaolam"), r.getInt("CMND"),r.getInt("SDT"), r.getBoolean("gioiTinh"));
//				nv.setPhongban(new PhongBan(r.getString("maPB"), null));
				CongDoan cd = new CongDoan(r.getString("maCongDoan"), r.getString("tenCongDoan"), r.getDouble("donGia"));
				cd.setSanpham(new SanPham(r.getString("maSanPham")));
				
				return cd;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
	}
	
	public SanPham getSPtheoMaVaTen( String tenSP) {
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from SanPham where tenSP like N'"+tenSP+"'");
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				SanPham sp = new SanPham(r.getString("maSP"), r.getString("maSP"), r.getString("moTa"), r.getString("mau"));
				return sp;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
	}
	
	public CongDoan getCDTheoMaSpvaTenCD(String maSanPham , String tenCongDoan)
	{	
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("Select * From SanPham sp join [dbo].[CongDoan] cd on sp.maSP = cd.maSanPham where maSanPham ='"+maSanPham+"' and tenCongDoan = N'"+tenCongDoan+"'");
			ResultSet r=p.executeQuery();
			while(r.next())
			{
//				NhanVien nv = new NhanVien(r.getString("maNV"), r.getString("tenNV"),r.getDate("ngaySinh"), r.getDate("ngayVaolam"), r.getInt("CMND"),r.getInt("SDT"), r.getBoolean("gioiTinh"));
//				nv.setPhongban(new PhongBan(r.getString("maPB"), null));
				CongDoan cd = new CongDoan(r.getString("maCongDoan"), r.getString("tenCongDoan"), r.getDouble("donGia"));
				cd.setSanpham(new SanPham(r.getString("maSanPham"),null));
				
				return cd;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
	}
}
