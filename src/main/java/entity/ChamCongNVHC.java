package entity;

import java.util.Objects;

public class ChamCongNVHC {
		private String maChamCong;
		private NhanVien maNV;
		private double heSoLuong;
		private int soCongNgayLe;
		private int soCongNgayThuong;
		private int nghiKhongPhep;
		private int thang;
		private int nam;
		

		public ChamCongNVHC(String maChamCong, NhanVien maNV, double heSoLuong, int soCongNgayLe, int soCongNgayThuong,
				int nghiKhongPhep, int thang, int nam) {
			super();
			this.maChamCong = maChamCong;
			this.maNV = maNV;
			this.heSoLuong = heSoLuong;
			this.soCongNgayLe = soCongNgayLe;
			this.soCongNgayThuong = soCongNgayThuong;
			this.nghiKhongPhep = nghiKhongPhep;
			this.thang = thang;
			this.nam = nam;
		}

		public ChamCongNVHC(double heSoLuong, int soCongNgayLe, int soCongNgayThuong, int nghiKhongPhep, int thang,
				int nam) {
			super();
			this.heSoLuong = heSoLuong;
			this.soCongNgayLe = soCongNgayLe;
			this.soCongNgayThuong = soCongNgayThuong;
			this.nghiKhongPhep = nghiKhongPhep;
			this.thang = thang;
			this.nam = nam;
		}

		public ChamCongNVHC(String maChamCong) {
			super();
			this.maChamCong = maChamCong;
		}

		public ChamCongNVHC(String maChamCong, double heSoLuong, int soCongNgayLe, int soCongNgayThuong,
				int nghiKhongPhep, int thang, int nam) {
			super();
			this.maChamCong = maChamCong;
			this.heSoLuong = heSoLuong;
			this.soCongNgayLe = soCongNgayLe;
			this.soCongNgayThuong = soCongNgayThuong;
			this.nghiKhongPhep = nghiKhongPhep;
			this.thang = thang;
			this.nam = nam;
		}

		public ChamCongNVHC() {
			// TODO Auto-generated constructor stub
		}
		public String getMaChamCong() {
			return maChamCong;
		}
		public String setMaChamCong(String maChamCong) {
			return this.maChamCong = maChamCong;
		}
		public NhanVien getMaNV() {
			return maNV;
		}
		public void setMaNV(NhanVien maNV) {
			this.maNV = maNV;
		}
		public double getHeSoLuong() {
			return heSoLuong;
		}
		public void setHeSoLuong(double heSoLuong) {
			this.heSoLuong = heSoLuong;
		}
		public int getSoCongNgayLe() {
			return soCongNgayLe;
		}
		public void setSoCongNgayLe(int soCongNgayLe) {
			this.soCongNgayLe = soCongNgayLe;
		}
		public int getSoCongNgayThuong() {
			return soCongNgayThuong;
		}
		public void setSoCongNgayThuong(int soCongNgayThuong) {
			this.soCongNgayThuong = soCongNgayThuong;
		}
		public int getNghiKhongPhep() {
			return nghiKhongPhep;
		}
		public void setNghiKhongPhep(int nghiKhongPhep) {
			this.nghiKhongPhep = nghiKhongPhep;
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
		public int hashCode() {
			return Objects.hash(maChamCong);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ChamCongNVHC other = (ChamCongNVHC) obj;
			return Objects.equals(maChamCong, other.maChamCong);
		}
		@Override
		public String toString() {
			return "ChamCongNCVHC_DAO [maChamCong=" + maChamCong + ", maNV=" + maNV + ", heSoLuong=" + heSoLuong
					+ ", soCongNgayLe=" + soCongNgayLe + ", soCongNgayThuong=" + soCongNgayThuong + ", nghiKhongPhep="
					+ nghiKhongPhep + ", thang=" + thang + ", nam=" + nam + "]";
		}
		
		
}
