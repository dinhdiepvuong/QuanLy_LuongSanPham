package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Connect;
import entity.LuongTK;

public class ThongKeDS_DAO {
	public ArrayList<LuongTK> getAllBLuong(int nam, int thoiGian, int thangHoacQuy, int loaiNV){
		ArrayList<LuongTK> list = new ArrayList<LuongTK>();
		Connection con = new Connect().getConnect();
		/**
		 * 0: Tat ca danh sach
		 * 1: Chon tieu chi theo THANG
		 * 2: Chon tieu chi theo NAM
		 */
		
		String sql = "select * from LuongTK where ";
		
		sql += " nam = '" + nam + "' and ";
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
			sql += " maNV like 'LD%' ";
		else if(loaiNV == 2) 
			sql += " maNV like 'NV%' ";
		else 
			sql = sql.substring(0, sql.length()-4);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(new LuongTK(rs.getString("maBangluong"), rs.getString("maNV"),
						rs.getString("tenNV").trim(), rs.getInt("thang"),
						rs.getInt("nam"), rs.getDouble("luongLamThem"), rs.getDouble("phuCap"),
						rs.getDouble("thuong"), rs.getDouble("luong")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return list;
		
	}
}
