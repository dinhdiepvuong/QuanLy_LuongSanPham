package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connect;
import entity.SanPham;

public class SanPham_DAO {
	Connection con = new Connect().getConnect();
	public List<SanPham> getSPall()
	{	
		List ds = new ArrayList<SanPham>();
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from SanPham");
			
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				SanPham sp = new SanPham(r.getString("maSP"), r.getString("tenSP"),r.getString("moTa"),r.getString("mau"));
				ds.add(sp);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return ds;
		
	}
	public SanPham getSPCuoi() {
		SanPham sp = new SanPham();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select max(maSP) as maCuoi from SanPham where maSP like 'SP%' ");
			ResultSet r = p.executeQuery();
			r.next();
			sp = new SanPham(r.getString("maCuoi"));
		} catch (Exception e) {
		}
		return sp;
	}
	public boolean themSP(SanPham sp) {
		PreparedStatement p=null;
		try {
			p = con.prepareStatement("insert into SanPham values (?,?,?,?)");
			p.setString(1, sp.getMaSP());
			p.setString(2, sp.getTenSP());
			p.setString(3, sp.getMoTa());
			p.setString(4, sp.getMau());
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean XoaSP(String maSP)
	{
		PreparedStatement stmt =null;
		int n=0;
		try {
			stmt=con.prepareStatement("delete from SanPham where maSP=?");
			stmt.setString(1, maSP);
			n=stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean SuaSP(SanPham sp) {
		PreparedStatement p=null;
		int n=0;
		try {
			p = con.prepareStatement("update SanPham set tenSP=?,moTa=?,mau=? where maSP=?");
			p.setString(1, sp.getTenSP());
			p.setString(2, sp.getMoTa());
			p.setString(3, sp.getMau());
			p.setString(4, sp.getMaSP());
			n = p.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<SanPham> getmaSp(String maSP)
	{	
		List ds = new ArrayList<SanPham>();
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from SanPham where maSP=?");
			p.setString(1, maSP);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				
				SanPham sp = new SanPham(r.getString("maSP"), r.getString("tenSP"),r.getString("moTa"),r.getString("mau"));
				ds.add(sp);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return ds;
		
	}
}
