package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connect;
import entity.Luong;
import entity.NhanVien;
import entity.PhongBan;
import entity.ThuNhapKhac;

public class ThuNhapKhac_DAO {
	Connection con = new Connect().getConnect();
	private NhanVien_DAO nvDao = new NhanVien_DAO();
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
			e.printStackTrace();
		}
		return ds;
		
	}
//	public double getluongLamThem2(String maNV,int thang,int nam) {
//		PreparedStatement p;
//		List<ThuNhapKhac> ds = new ArrayList<ThuNhapKhac>();
//		try {
//			p = con.prepareStatement("select luongLamThem from ThuNhapKhac where maNV =? and thang = ? and nam =?");
//			p.setString(1, maNV);
//			p.setInt(2, thang);
//			p.setInt(3, nam);
//			ResultSet r=p.executeQuery();
//			while(r.next())
//			{
//				ThuNhapKhac tnk = new ThuNhapKhac(r.getString("maThuNhapKhac"), r.getDouble("luongLamThem"), r.getDouble("phuCap"), r.getDouble("thuong"), r.getInt(thang), r.getInt(nam));
//				double a =  tnk.
//				
////				return a;
//
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return 0;
//	}
	public ThuNhapKhac getTNKtheoMa(String maNV)
	{	
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from ThuNhapKhac where maNV =?");
			p.setString(1, maNV);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				ThuNhapKhac tnk = new ThuNhapKhac(r.getString("maThuNhapKhac"),r.getDouble("luongLamThem"),r.getDouble("phuCap"),r.getDouble("thuong"),
						r.getInt("thang"), r.getInt("nam"));
				tnk.setMaNv(new NhanVien(r.getString("maNV")));
				return tnk;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
		
	}
}
