package entity;

public class ThuNhapKhac {
		private String maThuNhapKhac;
		private double luongLamThem;
		private double phuCap;
		private double thuong;
		private NhanVien maNv;
		private int thang;
		private int nam;
		public ThuNhapKhac() {
			// TODO Auto-generated constructor stub
		}
		
		
		


		public ThuNhapKhac(double luongLamThem, double phuCap, double thuong, NhanVien maNv, int thang, int nam) {
			super();
			this.luongLamThem = luongLamThem;
			this.phuCap = phuCap;
			this.thuong = thuong;
			this.maNv = maNv;
			this.thang = thang;
			this.nam = nam;
		}





		public ThuNhapKhac(String maThuNhapKhac, double luongLamThem, double phuCap, double thuong, NhanVien maNv,
				int thang, int nam) {
			super();
			this.maThuNhapKhac = maThuNhapKhac;
			this.luongLamThem = luongLamThem;
			this.phuCap = phuCap;
			this.thuong = thuong;
			this.maNv = maNv;
			this.thang = thang;
			this.nam = nam;
		}





		public ThuNhapKhac(String maThuNhapKhac, double luongLamThem, double phuCap, double thuong, int thang,
				int nam) {
			super();
			this.maThuNhapKhac = maThuNhapKhac;
			this.luongLamThem = luongLamThem;
			this.phuCap = phuCap;
			this.thuong = thuong;
			this.thang = thang;
			this.nam = nam;
		}


		public ThuNhapKhac(double luongLamThem) {
			super();
			this.luongLamThem = luongLamThem;
		}
		public ThuNhapKhac(String maThuNhapKhac) {
			super();
			this.maThuNhapKhac = maThuNhapKhac;
		}
		public String getMaThuNhapKhac() {
			return maThuNhapKhac;
		}
		public void setMaThuNhapKhac(String maThuNhapKhac) {
			this.maThuNhapKhac = maThuNhapKhac;
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
		public NhanVien getMaNv() {
			return maNv;
		}
		public void setMaNv(NhanVien maNv) {
			this.maNv = maNv;
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
			return "ThuNhapKhac [maThuNhapKhac=" + maThuNhapKhac + ", luongLamThem=" + luongLamThem + ", phuCap="
					+ phuCap + ", thuong=" + thuong + ", maNv=" + maNv + ", thang=" + thang + ", nam=" + nam + "]";
		}
		
}
