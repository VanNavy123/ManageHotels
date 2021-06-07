package hotel.app;

import hotel.db.Khach_db;
import hotel.db.ThuePhong_db;

public class ThuePhong_ap 
{
	//Ham tu dong sinh MaThuePhong (MaThuePhong co do dai = 9):
	public static String CreatMaThuePhong() 
	{		
		String s = "MTP000000";
		String MaThuePhong = "";
		String sq_curent = "";
		
		sq_curent = ThuePhong_db.SelectMTP_max();
		
		if (sq_curent != "")
		{
			sq_curent = sq_curent.trim(); //cat bo khoang trang 2 dau
			String tmp = sq_curent.substring(3); //tao chuoi con khong co chu "MK"
			int sq = Integer.parseInt(tmp) + 1; //convert sq sang kieu int va tang 1
			MaThuePhong = s.substring(0, 9 - Integer.toString(sq).length()) + Integer.toString(sq);	
		}
		else MaThuePhong = "MTP000001";
		
		return MaThuePhong;
	}
}
