package hotel.app;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import hotel.db.Khach_db;
import hotel.db.LoaiPhong_db;
import hotel.db.Phong_db;
import hotel.oop.*;

public class LoaiPhong_ap {
	private static Scanner sc = new Scanner(System.in);

	//Them moi loai phong:
		public static void nhapThongTinLoaiPhong() {
			System.out.println("======Nhap thong tin Loai phong=======");
			
			boolean flag = false;
			String luaChon = "";
			String loaiPhong = "";
			int giaPhong = 0;
			int soNguoiToiDa = 0;
			
			do {
				//Nhap loai phong
				System.out.print("Nhap Loai phong: ");
				loaiPhong = sc.nextLine();
				loaiPhong = CheckDataInput.chuannHoa_str(loaiPhong);
				
				//Nhap gia phong:
				do {
					try
					{
						System.out.println("Nhap gia phong: ");
						String giaPhong_input = sc.nextLine();
						giaPhong = Integer.parseInt(giaPhong_input);
						flag = false;
					}
					catch (Exception e) 
					{
						flag = true;
						System.out.println("\n\t\t     Gia phong khong dung dinh dang!");
					}
				} while (flag);
				
				// Nhap so nguoi toi da
				do {
					try
					{
						System.out.println("Nhap gia phong: ");
						String soNguoi_input = sc.nextLine();
						soNguoiToiDa = Integer.parseInt(soNguoi_input);
						flag = false;
					}
					catch (Exception e) 
					{
						flag = true;
						System.out.println("\n\t\t     So nguoi toi da khong dung dinh dang!");
					}
				} while (flag);
				
				LoaiPhong lp = new LoaiPhong(creatMaLoaiphong(), loaiPhong, giaPhong, soNguoiToiDa);
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
		
		//Sua thong tin phong
		public static void suaThongTinPhong() {
			LoaiPhong ph = null;
			String ma = "";
			
			do {
				System.out.print("Nhap ma loai phong can sua: ");
				ma = sc.nextLine();
				
				ph = LoaiPhong_db.selectOneLP(ma);
				
				if (ph != null) {
					System.out.println("Thong tin phong: " + ph.get_maLoaiPhong() + "\t" + ph.get_loaiPhong()
								+ "\t" + ph.get_giaPhong() + "\t" + ph.get_soNguoiToiDa());
					
					System.out.println("\n\n                  ===============================================");
					System.out.println("	          =           SUA THONG TIN LOAI PHONG          =");
					System.out.println("	          ===============================================");
					System.out.println("	          =                                             =");
					System.out.println("	          =              1. Sua loai phong              =");
					System.out.println("	          =              2. Sua gia phong               =");
					System.out.println("	          =              3. Sua so nguoi toi da         =");
					System.out.println("	          =              4. Sua tat ca                  =");
					System.out.println("	          =              0. Thoat                       =");
					System.out.println("	          =                                             =");
					System.out.println("	          ===============================================");
					System.out.println("\n\t\t\t    Moi ban nhap lua chon: ");
					
					String luachon;
					boolean flag = false;
					int giaPhong_moi = 0;
			        int soNguoi_moi = 0;
			        String maLoaiPhong_moi = "";
			        String loaiphong = "";
					
					luachon = sc.nextLine();
					switch (luachon)
					{		
						case "1":				
							System.out.println("	          1. SUA LOAI PHONG");
							System.out.println("\n\t\t     Nhap loai phong moi: ");
							loaiphong = sc.nextLine();
							loaiphong = CheckDataInput.chuannHoa_str(loaiphong);
							ph.set_loaiPhong(loaiphong);
							if (LoaiPhong_db.update(ph))
							{
								System.out.println("Succes!");
							}
							else System.out.println("False!");
							break;
							
						case "2":
							System.out.println("	          2. SUA GIA PHONG");
							//Kiem tra du lieu nhap vao, neu sai bat nhap lai:
							do {
								try
								{
									System.out.println("\n\t\t     Nhap gia phong moi: ");
									String giaPhong_input = sc.nextLine();
									giaPhong_moi = Integer.parseInt(giaPhong_input);
									ph.set_giaPhong((giaPhong_moi));
									flag = false;
								}
								catch (Exception e) 
								{
									flag = true;
									System.out.println("\n\t\t     Gia phong khong dung dinh dang!");
								}
							} while (flag);

							if (LoaiPhong_db.update(ph))
							{
								System.out.println("Succes!");
							}
							else System.out.println("False!");
							break;
							
						case "3":
							System.out.println("	          3. SUA SO NGUOI TOI DA");
							//Kiem tra du lieu nhap vao, neu sai bat nhap lai:
							do {
								try
								{
									System.out.println("\n\t\t     Nhap so nguoi toi da moi: ");
									String soNguoi_input = sc.nextLine();
									soNguoi_moi = Integer.parseInt(soNguoi_input);
									flag = false;									
									ph.set_soNguoiToiDa((soNguoi_moi));
								}
								catch (Exception e) 
								{
									flag = true;
									System.out.println("\n\t\t     So nguoi khong dung dinh dang!");
								}
							} while (flag);

							if (LoaiPhong_db.update(ph))
							{
								System.out.println("Succes!");
							}
							else System.out.println("False!");
							break;
							
						case "4":
							System.out.println("	          4. SUA TAT CA");
							//Nhap Loai phong:
							System.out.println("\n\t\t     Nhap loai phong moi: ");
							loaiphong = sc.nextLine();
							loaiphong = CheckDataInput.chuannHoa_str(loaiphong);
							ph.set_loaiPhong(loaiphong);
							
							// Nhap gia phong:
							do {
								try
								{
									System.out.println("\n\t\t     Nhap gia phong moi: ");
									String giaPhong_input = sc.nextLine();
									giaPhong_moi = Integer.parseInt(giaPhong_input);
									ph.set_giaPhong((giaPhong_moi));
									flag = false;
								}
								catch (Exception e) 
								{
									flag = true;
									System.out.println("\n\t\t     Gia phong khong dung dinh dang!");
								}
							} while (flag);
							
							//Nhap so nguoi toi da:
							do {
								try
								{
									System.out.println("\n\t\t     Nhap so nguoi toi da moi: ");
									String soNguoi_input = sc.nextLine();
									soNguoi_moi = Integer.parseInt(soNguoi_input);
									flag = false;									
									ph.set_soNguoiToiDa((soNguoi_moi));
								}
								catch (Exception e) 
								{
									flag = true;
									System.out.println("\n\t\t     So nguoi khong dung dinh dang!");
								}
							} while (flag);
	
							//update
							if (LoaiPhong_db.update(ph))
							{
								System.out.println("Succes!");
							}
							else System.out.println("False!");
							
							break;
							
						default:
							System.out.println("\nGoodbye!");
							break;
					}

				} else {
					System.out.println("Thong tin khach hang khong ton tai trong he thong");
					
				}
				
			} while (ph == null);
			
			
		}
}
