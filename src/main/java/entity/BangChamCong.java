package entity;

public class BangChamCong {
		private String maNV;
		private String tenNV;
		private double heSoluong;
		private int nghiKhongPhep;
		private int soCongNgayLe;
		private int soCongngaythuong;
		private int thang;
		private int nam;
		private int soCongchuan;
		private double thuNhapKhac;
		public BangChamCong(String maNV, String tenNV, double heSoluong, int nghiKhongPhep, int soCongNgayLe,
				int soCongngaythuong, int thang, int nam, int soCongchuan, double thuNhapKhac) {
			super();
			this.maNV = maNV;
			this.tenNV = tenNV;
			this.heSoluong = heSoluong;
			this.nghiKhongPhep = nghiKhongPhep;
			this.soCongNgayLe = soCongNgayLe;
			this.soCongngaythuong = soCongngaythuong;
			this.thang = thang;
			this.nam = nam;
			this.soCongchuan = soCongchuan;
			this.thuNhapKhac = thuNhapKhac;
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
		public int getNghiKhongPhep() {
			return nghiKhongPhep;
		}
		public void setNghiKhongPhep(int nghiKhongPhep) {
			this.nghiKhongPhep = nghiKhongPhep;
		}
		public int getSoCongNgayLe() {
			return soCongNgayLe;
		}
		public void setSoCongNgayLe(int soCongNgayLe) {
			this.soCongNgayLe = soCongNgayLe;
		}
		public int getSoCongngaythuong() {
			return soCongngaythuong;
		}
		public void setSoCongngaythuong(int soCongngaythuong) {
			this.soCongngaythuong = soCongngaythuong;
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
		@Override
		public String toString() {
			return "BangChamCong [maNV=" + maNV + ", tenNV=" + tenNV + ", heSoluong=" + heSoluong + ", nghiKhongPhep="
					+ nghiKhongPhep + ", soCongNgayLe=" + soCongNgayLe + ", soCongngaythuong=" + soCongngaythuong
					+ ", thang=" + thang + ", nam=" + nam + ", soCongchuan=" + soCongchuan + ", thuNhapKhac="
					+ thuNhapKhac + "]";
		}
		
}
