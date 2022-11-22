package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connect;
import entity.ChamCongNVHC;
import entity.Luong;
import entity.LuongNV;
import entity.NhanVien;
import entity.NhanVien_CongDoan;
import entity.ThuNhapKhac;

public class TinhLuong_DAO {
	Connection con = new Connect().getConnect();
	public List<ThuNhapKhac> getTNK(String manv,int thang, int nam)
	{
		NhanVien_DAO nvdao = new NhanVien_DAO();
		List<ThuNhapKhac> ds = new ArrayList<ThuNhapKhac>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from ThuNhapKhac where maNV = ? and thang = ? and nam = ?");
			p.setString(1, manv);
			p.setInt(2, thang);
			p.setInt(3, nam);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				ThuNhapKhac tnk = new ThuNhapKhac(r.getString("maThuNhapKhac"), r.getDouble("luongLamThem"),
						r.getDouble("phuCap"),r.getDouble("thuong") , r.getInt("thang"), r.getInt("nam"));
				NhanVien nv= nvdao.getNVtheoma(r.getString("maNV"));
				ds.add(tnk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
	
//	public List<NhanVien_CongDoan> getNV_CD(int thang , int nam)
//	{
//		List<NhanVien_CongDoan> ds = new ArrayList<NhanVien_CongDoan>();
//		NhanVien_DAO nvdao = new NhanVien_DAO();
//		CongDoan_DAO cddao = new CongDoan_DAO();
//		PreparedStatement p;
//		try {
//			p = con.prepareStatement("select * from NhanVien_CongDoan where thang = ? and nam = ?");
//			p.setInt(1, thang);
//			p.setInt(2, nam);
//			ResultSet r=p.executeQuery();
//			while(r.next())
//			{
//				NhanVien_CongDoan nvcd = new NhanVien_CongDoan(nvdao.getNVtheoma( r.getString("maNhanVien")),r.getString("maCongDoan") , r.getInt("soluong"), r.getInt("thang"), r.getInt("thang"));
//				ds.add(nvcd);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ds;
//	}
	public List<Luong> getLuong(int thang, int nam)
	{
		List<Luong> ds = new ArrayList<Luong>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongCN1 where thang = ? and nam = ?");
			p.setInt(1, thang);
			p.setInt(2, nam);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				Luong luong = new Luong(r.getString("maNV"), r.getString("tenNV"),r.getString("tenSP"), r.getString("tenCongDoan"),r.getInt("soluong"),r.getDouble("ThuNhapKhac"),r.getInt("thang"),r.getInt("nam"),r.getDouble("Tongluong"));
				ds.add(luong);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public List<Luong> getThangNam(int fromthang, int fromnam, int tothang, int tonam )
	{
		List<Luong> ds = new ArrayList<Luong>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongCN1 where (nam> ? or (thang>= ? and nam = ?)) and (nam<? or (thang <=? and nam = ?))");
			p.setInt(1, fromnam);
			p.setInt(2, fromthang);
			p.setInt(3, fromnam);
			p.setInt(4, tonam);
			p.setInt(5, tothang);
			p.setInt(6, tonam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				Luong luong = new Luong(r.getString("maNV"), r.getString("tenNV"),r.getString("tenSP"), r.getString("tenCongDoan"),r.getInt("soluong"),r.getDouble("ThuNhapKhac"),r.getInt("thang"),r.getInt("nam"),r.getDouble("Tongluong"));
				luong.setTinhTrang(r.getBoolean("tinhTrang"));
				ds.add(luong);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public List<Luong> TimKiem(int fromthang, int fromnam, int tothang, int tonam,String tk)
	{
		List<Luong> ds = new ArrayList<Luong>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongCN1 where (nam> ? or (thang>= ? and nam = ?)) and (nam<? or (thang <=? and nam = ?)) and( tenNV like N'%"+tk+"%' or maNV like N'%"+tk+"%' )");
			p.setInt(1, fromnam);
			p.setInt(2, fromthang);
			p.setInt(3, fromnam);
			p.setInt(4, tonam);
			p.setInt(5, tothang);
			p.setInt(6, tonam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				Luong luong = new Luong(r.getString("maNV"), r.getString("tenNV"),r.getString("tenSP"), r.getString("tenCongDoan"),r.getInt("soluong"),r.getDouble("ThuNhapKhac"),r.getInt("thang"),r.getInt("nam"),r.getDouble("Tongluong"));
				luong.setTinhTrang(r.getBoolean("tinhTrang"));
				ds.add(luong);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public List<LuongNV> TimKiemNV(int fromthang, int fromnam, int tothang, int tonam,String tk)
	{
		List<LuongNV> ds = new ArrayList<LuongNV>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongNV1 where (nam> ? or (thang>= ? and nam = ?)) and (nam<? or (thang <=? and nam = ?)) and( tenNV like N'%"+tk+"%' or maNV like N'%"+tk+"%' )");
			p.setInt(1, fromnam);
			p.setInt(2, fromthang);
			p.setInt(3, fromnam);
			p.setInt(4, tonam);
			p.setInt(5, tothang);
			p.setInt(6, tonam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				LuongNV luongNV= new  LuongNV(r.getString("maNV"), r.getString("tenNV"),r.getDouble("heSoluong"),r.getInt("soCongngaythuong"),
						r.getInt("soCongngayle"),r.getInt("nghiKhongphep"), r.getInt("SoCongChuan"),r.getDouble("Thunhapkhac"),r.getInt("thang"), r.getInt("nam"),r.getDouble("Tongluong"));
				luongNV.setTinhTrang(r.getBoolean("tinhTrang"));
				ds.add(luongNV);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public List<LuongNV> getThangNamNV(int fromthang, int fromnam, int tothang, int tonam )
	{
		List<LuongNV> ds = new ArrayList<LuongNV>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongNV1 where (nam> ? or (thang>= ? and nam = ?)) and (nam<? or (thang <=? and nam = ?))");
			p.setInt(1, fromnam);
			p.setInt(2, fromthang);
			p.setInt(3, fromnam);
			p.setInt(4, tonam);
			p.setInt(5, tothang);
			p.setInt(6, tonam);
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				LuongNV luongnv = new LuongNV(r.getString("maNV"), r.getString("tenNV"),r.getDouble("heSoluong"),r.getInt("soCongngaythuong"),
						r.getInt("soCongngayle"),r.getInt("nghiKhongphep"), r.getInt("SoCongChuan"),r.getDouble("Thunhapkhac"),r.getInt("thang"), r.getInt("nam"),r.getDouble("Tongluong"));
				luongnv.setTinhTrang(r.getBoolean("tinhTrang"));
				ds.add(luongnv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public void TinhLuongCN(NhanVien_CongDoan nvcd)
	{
		PreparedStatement p;
		try {
			p = con.prepareStatement("update NhanVien_CongDoan set tinhTrang = 1 where maNhanVien = ? and thang = ? and nam = ?");
			p.setString(1,nvcd.getMaNhanVien().getMaNV() );
			p.setInt(2, nvcd.getThang());
			p.setInt(3, nvcd.getNam());
			p.execute();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void TinhLuongNV(ChamCongNVHC cc)
	{
		PreparedStatement p;
		try {
			p = con.prepareStatement("update ChamCongNVHC set tinhTrang = 1 where maNV = ? and thang = ? and nam = ?");
			p.setString(1,cc.getMaNV().getMaNV() );
			p.setInt(2, cc.getThang());
			p.setInt(3, cc.getNam());
			p.execute();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
