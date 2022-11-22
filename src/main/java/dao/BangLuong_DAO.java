package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;
import entity.BangLuong;
import entity.NhanVien;

public class BangLuong_DAO {
	Connection con = new Connect().getConnect();
	public void ThemBangLuong(BangLuong bangluong)
	{
		PreparedStatement p;
		try {
			p = con.prepareStatement("insert BangLuong values (? ,? ,? ,? ,?)");
			p.setString(1,bangluong.getMaBL());
			p.setString(2, bangluong.getNhanVien().getMaNV());
			p.setDouble(3, bangluong.getLuong());
			p.setInt(4, bangluong.getThang());
			p.setInt(5, bangluong.getNam());
			p.execute();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BangLuong getBLCuoi() {
		BangLuong bl = new BangLuong();
		
		try {
			PreparedStatement p = con.prepareStatement("select max(maBangluong) as maCuoi from BangLuong where maBangluong like 'BL%' ");
			ResultSet r = p.executeQuery();
			r.next();
			bl = new BangLuong(r.getString("maCuoi"));
		} catch (Exception e) {
		}
		return bl;
	}
	public boolean KtraBangLuong(BangLuong bl)
	{
		try {
			PreparedStatement p = con.prepareStatement("select * from BangLuong where maBangluong = ? and thang = ? and nam = ?");
			ResultSet r = p.executeQuery();
			if(r!=null)
			{
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
	
	
}
