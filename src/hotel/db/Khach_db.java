package hotel.db;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hotel.oop.Khach;

public class Khach_db 
{
	//Them moi khach hang:
	public static boolean insert(Khach kh) 
	{
		boolean tmp = false;
		Connection connect = null;
		PreparedStatement prStmt = null;
		String insertSQL = "INSERT INTO Khach VALUES (?,?,?,?,?)";
		
		try {
			connect = Provide_db.getConnection();
	
			prStmt = connect.prepareStatement(insertSQL);
			prStmt.setString(1, kh.getMaKhach());
			prStmt.setString(2, kh.getHoTen());
			prStmt.setString(3, kh.getCmnd());
			prStmt.setDate(4, kh.getNgaySinh());
			prStmt.setString(5, kh.getDiaChi());
			
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
	
	//Lay danh sach khach hang:
	public static ArrayList<Khach> select() 
	{
		String query = "Select * from Khach";
		ArrayList<Khach> khachList = new ArrayList<Khach>();
		
		Connection connect = null;
		PreparedStatement prStmt = null;

		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				String maKhach = rs.getString("MaKhach");
				String hoTen = rs.getString("HoTen");
				String cmnd = rs.getString("CMND");
				Date ngaySinh = rs.getDate("NgaySinh");
				String diaChi = rs.getString("DiaChiThuongTru");
				
				Khach kh = new Khach(maKhach, hoTen, cmnd, diaChi, ngaySinh);
				khachList.add(kh);
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
		return khachList;
	}
	
	//Lay maKH max:
	public static String selectMK_max() {
		String tmp = "";
		String query = "SELECT TOP 1 MaKhach FROM dbo.Khach ORDER BY MaKhach DESC";
		
		Connection connect = null;
		PreparedStatement prStmt = null;

		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				tmp = rs.getString("MaKhach");
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
	
	public static boolean update(Khach kh) 
	{
		boolean tmp = false;
		Connection connect = null;
		PreparedStatement prStmt = null;
		String query = "UPDATE Khach SET HoTen = ?, CMND = ?, NgaySinh = ?, DiaChiThuongTru = ?  WHERE MaKhach = ?";
		
		try 
		{
			connect = Provide_db.getConnection();
			PreparedStatement preparedStm = connect.prepareStatement(query);
			preparedStm.setString(1, kh.getHoTen());
			preparedStm.setString(2, kh.getCmnd() );
			preparedStm.setDate(3, kh.getNgaySinh() );
			preparedStm.setString(4, kh.getDiaChi() );
			preparedStm.setString(5, kh.getMaKhach() );
			
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
	
	public static boolean kiemTraTonTaiKhach(String maKhach) {
		Connection connect = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		boolean existKhachFlag = true;
		String selectKhachSql = "SELECT COUNT(*) FROM Khach WHERE MaKhach = ?";
		
		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(selectKhachSql);
			prStmt.setString(1, maKhach);
			
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
	public static Khach selectOneMK(String maKh) {
		Khach kh = null;
		String query = "SELECT * FROM Khach WHERE MaKhach = ?";
		Connection connect = null;
		PreparedStatement prStmt = null;
		try {
			connect = Provide_db.getConnection();
			prStmt = connect.prepareStatement(query);
			prStmt.setString(1, maKh);
			ResultSet rs = prStmt.executeQuery();
			while (rs.next()) {
				String maKhach = rs.getString("MaKhach");
				String hoTen = rs.getString("HoTen");
				String cmnd = rs.getString("CMND");
				Date ngaySinh = rs.getDate("NgaySinh");
				String diaChi = rs.getString("DiaChiThuongTru");
				
				kh = new Khach(maKhach, hoTen, cmnd, diaChi, ngaySinh);
				
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
		return kh;
	}
}
