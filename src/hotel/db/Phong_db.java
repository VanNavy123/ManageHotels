package hotel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hotel.oop.Phong;

public class Phong_db {
	public static boolean Insert() {
		//Nội dung hàm insert
		
		return true;
	}
	
	public static ArrayList<Phong> Select() {
		ArrayList<Phong> List_P = new ArrayList<Phong>();
		
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
	
	public static boolean Update() {
		//Nội dung hàm Update
		
		return true;
	}
	
	public static boolean Delete() {
		//Nội dung hàm Delete
		
		return true;
	}
}
