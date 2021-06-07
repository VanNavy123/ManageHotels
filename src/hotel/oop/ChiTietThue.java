package hotel.oop;

import java.sql.Date;

public class ChiTietThue {
	private String maThuePhong;
	private String maPhong;
	private Date ngayThue;
	private Date ngayTra;

	
	public String get_maThuePhong() {
		return maThuePhong;
	}


	public void set_maThuePhong(String maThuePhong) {
		this.maThuePhong = maThuePhong;
	}


	public String get_maPhong() {
		return maPhong;
	}


	public void set_maPhong(String maPhong) {
		this.maPhong = maPhong;
	}


	public Date get_ngayThue() {
		return ngayThue;
	}


	public void set_ngayThue(Date ngayThue) {
		this.ngayThue = ngayThue;
	}
	
	public Date get_ngayTra() {
		return ngayTra;
	}


	public void set_ngayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}
	
	
	public ChiTietThue(String maThuePhong, String maPhong, Date ngayThue, Date ngayTra) {
		super();
		this.maThuePhong = maThuePhong;
		this.maPhong = maPhong;
		this.ngayThue = ngayThue;
		this.ngayTra = ngayTra;
	}


	public ChiTietThue() {
		super();
	}
}
