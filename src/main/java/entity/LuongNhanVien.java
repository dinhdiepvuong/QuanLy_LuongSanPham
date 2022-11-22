package entity;

public class LuongNhanVien {
		private String maNV;
		private String tenNV;
		private double heSoLuong;
		private int congNgayThuong;
		private int congNgayLe;
		private int nghiKhongPhep;
		private int soCongChuan;
		private double luongLamThem;
		private double thuong;
		private double phuCap;
		private double thuNhapKhac;
		private int thang;
		private int nam;
		private double tongLuong;
		private boolean tinhTrang;
		public LuongNhanVien() {
			// TODO Auto-generated constructor stub
		}
		public LuongNhanVien(String maNV, String tenNV, double heSoLuong, int congNgayThuong, int congNgayLe,
				int nghiKhongPhep, int soCongChuan, double luongLamThem, double thuong, double phuCap,
				double thuNhapKhac, int thang, int nam, double tongLuong, boolean tinhTrang) {
			super();
			this.maNV = maNV;
			this.tenNV = tenNV;
			this.heSoLuong = heSoLuong;
			this.congNgayThuong = congNgayThuong;
			this.congNgayLe = congNgayLe;
			this.nghiKhongPhep = nghiKhongPhep;
			this.soCongChuan = soCongChuan;
			this.luongLamThem = luongLamThem;
			this.thuong = thuong;
			this.phuCap = phuCap;
			this.thuNhapKhac = thuNhapKhac;
			this.thang = thang;
			this.nam = nam;
			this.tongLuong = tongLuong;
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
		public double getHeSoLuong() {
			return heSoLuong;
		}
		public void setHeSoLuong(double heSoLuong) {
			this.heSoLuong = heSoLuong;
		}
		public int getCongNgayThuong() {
			return congNgayThuong;
		}
		public void setCongNgayThuong(int congNgayThuong) {
			this.congNgayThuong = congNgayThuong;
		}
		public int getCongNgayLe() {
			return congNgayLe;
		}
		public void setCongNgayLe(int congNgayLe) {
			this.congNgayLe = congNgayLe;
		}
		public int getNghiKhongPhep() {
			return nghiKhongPhep;
		}
		public void setNghiKhongPhep(int nghiKhongPhep) {
			this.nghiKhongPhep = nghiKhongPhep;
		}
		public int getSoCongChuan() {
			return soCongChuan;
		}
		public void setSoCongChuan(int soCongChuan) {
			this.soCongChuan = soCongChuan;
		}
		public double getLuongLamThem() {
			return luongLamThem;
		}
		public void setLuongLamThem(double luongLamThem) {
			this.luongLamThem = luongLamThem;
		}
		public double getThuong() {
			return thuong;
		}
		public void setThuong(double thuong) {
			this.thuong = thuong;
		}
		public double getPhuCap() {
			return phuCap;
		}
		public void setPhuCap(double phuCap) {
			this.phuCap = phuCap;
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
		public boolean isTinhTrang() {
			return tinhTrang;
		}
		public void setTinhTrang(boolean tinhTrang) {
			this.tinhTrang = tinhTrang;
		}
		@Override
		public String toString() {
			return "LuongNhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", heSoLuong=" + heSoLuong + ", congNgayThuong="
					+ congNgayThuong + ", congNgayLe=" + congNgayLe + ", nghiKhongPhep=" + nghiKhongPhep
					+ ", soCongChuan=" + soCongChuan + ", luongLamThem=" + luongLamThem + ", thuong=" + thuong
					+ ", phuCap=" + phuCap + ", thuNhapKhac=" + thuNhapKhac + ", thang=" + thang + ", nam=" + nam
					+ ", tongLuong=" + tongLuong + ", tinhTrang=" + tinhTrang + "]";
		}
		
}
