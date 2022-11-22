package entity;


public class BangLuong {
	private String maBL;
	private double luong;
	private NhanVien nhanVien;
	private int thang;
	private int nam;
	public BangLuong() {
		
	}
	public String getMaBL() {
		return maBL;
	}
	public void setMaBL(String maBL) {
		this.maBL = maBL;
	}
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
	}
	
	public BangLuong(String maBL, int thang, double luong, int nam) {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BangLuong(String maBL) {
		super();
		this.maBL = maBL;
	}
	public BangLuong(String maBL, double luong, NhanVien nhanVien, int thang, int nam) {
		super();
		this.maBL = maBL;
		this.luong = luong;
		this.nhanVien = nhanVien;
		this.thang = thang;
		this.nam = nam;
	}
	
	public BangLuong(String maBL, NhanVien tennv, int thang, int nam, double luong) {
		super();
		this.maBL = maBL;
		this.nhanVien= tennv;
		this.thang = thang;
		this.nam = nam;
		this.luong = luong;
	}

	
	
	
	
	@Override
	public String toString() {
		return "BangLuong [maBL=" + maBL + ", luong=" + luong + ", nhanVien=" + nhanVien + ", thang=" + thang + ", nam="
				+ nam + "]";
	}
	
	
	
	
	
}
