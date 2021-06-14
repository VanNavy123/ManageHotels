package hotel.oop;

public class ChiTietO {
	private String maThuePhong;
	private String maKhach;
	private String maPhong;
	private int truongNhom;

	
	public String get_maThuePhong() {
		return maThuePhong;
	}


	public void set_maThuePhong(String maThuePhong) {
		this.maThuePhong = maThuePhong;
	}


	public String get_maKhach() {
		return maKhach;
	}


	public void set_maKhach(String maKhach) {
		this.maKhach = maKhach;
	}


	public String get_maPhong() {
		return maPhong;
	}


	public void set_maPhong(String maPhong) {
		this.maPhong = maPhong;
	}


	public int get_truongNhom() {
		return truongNhom;
	}


	public void set_truongNhom(int truongNhom) {
		this.truongNhom = truongNhom;
	}
	
	
	public ChiTietO(String maThuePhong, String maKhach, String maPhong, int truongNhom) {
		super();
		this.maThuePhong = maThuePhong;
		this.maKhach = maKhach;
		this.maPhong = maPhong;
		this.truongNhom = truongNhom;
	}


	public ChiTietO() {
		super();
	}
}
