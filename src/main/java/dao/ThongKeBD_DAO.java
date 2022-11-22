package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.Authenticator.Result;

import connect.Connect;
import entity.BangLuong;
import entity.NhanVien;
import entity.PhongBan;
import entity.ThongKe;
import entity.ThuNhapKhac;

public class ThongKeBD_DAO {
	public ArrayList<BangLuong> getAllBangLuong(int nam, int thoiGian, int thangHoacQuy, int loaiNV){
		ArrayList<BangLuong> list = new ArrayList<BangLuong>();
		Connection con = new Connect().getConnect();
		/**
		 * 0: Tất cả danh sách
		 * 1: Chọn tiêu chí Quý
		 * 2: Chọn tiêu chí Tháng
		 */
		

		
		String sql = "select * from BangLuong l join NhanVien n on l.maNV = n.maNV where";
		
		sql += " nam = " + nam + " and ";
		if(thangHoacQuy == 1) {
			if(thoiGian == 1)
				sql += " thang between 1 and 3 and ";
			else if(thoiGian == 2)
				sql += " thang between 4 and 6 and ";
			else if(thoiGian == 3)
				sql += " thang between 7 and 9 and ";
			else
				sql += " thang between 10 and 12 and ";
		} else if(thangHoacQuy == 2) 
			sql += " thang = " + thoiGian + " and ";
		if(loaiNV == 1)
			sql += " maPB = N'LD' ";
		else if (loaiNV == 2)
			sql += " maPB = N'HC' ";
		else
				sql = sql.substring(0, sql.length()-4);
		try {
			
			Statement stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString("maBangluong");
				String maNV = rs.getString("maNV");
				int thang = rs.getInt("thang");
				int tgnam= rs.getInt("nam");
				double luong = rs.getDouble("luong");
				String name= rs.getString("tenNV");
		
		        NhanVien nhanVien =new NhanVien();
		        nhanVien.setTenNV(name);
		        nhanVien.setMaNV(maNV);
			
				BangLuong b = new BangLuong();
				b.setLuong(luong);
				b.setNhanVien(nhanVien);
				b.setMaBL(ma);
				b.setNam(tgnam);
				b.setThang(thang);
	
				list.add(b);
				
				
			}
		} catch (Exception e) {
				// TODO: handle exception
		}
	return list;
		
	}
	
	public List<ThongKe> getBL(int nam)
	{
		Connection con = new Connect().getConnect();
		List<ThongKe> ds = new ArrayList<ThongKe>();
		PreparedStatement p;
		try {
			
			p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'LD%' group by thang");
			p.setInt(1, nam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{

				ThongKe tk = new ThongKe(r.getInt("thang"),r.getLong("Tongluong"));
				ds.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
	public List<ThongKe> getTNK(int nam)
	{
		Connection con = new Connect().getConnect();
		List<ThongKe> ds = new ArrayList<ThongKe>();
		PreparedStatement p;
		try {
			
			p = con.prepareStatement("select thang, tongTNK=Sum(ThuNhapKhac) from LuongCongNhan where nam = ? and tinhTrang=1 group by thang");
			p.setInt(1, nam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
//				ThuNhapKhac tnk = new ThuNhapKhac(r.getString("maThuNhapKhac"), r.getDouble("luongLamThem"),
//						r.getDouble("phuCap"),r.getDouble("thuong") , r.getInt("thang"), r.getInt("nam"));
//				NhanVien nv= nvdao.getNVtheoma(r.getString("maNV"));
//				ds.add(tnk);
				ThongKe tk = new ThongKe(r.getInt("thang"),r.getLong("tongTNK"));
				ds.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
	
	public List<ThongKe> getLuongTheoQuy(int nam,int quy)
	{
		Connection con = new Connect().getConnect();
		List<ThongKe> ds = new ArrayList<ThongKe>();
		PreparedStatement p = null;
		try {
			if(quy ==1)
			{
				p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'LD%' and thang>0 and thang <4 group by thang");
			}
			else if(quy ==2)
			{
				p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'LD%' and thang>3 and thang <7 group by thang");
			}
			else if(quy ==3)
			{
				p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'LD%' and thang>6 and thang <10 group by thang");
			}
			else if(quy ==4)
			{
				p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'LD%' and thang>9 and thang <13 group by thang");
			}
			else
			{
				p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'LD%'  group by thang");
			}
			
			p.setInt(1, nam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
//				ThuNhapKhac tnk = new ThuNhapKhac(r.getString("maThuNhapKhac"), r.getDouble("luongLamThem"),
//						r.getDouble("phuCap"),r.getDouble("thuong") , r.getInt("thang"), r.getInt("nam"));
//				NhanVien nv= nvdao.getNVtheoma(r.getString("maNV"));
//				ds.add(tnk);
				ThongKe tk = new ThongKe(r.getInt("thang"),r.getLong("Tongluong"));
				ds.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
	
	public List<ThongKe> getTNKTheoQuy(int nam,int quy)
	{
		Connection con = new Connect().getConnect();
		List<ThongKe> ds = new ArrayList<ThongKe>();
		PreparedStatement p = null;
		try {
			if(quy ==1)
			{
				p = con.prepareStatement("select thang,tongTNK=Sum(ThuNhapKhac) from LuongCongNhan where nam = ? and tinhTrang=1 and thang>0 and thang <4 group by thang");
			}
			else if(quy ==2)
			{
				p = con.prepareStatement("select thang,tongTNK=Sum(ThuNhapKhac) from LuongCongNhan where nam = ? and tinhTrang=1 and thang>3 and thang <7 group by thang");
			}
			else if(quy ==3)
			{
				p = con.prepareStatement("select thang,tongTNK=Sum(ThuNhapKhac) from LuongCongNhan where nam = ? and tinhTrang=1 and thang>6 and thang <10 group by thang");
			}
			else if(quy ==4)
			{
				p = con.prepareStatement("select thang,tongTNK=Sum(ThuNhapKhac) from LuongCongNhan where nam = ? and tinhTrang=1 and thang>9 and thang <13 group by thang");
			}
			else
			{
				p = con.prepareStatement("select thang,tongTNK=Sum(ThuNhapKhac) from LuongCongNhan where nam = ? and tinhTrang=1 group by thang");
			}
			
			p.setInt(1, nam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
//				ThuNhapKhac tnk = new ThuNhapKhac(r.getString("maThuNhapKhac"), r.getDouble("luongLamThem"),
//						r.getDouble("phuCap"),r.getDouble("thuong") , r.getInt("thang"), r.getInt("nam"));
//				NhanVien nv= nvdao.getNVtheoma(r.getString("maNV"));
//				ds.add(tnk);
				ThongKe tk = new ThongKe(r.getInt("thang"),r.getLong("tongTNK"));
				ds.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
	
	public List<ThongKe> getBLNV(int nam)
	{
		Connection con = new Connect().getConnect();
		List<ThongKe> ds = new ArrayList<ThongKe>();
		PreparedStatement p;
		try {
			
			p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'NV%' group by thang");
			p.setInt(1, nam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
//				ThuNhapKhac tnk = new ThuNhapKhac(r.getString("maThuNhapKhac"), r.getDouble("luongLamThem"),
//						r.getDouble("phuCap"),r.getDouble("thuong") , r.getInt("thang"), r.getInt("nam"));
//				NhanVien nv= nvdao.getNVtheoma(r.getString("maNV"));
//				ds.add(tnk);
				ThongKe tk = new ThongKe(r.getInt("thang"),r.getLong("Tongluong"));
				ds.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
	
	public List<ThongKe> getTNKNVTheoQuy(int nam,int quy)
	{
		Connection con = new Connect().getConnect();
		List<ThongKe> ds = new ArrayList<ThongKe>();
		PreparedStatement p = null;
		try {
			if(quy ==1)
			{
				p = con.prepareStatement("select thang,tongTNK=Sum(ThuNhapKhac) from LuongNhanVien where nam = ? and tinhTrang=1 and thang>0 and thang <4 group by thang");
			}
			else if(quy ==2)
			{
				p = con.prepareStatement("select thang,tongTNK=Sum(ThuNhapKhac) from LuongNhanVien where nam = ? and tinhTrang=1 and thang>3 and thang <7 group by thang");
			}
			else if(quy ==3)
			{
				p = con.prepareStatement("select thang,tongTNK=Sum(ThuNhapKhac) from LuongNhanVien where nam = ? and tinhTrang=1 and thang>6 and thang <10 group by thang");
			}
			else if(quy ==4)
			{
				p = con.prepareStatement("select thang,tongTNK=Sum(ThuNhapKhac) from LuongNhanVien where nam = ? and tinhTrang=1 and thang>9 and thang <13 group by thang");
			}
			else
			{
				p = con.prepareStatement("select thang,tongTNK=Sum(ThuNhapKhac) from LuongNhanVien where nam = ? and tinhTrang=1 group by thang");
			}
			
			p.setInt(1, nam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
//				ThuNhapKhac tnk = new ThuNhapKhac(r.getString("maThuNhapKhac"), r.getDouble("luongLamThem"),
//						r.getDouble("phuCap"),r.getDouble("thuong") , r.getInt("thang"), r.getInt("nam"));
//				NhanVien nv= nvdao.getNVtheoma(r.getString("maNV"));
//				ds.add(tnk);
				ThongKe tk = new ThongKe(r.getInt("thang"),r.getLong("tongTNK"));
				ds.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
	
	public List<ThongKe> getLuongNVTheoQuy(int nam,int quy)
	{
		Connection con = new Connect().getConnect();
		List<ThongKe> ds = new ArrayList<ThongKe>();
		PreparedStatement p = null;
		try {
			if(quy ==1)
			{
				p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'NV%' and thang>0 and thang <4 group by thang");
			}
			else if(quy ==2)
			{
				p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'NV%' and thang>3 and thang <7 group by thang");
			}
			else if(quy ==3)
			{
				p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'NV%' and thang>6 and thang <10 group by thang");
			}
			else if(quy ==4)
			{
				p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'NV%' and thang>9 and thang <13 group by thang");
			}
			else
			{
				p = con.prepareStatement("select thang,Tongluong = SUM(luong) from BangLuong where nam =? and maNV like 'NV%'  group by thang");
			}
			
			p.setInt(1, nam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
//				ThuNhapKhac tnk = new ThuNhapKhac(r.getString("maThuNhapKhac"), r.getDouble("luongLamThem"),
//						r.getDouble("phuCap"),r.getDouble("thuong") , r.getInt("thang"), r.getInt("nam"));
//				NhanVien nv= nvdao.getNVtheoma(r.getString("maNV"));
//				ds.add(tnk);
				ThongKe tk = new ThongKe(r.getInt("thang"),r.getLong("Tongluong"));
				ds.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
	
	public List<ThongKe> getTNKNV(int nam)
	{
		Connection con = new Connect().getConnect();
		List<ThongKe> ds = new ArrayList<ThongKe>();
		PreparedStatement p;
		try {
			
			p = con.prepareStatement("select thang, tongTNK=Sum(ThuNhapKhac) from LuongNhanVien where nam = ? and tinhTrang=1 group by thang");
			p.setInt(1, nam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
//				ThuNhapKhac tnk = new ThuNhapKhac(r.getString("maThuNhapKhac"), r.getDouble("luongLamThem"),
//						r.getDouble("phuCap"),r.getDouble("thuong") , r.getInt("thang"), r.getInt("nam"));
//				NhanVien nv= nvdao.getNVtheoma(r.getString("maNV"));
//				ds.add(tnk);
				ThongKe tk = new ThongKe(r.getInt("thang"),r.getLong("tongTNK"));
				ds.add(tk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
	
	

}
