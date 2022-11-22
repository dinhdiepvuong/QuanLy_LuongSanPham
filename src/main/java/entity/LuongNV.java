package entity;

public class LuongNV {
	private String maNV;
	private String tenNV;
	private double heSoluong;
	private int soCongngaythuong;
	private int soCongngayle;
	private int nghiKhongphep;
	private int soCongchuan;
	private double thuNhapKhac;
	private int thang;
	private int nam;
	private double tongLuong;
	private boolean tinhTrang;
	
	public boolean isTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public double getHeSoluong() {
		return heSoluong;
	}
	public void setHeSoluong(double heSoluong) {
		this.heSoluong = heSoluong;
	}
	public int getSoCongngaythuong() {
		return soCongngaythuong;
	}
	public void setSoCongngaythuong(int soCongngaythuong) {
		this.soCongngaythuong = soCongngaythuong;
	}
	public int getSoCongngayle() {
		return soCongngayle;
	}
	public void setSoCongngayle(int soCongngayle) {
		this.soCongngayle = soCongngayle;
	}
	public int getNghiKhongphep() {
		return nghiKhongphep;
	}
	public void setNghiKhongphep(int nghiKhongphep) {
		this.nghiKhongphep = nghiKhongphep;
	}
	public int getSoCongchuan() {
		return soCongchuan;
	}
	public void setSoCongchuan(int soCongchuan) {
		this.soCongchuan = soCongchuan;
	}
	public double getThuNhapKhac() {
		return thuNhapKhac;
	}
	public void setThuNhapKhac(double thuNhapKhac) {
		this.thuNhapKhac = thuNhapKhac;
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
	public double getTongLuong() {
		return tongLuong;
	}
	public void setTongLuong(double tongLuong) {
		this.tongLuong = tongLuong;
	}
	public LuongNV(String maNV, String tenNV, double heSoluong, int soCongngaythuong, int soCongngayle,
			int nghiKhongphep, int soCongchuan, double thuNhapKhac, int thang, int nam, double tongLuong) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.heSoluong = heSoluong;
		this.soCongngaythuong = soCongngaythuong;
		this.soCongngayle = soCongngayle;
		this.nghiKhongphep = nghiKhongphep;
		this.soCongchuan = soCongchuan;
		this.thuNhapKhac = thuNhapKhac;
		this.thang = thang;
		this.nam = nam;
		this.tongLuong = tongLuong;
	}
	@Override
	public String toString() {
		return "LuongNV [maNV=" + maNV + ", tenNV=" + tenNV + ", heSoluong=" + heSoluong + ", soCongngaythuong="
				+ soCongngaythuong + ", soCongngayle=" + soCongngayle + ", nghiKhongphep=" + nghiKhongphep
				+ ", soCongchuan=" + soCongchuan + ", thuNhapKhac=" + thuNhapKhac + ", thang=" + thang + ", nam=" + nam
				+ ", tongLuong=" + tongLuong + "]";
	}
	
	
}
