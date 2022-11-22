package entity;

public class CongDoan {
	private SanPham sanpham;
	private String maCongDoan;
	private String tenCongDoan;
	private double donGia;
	public CongDoan(SanPham sanpham, String maCongDoan, String tenCongDoan, double donGia) {
		super();
		this.sanpham = sanpham;
		this.maCongDoan = maCongDoan;
		this.tenCongDoan = tenCongDoan;
		this.donGia = donGia;
	}
	
	public CongDoan(String maCongDoan, String tenCongDoan, double donGia) {
		super();
		this.maCongDoan = maCongDoan;
		this.tenCongDoan = tenCongDoan;
		this.donGia = donGia;
	}
	

	public CongDoan(String maCongDoan) {
		super();
		this.maCongDoan = maCongDoan;
	}

	public CongDoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SanPham getSanpham() {
		return sanpham;
	}
	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}
	public String getMaCongDoan() {
		return maCongDoan;
	}
	public void setMaCongDoan(String maCongDoan) {
		this.maCongDoan = maCongDoan;
	}
	public String getTenCongDoan() {
		return tenCongDoan;
	}
	public void setTenCongDoan(String tenCongDoan) {
		this.tenCongDoan = tenCongDoan;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	@Override
	public String toString() {
		return "CongDoan [sanpham=" + sanpham + ", maCongDoan=" + maCongDoan + ", tenCongDoan=" + tenCongDoan
				+ ", donGia=" + donGia + "]";
	}
	
}
