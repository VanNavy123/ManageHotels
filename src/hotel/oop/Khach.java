package hotel.oop;

import java.sql.Date;

public class Khach {
	private String maKhach;
	private String hoTen;
	private String cmnd;
	private String diaChi;
	private Date ngaySinh;

	
	/**
	 * @return the maKhach
	 */
	public String getMaKhach() {
		return maKhach;
	}
	/**
	 * @param maKhach the maKhach to set
	 */
	public void setMaKhach(String maKhach) {
		this.maKhach = maKhach;
	}
	/**
	 * @return the hoTen
	 */
	public String getHoTen() {
		return hoTen;
	}
	/**
	 * @param hoTen the hoTen to set
	 */
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	/**
	 * @return the cmnd
	 */
	public String getCmnd() {
		return cmnd;
	}
	/**
	 * @param cmnd the cmnd to set
	 */
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	/**
	 * @return the diaChi
	 */
	public String getDiaChi() {
		return diaChi;
	}
	/**
	 * @param diaChi the diaChi to set
	 */
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	/**
	 * @return the ngaySinh
	 */
	public Date getNgaySinh() {
		return ngaySinh;
	}
	/**
	 * @param ngaySinh the ngaySinh to set
	 */
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	/**
	 * @param maKhach
	 * @param hoTen
	 * @param cmnd
	 * @param diaChi
	 * @param ngaySinh
	 */
	
	public Khach(String maKhach, String hoTen, String cmnd, String diaChi, Date ngaySinh) {
		super();
		this.maKhach = maKhach;
		this.hoTen = hoTen;
		this.cmnd = cmnd;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
	}
	/**
	 * 
	 */
	public Khach() {
		super();
	}
	@Override
	public String toString() {
		return maKhach + "    " +  hoTen + "    "+ cmnd + "    " + diaChi
				+ "    "+ ngaySinh;
	}
	
}
