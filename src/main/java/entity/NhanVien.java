package entity;

import java.sql.Date;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private Date ngaySinh; 
	private Date ngayVaolam; 
	private int CMND;
	private int SDT;
	private boolean trangThai;
	private boolean quanLy;
	private boolean gioiTinh;
	private TaiKhoan taikhoan;
	private PhongBan phongban;
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
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public Date getNgayVaolam() {
		return ngayVaolam;
	}
	public void setNgayVaolam(Date ngayVaolam) {
		this.ngayVaolam = ngayVaolam;
	}
	public int getCMND() {
		return CMND;
	}
	public void setCMND(int cMND) {
		CMND = cMND;
	}
	public int getSDT() {
		return SDT;
	}
	public void setSDT(int sDT) {
		SDT = sDT;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public boolean isQuanLy() {
		return quanLy;
	}
	public void setQuanLy(boolean quanLy) {
		this.quanLy = quanLy;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public TaiKhoan getTaikhoan() {
		return taikhoan;
	}
	public void setTaikhoan(TaiKhoan taikhoan) {
		this.taikhoan = taikhoan;
	}
	public PhongBan getPhongban() {
		return phongban;
	}
	public void setPhongban(PhongBan phongban) {
		this.phongban = phongban;
	}
	
	public NhanVien() {
		super();
	}
	public NhanVien(String maNV, String tenNV, Date ngaySinh, Date ngayVaolam, int cMND, int sDT, boolean trangThai,
			boolean quanLy, boolean gioiTinh) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.ngayVaolam = ngayVaolam;
		CMND = cMND;
		SDT = sDT;
		this.trangThai = trangThai;
		this.quanLy = quanLy;
		this.gioiTinh = gioiTinh;
	}
	
	public NhanVien(String maNV, String tenNV, Date ngaySinh, Date ngayVaolam, int cMND, int sDT, boolean gioiTinh) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.ngayVaolam = ngayVaolam;
		CMND = cMND;
		SDT = sDT;
		this.gioiTinh = gioiTinh;
	}
	
	
	public NhanVien(String maNV, String tenNV) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
	}
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	public NhanVien(String maNV, String tenNV, Date ngaySinh, Date ngayVaolam, int cMND, int sDT, boolean gioiTinh,
			PhongBan phongban) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.ngayVaolam = ngayVaolam;
		CMND = cMND;
		SDT = sDT;
		this.gioiTinh = gioiTinh;
		this.phongban = phongban;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", ngaySinh=" + ngaySinh + ", ngayVaolam=" + ngayVaolam
				+ ", CMND=" + CMND + ", SDT=" + SDT + ", trangThai=" + trangThai + ", quanLy=" + quanLy + ", gioiTinh="
				+ gioiTinh + ", taikhoan=" + taikhoan + ", phongban=" + phongban + "]";
	}
	
}
