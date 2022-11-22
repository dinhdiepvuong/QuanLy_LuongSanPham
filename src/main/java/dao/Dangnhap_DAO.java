package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;

public class Dangnhap_DAO {
	Connection con = new Connect().getConnect();
	public boolean ktraDangnhap(String username, String password)
	{
		
		try {
			PreparedStatement p=con.prepareStatement("select userName from TaiKhoan where userName =? and password =?");
			p.setString(1, username);
			p.setString(2, password);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				if(!r.getString("userName").equals(""))
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	
	}
	public boolean KtraQuanLy(String userName)
	{
		try {
			PreparedStatement p=con.prepareStatement("select quanLy from NhanVien where userName =?");
			p.setString(1, userName);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				if(r.getBoolean("quanLy"))
					return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean KtraTrangthai(String userName)
	{
		try {
			PreparedStatement p=con.prepareStatement("select trangThai from NhanVien where userName =?");
			p.setString(1, userName);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				if(r.getBoolean("trangThai"))
					return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
