package entity;

public class ListSearchCongNhan {
		private String maNV;
		private String tenNV;
		private String tenSP;
		private String tenCongDoan;
		public ListSearchCongNhan() {
			// TODO Auto-generated constructor stub
		}
		public ListSearchCongNhan(String maNV, String tenNV, String tenSP, String tenCongDoan) {
			super();
			this.maNV = maNV;
			this.tenNV = tenNV;
			this.tenSP = tenSP;
			this.tenCongDoan = tenCongDoan;
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
		@Override
		public String toString() {
//			return "ListSearchCongNhan [maNV=" + maNV + ", tenNV=" + tenNV + ", tenSP=" + tenNV + ", tenCongDoan="
//					+ tenCongDoan + "]";
			return  maNV   +"   |   "+   tenNV;
		}
		
		
		
}
