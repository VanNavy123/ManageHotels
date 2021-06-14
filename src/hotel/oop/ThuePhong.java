package hotel.oop;

public class ThuePhong {
	private String maThuePhong;
	private String maKhachThue;
	private int tongChiPhi;

	
	public String get_maThuePhong() {
		return maThuePhong;
	}


	public void set_maThuePhong(String maThuePhong) {
		this.maThuePhong = maThuePhong;
	}


	public String get_maKhachThue() {
		return maKhachThue;
	}


	public void set_maKhachThue(String maKhachThue) {
		this.maKhachThue = maKhachThue;
	}


	public int get_tongChiPhi() {
		return tongChiPhi;
	}


	public void set_tongChiPhi(int tongChiPhi) {
		this.tongChiPhi = tongChiPhi;
	}
	
	
	public ThuePhong(String maThuePhong, String maKhachThue, int tongChiPhi) {
		super();
		this.maThuePhong = maThuePhong;
		this.maKhachThue = maKhachThue;
		this.tongChiPhi = tongChiPhi;
	}


	public ThuePhong() {
		super();
	}


	@Override
	public String toString() {
		return maThuePhong + " " + maKhachThue + " "+  tongChiPhi;
	}
	
}
