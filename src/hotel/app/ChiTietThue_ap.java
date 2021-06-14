package hotel.app;

import java.util.ArrayList;
import java.util.Scanner;

import hotel.db.ChiTietThue_db;
import hotel.db.ThuePhong_db;
import hotel.oop.ThuePhong;

public class ChiTietThue_ap {
	private static Scanner sc = new Scanner(System.in);
	public static void hienThiChiTietThuePhong(){
		String maThuePhong;
		ThuePhong_ap.hienThiDanhSachThuePhong();
		System.out.println("Nhap MaThuePhong can thanh toan: ");
		maThuePhong = sc.nextLine();
		ChiTietThue_db.selectThuePhong(maThuePhong);
		
		 
	}
	

}
