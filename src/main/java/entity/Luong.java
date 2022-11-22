package entity;

public class Luong {
	private String maNV;
	private String tenNV;
	private String tenSP;
	private String tenCongDoan;
	private int soluong;
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
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public String getTenCongDoan() {
		return tenCongDoan;
	}
	public void setTenCongDoan(String tenCongDoan) {
		this.tenCongDoan = tenCongDoan;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
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
	public Luong(String maNV, String tenNV, String tenSP, String tenCongDoan, int soluong, double thuNhapKhac,
			int thang, int nam, double tongLuong) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.tenSP = tenSP;
		this.tenCongDoan = tenCongDoan;
		this.soluong = soluong;
		this.thuNhapKhac = thuNhapKhac;
		this.thang = thang;
		this.nam = nam;
		this.tongLuong = tongLuong;
	}
	@Override
	public String toString() {
		return "Luong [maNV=" + maNV + ", tenNV=" + tenNV + ", tenSP=" + tenSP + ", tenCongDoan=" + tenCongDoan
				+ ", soluong=" + soluong + ", thuNhapKhac=" + thuNhapKhac + ", thang=" + thang + ", nam=" + nam
				+ ", tongLuong=" + tongLuong + "]";
	}
	
	
	
}
