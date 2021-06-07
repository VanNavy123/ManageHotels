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

public class Phong_db {
	public static boolean insert(Phong ph) {
		//Nội dung hàm insert
		boolean tmp = false;
		Connection connect = null;
		PreparedStatement prStmt = null;
		String insertSQL = "INSERT INTO Phong VALUES (?,?,?,?)";
		
		try {
			connect = Provide_db.getConnection();
	
			prStmt = connect.prepareStatement(insertSQL);
			prStmt.setString(1, ph.get_maPhong());
			prStmt.setInt(2, ph.get_trangThai());
			prStmt.setString(3, ph.get_maLoaiPhong());
			prStmt.setString(4, ph.get_moTa());
			
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
	
	public static ArrayList<Phong> Select() {
		ArrayList<Phong> List_P = new ArrayList<Phong>();
		String query = "Select * from Phong";
		
		Connection connect = null;
		PreparedStatement prStmt = null;

		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString("MaPhong");
				int trangThai = rs.getInt("TrangThai");
				String maLoaiPhong = rs.getString("MaLoaiPhong");
				String moTa = rs.getString("MoTa");
				
				Phong ph = new Phong(maPhong, trangThai, maLoaiPhong, moTa);
				List_P.add(ph);
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
		return List_P;
	}
	
	public static String SelectMP_max() {
		String tmp = "";
		String query = "SELECT TOP 1 MaPhong FROM dbo.Phong ORDER BY MaPhong DESC";
		
		Connection connect = null;
		PreparedStatement prStmt = null;

		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				tmp = rs.getString("MaPhong");
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
	
	public static Phong selectOneMP(String ma) {
		Phong ph = null;
		String query = "SELECT * FROM Phong WHERE MaPhong = ?";
		Connection connect = null;
		PreparedStatement prStmt = null;
		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			prStmt.setString(1, ma);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString("MaPhong");
				int trangThai = rs.getInt("TrangThai");
				String maLoaiPhong = rs.getString("MaLoaiPhong");
				String moTa = rs.getString("MoTa");
				
				ph = new Phong(maPhong, trangThai, maLoaiPhong, moTa);
				
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
	
	public static boolean Update(Phong ph) 
	{
		boolean tmp = false;
		Connection connect = null;
		PreparedStatement prStmt = null;
		String query = "UPDATE Phong SET TrangThai = ?, MaLoaiPhong = ?, MoTa = ?  WHERE MaPhong = ?";
		
		try 
		{
			connect = Provide_db.getConnection();
			PreparedStatement preparedStm = connect.prepareStatement(query);
			
			preparedStm.setInt(1, ph.get_trangThai());
			preparedStm.setString(2, ph.get_maLoaiPhong() );
			preparedStm.setString(3, ph.get_moTa() );
			preparedStm.setString(4, ph.get_maPhong());
			
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
	
	//Ham Xoa
	public static boolean Delete(String ma) 
	{
		boolean tmp = false;
		Connection connect = null;
		PreparedStatement prStmt = null;
		String insertSQL = "DELETE FROM Phong WHERE MaPhong = ?";
		
		try 
		{
			connect = Provide_db.getConnection();
			
			prStmt = connect.prepareStatement(insertSQL);
			prStmt.setString(1, ma);
			if (prStmt.executeUpdate() > 0)
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
}
