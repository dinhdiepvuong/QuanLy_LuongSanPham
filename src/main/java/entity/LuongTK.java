package entity;

public class LuongTK {
	private String maBL;
	private String maNV;
	private String tenNV;
	private int thang;
	private int nam;
	private double luongLamThem;
	private double phuCap;
	private double thuong;
	private double luong;
	
	public String getMaBL() {
		return maBL;
	}
	public void setMaBL(String maBL) {
		this.maBL = maBL;
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
	public double getLuongLamThem() {
		return luongLamThem;
	}
	public void setLuongLamThem(double luongLamThem) {
		this.luongLamThem = luongLamThem;
	}
	public double getPhuCap() {
		return phuCap;
	}
	public void setPhuCap(double phuCap) {
		this.phuCap = phuCap;
	}
	public double getThuong() {
		return thuong;
	}
	public void setThuong(double thuong) {
		this.thuong = thuong;
	}
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	
	public LuongTK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LuongTK(String maBL, String maNV, String tenNV, int thang, int nam, double luongLamThem, double phuCap,
			double thuong, double luong) {
		super();
		this.maBL = maBL;
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.thang = thang;
		this.nam = nam;
		this.luongLamThem = luongLamThem;
		this.phuCap = phuCap;
		this.thuong = thuong;
		this.luong = luong;
	}
	
	@Override
	public String toString() {
		return "LuongTK [maBL=" + maBL + ", maNV=" + maNV + ", tenNV=" + tenNV + ", thang=" + thang + ", nam=" + nam
				+ ", luongLamThem=" + luongLamThem + ", phuCap=" + phuCap + ", thuong=" + thuong + ", luong=" + luong
				+ "]";
	}
	
	
	
	
}
