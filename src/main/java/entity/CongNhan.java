package entity;

public class CongNhan {
		private String maNV;
		private String tenNV;
		private String tenSP;
		private String tenCongDoan;
		private int soLuong;
		private int thang;
		private int nam;
		private double thuNhapKhac;
		public CongNhan() {
			// TODO Auto-generated constructor stub
		}
		public CongNhan(String maNV, String tenNV, String tenSP, String tenCongDoan, int soLuong, int thang, int nam,
				double thuNhapKhac) {
			super();
			this.maNV = maNV;
			this.tenNV = tenNV;
			this.tenSP = tenSP;
			this.tenCongDoan = tenCongDoan;
			this.soLuong = soLuong;
			this.thang = thang;
			this.nam = nam;
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
		public int getSoLuong() {
			return soLuong;
		}
		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
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
		public double getThuNhapKhac() {
			return thuNhapKhac;
		}
		public void setThuNhapKhac(double thuNhapKhac) {
			this.thuNhapKhac = thuNhapKhac;
		}
		@Override
		public String toString() {
			return "CongNhan [maNV=" + maNV + ", tenNV=" + tenNV + ", tenSP=" + tenSP + ", tenCongDoan=" + tenCongDoan
					+ ", soLuong=" + soLuong + ", thang=" + thang + ", nam=" + nam + ", thuNhapKhac=" + thuNhapKhac
					+ "]";
		}
		
		
		
}
