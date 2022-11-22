package entity;

public class SanPham {
	private String maSP;
	private String tenSP;
	private String moTa;
	private String mau;
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SanPham(String maSP, String tenSP, String moTa, String mau) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.moTa = moTa;
		this.mau = mau;
	}
	
	public SanPham(String maSP, String tenSP) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
	}
	
	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}
	public SanPham(String tenSP, String moTa, String mau) {
		super();
		this.tenSP = tenSP;
		this.moTa = moTa;
		this.mau = mau;
	}
	
	
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getMau() {
		return mau;
	}
	public void setMau(String mau) {
		this.mau = mau;
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", moTa=" + moTa + ", mau=" + mau + "]";
	}
	
}
