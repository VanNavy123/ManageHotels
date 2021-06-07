package hotel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hotel.oop.ThuePhong;

public class ThuePhong_db {
	public static boolean Insert() {
		//Nội dung hàm insert
		
		return true;
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
	
	public static boolean Update() {
		//Nội dung hàm Update
		
		return true;
	}
	
	public static boolean Delete() {
		//Nội dung hàm Delete
		
		return true;
	}
}
