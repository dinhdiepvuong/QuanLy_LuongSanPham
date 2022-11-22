package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoan_Dao {
	public boolean themTK(TaiKhoan tk) {
		Connection con = new Connect().getConnect();
		PreparedStatement p=null;
		try {
			p = con.prepareStatement("insert into TaiKhoan values (?,?)");
			p.setString(1, tk.getUsername());
			p.setString(2, tk.getPassword());
			p.execute();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public TaiKhoan getTaiKhoanTheoMa(String ma) {
		Connection con = new Connect().getConnect();
		PreparedStatement p=null;
		try {
			p = con.prepareStatement("Select * From TaiKhoan where userName =?");
			p.setString(1, ma);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				TaiKhoan tk = new TaiKhoan(r.getString("userName"), r.getString("password"));
				return tk;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
