package entity;

public class LuongCongNhan {
			private String maNV;
			private String tenNV;
			private String tenSP;
			private String tenCongDoan;
			private int soluong;
			private double donGia;
			private double phuCap;
			private double thuong;
			private double luongLamThem;
			private double thuNhapKhac;
			private int thang;
			private int nam;
			private double tongLuong;
			private boolean tinhTrang;
			public LuongCongNhan() {
				// TODO Auto-generated constructor stub
			}
			public LuongCongNhan(String maNV, String tenNV, String tenSP, String tenCongDoan, int soluong,
					double donGia, double phuCap, double thuong, double luongLamThem, double thuNhapKhac, int thang,
					int nam, double tongLuong, boolean tinhTrang) {
				super();
				this.maNV = maNV;
				this.tenNV = tenNV;
				this.tenSP = tenSP;
				this.tenCongDoan = tenCongDoan;
				this.soluong = soluong;
				this.donGia = donGia;
				this.phuCap = phuCap;
				this.thuong = thuong;
				this.luongLamThem = luongLamThem;
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
			public double getDonGia() {
				return donGia;
			}
			public void setDonGia(double donGia) {
				this.donGia = donGia;
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
			public double getLuongLamThem() {
				return luongLamThem;
			}
			public void setLuongLamThem(double luongLamThem) {
				this.luongLamThem = luongLamThem;
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
				return "LuongCongNhan [maNV=" + maNV + ", tenNV=" + tenNV + ", tenSP=" + tenSP + ", tenCongDoan="
						+ tenCongDoan + ", soluong=" + soluong + ", donGia=" + donGia + ", phuCap=" + phuCap
						+ ", thuong=" + thuong + ", luongLamThem=" + luongLamThem + ", thuNhapKhac=" + thuNhapKhac
						+ ", thang=" + thang + ", nam=" + nam + ", tongLuong=" + tongLuong + ", tinhTrang=" + tinhTrang
						+ "]";
			}
			
			
			
}
