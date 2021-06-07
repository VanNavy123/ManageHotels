package hotel.app;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;



import hotel.db.Khach_db;
import hotel.db.LoaiPhong_db;
import hotel.db.Phong_db;
import hotel.oop.Khach;
import hotel.oop.LoaiPhong;
import hotel.oop.Phong;

public class Phong_ap {
	
	private static Scanner sc = new Scanner(System.in);
	//Them moi phong:
			public static void nhapThongTinPhong() {
				System.out.println("======Nhap thong tin phong=======");
				
				String luaChon = "";
				do {
					//Nhap thong tin
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
			if (sq >= 999999) 
			{
				System.out.println("Ma phong hien tai da lon hon MP999999!");
				System.exit(0);
			}
			maPhong = s.substring(0, 8 - Integer.toString(sq).length()) + Integer.toString(sq);	
		}
		else maPhong = "MP000001";
		
		return maPhong;
	}

	//Sua thong tin phong
	public static void suaThongTinPhong() {
		Phong ph = null;
		String ma = "";
		
		do {
			System.out.print("Nhap ma phong can sua: ");
			ma = sc.nextLine();
			
			ph = Phong_db.selectOneMP(ma);
			
			if (ph != null) {
				System.out.println("Thong tin phong: " + ph.get_maPhong() + "\t" + ph.get_trangThai()
							+ "\t" + ph.get_maLoaiPhong() + "\t" + ph.get_moTa());
				
				System.out.println("\n\n                  ===============================================");
				System.out.println("	          =              SUA THONG TIN PHONG            =");
				System.out.println("	          ===============================================");
				System.out.println("	          =                                             =");
				System.out.println("	          =              1. Sua trang thai              =");
				System.out.println("	          =              2. Sua mo ta                   =");
				System.out.println("	          =              3. Sua ma loai phong           =");
				System.out.println("	          =              4. Sua tat ca                  =");
				System.out.println("	          =              0. Thoat                       =");
				System.out.println("	          =                                             =");
				System.out.println("	          ===============================================");
				System.out.println("\n\t\t\t    Moi ban nhap lua chon: ");
				
				String luachon;
				boolean flag = false;
				int trangThai_moi = 0;
		        int stt_int = 0;
		        String maLoaiPhong_moi = "";
		        String mota = "";
		        ArrayList<LoaiPhong> arr;
				
				luachon = sc.nextLine();
				switch (luachon)
				{		
					case "1":				
						System.out.println("	          1. SUA TRANG THAI");
						//Kiem tra du lieu nhap vao, neu sai bat nhap lai:
						do {
							try
							{
								System.out.println("\n\t\t     Nhap trang thai moi: ");
								String trangThai = sc.nextLine();
								trangThai_moi = Integer.parseInt(trangThai);
								flag = false;
							}
							catch (Exception e) 
							{
								flag = true;
								System.out.println("\n\t\t     Trang thai khong dung dinh dang!");
							}
						} while (flag);

						ph.set_trangThai((trangThai_moi));
						if (Phong_db.Update(ph))
						{
							System.out.println("Succes!");
						}
						else System.out.println("False!");
						break;
						
					case "2":
						System.out.println("	          2. SUA MO TA");
						System.out.println("\n\t\t     Nhap mo ta moi: ");
						mota = sc.nextLine();

						ph.set_moTa(mota);
						if (Phong_db.Update(ph))
						{
							System.out.println("Succes!");
						}
						else System.out.println("False!");
						break;
						
					case "3":
						System.out.println("	          3. SUA MA LOAI PHONG");
						System.out.println("\n\t\t     Danh sach ma loai phong: ");
						
						arr = LoaiPhong_db.Select();
						
				        for (int i = 0; i < arr.size(); i++) 
				        {
							System.out.println("\t   " + (i + 1) + ". "+ arr.get(i).get_maLoaiPhong());
						}
				        System.out.println("\t   0. Thoat");
				        
				        //Chon ma loai phong moi:
						do {
							try
							{
								System.out.println("\t\t     Chon ma loai phong theo so thu tu: ");
								String stt = sc.nextLine();
								stt_int = Integer.parseInt(stt);
								
								if (stt_int > arr.size() || stt_int < 0)
								{
									System.out.println("\t   Chon sai roi!");
									flag = true;
								}
								else
								{
									if (stt_int == 0) break;
									else
									{
										maLoaiPhong_moi = arr.get(stt_int - 1).get_maLoaiPhong();
										ph.set_maLoaiPhong(maLoaiPhong_moi);
										flag = false;
										if (Phong_db.Update(ph))
										{
											System.out.println("Succes!");
										}
										else System.out.println("False!");
									}
								}
							}
							catch (Exception e) 
							{
								flag = true;
								System.out.println("\n\t   Yeu cau chi nhap chu so!");
							}
						} while (flag);
						
						break;
						
					case "4":
						System.out.println("	          4. SUA TAT CA");
						
						//Nhap trang thai
						do {
							try
							{
								System.out.println("\n\t\t     Nhap trang thai moi: ");
								String trangThai = sc.nextLine();
								trangThai_moi = Integer.parseInt(trangThai);
								flag = false;
							}
							catch (Exception e) 
							{
								flag = true;
								System.out.println("\n\t\t     Trang thai khong dung dinh dang!");
							}
						} while (flag);

						ph.set_trangThai((trangThai_moi));
						
						// Nhap mo ta moi:
						System.out.println("\n\t\t     Nhap mo ta moi: ");
						mota = sc.nextLine();
						ph.set_moTa(mota);
						
						// Nhap ma loai phong
						System.out.println("\n\t\t     Danh sach ma loai phong: ");
						
						arr = LoaiPhong_db.Select();
						
				        for (int i = 0; i < arr.size(); i++) 
				        {
							System.out.println("\t   " + (i + 1) + ". "+ arr.get(i).get_maLoaiPhong());
						}
				        System.out.println("\n\t   0. Thoat");
				        
				        //Chon ma loai phong moi:
						do {
							try
							{
								System.out.println("\t\t     Chon ma loai phong theo so thu tu: ");
								String stt = sc.nextLine();
								stt_int = Integer.parseInt(stt);
								
								if (stt_int > arr.size() || stt_int < 0)
								{
									System.out.println("\n\t   Chon sai roi!");
									flag = true;
								}
								else
								{
									if (stt_int == 0) break;
									else
									{
										maLoaiPhong_moi = arr.get(stt_int - 1).get_maLoaiPhong();
										ph.set_maLoaiPhong(maLoaiPhong_moi);
										flag = false;
									}
								}
							}
							catch (Exception e) 
							{
								flag = true;
								System.out.println("\n\t   Yeu cau chi nhap chu so!");
							}
						} while (flag);
						
						//update
						if (Phong_db.Update(ph))
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
