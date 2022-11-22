package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connect;
import entity.BangChamCong;
import entity.ChamCongNVHC;
import entity.CongDoan;
import entity.CongNhan;

import entity.NhanVien_CongDoan;
import entity.ThuNhapKhac;

public class DuLieuCongNhan_DAO {
	Connection con = new Connect().getConnect();

	public List<CongNhan> getallCongNhan()
	{
		List<CongNhan> ds = new ArrayList<CongNhan>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongCN1");
//			p.setInt(1, thang);
//			p.setInt(2, nam);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				CongNhan nv = new CongNhan(r.getString("maNV"),r.getString("tenNV"),r.getString("tenSP"),r.getString("tenCongDoan"),
						r.getInt("soluong")
						,r.getInt("thang"),r.getInt("nam"),r.getDouble("ThuNhapKhac"));
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public boolean themCongNhan(NhanVien_CongDoan nv) {
		Connection con = new Connect().getConnect();
		PreparedStatement stt = null;
		try {
			stt = con.prepareStatement("insert into NhanVien_CongDoan values (?, ?, ?, ?, ?,0)");
			stt.setString(1, nv.getMaNhanVien().getMaNV());
			stt.setString(2, nv.getMaCongDoan().getMaCongDoan());
			stt.setDouble(3, nv.getSoluong());
			stt.setInt(4, nv.getThang());
			stt.setInt(5, nv.getNam());
			stt.execute();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	public boolean themThuNhapKhac(ThuNhapKhac nv) {
		Connection con = new Connect().getConnect();
		PreparedStatement stt = null;
		try {
			stt = con.prepareStatement("insert into ThuNhapKhac values (?, ?, ?, ?, ?, ?, ?)");
			stt.setString(1, nv.getMaThuNhapKhac());
			stt.setDouble(2, nv.getLuongLamThem());
			stt.setDouble(3, nv.getPhuCap());
			stt.setDouble(4, nv.getThuong());
			stt.setString(5, nv.getMaNv().getMaNV());
			stt.setInt(6, nv.getThang());
			stt.setInt(7, nv.getNam());
			stt.execute();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	public List<CongNhan> timKiem(String tk,int fromthang, int fromnam, int tothang, int tonam){
		List<CongNhan> ds = new ArrayList<CongNhan>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongCN1 where (nam> ? or (thang>= ? and nam = ?)) and (nam<? or (thang <=? and nam = ?)) and maNV like N'%"+tk+"%' or tenNV like N'%"+tk+"%' or tenSP like N'%"+tk+"%' or tenCongDoan like N'%"+tk+"%'");
			p.setInt(1, fromnam);
			p.setInt(2, fromthang);
			p.setInt(3, fromnam);
			p.setInt(4, tonam);
			p.setInt(5, tothang);
			p.setInt(6, tonam);
			ResultSet r = p.executeQuery();
			
			while (r.next()) {
				CongNhan nv = new CongNhan(r.getString("maNV"),r.getString("tenNV"),r.getString("tenSP"),r.getString("tenCongDoan"),
						r.getInt("soluong")
						,r.getInt("thang"),r.getInt("nam"),r.getDouble("ThuNhapKhac"));
				ds.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Tim loi!!!");
		}
		return ds;
	}
	public List<CongNhan> timKiemTheoThangNam(String tk,int thang,int nam){
		List<CongNhan> ds = new ArrayList<CongNhan>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("Select * From LuongCN1 where maNV like N'%"+tk+"%' or tenNV like N'%"+tk+"%' or tenSP like N'%"+tk+"%' or tenCongDoan like N'%"+tk+"%' and thang = ? and nam =?");
			p.setInt(1, thang);
			p.setInt(2, nam);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				CongNhan nv = new CongNhan(r.getString("maNV"),r.getString("tenNV"),r.getString("tenSP"),r.getString("tenCongDoan"),
						r.getInt("soluong")
						,r.getInt("thang"),r.getInt("nam"),r.getDouble("ThuNhapKhac"));
				ds.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Tim loi!!!");
		}
		return ds;
	}
	public boolean updateNhanVienCongDoan(NhanVien_CongDoan nv) {
		Connection con = new Connect().getConnect();
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement("update NhanVien_CongDoan set soluong=?, maCongDoan =? where thang = ? and nam = ? and maNhanVien = ? and maCongDoan =?");
			
			stm.setDouble(1, nv.getSoluong());
			stm.setString(2, nv.getMaCongDoan().getMaCongDoan());
			stm.setInt(3, nv.getThang());
			stm.setInt(4, nv.getNam());
			stm.setString(5,nv.getMaNhanVien().getMaNV());
			stm.setString(6, nv.getMaCongDoan().getMaCongDoan());
			
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean rangBuocThangNam(String ma,int thang,int nam){
		List<CongNhan> ds = new ArrayList<CongNhan>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongCN1 where maNV =? and thang =? and nam = ? ");
			p.setString(1, ma);
			p.setInt(2, thang);
			p.setInt(3, nam);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				CongNhan nv = new CongNhan(r.getString("maNV"),r.getString("tenNV"),r.getString("tenSP"),r.getString("tenCongDoan"),
						r.getInt("soluong")
						,r.getInt("thang"),r.getInt("nam"),r.getDouble("ThuNhapKhac"));
				ds.add(nv);
			}
			if(ds.size()>0) {
				return false;
			}
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public boolean kiemTraThanhToanCN(String maNV,int thang, int nam) {
		List<CongNhan> ncc = new ArrayList<CongNhan>();
		PreparedStatement p;
		int trangThai = 1;
		try {
			p = con.prepareStatement("Select * From LuongCN1 where tinhTrang like '"+trangThai+"' and maNV like '"+maNV+"' and thang like '"+thang+"' and nam like '"+nam+"'");
		
			ResultSet r = p.executeQuery();
			while (r.next()) {
				CongNhan nv = new CongNhan(r.getString("maNV"),r.getString("tenNV"),r.getString("tenSP"),r.getString("tenCongDoan"),
						r.getInt("soluong")
						,r.getInt("thang"),r.getInt("nam"),r.getDouble("ThuNhapKhac"));
				ncc.add(nv);
			}
			if(ncc.size()>0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public boolean kiemTraNhanVien(String ma){
		List<CongNhan> ncc = new ArrayList<CongNhan>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongCN1 where maNV =? ");
			p.setString(1, ma);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				CongNhan nv = new CongNhan(r.getString("maNV"),r.getString("tenNV"),r.getString("tenSP"),r.getString("tenCongDoan"),
						r.getInt("soluong")
						,r.getInt("thang"),r.getInt("nam"),r.getDouble("ThuNhapKhac"));
				ncc.add(nv);
			}
			if(ncc.size()>0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	} 
	public List<CongNhan> getThangNam(int fromthang, int fromnam, int tothang, int tonam )
	{
		List<CongNhan> ds = new ArrayList<CongNhan>();
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
				CongNhan nv = new CongNhan(r.getString("maNV"),r.getString("tenNV"),r.getString("tenSP"),r.getString("tenCongDoan"),
						r.getInt("soluong")
						,r.getInt("thang"),r.getInt("nam"),r.getDouble("ThuNhapKhac"));
				ds.add(nv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	
}
