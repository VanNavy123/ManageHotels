package hotel.app;

import hotel.db.Khach_db;
import hotel.db.Phong_db;

public class Phong_ap {
	
	//Tao ma phong tu dong:
	public static String CreatMaPhong() 
	{		
		String s = "MP000000";
		String maPhong = "";
		String sq_curent = "";
		
		sq_curent = Phong_db.SelectMP_max();
		
		if (sq_curent != "")
		{
			sq_curent = sq_curent.trim(); //cat bo khoang trang 2 dau
			String tmp = sq_curent.substring(2); //tao chuoi con khong co chu "MP"
			int sq = Integer.parseInt(tmp) + 1; //convert sq sang kieu int va tang 1
			maPhong = s.substring(0, 8 - Integer.toString(sq).length()) + Integer.toString(sq);	
		}
		else maPhong = "MP000001";
		
		return maPhong;
	}
}
