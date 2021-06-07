package hotel.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hotel.oop.Khach;
import hotel.oop.LoaiPhong;
import hotel.oop.Phong;

public class LoaiPhong_db {
	public static boolean insert(LoaiPhong lp) {
		//Nội dung hàm insert
		boolean tmp = false;
		Connection connect = null;
		PreparedStatement prStmt = null;
		String insertSQL = "INSERT INTO LoaiPhong VALUES (?,?,?,?)";
		
		try {
			connect = Provide_db.getConnection();
	
			prStmt = connect.prepareStatement(insertSQL);
			prStmt.setString(1, lp.get_maLoaiPhong());
			prStmt.setString(2, lp.get_loaiPhong());
			prStmt.setInt(3, lp.get_giaPhong());
			prStmt.setInt(4, lp.get_soNguoiToiDa());
			
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
	
	public static ArrayList<LoaiPhong> Select() {
		ArrayList<LoaiPhong> list_LP = new ArrayList<LoaiPhong>();
		String query = "Select * from LoaiPhong";
		
		Connection connect = null;
		PreparedStatement prStmt = null;

		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				String maLoaiPhong = rs.getString("MaLoaiPhong");
				String loaiPhong = rs.getString("LoaiPhong");
				int giaPhong = rs.getInt("GiaPhong");
				int soNguoiToiDa = rs.getInt("SoNguoiToiDa");
				
				LoaiPhong ph = new LoaiPhong(maLoaiPhong, loaiPhong, giaPhong, soNguoiToiDa);
				list_LP.add(ph);
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
		return list_LP;
	}
	
	public static LoaiPhong selectOneLP(String ma) {
		LoaiPhong ph = null;
		String query = "SELECT * FROM LoaiPhong WHERE MaLoaiPhong = ?";
		Connection connect = null;
		PreparedStatement prStmt = null;
		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			prStmt.setString(1, ma);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				String maLoaiPhong = rs.getString("MaLoaiPhong");
				String loaiPhong = rs.getString("LoaiPhong");
				int giaPhong = rs.getInt("GiaPhong");
				int soNguoiToiDa = rs.getInt("SoNguoiToiDa");
				
				ph = new LoaiPhong(maLoaiPhong, loaiPhong, giaPhong, soNguoiToiDa);
				
			}
		
		} 
		catch (SQLException e) 
		{
			ph = null;
			e.printStackTrace();
		} 
		finally 
		{
			Provide_db.closeStatment(prStmt);
			Provide_db.closeConnection(connect);
		}
		return ph;
	}
	
	public static String selectMLP_max() {
		String tmp = "";
		String query = "SELECT TOP 1 MaLoaiPhong FROM dbo.LoaiPhong ORDER BY MaLoaiPhong DESC";
		
		Connection connect = null;
		PreparedStatement prStmt = null;

		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				tmp = rs.getString("MaLoaiPhong");
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
		
		return tmp;
	}
	
	public static boolean update(LoaiPhong lp) 
	{
		boolean tmp = false;
		Connection connect = null;
		PreparedStatement prStmt = null;
		String query = "UPDATE LoaiPhong SET LoaiPhong = ?, GiaPhong = ?, SoNguoiToiDa = ? WHERE MaLoaiPhong = ?";
		
		try 
		{
			connect = Provide_db.getConnection();
			PreparedStatement preparedStm = connect.prepareStatement(query);
			preparedStm.setString(1, lp.get_loaiPhong());
			preparedStm.setInt(2, lp.get_giaPhong());
			preparedStm.setInt(3, lp.get_soNguoiToiDa());
//			int numberRecordUpdated = preparedStm.executeUpdate();//dung cho update, insert, delete
			if (preparedStm.executeUpdate() > 0)
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
	
	public static boolean delete() {
		//Nội dung hàm Delete
		
		return true;
	}
}
