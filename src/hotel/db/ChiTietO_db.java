package hotel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hotel.oop.ChiTietO;
import hotel.oop.ChiTietThue;

public class ChiTietO_db {
	
		public static boolean insert(ChiTietO chiTietO) {
			//Nội dung hàm insert
			boolean tmp = false;
			Connection connect = null;
			PreparedStatement prStmt = null;
			String insertSQL = "INSERT INTO ChiTietO VALUES (?,?,?,?)";
			
			try {
				connect = Provide_db.getConnection();
		
				prStmt = connect.prepareStatement(insertSQL);
				prStmt.setString(1,chiTietO.get_maThuePhong() );
				prStmt.setString(2, chiTietO.get_maKhach());
				prStmt.setString(3, chiTietO.get_maPhong());
				prStmt.setInt(4, chiTietO.get_truongNhom());
				
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
		
		public static boolean kiemTraTonTaiKhach(String maKhachO) {
			Connection connect = null;
			PreparedStatement prStmt = null;
			ResultSet rs = null;
			boolean existKhachFlag = true;
			String selectKhachSql = "SELECT COUNT(*) FROM ChiTietO WHERE MaKhach = ?";
			
			try {
				connect = Provide_db.getConnection();
				prStmt = connect.prepareStatement(selectKhachSql);
				prStmt.setString(1, maKhachO);
				
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
	
	public static ArrayList<ChiTietO> Select() {
		ArrayList<ChiTietO> list_CTO = new ArrayList<ChiTietO>();
		
		return list_CTO;
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
