package entity;

public class NhanVien_CongDoan {
	private NhanVien maNhanVien;
	private CongDoan maCongDoan;
	private int soluong;
	private int thang;
	private int nam;
	
	public NhanVien_CongDoan() {
		// TODO Auto-generated constructor stub
	}

	public NhanVien_CongDoan(NhanVien maNhanVien, CongDoan maCongDoan, int soluong, int thang, int nam) {
		super();
		this.maNhanVien = maNhanVien;
		this.maCongDoan = maCongDoan;
		this.soluong = soluong;
		this.thang = thang;
		this.nam = nam;
	}
	

	public NhanVien_CongDoan(NhanVien maNhanVien, int thang, int nam) {
		super();
		this.maNhanVien = maNhanVien;
		this.thang = thang;
		this.nam = nam;
	}

	public NhanVien_CongDoan(CongDoan maCongDoan, int soluong, int thang, int nam) {
		super();
		this.maCongDoan = maCongDoan;
		this.soluong = soluong;
		this.thang = thang;
		this.nam = nam;
	}

	public NhanVien getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(NhanVien maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public CongDoan getMaCongDoan() {
		return maCongDoan;
	}

	public void setMaCongDoan(CongDoan maCongDoan) {
		this.maCongDoan = maCongDoan;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
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

	@Override
	public String toString() {
		return "NhanVien_CongDoan [maNhanVien=" + maNhanVien + ", maCongDoan=" + maCongDoan + ", soluong=" + soluong
				+ ", thang=" + thang + ", nam=" + nam + "]";
	}
	
	
	
	
	
	
	
}
