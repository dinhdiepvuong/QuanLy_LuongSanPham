package entity;

public class ListSearchNhanVien {
		private String maNV;
		private String tenNV;
		public ListSearchNhanVien() {
			// TODO Auto-generated constructor stub
		}
		public ListSearchNhanVien(String maNV, String tenNV) {
			super();
			this.maNV = maNV;
			this.tenNV = tenNV;
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
		@Override
		public String toString() {
//			return "ListSearchNhanVien [maNV=" + maNV + ", tenNV=" + tenNV + "]";
			return maNV   +"   |   "+   tenNV;
		}
		
}
