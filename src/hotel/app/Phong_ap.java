package hotel.app;

import java.util.Scanner;

import hotel.db.Khach_db;
import hotel.db.LoaiPhong_db;
import hotel.db.Phong_db;
import hotel.oop.LoaiPhong;
import hotel.oop.Phong;

public class Phong_ap {
	
	private static Scanner sc = new Scanner(System.in);
	//Them moi phong:
			public static void nhapThongTinPhong() {
				System.out.println("======Nhap thong tin phong=======");
				
				String luaChon = "";
				do {
					//Nhap thong tin khach
					System.out.print("Nhap Trang Thai phong: ");
					String trangThaiPhongStr = sc.nextLine();
					int trangThaiPhong = Integer.parseInt(trangThaiPhongStr);
					System.out.print("Ma Loai phong: ");
					String maLoaiPhong = sc.nextLine();
					System.out.print("Mo ta: ");
					String moTa = sc.nextLine();
					
					Phong ph = new Phong(creatMaPhong(),trangThaiPhong, maLoaiPhong, moTa);
					if (Phong_db.insert(ph))
					{
						System.out.println("Them moi loai phong thanh cong!");
					}
					else
					{
						System.out.println("Them moi khach hang that bai. Vui long kiem tra lai!");
					}
					
					//Cho lua chon co nhap tiep khong
					System.out.print("Ban co muon nhap them khach hang nao nua khong (Y/N): ");
					luaChon = sc.nextLine();
				} while ("Y".equalsIgnoreCase(luaChon));
			}
	
	//Ham tu dong sinh ma phong (ma phong co do dai = 8):
	public static String creatMaPhong() 
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
			if (sq >= 99999) 
			{
				System.out.println("Ma phong hien tai da lon hon MP999999!");
				System.exit(0);
			}
			maPhong = s.substring(0, 8 - Integer.toString(sq).length()) + Integer.toString(sq);	
		}
		else maPhong = "MP000001";
		
		return maPhong;
	}
}
