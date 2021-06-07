package hotel.oop;


public class LoaiPhong {
	private String maLoaiPhong;
	private String loaiPhong;
	private int giaPhong;
	private int soNguoiToiDa;

	
	public String get_maLoaiPhong() {
		return maLoaiPhong;
	}


	public void set_maLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}


	public String get_loaiPhong() {
		return loaiPhong;
	}


	public void set_loaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}


	public int get_giaPhong() {
		return giaPhong;
	}


	public void set_giaPhong(int giaPhong) {
		this.giaPhong = giaPhong;
	}
	
	public int get_soNguoiToiDa() {
		return soNguoiToiDa;
	}


	public void set_soNguoiToiDa(int soNguoiToiDa) {
		this.soNguoiToiDa = soNguoiToiDa;
	}
	
	
	public LoaiPhong(String maLoaiPhong, String loaiPhong, int giaPhong, int soNguoiToiDa) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.loaiPhong = loaiPhong;
		this.giaPhong = giaPhong;
		this.soNguoiToiDa = soNguoiToiDa;
	}


	public LoaiPhong() {
		super();
	}
}
