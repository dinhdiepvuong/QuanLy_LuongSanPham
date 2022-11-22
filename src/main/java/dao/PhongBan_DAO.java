package dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Connect;
import entity.NhanVien;
import entity.PhongBan;

public class PhongBan_DAO {
	Connection con = new Connect().getConnect();
//	public PhongBan getPBma(String maPB)
//	{	
//		PhongBan pb = new PhongBan(maPB);
//		PreparedStatement p;
//		try {
//			p = con.prepareStatement("select * from PhongBan where maPhongBan =?");
//			p.setString(1, maPB);
//			ResultSet r=p.executeQuery();
//			while(r.next())
//			{
//				 pb.setMaPB(r.getString(maPB));
//				 pb.setTenPB(r.getString("tenPhongBan"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		return pb;
//	}
	public PhongBan getPBma(String maPB)
	{	
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from PhongBan where maPhongBan =?");
			p.setString(1, maPB);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				PhongBan pb = new PhongBan(r.getString("maPhongban"),r.getString("tenPhongBan"));
				return pb;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
		
	}
	public ArrayList<PhongBan> gettalltbPhongBan(){
		ArrayList<PhongBan> dspb=new ArrayList<PhongBan>();
		try {
			String sql="Select * from PhongBan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maPB=rs.getString(1);
				String tenPB=rs.getString(2);
				PhongBan pb = new PhongBan(maPB,tenPB);
				dspb.add(pb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dspb;
	}
	public PhongBan getPBten(String tenPB)
	{	
		
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from PhongBan where tenPhongBan =?");
			p.setString(1, tenPB);
			ResultSet r=p.executeQuery();
			while(r.next())
			{
				PhongBan pb = new PhongBan(r.getString("maPhongBan"), r.getString("tenPhongBan"));
				return pb;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
		
	}
}
