package hotel.app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import hotel.db.ChiTietO_db;
import hotel.db.ChiTietThue_db;
import hotel.db.Khach_db;
import hotel.db.Phong_db;
import hotel.db.Provide_db;
import hotel.db.ThuePhong_db;
import hotel.oop.ChiTietO;
import hotel.oop.ChiTietThue;
import hotel.oop.Khach;
import hotel.oop.Phong;
import hotel.oop.ThuePhong;

public class ThuePhong_ap {
	private static Scanner sc = new Scanner(System.in);

	public static void themThuePhong() {
		System.out.println("============DANH SACH KHACH HIEN CO=============");
		int tongChiPhi = 0;
		String maThuePhong = "";
		String maKhachThue = "";
		String choice = "";
		// Hien thi danh sach Khach 
		Khach_ap.showKhach();
		System.out.println("============NHAP THONG TIN THUE PHONG============");
		
		
		do {
				System.out.print("Nhap Ma khach thue: ");
				maKhachThue = sc.nextLine();
				maThuePhong = ThuePhong_db.selectMaThuePhong(maKhachThue);

			if ((maThuePhong == null) || "".equals(maThuePhong) ) {
				maThuePhong = creatMaThuePhong();
				ThuePhong tp = new ThuePhong(maThuePhong, maKhachThue, tongChiPhi);

				if (ThuePhong_db.insert(tp)) {
					System.out.println("Them moi Thue phong thanh cong!");
				} else {
					System.out.println("Them moi Thue phong that bai. Vui long kiem tra lai!");
				}
			} else {

				System.out.println("MaThuePhong da ton tai. Vui Long them vao chi tiet thue");
			}
			   // Them chi tiet thue phong
			System.out.println("================THEM CHI TIET THUE PHONG===============");
			  // Danh sach Phong trong hien co: 
			System.out.println("=============DANH SACH PHONG TRONG HIEN CO=============");
			Phong_ap.hienThiDanhSachPhongTrong();
			boolean flag = false; 
			Phong ph = null;
			int trangThai_moi = 1;
			String maPhong = "";
			System.out.print("Nhap ma phong: "); 
			maPhong = sc.nextLine(); 
			if (maPhong == ChiTietThue_db.selectMaPhong(maThuePhong)) {
				System.out.print("Ban da thue phong nay. Vui long chon Phong khac ");
			} else {
						Date ngayThue = null; 
						Date ngayTra = null; 
						do { 
							try {
									System.out.print("Nhap ngay Thue (Nhap ngay/thang/nam): "); 
									String ngayThueStr = sc.nextLine();
									System.out.print("Nhap ngay Tra (Nhap ngay/thang/nam): "); 
									String ngayTraStr = sc.nextLine(); 
									ngayThue = CheckDataInput.convertToDateVN(ngayThueStr);
									ngayTra = CheckDataInput.convertToDateVN(ngayTraStr); 
									if ((ngayThue == null)|| (ngayTra == null)) { 
										flag = true;
										System.out.print("Nhap ngay khong dung dinh dang!"); } else flag = false; }
							catch (Exception e) { 
								flag = true;
								System.out.print("Nhap ngay khong dung dinh dang!"); }
				  
						} while (flag);
				 
					//System.out.print(ThuePhong_db.selectMaThuePhong(maKhachThue));
						ChiTietThue cTT = new ChiTietThue(ThuePhong_db.selectMaThuePhong(maKhachThue), maPhong,ngayThue,
				  ngayTra ); 
						if (ChiTietThue_db.insert(cTT)){
								System.out.println("Them moi Chi tiet Thue thanh cong!"); 
								ph = Phong_db.selectOneMP(maPhong);
								ph.set_trangThai((trangThai_moi));
								Phong_db.Update(ph);
								
						} else {
								System.out.println("Them moi Ch tiet Thue that bai. Vui long kiem tra lai!"); }
				}
			//Them chi tiet o
			//Nhap ma Khach o
			//Hien thi danh sach Khach
			System.out.println("===============THEM CHI TIET O================");
			String maKhachO;
			int truongNhom;
			String nhapKhachO = "";
			System.out.println("============DANH SACH KHACH HIEN CO============="); 
			Khach_ap.showKhach();
			
			do {
					System.out.print("Nhap ma khach O: "); 
					maKhachO = sc.nextLine(); 
					if (ChiTietO_db.kiemTraTonTaiKhach(maKhachO) == false) {
						System.out.print("Xac nhan Truong nhom: ");
						String truongNhomStr = sc.nextLine();
						truongNhom = Integer.parseInt(truongNhomStr);
						ChiTietO chiTietO = new ChiTietO(maThuePhong, maKhachO, maPhong, truongNhom);

						if (ChiTietO_db.insert(chiTietO)) {
							System.out.println("Them moi Chi tiet o thanh cong!");
						} else {
							System.out.println("Them moi Chi tiet o that bai. Vui long kiem tra lai!");
						}
					} else {
						System.out.println("Ma Khach o da duoc nhap. Vui long kiem tra lai!...");
					}
					
					System.out.println("Ban co muon nhap thue them Khach O nao nua khong (Y/N): ");
					nhapKhachO = sc.nextLine();
			} while ("Y".equalsIgnoreCase(nhapKhachO));
			
			// Cho lua chon co nhap tiep khong
			System.out.print("Ban co muon nhap thue them phong nao nua khong (Y/N): ");
			choice = sc.nextLine();
		} while ("Y".equalsIgnoreCase(choice));
	}

	// Ham tu dong sinh MaThuePhong (MaThuePhong co do dai = 9):
	public static String creatMaThuePhong() {
		String s = "MTP000000";
		String MaThuePhong = "";
		String sq_curent = "";

		sq_curent = ThuePhong_db.SelectMTP_max();

		if (sq_curent != "") {
			sq_curent = sq_curent.trim(); // cat bo khoang trang 2 dau
			String tmp = sq_curent.substring(3); // tao chuoi con khong co chu "MK"
			int sq = Integer.parseInt(tmp) + 1; // convert sq sang kieu int va tang 1
			if (sq >= 999999) {
				System.out.println("Ma phong hien tai da lon hon MTP999999!");
				System.exit(0);
			}
			MaThuePhong = s.substring(0, 9 - Integer.toString(sq).length()) + Integer.toString(sq);
		} else
			MaThuePhong = "MTP000001";

		return MaThuePhong;
	}
	//Hien thi danh sach Thue Phong
	public static void hienThiDanhSachThuePhong(){
		
		  ArrayList <ThuePhong> arr = null;
		  
		  arr = ThuePhong_db.select();
		  
		  for (int i = 0; i < arr.size(); i++) { System.out.println("\t   " + (i + 1) +
		  ". "+ arr.get(i).toString()); }
		 
	}
}
