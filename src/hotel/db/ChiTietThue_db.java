package hotel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


import hotel.oop.ChiTietThue;
import hotel.oop.ThuePhong;

public class ChiTietThue_db {
	public static boolean insert(ChiTietThue chiTietthuePh) {
		//Nội dung hàm insert
		boolean tmp = false;
		Connection connect = null;
		PreparedStatement prStmt = null;
		String insertSQL = "INSERT INTO ChiTietThue VALUES (?,?,?,?)";
		
		try {
			connect = Provide_db.getConnection();
	
			prStmt = connect.prepareStatement(insertSQL);
			prStmt.setString(1, chiTietthuePh.get_maThuePhong());
			prStmt.setString(2, chiTietthuePh.get_maPhong());
			prStmt.setDate(3, chiTietthuePh.get_ngayThue());
			prStmt.setDate(4, chiTietthuePh.get_ngayTra());
			
			int numerInserted = prStmt.executeUpdate();
			if (numerInserted > 0) 
			{
				tmp = true;
			} 
			else 
			{
				tmp = false;
			}
			
		} 
		catch (SQLException e) 
		{
			tmp = false;
			e.printStackTrace();
		} 
		finally 
		{
			Provide_db.closeStatment(prStmt);
			Provide_db.closeConnection(connect);
		}
		return tmp;
	}
	public static ArrayList<ChiTietThue> Select() {
		ArrayList<ChiTietThue> list_CTT = new ArrayList<ChiTietThue>();
		
		return list_CTT;
	}
	
	public static String selectMaPhong(String maThPh) {
		String maPhong = "";
		String query = "SELECT * FROM ChiTietThue WHERE MaThuePhong = ?";
		Connection connect = null;
		PreparedStatement prStmt = null;
		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			prStmt.setString(1, maThPh);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				 maPhong = rs.getString("MaThuePhong");	
			}
		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			Provide_db.closeStatment(prStmt);
			Provide_db.closeConnection(connect);
		}
		return maPhong;
	}
	
	public static void  selectThuePhong(String maThuePhong) {
		String query = "Select ThuePhong.TongChiPhi, ChiTietThue.MaPhong, Phong.MaloaiPhong, LoaiPhong.GiaPhong, ChiTietThue.NgayThue, ChiTietThue.NgayTra "
				+ "from ChiTietThue, Phong, LoaiPhong, ThuePhong "
				+ "where ThuePhong.MaThuePhong = ? "
				+ "and ThuePhong.MaThuePhong = ChiTietThue.MaThuePhong "
				+ "and ChiTietThue.MaPhong = Phong.MaPhong "
				+ "and Phong.MaLoaiPhong = LoaiPhong.MaLoaiPhong";
		
		Connection connect = null;
		PreparedStatement prStmt = null;

		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			prStmt.setString(1, maThuePhong);
			ResultSet rs = prStmt.executeQuery();
			int TongTien = 0;
			while (rs.next()) {
				String maPhong = rs.getString("MaPhong");
				String maLoaiPhong = rs.getString("MaLoaiPhong");
				int giaPhong = rs.getInt("GiaPhong");
				Date ngayThue = rs.getDate("NgayThue");
				Date ngayTra = rs.getDate("NgayTra");
				int tongChiPhi = rs.getInt("TongChiPhi");
				System.out.print(maPhong);
				System.out.print(" "+ maLoaiPhong);
				System.out.print(" " + giaPhong);
				System.out.print("    "+ ngayThue);
				System.out.print("       "+ngayTra);
				System.out.print("       "+ date_Date(ngayThue, ngayTra) * giaPhong);
				TongTien += date_Date(ngayThue, ngayTra) * giaPhong;
				System.out.println("\n-------------------------------------------------------------------------------");
			}
			System.out.print("Tong so tien can thanh toan: "+ TongTien);
		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			Provide_db.closeStatment(prStmt);
			Provide_db.closeConnection(connect);
		}
	}
	
	public static int date_Date (Date ngayThue, Date ngayTra)
	{
		long diff = ngayTra.getTime() - ngayThue.getTime();  //don vi la mini giay
		int soNgay = (int)(diff / (24*60 * 60 * 1000));
		if (soNgay == 0) return 1;
		else return soNgay;
	}
		
	public static boolean Update() {
		//Nội dung hàm Update
		
		return true;
	}
	
	public static boolean Delete() {
		//Nội dung hàm Delete
		
		return true;
	}
}
