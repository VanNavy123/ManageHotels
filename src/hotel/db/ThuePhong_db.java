package hotel.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hotel.oop.Khach;
import hotel.oop.Phong;
import hotel.oop.ThuePhong;

public class ThuePhong_db {
	public static boolean insert(ThuePhong thuePh) {
		//Nội dung hàm insert
		boolean tmp = false;
		Connection connect = null;
		PreparedStatement prStmt = null;
		String insertSQL = "INSERT INTO ThuePhong VALUES (?,?,?)";
		
		try {
			connect = Provide_db.getConnection();
	
			prStmt = connect.prepareStatement(insertSQL);
			prStmt.setString(1, thuePh.get_maThuePhong());
			prStmt.setString(2, thuePh.get_maKhachThue());
			prStmt.setInt(3, thuePh.get_tongChiPhi());
			
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
	
	public static ArrayList<ThuePhong> Select() {
		ArrayList<ThuePhong> List_TP = new ArrayList<ThuePhong>();
		
		return List_TP;
	}
	
	public static String SelectMTP_max() {
		String tmp = "";
		String query = "SELECT TOP 1 MaThuePhong FROM dbo.ThuePhong ORDER BY MaThuePhong DESC";
		
		Connection connect = null;
		PreparedStatement prStmt = null;

		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				tmp = rs.getString("MaThuePhong");
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
	
	public static String selectMaThuePhong(String maKhTh) {
		String maThuePhong = "";
		String query = "SELECT * FROM ThuePhong WHERE MaKhachThue = ?";
		Connection connect = null;
		PreparedStatement prStmt = null;
		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			prStmt.setString(1, maKhTh);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				 maThuePhong = rs.getString("MaThuePhong");	
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
		return maThuePhong;
	}
	public static boolean kiemTraTonTaiMTP(String maTP) {
		Connection connect = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		boolean existKhachFlag = true;
		String selectKhachSql = "SELECT COUNT(*) FROM ThuePhong WHERE MaThuePhong = ?";
		
		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(selectKhachSql);
			prStmt.setString(1, maTP);
			
			rs = prStmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) == 0) {
					existKhachFlag = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Provide_db.closeStatment(prStmt);
			Provide_db.closeConnection(connect);
		}
		
		return existKhachFlag;
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
