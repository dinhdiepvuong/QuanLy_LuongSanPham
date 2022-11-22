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
import entity.CongNhan;
import entity.LuongCongNhan;
import entity.LuongNhanVien;
import entity.NhanVien;
import entity.PhongBan;
import entity.ThuNhapKhac;


public class ChamCongNVHC_DAO {
	Connection con = new Connect().getConnect();
	NhanVien_DAO nvDao = new NhanVien_DAO();
	NhanVien nv;
	ThuNhapKhac_DAO tnkDao = new ThuNhapKhac_DAO();
	public List<ChamCongNVHC> getNVall()
	{	
		List ds = new ArrayList<ChamCongNVHC>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from ChamCongNVHC");
			
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				ChamCongNVHC chamcong = new ChamCongNVHC(r.getString("maChamCong"), r.getDouble("heSoluong"),r.getInt("soCongngayle"), r.getInt("soCongngaythuong"), r.getInt("nghiKhongphep"), r.getInt("thang"), r.getInt("nam"));
				chamcong.setMaNV(new NhanVien(r.getString("maNV")));
				ds.add(chamcong);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return ds;
		
	}

	public List<ThuNhapKhac> getallThuNhapKhac()
	{	
		List ds = new ArrayList<ThuNhapKhac>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from ThuNhapKhac");
			
			
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				ThuNhapKhac tnk = new ThuNhapKhac(r.getString("maThuNhapKhac"), r.getDouble("luongLamThem"), r.getDouble("phuCap"), r.getDouble("thuong"), r.getInt("thang"), r.getInt("nam"));
				tnk.setMaNv(new NhanVien(r.getString("maNV")));
//				ChamCongNVHC chamcong = new ChamCongNVHC(r.getString("maChamCong"), r.getDouble("heSoluong"),r.getInt("soCongngayle"), r.getInt("soCongngaythuong"), r.getInt("nghiKhongphep"), r.getInt("thang"), r.getInt("nam"));
//				chamcong.setMaNV(new NhanVien(r.getString("maNV")));
				ds.add(tnk);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return ds;
		
	}
	public ChamCongNVHC getNVCuoi() {
		ChamCongNVHC nv = new ChamCongNVHC();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select max(maChamCong) as maCuoi from ChamCongNVHC");
			ResultSet r = p.executeQuery();
			r.next();
			nv = new ChamCongNVHC(r.getString("maCuoi"));
			con.close();
		} catch (Exception e) {
		}
		return nv;
	}
	public boolean themChamCongNhanVienHC(ChamCongNVHC nv) {
		Connection con = new Connect().getConnect();
		PreparedStatement stt = null;
		
		try {
			stt = con.prepareStatement("insert into ChamCongNVHC values (?, ?, ?, ?, ?, ?, ?, ?,0)");
			stt.setString(1, nv.getMaChamCong());
			stt.setString(2, nv.getMaNV().getMaNV());
			stt.setInt(3, nv.getThang());
			stt.setInt(4, nv.getNam());
			stt.setDouble(5, nv.getHeSoLuong());
			stt.setInt(6, nv.getSoCongNgayLe());
			stt.setInt(7, nv.getSoCongNgayThuong());
			stt.setInt(8, nv.getNghiKhongPhep());
			stt.execute();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	public ThuNhapKhac getTHCuoi() {
		ThuNhapKhac ld = new ThuNhapKhac();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select max(maThuNhapKhac) as maCuoi from ThuNhapKhac");
			ResultSet r = p.executeQuery();
			r.next();
			ld = new ThuNhapKhac(r.getString("maCuoi"));
			con.close();
		} catch (Exception e) {
		}
		return ld;
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
	public boolean updateNhanVienHanhChinh(ChamCongNVHC nv) {
		Connection con = new Connect().getConnect();
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement("update ChamCongNVHC set thang=?, nam=?, heSoluong=?, soCongngayle=?, soCongngaythuong=?, nghiKhongphep=? "
					+ "where maNV = ? and thang = ? and nam = ?");
			stm.setInt(1, nv.getThang());
			stm.setInt(2, nv.getNam());
			stm.setDouble(3, nv.getHeSoLuong());
			stm.setInt(4, nv.getSoCongNgayLe());
			stm.setInt(5, nv.getSoCongNgayThuong());
			stm.setInt(6, nv.getNghiKhongPhep());
			stm.setString(7, nv.getMaNV().getMaNV());
			stm.setInt(8, nv.getThang());
			stm.setInt(9, nv.getNam());
			stm.executeUpdate();
			
		} catch (SQLException e) {
			// ToDo: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	public boolean updateThuNhapKhac(ThuNhapKhac nv) {
		Connection con = new Connect().getConnect();
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement(
					"update ThuNhapKhac set thang=?, nam=?, luongLamThem=?," + " phuCap=?, thuong=?"
							+ " where maNV=? and thang = ? and nam = ?");
			stm.setInt(1, nv.getThang());
			stm.setInt(2, nv.getNam());
			stm.setDouble(3, nv.getLuongLamThem());
			stm.setDouble(4, nv.getPhuCap());
			stm.setDouble(5, nv.getThuong());
			stm.setString(6, nv.getMaNv().getMaNV());
			stm.setInt(7, nv.getThang());
			stm.setInt(8, nv.getNam());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// ToDo: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	
	public List<BangChamCong> getBangChamCong()
	{
		List<BangChamCong> ds = new ArrayList<BangChamCong>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongNV1 ");
//			p.setInt(1, thang);
//			p.setInt(2, nam);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				BangChamCong bcc = new BangChamCong(r.getString("maNV"), r.getString("tenNV"), r.getDouble("heSoluong"), r.getInt("nghiKhongphep")
						,r.getInt("soCongngayle"), r.getInt("soCongngaythuong"), r.getInt("thang"), r.getInt("nam"), r.getInt("soCongChuan"), r.getDouble("Thunhapkhac"));
				ds.add(bcc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public List<BangChamCong> getBangChamCongTheoThangNam(int thang, int nam)
	{
		List<BangChamCong> ds = new ArrayList<BangChamCong>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongCN1 where thang = ? and nam = ?");
			p.setInt(1, thang);
			p.setInt(2, nam);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				BangChamCong bcc = new BangChamCong(r.getString("maNV"), r.getString("tenNV"), r.getDouble("heSoluong"), r.getInt("nghiKhongphep")
						,r.getInt("soCongngayle"), r.getInt("soCongngaythuong"), r.getInt("thang"), r.getInt("nam"), r.getInt("soCongChuan"), r.getDouble("Thunhapkhac"));
				ds.add(bcc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public List<BangChamCong> getThangNam(int fromthang, int fromnam, int tothang, int tonam )
	{
		List<BangChamCong> ds = new ArrayList<BangChamCong>();
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
				BangChamCong bcc = new BangChamCong(r.getString("maNV"), r.getString("tenNV"), r.getDouble("heSoluong"), r.getInt("nghiKhongphep")
						,r.getInt("soCongngayle"), r.getInt("soCongngaythuong"), r.getInt("thang"), r.getInt("nam"), r.getInt("soCongChuan"), 
						r.getDouble("Thunhapkhac"));
				ds.add(bcc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public List<BangChamCong> TimKiem(String tk,int fromthang, int fromnam, int tothang, int tonam)
	{
		List<BangChamCong> ds = new ArrayList<BangChamCong>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongNV1 where (nam> ? or (thang>= ? and nam = ?)) and (nam<? or (thang <=? and nam = ?)) and maNV like N'%"+tk+"%' or tenNV like N'%"+tk+"%' ");
			p.setInt(1, fromnam);
			p.setInt(2, fromthang);
			p.setInt(3, fromnam);
			p.setInt(4, tonam);
			p.setInt(5, tothang);
			p.setInt(6, tonam);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				BangChamCong bcc = new BangChamCong(r.getString("maNV"), r.getString("tenNV"), r.getDouble("heSoluong"), r.getInt("nghiKhongphep")
						,r.getInt("soCongngayle"), r.getInt("soCongngaythuong"), r.getInt("thang"), r.getInt("nam"), r.getInt("soCongChuan"), r.getDouble("thuNhapKhac"));
				ds.add(bcc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public boolean rangBuocThangNam(String ma,int thang,int nam){
		List<BangChamCong> ncc = new ArrayList<BangChamCong>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongNV1 where maNV =? and thang =? and nam = ? ");
			p.setString(1, ma);
			p.setInt(2, thang);
			p.setInt(3, nam);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				BangChamCong bcc = new BangChamCong(r.getString("maNV"), r.getString("tenNV"), r.getDouble("heSoluong"), r.getInt("nghiKhongphep")
						,r.getInt("soCongngayle"), r.getInt("soCongngaythuong"), r.getInt("thang"), r.getInt("nam"), r.getInt("soCongChuan"), r.getDouble("thuNhapKhac"));
				ncc.add(bcc);
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
	public boolean kiemTraNghiViec(String maNV) {
		List<NhanVien> ncc = new ArrayList<NhanVien>();
		PreparedStatement p;
		int trangThai = 1;
		try {
			p = con.prepareStatement("Select * From NhanVien where maNV like '"+maNV+"' and trangThai like '"+trangThai+"'  ");
//			p.setString(1, maNV);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				NhanVien nv = new NhanVien(r.getString("maNV"), r.getString("tenNV"),r.getDate("ngaySinh"), r.getDate("ngayVaolam"), r.getInt("CMND"),r.getInt("SDT"), r.getBoolean("gioiTinh"));
				nv.setPhongban(new PhongBan(r.getString("maPB"), null));
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
	public boolean kiemTraThanhToanNV(String maNV,int thang, int nam) {
		List<BangChamCong> ncc = new ArrayList<BangChamCong>();
		PreparedStatement p;
		int trangThai = 1;
		try {
			p = con.prepareStatement("Select * From LuongNhanVien where tinhTrang like '"+trangThai+"' and maNV like '"+maNV+"' and thang like '"+thang+"' and nam like '"+nam+"'");
		
			ResultSet r = p.executeQuery();
			while (r.next()) {
				BangChamCong bcc = new BangChamCong(r.getString("maNV"), r.getString("tenNV"), r.getDouble("heSoluong"), r.getInt("nghiKhongphep")
						,r.getInt("soCongngayle"), r.getInt("soCongngaythuong"), r.getInt("thang"), r.getInt("nam"), r.getInt("soCongChuan"), r.getDouble("thuNhapKhac"));
				ncc.add(bcc);
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
		List<NhanVien> ncc = new ArrayList<NhanVien>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from NhanVien where maNV =? ");
			p.setString(1, ma);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				NhanVien nv = new NhanVien(r.getString("maNV"), r.getString("tenNV"),r.getDate("ngaySinh"), r.getDate("ngayVaolam"), r.getInt("CMND"),r.getInt("SDT"), r.getBoolean("gioiTinh"));
				nv.setPhongban(new PhongBan(r.getString("maPB"), null));
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
	public boolean kiemTraPhongBan(String ma) {
		List<BangChamCong> ncc = new ArrayList<BangChamCong>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongCN1 where maNV =? order by thang desc ");
			p.setString(1, ma);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				BangChamCong bcc = new BangChamCong(r.getString("maNV"), r.getString("tenNV"), r.getDouble("heSoluong"), r.getInt("nghiKhongphep")
						,r.getInt("soCongngayle"), r.getInt("soCongngaythuong"), r.getInt("thang"), r.getInt("nam"), r.getInt("soCongChuan"), r.getDouble("thuNhapKhac"));
				ncc.add(bcc);
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
	
	public List<LuongCongNhan> getLuongCongNhan(String maNV)
	{
		List<LuongCongNhan> ds = new ArrayList<LuongCongNhan>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongCongNhan where maNV =? order by thang desc ");
			p.setString(1, maNV);

			ResultSet r=p.executeQuery();
			while(r.next())
			{
				LuongCongNhan bcc = new LuongCongNhan(r.getString("maNV"), r.getString("tenNV"), r.getString("tenSP"), r.getString("tenCongDoan"),r.getInt("soluong"),
						r.getDouble("donGia"), r.getDouble("phuCap"), r.getDouble("thuong"), r.getDouble("luongLamThem"), r.getDouble("Thunhapkhac"), 
						r.getInt("thang"), r.getInt("nam"), r.getDouble("Tongluong"),r.getBoolean("tinhTrang"));
				ds.add(bcc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public List<LuongNhanVien> getLuongNhanVien(String maNV)
	{
		List<LuongNhanVien> ds = new ArrayList<LuongNhanVien>();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from LuongNhanVien where maNV =? ");
			p.setString(1, maNV);

			ResultSet r=p.executeQuery();
			while(r.next())
			{
				
				LuongNhanVien bcc = new LuongNhanVien(r.getString("maNV"), r.getString("tenNV"), r.getDouble("heSoluong"), r.getInt("soCongngaythuong"),
						r.getInt("soCongngayle"), r.getInt("nghiKhongphep"), r.getInt("SoCongChuan"), r.getDouble("luongLamThem"), 
						r.getDouble("thuong"), r.getDouble("phuCap"), r.getDouble("Thunhapkhac"),r.getInt("thang") , r.getInt("nam"), r.getDouble("Tongluong"),r.getBoolean("tinhTrang"));
				ds.add(bcc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public boolean doiMatKhau(String password,String username ) {
		Connection con = new Connect().getConnect();
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement("update TaiKhoan set password = ? where userName = ?");
			stm.setString(1,password);
			stm.setString(2,username);
			
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
