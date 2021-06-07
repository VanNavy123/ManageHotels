package hotel.oop;

public class Phong {
	private String maPhong;
	private String trangThai;
	private String maLoaiPhong;
	private String moTa;

	
	public String get_maPhong() {
		return maPhong;
	}


	public void set_maPhong(String maPhong) {
		this.maPhong = maPhong;
	}


	public String get_trangThai() {
		return trangThai;
	}


	public void set_trangThai(String trangThai) {
		this.trangThai = trangThai;
	}


	public String get_maLoaiPhong() {
		return maLoaiPhong;
	}


	public void set_maLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}
	
	public String get_moTa() {
		return moTa;
	}


	public void set_moTa(String moTa) {
		this.moTa = moTa;
	}
	

	public Phong(String maPhong, String trangThai, String maLoaiPhong, String moTa) {
		super();
		this.maPhong = maPhong;
		this.trangThai = trangThai;
		this.maLoaiPhong = maLoaiPhong;
		this.moTa = moTa;
	}


	public Phong() {
		super();
	}
}
