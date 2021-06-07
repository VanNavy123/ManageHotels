package hotel.app;

import java.sql.Date;
import java.util.Scanner;

import hotel.db.Khach_db;
import hotel.db.LoaiPhong_db;
import hotel.oop.*;

public class LoaiPhong_ap {
	private static Scanner sc = new Scanner(System.in);

	//Them moi khach hang:
		public static void nhapThongTinLoaiPhong() {
			System.out.println("======Nhap thong tin Loai phong=======");
			
			String luaChon = "";
			do {
				//Nhap thong tin khach
				System.out.print("Nhap Loai phong: ");
				String loaiPhong = sc.nextLine();
				System.out.print("Nhap Gia phong: ");
				String giaPhongStr = sc.nextLine();
				int giaPhong = Integer.parseInt(giaPhongStr);
				System.out.print("So nguoi toi da: ");
				String soNguoiTDStr = sc.nextLine();
				int soNguoiTD = Integer.parseInt(soNguoiTDStr);
				
				
				LoaiPhong lp = new LoaiPhong(creatMaLoaiphong(),loaiPhong, giaPhong, soNguoiTD);
				if (LoaiPhong_db.insert(lp))
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
		//Ham tu dong sinh ma loai phong (ma loai phong co do dai = 8):
		public static String creatMaLoaiphong() 
		{		
			String s = "MLP00000";
			String maLoaiPhong = "";
			String sq_curent = "";
			
			sq_curent = LoaiPhong_db.selectMLP_max();
			
			if (sq_curent != "")
			{
				sq_curent = sq_curent.trim(); //cat bo khoang trang 2 dau
				String tmp = sq_curent.substring(3); //tao chuoi con khong co chu "MK"
				int sq = Integer.parseInt(tmp) + 1; //convert sq sang kieu int va tang 1
				if (sq >= 99999) 
				{
					System.out.println("Ma loai phong hien tai da lon hon MLP99999!");
					System.exit(0);
				}
				maLoaiPhong = s.substring(0, 8 - Integer.toString(sq).length()) + Integer.toString(sq);	
			}
			else maLoaiPhong = "MLP00001";
			
			return maLoaiPhong;
		}
}
