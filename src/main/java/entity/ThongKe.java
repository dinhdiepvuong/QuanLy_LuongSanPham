package entity;

public class ThongKe {
	private int thang;
	private long Tongluong;
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public double getTongluong() {
		return Tongluong;
	}
	public void setTongluong(long tongluong) {
		Tongluong = tongluong;
	}
	public ThongKe(int thang, long tongluong) {
		super();
		this.thang = thang;
		Tongluong = tongluong;
	}
	@Override
	public String toString() {
		return "ThongKe [thang=" + thang + ", Tongluong=" + Tongluong + "]";
	}
	
}
